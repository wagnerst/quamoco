package IReport::Libraries::duplicate_code;

##This script is designed to run with Understand as an Interactive Report
use base ("Understand::IReport");
use strict;

# Required - Return the short name of the Report, slashes (/) will be used to create directories in the menu
sub name { return "Duplicate Lines";}

# return -1 to indicate this report is never available, nor valid, for any entities.
# otherwise test $entity to determine if the report can be run on that entity and return 0 or 1
sub test_entity {
  my $entity = shift;
  # return $entity->kind->check("function"); # run the report on function entities
  # return $entity->name =~ /^p/i; # run the report on entities whose name starts with the letter 'p'
  return 0; # run the report on any entity by right clicking it and selecting interactive report
}

# return -1 to indicate this report is never available, nor valid, for any architectures.
# otherwise test $arch to determine if the report can be run on that architecture and return 0 or 1
sub test_architecture {
    my $arch = shift;
    return 0;	# any arch is valid, right click on the architecture and select interactive report
}

# Required - Return 1 if this report should be run on the entire project
# and appear in the top level Report menu, otherwise return 0
# Understand must be restarted to view new global reports
sub test_global { return 1;}


# Indicate this report supports displaying the progress bar.
sub support_progress { return 1; }

# Indicate this report supports the cancel button.
sub support_abort { return 1; }
our $abort_called;
our %lexers;

# This function is called when the cancel button is clicked
sub abort{
  $abort_called=1;
}

# Initialization code will be called once, per external report object.
sub init {
  my $report = shift;
  $abort_called=0; #reset the cancel flag in case it was set on another run
  %lexers = ();
  $report->option->integer("minline","Minimum # lines to match",3);
  $report->option->integer("minchar","Minimum # characters to match",20);
}


# Required - This is where the report is actually run
sub generate {
  my $report = shift;
  my @matches = createInitialMatches($report,0,65);
  @matches = removeDuplicates($report,65,2,@matches);
  @matches = fillMatchValues($report,67,30,@matches);
  @matches = removeDuplicates($report,97,3,@matches);

  my $dupLines = 0;
  foreach my $match (@matches){
    my $lines = $match->getNumLines;
    $dupLines +=$lines;
    $match->print($report);
  }
  $report->tree();
  close(FILE);
  $report->print("\n$dupLines lines of Duplicated code found\n") if $dupLines;
  $report->print("\nNo Duplicated code found\n") unless $dupLines;
  print("\n"); #Make sure to end with a new line, or the last line may not display  
   
}

