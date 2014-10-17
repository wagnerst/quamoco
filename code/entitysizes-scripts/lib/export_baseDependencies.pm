############################################################################
#                                                                          #
# Author: hgruber            											   #
#                                                                          #
# Synopsis: Base library for class and package dependencies                #
#																		   #
# Categories: Design Metrics                                               #
#                                                                          #
# Languages: Java, C/C++, C#                                               #
#                                                                          #
############################################################################

package export_baseDependencies;
use strict;
use export_base;
use export_baseClassPackage;
use Understand;
use vars qw(@ISA @EXPORT @EXPORT_OK);

use constant UNNAMED_ENTITY => export_baseClassPackage::UNNAMED_ENTITY;

require Exporter;
@ISA = qw(Exporter);
@EXPORT = qw( buildClassAndPackageDependencies calculateStronglyConnectedComponents calculateCycles trimList ); 
@EXPORT_OK = qw(UNNAMED_ENTITY);             

############################################################################
# build the dependencies and fill packageMap and classMap                  #
# @param db database object                                                # 
# @param $depTypes a comma seperated list with dependency types (optional) # 
############################################################################
sub buildClassAndPackageDependencies {
	my $db = shift;
	my $depTypes = shift;
	# hold the package and class data: key=name, value=object
	my $packageMap = {};
	my $classMap = {};
    # store classes and find the corresponding packages 
    foreach my $ent ($db->ents("Type ~unknown")){
		next if $ent->library;
		my $className = getClassName($ent);
		my $packageEntity = getPackageForClass($ent);
		my $packageName = getPackageName($packageEntity);
		addClassToPackage($packageMap, $classMap, $packageName, $className, $packageEntity, $ent);
		# get dependent classes
		foreach my $depends ($ent->dependsby()) {			
			foreach my $depkey ($depends->keys()){
				next if $depkey->library;
				my $refClass = $depkey;
				my $refClassName =  getClassName($refClass);
				if ($className ne $refClassName) {
					my $refPackageEntity = getPackageForClass($refClass);
					my $refPackageName = getPackageName($refPackageEntity);
					addClassToPackage($packageMap, $classMap, $refPackageName, $refClassName, $refPackageEntity, $refClass);
					foreach my $depref ($depends->value($depkey)){
						# check for dependency type (or use all if undefined)
						my $depType = $depref->kind()->name();
						if (!$depTypes || testDependencyType($depType,$depTypes)) {
							addClassAndPackageDependency($packageMap, $classMap, $packageName, $className, $refPackageName, $refClassName, $depType);
						}	
					}
				}
			}
		}
    }
    # also add empty packages
    foreach my $ent ($db->ents('Package,Namespace')) {
    	next if $ent->library();
    	my $name = getPackageName($ent);
    	getOrCreatePackage($packageMap, $name, $ent) unless $packageMap->{$name};
    }
    return ($packageMap, $classMap);
}

############################################################################
# adds a class (name) to a package (name) and to the class map             #
# @param packageMap the hash map with package names and package objects    # 
# @param classMap the hash map with class names and class objects          # 
# @param packageName the name of the package to add a class for            # 
# @param className the name of the class to add to the package             # 
# @param entPackage the understand entity object of the package (optional) # 
# @param entClass the understand entity object of the class (optional)     # 
############################################################################
sub addClassToPackage {
	my $packageMap = shift;
	my $classMap = shift; 
	my $packageName = shift;
	my $className = shift;
	my $entPackage = shift;
	my $entClass = shift;
	# add class to package
	my $package = getOrCreatePackage($packageMap, $packageName, $entPackage);
	$package->addClass($className);
	# add class to class map
	my $class = $classMap->{$className};
	$classMap->{$className} = Dependency::Class->new($className, $entClass, $packageName) if !$class;
}

