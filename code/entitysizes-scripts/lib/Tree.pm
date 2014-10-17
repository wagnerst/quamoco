package Tree;

use 5.006;

use strict;
use warnings FATAL => 'all';

our $VERSION = '1.01';

use Scalar::Util qw( blessed refaddr weaken );

use base 'Tree::Fast';

# These are the class methods

my %error_handlers = (
    'quiet' => sub {
        my $node = shift;
        $node->last_error( join "\n", @_);
        return;
    },
    'warn' => sub {
        my $node = shift;
        $node->last_error( join "\n", @_);
        warn @_;
        return;
    },
    'die' => sub {
        my $node = shift;
        $node->last_error( join "\n", @_);
        die @_;
    },
);

sub QUIET { return $error_handlers{ 'quiet' } } 
sub WARN  { return $error_handlers{ 'warn' } } 
sub DIE   { return $error_handlers{ 'die' } } 

# The default error handler is quiet
my $ERROR_HANDLER = $error_handlers{ 'quiet' };

sub _init {
    my $self = shift;

    $self->SUPER::_init( @_ );

    $self->{_height} = 1,
    $self->{_width} = 1,
    $self->{_depth} = 0,

    $self->{_error_handler} = $ERROR_HANDLER,
    $self->{_last_error} = undef;

    $self->{_handlers} = {
        add_child    => [],
        remove_child => [],
        value        => [],
    };

    $self->{_root} = undef,
    $self->_set_root( $self );

    return $self;
}

# These are the behaviors

sub add_child {
    my $self = shift;
    my @nodes = @_;

    $self->last_error( undef );

    my $options = $self->_strip_options( \@nodes );

    unless ( @nodes ) {
        return $self->error( "add_child(): No children passed in" );
    }

    if ( defined $options->{at}) {
        my $num_children = () = $self->children;
        unless ( $options->{at} =~ /^-?\d+$/ ) {
            return $self->error(
                "add_child(): '$options->{at}' is not a legal index"
            );
        }

        if ( $options->{at} > $num_children ||
                $num_children + $options->{at} < 0
        ) {
            return $self->error( "add_child(): '$options->{at}' is out-of-bounds" );
        }
    }

    for my $node ( @nodes ) {
        unless ( blessed($node) && $node->isa( __PACKAGE__ ) ) {
            return $self->error( "add_child(): '$node' is not a " . __PACKAGE__ );
        }

        if ( $node->root eq $self->root ) {
            return $self->error( "add_child(): Cannot add a node in the tree back into the tree" );
        }

        if ( $node->parent ) {
            return $self->error( "add_child(): Cannot add a child to another parent" );
        }
    }

    $self->SUPER::add_child( $options, @nodes );

    for my $node ( @nodes ) {
        $node->_set_root( $self->root );
        $node->_fix_depth;
    }

    $self->_fix_height;
    $self->_fix_width;

    $self->event( 'add_child', $self, @_ );

    return $self;
}

sub remove_child {
    my $self = shift;
    my @nodes = @_;

    $self->last_error( undef );

    my $options = $self->_strip_options( \@nodes );

    unless ( @nodes ) {
        return $self->error( "remove_child(): Nothing to remove" );
    }

    my @indices;
    my $num_children = () = $self->children;
    foreach my $proto (@nodes) {
        if ( !defined( $proto ) ) {
            return $self->error( "remove_child(): 'undef' is out-of-bounds" );
        }

        if ( !blessed( $proto ) ) {
            unless ( $proto =~ /^-?\d+$/ ) {
                return $self->error( "remove_child(): '$proto' is not a legal index" );
            }

            if ( $proto >= $num_children || $num_children + $proto <= 0 ) {
                return $self->error( "remove_child(): '$proto' is out-of-bounds" );
            }

            push @indices, $proto;
        }
        else {
            my ($index) = $self->get_index_for( $proto );

            unless ( defined $index ) {
                return $self->error( "remove_child(): '$proto' not found" );
            }

            push @indices, $index;
        }
    }

    my @return = $self->SUPER::remove_child( $options, @indices );

    for my $node ( @return ) {
        $node->_set_root( $node );
        $node->_fix_depth;
    }

    $self->_fix_height;
    $self->_fix_width;

    $self->event( 'remove_child', $self, @_ );

    return @return;
}

