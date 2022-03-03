# User Guide

## Features 

### Add a todo task: `todo`

Add a todo task to the task list.
Format:
`todo DESCRIPTION` 

Example: 
`todo buy Bread`

### Add a deadline task: `deadline`

Add a deadline task to the task list.
Format:
`deadline DESCRIPTION /by DEADLINE`

Example:
`deadline return book /by Monday`

### Add an event task: `event`

Add an event task to the task list.
Format:
`event DESCRIPTION /at EVENT TIME`

Example:
`event project meeting /at Tuesday 3pm`

### List all the tasks: `list`

Print all the tasks in the task list.
Format: 
`list`

### Find all the task containing the keyword: `find`

Find all the task with the given keyword.
Format: `
find KEYWORD`

Example:
`find book`
-> Returns all the task containing book.

### Delete the task: `delete`

Delete the specified task with the given index
Format: 
`delete INDEX`

Example:
`list`
`delete 2`
-> Delete the second task from the task list.

### Exit the program: `bye`

Exits the application
Format: 
`bye`

### Saving the data

All of added task is saved automatically when the user
exit the application by typing `bye`.

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
