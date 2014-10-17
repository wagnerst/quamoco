############################################################################
#                                                                          #
# Author: harald.gruber@jku.at, alois.mayr1@jku.at                         #
#                                                                          #
# Synopsis: Base library for writting size data into a file.               #
#                                                                          #
# Categories: Project Metrics, Entity Metrics                              #
#                                                                          #
# Languages: C/C++, Java                                                   #
#                                                                          #
############################################################################

package entity_sizes_base;
use strict;
use vars qw(@ISA @EXPORT);

use Getopt::Long;
use File::Basename;
use File::Spec;
use Understand;
use lib dirname($0).'/../lib';
use export_baseDependencies;
use export_baseMetrics;

require Exporter;
@ISA = qw(Exporter);
@EXPORT = qw(usage openDatabase closeDatabase openExportFile closeExportFile 
             setCutPath setExtendedOptions
			 printToFile printToFileXML printFileHeader printFileFooter 
			 printField printEntity printMetrics
			 getMethodName getEntityName getFileName getFileDirectory
			 getLastTextPart getLastNamePart setPathSeparator
			);


use constant METRIC_PUBLIC_API              => 'publicapi';
use constant METRIC_UNDOCUMENTED_API        => 'undocapi';
use constant METRIC_PACKAGE_TANGLES         => 'pkgtangles';
use constant METRIC_PACKAGE_EDGES           => 'pkgedges';
use constant METRIC_DUPLICATED_CODE_BLOCKS  => 'dupcode';
use constant METRIC_DUPLICATED_CODE_DENSITY => 'dupdens';

# about 100 tokens, see Sonar CoreProperties.CPD_MINIMUM_TOKENS_DEFAULT_VALUE
use constant METRIC_DUPLICATED_CODE_MIN_CHARS => 150; 
use constant METRIC_DUPLICATED_CODE_MIN_LINES => 10;

my $dbPath;
my $dbObject;
my $exportFile;
my $cutPath;
my $pathSeparator;
my $language;
my %projectMetrics;
my ( $apidocu, $cycles, $dupcode, $lcom );