sub add_event_handler {
    my $self = shift;
    my ($opts) = @_;

    while ( my ($type,$handler) = each %$opts ) {
        push @{$self->{_handlers}{$type}}, $handler;
    }

    return $self;
}

sub event {
    my $self = shift;
    my ( $type, @args ) = @_;

    foreach my $handler ( @{$self->{_handlers}{$type}} ) {
        $handler->( @args );
    }

    $self->parent->event( @_ );

    return $self;
}

# These are the state-queries

sub is_root {
    my $self = shift;
    return !$self->parent;
}

sub is_leaf {
    my $self = shift;
    return $self->height == 1;
}

sub has_child {
    my $self = shift;
    my @nodes = @_;

    my @children = $self->children;
    my %temp = map { refaddr($children[$_]) => $_ } 0 .. $#children;

    my $rv = 1;
    $rv &&= exists $temp{refaddr($_)}
        for @nodes;
    return $rv;
}

sub get_index_for {
    my $self = shift;
    my @nodes = @_;

    my @children = $self->children;
    my %temp = map { refaddr($children[$_]) => $_ } 0 .. $#children;

    return map { $temp{refaddr($_)} } @nodes;
}

# These are the smart accessors

sub root {
    my $self = shift;
    return $self->{_root};
}

sub _set_root {
    my $self = shift;

    $self->{_root} = shift;
    weaken( $self->{_root} );

    # Propagate the root-change down to all children
    # Because this is called from DESTROY, we need to verify
    # that the child still exists because destruction in Perl5
    # is neither ordered nor timely.

    $_->_set_root( $self->{_root} )
        for grep { $_ } @{$self->{_children}};

    return $self;
}

for my $name ( qw( height width depth ) ) {
    no strict 'refs';

    *{ __PACKAGE__ . "::${name}" } = sub {
        use strict;
        my $self = shift;
        return $self->{"_${name}"};
    };
}

sub size {
    my $self = shift;
    my $size = 1;
    $size += $_->size for $self->children;
    return $size;
}

sub set_value {
    my $self = shift;

    my $old_value = $self->value();
    $self->SUPER::set_value( @_ );

    $self->event( 'value', $self, $old_value, $self->value );

    return $self;
}

# These are the error-handling functions

sub error_handler {
    my $self = shift;

    if ( !blessed( $self ) ) {
        my $old = $ERROR_HANDLER;
        $ERROR_HANDLER = shift if @_;
        return $old;
    }

    my $root = $self->root;
    my $old = $root->{_error_handler};
    $root->{_error_handler} = shift if @_;
    return $old;
}

sub error {
    my $self = shift;
    my @args = @_;

    return $self->error_handler->( $self, @_ );
}

sub last_error {
    my $self = shift;
    $self->root->{_last_error} = shift if @_;
    return $self->root->{_last_error};
}

# These are private convenience methods

sub _fix_height {
    my $self = shift;

    my $height = 1;
    for my $child ($self->children) {
        my $temp_height = $child->height + 1;
        $height = $temp_height if $height < $temp_height;
    }

    $self->{_height} = $height;

    $self->parent->_fix_height;

    return $self;
}

sub _fix_width {
    my $self = shift;

    my $width = 0;
    $width += $_->width for $self->children;

    $self->{_width} = $width ? $width : 1;

    $self->parent->_fix_width;

    return $self;
}

sub _fix_depth {
    my $self = shift;

    if ( $self->is_root ) {
        $self->{_depth} = 0;
    }
    else {
        $self->{_depth} = $self->parent->depth + 1;
    }

    $_->_fix_depth for $self->children;

    return $self;
}

