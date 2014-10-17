############################################################################
#                                                                          #
# Author: harald.gruber@jku.at                                             #
#                                                                          #
# Synopsis: Write size data for various entities into a file               #
#                                                                          #
# Categories: Project Metrics, Entity Metrics                              #
#                                                                          #
# Languages: C/C++                                                         #
#                                                                          #
# Usage: entity_sizes_cpp -db database [-export path] [-cutpath path]      #
#                                                                          #
# Hints: - The program extracts information about files, classes,          #
#          methods/functions, union, and structs.                          #
#        - I don't know how to get the size of the class declaration, so   #
#          this value is calculated at the header file and there are only  #
#          few metrics available for it                                    #
#                                                                          #
############################################################################

package entity_sizes_cpp;
use strict;
use Understand;
use entity_sizes_base;
use export_baseClassPackage;
use EntityProxy;

############################################################################
# loop entities                                                            #
############################################################################
sub loopEntitiesCpp {
	my ($db) = @_[0];
	setPathSeparator('::');
	loopNamespacesCpp($db);
	loopFilesCpp($db);
	loopClassesCpp($db);
	loopMethodsCpp($db);
	loopStructsCpp($db);
	loopUnionsCpp($db);
}

############################################################################
# loop namespaces (does not work)                                          #
############################################################################
sub loopNamespacesCpp {
	my ($db) = @_[0];
	# assume that there is a default/unnamed namespace
	my $name = export_baseClassPackage::UNNAMED_ENTITY;
	printEntity(EntityProxy->new($db,$name), "Namespace", $name, "", undef);
	foreach my $ent ( $db->ents("Namespace") ) {
		my $name = getPackageName($ent);
		printEntity($ent, "Namespace", $ent->longname, "", undef);
	}
}

############################################################################
# loop files                                                             #
############################################################################
sub loopFilesCpp {
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
sub loopClassesCpp {
	my ($db) = @_[0];
	loopComplexTypesCpp($db, "Class");
}

############################################################################
# loop structs                                                             #
############################################################################
sub loopStructsCpp {
	my ($db) = @_[0];
	# as C++ allows structs with methods, treat them like a class
	loopComplexTypesCpp($db, "Struct");
}

############################################################################
# loop unions                                                              #
############################################################################
sub loopUnionsCpp {
	my ($db) = @_[0];
	loopSimpleTypesCpp($db, "Union");
}

############################################################################
# loop complex C/C++ types (classes and structs)                           #
############################################################################
sub loopComplexTypesCpp {
	my ($db) = @_[0];
	my ($type) = @_[1];
	foreach my $ent ( $db->ents($type) ) {
		my $path = getNamespaceNameCpp($ent);
		my $name = getClassNameCpp($ent);
		# a struct can be unnamed
		$name = "" if ($name eq "[unnamed]");
		
		my $headerfile = $ent->refs("Definein");
		my $startline = ($headerfile)? $headerfile->line : 0;
		my $endline = ($headerfile)? $ent->refs("End")->line : 0;
		# also add undefined headerfile (because auf special handling for Header in printEntity
		my @filerefs = $headerfile;

		foreach my $defineref ($ent->ents("Define", "Function,Object Static")) {
			my $fileref = $defineref->refs("Definein");
			# add defined functions/objects only if ithey not already part of the class definition 
			if (!$headerfile || $fileref->file != $headerfile->file || $fileref->line<$startline || $fileref->line>$endline) {
				push(@filerefs, $fileref);
			}
		}
		
		printEntity($ent, $type, $name, $path, @filerefs);
	}
}

############################################################################
# loop simple C/C++ types (unions)                                         #
############################################################################
sub loopSimpleTypesCpp {
	my ($db) = @_[0];
	my ($type) = @_[1];
	foreach my $ent ( $db->ents($type) ) {
		my $name = getLastNamePart($ent->longname);
		$name = "" if ($name eq "[unnamed]");
		my $fileref = $ent->refs("Definein,Declarein");
		if ($fileref) {
			my $path = $fileref->ent()->relname eq ""? $fileref->ent()->simplename : $fileref->ent()->relname;
			my $pathref = $fileref;
			while ($pathref && $pathref->ent()->kindname ne "File") {
				$pathref = $pathref->ent()->refs("Definein");
				if ($pathref) {
			 		$path = $pathref->ent()->relname eq ""? $pathref->ent()->simplename."::".$path : $pathref->ent()->relname."::".$path;
				}
			}
			printEntity($ent, $type, $name, $path, $fileref);
		}
	}
}

############################################################################
# loop methods                                                             #
############################################################################
sub loopMethodsCpp {
	my ($db) = @_[0];
	foreach my $ent ( $db->ents("Function") ) {
		my $name = getMethodNameCpp($ent);
		my @filerefs = $ent->refs("Definein");
		my $methodref = undef;
		if (scalar(@filerefs)==1) {
			# there is only one reference so take this
			$methodref = @filerefs[0];
		} elsif (scalar(@filerefs)>1) {
			# take the reference that is next smaller than end (if there is more than one define)
			my $endline = $ent->refs("End")->line;
			foreach my $temp (@filerefs) {
				if ($temp->line<=$endline && (!$methodref || $temp->line>$methodref->line)) {
					$methodref = $temp;
				}
			}			
		}
		if ($methodref) {
			# it's a C style function or C++ class member where it is possible to get the file reference
			my $methoddef = $methodref->ent();
			my $path = ($methoddef->kindname eq "File")? $methoddef->relname : $methoddef->longname;
			my $type = ($methoddef->kindname eq "File")? "Function": "Method";
			#printEntity($ent, $type, $name, $path, $methodref);
			# add also declarations as sub-elements, there is an Understand bug that returns wrong declarations
			# for some overloaded methods/functions that (e.g. using an enum/template mechanism for different types)
			my @defdeclref = $methodref;
			foreach my $declare ($ent->refs("Declarein")) {
				push(@defdeclref, $declare) if ($declare->line && $declare->line>0);
			}
			printEntity($ent, $type, $name, $path, @defdeclref);
		}
	}
}

############################################################################
# get the name of the class (for C++)                                      #
############################################################################
sub getClassNameCpp {
	my ($class) = @_[0];
	return getLastNamePart($class->longname);
}

############################################################################
# get the identifying path to a class (for C++)                            #
############################################################################
sub getNamespaceNameCpp {
	my ($class) = @_[0];
	return getAllExceptLastNamePart($class->longname);
}

############################################################################
# get the name of the method (for C++)                                     #
############################################################################
sub getMethodNameCpp {
	my ($method) = @_[0];
	my $name = getMethodName($method);
	
	# get const modifiers
    $name = $name." const" if index($method->kindname," Const ")>0;
    
	return $name;
}

############################################################################
# get the name except the last part of a ::-seperated name                 #
############################################################################
sub getAllExceptLastNamePart {
	my ($name) = @_[0];
	my $last = getLastNamePart($name);
	return (length($name)>length($last))? substr($name,0,length($name)-length($last)-2) : "";
}

############################################################################
# evaluate my package to TRUE                                              #
############################################################################
1