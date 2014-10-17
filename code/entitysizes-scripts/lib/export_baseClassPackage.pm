############################################################################
#                                                                          #
# Author: hgruber            											   #
#                                                                          #
# Synopsis: Base library for class and package handling                    #
#           inlcuding interfaces and namespaces                            #
#																		   #
# Categories: Design Metrics                                               #
#                                                                          #
# Languages: Java, C/C++, C#                                               #
#                                                                          #
############################################################################

package export_baseClassPackage;
use strict;
use export_base;
use export_baseMethod;
use Understand;
use Tree;
use vars qw(@ISA @EXPORT @EXPORT_OK);

use constant UNNAMED_ENTITY => '[unnamed]';
use constant CONFIG_KNOWN_INTERFACES => 'Global.KnownInterfaces';
use constant CONFIG_EXCEPTOIN_CLASSES => 'Global.ExceptionClasses';

require Exporter;
@ISA = qw(Exporter);
@EXPORT = qw( getPackageName getPackageTypeName getClassName getOuterClass getPackageForClass 
              isClassType isInterfaceType isExceptionType isEnumType isTemplateParameter 
              isSuperClass resolveType
              getClassElements getClassMethods getClassAttributes getClassElementsCached 
              getUnresolvedSuperClasses buildPackageTree getPackageReference ); 
@EXPORT_OK = qw(UNNAMED_ENTITY);             


my @IGNORE_UNRESOLVED = ("java.lang.Object");

############################################################################
# get the name of the given class or package entity (may be undef)         #
# @param ent the entity to get the name for                                # 
############################################################################
sub getEntityName($) {
	my $ent = shift;
	return ($ent)? $ent->longname() : UNNAMED_ENTITY;
}

############################################################################
# get the name of the given package (may be undef)                         #
# @param package the package to get the name for                           # 
############################################################################
sub getPackageName {
	my $package = shift;
	return getEntityName($package);
}

############################################################################
# get the type name of the given package, i.e., package or namespace       #
# @param package the package entity to get the type name for               # 
############################################################################
sub getPackageTypeName {
	my $package = shift;
	return ($package && index($package->kind()->longname(),'Package')>=0)? 'package' : 'namespace';
}

############################################################################
# get the name of the given class (may be undef)                           #
# @param class the class to get the name for                               # 
############################################################################
sub getClassName {
	my $class = shift;
	# test for inner classes to return name of outer class
	$class = getOuterClass($class);
	return getEntityName($class);
}

############################################################################
# get the outer class of a possible inner class, returns the class itself  #
# if it's already an outer class                                           #
# @param ent the entity to get the correct class for                       # 
############################################################################
sub getOuterClass {
	my $ent = shift;
	return undef unless $ent;
	my $class;
	$class = $ent if isClassType($ent);
	my $ref = $ent->ref('definein');
	if ($ref && $ref->ent()) {
		my $testEnt = getOuterClass($ref->ent());
		$class = $testEnt if ($testEnt);
	}
	return $class;
}


############################################################################
# get the package/namespace for the given class entity (or undef)          #
# @param ent the entity (class) to get the package for                     # 
############################################################################
sub getPackageForClass($) {
	my $ent = shift;
	return undef unless $ent;
	my $ref = $ent->ref('containin');
	return $ref->ent() if $ref;
	$ref = $ent->ref('definein');
	if ($ref && isClassType($ref->ent())) {
		# it's an inner class or a file
		return getPackageForClass($ref->ent());
	} elsif ($ref && $ref->ent() && $ref->ent()->kindname() eq "Namespace") {
		# it's a namespace
		return $ref->ent();
	} elsif ($ref && $ref->ent()) {
		# it's possibly an anonymous inner class
		return getPackageForClass($ref->ent());
	} else {
		# it's the default package/namespace
		return undef;
	}
}

