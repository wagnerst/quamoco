package Codecheck::Libraries::Flowchart;
use constant MAXTOKENCOUNT => 30000000; #After this return an error that it is too big or stuck in a loop

#  This library generates a logical control flow graph for an Understand::Entity and provides access to the nodes and edges and their coorespoding information
#  It does not draw the graph or anything else,
#
#  isValidFlowchartEntity()   Tests an entity to see if it is valid for this script and returns true of false accordingly
#  createFlowchart()          Generates the flowchart and returns an array of $node objects
#  $node->propertyList()      Returns a list of names of the valid properties for this node
#  $node->get($nodeproperty)  Returns the value of $property for this node
#  $node->edgeList()          Returns a list of $edge objects originating from this node
#  $edge->propertyList()      Returns a list of names of the valid properties for this edge
#  $edge->get($edgeproperty)  Returns the value of $property for this edge
#
#  Valid $nodeproperty values may include "id","shape","color","label","comment","type","num_edges_in","num_edges_out","file_ent".
#       Any value may return null, except "id" which is guaranteed to exist and be unique
#  Valid $edgeproperty values may include "source","target","type","color". "source" and "target" are guaranteed to exist
#
#  Example:
#  The below script will show all avaiable information for the nodes and edges of $ent:
#      if(isValidFlowchartEntity($ent)){
#        my @nodes = createFlowchart($ent);
#
#        foreach my $node (@nodes){
#          print "************\n";
#          foreach my $property ($node->propertyList()){
#              print "$property: ".$node->get($property)."\n";
#          }
#          my @edges = $node->edgeList();
#          my $edgeNum=1;
#          foreach my $edge (@edges){
#            print "Edge #".$edgeNum++."\n";
#            foreach my $property ($edge->propertyList()){
#              print "   Edge $property: ".$edge->get($property)."\n";
#            }
#          }
#        }
#      }
#

use strict;

our @ISA  = qw(Exporter);
our @EXPORT = qw(createFlowchart isValidFlowchartEntity);

#
# Globals
#

########
# immutable
my $ada;
my $fortran;
my $java;
my $vhdl;
my $python;
my $lexer;
my $definein;
my $file_ent;
my $collapse;
my $debug=0; #Debug level - 0 (none)-3 (verbose)
my $debugFile = 'x:\temp\flowchart.log';
my $mainEnt;
########

########
# mutable
my $stream;
my $node_number;

my @switch_var;        # partial variable string for the current switch
my $keyword_loop_label;# the label for the current loop
my $stmtCount;
my %stmtCompleted;

my $unreachable;       # is the current statement unreachable?
my $loop_head;         # need to capture next node for loop return?
my $exception;         # inside an ada or vhdl exception handler
my $try;               # could the current statement throw an exception?

my $brace_count;       # count of opening braces for inline brace pairs
my $end_ref;           # reference to the end of the function
my $asm_block;         # are we inside an asm block?
my $ada_select_block;  # Are we in an ada select block?

my @returns;           # returns from the function under consideration
my @try_list;          # a list of nodes that could throw an exception
my @break_list;        # a stack of lists for nested loops
my @continue_list;     # a stack of lists for nested loops
my @loop_head_list;    # a stack of nodes for loop beginnings
my @fortran_labels;    # a stack of fortran control labels that have not been reached yet
my @found_keyword_fortran_labels;     # a stack of all fortran labels that have been passed
my @append_functions;  # a list of Fortran/vhdl/ada functions that have optional spaces in the name
my @cStyleFallThrough; # The previous Case did not break
my @switchdefault;     # Keep track if a switch has a default
my @decisionTree;      # Keep track of what decision cluster we are in
my $nodecount;         # Keep track of the number of nodes in the graph.
my $edgecount;         # Keep track of the number of edges in the graph.
my $token_count;       # Keep track of number of tokens read to prevent infinite loop

my %labels;            # list of labels up to this point
my %labelAlias;        # list of label aliases up to this point
my %deferred_gotos;    # gotos that point to an unknown label
my %loop_labels;       # list of labels for named loops
my %named_break_list;  # list of exit nodes for named unconditional loops (ada)


my @flowchartNodes;    # hash that makes up the nodes of the graph


sub isValidFlowchartEntity{
  my $entity = shift;
  my $result = 0;
  return $result unless $entity;
  $mainEnt = $entity;
  if ($entity->kind->check(
   "c function ~unknown ~unresolved".
   ",java method ~abstract".
   ",fortran subroutine, fortran function, fortran main program ~unknown ~unresolved".
   ",c# method ~abstract".
   ",vhdl procedure, vhdl function".
   ",python function, python file".
   ",ada procedure, ada function, ada task")) {
      $definein = $entity->ref("definein, body declarein");
      if ($definein || $entity->kind->check("file")) {
        $file_ent = $definein->file if $definein;
        $file_ent = $entity unless $definein;
        if ($file_ent->lexer_test) {
          # The file is probably unreadable or modified.
          # Return zero so that the user knows to refresh.
          $result = 0;
        } else {
          $result = 1;
        }
      }
    }
  return $result;
}

sub createFlowchart{
  my $entity = shift;
  my $expandmacros = shift;
  $collapse = shift;

  # set the globals $definein and $file_ent, as a side effect of isValidFlowchartEntity()
  $definein = 0;
  $file_ent = 0;
  $mainEnt = 0;
  return unless isValidFlowchartEntity($entity);

  $ada = $entity->kind->check("ada");
  $fortran = $entity->kind->check("fortran");
  $python = $entity->kind->check("python");
  $vhdl = $entity->kind->check("vhdl");
  $java = $entity->kind->check("java");
  $lexer = $file_ent->lexer(1,1,1,$expandmacros);

  return unless $lexer;

  $stmtCount =0;
  %stmtCompleted=();
  $nodecount =0;
  $edgecount =0;
  $stream    = 0;
  $node_number = 0;
  @switch_var = ();
  $keyword_loop_label = 0;
  $unreachable = 0;
  $loop_head = 0;
  $exception = 0;
  $try = 0;
  $brace_count = 0;
  $end_ref = $entity->ref("end");
  if ($debug && $debugFile){
    open(FILE,">$debugFile");
    print FILE "**************START********\n";\
    print FILE "Control flow graph for ".$entity->longname."\n";
    print FILE "Expand Macros: $expandmacros\n";
    print FILE "Collapse: $collapse\n";  
    close(FILE);
    }
  #Handle ada tasks that have multiple ends, get the one closest to the definition
  if ($entity->kind->check("ada task")){
    printDebug("ada task",2);
    foreach my $ref (sort{$a->line() <=> $b->line();} $entity->refs("end")){
      next if $ref->file->id ne $definein->file->id;
      if ($ref->line >= $definein->line){
        $end_ref = $ref;
        last;
      }
     }
   }
   
   #Fortran can have nested structures inside, go to the last end reference
   if ($fortran){
    my @endRefs = $entity->refs("end,endby");
    @endRefs = sort{$b->line <=> $a->line;} @endRefs;
    $end_ref= $endRefs[0] if @endRefs;
    printDebug("EndLine = ".$end_ref->line,2);
   }

  $asm_block = 0;

  @returns = ();
  @try_list = ();
  @break_list = ();
  @continue_list = ();
  @loop_head_list = ();
  @fortran_labels = ();
  @found_keyword_fortran_labels = ();
  @append_functions = ("enddo","endif","blockdata","selectcase","elseif","goto","selectcase","endselect","endstructure") unless $ada;
  @append_functions =("endif") if $vhdl;
  @append_functions = ("endloop", "endselect", "orelse") if $ada;
  push (@append_functions,("endinterface","endwhere","endfunction")) if $fortran;
  @cStyleFallThrough = ();
  @switchdefault = ();
  @decisionTree = ();
  %labels = ();
  %labelAlias = ();
  %deferred_gotos = ();
  %loop_labels=();       
  %named_break_list=();
  $token_count=0;
  @flowchartNodes = ();

  parse_function($entity);
  return @flowchartNodes;
}



#****************************************************************************

#
# Parsing
#

sub parse_function {
  my $entity = shift;

  # get the first lexeme
  if ($definein){
      $stream = $lexer->lexeme($definein->line, 1);
      printDebug("Could not create lexical stream ".$definein->file->relname."(".$definein->line.")",1) if !$stream;
      die "Could not read file: ".$definein->file->relname."(".$definein->line."). Error " if !$stream;
      printDebug("Start Line: ".$definein->line,2);
  }else{ #File Entity
     $stream = $lexer->first();
      printDebug("Could not create lexical stream for file ".$entity->relname,1) if !$stream;
      die "Could not read file: ".$entity->relname." Error " if !$stream;
      printDebug("Start Line: 0",2);
  }
   my $defined;
  # skip past any body declares for nested procedures
  my @nested_procs = $entity->ents("body declare") unless $mainEnt->kind->check("file");
  foreach my $nested_proc (@nested_procs) {
    my $end_ref = $nested_proc->ref("end");
    #die "No end reference found for ".$nested_proc->name."- Flowchart cannot be rendered" if (!$end_ref);
    if ($end_ref && $end_ref->line > $stream->line_end) {
      $stream = $lexer->lexeme($end_ref->line, 1);
    }
  }

  my $startText = "start";
  my $start_node = make_node($startText);
  $start_node->set("shape", "Msquare");
  $start_node->set("color", "darkgreen");
  my @start_nodes = ($start_node);

  
  # skip to the first block begin
  if ($fortran) {fortran_start();}
  elsif ($mainEnt->kind->check("~file")){
     # Walk forward to the start of the function data
    while ($stream && ! keyword_block_begin()){
      $defined=1 if $stream->ent && $stream->ent->id == $entity->id;
      $stream=$stream->next();
      if (! $stream || ($end_ref && $stream->line_begin() > $end_ref->line)){
        printDebug("Couldn't find source start before end of function",1);
        return;
      }
    }
    if (!$defined  && !$fortran && !$ada && !$vhdl ){
      printDebug( "Couldn't find the function definition reference for ".$mainEnt->longname." Most likely reason is it defined in Macro and macro expansion text is not saved",1);
      return;
    }
  }
  
  my $exits = parse_block(\@start_nodes,"function");
  push (@$exits, @returns);
  my $end_node = make_node("end");
  $end_node->set("shape", "Msquare");
  $end_node->set("color", "brown");
  printDebug("\$end_ref: line ".$end_ref->line,2) if $end_ref;
  printDebug("connecting Final Nodes",2);
  connect_nodes($exits, $end_node);
  printDebug("Script Complete. $nodecount Nodes, $edgecount Edges",2);
}

