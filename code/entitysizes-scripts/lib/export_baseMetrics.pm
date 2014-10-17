############################################################################
#                                                                          #
# Author: hgruber            											   #
#                                                                          #
# Synopsis: Base library for calcuculating some base metrics that can be   #
#           used for entity sizes and export rules                         #
#																		   #
# Categories: Metrics                                                      #
#                                                                          #
# Languages: Java, C/C++, C#                                               #
#                                                                          #
############################################################################

package export_baseMetrics;
use strict;
use export_baseClassPackage;
use export_baseMethod;
use export_baseUtils;
use Understand;
use IReport::IReportProxy;
use IReport::Libraries::duplicate_code;
use vars qw(@ISA @EXPORT);

require Exporter;
@ISA = qw(Exporter);
@EXPORT = qw( calculateCodeDuplication calculateLCOM4 getNamespaceMetric );

############################################################################
# uses the code duplication report for generating the list with machtes    #
# @param db the database obect                                             #
# @param minLines the minimum number of lines for duplication              #
# @param minChars the minimum number of characters for duplication         #
# @return a list with code matches                                         #
############################################################################
sub calculateCodeDuplication {
	my $db = shift;
	my $minLines = shift;
	my $minChars = shift;
	my $report = IReport::IReportProxy->new($db);
	IReport::Libraries::duplicate_code::init($report);
  	$report->option->define("minline", $minLines) if $minLines;
  	$report->option->define("minchar", $minChars) if $minChars;
	# code taken from duplicate_code's generate and print methods
	my @matches = IReport::Libraries::duplicate_code::createInitialMatches($report,0,65);
	@matches = IReport::Libraries::duplicate_code::removeDuplicates($report,65,2,@matches);
	@matches = IReport::Libraries::duplicate_code::fillMatchValues($report,67,30,@matches);
	@matches = IReport::Libraries::duplicate_code::removeDuplicates($report,97,3,@matches);
	return @matches;	
}

############################################################################
# calculates the LCOM4 for the given type, depending on the caller the     #
# metric value or a list with methods/attributes belonging together is     #
# returned (each list element is a map with method/attribute names as keys #
# @param ent the class entity to calculate LCOM4 for                       #
# @return either a value or a list with class elements belonging together  #
############################################################################
sub calculateLCOM4 {
	my $ent = shift;
	my @elemList;
	my $staticMethodSet = {};
	# search for all methods of a class (except constructor,destructor and operators) but store static ones extra
	foreach my $method ($ent->ents('Define','Function ~Pure,Method ~Stub,Property,Indexer')) {
		next if isOperator($method);
		next if $method->simplename() eq $ent->simplename() || $method->simplename() eq '~'.$ent->simplename();
		$staticMethodSet->{getMethodName($method)} = 1 if (index($method->kindname(),'Static')>=0);
		my $elemSet = {};
		if (addMethodToSet(getMethodName($method), $ent->longname(), $elemSet)) {
			# check method usage only if it was not done before (because of calling another method)
			addMethodUsage($ent, $method, $elemSet);
		}
		# look if the method is used by any of the elements in the list and rearrange the element list´
		@elemList = mergeSetToListWithSets(\@elemList, $elemSet);
	}
	# add the static method to an extra group
	@elemList = mergeSetToListWithSets(\@elemList, $staticMethodSet) if scalar(keys %$staticMethodSet)>0;
	return wantarray? @elemList : scalar(@elemList);	
}

############################################################################
# check what the given method uses and add this to the hash map, this      #
# method is called recursively for check usage of method of super class    #
# @param ent the class entity of the base class                            #
# @param method the method entity to check for usage                       #
# @param elemSet the hash map with the already used methods/attributes     #
# @return the number of library classes involved (and usage is unknown)    #
############################################################################
sub addMethodUsage {
	my $ent = shift;
	my $method = shift;
	my $elemSet = shift;
	my $libraryClasses = {};
	# look if the method uses any other method or attribute of the class (or super-class)
	foreach my $use ($method->refs('Call,Use,Set')) {
		my $useEntity = $use->ent();
		my $useName = getMethodName($useEntity);
		my $declRef = $useEntity->ref('Declarein,Definein,Typedby');
		if ($declRef) {
			my $classEntity = $declRef->ent();
			my $className = $classEntity->longname;
			if ($ent->longname() eq $className || isSuperClass($ent,$classEntity)) {
				if (addMethodToSet($useName, $className, $elemSet) && isMethod($useEntity)) {
					if ($classEntity->library() || index($classEntity->kind()->longname(),'Unresolved')>=0) {
						$libraryClasses->{$className} = 1;
					} else {
						# call recursively on a base class call, etc. (prevent endless loop by storing already analysed class and elements)
						addMethodUsage($classEntity, $useEntity, $elemSet);
					}
				}
			}
		}
	}
	return $libraryClasses;
}

############################################################################
# add the method to the hash map where the value is a set of classes where #
# methods/attributes are declared in                                       #
# @param methodName the name of the method/attribute to be stored          #
# @param className the name of the class where the element is declared in  #
# @param elemSet the hash map where element is key and map with classes is #
#        value                                                             #
# @return true (1) if method was added and false (undef) if not, because   #
#         the class already exists in the corresponding value map          #
############################################################################
sub addMethodToSet {
	my $methodName = shift;
	my $className = shift;
	my $elemSet = shift;
	my $classSet = $elemSet->{$methodName};
	if (!$classSet) {
		$classSet = {};
		$elemSet->{$methodName} = $classSet;
	} 
	if (!$classSet->{$className}) {
		$classSet->{$className} = 1;
		return 1;
	}
	return undef;
}

############################################################################
# try to get a metric for a C++ namespace by retrieving this metric from   #
# all entities that are declared in the given namespace (namespaces do not #
# have metrics), is only an approximate value because not all sub-entities #
# have metrics stored (e.g. enums)                                         #
# @param namespace the namespace entity                                    #
# @param metric the name of the metric to get                              #
# @return the metric value for the namespace                               #
############################################################################
sub getNamespaceMetric {
	my $namespace = shift;
	my $metric = shift;
	my $totalValue = 0;
	if ($namespace->longname() eq export_baseClassPackage::UNNAMED_ENTITY) {
		foreach my $ent ($namespace->ents('Type ~Unknown ~Unresolved,Function ~Member ~Unknown ~Unresolved')) {
			next if $ent->library();
			unless (getPackageForClass($ent)) {
				my $value = $ent->metric($metric);
				$totalValue += $value if $value;
				#print "Add ".$ent->kindname()." ".$ent->longname()."\n";
			}
		}
	} else {
		foreach my $ent ($namespace->ents('Define')) {
			my $value = $ent->metric($metric);
			$value = getNamespaceMetric($ent, $metric) unless $value;
			$totalValue += $value if $value;
		}
	}
	return $totalValue;
}

############################################################################
# evaluate my package to TRUE                                              #
############################################################################
1
__END__