############################################################################
# adds a class (name) as dependency to another class (name)                #
# @param packageMap the hash map with package names and package objects    # 
# @param classMap the hash map with class names and class objects          # 
# @param packageName the name of the package to add a dependency for       # 
# @param className the name of the class to add a dependency for           # 
# @param depPackageName the name of the package that depends on            # 
# @param depClassName the name of the class that depends on                #
# @param depType The dependency type                                       #
############################################################################
sub addClassAndPackageDependency {
	my $packageMap = shift;
	my $classMap = shift; 
	my $packageName = shift;
	my $className = shift;
	my $depPackageName = shift;
	my $depClassName = shift;
	my $depType = shift;
	# class and package object already exists in the maps
	my $package = $packageMap->{$packageName};
	my $class = $classMap->{$className};
	my $depPackage = $packageMap->{$depPackageName};
	my $depClass = $classMap->{$depClassName};
	# add dependencies to classes and packages
	addEntityDependency($class, $depClass, $depType);
	if ($packageName ne $depPackageName) {
		addEntityDependency($package, $depPackage, $depType);
		$package->addDependedOnByClass($depClassName, $depType);
		$depPackage->addDependsOnClass($className, $depType);
	}
}

############################################################################
# adds the dependency between the first and the second entity              #
# @param classMap the hash map with class names and class objects          # 
# @param ent the dependency object to add a dependend object for           # 
# @param depEnt the dependend object to be added                           # 
# @param depType The dependency type                                       #
############################################################################
sub addEntityDependency {
	my $ent = shift;
	my $depEnt = shift;
	my $depType = shift;
	$ent->addDependedOnBy($depEnt->name(), $depType);
	$depEnt->addDependsOn($ent->name(), $depType);
}

############################################################################
# get the package object with the given name (or create a new package)     #
# @param packageMap the hash map with package names and package objects    # 
# @param packageName the name of the package to fetch from/insert into map # 
# @param entPackage the understand entity object of the package (optional) # 
############################################################################
sub getOrCreatePackage {
	my $packageMap = shift;
	my $packageName = shift;
	my $entPackage = shift;
	my $package = $packageMap->{$packageName};
	if (!$package) {
		$package = Dependency::Package->new($packageName, $entPackage);
		$packageMap->{$packageName} = $package;
	}
	return $package;
}

############################################################################
# tests whether the given reference type name is part of the allowed       #
# dependency types
# @param db database object                                                # 
# @param $depTypes a comma seperated list with dependency types (optional) # 
############################################################################
sub testDependencyType {
	my $refName = lc(shift);
	my $depTypes = lc(shift);
	foreach my $depType (split(',',$depTypes)) {
		$depType =~ s/^\s+|\s+$//g;
		if (index($refName,$depType)>=0) {
			return 1;
		}
	}
	return undef;
} 

