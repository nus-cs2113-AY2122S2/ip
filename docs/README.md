# User Guide

## Overview
Duke is a task management app.

## Features 

### Add task
Tasks can be ToDos, Deadlines or Events.

### Delete task
Tasks can be deleted.

### Find task
Tasks can be searched for.

### Mark task
Tasks can be marked as done.

### Unmark task
Tasks can be unmarked.

### List tasks
Tasks can be listed in a readable format.

### Saves tasks
Tasks are saved when exiting the program and will be loaded
when the program is next launched.

### Supports YYYY-MM-DD format
Timings entered in YYYY-MM-DD format will be formatted to be
more readable.

Example:

2022-03-15 becomes Mar 03 2022

## Usage

### `todo <DESCRIPTION>`

Adds a ToDo task with `<DESCRIPTION>` as its description.

Example of usage: 

`todo read book`

Expected outcome:

Adds a ToDo task with `read book` as its description.

### `deadline <DESCRIPTION> /by <TIMING>`

Adds a Deadline task with `<DESCRIPTION>` as its description
and `<TIMING>` as its timing.

Example of usage:

`deadline return book /by June 8th`

Expected outcome:

Adds a Deadline task with `return book` as its description and 
`June 8th` as its timing.

### `event <DESCRIPTION> /at <TIMING>`

Adds an Event task with `<DESCRIPTION` as its description and 
`<TIMING>` as its timing.

Example of usage:

`event company meeting /at 8pm`

Expected outcome:

Adds an Event task with `company meeting` as its description and 
`8pm` as its timing.

### YYYY-MM-DD format for timings

Deadline and Event tasks will format YYYY-MM-DD timings to be more
readable.

Example of usage:

`deadline return book /by 2022-08-13`

`event company meeting /at 2022-07-01`

Expected outcome:


Adds a Deadline task with `return book` as its description and
`Aug 13 2022` as its timing.

Adds an Event task with `company meeting` as its description and 
`2022-07-01` as its timing.

### `list`

Lists all currently stored tasks.

The number beside each task represents their position in the list,
and may be used with other commands.

Example of usage:

`todo read book`

`deadline return book /by 2022-03-15`

`event meeting /at office`

`list`

Expected outcome:

```
_____________________________________
Here are the tasks in your list:
1.[T][ ] read book
2.[D][ ] return book (by: Mar 15 2022)
3.[E][ ] meeting (at: office)
_____________________________________
```

### `mark <TASK_NUMBER>`

Marks the task corresponding to <TASK_NUMBER> as done.
`<TASK_NUMBER>` can be determined by the `list` command.

Example of usage:

`mark 1`

Expected outcome:

Marks the task as done.

### `unmark <TASK_NUMBER>`

Marks the task corresponding to <TASK_NUMBER> as incomplete.
<TASK_NUMBER> can be determined by the `list` command.

Example of usage:

`unmark 1`

Expected outcome:

Marks the task as incomplete.

### `bye`

Saves tasks to a save file on disk and exits the program.
Tasks will not be saved unless this command is run.

Example of usage:

`bye`

Expected outcome:

Prints a goodbye message and exits. Task data is saved to the save file.