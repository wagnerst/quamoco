############################################################################
#                                                                          #
# Author: hgruber            											   #
#                                                                          #
# Synopsis: Base utility library for                                       #
#																		   #
# Categories: Library                                                      #
#                                                                          #
# Languages: Java, C/C++, C#                                               #
#                                                                          #
############################################################################

package export_baseUtils;
use strict;
use Understand;
use vars qw(@ISA @EXPORT);

require Exporter;
@ISA = qw(Exporter);
@EXPORT = qw( anyKeyOfMap1InMap2 anyElementOfList1InList2 mergeSetToListWithSets 
              getOrCreateHash 
              getTemplateType isDeclaredInHeaderFile); 

############################################################################
# checks of any key of the first map is also a key in the second map       #
# @param map1 the first map where a key shall be found in the second map   #
# @param map2 the second map that is searched for keys                     #
# @return true (1) if there is a key match, otherwise false (undef)        #
############################################################################
sub anyKeyOfMap1InMap2 {
	my $map1 = shift;
	my $map2 = shift;
	foreach my $key1 (keys %$map1) {
		return 1 if $map2->{$key1};
	}
	return undef;
}

############################################################################
# checks of any element of the first list is also part of the second list  #
# @param list1 the first list where an element shall be found in the second#
# @param list2 the second list that is searched for elements               #
# @return true (1) if there is  match, otherwise false (undef)             #
############################################################################
sub anyElementOfList1InList2 {
	my $list1 = shift;
	my $list2 = shift;
	my %map1 = map {$_ => 1} @$list1;
	my %map2 = map {$_ => 1} @$list2;
	return anyKeyOfMap1InMap2(\%map1, \%map2);
}

############################################################################
# gets a set with element and a list containing further sets, merges the   #
# new set in a way that related elements are in the same set now           #
# @param elemList reference to the list containing sets of elements        #
# @param newElemSet the new set with elements to merge                     #
# @param return a new list with sets containing related elements           #
############################################################################
sub mergeSetToListWithSets {
	my $elemList = shift;
	my $newElemSet = shift;
	my @newElemList;
	push(@newElemList,$newElemSet);
	foreach my $oldElemSet (@$elemList) {
		if (anyKeyOfMap1InMap2($newElemSet,$oldElemSet)) {
			foreach my $elemToAdd (keys %$oldElemSet) {
				$newElemSet->{$elemToAdd} = $oldElemSet->{$elemToAdd};
			}
		} else {
			push(@newElemList,$oldElemSet);
		}
	}
	return @newElemList;
}

############################################################################
# get or create a hash that is supposed to be a value of the given hash    #
# @param hash the hash to get the hash value from (or add a new one)       #
# @param key the key for the hash value                                    #
# @return the hash value associated with the given key or a new empty one  #
############################################################################
sub getOrCreateHash {
	my $hash = shift;
	my $key = shift;
	my $value = $hash->{$key};
	if (!$value) {
		$value = {};
		$hash->{$key} = $value;
	}
	return $value;
}

############################################################################
# get type of the template specialization of the given entity or undef     #
# @param entity an entity that should be a kind of template specialization #
# @return the type or undef (if no specialization or file not available)   #
############################################################################
sub getTemplateType {
	my $entity = shift;
	my $type = $entity->type();
	my $startIndex = index($type,'<'); 
	my $endIndex = rindex($type,'>'); 
	if ($startIndex<0 && $endIndex<0) {
		# if database is not analyzed "strict" (with version 3.0) I have to look into the code for the template type
		my $file = $entity->ref('Definein,Declarein');
		return undef unless $file;
		my @lines = split('\n',$file->file()->contents());
		$type = @lines[$file->line()-1];
		$startIndex = index($type,'<');
		$endIndex = rindex($type,'>');
	}	
	if ($startIndex>=0 && $endIndex>$startIndex) {
		return substr($type, $startIndex+1, $endIndex-$startIndex-1);
	} else {
		return undef;
	}
}

############################################################################
# tests whether the given entity is declared in a C/C++ header file or for #
# for other languages in a java or cs file                                 # 
# @param entity the entity to test                                         #
# @return the corresponding reference or undef                             #
############################################################################
sub isDeclaredInHeaderFile {
	my $entity = shift;
	my @fileExtensions = (".h", ".hpp", ".java", ".cs");
	foreach my $ref ($entity->refs('Declarein,Definein')) {
		my $filename = $ref->file()->name();
		# get the file extension
		my ($extension) = $filename =~ /(\.[^.]+)$/;
		return $ref if grep {$_ eq $extension} @fileExtensions;
	}
	return undef; 	
}

############################################################################
# evaluate my package to TRUE                                              #
############################################################################
1
__END__