# Duke User Guide

## Features
- Adding tasks
    - Adding a todo: `todo`
    - Adding an event: `event`
    - Adding a deadline: `deadline`
- Listing all tasks: `list`
- Finding tasks based on keyword(s): `find`
- Mark a task: `mark`
- Unmark a task: `unmark`
- Deleting a task: `delete`
- Saving tasks to a local data file

## Quick Start Example
- Add an event task: `event meeting /at 3pm`
- List all tasks: `list`
- Mark the first task: `mark 1`
- unmark the first task: `unmark 1`
- Delete the first task: `delete 1`
- Exit the program: `bye`

## Features

- Words in square brackets are parameters to be specified by user: `[example]`

### `todo` - Add a todo task

Adds a todo task to Duke.

Format: `todo [description]`

Example: `todo read a book`

```
Got it. I've added this task:
1.[T][ ] read a book
Now you have 1 task(s) in the list.
```

### `event` - Add an event task

Adds an event task to Duke.

Format: `event [description] /at [time]`

Example: `event meetup /at 3pm`

```
Got it. I've added this task:
2.[E][ ] meetup (at: 3pm)
Now you have 2 task(s) in the list.
```

### `deadline` - Add a deadline task

Adds a deadline task to Duke.

Format: `deadline [description] /by [time]`

Example: `deadline project /by Thursday 5pm`

```
Got it. I've added this task:
3.[D][ ] project (by: Thursday 5pm)
Now you have 3 task(s) in the list.
```

### `list` - List all tasks

List all tasks that Duke knows.

Format: `list`

```
Here are the tasks in your list:
1.[T][ ] read a book
2.[E][ ] meetup (at: 3pm)
3.[D][ ] project (by: Thursday 5pm)
```
### `find` - Find tasks based on matching keyword(s)

Lists all tasks that Duke has with the matching keyword(s)

Format: `find [keyword(s)]`

Example: `find project`

```
Here are the matching tasks in your list:
1.[D][ ] project (by: Thursday 5pm)
```

### `mark` - Mark the task as done

Sets the task with the given index to be marked as done.

Format: `mark [index]`

Example: `mark 1`

```
Nice! I've marked this task as done:
1.[T][X] read a book
```

### `unmark` - Mark the task as not done

Sets the task with the given index to be marked as not done.

Format: `unmark [index]`

Example: `unmark 1`

```
Nice! I've marked this task as not done:
1.[T][ ] read a book
```

### `delete` - Delete a task

Deletes the task with the given index.

Format: `delete [index]`

Example: `delete 1`

```
Noted. I've removed this task:
[T][ ] read a book
Now you have 2 tasks in the list.
```

### `bye` - Exits the program

Duke will terminate.

Format: `bye`

```
Bye. Hope to see you again soon!
```