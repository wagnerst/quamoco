#!/bin/sh

echo '<p font-size="2"><b>Used Quality Model: </b>' >> $2
echo $1 >> $2
echo "<br>" >> $2
svn info $1 | grep Revision >> $2
echo "<br>" >> $2
svn info $1 | grep "Last Changed Author" >> $2
echo "<br>" >> $2
svn info $1 | grep "Last Changed Date" >> $2
echo "</p>"