############################################################################
# return true if the entity is of any class type, otherwise false          #
# @param ent the entity to test for class type                             # 
############################################################################
sub isClassType {
	my $ent = shift;
	#return ($ent && (index($ent->kindname(),"Class")>=0 || index($ent->kindname(),"Struct")>=0 || index($ent->kindname(),"Interface")>=0) || index($ent->kindname(),"Enum")>=0);
	my $kindname = ($ent)? $ent->kind()->longname() : ''; 
	return ($ent && index($kindname,'Type')>=0 && index($kindname,'Typedef')<0 && index($kindname,'Unnamed')<0 && index($kindname,'Union')<0 && index($kindname,'TemplateParameter')<0); 
}

############################################################################
# tests if a given entity is an interface                                  #
# @param db the database object                                            #
# @param ent the entity to test for interface type                         # 
# @return true (1) if the entity is an interface, otherwise false (undef)  #
############################################################################
sub isInterfaceType {
	my $db = shift;
	my $ent = shift;
	if (isClassType($ent)) {		
		my @knownInterfaces = split(/,/,getConfiguration()->getProperty(CONFIG_KNOWN_INTERFACES));
		return 1 if (grep { $ent->longname() =~ /^$_$/ } @knownInterfaces);
		my $language = lc($db->language());
		if (lc($db->language()) eq 'c') {
			# an interface in C++ is a class without non-static attributes and wihtout non-pure methods
			my @attributes = grep { index($_->kindname(),'Static')<0 || index($_->type(),'const')<0 } $ent->ents('Define', 'Object,Type');
			my @enumarators = $ent->ents('Define', 'Enumerator');
			my @declareImplicit = $ent->refs('Declare Implicit');
			my @methods = $ent->ents('Define,Declare ~Implicit', 'Function ~Pure');
			my %pureMethods = map {$_->simplename() => 1} $ent->ents('Declare', 'Function Pure');
			my $countMethods = 0;
			foreach my $method (@methods) {
				if (isConstructor($ent, $method) || isDestructor($ent, $method)) {
					# do not count empty constructors or destructors (may also be public)
					#my $isProtected = index($method->kind()->longname(),'Protected')>=0;
					my @methodWork = $method->refs('Use,Set ~Implicit,Call ~Implicit,Modify ~Implicit,Type,Throw,Cast,Create');
					$countMethods++ if scalar(@methodWork)>0;
				} else {
					$countMethods++;
				}
			}
			my $countImplicit = 0;
			foreach my $implicit (@declareImplicit) {
				# do not count implicit methods that are found as normal functions and that are pure
				$countImplicit++ unless $pureMethods{$implicit->ent()->simplename()};
			}
			if ($countMethods==0 && $countImplicit==0 && scalar(@attributes)==0 && scalar(@enumarators)==0) {
				# check all base classes
				for my $base ($ent->refs('Base')) {
					return undef if (!isInterfaceType($db, $base->ent())); 
				}
				return 1;
			}
		} else {
			# for other languages look for the keyword 'Interface'
			return (index($ent->kind()->longname(),'Interface')>=0)
		}		
	}
	return undef;
}

############################################################################
# test whether the given class is an exception type by comparing its super #
# classes with a list of possible names of exception base classes          #
# @param class the class to test whether its an exception                  #
# @return true (1) if the class is an exception, otherwise false (undef)   #
############################################################################
sub isExceptionType {
	my $class = shift;
	my @exceptionClasses = split(/,/,getConfiguration()->getProperty(CONFIG_EXCEPTOIN_CLASSES));
	return 1 if (grep { $class->longname() =~ /^$_$/ } @exceptionClasses);
	foreach my $superClass ($class->ents('Base ~Private ~Protected,Implement,Extend')) {
		return 1 if isExceptionType($superClass);
	}
	return undef;
}


############################################################################
# checks if the given class is an enumarator by comparing the kindname     #
# @param class the class to test whether its an enumarator                 #
# @return true (1) if the class is an exception, otherwise false (undef)   #
############################################################################
sub isEnumType {
	my $entity = shift;
	return index($entity->kindname(),'Enum')>=0;
}