sub _strip_options {
    my $self = shift;
    my ($params) = @_;

    if ( @$params && !blessed($params->[0]) && ref($params->[0]) eq 'HASH' ) {
        return shift @$params;
    }
    else {
        return {};
    }
}

1;
__END__

=head1 NAME

Tree - an N-ary tree

=head1 SYNOPSIS

  my $tree = Tree->new( 'root' );
  my $child = Tree->new( 'child' );
  $tree->add_child( $child );

  $tree->add_child( { at => 0 }, Tree->new( 'first child' ) );
  $tree->add_child( { at => -1 }, Tree->new( 'last child' ) );

  $tree->set_value( 'toor' );
  my $value = $tree->value;

  my @children = $tree->children;
  my @some_children = $tree->children( 0, 2 );

  my $height = $tree->height;
  my $width  = $tree->width;
  my $depth  = $tree->depth;
  my $size   = $tree->size;

  if ( $tree->has_child( $child ) ) {
      $tree->remove_child( $child );
  }

  $tree->remove_child( 0 );

  my @nodes = $tree->traverse( $tree->POST_ORDER );
  my $clone = $tree->clone;
  my $mirror = $tree->clone->mirror;

  $tree->add_event_handler({
      add_child    => sub { ... },
      remove_child => sub { ... },
      value        => sub { ... },
  });

=head1 DESCRIPTION

This is meant to be a full-featured N-ary tree representation with
configurable error-handling and a simple events system that allows for
transparent persistence to a variety of datastores. It is derived from
L<Tree::Simple>, but has a simpler interface and much, much more.

=head1 METHODS

=head2 Constructor

=over 4

=item B<new([$value])>

This will return a Tree object. It will accept one parameter which, if passed,
will become the value (accessible by C<value()>). All other parameters will be
ignored.

If you call C<$tree-E<gt>new([$value])>, it will instead call C<clone()>, then set
the value of the clone to $value.

=item B<clone()>

This will return a clone of C<$tree>. The clone will be a root tree, but all
children will be cloned.

If you call L<Tree-E<gt>clone([$value])>, it will instead call C<new()>.

B<NOTE:> the value is merely a shallow copy. This means that all references
will be kept.

=back

=head2 Behaviors

=over 4

=item B<add_child([$options], @nodes)>

This will add all the C<@nodes> as children of C<$tree>. $options is a optional
unblessed hashref that specifies options for add_child(). The optional
parameters are:

=over 4

=item * at

This specifies the index to add C<@nodes> at. If specified, this will be passed
into splice(). The only exceptions are if this is 0, it will act as an
unshift(). If it is unset or undefined, it will act as a push().

=back

=item B<remove_child([$options], @nodes)>

This will remove all the C<@nodes> from the children of C<$tree>. You can either
pass in the actual child object you wish to remove, the index of the child you
wish to remove, or a combination of both.

$options is a optional unblessed hashref that specifies parameters for
remove_child(). Currently, no parameters are used.

=item B<mirror()>

This will modify the tree such that it is a mirror of what it was before. This
means that the order of all children is reversed.

B<NOTE>: This is a destructive action. It I<will> modify the tree's internal
structure. If you wish to get a mirror, yet keep the original tree intact, use
C<my $mirror = $tree-E<gt>clone-E<gt>mirror;>

=item B<traverse( [$order] )>

This will return a list of the nodes in the given traversal order. The default
traversal order is pre-order.

The various traversal orders do the following steps:

=over 4

=item * Pre-order (aka Prefix traversal)

This will return the node, then the first sub tree in pre-order traversal,
then the next sub tree, etc.

Use C<$tree-E<gt>PRE_ORDER> as the C<$order>.

=item * Post-order (aka Prefix traversal)

This will return the each sub-tree in post-order traversal, then the node.

Use C<$tree-E<gt>POST_ORDER> as the C<$order>.

=item * Level-order (aka Prefix traversal)

This will return the node, then the all children of the node, then all
grandchildren of the node, etc.