# Pre:  The stream is positioned at a non-whitespace, non-comment token
# Post: The stream is at a statement ending (keyword_block_end or keyword_statement_end)
sub parse_statement {
  my $parents = shift;
  my $block = shift;


  die "Undefined parent value - last parsed line:".$stream->line_begin() if ! defined($parents);

  if (is_overflowed()){
    printDebug("parse_statment end - overflowed",2);
    return $parents ;
  }


  if (!@$parents && !$exception && !keyword_goto_label()&& ! keyword_catch()) {
    # This means some orphan nodes will be left around.
    # They will appear in the diagram shaded red.
    printDebug ("Warning: Unreachable code at line ". $stream->line_begin);
    $unreachable = 1;
  }

  if ($unreachable) {
    # yes, really increment again even if !@$parents
    # Make sure that we have returned from the enclosing statement
    # before we find reachable code again.
    $unreachable++;
  }
  my $stmt = $stmtCount++;
  $stmtCompleted{$stmt}=$stream->line_begin;
  
  my $exits;

  if($asm_block){
    printDebug("call parse_simple_statement #$stmt",1);
    $exits = parse_simple_statement($parents, $block);
    printDebug("exit parse_simple_statement #$stmt",1);
  }elsif(keyword_function_begin()){
    printDebug("call function_parse #$stmt",1);
    $exits = function_parse($parents);
    printDebug("exit function_parse #$stmt",1);
  }elsif (keyword_block_begin()) {
    printDebug("call parse_block #$stmt",1);
    $exits = parse_block($parents,"keyword");
    printDebug("exit parse_block #$stmt",1);
  } elsif(keyword_if_arithmetic()){
    push(@decisionTree,$stmt);
    printDebug("call parse_if_arithmetic #$stmt",1);
    $exits = parse_if_arithmetic($parents);
    printDebug("exit parse_if_arithmetic #$stmt",1);
    pop(@decisionTree);
  } elsif(keyword_assign_to()){
    printDebug("call parse_assign_to #$stmt",1);
    $exits = parse_assign_to($parents);
    printDebug("exit parse_assign_to #$stmt",1);
  } elsif (keyword_if()) {
    push(@decisionTree,$stmt);
    printDebug("call parse_if #$stmt",1);
    $exits = parse_if($parents);
    pop(@decisionTree);
    printDebug("exit parse_if #$stmt",1);
  } elsif (keyword_pre_loop()) {
    push(@decisionTree,$stmt);
    printDebug("call parse_pre_loop #$stmt",1);
    $exits = parse_pre_loop($parents);
    printDebug("exit parse_pre_loop #$stmt",1);
    pop(@decisionTree);
  } elsif (keyword_post_loop()) {
    push(@decisionTree,$stmt);
    printDebug("call parse_post_loop #$stmt",1);
    $exits = parse_post_loop($parents);
    printDebug("exit parse_post_loop #$stmt",1);
    pop(@decisionTree);
  } elsif (keyword_uncond_loop()) {
    push(@decisionTree,$stmt);
    printDebug("call parse_uncond_loop #$stmt",1);
    $exits = parse_uncond_loop($parents);
    printDebug("exit parse_uncond_loop #$stmt",1);
    pop(@decisionTree);
  } elsif (keyword_switch()) {
    push(@decisionTree,$stmt);
    printDebug("call parse_switch #$stmt",1);
    $exits = parse_switch($parents);
    printDebug("exit parse_switch #$stmt",1);
    pop(@decisionTree);
  } elsif (keyword_case()) {
    printDebug("call parse_case #$stmt",1);
    $exits = parse_case($parents);
    printDebug("exit parse_case #$stmt",1);
  } elsif (keyword_try()) {
    printDebug("call parse_try #$stmt",1);
    $exits = parse_try($parents);
    printDebug("exit parse_try #$stmt",1);
  } elsif (keyword_catch()) {
    printDebug("call parse_catch #$stmt",1);
    $exits = parse_catch($parents);
    printDebug("exit parse_catch #$stmt",1);
  } elsif (keyword_throw()) {
    printDebug("call parse_throw #$stmt",1);
    $exits = parse_throw($parents);
    printDebug("exit parse_throw #$stmt",1);
  } elsif (keyword_break()) {
    printDebug("call parse_break #$stmt",1);
    $exits = parse_break($parents);
    printDebug("exit parse_break #$stmt",1);
  } elsif (keyword_continue()) {
    printDebug("call parse_continue #$stmt",1);
    $exits = parse_continue($parents);
    printDebug("exit parse_continue #$stmt",1);
  } elsif (keyword_return()) {
    printDebug("call parse_return #$stmt",1);
    $exits = parse_return($parents);
    printDebug("exit parse_return #$stmt",1);
  } elsif (keyword_goto_computed()) {
    printDebug("call parse_goto_computed #$stmt",1);
    $exits = parse_goto_computed($parents);
    printDebug("exit parse_goto_computed #$stmt",1);
  } elsif (keyword_goto()) {
    printDebug("call parse_goto #$stmt",1);
    $exits = parse_goto($parents);
    printDebug("exit parse_goto #$stmt",1);
  } elsif (keyword_generic_label()) {
    printDebug("call parse_keyword_generic_label #$stmt",1);
    $exits = parse_keyword_generic_label($parents);
    printDebug("exit parse_keyword_generic_label #$stmt",1);
  } elsif (keyword_loop_label()) {
    printDebug("call parse_keyword_loop_label #$stmt",1);
    $exits = parse_keyword_loop_label($parents);
    printDebug("exit parse_keyword_loop_label #$stmt",1);
  } elsif (keyword_goto_label()) {
    printDebug("call parse_keyword_goto_label #$stmt",1);
    $exits = parse_keyword_goto_label($parents);
    printDebug("exit parse_keyword_goto_label #$stmt",1);
  } elsif(keyword_asm()){
    printDebug("call parse_asm #$stmt",1);
    $exits = parse_asm($parents,$block);
    printDebug("exit parse_asm #$stmt",1);
  }else{
    printDebug("call parse_simple_statement #$stmt",1);
    $exits = parse_simple_statement($parents, $block);
    printDebug("exit parse_simple_statement #$stmt",1);
  }

  if ($unreachable) {
    $unreachable--;
  }

  my @exits = ();
  $exits = \@exits if (! defined $exits);
  $stmtCompleted{$stmt}=0;
  return $exits;
}

# Pre:  The stream is at a function definition keyword
# Post: The stream is at at the end of the function definition
#Currently skips over function definitions
sub function_parse{
  my $parents = shift;
  find(\&keyword_block_begin);
  token_next();
  my $blockCount=1;
  while ($stream->next && $blockCount){
    $blockCount++ if keyword_block_begin();
    $blockCount-- if keyword_block_end();
    token_next();
  }
  token_previous();
  return $parents;
}

# Pre:  The stream is at a block begin
# Post: The stream is at a block end
sub parse_block {
  my $parents = shift;
  my $add_end = shift;
  my $exits = $parents;
  my $is_empty_block = 1;

  printDebug("begin parse_block - $add_end",2);
  # Invariant: The stream is at a statement boundary
  if ($fortran) {
    find(\&is_interesting);
    my $blockended = 0;
    while (!keyword_block_end()&& !$blockended) {
      $exits = parse_statement($exits, 1); # post guarantees invariant
      if (keyword_fortran_passed_label() && keyword_statement_end() && $add_end eq "do"){
        # a fortran label was passed, meaning the block is done
        $blockended = 1 ;
        pop(@fortran_labels); #remove the offending label
      }
      elsif(keyword_fortran_passed_label() && keyword_statement_end()){
        #Here we hit a neccesary fortran label, but we're too far removed from the
        #do loop so we have to pop at an extra block level, and revisit label again
        $blockended=1;
      }
      else{find(\&is_interesting);}
        $is_empty_block = 0;
    }

    # If there is a single if statement and the next line has a discreet end,
    # block end needs to be called twice.

    if (token_current() eq "enddo" && $add_end ne "do"){
      printDebug("Double Exit",2);
      token_previous();
       $exits = parse_statement($exits,1);
    }
  }
  else {
    #Ada exceptions can be thrown at any level so the try lists are nested
    if ($ada|| $vhdl){
      my @newTry;
      push(@try_list,\@newTry);
    }
    find(\&is_interesting);
    my $exitLoop=0;
    while (!keyword_block_end() && !$exitLoop) {
      $is_empty_block = 0;
      $exits = parse_statement($exits, 1); # post guarantees invariant
      printDebug("End of statement in block",1);
      token_next() if $stream->next;
      while ($stream->next && ! is_interesting()){
        token_next();
      }
      printDebug("Intersting",1);
      if(! $stream->next){
        printDebug("End of File",1);
        last;
      }
      if (!$ada || $add_end ne "loop"){
        $exitLoop = check_list($add_end);
      }
    }
  }

  # is_empty block
  if ($is_empty_block) {
    printDebug("Making empty block (no_op)",2);
    my $no_op = make_node("no_op");
    connect_nodes($parents, $no_op);
    my @exits = ($no_op);
    $exits = \@exits;
  }

  if ($ada || $vhdl) {
    # check to see if this block is really ended
    if (check_list("exception")) {
      $exits = ada_exception_handler($exits);
    } elsif (check_list($add_end)) {
      token_previous(); # special case of 'when' block
    } elsif (check_list("end") && ! $add_end) {
      find(\&keyword_statement_end); # real block ending
    }
    pop(@try_list);
  }
  printDebug ("end parse_block - $add_end",2);
  token_previous() if ($mainEnt->kind->check("file") && ! $stream->next);
  return $exits;
}