############################################################################
# checks if the given entity is a template parameter / type variable       #
# @param entity the entity to check for template parameter                 #
# @return true (1) if the class is an exception, otherwise false (undef)   #
############################################################################
sub isTemplateParameter {
	my $entity = shift;
	return $entity->kindname() eq 'TypeVariable' || $entity->kindname() eq 'Template Parameter';
}


############################################################################
# test whether a given class is a super class (any hierarchy level) of a   #
# given class                                                              #
# @param class the class to test whether another is a super class          #
# @param super the potential super class                                   #
# @return true (1) if super is a super class, otherwise false (undef)      #
############################################################################
sub isSuperClass {
	my $class = shift;
	my $super = shift;
	foreach my $superClass ($class->ents('Base ~Private ~Protected,Implement,Extend')) {
		return 1 if ($superClass->longname() eq $super->longname() || isSuperClass($superClass,$super));
	}
	return undef;
}

############################################################################
# if it's a typedef the real type will be returned                         #
# @param typeEntity the entity for the type to be resolved                 #
# @return the entity for the type or the original one                      #
############################################################################
sub resolveType {
	my $typeEntity = shift;
	if ($typeEntity && index($typeEntity->kindname(),'Typedef')>=0) {
		my $realType = resolveType($typeEntity->ents('Typed','Type'));
		$typeEntity = $realType if $realType;
	}
	return $typeEntity;
}

############################################################################
# get all methods and attributes that are defined for a class (including   #
# elements of super class)                                                 #
# @param ent the class entity to get the elements for                      #
# @return a list with methods and attributes                               #
############################################################################
sub getClassElements {
	my $ent = shift;
	my @elements;
	push(@elements, getClassMethods($ent));
	push(@elements, getClassAttributes($ent));
	return @elements;
}

############################################################################
# get all methods that are defined for a class (including non-private      #
# methods of super class)                                                  #
# @param ent the class entity to get the elements for                      #
# @return a list with methods                                              #
############################################################################
sub getClassMethods {
	my $ent = shift;
	my $methodMap = {};
	foreach my $method ($ent->ents('Define,Declare','Function,Method ~Stub,Property,Indexer')) {
		$methodMap->{getMethodName($method)} = $method;
	}
	addMethodsOfSuperClass($ent, $methodMap);
	return values %$methodMap;
} 

############################################################################
# add all methods of the superclass to the given method list               #
# @param subClass the sub-class to add the super class' methods            #
# @param methodList a reference to the method list                         #
############################################################################
sub addMethodsOfSuperClass {
	my $subClass = shift;
	my $methodMap = shift;
	foreach my $superClass ($subClass->ents('Base Public,Implement,Extend')) {
		foreach my $method ($superClass->ents('Define,Declare','Function ~Private,Method ~Private ~Stub,Property ~Private,Indexer ~Private')) {
			$methodMap->{getMethodName($method)} = $method;
		}
		addMethodsOfSuperClass($superClass, $methodMap);
	}
}

############################################################################
# get all attributes that are defined for a class (including non-private   #
# attributes of super class)                                               #
# @param ent the class entity to get the elements for                      #
# @return a list with attributes                                           #
############################################################################
sub getClassAttributes {
	my $ent = shift;
	my $attributeMap = {};
	foreach my $attribute ($ent->ents('Define','Object,Variable,Type,Field')) {
		$attributeMap->{getMethodName($attribute)} = $attribute;
	}
	addMethodsOfSuperClass($ent, $attributeMap);
	return values %$attributeMap;
} 

############################################################################
# add all attributes of the superclass to the given attribute list         #
# @param subClass the sub-class to add the super class' attributes         #
# @param methodList a reference to the attribute list                      #
############################################################################
sub addAttributesOfSuperClass {
	my $subClass = shift;
	my $attrMap = shift;
	foreach my $superClass ($subClass->ents('Base Public,Implement,Extend')) {
		foreach my $attribute ($superClass->ents('Define','Object ~Private,Variable ~Private,Type ~Private,Field ~Private')) {
			$attrMap->{getMethodName($attribute)} = $attribute;
		}
		addAttributesOfSuperClass($superClass, $attrMap);
	}
}

