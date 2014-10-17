#!/bin/bash

export PROJECT=filezilla
export PERL=uperl
export SCRIPT=/home/musedev/test/entity_sizes.pl
export INCLUDE_PATH1=/home/musedev/test
export INCLUDE_PATH2=/home/musedev/test/lib
#export INCLUDE_PATH3=/home/musedev/muse.tools/Understand/bin/linux64/Perl/STI/Maintain

export UND_NAMED_ROOT_PROJECT_ROOT_CPP="/media/sf_swe.svn/cqm.benchmark/Benchmark storage/CPP/Projects"

$PERL -I$INCLUDE_PATH1 -I$INCLUDE_PATH2 $SCRIPT -db "$PROJECT.understand.udb" -output "$PROJECT.understand-entitysizes.xml"