# Pre:  The stream is at the if part of an if statement
# Post: The stream is at the last statement's ending
sub parse_if {
  my $parents = shift;
  my $flag = shift;
  my $wherestmt=1 if $fortran && check_list("where");
  printDebug("start parse_if $flag",2);
  find(\&keyword_condition_begin) if !($ada || $vhdl || $python);
  my $ifcondition = parse_condition($parents,"if"); # really a list

  my @exits = ();
  my $exits = \@exits;

  find(\&is_interesting) if !($ada || $vhdl || $python);
  $ifcondition->[0]->mode("yes");
  if ($ada || $vhdl) {
    $exits = parse_block($ifcondition);
  } elsif($fortran){
    if ($wherestmt){
      $exits = parse_block($ifcondition);
    }else{
      $exits = parse_statement($ifcondition,"if");
    }
  }elsif($python){
    find (\&keyword_block_begin) if $python;
    $exits = parse_block($ifcondition);
    token_next();
  }else {
    $exits = parse_statement($ifcondition);
        die "Undefined exit value - last parsed line:".$stream->line_begin() if ! defined($exits);
    }
  $ifcondition->[0]->mode("no");

  if ($ada || $vhdl || $fortran) {
    if (keyword_if() && check_list("elsif", "elif")) {
      my $if_exits = parse_if($ifcondition,"if");
      push (@$exits, @$if_exits);
    } elsif (keyword_else()) {
      my $else_exits = parse_block($ifcondition,"else");
      push (@$exits, @$else_exits);
    } else {
      # condition needs to point to the next node
      push(@$exits, @$ifcondition);
      
    }
    #Move forward until out of entire endif; statement
     find(\&keyword_statement_end) if !$fortran && ! keyword_statement_end();
  }elsif($python){    
   if (keyword_if() && check_list("elif")) {
      my $if_exits = parse_if($ifcondition,"if");
      push (@$exits, @$if_exits);
    } elsif (keyword_else()) {
      find (\&keyword_block_begin) if $python;
      my $else_exits = parse_block($ifcondition,"else");
      push (@$exits, @$else_exits);
    } else {
      # condition needs to point to the next node
      push(@$exits, @$ifcondition);
      token_previous();
    }
  }else {
    find(\&is_interesting);
    if (keyword_else()) {
      find(\&is_interesting);
      my $else_exits = parse_statement($ifcondition);
      die "Undefined exit value - last parsed line:".$stream->line_begin() if ! defined($exits);

      push (@$exits, @$else_exits);
    } else {
      # condition needs to point to the next node
      push(@$exits, @$ifcondition);

      # preserve postcondition?
      token_previous();
    }
    # dangling else?
  }
 printDebug("end parse_if $flag",2);
  return $exits;
}


# Pre:  The stream is at the if part of an arithmetic if statement
# Post: The stream is at the last statement's ending
sub parse_if_arithmetic{
  my $parents = shift;
  return $parents if !$fortran;
  printDebug("parse_if_arithmetic start",2);

  my $nodeComment = get_preceding_comment();
  find(\&keyword_condition_begin);
  my $ifcondition = parse_condition_recursive($parents); # really a list
  my $exits;

  my @conds;
  while ($stream && @conds < 3){
    push @conds,token_current() if $stream->token eq "Literal";
    last if @conds == 3;
    token_next();
  }
  my $node = make_switch_node("SWITCH: $ifcondition",$nodeComment);
  connect_nodes($parents, $node);
  my @switchparent = $node;

  my $conditionNode1 = make_node($ifcondition." < 0");
  my $conditionNode2 = make_node($ifcondition." = 0");
  my $conditionNode3 = make_node($ifcondition." > 0");

  connect_nodes(\@switchparent, $conditionNode1,"blue");
  connect_nodes(\@switchparent, $conditionNode2,"blue");
  connect_nodes(\@switchparent, $conditionNode3,"blue");

  if (exists $labels{$conds[0]}){
    my @nodes = ($conditionNode1);
    connect_nodes(\@nodes, $labels{$conds[0]});
  } else {
    push @{$deferred_gotos{$conds[0]}}, $conditionNode1;
  }

  if (exists $labels{$conds[1]}){
    my @nodes = ($conditionNode2);
    connect_nodes(\@nodes, $labels{$conds[1]});
  } else {
    push @{$deferred_gotos{$conds[1]}}, $conditionNode2;
  }

  if (exists $labels{$conds[2]}){
    my @nodes = ($conditionNode3);
    connect_nodes(\@nodes, $labels{$conds[2]});
  } else {
    push @{$deferred_gotos{$conds[2]}}, $conditionNode3;
  }
  find(\&keyword_statement_end);
        return ();
}

# Pre:  The stream is at a pre-test loop keyword
# Post: The stream is at the statement/block ending
sub parse_pre_loop {
  my $parents = shift;
  printDebug("start parse_pre_loop",2);
  my @breaks;
  push(@break_list, \@breaks);

  my $exits;
  if ($ada || $vhdl || $python) {
    $exits = parse_condition($parents,"loop");
    my $condition = $exits->[0];
    if ($keyword_loop_label) {
      $condition->label($keyword_loop_label);
      $keyword_loop_label = 0;
    }

    $exits->[0]->mode("yes");
    find(\&keyword_block_begin) if $python;
    my $loop_exits = parse_block($exits,"do");
    #token_next() if $python;
    $exits->[0]->mode("no");

    my @current;
    foreach my $loop_exit (@$loop_exits) {
      if ($loop_exit->mode eq "break" && $loop_exit->label) {
        if ($loop_exit->label eq $condition->label) {
          $loop_exit->mode("broken");
        }
        push(@$exits, $loop_exit);
      } else {
        push(@current, $loop_exit);
      }
    }
    connect_nodes(\@current, $condition);
  } elsif ($fortran) {
    my @continues;
    push(@continue_list, \@continues);
    my $labelExit = $parents;
    my $keyword_loop_label = find(\&is_interesting);
    if (token_current() =~ /^\d+/){
      push(@fortran_labels,$keyword_loop_label);#set label
      #$labelExit = parse_keyword_goto_label($parents);
      find(\&is_interesting); #get to the conditions
    }
    find(\&is_interesting) if token_current() =~ /while/i; #skip the while
    $exits = parse_condition($labelExit,"do");
    $exits->[0]->mode("yes");
    my $statement_exits = parse_block($exits,"do");
    $exits->[0]->mode("no");

    my $continues = pop(@continue_list);
    push(@$statement_exits, @$continues);
    connect_nodes($statement_exits, $exits->[0]);

  }else {
    my @continues;
    push(@continue_list, \@continues);

    find(\&keyword_condition_begin);
    $exits = parse_condition($parents,"loop");
    find(\&is_interesting);
    $exits->[0]->mode("yes");
    my $statement_exits = parse_statement($exits);
    $exits->[0]->mode("no");

    my $continues = pop(@continue_list);
    push(@$statement_exits, @$continues);
    connect_nodes($statement_exits, $exits->[0]);
  }

  pop(@break_list);
  push(@$exits, @breaks);
printDebug("end parse_pre_loop",2);
  return $exits;
}

# Pre:  The stream is at a post-test loop keyword
# Post: The stream is at the last statement/block ending
sub parse_post_loop {
  my $parents = shift;
  printDebug("start parse_post_loop",2);

  my @breaks;
  push(@break_list, \@breaks);
  my @continues;
  push(@continue_list, \@continues);

  find(\&is_interesting);
  $loop_head++;
  my $statement_exits = parse_statement($parents);
  my $post_loop_node = pop(@loop_head_list);
  find(\&keyword_condition_begin);
  my $exits = parse_condition($statement_exits,"loop");
  $exits->[0]->mode("yes");
  connect_nodes($exits, $post_loop_node);
  $exits->[0]->mode("no");

  my $continues = pop(@continue_list);
  connect_nodes($continues, $exits->[0]);

  my $breaks = pop(@break_list);
  push(@$exits, @$breaks);

  # for C style do-while consume a ';'
  find(\&keyword_statement_end);
  printDebug("end parse_post_loop",2);
  return $exits;
}

# Pre:  The stream is at an unconditional loop keyword
# Post: The stream is at the last statement/block ending
sub parse_uncond_loop {
  my $parents = shift;
  printDebug("start parse_uncond_loop",2);
  my @breaks;
  push(@break_list, \@breaks);
  my $label;
  
  if ($keyword_loop_label) {
      $label = $keyword_loop_label;
      $keyword_loop_label = 0;
    }
  
  $loop_head++;
  my $loop_exits = parse_block($parents,"loop");
  my $uncond_loop_node = pop(@loop_head_list);

  my $exits = pop(@break_list);
  
  if ($label){
    push(@$exits,@{$named_break_list{$label}});
  }
  
  my @current;
  foreach my $loop_exit (@$loop_exits) {
    if ($loop_exit->mode eq "break" && $loop_exit->label) {
      $loop_exit->mode("broken") if ($loop_exit->label eq $uncond_loop_node->label);
      push(@$exits, $loop_exit);
    } else {
      push(@current, $loop_exit);
    }
  }
  connect_nodes(\@current, $uncond_loop_node);
  
  if($ada || $vhdl){
  #Move forward until out of entire end loop statement
    find(\&keyword_statement_end);
  }
  printDebug("end parse_uncond_loop",2);
  return $exits;
}


