############################################################################
#                                                                          #
# Author: harald.gruber@jku.at                                             #
#                                                                          #
# Synopsis: Write size data for various entities into a file.              #
#                                                                          #
# Categories: Project Metrics, Entity Metrics                              #
#                                                                          #
# Languages: Java                                                          #
#                                                                          #
# Usage: entity_sizes_java -db database [-export path] [-cutpath path]     #
#                                                                          #
# Hints: - The program extracts information about packages, classes,       #
#          interfaces, enums, and methods.                                 #
#        - Anonymous classes can have a different name (number) from the   #
#          name given by the Java compiler.                                #
#                                                                          #
############################################################################

package entity_sizes_java;
use strict;
use Understand;
use entity_sizes_base;

############################################################################
# loop entities                                                            #
############################################################################
sub loopEntitiesJava {
	my ($db) = @_[0];
	setPathSeparator('\.');
	loopPackagesJava($db);
	loopClassesJava($db);
	loopInterfacesJava($db);
	loopEnumsJava($db);
	loopMethodsJava($db);	
}

############################################################################
# loop packages                                                            #
############################################################################
sub loopPackagesJava {
	my ($db) = @_[0];
	foreach my $ent ( $db->ents("Package") ) {
		printEntity($ent, "Package", $ent->longname, "", undef);
	}
}

############################################################################
# loop classes (class only classes and abstract classes)                   #
############################################################################
sub loopClassesJava {
	my ($db) = @_[0];
	loopComplexTypesJava($db, "Class");
	# use different types onw (class, interface, enum) instead of type
}

############################################################################
# loop interfaces                                                          #
############################################################################
sub loopInterfacesJava {
	my ($db) = @_[0];
	loopComplexTypesJava($db, "Interface");
}

############################################################################
# loop enums                                                               #
############################################################################
sub loopEnumsJava {
	my ($db) = @_[0];
	loopComplexTypesJava($db, "Enum");
}

############################################################################
# loop complex java types (class, interface, enum)                         #
############################################################################
sub loopComplexTypesJava {
	my ($db) = @_[0];
	my ($type) = @_[1];
	foreach my $ent ( $db->ents($type." ~Anonymous ~Unknown ~Unresolved") ) {
	#foreach my $ent ( $db->ents($type." ~Unknown ~Unresolved") ) {
		next if $ent->library();
		next if $type eq "Class" && index($ent->kindname,"Enum")>=0;
		#my $path = getClassPathNameJava($ent);
		my $path = getPackageNameJava($ent);
		my $name = getClassNameJava($ent);
		printEntity($ent, $type, $name, $path, $ent->refs("Definein"));
	}
}

############################################################################
# loop methods                                                             #
############################################################################
sub loopMethodsJava {
	my ($db) = @_[0];
	foreach my $ent ( $db->ents("Method ~Unknown ~Unresolved") ) {
		#my $path = getClassPathNameJava($ent);
		my $class = $ent->ref("Definein")->ent();
		my $path = getPackageNameJava($class).".".getClassNameJava($class);
		my $name = getMethodNameJava($ent);
		printEntity($ent, "Method", $name, $path, $ent->refs("Definein"));
	}
}

############################################################################
# get the path to a package as entity (for Java)                            #
############################################################################
sub getPackageEntityJava {
	my ($class) = @_[0];
	my $package = $class->ref("Containin");
	return $package->ent() if ($package);
	my $parentclass = $class->ref("Definein");
	return ($parentclass)? getPackageEntityJava($parentclass->ent()) : undef;
}

############################################################################
# get the path to a package as name (for Java)                             #
############################################################################
sub getPackageNameJava {
	my ($class) = @_[0];
	my $package = getPackageEntityJava($class);
	return ($package)? $package->longname : "";
}

############################################################################
# get the entity of the outer class or undef (for Java)                    #
############################################################################
sub getOuterClassJava {
	my ($ent) = @_[0];
	my $defRef = $ent->ref("Definein");
	if ($defRef) {
		my $kind = $defRef->ent()->kindname;
		if (index($kind,"Class")>=0 || index($kind,"Interface")>=0) {
			return $defRef->ent();
		} elsif (index($kind,"File")>=0 || index($kind,"Package")>=0) {
			return undef;
		} else {
			return getOuterClassJava($defRef->ent());
		}
	} else {
		return undef;
	}
}

############################################################################
# get the name of the class (for Java)                                     #
############################################################################
sub getClassNameJava {
	my ($class) = @_[0];
	my $name = getLastNamePart($class->longname);
	if (index($name,"(Anon_")==0) {
		# remove the additional Anon prefix for anonymous classes
		$name = substr($name,6,length($name)-7);			
	}	
	my $outerclass = getOuterClassJava($class);
	$name = getClassNameJava($outerclass).'$'.$name if $outerclass;
	return $name;
}

############################################################################
# get the name of the method (for Java)                                    #
############################################################################
sub getMethodNameJava {
	my ($method) = @_[0];
	my $name = getMethodName($method);
	
	# get throws definitions (not necessary for identifying)
	#my $throws = getMethodThrowDeclarations($method);
	#$name = $name." throws ".$throws if $throws ne "";
	
	return $name;
}

############################################################################
# return a list of declared exceptions by the method (deprecated)          #
############################################################################
sub getMethodThrowDeclarations {
	my ($method) = @_[0];
	my $throws = "";
   	my $first = 1;
    foreach my $throwref ($method->refs("Throw")) {
      $throws = $throws."," unless $first;
      $throws = $throws.getLastNamePart($throwref->ent()->longname);
      $first = 0;
    }	
	return $throws;	
}

############################################################################
# evaluate my package to TRUE                                              #
############################################################################
1