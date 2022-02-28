# User Guide

## Features 

### Adding Tasks

You are able to add tasks of 3 different types (Todo, Deadline and Event).
- Todo: Basic task (No deadlines or timings)
- Deadline: Includes a deadline for the task for you to keep track
- Event: Include a start date and time, and an end date and time for the event

### Marking / Unmarking a Task

You are able to mark or unmark any task for your own needs (when you complete a task, e.g.).

### Removing Tasks
You are able to remove tasks that you do not need as well.

### Saving and Loading Tasks
All tasks and any modifications to them will be automatically saved and loaded when you start the program.
### Finding and Listing Tasks
You are able to find tasks based on a matching keyword or list out all your tasks you currently have.
## Usage

### Adding a Task
### `todo` - Add a new Todo task


Example of usage: 

`todo TASK_NAME`

Expected outcome: 


```
added: [T][] TASK_NAME
Total number of tasks now: 1
```

### `deadline` - Add a new Deadline task

The deadline should have the format of YYYY-MM-DD for the date and HH:mm for the time

Example of usage:

`deadline TASK_NAME /by YYYY-MM-DD HH:mm`

Expected outcome:

The output will have the date and time in MMM DD YYYY HH:mm format (Feb 28 2022 16:30, e.g.).
```
added: [D][] TASK_NAME (by: MMM DD YYYY HH:mm)
Total number of tasks now: 2
```

### `event` - Add a new Event task

The Event task takes in two dates and times, one for the start and one for the end.
Format for both dates is YYYY-MM-DD and format for both times is HH:mm

Example of usage:

`event TASK_NAME /at YYYY-MM-DD HH:mm to YYYY-MM-DD HH:mm`

Expected outcome:

The output will have the dates and times in MMM DD YYYY HH:mm format (Feb 28 2022 16:30, e.g.).
```
added: [E][] TASK_NAME (at: MMM DD YYYY HH:mm to MMM DD YYYY HH:mm)
Total number of tasks now: 3
```
### Marking and Unmarking a Task
### `mark` - Mark a Task

The index of the task can be found by doing a `list` command shown below.
The index has to be an integer.

Example of usage:

`mark INDEX`

Expected outcome:

A task would be marked regardless of its status before the command.
```
Nice! I've marked this task as done:
 [X] TASK_NAME
```

### `unmark` - Unmark a Task

The index of the task can be found by doing a `list` command shown below.
The index has to be an integer.

Example of usage:

`unmark INDEX`

Expected outcome:

A task would be unmarked regardless of its status before the command.
```
OK, I've marked this task as not done yet:
 [ ] TASK_NAME
```
### Deleting a Task
### `delete` - Delete a Task

The index of the task can be found by doing a `list` command shown below.
The index has to be an integer.

Example of usage:

`delete INDEX`

Expected outcome:

```
 I have removed this task:
 [D][ ] TASK_NAME (by: MMM DD YYYY HH:mm)
 Total number of tasks now: 2
```
### Finding and Listing Tasks
### `find` - Find tasks based on matching keywords

A task will only be matched if the keywords are fully in the task name.

Example of usage:

`find KEYWORD`

Expected outcome:

```
 Here are the matching tasks:
 1 [T][ ] MATCHED_TASK_NAME
 4 [D][ ] MATCHED_TASK_NAME (by MMM DD YYYY HH:mm)
```
If there are no matching tasks, the expected outcome would be:
```
There are no matching tasks.
```
### `list` - List all Tasks


Example of usage:

`list`

Expected outcome:

```
 Here are the matching tasks:
 1 [T][ ] TASK_NAME
 2 [D][X] TASK_NAME (by MMM DD YYYY HH:mm)
 3 [E][ ] TASK_NAME (at MMM DD YYYY HH:mm to MMM DD YYYY HH:mm)
```

