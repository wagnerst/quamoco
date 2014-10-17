############################################################################
#                                                                          #
# Author: hgruber            											   #
#                                                                          #
# Synopsis: Proxy for be used instead of Understand's original Codececk    #
#                                                                          #
############################################################################

package Codecheck::CodecheckProxy;

use strict;
use File::Basename;
use lib dirname($0).'/../';
use OptionProxy;
use vars qw(@EXPORT);

@EXPORT = qw(new option);

# create a new instance of naming codecheck
sub new {
	my $class = shift;
	my $self = {
		_option => OptionProxy->new()
	};
	bless $self, $class;
}

# return the option instance
sub option {
	my $self = shift;
	return $self->{_option};
}

1;
__END__
