############################################################################
#                                                                          #
# Author: harald.gruber@jku.at                                             #
#                                                                          #
# Synopsis: Write size data for various entities into a file.              #
#                                                                          #
# Categories: Project Metrics, Entity Metrics                              #
#                                                                          #
# Languages: C/C++, C#, Java                                               #
#                                                                          #
# Usage: entity_sizes -db database [-output path] [-cutpath path]          #
#                                  [-apidocu yes|no]                       #
#                                  [-cycles  yes|no]                       # 
#                                  [-dupcode yes|no]                       #
#                                  [-lcom    yes|no]                       #
#                                                                          #
# Hints: - Uses entity_sizes_cpp.pl, entity_sizes_csharp or                # 
#          entity_sizes_java depending on the language given by the        #
#          Understand database.                                            #
#                                                                          #
############################################################################

use strict;
use Understand;
use Getopt::Long;
use File::Basename;
use lib dirname($0).'/lib';
use lib dirname($0).'/sizes';
use entity_sizes_base;
use entity_sizes_cpp;
use entity_sizes_csharp;
use entity_sizes_java;


############################################################################
# MAIN                                                                     #
############################################################################

# get options
my ( $dbPath, $exportFile, $cutPath, $help );
my ( $apidocu, $cycles, $dupcode, $lcom );
GetOptions("db=s" => \$dbPath, "output=s" => \$exportFile, "cutpath=s" => \$cutPath, "help" => \$help,
           "apidocu=s" => \$apidocu, "cycles=s" => \$cycles, "dupcode=s" => \$dupcode, "lcom=s" => \$lcom);

# overwrite variables for debugging
#my $path = "D:\\Daten\\";
#my $dbPath = $path."Understand.udb";
#my $exportFile = $path."entitysizes.xml";
#my $cutPath = "S:\\cqm.benchmark";

# help message
die usage("") if ($help || !$dbPath);

# set extended options for calculating additional metrics at the beginning
setExtendedOptions($apidocu, $cycles, $dupcode, $lcom);
# open the database and - if given - the export file
my $db = openDatabase($dbPath);
openExportFile($exportFile);
# set the path to cut off for getting relative file names
setCutPath($cutPath) if ($cutPath);

printFileHeader();

# print the project metrics
# this entry is necessary as first entry as it is for the MOT size transformation
printEntity($db, "Project", $db->name." (".$db->language.")", "", undef);
# select the programming language of the project
if ($db->language eq "C") {
	entity_sizes_cpp::loopEntitiesCpp($db);
} elsif ($db->language eq "C#") {
	entity_sizes_csharp::loopEntitiesCsharp($db);
} elsif ($db->language eq "Java") {
	entity_sizes_java::loopEntitiesJava($db);
} else { 
	print "Unknown language!\n";
}

printFileFooter();

# close database and export file
closeDatabase($db);
closeExportFile();
