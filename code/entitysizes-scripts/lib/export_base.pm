############################################################################
#                                                                          #
# Author: harald.gruber@jku.at, alois.mayr1@jku.at                         #
#                                                                          #
# Synopsis: Base library for writing findings/metrics into a file.         #
#                                                                          #
# Categories: Project Metrics, Entity Metrics                              #
#                                                                          #
# Languages: C/C++, Java                                                   #
#                                                                          #
############################################################################

package export_base;
use strict;
use vars qw(@ISA @EXPORT);

use Understand;
use File::Basename;
use File::Spec;
use Config::Properties;
use Cwd;


# the default filename for export rule configurations
use constant DEFAULT_CONFIG_PROPERTIES_FILE => "config.properties";
# minimum threshold, if exceeded findings are reported
use constant DEFAULT_MIN_THRESHOLD => 0;
# maximum threshold, if value is below this limit findings are reported
use constant DEFAULT_MAX_THRESHOLD => 2147483647;
# the default filename for the threshold values for metrics
use constant DEFAULT_THRESHOLD_PROPERTIES_FILE => "thresholds.properties";

require Exporter;
@ISA = qw(Exporter);
@EXPORT = qw(openDatabase closeDatabase openExportFile closeExportFile 
			 printToFile printToFileXML printFileHeader printFileFooter 
			 printField checkThresholdAndPrintEntry printTextNode
			 loadConfigurationFile loadThresholdFile getConfiguration getDeclRef 
			);


my $exportFile;
my $configurations;
my $thresholds;

			
############################################################################
# open the database                                                        #
############################################################################
sub openDatabase($)
{
    my ($dbPath) = @_;
    
    my $db = Understand::Gui::db();

    # path not allowed if opened by understand
    if ($db&&$dbPath) {
	die "database already opened by GUI, don't use -db option\n";
    }

    # open database if not already open
    if ($dbPath&&!$db) {
		my $status;
		($db,$status)=Understand::open($dbPath);
		die "Error opening database: ",$status,"\n" if $status;
    }
    return($db);
}

############################################################################
# close the database                                                       #
############################################################################
sub closeDatabase($)
{
    my ($db)=@_;
    # close database only if we opened it
    $db->close() if ($db && !Understand::Gui::active());
}

############################################################################
# checks thresholds and print an entry to the export file                  #
############################################################################
sub checkThresholdAndPrintEntry {
	my ($metric) = @_[0];
	my ($file)   = @_[1];
	my ($value)  = @_[2];
	my ($line)   = @_[3];
	my ($detail) = @_[4];

	
	if (violatesThreshold($metric,$value)){
		printToFile("<item>");
		printTextNode("tool","Understand");
		printTextNode("metric",$metric);
		printTextNode("file",$file);
		printTextNode("value",$value);
		printTextNode("line",$line);
		printTextNode("detail",$detail);
		printToFile("</item>\n");
	}
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
# print a single textnode of the file                                         #
############################################################################
sub printTextNode {
	my ($textnode) = @_[0];
	my ($value)     = @_[1];
	
	printToFile("<".$textnode.">");
	printToFileXML($value);
	printToFile("</".$textnode.">");
}


############################################################################
# print the header of the export file                                      #
############################################################################
sub printFileHeader {
	printToFile("<?xml version=\"1.0\"?>\n");
	printToFile("<items>\n");
}

############################################################################
# print the footer of the export file                                      #
############################################################################
sub printFileFooter {
	printToFile("</items>\n");
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
# load a properties file                                                   #
############################################################################
sub loadPropertiesFile {
	my ($propertyFile) = @_[0];
	my ($defaultName) = @_[1];
	my ($fileType) = @_[2];
	my $properties = new Config::Properties();
	unless (-e $propertyFile) {
		print "$fileType file '$propertyFile' does not exist - try to access default file location '".getScriptDirectory().$defaultName."'\n" if length($propertyFile)>0;
		$propertyFile=getScriptDirectory().$defaultName;
	}
	if (-e $propertyFile){
		open(PROPERTYFILE, "<$propertyFile");
		$properties->load(*PROPERTYFILE);
		close(PROPERTYFILE);
	 }else{
		print "$fileType file '$propertyFile' does not exist; hence, using default values.\n";
	}
	return $properties;
}

############################################################################
# load rule configuration file, given by a parameter                       #
############################################################################
sub loadConfigurationFile {
	my ($configFile) = @_;
	$configurations = loadPropertiesFile($configFile, DEFAULT_CONFIG_PROPERTIES_FILE, 'Configuration');
	return $configurations;
}

############################################################################
# get the configuration object that was loaded by loadConfigurationFile    #
############################################################################
sub getConfiguration {
	return $configurations;
}

############################################################################
# load threshold file, given by a parameter                                #
############################################################################
sub loadThresholdFile {
	my ($thresholdFile) = @_;
	$thresholds = loadPropertiesFile($thresholdFile, DEFAULT_THRESHOLD_PROPERTIES_FILE, 'Threshold');
	return $thresholds;
}

############################################################################
# checks if metric-value violates thresholds                              #
############################################################################
sub violatesThreshold {
	my ($metric) = @_[0];
	my ($value)  = @_[1];
		
	return 1 unless defined($thresholds);
	
	my $minThreshold = $thresholds->getProperty($metric.".MIN",DEFAULT_MIN_THRESHOLD);
	my $maxThreshold = $thresholds->getProperty($metric.".MAX",DEFAULT_MAX_THRESHOLD);

	return 1 if ($value >=$minThreshold && $value <=$maxThreshold);
	return 0;
}

############################################################################
# returns the directory containing the script							   #
############################################################################
sub getScriptDirectory{
	$0=~/^(.+[\\\/])[^\\\/]+[\\\/]*$/;
	my $dir= $1 || "./";
	return $dir;
}

##########################################################################
# returns the declaration/definition of an entity, or 0 if unknown		   #
##########################################################################
sub getDeclRef {
    my $ent = shift;
    my $language = shift;
    my $decl;
    return $decl unless defined ($ent);
    
	if ($language =~ /ada/i) {
		my @declOrder =
			("declarein ~spec ~body ~instance ~formal ~incomplete ~private ~stub",
			 "spec declarein",
			 "body declarein",
			 "instance declarein",
			 "formal declarein",
			 "incomplete declarein",
			 "private declarein",
			 "stub declarein");
		foreach my $type (@declOrder) {
			last if (($decl) = $ent->refs($type,"",1));
		}
    }elsif($language =~ /c|fortran|java|c#/i) {
        ($decl) = $ent->refs("definein");
        ($decl) = $ent->refs("declarein") unless ($decl);
    } # end if($language =~ /ada/i)
    return $decl;
} # end sub getDeclRef ()

############################################################################
# evaluate my package to TRUE                                              #
############################################################################
1