# Pre:  The stream is at switch keyword
# Post: The stream is at a block ending
sub parse_switch {
  my $parents = shift;
  my $switch_var = "";
  my $firstAdaSelect;
  printDebug("begin parse_switch",2);
  if ($ada && check_list("select") && is_keyword()){
      printDebug("in ada_select_block",2);
      $ada_select_block = 1;
      $firstAdaSelect = 1;
    }
  my $nodeComment = get_preceding_comment();
  # set the switch variable text
  if ($ada || $vhdl || $fortran) {
    if ($ada_select_block){
      $switch_var = "select";
    }else{
      $switch_var = get_condition_text();
    }
  } else {
    find(\&keyword_condition_begin);
    $switch_var = parse_condition_recursive();
    find(\&keyword_block_begin);
  }
  $switch_var =~ s/\)$//;
  $switch_var =~ s/^\(//;
  push(@switch_var,$switch_var);
  push(@switchdefault,0);

  my @breaks;
  push(@break_list, \@breaks);

  my $switchname = "SWITCH";
  $switchname  = "CASE" if ($ada || $vhdl);
  my $node;
  $node = make_switch_node("$switchname: (".$switch_var.")",$nodeComment) unless $ada_select_block;
  $node = make_switch_node("select",$nodeComment) if $ada_select_block;
  connect_nodes($parents, $node);

  my @switchparent = ($node);
  my @exits = ();

  while ((!$ada_select_block && !keyword_block_end() && !is_overflowed()) || ($ada_select_block && token_current() !~ /endselect/i) && !is_overflowed())
  {
    if (keyword_case() || $firstAdaSelect){
      $firstAdaSelect = 0;
      my $parse = parse_case(\@switchparent);
    }
    token_next();
  }
  find(\&keyword_statement_end) if $ada || $vhdl; # real block ending
  push(@exits,@cStyleFallThrough);
  @cStyleFallThrough = ();

  pop(@switch_var);
  pop(@break_list);
  push(@exits, @breaks);

  # manually create a default flow if no default was specified
  # if (! pop(@switchdefault) && ! $ada_select_block){
    # my $text = "DEFAULT";

    # my $node = make_node($text);
    # connect_nodes(\@switchparent, $node,"blue");
    # push(@exits,$node);
  # }
  #token_previous() if $ada || $vhdl; #preserve invariant
  if ($ada_select_block){
    $ada_select_block=0;
    printDebug("exit ada_select_block",2);
  }
  printDebug("end parse_switch",2);
  return \@exits;
}


# Pre:  The stream is at an exception keyword
# Post: The stream is at a block ending
sub ada_exception_handler {
  return unless $ada || $vhdl;
  my $parents = shift;
  $exception = 1;
  my $switch_var = "Exception";
  printDebug("begin ada_exception_handler",2);

  push(@switch_var,$switch_var);
  push(@switchdefault,0);

  my @breaks;
  push(@break_list, \@breaks);

  my $node = make_exception_node("Exception");
  
  # set to exception mode so that edges are drawn correctly
  if (@try_list){
  my $last_try = $try_list[-1];
    foreach my $try_node (@$last_try) {
      $try_node->mode("exception");
      my @tries = ($try_node);
      connect_nodes(\@tries,$node);
      $try_node->mode($try_node->previous_mode); # restore previous value
    }
  }
  my @switchparent = ($node);
  my @exits = ();

  while ( token_current() !~ /end/i && !is_overflowed())
  {
    if (keyword_case()){
      my $parse = parse_case(\@switchparent);
    }
    token_next();
  }
  find(\&keyword_statement_end); # real block ending

  pop(@switch_var);
  pop(@break_list);
  push(@exits, @breaks);
  push(@exits,@{$parents});

  printDebug("end ada_exception_handler",2);
  $exception = 0;
  
  return \@exits;
}


# Pre:  The stream is at a case keyword
# Post: The stream is immediately before next case or block ending
# TODO: This could use some further work/cleanup
sub parse_case {
  my $parents = shift;
  my (@exits,$exits);
  printDebug("start parse_case".($ada_select_block?" - \$ada_select_block":""),2);
  unless($ada_select_block){
    my $nodeComment = get_preceding_comment();
    my $text = parse_case_text();
    # do a manual construction of the condition node
    my $condition = make_node($text,$nodeComment);

    connect_nodes($parents, $condition,"blue");
    @exits =($condition);
    $exits = \@exits;
  }else{
    @exits = @$parents;
    $exits = \@exits;
  }

  if ($ada || $vhdl) {
    my $breaks = parse_block($exits, "when");
    push(@{$break_list[-1]}, @{$breaks}) if @break_list;
    $exits = ();
    token_previous();
  } else {
    # case statements have degenerate blocks
    # parse all statements up to the next case or switch end
    find(\&is_interesting);

    push(@$exits,@cStyleFallThrough);
    @cStyleFallThrough = ();

    my $fallthrough = 1;
    my $break = 0;
    while (!keyword_case() && !keyword_block_end() && !is_overflowed()) {
      $break = 1 if keyword_break() || keyword_return();
      $exits = parse_statement($exits, 1);
      find(\&is_interesting);
      $fallthrough = 0;
    }
    #C++ allows  a fallthrough as long as there is no break
    if ($fallthrough || (keyword_case() && !$fortran)){
      @cStyleFallThrough = @$exits if $exits;
      @$exits = ();

      }
    elsif(!$break && $exits){
      #handle the case where there is no break
      $exits->[0]->mode("break") if $exits->[0] && !$exits->[0]->mode();
      push(@{$break_list[-1]}, $exits->[0]) if $exits->[0] && @break_list;
    }
    # preserve postcondition since we stopped on
    # a case or block ending above
    token_previous();
  }
  printDebug("exit parse_case",2);
  return $exits;
}

sub parse_case_text{
  printDebug("start parse_case_text",2);
  my $is_default;
  my $text;
  my $var = pop(@switch_var);
  push(@switch_var,$var);
  $var .= " = ";


  if ($ada || $vhdl) {
    $is_default = 0;
    token_next();
    while (!keyword_when_condition_end()) {
      $text .= token_current();
      token_next();
    }
    $text =~ s/^\s+|\s+$//g;
    $is_default = 1 if $text =~ /^others/i;
  } else {
    $is_default = 1;
    # take everything up to the colon as the text
    my $token = find(\&text);
    while (((!$fortran && $token ne ":") || ($fortran && $token ne "\n"))  && !is_overflowed()) {
      $text .= $token;
      $is_default = 0 if $token !~ /\s+/;
      $token = find(\&text);
    }
    # case == "" indicates default case
    $text .= "default" if $is_default;
    $text =~ s/^\s+|\s+$//g;
    $is_default = 1 if $text =~ /^default/i;
  }
  if ($is_default){
    pop (@switchdefault);
    push (@switchdefault,1);
  }

  $text = $var.$text if (! $is_default);
  $text = format_text($text);

  #if the condition fallsthrough to the next and collapse is on, include in the text
  if($collapse ne "None"){
    my $save = $stream;
    find(\&is_interesting);
    if (keyword_case()){
      $text .= "\n".parse_case_text();
    }
    else{
      $stream = $save;
    }
  }
  return $text;

}

# Pre:  The stream is at a keyword_condition_begin
# Post: The stream is at the matching keyword_condition_end
sub parse_condition {
  my $parents = shift;
  my $conditionType = shift;
  printDebug("start parse_condition",2);
  my $nodeComment = get_preceding_comment();
  my $text;
  if ($ada || $vhdl || $python) {
    $text = get_condition_text();
  }elsif ($fortran){
    if ($conditionType eq "do"){
      while (!keyword_statement_end()) {
        $text .= token_current() if ! is_comment();
        token_next();
      }
    }
    else{ $text = parse_condition_recursive();}

  } else {
    $text = parse_condition_recursive();
  }
  $text = format_text($text);


  my $condition;
  if ($conditionType =~ /loop|do/i){
  $condition = make_loop_node($text,$nodeComment);
  }else{
  $condition = make_conditional_node($text,$nodeComment);
  }

  connect_nodes($parents, $condition);
  my @exits = ($condition);
  return \@exits;
}


# Pre:  The stream is at a keyword_condition_begin
# Post: The stream is at the matching keyword_condition_end
sub parse_condition_recursive {
  # This doesn't need to do anything other than get
  # whatever is in the outermost parens
  printDebug("start parse_condition_recursive",2);
  my $text = "(";
  find(\&text);
  while (!keyword_condition_end()) {
    if (keyword_condition_begin()) {
      $text .= parse_condition_recursive();
    } else {
      $text .= token_current();
    }
    find(\&text);
  }
  return $text . ")";
}


# Pre:  The stream is at a keyword_condition_begin (any keyword)
# Post: The stream is at the next keyword_condition_end
sub get_condition_text {
  # This doesn't need to do anything other than get
  # whatever is in between the condition tokens
  printDebug("start get_condition_text",2);
  my $text;
  token_next();
  while (!keyword_condition_end()) {
    $text .= token_current() if $stream->token() ne "Comment";
    token_next();
  }
  return format_text($text);
}

# Pre:  The stream is at a assign_to_begin
# Post: The stream is at the statement end
sub parse_assign_to {
  printDebug("start parse_assign_to",2);
  my $parents = shift;
  return $parents if !$fortran;
  my $label= $stream->next()->next()->text();
  my $to = $stream->next()->next()->next()->next();
  my $variable = $to->next()->next()->text();
  $labelAlias{$variable} = $label;
  return parse_simple_statement($parents);
}





# Pre:  The stream is at a __asm keyword
# Post: The stream is at a __asm block end
sub parse_asm{
  my $parents=shift;
  my $block = shift;
  printDebug("start parse_asm",2);
  $asm_block =1;
  find(\&is_interesting); 
  
  if(token_current() eq "{"){
    find(\&is_interesting);
    while(!check_list("}")){
      $parents=parse_simple_statement($parents,$block) ;
      find(\&is_interesting);
    }
  }else{
    $parents=parse_simple_statement($parents,$block) ;
  }
  $asm_block=0;
  return $parents;
}

