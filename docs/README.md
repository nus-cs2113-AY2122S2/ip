# User Guide

## Features 

### Add Task

Adds a task to the current list for KaiKai to handle.

### Delete Task

Delete a task from the current list.

### List Tasks

Lists all tasks.

### Mark Task

Mark a task as done.

### Unmark Task

Mark a task as undone.

### Find Task

Find all tasks matching the search term.

### Exit

Closes the app.

## Usage

### `add` - Adding a task

Add a task to the task list

Example of usage: 

`add`

Expected outcome:

```
Okie, what type of task is this?
1.Todo
2.Deadline
3.Event
```
Follow the prompts accordingly.

### `delete` - Deleting a task

Delete a task from the task list

Example of usage: 

`delete`

Expected outcome:

```
1. [T][X] read book
2. [E][ ] mug (start: 01/01/2001 01:01, end: 01/01/2001 01:01)
3. [D][ ] mug more (by: 01/01/2001 01:01)
4. [E][ ] submit ip (start: 11/12/1234 11:11, end: 11/02/1234 00:00)
______________________________________
Which task would you like to remove?
```
Shows the current list of tasks and prompts for which task to delete (by index).

### `list` - Listing all tasks

Delete a task from the task list

Example of usage: 

`list`

Expected outcome (example list):

```
1. [T][X] read book
2. [E][ ] mug (start: 01/01/2001 01:01, end: 01/01/2001 01:01)
3. [D][ ] mug more (by: 01/01/2001 01:01)
4. [E][ ] submit ip (start: 11/12/1234 11:11, end: 11/02/1234 00:00)
```

### `mark` - Mark a task

Mark a task as done.

Example of usage: 

`mark`

Expected outcome (example list):

```
1. [T][X] read book
2. [E][ ] mug (start: 01/01/2001 01:01, end: 01/01/2001 01:01)
3. [D][ ] mug more (by: 01/01/2001 01:01)
4. [E][ ] submit ip (start: 11/12/1234 11:11, end: 11/02/1234 00:00)
______________________________________
Which task would you like to mark as completed?
```
Shows the current list of tasks and prompts for which task to mark (by index).

### `unmark` - Unmark a task

Mark a task as undone.

Example of usage: 

`unmark`

Expected outcome (example list):

```
1. [T][X] read book
2. [E][ ] mug (start: 01/01/2001 01:01, end: 01/01/2001 01:01)
3. [D][ ] mug more (by: 01/01/2001 01:01)
4. [E][ ] submit ip (start: 11/12/1234 11:11, end: 11/02/1234 00:00)
______________________________________
Which task would you like to mark as incomplete?
```
Shows the current list of tasks and prompts for which task to unmark (by index).

### `find` - Find a task

Displays matching tasks.

Example of usage: 

`find`

Expected outcome:

```
What would you like to find?
mug
1. [E][ ] mug (start: 01/01/2001 01:01, end: 01/01/2001 01:01)
2. [D][ ] mug more (by: 01/01/2001 01:01)
______________________________________
```
Prompts for search term and displays matching results.

### `bye` - Exit

Closes and app

Example of usage: 

`bye`

Expected outcome:

```
Bye! Hope to see you again!
```
