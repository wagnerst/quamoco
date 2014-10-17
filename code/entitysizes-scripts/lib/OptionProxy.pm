############################################################################
#                                                                          #
# Author: hgruber            											   #
#                                                                          #
# Synopsis: Proxy for be used instead of Understand's original options     #
#           Used by CodecheckProxy and IReportProxy                        #
#                                                                          #
############################################################################

package OptionProxy;

use strict;
use vars qw(@ISA @EXPORT);

@EXPORT = qw(define lookup);

my $hash;

# create a new instance of naming option
sub new {
	my $class = shift;
	my $self = {};
	$hash = {};
	bless $self, $class;
}

# define a value for an option type
sub define {
	my ($key) = @_[1];
	my ($value) = @_[2];
	$hash->{ $key } = $value; 
}

# define an integer value for an option type (with a help text that is ignored)
sub integer {
	my ($key) = @_[1];
	my ($text) = @_[2];
	my ($value) = @_[3];
	$hash->{ $key } = $value; 
}

# look for the value of the given optino type
sub lookup {
	my ($key) = @_[1];
	my $value = $hash->{ $key };
	return $hash->{ $key };
}

1;
__END__