############################################################################
# return a list wit SCCs (each scc is a list of entity names) using the    #
# algorithm from Cormen et al., Introduction to agorithms (chapter 22.5)   #
# taken from org.jgrapht.alg.StronglyConnectivityInspector                 #
# @param entityMap the hash map with entity names and entity objects       # 
############################################################################
sub calculateStronglyConnectedComponents {
	my $entityMap = shift;
	my @sccList;
	my $vertexToVertexData = {};
	foreach my $entityName (keys %$entityMap) {
		# store for each package: vertex, discovered, finished
	 	my @data = ($entityName, 0, 0);
		$vertexToVertexData->{$entityName} = \@data;
	}
	# perform first DFS, get vertices ordered by decreasing finishing time
	my $orderedVertices;
	for my $data (values %$vertexToVertexData) {
		if (@$data[1]==0) {
			$orderedVertices = dfsVisit($entityMap, $vertexToVertexData, $data, 1, $orderedVertices);
		}
	}
	# reset the vertex data, the first element is now a vertext data element (and not only a vertex)	
	foreach my $data (values %$vertexToVertexData) {
		@$data[1] = @$data[2] = 0;
	}
	# perform second DFS, going throughe the ordered vertices
	foreach my $data (@$orderedVertices) {
		if (@$data[1]==0) {
			my $sccMap = dfsVisit($entityMap, $vertexToVertexData, $data, 2, {});
			my @scc = keys %$sccMap;
			push(@sccList, \@scc);
		}
	}
	# return the list with SCCs
	return @sccList;	
}
sub dfsVisit {
	my $entMap = shift;      # the directed graph
	my $vertexToVertexData = shift; # the map with vertices and vertex data
	my $vertexData = shift;  # the data for vertex (vertex, discovered, finished)
	my $run = shift;         # 1st or 2nd run (different return value)
	my $vertices = shift;     # a list with ordered vertices in 1st run or a set with visited vertices in 2nd run
	my @stack;
	push(@stack, $vertexData);
	while (scalar(@stack)>0) {
		my $data = pop(@stack);
		if (@$data[1]==0) {
			@$data[1]=1;
			$vertices->{@$data[0]} = 1 if $run==2;
			my @newData = ($data, 1, 1);
			push(@stack, \@newData);
			my $entity = $entMap->{@$data[0]};
			my @dependsList = ($run==1)? $entity->getDependsOnList() : $entity->getDependedOnByList();
			foreach my $depName (@dependsList) {
				my $targetData = $vertexToVertexData->{$depName};
				if (@$targetData[1]==0) {
					push(@stack, $targetData);
				}
			}
		} elsif (@$data[2]!=0) {
			unshift(@$vertices, @$data[0]) if $run==1;
		}
	}
	return $vertices;
}

############################################################################
# returns a list holding lists with packages that are cycles               #
# adapted from Falleri et al., Efficient Retrieval and Ranking of Undesired#
# Package Cycles in Large Software Systems, p 7, http://goo.gl/M756e       #
# see also OzonePlugin from Stefan Schiffer                                #
# @param entityMap the hash map with entity names and entity objects       # 
# @param sccList the result of calculateStronglyConnectedComponents        # 
# @return a list with package names belonging to the cycle                 #
############################################################################
sub calculateCycles {
	my $entityMap = shift;
	my $sccList = shift;
	my $packageCycles = {};
	# iterate over all strongly connected components
	foreach my $scc (@$sccList) {
		next unless scalar(@$scc)>1;
		# iterate over all nodes of scc
		for my $vertex (@$scc) {
			my $entity = $entityMap->{$vertex};
			my $visitedMap = {};     # set of visited nodes
			my $pathMap = {};        # path back to vertex 
			$pathMap->{$vertex} = 1;
			my $ancestorsMap = {};   # set of ancestors of vertex
			my @dependsList = $entity->getDependedOnByList();
			foreach my $depIn (trimList(\@dependsList, $scc)) {
				$ancestorsMap->{$depIn} = 1;
			} 
			my @queue;               # queue initialized with vertex
			my $queueMap = {};       # help map for better performance for queue access
			push(@queue, $vertex);
			$queueMap->{$vertex} = 1;
			# breadth-first search from vertex that stops when every ancestor is found
			while (scalar(%$ancestorsMap)>0) {
				my $depend = shift(@queue);
				delete $queueMap->{$depend};
				my @dependsList2 = $entityMap->{$depend}->getDependsOnList();
				foreach my $depOut (trimList(\@dependsList2,$scc)) {
					# if depOut has not been visited or added to queue, we came to depOut from depend
					if (!$visitedMap->{$depOut} && !$queueMap->{$depOut}) {	
						$pathMap->{$depOut} = $depend;
						push(@queue, $depOut);
						$queueMap->{$depOut} = 1;
					}
					# if any ancestor of vertex is reached we have found a cycle
					if ($ancestorsMap->{$depOut}) {
						# build cycle
						my $nodeSet = {};
						my $b = $depOut;
						while ($b!=1) {
							my $a = $pathMap->{$b};
							$nodeSet->{($a==1)? $vertex : $b} = 1;
							$b = $a;
						}
						my @nodeList = keys %$nodeSet;
						my $key = join(",",sort(@nodeList));
						$packageCycles->{$key} = \@nodeList;
						delete %$ancestorsMap->{$depOut};
					}
				}
				$visitedMap->{$depend} = 1;
			}
		}
	}
	return values %$packageCycles;
}