# Pre:  The stream is at the first non-is_whitespace token of this statement
# Post: The stream is at a statement end
sub parse_simple_statement {
  my $parents = shift;
  my $block = shift;
  printDebug("start parse_simple_statement",2);
  my $text;
  my $nodeComment = get_preceding_comment();
  my $localBraces;

  my $startLine = $stream->line_begin;
  if (keyword_statement_end()) {
    printDebug("no_op",2);
    $text = "no_op";
  } elsif ($collapse ne "None" && $block) {
    my $processcollapse =0; #use this to make sure we don't get stuck in an infinite loop
    
    while (!$fortran) {
      if (keyword_block_begin()){
        $localBraces++;
      }elsif(keyword_block_end() && $localBraces){
        $localBraces--;
      }elsif($stream->token eq ("Newline") && $stream->previous->token eq ("Newline")){
        last if $collapse eq "Collapse By Block";
      }else{
        last if is_structure() || ! $stream->next;
      }
      
      $text .= token_current() unless $stream->token eq "Comment";
      
      find(\&text);
      if ($vhdl && token_current() eq "<="){
        $text .= token_current();
        #We're in a signal assignment
        printDebug("Signal assignment",2);
        while(! keyword_statement_end()){
          find(\&text);
          $text .= token_current();
        }
      }
      $processcollapse=1;
    }
    while ($fortran && !is_structure() && !(keyword_fortran_passed_label() && keyword_statement_end())) {
      $text .=token_current()if !keyword_fortran_labeled_control_line() && !($fortran && token_current() =~ /continue/i)  && $stream->token !~ /Continuation/;
      find(\&text) ;
      $processcollapse=1;
      }
    token_previous() if !(keyword_fortran_passed_label() && keyword_statement_end()) && $processcollapse;
    
    $text = format_text($text);
    $text =~ s/(;\s*\n\s*)|(;$)/\\l/g;  # left justify the block

  } else {
    my $newline = 0;
    while (!keyword_statement_end()) {
      if (keyword_block_begin()){
        $localBraces++;
      }elsif(keyword_block_end() && $localBraces){
        $localBraces--;
      }else{
        last if is_structure() || ! $stream->next;
      }
      #this is here so the new line is inclued in fortran in case of a continued line
      $text .= "\n" if $newline;
      $newline = 0;
      if ($fortran){
        $text .= token_current() if !keyword_fortran_labeled_control_line() && !(token_current() =~ /continue/i) && $stream->token !~ /Continuation/;
      }else{
        $text .= token_current() if $stream->token !~ /Continuation|Comment/;
      }
      find(\&text);
      if ($vhdl && token_current() eq "<="){
        $text .= token_current();
        #We're in a signal assignment
        printDebug("Signal assignment",2);
        while(! keyword_statement_end()){
          find(\&text);
          $text .= token_current();
        }
      }
      $newline = 1 if token_current() =~ /\n/ && $fortran;
    }

    token_previous() if is_structure() && !$vhdl;
    $text = format_text($text,$nodeComment);

  }
  #Don't make is_empty nodes
  if ($text){
    my $node = make_node($text,$nodeComment,"",$startLine);
    connect_nodes($parents, $node);
    my @exits = ($node);
    printDebug("end parse_simple_statement ($text)",2);
    return \@exits;
  }
  else {
    printDebug("end parse_simple_statement (empty)",2);
    return $parents;
  }
}


# Pre:  The stream is at a label
# Post: The stream is at the label end
sub parse_keyword_goto_label {
  my $parents = shift;
  printDebug("start parse_keyword_goto_label",2);
  my $label = ($ada || $vhdl) ? token_next() : token_current();
  find(\&keyword_label_end) if (!$fortran);

  # see if we can reach this label before we try to create it
  # there are still (exquisite) cases where this could be broken
  if (exists $deferred_gotos{$label}) {
    $unreachable = 0;
  }
  my $node = make_label_node(format_text($label));
  $labels{$label} = $node;
  if (exists $deferred_gotos{$label}) {
    # connect gotos above this label
    connect_nodes($deferred_gotos{$label}, $node);
  }

  connect_nodes($parents, $node);
  my @exits = ($node);
  return \@exits;
}


# Pre:  The stream is at a label
# Post: The stream is at the colon separator
sub parse_keyword_generic_label {
  my $parents = shift;
  printDebug("start parse_keyword_generic_label",2);
  my $label = token_current();
  find(\&is_interesting) if (!$fortran);#no colon

  my $node = make_label_node($label);
  connect_nodes($parents, $node);
  my @node = ($node);

  return \@node;
}


# Pre:  The stream is at a loop label
# Post: The stream is at the colon separator
sub parse_keyword_loop_label {
  my $parents = shift;
  printDebug("start parse_keyword_loop_label",2);
  $keyword_loop_label = token_current();
  find(\&is_interesting);

  my $node = make_label_node($keyword_loop_label);
  connect_nodes($parents, $node);
  $loop_labels{$keyword_loop_label}=$node;
  my @node = ($node);

  return \@node;
}


# Pre:  The stream is at a try keyword
# Post: The stream is at a try block end
sub parse_try {
  my $parents = shift;
  printDebug ("start parse_try");
  # clear list of old values
  @try_list = ();
  $try++; # with $try set any node can possibly throw an exception
  my $exits;
  if ($python){
    find(\&keyword_block_begin) ;
    $exits = parse_block($parents);
  
  }else{
    find(\&is_interesting);
    $exits = parse_statement($parents);
  }
  $try--;
  printDebug("end parse_try",2);
  return $exits;
}


# Pre:  The stream is at a catch or finally keyword
# Post: The stream is at a catch block end
sub parse_catch {
  my $parents = shift;
  printDebug ("start parse_catch");
  my $text = token_current();
  
  find(\&is_interesting);
  if(keyword_condition_begin()){ 
    $text = format_text(parse_condition_recursive());
    }

  my $exceptionNode = make_exception_node($text);   
  
  # set to exception mode so that edges are drawn correctly
  foreach my $try_node (@try_list) {
    $try_node->mode("exception");
    my @trys = ($try_node);
    connect_nodes(\@trys,$exceptionNode);
    $try_node->mode($try_node->previous_mode); # restore previous value
    #Finally's intercept return statements
    if ($text =~ /finally/i){
        my @keepers;
        foreach my $return_node (@returns){
          if ($return_node == $try_node){
            connect_nodes([$return_node],$exceptionNode);
          }else{
            push @keepers,$return_node;
          }
        }
        @returns = @keepers;
    }
  }
  find(\&keyword_block_begin) if $python;
  find(\&is_interesting) unless keyword_block_begin() || $python;
  my $exits = parse_block([$exceptionNode]);

  if ($text =~ /finally/i){
    connect_nodes($parents,$exceptionNode);
  }else{
  push (@$exits, @$parents);
  }
  printDebug ("end parse_catch");
  return $exits;
}


# Pre:  The stream is at a catch keyword
# Post: The stream is at a catch block end
sub parse_throw {
  my $parents = shift;
  printDebug ("start parse_throw");
  my $node_list = parse_unstructured_statement($parents);
  $node_list->[0]->mode("throw");
  $node_list->[0]->set("color", "red");

  if (!$try) {
    push(@returns, $node_list->[0]);
  }

  return ();
}


# Pre:  The stream is at a break keyword
# Post: The stream is at the break statement end
sub parse_break {
  my $parents = shift;
    printDebug("start parse_break",2);
  my $nodeComment = get_preceding_comment();
  if (! @break_list){
    printDebug("end parse_break - no \@break_List",2);
    return parse_return($parents);
  }elsif ($ada || $vhdl) {
    my $label;
    my $text = token_current();
    find(\&is_interesting);
    my $breakTarget;
    if (!keyword_condition_begin() && !keyword_statement_end()) {
      $label = token_current();
      if($loop_labels{token_current()}){
          $breakTarget=token_current();
        }else{
          $text .= " $label";
      }
      find(\&is_interesting);
    }
    #If there is a condition node, make it come before the break node
    my $condition;
    my $break;

    if (keyword_condition_begin()) {
      $condition = parse_condition($parents); # really a list
      $condition->[0]->mode("yes");
      printDebug("parse_break make node - with condition",2);
      $break = make_node($text,$nodeComment,"break");
      connect_nodes($condition, $break);
      $condition->[0]->mode("no");
    } else {
      # assert keyword_statement_end()
      printDebug("parse_break make node - no condition",2);
      $break = make_node($text,$nodeComment,"break");
      connect_nodes($parents, $break);

    }

    if($breakTarget){
      push(@{$named_break_list{$breakTarget}},$break);
    }else{
      push(@{$break_list[-1]}, $break) if @break_list;
    }
    printDebug("end parse_break",2);
    return $condition;

  } else {
    my $node_list = parse_unstructured_statement($parents);
    $node_list->[0]->mode("break");

    push(@{$break_list[-1]}, $node_list->[0]) if @break_list;
    printDebug("end parse_break",2);
    return ();
  }
}


# Pre:  The stream is at a continue
# Post: The stream is at the continue statement end
sub parse_continue {
  my $parents = shift;
  printDebug("start parse_continue",2);
  my $node_list = parse_unstructured_statement($parents);
  $node_list->[0]->mode("continue");

  push(@{$continue_list[-1]}, $node_list->[0]) if @continue_list;
  printDebug("end parse_continue",2);
  return ();
}


# Pre:  The stream is at a return keyword
# Post: The stream is at the return statement end
sub parse_return {
  my $parents = shift;
  printDebug("start parse_return",2);

  my $node_list = parse_unstructured_statement($parents);
  $node_list->[0]->mode("return");

  push(@returns, $node_list->[0]);
  printDebug("end parse_return",2);
  return ();
}

# Pre:  The stream is at the first non-whitespace token of this statement
# Post: The stream is at a statement end
sub parse_unstructured_statement {
  my $parents = shift;
  my $nodeComment = get_preceding_comment();
  printDebug("start parse_unstructured_statement",2);
  my $text;
  while (!keyword_statement_end()) {
    $text .= token_current();
    find(\&text);
  }

  my $node = make_node(format_text($text),$nodeComment);
  connect_nodes($parents, $node);
  my @exits = ($node);
  printDebug("ending parse_unstructured_statement",2);
  return \@exits;
}


# Pre:  The stream is at a goto keyword
# Post: The stream is at a statement end
sub parse_goto {
  my $parents = shift;
  printDebug("start parse_goto",2);
  my $nodeComment = get_preceding_comment();
  my $text = token_current(); # the keyword
  my $label = find(\&is_interesting); # the label
  $text .= " $label";
  my $node = make_node($text, $nodeComment,"goto");
  connect_nodes($parents,$node);
  $label =~ s/^\s+|\s+$//;
  $label = $labelAlias{$label} if $labelAlias{$label};

  if (exists $labels{$label}) {
    my @nodes = ($node);
    connect_nodes(\@nodes, $labels{$label});
  } else {
    push @{$deferred_gotos{$label}}, $node;
  }

  find(\&keyword_statement_end);

  return ();
}