############################################################################
# print entity data (for C/C++ and Java)                                   #
############################################################################
sub printEntity {
	my ($entity)  = @_[0];
	my ($type)    = @_[1];
	my ($name)    = @_[2];
	my ($path)    = @_[3];
	my (@fileref) = @_[4..$#_];
	
	# do not print library classes and functions
	my $parent = undef;
	if ($type ne "Project" && ($entity->library || (($parent=$entity->refs("Definein")) && $parent->ent()->library) || ($type eq "Package" && scalar($entity->refs("Definein"))==0))) {
		return;
	}
	# do not print entities without name (except structs and unions) or methods without path
	if (($name eq "" && !($type eq "Struct" || $type eq "Union")) || ($path eq "" && $type eq "Method")) {
		return;
	}
	# do not print standard libraries as files
	if ($type eq "File" && !$entity->metric("CountLine")) {
		return;
	}
	# do not print classes, methods, etc. without files
	if (!@fileref && ($type eq "Class" || $type eq "Interface" || $type eq "Enum" || $type eq "Method" || $type eq "Function" || $type eq "Struct" || $type eq "Union")) {
		return;
	}
	# do not print entities where it is not possible to retrieve TLOC 
	# (except package for Java, or namespace/struct/enum/property/indexer where it's unclear for C++(C# why they do not have these metrics)
	my $tloc = $entity->metric("CountLine");
	if ((!$tloc || $tloc eq "") && ($type ne "Package" && $type ne "Namespace" && $type ne "Struct" && $type ne "Enum" && $type ne "Delegate" && $type ne "Property" && $type ne "Indexer" && $type ne "Event")) {
		return;
	}
	
	printToFile("    <entity");
	printField("type", $type);
	if ($name) { 
		printField("name", $name);
	}
	if ($path) { 
		printField("path", $path);
	}
	if ($type eq "File") {
		printField("file", getPathName($entity->longname));
		printField("line", "1");
	} elsif (@fileref && (scalar(@fileref)==1 || scalar(@fileref)>=1 && ($type eq "Method" || $type eq "Function")) && @fileref[0]) {
		printField("file", getPathName(getEntityName(@fileref[0]->file())));
		printField("line", @fileref[0]->line());
	} 
	
	printMetrics($entity, $type);
	
	if ($type ne "File" && @fileref && scalar(@fileref)>1) {
		printToFile(">\n");
		if ($type eq "Method" || $type eq "Function") {
			# print the declarations, ignore the first entry as this is the implementation and already written at the entity
			shift(@fileref);
			foreach my $file (sort {getFileExtension($b->file()) cmp getFileExtension($a->file()) || getFileName($a->file()) cmp getFileName($b->file()) || $a->line() <=> $b->line();} @fileref) {
				printDeclarationEntry($entity, $file);
			}
		} else {
			if ($language eq "C") {
				# the first file of multiple files is the C/C++ header file where I don't know how to get the size of the class declaration;
				# so I fetch the end reference and calculate the tloc
				my $headerfile = shift(@fileref);
				printFileEntry($entity, $headerfile, "Header") if $headerfile;
			}
			# print all the other files (first header than implementation)
			foreach my $file (sort {getFileExtension($b->file()) cmp getFileExtension($a->file()) || getFileName($a->file()) cmp getFileName($b->file()) || $a->line() <=> $b->line();} @fileref) {
				printFileEntry($entity, $file, $type);
			}
		}
		printToFile("    </entity>\n");
	} else {
		printToFile("/>\n");
	}
}

############################################################################
# get the correct path name, i.e., a relative one if cutpath is set        #
############################################################################
sub getPathName($) {
	my ($name) = @_;
	$name = File::Spec->abs2rel( $name, $cutPath ) if ($cutPath);
	return $name;
}

############################################################################
# print the desired metrics for the entity                                 #
############################################################################
sub printMetrics {
	my ($entity)  = @_[0];
	my ($type)    = @_[1];

	# print the basic size metrics
	my $tloc = 0; my $lloc = 0; my $cloc = 0; my $loc = 0; my $stmts = 0;
	if ($type eq "Package") {
		# special handling for packages
		foreach my $fileref ($entity->refs("Definein")) {			
			my $file = $fileref->ent();
			$tloc = $tloc + $file->metric("CountLine");
			$lloc = $lloc + $file->metric("CountLineCode");
			$cloc = $cloc + $file->metric("CountLineComment");
			$loc = $loc + ($file->metric("CountLine") - $file->metric("CountLineBlank"));
			$stmts = $stmts + $file->metric("CountStmt");
		}
	} elsif ($type eq "Event") {
		$tloc = 1;
	} else {
		# the entity itself knows the size metrics
		$tloc = $entity->metric("CountLine");
		if (!$tloc || $tloc eq "") {
			# if tloc is not known, try to calculate it
			$tloc = 0;
			foreach my $refstart ($entity->refs("Definein")) {
				my $refend = findCorrespondingEndRef($entity, $refstart);
				$tloc = $tloc + ($refend->line() - $refstart->line() + 1) if $refend;
			}
		} 
		$lloc = $entity->metric("CountLineCode");
		$cloc = $entity->metric("CountLineComment");
		$loc = ($entity->metric("CountLine") - $entity->metric("CountLineBlank"));
		$stmts = $entity->metric("CountStmt");
	}
	if ($tloc==0 && $type eq "Namespace") {
		# it seems to be a C++ namespace that does not have metrics, estimate them
		$tloc = getNamespaceMetric($entity, "CountLine");
		$lloc = getNamespaceMetric($entity, "CountLineCode");
		$cloc = getNamespaceMetric($entity, "CountLineComment");
		$loc = getNamespaceMetric($entity, "CountLine") - getNamespaceMetric($entity, "CountLineBlank");
		$stmts = getNamespaceMetric($entity, "CountStmt");
	}
	
	printField("tloc", $tloc);
	printField("lloc", $lloc);
	printField("cloc", $cloc);
	printField("nbloc", $loc);
	printField("stmts", $stmts);
	
	# print special metrics for various entity types 
	if ($type eq "Class" || $type eq "Interface" || $type eq "Enum" || $type eq "Struct" || $type eq "Union") {
		printField("methods", ($entity->metric("CountDeclClassMethod") + $entity->metric("CountDeclInstanceMethod")));
		printField("fields", ($entity->metric("CountDeclClassVariable") + $entity->metric("CountDeclInstanceVariable")));
		my @refs = $entity->refs("Definein,Useby,Typedby,Castby,Importby,Createby,DotRefby,Extendby,Implementby");
		printField("used", scalar(@refs));
		@refs = $entity->refs("Useby,Typedby,Castby,Createby,DotRefby");
		printField("usedstmts", scalar(@refs));
		printField("complexity", $entity->metric("SumCyclomaticStrict"));
		if (isOptionYes($lcom) && $type eq "Class") {
			my $lcom4 = calculateLCOM4($entity);
			printField("lcom4", $lcom4);
		}
	} elsif ($type eq "Method" || $type eq "Function") {
		my @refs = $entity->refs("Definein,Callby");
		printField("used", scalar(@refs));
		@refs = $entity->refs("Callby");
		printField("usedstmts", scalar(@refs));
		printField("complexity", $entity->metric("CyclomaticStrict"));
	} elsif ($type eq "File") {
		printField("complexity", $entity->metric("SumCyclomaticStrict"));
	} elsif ($type eq "Project") {
		#printField("classes", $entity->metric("CountDeclClass")); does not count C++ structs,etc.
		printField("classes", scalar(grep{!$_->library()}$dbObject->ents("Type")));
		printField("methods", $dbObject->metric("CountDeclFunction"));
		#printField("methods", scalar(grep{!$_->library()}$dbObject->ents("Method,Function")));
		#printField("fields", $dbObject->metric("CountDeclClassVariable")+$dbObject->metric("CountDeclInstanceVariable"));
		printField("fields", scalar(grep{!$_->library() && scalar($_->ents('Definein,Declarein','Type'))>0 && index($_->type,'(*) ()')<=0}$dbObject->ents("Member Object ~Unresolved ~Unknown,Member Variable ~Unresolved ~Unknown ~EnumConstant")));
		my $numPackages = scalar(grep{!$_->library()}$dbObject->ents("Package,Namespace"));
		$numPackages++ if ($dbObject->language eq "C"); 
		printField("packages", $numPackages);
		#printField("files", scalar(grep{!$_->library()}$dbObject->ents("File")));
		printField("files", $dbObject->metric("CountDeclFile"));
		foreach my $metric (sort(keys %projectMetrics)) {
			printField($metric, %projectMetrics->{$metric});
		}
	}
}

############################################################################
# print the entry of a file when there are multiple ones available         #
# special handling if type is "Header"                                     #
############################################################################
sub printFileEntry {
	my ($entity) = @_[0];
	my ($file)   = @_[1];	
	my ($type)   = @_[2];
	if ($file) {
		# all file entries need the tloc metrics (except C++ class header files or C# partial classes)
		my $tloc = $file->scope()->metric("CountLine");
		if ((!$tloc || $tloc eq "") && !(($language eq "C" && $type eq "Header") || ($language eq "C#" && ($type eq "Class" || $type eq "Indexer")))) {
			return;
		}	
		printToFile("        <file");
		printField("name", getPathName(getEntityName($file->file())));
		printField("line", $file->line());
		if ($language eq "C" && $type eq "Header") {
			# special handling for the C/C++ header file
			printField("tloc", $entity->refs("End")->line() - $file->line() + 1);
		} elsif ($language eq "C#" && ($type eq "Class" || $type eq "Indexer")) {
			# it's a partial class, so find the correct file-end for the given file
			my $endref = findCorrespondingEndRef($entity, $file);
			printField("tloc", $endref->line() - $file->line() + 1) if $endref;
		} else {
			printMetrics($file->scope(), $type);
		}
		printToFile("/>\n");
	}	
}

############################################################################
# find the fitting end-ref of the entity with the given start-ref          #
############################################################################
sub findCorrespondingEndRef() {
	my ($entity) = @_[0];
	my ($startref) = @_[1];
	my $endref;
	for my $ref ($entity->refs("End")) {
		if ($startref->file()->longname eq $ref->file()->longname) {
			my $line = $ref->line();
			if ($line>=$startref->line() && (!$endref || $line<$endref->line())) {
				$endref = $ref;
			}
		}
	}
	return $endref;
}

############################################################################
# print the entries for extra function/method declarations (C/C++)         #
############################################################################
sub printDeclarationEntry {
	my ($entity) = @_[0];
	my ($file)   = @_[1];	
	if ($file) {
		printToFile("        <declaration");
		printField("file", getPathName(getEntityName($file->file())));
		printField("line", $file->line());
		printToFile("/>\n");
	}
}

############################################################################
# get the name of the method                                               #
############################################################################
sub getMethodName {
	my ($method) = @_[0];
	my $name = getLastNamePart($method->longname);
	
	# get return value
	my $returntype = $method->type;
	$name = $returntype? getLastNamePart($returntype)." ".$name : $name;

	# get parameters
	$name = $name."(".getMethodParameters($method).")";
	
	return $name;
}

############################################################################
# return a list of method parameters                                       #
############################################################################
sub getMethodParameters {
	my ($method) = @_[0];
	my $parameters = "";
   	my $first = 1;
    foreach my $param ($method->ents("Define","Parameter")) {
      $parameters = $parameters."," unless $first;
      $parameters = $parameters.getLastNamePart($param->type());
      $first = 0;
    }	
	return $parameters;	
}

############################################################################
# get the entity name (or empty string if undefined)                       #
############################################################################
sub getEntityName {
	my ($ent) = @_[0];
	return $ent? $ent->longname : "";
}

############################################################################
# get the relname ((if possible, otherwise the longname)                   #
############################################################################
sub getRelName {
	my ($ent) = @_[0];
	my $relname = $ent->relname;
	my $longname = $ent->longname;
	return $ent? (($relname && $relname ne "")? $relname : $longname) : "";
}

############################################################################
# get the file name (without path)                                         #
############################################################################
sub getFileName {
	my ($file) = @_[0];
	my $name = getRelName($file);
	my @parts = split(/(\/|\\)/, $name);
	return scalar(@parts)==0? $name : @parts[scalar(@parts)-1];
}

############################################################################
# get the directory of the given file                                      #
############################################################################
sub getFileDirectory {
	my ($file) = @_[0];
	my $name = getRelName($file);
	my $last = getFileName($file);
	return (length($name)>length($last))? substr($name,0,length($name)-length($last)-1) : "";
}

############################################################################
# get the file extension                                                   #
############################################################################
sub getFileExtension {
	my ($file) = @_[0];
	my $name = getEntityName($file);
	my @parts = split(/\./, $name);
	return scalar(@parts)==0? $name : @parts[scalar(@parts)-1];
}

############################################################################
# get the last part of a name separated by $pathSeparator                  #
############################################################################
sub getLastNamePart {
	my ($name) = @_[0];
	my @parts = split($pathSeparator, $name);
	return scalar(@parts)==0? $name : @parts[scalar(@parts)-1];
}

############################################################################
# setting the path seperator is necessary before calling getLastNamePart   #
############################################################################
sub setPathSeparator {
	$pathSeparator = shift;
}

############################################################################
# print a single field of the file                                         #
############################################################################
sub printField {
	my ($attribute) = @_[0];
	my ($value)     = @_[1];
	printToFileXML(" ".$attribute."=\"".$value."\"") if ($value && $value ne "");
}

############################################################################
# print the header of the export file                                      #
############################################################################
sub printFileHeader {
	printToFile("<?xml version=\"1.0\"?>\n");
	printToFile("<entities>\n");
}

############################################################################
# print the footer of the export file                                      #
############################################################################
sub printFileFooter {
	printToFile("</entities>\n");
}

############################################################################
# print a message to the file or to the console if no file defined         #
############################################################################
sub printToFile {
	my ($message) = @_[0];
	if ($exportFile) {
		print EXPORTFILE $message;
	} else {
		print $message;
	}
}

############################################################################
# print a message to file or console and parse invalid XML characters      #
############################################################################
sub printToFileXML {
	my ($message) = @_[0];
	$message =~ s/&/&amp;/g;
	$message =~ s/</&lt;/g;
	$message =~ s/>/&gt;/g;
	printToFile($message);
}

############################################################################
# explain the usage of the program                                         #
############################################################################
sub usage($) {
	my ($error) = @_[0];
	my $message = "Usage: entity_sizes -db database [-output path]\n".
				  " -db database    Specify Understand database (required for uperl,\n".
				  "                 inherited from Understand).\n".
				  " -output path    Specify the location to export the size data.\n".
				  " -cutpath path    The part of the absolute source path to cut (optional).\n".
				  " -apidocu yes|no Export data about public api documentation (default: no).\n".
				  " -cycles  yes|no Export data about package cycles (default: no).\n".
				  " -dupcode yes|no Export data about duplicated code (default: no).\n";
				  " -lcom    yes|no Export data about LCOM metric (default: no).\n";
	$message = $error.$message if ($error && $error ne "");				
	return $message;
}

############################################################################
# open the export file                                                     #
############################################################################
sub openExportFile($) {
	($exportFile) = @_;
	open(EXPORTFILE, ">$exportFile") if ($exportFile);
}

############################################################################
# close the export file                                                    #
############################################################################
sub closeExportFile {
	close(EXPORTFILE) if ($exportFile);
}

############################################################################
# open the database                                                        #
############################################################################
sub openDatabase($) {
	($dbPath) = @_;
	my $db = Understand::Gui::db();
	# path not allowed if opened by understand
	if ( $db && $dbPath ) {
		die "database already opened by GUI, don't use -db option\n";
	}
	# open database if not already open
	if ( !$db ) {
		my $status;
		die usage("Error, database not specified!\n") unless ($dbPath);
		( $db, $status ) = Understand::open($dbPath);
		die "Error opening database: ", $status, "\n" if $status;
	}
	# store the database object
	$dbObject = $db;
	# read out the programming language
	$language = $db->language;
	calculateProjectMetrics($db);
	return ($db);
}

############################################################################
# close the database                                                       #
############################################################################
sub closeDatabase($) {
	my ($db) = @_;
	# close database only if we opened it
	$db->close() if ($dbPath);
}

############################################################################
# set the path to cut off from file names for getting relative names       #
############################################################################
sub setCutPath($) {
	($cutPath) = @_;
}

############################################################################
# set extended options to calculate more size metrics                      #
# @param apidocu yes to calculate api documentation metrics                #
# @param cycles yes to calculate package cycles                            #
# @param dupcode yes to calculate duplicated code                          #
# @param lcom yes to calculate the lcom4 metric for each class             #
############################################################################
sub setExtendedOptions {
	$apidocu = shift;
	$cycles = shift;
	$dupcode = shift;
	$lcom = shift;
}

############################################################################
# calculates some additional project metrics, store in  %projectMetrics    #
# @param db the database                                                   #
############################################################################
sub calculateProjectMetrics() {
	my ($db) = @_;
	if (isOptionYes($apidocu)) {
		my ($publicApiCount, $undocumentedApi) = countPublicAndUndocumentedAPI($db);
		%projectMetrics->{METRIC_PUBLIC_API()} = $publicApiCount;
		%projectMetrics->{METRIC_UNDOCUMENTED_API()} = $undocumentedApi;
	}
	if (isOptionYes($cycles)) {
		my ($packageTangles, $packageEdges) = calculatePackageTangleIndex($db);
		%projectMetrics->{METRIC_PACKAGE_TANGLES()} = $packageTangles;
		%projectMetrics->{METRIC_PACKAGE_EDGES()} = $packageEdges;
	}
	if (isOptionYes($dupcode)) {
		my ($dupcBlocks, $dupcDensity) = calculateDoublicatedCode($db);
		%projectMetrics->{METRIC_DUPLICATED_CODE_BLOCKS()} = $dupcBlocks;
		%projectMetrics->{METRIC_DUPLICATED_CODE_DENSITY()} = $dupcDensity*100;
	}
}

############################################################################
# @param option the option to test (can be undef)                          #
# @return 1 if option is yes (or y) otherwise undef                        #
############################################################################
sub isOptionYes {
	my $option = shift;
	$option = lc($option) if $option;
	return ($option && ($option eq 'yes' || $option eq 'y'));
}

############################################################################
# counts the public API and additionally count the undocumented ones       #
# @return (1) public API count (2) undocumented public API count           #
############################################################################
sub countPublicAndUndocumentedAPI($) {
	my ($db) = @_;
	my $publicApiCount = 0;
	my $undocumentedApi = 0;
	foreach my $publicApi ($db->ents("Method Public,Function Public,Type Public,Variable Public ~Final ~Static, Object Public ~Static, Field Public ~Static")) {
		next if $publicApi->library();
		#next if scalar($publicApi->refs("Override,Overrides"))>0;
		$publicApiCount++;
		$undocumentedApi++ if length($publicApi->comments("before"))==0;
	} 

	return ($publicApiCount, $undocumentedApi);
}

############################################################################
# calculates the values that are necessary for the package tangle index    #
# @return (1) package tangles (2) packages edges (weighted)                #
############################################################################
sub calculatePackageTangleIndex {
	my ($db) = @_;
	my $packageEdges = 0;
	my ($packageMap, $classMap) = buildClassAndPackageDependencies($db);
	# calculate all weights (number of classes) between packages
	foreach my $packageName (keys %$packageMap) {
		my $weight = getPackageEdgesWeight($packageMap, $packageName);
		$packageEdges += $weight;
	}
	# the Sonar technical debt plug-in divides the package edges weight by 2
	$packageEdges = $packageEdges * 2;
	# cut package relations until there is no cycle (modifies $packageMap)
	my $packageTangles = calculatePackageEdgesToCut($packageMap, $classMap, 0);

	return ($packageTangles, $packageEdges);
}

############################################################################
# calculate package edges weight by taking the number of classes connecting#
# the first package with the second one (depends-on relation). without a   #
# second parameter, the weights to all connected classes are returned.     #
# @param packageMap the map holding all package information                #
# @param packageName the name of the package to calculate weights for      #
# @param classMap the map holding all class information (only necessary if #
#                 a target package name is specified                       #
# @param packageName2 an optional target package                           #
# @return the package edge weight to all connected packages or the the     #
#         second one if given                                              #
############################################################################
sub getPackageEdgesWeight {
	my $packageMap = shift;
	my $packageName = shift;
	my $classMap = shift;
	my $packageName2 = shift;
	my @classList = ($packageName2)? getClassesForPackageDependency($packageMap, $classMap, $packageName, $packageName2) : 
	                                 $packageMap->{$packageName}->getDependsOnClasses();
	return scalar(@classList);
}

############################################################################
# get a list of all classes of package2 that have a depends-on relation    #
# with package1                                                            #
# @param packageMap the map holding all package information                #
# @param classMap the map holding all class information                    #
# @param packageName1 the starting package                                 #
# @param packageName2 the target package                                   #
# @return a list of class names of package2 where package1 depends on      #
############################################################################
sub getClassesForPackageDependency {
	my $packageMap = shift;
	my $classMap = shift;
	my $packageName1 = shift;
	my $packageName2 = shift;
	my $package = $packageMap->{$packageName1};
	my %classListToPackage;
	if ($package->getDependsOnCount($packageName2)>0) {
		my @classList = $package->getDependsOnClasses();
		foreach my $className (@classList) {
			my $class = $classMap->{$className};
			%classListToPackage->{$className} = 1 if $packageName2 eq $class->getPackageName();
		}
	}
	return keys %classListToPackage;
}

############################################################################
# return the number of edges (weighted by classes between packages) that   #
# have to be cut to be free of cycles, will modify packageMap(!)           #
# @param packageMap the map holding all package information                #
# @param classMap the map holding all class information                    #
# @param packageCut the number of edges calculated until now               #
# @return the number of edged to be cut including the next iteration       #
############################################################################
sub calculatePackageEdgesToCut {
	my $packageMap = shift;
	my $classMap = shift;
	my $packageCut = shift;
	my @sccList = calculateStronglyConnectedComponents($packageMap);
	my @packageCycles = calculateCycles($packageMap, \@sccList);
	if (scalar(@packageCycles)>0) {
		# cut the dependency of the smallest cycle with the smallest weight
		my $smallestCycleEdges;
		my $smallestCycleWeight;
		my $smallestCyclePackage1;
		my $smallestCyclePackage2;
		foreach my $packageCycle (@packageCycles) {
			my $cycleSize = scalar(@$packageCycle);
			next if $smallestCycleEdges && $smallestCycleEdges<$cycleSize;
			foreach my $packageName1 (@$packageCycle) {
				foreach my $packageName2 (@$packageCycle) {
					next if $packageName1 eq $packageName2;
					my $weight = getPackageEdgesWeight($packageMap, $packageName1, $classMap, $packageName2);
					if ($weight>0 && (!$smallestCycleWeight || $weight<$smallestCycleWeight || $weight==$smallestCycleWeight && $cycleSize<$smallestCycleEdges)) {
						$smallestCycleWeight = $weight;
						$smallestCycleEdges = $cycleSize;
						$smallestCyclePackage1 = $packageName1;
						$smallestCyclePackage2 = $packageName2;
					}
				}
			}
		}
		# cut only dependencies between package (that should be sufficient for cycle calculation),
		# class dependencies will remain, but that should make no problems
		$packageMap->{$smallestCyclePackage1}->removeDependsOn($smallestCyclePackage2);
		$packageMap->{$smallestCyclePackage2}->removeDependedOnBy($smallestCyclePackage1);
		# cycle has been cutted, call the method again to test whether there still exist ones
		$packageCut = calculatePackageEdgesToCut($packageMap, $classMap, $packageCut+$smallestCycleWeight);
	}	
	return $packageCut;
}

############################################################################
# calculates the values for duplicated code                                #
# a newer version of Sonar could calculate this value by itself            #
# @return (1) duplicated code blocks cound (2) duplication density         #
############################################################################
sub calculateDoublicatedCode {
	my $db = shift;
    my $dupLines = 0;
    my $dupCount = 0;
    my @matches = calculateCodeDuplication($db, METRIC_DUPLICATED_CODE_MIN_LINES, METRIC_DUPLICATED_CODE_MIN_CHARS);
    foreach my $match (@matches){
	    $dupLines += $match->getNumLines();
	    $dupCount++;
    }
	return ($dupCount, $dupLines/$db->metric("CountLine"));
}

############################################################################
# evaluate my package to TRUE                                              #
############################################################################
1