############################################################################
# trim the list that it only contains allowed values                       #
# @param listToTrim the list with values to be trimmed                     # 
# @param listAllowedValues a list with all allowed values                  # 
############################################################################
sub trimList {
	my $listToTrim = shift;
	my $listAllowedValues = shift;
	my $allowedValueMap = {};
	foreach my $allowed (@$listAllowedValues) {
		$allowedValueMap->{$allowed} = 1;
	}
	my @trimmedList;
	foreach my $value (@$listToTrim) {
		push(@trimmedList, $value) if $allowedValueMap->{$value};
	}
	return @trimmedList;
}

############################################################################
# (abstract) entity object with name and dependencies maps                 #
############################################################################
package Dependency::Entity;
use strict;

sub new {
	my $class = shift;
	my $self = {
        _name => shift,
        _dependsOnList  => {},
        _dependedOnByList  => {}
	};
	my $ent = shift;
	$self->{_entityId} = ($ent)? $ent->id() : undef;
	bless $self, $class;
	return $self;
}

sub name {
	my $self = shift;
	return $self->{_name};
}

sub entityId {
	my $self = shift;
	return $self->{_entityId};
}

my $dependencies = sub {
	my $self = shift;
	my $dependencyList = shift;
	return keys %$dependencyList;
};
my $dependenciesCount = sub {
	my $self = shift;
	my $dependencyList = shift;
	my $sum = 0;
	foreach my $depTypes (values %$dependencyList) {
	  $sum += ($depTypes)? scalar(@$depTypes) : 0;
	}
	return$sum;
};
my $dependencyCount = sub {
	my $self = shift;
	my $dependencyList = shift;
	my $depClass = shift;
	#my $count =  $dependencyList->{$depClass};
	#return ($count)? $count : 0;
	my $depTypes = $dependencyList->{$depClass};
	return return ($depTypes)? scalar(@$depTypes) : 0;
};
my $addDependency = sub {
	my $self = shift;
	my $dependencyList = shift;
	my $depClass = shift;
	my $depType = shift;
	#my $count = $self->$dependencyCount($dependencyList, $depClass) + 1;
	#$dependencyList->{$depClass} = $count;
	my $depTypes = $dependencyList->{$depClass};
	push(@$depTypes, $depType);
	$dependencyList->{$depClass} = $depTypes;	
};
my $removeDependency = sub {
	my $self = shift;
	my $dependencyList = shift;
	my $depClass = shift;
	delete $dependencyList->{$depClass};
};