# Pre:  The stream is at a computed goto keyword
# Post: The stream is at a statement end
#    GOTO (snr1, snr2, snr3), integer_expression
#    conditional GOTO statement. If the integer expression is 1, 2 or 3, execution jumps to statement
#    number snr1, snr2 or snr3 (an arbitrary number of statement numbers snr are permitted).
sub parse_goto_computed {
  my $parents = shift;
  printDebug("start parse_goto_computed",2);
  return $parents if !$fortran;
  my $nodeComment = get_preceding_comment();

  my $lex = $stream->next();
  find(\&is_interesting);
  my $cl;
  while ($stream && $stream->text() ne ")"){
    $cl .= $stream->text() if $stream->token !~ /Continuation/i;
    $stream = $stream->next();
  }
  $cl =~ s/\(|\)|\s//g;
  my @computedlabels = split(/,/,$cl);
  my $exits;

  find(\&is_identifier);
  my $expression = token_current();
  $expression =~ s/\s//g;

  my $node = make_switch_node("Goto: $expression",$nodeComment);
  connect_nodes($parents, $node);
  my @switchparent = $node;

  for (my $i = 0; $i < @computedlabels; $i++)
  {
    my $label = $computedlabels[$i];
    $label =~ s/\s//g;
    my $conditionNode = make_node($expression." = ".($i+1));
    connect_nodes(\@switchparent, $conditionNode,"blue");
    if (exists $labels{$label}){
      my @nodes = ($conditionNode);
      connect_nodes(\@nodes, $labels{$label});
    } else {
      push @{$deferred_gotos{$label}}, $conditionNode;
    }
  }
  #default fall through
  my $defaultNode = make_node("default");
  connect_nodes(\@switchparent, $defaultNode,"blue");


  find(\&keyword_statement_end);
  my @exits = ($defaultNode);
  return \@exits;

}

# Connect all of the sources to target
sub connect_nodes {
  my $sources = shift;
  my $target = shift;
  my $color = shift;

  foreach my $source (@$sources) {
    if ($color){
      make_color_edge($source, $target, $color);
    } elsif ($source->mode eq "yes") {
      make_yes_edge($source, $target);
    } elsif ($source->mode eq "no") {
      make_no_edge($source, $target);
    } elsif ($source->mode eq "exception") {
      make_exception_edge($source, $target) unless $source == $target;
    } else {
      make_edge($source, $target);
    }
  }
}

#
# Stream Manipulation
#
sub token_current {
  if ($stream && ! is_overflowed()) {
    #the fortran lexer is returning endif as 2 tokens
    if (($fortran || $vhdl || $ada)&& appended_function($stream))
    {
      return appended_function($stream);
    }
    else { return $stream->text();}
  }
  else{
    my $lastStream;
    if ($stream){
      $lastStream = $stream->previous;
      while($lastStream && $lastStream->text !~ /\S/){
        $lastStream = $lastStream->previous;
      }
    }
    printDebug("Last String - ". ($lastStream?$lastStream->text:" <<No Lexeme>>"),2);
    if ($lastStream && $lastStream->text eq '...'){
      printDebug("Macro not fully expanded, canceling analysis",1);
      die("Could not successfully parse control flow for ".$mainEnt->longname." in '".$file_ent->relname."'. The macros were too large to expand.\n");
    }else{
      printDebug("Unexpected end of file. ".getUnfinishedStmts(),1);
      die("Could not successfully parse control flow for ".$mainEnt->longname." in '".$file_ent->relname."'. If it compiles correctly and your corporate policy allows it, please send a copy of the file to support\@scitools.com.\n".getUnfinishedStmts());
    }
    
  }
}

sub token_next {
  if ($stream) {
            my $nextreturn;
            if($token_count++ > MAXTOKENCOUNT){
                printDebug("Max Count Reached, parse stopped",1);
                die "Could not finish parsing control flow for ".$mainEnt->longname." in '".$file_ent->relname."', either it is too large or parsing is in an infinite loop";
            }
            if ($vhdl || $fortran || $ada){

      #get linelabels if in Fortran
      if ($fortran && keyword_fortran_label()){
        #if there is a label and it is the next token, shove it onto the stack
        push(@found_keyword_fortran_labels,keyword_fortran_label());
      }
      #the fortran lexer is returning commands as 2 or 3 tokens
      if (appended_function($stream)){
        #the command has a space in the middle
        if ($stream->next()->text() =~ /\s/){
          $stream = $stream->next()->next()->next();}
        else{  $stream = $stream->next()->next();}
      }
      else{$stream = $stream->next();}
    }
    else {$stream = $stream->next();}
    return token_current();
  }
  else{
    printDebug("Unexpected end of file - Token_next\n",1);
    die("Unexpected end of file - Token_next\n");
  }
}

sub token_previous {
  if ($stream) {
    if ($fortran || $vhdl || $ada){
      #the fortran lexer is returning commands as 2 or 3 tokens
      if (appended_function($stream->previous()->previous())){
        $stream = $stream->previous()->previous();
      }
      elsif ($stream->previous()->previous()->text() =~ /\s/ && appended_function($stream->previous()->previous()->previous())){
        $stream = $stream->previous()->previous()->previous();
      }
      else{
        $stream = $stream->previous();
      }
    }
    else {$stream = $stream->previous();}
    return token_current();
  }
}