#*************************************
#Read through each file with a lexer and store a hash for each chunk of text based off the minimums
#Return an array of match objects with locations, but incomplete text
sub createInitialMatches{
  my ($report,$initPercent,$totalPercentage) = @_;
  my %initialMatches;
  my $currentLine;
  my $minline = $report->option->lookup("minline");
  my $minchar = $report->option->lookup("minchar");
  foreach my $file($report->db->ents("file ~unknown ~unresolved")){
    my $lexer = $file->lexer(0);
    return unless $lexer;
    $lexers{$file->id}=$lexer;
    my $lex = $lexer->first();
    my @lines;
    my $linenum;
    while ($lex){
      $linenum = $lex->line_begin;
     
      if ($lex->token !~ /Comment|Newline|Whitespace/){
        $lines[$lex->line_begin].=$lex->text;
      }
      $lex=$lex->next;
    }
    #Update status bar
    if ($linenum % 100 == 0){
      my $percent = int((($currentLine + $linenum) / $report->db->metric("CountLine")) * $totalPercentage)+ $initPercent;
      updatePercentage($report,$percent,"Initial Match Identification") ;
    }
    $currentLine += $file->metric("CountLine");
    foreach (@lines){$_ =~ s/^\s*|\s*$//g;}
    
    for(my $i=0;$i<$#lines-($minline);$i++){
      my $line = $lines[$i];
      next unless $line;
      for (my $j=1;$j<$minline;$j++){
        $line.= $lines[$i+$j]
      }
      $line =~ s/^\s*|\s*$//g;
      if (length($line)>= $minchar){
        $initialMatches{$line} = new Match($line, $minline) unless $initialMatches{$line};
        $initialMatches{$line}->addLoc($file, $i)
      }
    }                                 
  }
  my @tempArray;
  foreach(values %initialMatches){
    push @tempArray, $_ if $_->getLocCount > 1;
  }
  return @tempArray;
}

sub removeDuplicates{
  my ($report,$initPercent,$totalPercentage, @matches) = @_;
  my %dupList;
  my @finalList;
  my $minline = $report->option->lookup("minline");
  my $minchar = $report->option->lookup("minchar");
  my $counter = 0;
  foreach my $match (sort{$b->getNumLines() <=> $a->getNumLines();} @matches){
    #Update status bar
    my $percent = int(($counter++/scalar(@matches)) * $totalPercentage)+$initPercent;
    updatePercentage($report,$percent,"Removing Duplicates") ;
    my $i = 0;
    LOC:while($i<$match->getLocCount){
      next LOC if $match->removeDuplicateLocations(\%dupList,$i);
      my ($file,$line) = $match->getLoc($i);
      foreach (my $j=0;$j<$match->getNumLines();$j++){
        $dupList{$file->id}{$line+$j}=1;
      }
      $i++
    }
    push @finalList, $match if ($match->getLocCount()>1);
  }
  return @finalList;
}
 
sub fillMatchValues{
  my ($report,$initPercent,$totalPercentage, @matches) = @_;
  my @filledMatches;
  my $counter;
  my $minline = $report->option->lookup("minline");
  my $minchar = $report->option->lookup("minchar");
  MATCH: foreach my $match (@matches){
    my $percent = int(($counter++/scalar(@matches)) * $totalPercentage)+$initPercent;
    updatePercentage($report,$percent,"Calculating Match Length") ;
    my $locCount = $match->getLocCount();
    next MATCH if $locCount < 2;
    
    my @lexArray; #used to keep track of the current location in each location
    my @endArray; #keep track of the last matching lexeme in each location
    
    #load match object with the first code lexeme for each location in the match
    for (my $i=0; $i < $locCount;$i++){
      my $startLex = lexFromLoc($match->getLoc($i));
      next MATCH unless $startLex;
      while ($startLex && $startLex->token =~ /Comment|Whitespace|Newline/){
        $startLex= $startLex->next;
      }
      $match->addLex($i, $startLex) if $startLex;
      $lexArray[$i] = $startLex;
    }

    my $textmatched = 1;
    STEP:while ($textmatched){
      for (my $i =0;$i < @lexArray; $i++){
        my $lex = $lexArray[$i];
        $endArray[$i]=$lex;

        #Goto the next code lexeme
        $lex=$lex->next;
        while ($lex && $lex->token =~ /Comment|Whitespace|Newline/){
          $lex = $lex->next;
        }
        $match->removeLocation($i) unless $lex;
        $lexArray[$i]=$lex;
      }
      map {$textmatched=0 if ! $_ || !$lexArray[0] || $_->text() ne $lexArray[0]->text();} @lexArray;
    }
    #At this point endArray is on the last matching lexeme in each file, @lexArray is undef
    
    #Get the actual text from the first file, sans comments
    my ($file, $line) = $match->getLoc(0);
    my $lex =  lexFromLoc($file, $line);
    my $text;
    while ($lex && ! ($lex->line_begin eq $endArray[0]->line_begin && $lex->column_begin eq $endArray[0]->column_begin)){
      $text .= $lex->text unless $lex->token eq "Comment";
      $lex=$lex->next;
    }
    my $lines =  ($endArray[0]->line_end - $line) + 1;
    $match->update($text,$lines);
      
    push @filledMatches, $match if $match && $match->getNumLines >= $minline ;
  }
  return @filledMatches;
}
  
sub lexFromLoc{
  my($file,$line) = @_;
  my $lexer = $lexers{$file->id};
  return unless $lexer;
  return $lexer->lexeme($line,0);
}

sub updatePercentage{
  my ($report,$percent,$message) = @_;
  $report->progress($percent,$message);
}


package Match;
sub new{
  my ($class,$text, $numLines) = @_;
  my $self = bless { 
    _fileList => [],
    _lineList => [],
    _lexList => [],
    locCount => 0,
    text => $text,
    numLines => $numLines,
    }, $class;
  return $self;
}

sub addLoc{
  my ($self, $fileEnt, $line) = @_;
  push @{$self->{_fileList}},$fileEnt;
  push @{$self->{_lineList}}, $line;
  $self->{locCount}++;
}

sub addLex{
  my ($self, $i, $lexeme) = @_;
  ${$self->{_lexList}}[$i]= $lexeme;
}

sub update{
  my ($self, $text, $numLines) = @_;
  $self->{text} = $text if $text;
  $self->{numLines} = $numLines if $numLines;
}

sub getLoc{
  my ($self, $i) = @_;
  return if  $i >= $self->{locCount};
  return (@{$self->{_fileList}}[$i],@{$self->{_lineList}}[$i]);
}

sub getLex{
  my ($self, $i) = @_;
  return if  $i >= $self->{locCount};
  my $lex =  @{$self->{_lexList}}[$i];
  return $lex;
}

sub getLocCount{
  my ($self) = @_;
  return $self->{locCount};
}

sub getText{
  my ($self) = @_;
  return $self->{text};
}

sub getNumLines{
  my ($self) = @_;
  return $self->{numLines};
}

sub removeLocation{
  my ($self,$victim) = @_;
  return unless $victim;
  splice @{$self->{_fileList}}, $victim,1;
  splice @{$self->{_lineList}}, $victim,1;
  $self->{locCount}--;
}

sub removeDuplicateLocations{
  my ($self,$hash, $i) = @_;
  my %dupList = %{$hash};
  my @victims;
  if (!$i){
    for (my $j=$self->{locCount}-1; $j >=0 ;$j--){
      push @victims,$j if $dupList{@{$self->{_fileList}}[$j]->id}{@{$self->{_lineList}}[$j]};
    }
  }else{
    push @victims,$i if $dupList{@{$self->{_fileList}}[$i]->id}{@{$self->{_lineList}}[$i]};
  }
  foreach my $j(@victims){
    splice @{$self->{_fileList}}, $j,1;
    splice @{$self->{_lineList}}, $j,1;
    $self->{locCount}--;
  }
  return scalar(@victims) if @victims;
}

sub print{
  my ($self, $report) = @_;
  $report->tree(1,1);
  $report->print($self->{numLines}." lines matched in ".$self->{locCount}." locations");
  for (my $i;$i<$self->{locCount};$i++){
    $report->tree(2,1);
    my $file = @{$self->{_fileList}}[$i];
    my $line = @{$self->{_lineList}}[$i];
    $report->syncfile($file,$line,0);
    $report->print($file->relname. "($line)");
    $report->syncfile();
  }
  $report->fontcolor("gray");
  $report->print("\nView Duplicate Lines");
  $report->fontcolor();
  $report->tree(3,0);
  my $text = $self->{text};
  $text =~ s/\t/  /g;
  $report->print($text);
}

1;