############################################################################
# get the class elements (methods,attributes), first check the map for     #
# performance reason                                                       #
# @param ent the class entity to get the elements for                      #
# @param classElements the map with element lists for classes              #
# @return a list with class' elements                                      #
############################################################################
sub getClassElementsCached {
	my $ent = shift;
	my $classElements = shift;
	my $methods = $classElements->{$ent->longname()};
	if ($methods) {
		return @$methods;
	} else {
		my @methodList = getClassElements($ent);
		$classElements->{$ent->longname()} = \@methodList;
		return @methodList;
	}
}

############################################################################
# get all super classes of a class that are either unresolved or library   #
# @param class the class to get the unresolved super classes for           #
# @return a list with unresolved super classes                             #
############################################################################
sub getUnresolvedSuperClasses {
	my $class = shift;
	my $unresolvedMap = shift;
	$unresolvedMap = {} if !$unresolvedMap;
	if ($class->library() || index($class->kind()->longname(),'Unresolved')>=0) {
		$unresolvedMap->{$class->longname()} = 1;
	} else {
		foreach my $superClass ($class->ents('Base,Extend')) {
			next if (grep {$_ eq $superClass->longname()} @IGNORE_UNRESOLVED);
			foreach my $unresolved (getUnresolvedSuperClasses($superClass,$unresolvedMap)) {
				$unresolvedMap->{$unresolved} = 1;
			}
		}
	}
 	return keys %$unresolvedMap;
}

############################################################################
# build a hierarchical tree depending on the package names, they are either#
# separated by . or ::                                                     #
# @param packageMap the hash map with package names and package objects    # 
############################################################################
sub buildPackageTree {
	my $packageMap = shift;
	my $nodeMap = {};
	# unnamed is always the root of the tree
	my $tree = createTreeNode($nodeMap, UNNAMED_ENTITY);
	for my $packageName (sort(keys %$packageMap)) {
		next if $packageName eq UNNAMED_ENTITY;
		my $node = createTreeNode($nodeMap, $packageName);
		my $parentName = getParentPackageName($packageMap, $packageName);
		# because of alphabetical order, the parent must already exist in tree
		my $parent = $nodeMap->{$parentName};
		$parent->add_child($node);
	}
	return $tree, $nodeMap;
}
sub createTreeNode {
	my $nodeMap = shift;
	my $nodeName = shift;
	my $node = Tree->new($nodeName);
	$nodeMap->{$nodeName} = $node;
	return $node;
}

############################################################################
# get the parent name of the package where parts are separated by . or ::  #
# if parent does not exist, its parent is searched, at least UNNAMED_ENTITY#
# is returned                                                              #
# @param packageMap the hash map with package names and package objects    #
# @param packageName the name of the package to get the parent for         # 
############################################################################
sub getParentPackageName {
	my $packageMap = shift;
	my $packageName = shift;
	my $index = rindex($packageName, '.');
	$index = rindex($packageName, '::') if $index<0;
	if ($index<0) {
		return UNNAMED_ENTITY;
	} else {
		my $parentName = substr($packageName, 0, $index);
		return ($packageMap->{$parentName})? $parentName : getParentPackageName($packageMap, $parentName);
	}
}

############################################################################
# get the reference to the package, this can be the file name and a line or#
# a string with \*.* and an undef line value                               #
# @param package the package entity to get the reference for               #
# @return the reference to the package as string and a line (can be undef) #
############################################################################
sub getPackageReference {
	my $package = shift;
	return (undef,undef) unless $package;
	my $pkgName = getPackageName($package);
	if (index($package->kind()->longname(),'Package')>=0) {
		# a Java package is always a directory
		return ($pkgName.'\*.*',undef);
	} else {
		# find the file for the namespace
		my @fileRefs = $package->refs('Declarein');
		return (scalar(@fileRefs)==1)? (@fileRefs[0]->file()->longname(),@fileRefs[0]->line()) : ($pkgName.'\*.*',undef);
	}
}

############################################################################
# evaluate my package to TRUE                                              #
############################################################################
1
__END__