Use C<$tree-E<gt>LEVEL_ORDER> as the C<$order>.

=back

=back

All behaviors will reset last_error().

=head2 State Queries

=over 4

=item * B<is_root()>

This will return true is C<$tree> has no parent and false otherwise.

=item * B<is_leaf()>

This will return true is C<$tree> has no children and false otherwise.

=item * B<has_child(@nodes)>

This will return true is C<$tree> has each of the C<@nodes> as a child.
Otherwise, it will return false.

=item * B<get_index_for(@nodes)>

This will return the index into the children list for each of the C<@nodes>
passed in.

=back

=head2 Accessors

=over 4

=item * B<parent()>

This will return the parent of C<$tree>.

=item * B<children( [ $idx, [$idx, ..] ] )>

This will return the children of C<$tree>. If called in list context, it will
return all the children. If called in scalar context, it will return the
number of children.

You may optionally pass in a list of indices to retrieve. This will return the
children in the order you asked for them. This is very much like an
arrayslice.

=item * B<root()>

This will return the root node of the tree that C<$tree> is in. The root of
the root node is itself.

=item * B<height()>

This will return the height of C<$tree>. A leaf has a height of 1. A parent
has a height of its tallest child, plus 1.

=item * B<width()>

This will return the width of C<$tree>. A leaf has a width of 1. A parent has
a width equal to the sum of all the widths of its children.

=item * B<depth()>

This will return the depth of C<$tree>. A root has a depth of 0. A child has
the depth of its parent, plus 1.

This is the distance from the root. It's useful for things like
pretty-printing the tree.

=item * B<size()>

This will return the number of nodes within C<$tree>. A leaf has a size of 1.
A parent has a size equal to the 1 plus the sum of all the sizes of its
children.

=item * B<value()>

This will return the value stored in the node.

=item * B<set_value([$value])>

This will set the value stored in the node to $value, then return $self.

=item * B<meta()>

This will return a hashref that can be used to store whatever metadata the
client wishes to store. For example, L<Tree::Persist::DB> uses this to store
database row ids.

It is recommended that you store your metadata in a subhashref and not in the
top-level metadata hashref, keyed by your package name. L<Tree::Persist> does
this, using a unique key for each persistence layer associated with that tree.
This will help prevent clobbering of metadata.

=back

=head1 ERROR HANDLING

Describe what the default error handlers do and what a custom error handler is
expected to do.

=head2 Error-related methods

=over 4

=item * B<error_handler( [ $handler ] )>

This will return the current error handler for the tree. If a value is passed
in, then it will be used to set the error handler for the tree.

If called as a class method, this will instead work with the default error
handler.

=item * B<error( $error, [ arg1 [, arg2 ...] ] )>

Call this when you wish to report an error using the currently defined
error_handler for the tree. The only guaranteed parameter is an error string
describing the issue. There may be other arguments, and you may certainly
provide other arguments in your subclass to be passed to your custom handler.

=item * B<last_error()>

If an error occurred during the last behavior, this will return the error
string. It is reset only when a behavior is called.

=back

=head2 Default error handlers

=over 4

=item QUIET

Use this error handler if you want to have quiet error-handling. The
last_error method will retrieve the error from the last operation, if there
was one. If an error occurs, the operation will return undefined.

=item WARN

=item DIE

=back

=head1 EVENT HANDLING

Forest provides for basic event handling. You may choose to register one or
more callbacks to be called when the appropriate event occurs. The events
are:

=over 4

=item * add_child

This event will trigger as the last step in an L<add_child()> call.

The parameters will be C<( $self, @args )> where C<@args> is the arguments
passed into the add_child() call.

=item * remove_child

This event will trigger as the last step in an L<remove_child()> call.

The parameters will be C<( $self, @args )> where C<@args> is the arguments
passed into the remove_child() call.

=item * value

This event will trigger as the last step in a L<set_value()> call.

The parameters will be C<( $self, $old_value )> where
C<$old_value> is what the value was before it was changed. The new value can
be accessed through C<$self-E<gt>value()>.