sub format_text {
  my $text = shift;
  $text =~ s/[ \t]+/ /g;   # remove extra is_whitespace
  $text =~ s/\n\s*\n/\n/g; # remove consecutive newlines
  $text =~ s/\s+$//g;      # remove trailing is_whitespace
  $text =~ s/^\s+//g;      # remove leading is_whitespace
  $text =~ s/\s+\)$/\)/g;  # remove extra is_whitespace before final ')'
  $text =~ s/^\(\s+/\(/g;  # remove extra is_whitespace after initial '('
  $text =~ s/\\/\\\\/g;    # escape literal backslashes
  return $text;
}


# Find the next token for which the given predicate is true
sub find {
  my $predicate = shift;
  token_next();  # does not consider the current token
  while (!&$predicate() && $stream) {
    token_next();
  }
  return token_current();
}

sub get_preceding_comment{
  my $substream = $stream;
  my $comment;
  printDebug("start get_preceding_comment:",3);
  token_previous();
  while ($stream && ! keyword_statement_end() && !keyword_block_end() && !is_preprocessor() && !is_inactive() && ! keyword_block_begin() ){
    my $line = token_current();
    if (is_comment()){
      $comment = $line."\n".$comment;
    }
    token_previous();
  }
  $stream = $substream;
  printDebug("end get_preceding_comment - $comment",3);
  return $comment;
}

sub getUnfinishedStmts{
  my $string= "Statements on the following lines did not finish parsing: ";
  my $val;
  foreach (sort keys %stmtCompleted){
    next unless $stmtCompleted{$_};
    $string .= $stmtCompleted{$_}. ", ";
    $val=1;
  }
  return $string if $val;
}


#
# Graph manipulation
#

sub make_node {
  my $label = shift;
  my $nodeComment = shift;
  my $id = $node_number++;
  printDebug("Make_node #$id, $label, $nodeComment",2);
  my $flow_node = FlowNodeLib->new($id,shift);
  my $alt_start_line = shift;


  if ($loop_head && (!($ada && $vhdl)|| $label !~ /exit/i)) {
    for (my $i = 0; $i < $loop_head; $i++) {
      push(@loop_head_list, $flow_node);
    }
    $loop_head = 0;
  }

  if ($ada || $vhdl) {
    #with ada anything can throw an exception, you don't know ahead of time
    if (@try_list){
      my $lastTry=$try_list[-1];
      push(@$lastTry,$flow_node) if ! $exception;
    }
  }else{
    # with $try set any node may possibly throw an exception
    push (@try_list, $flow_node) if $try;
  }
  $nodecount++;

  $flow_node->set("file_ent",$file_ent);
  $flow_node->set("line",$stream->line_begin);
  $flow_node->set("line",$alt_start_line) if $alt_start_line;
  $flow_node->set("comment",$nodeComment) if $nodeComment;
  $flow_node->set("label",$label);
  $flow_node->set("unreachable",1) if $unreachable;
  $flow_node->set("assembly",1) if $asm_block;
  $flow_node->set("decisionTree",join(",",@decisionTree));

  $flowchartNodes[$id]=$flow_node;
  return $flow_node;
}

sub make_conditional_node {
  my $node = make_node(@_);
  $node->set("shape", "diamond");
  $node->set("type", "conditional");
  return $node;
}

sub make_exception_node {
  my $node = make_node(@_);
  $node->set("shape", "ellipse");
  $node->set("color", "red");
  $node->set("type","exception");
  return $node;
}

sub make_switch_node {
  my $node = make_node(@_);
  $node->set("shape", "doubleoctagon");
  $node->set("color", "blue");
  $node->set("type","switch");
  return $node;
}

sub make_loop_node {
  my $node = make_node(@_);
  $node->set("shape", "ellipse");
  $node->set("color", "blue");
  $node->set("type","loop");
  return $node;
}

sub make_label_node {
  my $node = make_node(@_, "label");
  $node->set("color", "salmon");
  $node->set("type","label");
  return $node;
}

sub make_edge {
  $edgecount++;
  my $edge = FlowEdgeLib->new(@_);
  return $edge;
}

sub make_yes_edge {
  my $edge = make_edge(@_);
  $edge->set("type", "yes");
  $edge->set("color", "green");
  return $edge;
}

sub make_no_edge {
  my $edge = make_edge(@_);
  $edge->set("type", "no");
  $edge->set("color", "red");
  return $edge;
}

sub make_color_edge {
  my ($source, $target,$text) = @_;
  my $edge = make_edge($source,$target);
  $edge->set("label", $text);
  $edge->set("color", $text);
  return $edge;
}

sub make_exception_edge {
  my $edge = make_edge(@_);
  $edge->set("type", "exception");
  $edge->set("color", "#888888");
  return $edge;
}

#
# Language Specific
#
sub keyword_function_begin{
  return unless $mainEnt->kind->check("file");
  if ($python){
    return is_keyword() && check_list("def");
  }  
}


#test to see if the fortran label on the current statement has already been
#referenced by a control structure and if so we need to terminate
sub keyword_fortran_passed_label{
  if ($fortran){
    my $keyword_fortran_label = pop(@fortran_labels);
    push(@fortran_labels,$keyword_fortran_label);
    my $read;
    for (@found_keyword_fortran_labels) { vec($read,$_,1) = 1 }
    if(vec($read,$keyword_fortran_label,1)){
      return 1;
    }
  }
  return 0;
}
# the fortran and vhdl lexers returns several keywords as 2 or 3 tokens, identify
# takes a stream as the input and returns the appropriate keyword
sub appended_function
{
  my $compareStream = shift;
  return unless $compareStream->token eq "Keyword";
  return unless $fortran || $vhdl || $ada;
  my $returnval = 0;
  $returnval =  $compareStream->text().$compareStream->next->text()if ($fortran && lc($compareStream->text()) eq "do" && $compareStream->next()->text() =~ /\S+/);
  my $tripleString = "";
  my $doubleString = "";
  $doubleString = $compareStream->text().$compareStream->next()->text() if ($compareStream->next());
  $tripleString = $compareStream->text().$compareStream->next()->next()->text() if ($compareStream->next() && $compareStream->next()->next());
  $tripleString =~ s/\s//g; #remove all white space from the string
  foreach my $func(@append_functions) {
    if (lc($func) eq lc($doubleString)){ $returnval = $func ;}
    elsif (lc($func) eq lc($tripleString)){ $returnval = $func ;}
  }
  #printDebug("appended_function - $returnval",3) if $returnval;
  return $returnval;
 }

# Returns true if the current function line is labeled and a control function
sub keyword_fortran_labeled_control_line {
  if ($fortran && keyword_fortran_label()){
    # if the label is part of the control flow, flag it

    my $read;
    for (@fortran_labels) { vec($read,$_,1) = 1; }
    if(vec($read,keyword_fortran_label(),1))
    {
      #we don't want any of these things to display
      my $returnValue = appended_function($stream);
      $returnValue = $returnValue || keyword_block_end();
      $returnValue =  $returnValue || keyword_fortran_label() == $stream->text();
      printDebug("keyword_fortran_labeled_control_line",3) if $returnValue;
      return $returnValue;
    }
  }
}

# Return the fortran label at the start of a line if it is there (columns 1-5)
sub keyword_fortran_label{
  return if !$fortran;
  my $lex1 = $lexer->lexeme($stream->line_begin(), 0);
  while($lex1 && $lex1->text !~ /\n/ && $lex1->token !~ /Label/){$lex1=$lex1->next;}
  return $lex1->token=~/Label/? $lex1->text:0;
}

sub check_list {
  # grep through the supplied list comparing against the current token
  my $result;
  if ($ada || $vhdl || $fortran) {
    $result =  grep {lc($_) eq lc(token_current())} @_;
  } else {
    $result =  grep {$_ eq token_current()} @_;
  }
  return $result;
}

sub keyword_block_begin {
  if ($ada || $vhdl) {
    return check_list("begin");
  }elsif ($fortran){
    return check_list("then");
  }elsif ($python){
    return $stream->token() eq "Indent";
  }else{
    return check_list("{");
  }
}

#This should only be called by Fortran at the begining of a subroutine
sub fortran_start{
  my $count=0;
  while ($stream){
    ++$count if $stream->text =~ /\(/;
    --$count if $stream->text =~ /\)/;
    return  if $fortran && !$count && check_list(")","\n");
    $stream = $stream->next;
  }

}

sub keyword_block_end {
  my $result;
  if ($ada || $vhdl) {
    $result = is_keyword() && check_list("end", "else", "elsif", "exception","endloop","endselect");
    $result = $result || check_list("endif") if $vhdl;
    $result = $result || check_list("or") if $ada_select_block;
  }elsif ($fortran) {
    $result = check_list("elseif","else","end","endif","endselect","elsewhere","endwhere") && is_keyword();
    #the enddo should be ignored if there is a line number being used if that line number was assigned to the do stmt
    my $flabel = keyword_fortran_label;
    $result = $result || (check_list("enddo") && !($flabel && $flabel ~~ @fortran_labels));
  }elsif ($python){
    $result =  $stream->token() eq "Dedent";
  }else{
    $result =  check_list("}");
  }
  printDebug("keyword_block_end",3) if $result;
  return $result;
}

sub keyword_statement_end {
  my $result = 0;
  if ($fortran) {
    $result = $stream->token() =~/EndOfStatement/;
  }elsif ($python){
    $result = $stream->token() =~ /Newline/;
  }else{
    $result = (! $asm_block && check_list(";")) || ($asm_block && check_list("\n"));
  }
  printDebug("keyword_statement_end",3) if $result;
  return $result;
}

sub keyword_condition_begin {
  if ($ada || $vhdl) {
    return check_list("when");
  } else {
    return check_list("(");
  }
}

sub keyword_condition_end {
  if ($ada || $vhdl) {
    return check_list("then", "loop", "is", ";") && !keyword_and_then();
  }
  elsif($fortran){ return check_list(")");}
  elsif($python) { return $stream->text eq ":" && $stream->token eq "Punctuation";}
   else {
    return check_list(")");
  }
}

sub keyword_when_condition_end {
  if ($ada || $vhdl)  {
    return check_list("=>");
  } elsif ($fortran)  {
    return check_list(")");
  } else {
    return 0;
  }
}

sub keyword_if {
  if ($ada || $vhdl || $python) {
    return (check_list("if","elif","elsif") and $stream->token eq "Keyword");
  }
  if ($fortran){
    return (check_list("if", "elseif","where") and $stream->token eq "Keyword");
  }else {
    return check_list("if");
  }
}

sub keyword_if_arithmetic{
  return 0 if !$fortran;
  if (check_list("if","elseif")){
    my $lex = $stream;
    my ($restofline,$arithmeticIf);
    while ($lex && $lex->text() !~ /\n/){
      $restofline .= $lex->text();
      $lex = $lex->next();
    }
    $restofline =~ s/^.+\)//;
    $restofline =~ s/\s+//g;
    $restofline =~ s/,//g;
    return 1 if $restofline =~ /^\d+$/;
  }
}

sub keyword_else {

  my $returnVal;
  if ($fortran){
    $returnVal = check_list("else","elsewhere");
  }else{
    $returnVal= check_list("else");
    }
  printDebug("keyword_else",3) if $returnVal;
  return $returnVal;
}

sub keyword_pre_loop {
  if ($fortran) {
  return check_list("do");}
  return check_list("for", "while","foreach") && !keyword_for_use();
}

sub keyword_post_loop {
  if (!$ada && ! $fortran && !$vhdl) {
    return check_list("do");
  } else {
    return 0;
  }
}

sub keyword_uncond_loop {
  if ($ada || $vhdl) {
    return check_list("loop");
  } else {
    return 0;
  }
}

sub keyword_struct {
  if (!$ada && !$fortran && !$vhdl) {
    return check_list("struct");
  }
}

sub keyword_switch {
  if ($vhdl) {
    return check_list("case");
  } elsif ($ada){
    return check_list("case","select");
  } elsif ($fortran){
    return check_list("selectcase");
  }else {
    return check_list("switch");
  }
}

sub keyword_case {
  my $returnVal;
  if ($ada || $vhdl) {
    if (check_list("when")){
      $returnVal = 1;
      $returnVal = 0 if ! is_keyword() && $vhdl;
      my $tempToken = $stream;
      while ($tempToken && $tempToken->token !~ /EndOfStatement/i && $tempToken->text !~ /when/i){
        $returnVal = 0 if $tempToken->token =~ /Keyword/i && $tempToken->text =~ /exception/i;
        $tempToken = $tempToken->previous();
      }
    }elsif (check_list("or")) {
      $returnVal = 1 if $ada_select_block && is_keyword();
    }
  } else {
    $returnVal = check_list("case");
    if (check_list("default")){
      $returnVal=1;
      my $placeholder = $stream;
      find(\&is_interesting);
      $returnVal = 0 unless $stream && $stream->text eq ":"; 
      $stream = $placeholder;
    }
  }
  printDebug("keyword_case",3) if $returnVal;
  return $returnVal;
}
sub keyword_asm{
  return !$ada && !$vhdl && check_list("__asm","_asm");
}

sub keyword_break {
  if (($ada || $vhdl || $fortran) && $stream->token eq "Keyword") {
    return check_list("exit");
  } else {
    return check_list("break");
  }
}

sub keyword_continue {

  if ($fortran){
    if(check_list("cycle","continue") && $stream->token eq "Keyword"){
      my $space = $stream->previous()->text();
      my $number = $stream->previous()->previous()->text();
      if ($space =~ /\s+/ && $number =~ /\d/){
        #this is a named break, treat as goto label
        return 0;
      }
      return 1;
    }
  }elsif ($ada || $vhdl) {
    return 0;
  } else {
    return check_list("continue");
  }
}

sub keyword_return {
  my $check = check_list("return") if (!$fortran);
  if (($ada || $vhdl) && $check){
    my $tempstream = $stream;
    while ($tempstream && $tempstream->token !~ /EndOfStatement/i &&
    ! ($tempstream->token =~ /Keyword/ && $tempstream->text =~ /begin|then/) &&    ! ($tempstream->token =~ /Punctuation/ && $tempstream->text =~ /;/)){      return 0 if $tempstream->token =~ /keyword/i && $tempstream->text =~ /function/i;
      $tempstream = $tempstream->previous;
    }
  }
  if ($ada && check_list("terminate")){
    $check = 1;
  }
  return $check;
}

sub keyword_try {
  if (!$ada && !$vhdl) {
    return check_list("try");
  }
  return 0;
}

sub keyword_catch {
  if ($python){
    return $stream->token eq "Keyword" && check_list("except");
  }elsif (!$ada && !$vhdl) {
    return check_list("catch", "finally");
  }
  return 0;
}

sub keyword_throw {
  if ($fortran){
    return $stream->token eq "Keyword" && check_list("stop","return");
  }elsif($ada || $vhdl) {
    return 0;
  }else{
    return check_list("throw");
  }
}

sub keyword_for_use {
  if ($ada || $vhdl) {
    my $lexeme = $stream;
    while ($lexeme && $lexeme->text ne ";") {
      return 1 if lc($lexeme->text) eq "use";
      $lexeme = $lexeme->next;
    }
    return 0;
  } else {
    return 0;
  }
}

sub keyword_and_then {
  if ($ada || $vhdl) {
    my $lexeme = $stream;
    $lexeme = $lexeme->previous;
    while ($lexeme && $lexeme->text =~ /^\s+$/) {
      $lexeme = $lexeme->previous;
    }
    return lc($lexeme->text) eq "and";
  } else {
    return 0;
  }
}

