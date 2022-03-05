# User Guide

Yae is a desktop application for keeping track of tasks, taking input through a Command Line Interface (CLI).

## Starting Up
1. Ensure Java 11 is installed.
2. Open the command window at where the jar file is located.
3. Enter `java -jar Yae.jar`.

## Features

### Viewing Help
Displays all possible commands and their input format. </br>
Format: `help`

### Add a Task
Adds one of the following three types of tasks: </br>
- todo </br>
  Format: `todo <DESCRIPTION>` </br>
  Example: `todo Message mom` </br> </br>
- deadline </br>
  Format: `deadline <DESCRIPTION> /by <date>` </br>
  Example: `deadline hand in assignment /by 5pm` </br> </br>
- event </br>
  Format: `event <DESCRIPTION> /at <date>` </br>
  Example: `event birthday part /at Saturday 3pm`

### Remove a Task
Removes task with specified task number. </br>
Format: `delete <TASK_NUMBER>` </br>
Example: `delete 2` deletes the second task on the list.

### List all tasks
Shows the list of tasks added. </br>
Format: `list`

### Mark a Task as Completed/Uncompleted
- Marks task with specified task number completed. </br>
  Format: `mark <TASK_NUMBER>` </br>
  Example: `mark 2` marks the second task on the list completed. </br> </br>
- Marks task with specified task number uncompleted. </br>
  Format: `unmark <TASK_NUMBER>` </br>
  Example: `unmark 2` marks the second task on the list uncompleted.

### Find Tasks by Keyword
Shows all tasks that contain the given keyword(s). </br>
Format: `find <KEYWORDS>` </br>
Example: `find party` will show all tasks with 'party' in it.

### Saving and Exiting
Saves the list at data/Yae.txt and exits the program. </br>
Format: `bye`