# add a depends-on reference (by entity name)
sub addDependsOn {
	my $self = shift;
	my $depEntity = shift;
	my $depType = shift;
	$self->$addDependency($self->{_dependsOnList}, $depEntity, $depType);
}
# remove a depends-on reference to the given entity name
sub removeDependsOn {
	my $self = shift;
	my $depEntity = shift;
	$self->$removeDependency($self->{_dependsOnList}, $depEntity);
}
# get all depends-on references (by entity name)
sub getDependsOnList {
	my $self = shift;
	return $self->$dependencies($self->{_dependsOnList});
}
# get number of depends-on references for all or a particular entity (by entity name)
sub getDependsOnCount {
	my $self = shift;
	my $depEntity = shift;
	return ($depEntity)? $self->$dependencyCount($self->{_dependsOnList}, $depEntity) : $self->$dependenciesCount($self->{_dependsOnList});
}
# get the depends-on types for an entity
sub getDependsOnTypes {
	my $self = shift;
	my $depEntity = shift;
	my $dependencyTypes = $self->{_dependsOnList}->{$depEntity};
	return @$dependencyTypes;
}
# add a depended-on-by reference (by entity name)
sub addDependedOnBy {
	my $self = shift;
	my $depEntity = shift;
	my $depType = shift;
	$self->$addDependency($self->{_dependedOnByList}, $depEntity, $depType);
}
# remove a depended-on-by reference to the given entity name
sub removeDependedOnBy {
	my $self = shift;
	my $depEntity = shift;
	$self->$removeDependency($self->{_dependedOnByList}, $depEntity);
}
# get all depended-on-by references (by entity name)
sub getDependedOnByList {
	my $self = shift;
	return $self->$dependencies($self->{_dependedOnByList});
}
# get number of depended-on-by references for all or a particular entity (by entity name)
sub getDependedOnByCount {
	my $self = shift;
	my $depEntity = shift;
	return ($depEntity)? $self->$dependencyCount($self->{_dependedOnByList}, $depEntity) : $self->$dependenciesCount($self->{_dependedOnByList});
}
# get the depended-on-by types for an entity
sub getDependedOnByTypes {
	my $self = shift;
	my $depEntity = shift;
	my $dependencyTypes = $self->{_dependedOnByList}->{$depEntity};
	return @$dependencyTypes;
}

############################################################################
# package object with package name, classes and package dependencies maps  #
############################################################################
package Dependency::Package;
use strict;
our @ISA = qw(Dependency::Entity);

sub new {
	my $class = shift;
	my $name = shift;
	my $entPackage = shift;
	my $self = $class->SUPER::new($name, $entPackage);
	$self->{_classMap} = {};
    $self->{_dependsOnClasses} = {};
    $self->{_dependedOnByClasses} = {};
	bless $self, $class;
	return $self;
}

sub addClass {
	my $self = shift;
	my $className = shift;
	my $classMap = $self->{_classMap};
	$classMap->{$className} = 1; 
}

sub containsClass {
	my $self = shift;
	my $className = shift;
	my $classMap = $self->{_classMap};
	return ($classMap->{$className} == 1);
}

sub getClasses {
	my $self = shift;
	my $classMap = $self->{_classMap};
	return keys %$classMap;
}

sub addDependsOnClass {
	my $self = shift;
	my $depClass = shift;
	my $depType = shift;
	$self->$addDependency($self->{_dependsOnClasses}, $depClass, $depType);
}

sub getDependsOnClasses {
	my $self = shift;
	return $self->$dependencies($self->{_dependsOnClasses});
}

sub addDependedOnByClass {
	my $self = shift;
	my $depClass = shift;
	my $depType = shift;
	$self->$addDependency($self->{_dependedOnByClasses}, $depClass, $depType);
}

sub getDependedOnByClasses {
	my $self = shift;
	return $self->$dependencies($self->{_dependedOnByClasses});
}

sub removeDependsOnClass {
	my $self = shift;
	my $depClass = shift;
	$self->$removeDependency($self->{_dependsOnClasses}, $depClass);
	
}
sub removeDependedOnByClass {
	my $self = shift;
	my $depClass = shift;
	$self->$removeDependency($self->{_dependedOnByClasses}, $depClass);
	
}

############################################################################
# class object with class name and dependent classes (names)               #
############################################################################
package Dependency::Class;
use strict;
our @ISA = qw(Dependency::Entity);

sub new {
	my $class = shift;
	my $name = shift;
	my $entPackage = shift;
	my $self = $class->SUPER::new($name, $entPackage);
	$self->{_packageName} = shift;
	bless $self, $class;
	return $self;
}

sub getPackageName {
	my $self = shift;
	return $self->{_packageName};
}


############################################################################
# evaluate my package to TRUE                                              #
############################################################################
1
__END__