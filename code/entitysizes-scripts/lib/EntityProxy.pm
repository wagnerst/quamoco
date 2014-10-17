############################################################################
#                                                                          #
# Author: hgruber            											   #
#                                                                          #
# Synopsis: Proxy for be used instead of Understand's original entity      #
#           Used to have an enitity object if there exists no one in the   #
#           database (e.g. default/unnamed namespace)                      #
#                                                                          #
############################################################################

package EntityProxy;

use strict;
use vars qw(@ISA @EXPORT);

@EXPORT = qw(define lookup);

my $hash;

# create a new instance of naming option
sub new {
	my $class = shift;
	my $self = {_db => shift, _name => shift};
	bless $self, $class;
	return $self;
}

sub db {
	my $self = shift;
	return $self->{_db};
}

sub name {
	my $self = shift;
	return $self->{_name};
}

sub longname {
	my $self = shift;
	return $self->name();
}

sub ents() {
	my $self = shift;
	my $query = shift;
	return $self->db()->ents($query);
}

sub contents() { }
sub depends() { }
sub dependsby() { }
sub ent() { }
sub filerefs() { }
sub id() { }
sub kind() { }
sub kindname() { }
sub library() { }
sub language() { }
sub metric() { }
sub metrics() { }
sub ref() { }
sub refs() { }
sub simplename() { }
sub type() { }
sub uniquename() { }
sub value() { }

1;
__END__