=back

=head2 Event handling methods

=over 4

=item * B<add_event_handler( $type => $callback [, $type => $callback, ... ])>

You may choose to add event handlers for any known type. Callbacks must be
references to subroutines. They will be called in the order they are defined.

=item * B<event( $type, $actor, @args )>

This will trigger an event of type C<$type>. All event handlers registered on
C<$tree> will be called with parameters of C<($actor, @args)>. Then, the
parent will be notified of the event and its handlers will be called, on up to
the root.

This allows you specify an event handler on the root and be guaranteed that it
will fire every time the appropriate event occurs anywhere in the tree.

=back

=head1 NULL TREE

If you call C<$self-E<gt>parent> on a root node, it will return a Tree::Null
object. This is an implementation of the Null Object pattern optimized for
usage with L<Tree>. It will evaluate as false in every case (using
L<overload>) and all methods called on it will return a Tree::Null object.

=head2 Notes

=over 4

=item *

Tree::Null does B<not> inherit from Tree. This is so that all the
methods will go through AUTOLOAD vs. the actual method.

=item *

However, calling isa() on a Tree::Null object will report that it is-a
any object that is either Tree or in the Tree:: hierarchy.

=item *

The Tree::Null object is a singleton.

=item *

The Tree::Null object I<is> defined, though. I couldn't find a way to
make it evaluate as undefined. That may be a good thing.

=back

=head1 CIRCULAR REFERENCES

Please q.v. L<Forest> for more info on this topic.

=head1 WHAT'S NOT HERE

=over 4

=item * The Visitor pattern

I have deliberately chosen to not implement the Visitor pattern as described
by Gamma et al. Given a sufficiently powerful C<traverse()> and Perl's
capabilities, an explicit visitor object is almost always unneeded. If you
want one, it's easy to write one yourself. Here's a simple one I wrote in 5
minutes:

  package My::Visitor;

  sub new {
      my $class = shift;
      my $opts  = @_;

      return bless {
          tree => $opts->{tree},
          action => $opts->{action},
      }, $class;
  }

  sub visit {
      my $self = shift;
      my ($mode) = @_;

      foreach my $node ( $self->{tree}->traverse( $mode ) ) {
          $self->{action}->( $node );
      }
  }

=back

=head1 CODE COVERAGE

We use L<Devel::Cover> to test the code coverage of our tests. Below is the
L<Devel::Cover> report on this module's test suite.

  ---------------------------- ------ ------ ------ ------ ------ ------ ------
  File                           stmt   bran   cond    sub    pod   time  total
  ---------------------------- ------ ------ ------ ------ ------ ------ ------
  blib/lib/Tree.pm              100.0  100.0   94.4  100.0  100.0   67.3   99.7
  blib/lib/Tree/Binary.pm        96.4   95.0  100.0  100.0  100.0   10.7   96.7
  blib/lib/Tree/Fast.pm          99.4   95.5   91.7  100.0  100.0   22.0   98.6
  Total                          98.9   96.8   94.9  100.0  100.0  100.0   98.5
  ---------------------------- ------ ------ ------ ------ ------ ------ ------

=head1 ACKNOWLEDGEMENTS

=over 4

=item * Stevan Little for writing L<Tree::Simple>, upon which Tree is based.

=back

=head1 SUPPORT

The mailing list is at L<TreeCPAN@googlegroups.com>. I also read
L<http://www.perlmonks.com> on a daily basis.

=head1 AUTHORS

Rob Kinyon E<lt>rob.kinyon@iinteractive.comE<gt>

Stevan Little E<lt>stevan.little@iinteractive.comE<gt>

Thanks to Infinity Interactive for generously donating our time.

=head1 COPYRIGHT AND LICENSE

Copyright 2004, 2005 by Infinity Interactive, Inc.

L<http://www.iinteractive.com>

This library is free software; you can redistribute it and/or modify it under
the same terms as Perl itself. 

=cut