sub keyword_assign_to {
  if ($fortran) {
    if (check_list("assign")){
      my $lexeme = $stream;
      $lexeme = $lexeme->next()->next()->next()->next();
      return lc($lexeme->text) eq "to";
    }
  } else {
    return 0;
  }
}

sub keyword_goto {
  return 1 if check_list("goto");
  if($fortran && $stream->token eq "Keyword" && check_list("cycle")){
      my $space = $stream->next()->text();
      my $number = $stream->next()->next()->text();
      if ($space =~ /[ ]+/ && $number =~ /\S/){
        #this is a named break, treat as goto
        return 1;
      }
    }

}

sub keyword_goto_computed{
  return 0 if !$fortran;
  if (check_list("goto")){
    my $lex = $stream->next();
    $lex = $lex->next if $lex->text =~ /to/i;
    my $restofline;
    while ($lex && $lex->text() !~ /\n/){
      $restofline .= $lex->text();
      $lex = $lex->next();
    }
    return 1 if $restofline =~ /^\s*\(.*/;
  }
}

sub keyword_declare {
  return 0 if !$ada && !$vhdl;
  return check_list("declare");
}

sub keyword_label_end {
  if ($ada || $vhdl) {
    return check_list(">>");
  } else {
    return check_list(":");
  }
}

sub keyword_goto_label {
  if ($ada || $vhdl) {
    return check_list("<<");
  } elsif($fortran){
    #get linelabels if in Fortran
    if(keyword_fortran_label())
    {
      my $lexeme = $stream;
      $stream = $stream->next();
            while($stream && $stream->text() =~ /\s/){ $stream = $stream->next();;}
      $stream = $lexeme;
      my $returnval =  $stream->text() == keyword_fortran_label();
      return $returnval;
    }
  }elsif($stream->ent && $stream->ent->kind->check("label")){
    printDebug("Label",3);
    return 1;
  }else {
    # get a copy so the the stream remains unchanged
    my $lexeme = $stream;

    # find the beginning of the line
    while ($lexeme && $lexeme->text() ne "\n") {
      $lexeme = $lexeme->previous();
    }
    #test that we are not continuing in the middle of another statement type
    my $test = $lexeme;
    while ($test && $test->text =~ \/s*/){
      $test = $test->previous();
    }
    return if $test->text !~ /;}{/;

    $lexeme = $lexeme->next();

    # search forward until we know if there's a label
    # Also make sure we are not on the same line as a Case
    while ($lexeme->text() !~ /^[:;?\n]$/ && $lexeme->text() !~ /case/i && $lexeme->text() !~ /default/i) {
      $lexeme = $lexeme->next();
    }
    return $lexeme->text() eq ":";
  }
}

sub keyword_loop_label {
  if ($ada || $vhdl) {
    my $lexeme = $stream;
    my $text = find(\&is_interesting);
    find(\&is_interesting);
    my $result = $text eq ":" && check_list("for", "while", "loop");
    $stream = $lexeme;
    return $result;
  } else {
    return 0;
  }
}

sub keyword_generic_label {
  if ($ada || $vhdl) {
    my $lexeme = $stream;
    my $text = find(\&is_interesting);
    find(\&is_interesting);
    my $result = $text eq ":" && check_list("declare");
    $stream = $lexeme;
    return $result;
  }
  elsif ($fortran && check_list("entry")) #the entry function acts just like a goto label
    {
      find(\&is_interesting);
      return 1;
    }
  else {
    return 0;
  }
}


sub is_whitespace {
  # be sure that this tokenwh contains only is_whitespace (nothing else)
  return token_current() =~ /^\s+$/;
}

sub is_empty {
  #be sure that the token contains something
  return (length(token_current()) == 0)
}

sub is_comment {
  my $isComment = 0;
  if ($ada || $vhdl) {
    $isComment = token_current() =~ /^-{2,}/;
  }
  elsif ($fortran) {
    $isComment = $stream->token =~ /Comment/;
  }
  else{
    $isComment = token_current() =~ /^\/[\/\*]/;
  }
  return $isComment;

}

# A is_preprocessor token is the directive and anything that follows
# up to the next newline character.
sub is_preprocessor {
  my $lexeme = $lexer->lexeme($stream->line_begin(), 0);
  while ($lexeme->text() =~ /^\s+$/) {
    $lexeme = $lexeme->next();
  }
  my $result = $lexeme->token eq "Preprocessor";
  if ($result) {
    if (token_current() eq "asm") {
      $asm_block = 1;
    } elsif (token_current() eq "endasm") {
      $asm_block = 0;
    }
  }

  return $result;
}

sub is_inactive {
  return $stream->inactive();
}

sub is_interesting {
  my $interestingReturn = (!is_whitespace() && !is_comment() && !is_preprocessor() && !is_inactive() && !keyword_declare());
  if ($fortran){ $interestingReturn = $interestingReturn && !is_empty() && $stream->token !~ /Continuation/i}
  return $interestingReturn;
}

sub text {
  return (!is_comment() && !is_preprocessor() && !is_inactive());
}

sub is_structure {
  if ($asm_block){
    return check_list("}");
  }
  my $result=0;
  if (keyword_block_begin()){
    if ($fortran){
      $result = 0;
    }elsif ($ada || $vhdl || $java){
      $result = 1;
    }else{

      my $tempstream = $stream->previous;
      while($tempstream && $tempstream->token =~ /whitespace|newline/i){
        $tempstream = $tempstream->previous;
      }
      $result = ($tempstream->text =~ /struct|union/i);
    }
  }

  $result = ($result || keyword_block_end() || keyword_if() || keyword_block_begin() ||
          keyword_pre_loop() || keyword_post_loop() || keyword_uncond_loop() ||
          keyword_switch() || keyword_case() || keyword_try() ||
          keyword_catch() || keyword_break() || keyword_continue() ||
          keyword_return() || keyword_goto() || keyword_generic_label() ||
          keyword_loop_label() || keyword_goto_label() || keyword_throw() || keyword_function_begin());

  printDebug("is_structure true",3) if $result;
  return $result;
}

sub is_overflowed {
  printDebug("is_overflowed true",3) if ($end_ref && ($stream->line_end > $end_ref->line));
  return ($end_ref && ($stream->line_end > $end_ref->line));
}

sub is_keyword{
  return $stream->token eq "Keyword";
}

sub is_identifier{
  return $stream->token eq "Identifier";
}

sub printDebug {
  my $debugmessage = shift;
  my $level = shift;
  return if $debug < $level;
  my $text = $stream->text if $stream;
  $text =~ s/\s+//g;
  $debugmessage =~ s/\n/\n                                 /g;
  my $output = sprintf("%-8s %-8s %-13s", "(".$stream->line_begin.",".$stream->column_begin.")",$text,"<".$stream->token.">", ) if $stream;
  $output .= " - $debugmessage\n";
  print $output;
  open FILE,">>$debugFile" || die ("can't open debug file");
  print FILE $output;
  close FILE;
  return;
}

sub getTime{
  my @months = qw(Jan Feb Mar Apr May Jun Jul Aug Sep Oct Nov Dec);
  my @weekDays = qw(Sun Mon Tue Wed Thu Fri Sat Sun);
  my ($second, $minute, $hour, $dayOfMonth, $month, $yearOffset, $dayOfWeek, $dayOfYear, $daylightSavings) = localtime();
  my $year = 1900 + $yearOffset;
  my $theTime = "$hour:$minute:$second" ;
  return $theTime;
}



package FlowNodeLib;
#**********Accessor Node Subs***********

sub get {
  my ($self,$property) = @_;
  my $value = $self->{$property};
  $value =~ s/^\s+|\s+$//g;
  return $value;
}

sub propertyList{
  my $self = shift;
  my @list = sort keys %{$self};
  my @clean;
  while(@list){
    my $element = shift(@list);
    push @clean, $element if $element !~ /^int_/ && exists $self->{$element};
  }
  return @clean;
}

sub edgeList{
  my $self = shift;
  my $edges = $self->{"int_edges"};

  bless($_,"FlowEdgeLib") for @$edges;
  return @$edges;
}

#**********************************

sub new {
  my ($class, $id,$mode) = @_;
  my $self = {};
  $self->{"int_mode"} = $mode;
  $self->{"int_previous_mode"} = "unset"; # set internally
  $self->{"id"} = $id;
  bless($self, $class);
  return $self;
}

sub mode {
  my $self = shift;
  if (@_) {
    $self->{"int_previous_mode"} = $self->{"int_mode"}; # save for later
    $self->{"int_mode"} = shift;
  }
  return $self->{"int_mode"};
}

sub previous_mode {
  my $self = shift;
  return $self->{"int_previous_mode"};
}

sub set {
  my ($self,$prop,$val) = @_;
  $self->{$prop} = $val;
}

sub label {
  my $self = shift;
  if (@_) {
    $self->{"label"} = shift;
  }
  return $self->{"label"};
}

sub addEdge{
  my $self = shift;
  my $edge = shift;
  push @{$self->{"int_edges"}},$edge;
  $self->{"num_edges_out"}++;
  my $target = $edge->get("int_target");
  $target->set("num_edges_in",$target->set("num_edges_in")+1) if $target;
}

package FlowEdgeLib;

sub new{
  my ($class, $sourceNode, $targetNode) = @_;
  my $self = {};
  $self->{"int_source"} = $sourceNode;
  $self->{"int_target"} = $targetNode;
  bless($self, $class);
  $self->{"target"}=$targetNode->get("id");
  $self->{"source"}=$sourceNode->get("id");
  $sourceNode->addEdge($self);
  return $self;
}

sub set {
  my ($self,$prop,$val) = @_;
  $self->{$prop} = $val;
}

#*******Edge Accessors*****************

sub get {
  my ($self,$property) = @_;
  my $value = $self->{$property};

  if ($property eq "int_source" || $property eq "int_target"){
    bless($value,"FlowNodeLib");
  }else{
    $value =~ s/^\s+|\s+$//g;
  }
  return $value;
}

sub propertyList{
  my $self = shift;
  my @list = sort keys %{$self};
  my @clean;
  while(@list){
    my $element = shift(@list);
    push @clean, $element if $element !~ /^int_/ && exists $self->{$element};
  }
  return @clean;
}


1;
