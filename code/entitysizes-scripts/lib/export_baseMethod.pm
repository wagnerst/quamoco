############################################################################
#                                                                          #
# Author: hgruber            											   #
#                                                                          #
# Synopsis: Base library for method and function handling                  #
#																		   #
# Categories: Design Metrics                                               #
#                                                                          #
# Languages: Java, C/C++, C#                                               #
#                                                                          #
############################################################################

package export_baseMethod;
use strict;
use export_base;
use Understand;
use vars qw(@ISA @EXPORT);

require Exporter;
@ISA = qw(Exporter);
@EXPORT = qw( isOperator getMethodName isMethod isConstructor isDestructor isOverridingMethod isPartOfProperty findMethodCall getMethodReference ); 

############################################################################
# tests if the given method is a C++ operator                              #
# @param method the method or function entity                              #
# @return true if its an operator, otherwise undef                         #
############################################################################
sub isOperator {
	my $method = shift;
	my $name = $method->simplename();
	return $name =~ m/^operator(\W+| .+)$/;
}

############################################################################
# tests if the given entity is a method or function, returns also true if  #
# it's a C# property or indexer                                            #
# @param method the entity to test for method                              #
# @return true (1) if it's a mehotd/function otherwise false (undef)       #
############################################################################
sub isMethod {
	my $method = shift;
	my @methodTypes = ('Method', 'Function', 'Property', 'Indexer');
	return grep {index($method->kindname(),$_)>=0} @methodTypes;
	#return (index($method->kindname(),'Method')>=0 || index($method->kindname(),'Function')>=0);
}

############################################################################
# tests if the given method is a constructor (by having the same name as   #
# the class) assuming that the given method belongs to the class           #
# @param class the class entity                                            #
# @param method the method entity                                          #
# @return true if the method is the constructor for the class              #
############################################################################
sub isConstructor {
	my $class = shift;
	my $method = shift;
	return ($method->simplename() eq $class->simplename());
}

############################################################################
# tests if the given method is a constructor (by having the same name as   #
# the class with leading ~) assuming that the given method belongs to the  #
# class                                                                    #
# @param class the class entity                                            #
# @param method the method entity                                          #
# @return true if the method is the destructor for the class               #
############################################################################
sub isDestructor {
	my $class = shift;
	my $method = shift;
	return ($method->simplename() eq '~'.$class->simplename());
}

############################################################################
# tests if the given method overrides the method of a base class, as       #
# Understand does not recognize for C# methods that override a library     #
# method if need the lexer to check for override keyword                   #
# @param db the database object                                            #
# @param method the method entity                                          #
# @return 1 if this is an overriding method or undef                       #
############################################################################
sub isOverridingMethod {
	my $db = shift;
	my $method = shift;
	return 1 if $method->ref('Override,Overrides');
	if ($db->language eq 'C#') {
		my $methodRef = $method->ref('Definein');
		my $lexer = $methodRef->file()->lexer() if $methodRef;
		return 1 if $lexer && grep {$_->token() eq 'Keyword' && $_->text() eq 'override'} $lexer->lexemes($methodRef->line(),$methodRef->line());
	}
	return undef;
}

############################################################################
# tests if the given method is part of a C# property or indexer            #
# @param method the method entity                                          #
# @return the property if the method is a getter or setter method or undef #
############################################################################
sub isPartOfProperty {
	my $method = shift;
	my $ref = $method->ref('Definein');
	my $property = $ref->ent() if $ref;
	return ($property && (index($property->kindname(),'Property')>=0 || index($property->kindname(),'Indexer')>=0))? $property : undef;
}

############################################################################
# get the simple method name with the parameter list; if it's not a method # 
# or function only the simplename is returned                              #
# @param method the method entity                                          #
# @return the simple name including parameter list                         #
############################################################################
sub getMethodName {
	my $method = shift;
	my $name = $method->simplename();
	if (isMethod($method)) {
		my $paramList = $method->parameters(undef);
		$name = "$name($paramList)";
	}
	return $name;
}

############################################################################
# try to find a method call with the usage of a variable and a list of     #
# methods of the class type the variable is declared. this is just an      #
# estimation; if the return value is undef it seems that there is no method#
# call (but e.g. an assignment or variable is used as parameter)           #
# @param use the using reference of the variable                           #
# @param methods the method list of the variable's type                    #
# @param refType parameter to specify reference type (default Callby)      #
# @return a reference to the method call or undef                          #
############################################################################
sub findMethodCall {
	my $use = shift;
	my $methods = shift;
	my $refType = shift;
	$refType = 'Callby' unless $refType;
	my $methodCallRef;
	for my $method (@$methods) {
		for my $call ($method->refs($refType)) {
		#for my $call ($method->refs('Callby')) {	
			if ($call->file()->longname() eq $use->file()->longname() && $call->line()==$use->line()) {
				# check for the nearest column
				if ($call->column()>=$use->column() && (!$methodCallRef || $call->column()<$methodCallRef->column())) {
					$methodCallRef = $call;
				}
			}
		}
	}
	return $methodCallRef;
}


############################################################################
# get the default reference for a method, i.e., return the reference to the#
# method declaration and - if there are more than one declarations - return#
# the reference to the method definition (there can be only one) or undef  #
# @param method The method entity                                          #
# @return the reference for the method (if available) or undef             # 
############################################################################
sub getMethodReference {
	my $method = shift;
	my @filerefs = $method->refs('Declarein');
	if (scalar(@filerefs)==1) {
		return @filerefs[0];
	} else {
		return $method->ref('Definein');
	}
}

############################################################################
# evaluate my package to TRUE                                              #
############################################################################
1
__END__