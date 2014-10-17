############################################################################
#                                                                          #
# Author: harald.gruber@jku.at                                             #
#                                                                          #
# Synopsis: Write size data for various entities into a file               #
#                                                                          #
# Categories: Project Metrics, Entity Metrics                              #
#                                                                          #
# Languages: C#                                                            #
#                                                                          #
# Usage: entity_sizes_csharp -db database [-export path] [-cutpath path]   #
#                                                                          #
# Hints: - The program extracts information about files, classes,          # 
#          interfaces, enums, structs, methods, properties,...             #
#        - Metrics are not calculated correctly for test(?) classes having #
#          the same name in the same file                                  #
#                                                                          #
############################################################################

package entity_sizes_csharp;
use strict;
use Understand;
use entity_sizes_base;

############################################################################
# loop entities                                                            #
############################################################################
sub loopEntitiesCsharp {
	my ($db) = @_[0];
	setPathSeparator('\.');
	loopNamespacesCsharp($db);
	loopFilesCsharp($db);
	loopClassesCsharp($db);
	loopStructsCsharp($db);
	loopInterfacesCsharp($db);
	loopEnumsCsharp($db);
	loopMethodsCsharp($db);
	loopPropertiesCsharp($db);
	loopIndexersCsharp($db);
	#loopDelegateCsharp($db);
	#loopEventsCsharp($db);
}

############################################################################
# loop namespaces                                                          #
############################################################################
sub loopNamespacesCsharp {
	my ($db) = @_[0];
	foreach my $ent ( $db->ents("Namespace") ) {
		printEntity($ent, "Namespace", $ent->longname, "", undef);
	}
}

############################################################################
# loop files                                                               #
############################################################################
sub loopFilesCsharp {
	my ($db) = @_[0];
	foreach my $ent ( $db->ents("File") ) {
		my $name = getFileName($ent);
		my $path = getFileDirectory($ent);
		printEntity($ent, "File",$name, $path, undef);
	}
}

############################################################################
# loop classes                                                             #
############################################################################
sub loopClassesCsharp {
	my ($db) = @_[0];
	loopComplexTypesCsharp($db, "Class");
}

############################################################################
# loop structs                                                             #
############################################################################
sub loopStructsCsharp {
	my ($db) = @_[0];
	loopComplexTypesCsharp($db, "Struct");
}

############################################################################
# loop interfaces                                                          #
############################################################################
sub loopInterfacesCsharp {
	my ($db) = @_[0];
	loopComplexTypesCsharp($db, "Interface");
}

############################################################################
# loop enums                                                               #
############################################################################
sub loopEnumsCsharp {
	my ($db) = @_[0];
	loopComplexTypesCsharp($db, "Enum");
}

############################################################################
# loop delegates                                                           #
############################################################################
sub loopDelegateCsharp {
	my ($db) = @_[0];
	loopComplexTypesCsharp($db, "Delegate");
}

############################################################################
# loop complex C# types (class, interface, enum, ... )                     #
############################################################################
sub loopComplexTypesCsharp {
	my ($db) = @_[0];
	my ($type) = @_[1];
 	foreach my $ent ( $db->ents($type) ) {
		my $path = getClassPathCsharp($ent);
		my $name = getClassNameCsharp($ent);
		printEntity($ent, $type, $name, $path, $ent->refs("Definein"));
	}
}

############################################################################
# loop methods                                                             #
############################################################################
sub loopMethodsCsharp {
	my ($db) = @_[0];
	loopMethodsOfTypeCsharp($db, "Method");
}

############################################################################
# loop properties                                                          #
############################################################################
sub loopPropertiesCsharp {
	my ($db) = @_[0];
	loopMethodsOfTypeCsharp($db, "Property");
	return;
}

############################################################################
# loop indexer                                                             #
############################################################################
sub loopIndexersCsharp {
	my ($db) = @_[0];
	loopMethodsOfTypeCsharp($db, "Indexer");
}

############################################################################
# loop events                                                              #
############################################################################
sub loopEventsCsharp {
	my ($db) = @_[0];
	loopComplexTypesCsharp($db, "Event");
}

############################################################################
# loop methods                                                             #
############################################################################
sub loopMethodsOfTypeCsharp {
	my ($db) = @_[0];
	my ($type) = @_[1];
	foreach my $ent ( $db->ents($type) ) {
		my $path = getClassPathCsharp($ent);
		if ($path && $path ne "") {
			my $name = getMethodNameCsharp($ent);
			printEntity($ent, $type, $name, $path, $ent->refs("Definein"));
		}
	}
}

############################################################################
# get the name of the namespace of the given class                         #
############################################################################
sub getClassPathCsharp {
	my ($entity) = @_[0];
	# an inner class should be defined in an outer class, other classes within a namespace
	my $pathref = $entity->ref("Definein", "Class,Struct,Namespace,Interface");
	return ($pathref)? $pathref->ent()->longname : "";
}

############################################################################
# get the name of the class (for C#)                                       #
############################################################################
sub getClassNameCsharp {
	my ($class) = @_[0];
	my $name = getLastNamePart($class->longname);
	return $name;
}

############################################################################
# get the name of the method (for C#)                                      #
############################################################################
sub getMethodNameCsharp {
	my ($method) = @_[0];
	my $name = getMethodName($method);
	if (index($method->kindname, "Property")>=0) {
		$name =~ s/\(\)//;
	} elsif (index($method->kindname, "Indexer")>=0) {
		$name =~ s/\]\(//;
		$name =~ s/\)/]/;
	} 
	return $name;
}

############################################################################
# evaluate my package to TRUE                                              #
############################################################################
1