############################################################################
#                                                                          #
# Author: hgruber            											   #
#                                                                          #
# Synopsis: Proxy for be used instead of Understand's original IReport     #
#                                                                          #
############################################################################

package IReport::IReportProxy;

use strict;
use File::Basename;
use lib dirname($0).'/../';
use OptionProxy;
use vars qw(@EXPORT);

@EXPORT = qw(new option db progress);

my $option;
my $db;

# create a new instance of naming codecheck
sub new {
	my $class = shift;
	my $self = {
		_db => shift,
		_option => OptionProxy->new()
	};
	bless $self, $class;
}

# return the option instance
sub option {
	my $self = shift;
	return $self->{_option};
}

# return the database (is given as parameter on creation)
sub db {
	my $self = shift;
	return $self->{_db};
}

# do nothing
sub progress {	
}

1;
__END__
