# User Guide

## Features 

### Feature - Add Todo task

Adds a Todo task to the task list.

Format: `todo [DESCRIPTION]`

Example of usage: `todo Study for quiz`
### Feature - Add Deadline task

Adds a Deadline task with a required parameter `DEADLINE` to the task list.

Format: `deadline [DESCRIPTION] /by [DEADLINE]`

Example of usage: `deadline buy groceries /by tomorrow`

### Feature - Add Event task

Adds an Event task with a required parameter `TIME_PERIOD` to the task list.

Format: `event [DESCRIPTION] /at [TIME_PERIOD]`

Example of usage: `event CS2113 Lecture /at Fri 4-6pm`

### Feature - List tasks

Lists all current tasks in the task list.

Format: `list`

### Feature - Mark task as done

Marks a specific task in the task list as done. `TASK_NUMBER` is a mandatory parameter.

Format: `mark [TASK_NUMBER]`

Example of usage: `mark 3`

### Feature - Mark task as undone

Marks a specific task in the task list as undone. `TASK_NUMBER` is a mandatory parameter.

Format: `unmark [TASK_NUMBER]`

Example of usage: `unmark 3`

### Feature - Delete a task

Deletes a specific task specified by the user. `TASK_NUMBER` is a mandatory parameter.

Format: `delete [TASK_NUMBER]`

Example of usage: `delete 4`

### Feature - Find tasks

Find tasks with specified keyword(s). `CONTENT` is a mandatory parameter and can be made up of multiple words.

Format: `find [CONTENT]`

Example of usage: `find CS2113 Tutorial`

### Feature - Exiting the program

Exits the program by passing the command `bye`.

Format: `bye`


## Usage

### `Keyword` - Describe action

Describe the action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```
