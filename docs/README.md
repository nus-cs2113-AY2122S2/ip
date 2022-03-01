# User Guide

## Features 

### Adding tasks

Able to add to or remove from a list of tasks.

### Marking tasks as done/not done

Able to mark a task as done or not done

### Listing tasks

Able to display a list of current Tasks

### Finding tasks

Able to find tasks with description matching a specified keyword

### Saving/loading tasks

Able to save and load tasks such that memory of list of tasks is maintained between different application session

## Usage

### `todo` - Adds a todo Task to the list

Creates new todo Task to be added and displays the Task added

Example of usage: 

`todo buy book`

Expected outcome:

A confirmation that the Task has been added and a description of it followed by total number of tasks

```
================================================
Got it. I've added this task:
	[T][ ] buy book
Now you have 1 tasks in the list.
================================================
```

### `deadline` - Adds a deadline Task to the list

Creates new deadline Task to be added and displays the Task added

Example of usage:

`deadline read book /by Tuesday`

Expected outcome:

A confirmation that the Task has been added and a description of it followed by total number of tasks

```
================================================
Got it. I've added this task:
	[D][ ] read book (by: Tuesday)
Now you have 2 tasks in the list.
================================================
```

### `event` - Adds an event Task to the list

Creates new event Task to be added and displays the Task added

Example of usage:

`event school open house /at 22 May`

Expected outcome:

A confirmation that the Task has been added and a description of it followed by total number of tasks

```
================================================
Got it. I've added this task:
	[E][ ] school open house (at: 22 May)
Now you have 3 tasks in the list.
================================================
```

### `list` - Lists all the tasks that are in the list

List out all the details of the tasks that have been added

Example of usage:

`list`

Expected outcome:

A list of the tasks

```
================================================
Here are the tasks in your list:
1.	[T][ ] buy book
2.	[D][ ] read book (by: Tuesday)
3.	[E][ ] school open hosue (at: 22 May)
================================================

```

### `mark` - mark a specified task as done

Marks a task as done according to the specified index in the list

Example of usage:

`mark 1`

Expected outcome:

A confirmation that the Task has been marked and a description of it

```
================================================
Nice! I've marked this task as done:
	[T][X] buy book
================================================

```

### `unmark` - mark a specified task as done

Marks a task as not done according to the specified index in the list

Example of usage:

`unmark 1`

Expected outcome:

A confirmation that the Task has been marked and a description of it

```
================================================
Nice! I've marked this task as not done yet:
	[T][ ] buy book
================================================

```

### `delete` - delete the specified task

Delete a Task according to the specified index in the list

Example of usage:

`Delete 3`

Expected outcome:

A confirmation that the Task has been deleted and a description of it followed by total number of tasks

```
================================================
Noted. I've removed this task:
	[E][ ] school open house (at: 22 May)
Now you have 2 tasks in the list.
================================================

```

### `find` - find tasks based on keyword

Find the tasks with description which matches the specified keyword

Example of usage:

`find book`

Expected outcome:

A list of tasks matching with the keyword

```
================================================
Here are the matching tasks in your list:
1.	[T][ ] buy book
2.	[D][ ] read book (by: Tuesday)
================================================

```