# User Guide

## Features

**Notes about the command format**

- Words in `UPPER_CASE` are the parameters to be supplied by the user. e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a paramter which can be used as `todo return book`.

### Adding Tasks

Adds a task to Duke to manage

1. To-do item

   Format: `todo DESCRIPTION`

   - Adds a todo with a `DESCRIPTION`

2. Event item

   Format: `event DESCRIPTION /at DATE`

   - Adds an event with a `DESCRIPTION` and a `DATE` representing when the event is happening

3. Deadline item

   Format: `deadline DESCRIPTION /by DATETIME`

   - Adds a deadline task with a `DESCRIPTION` and a deadline `DATETIME`
   - `DATETIME` must be in the format `yyyy-MM-dd HHmm`
   - e.g. `deadline assignment /by 2022-02-14 1800`

### Delete task

Deletes a task from Duke.

Format: `delete INDEX`

- Deletes the task at the specified `INDEX`
- The index refers to the index number shown in the displayed person list.

### List tasks

Lists all tasks in Duke.

Format: `list`

### Find task

Finds a task in Duke.

Format: `find SEARCH_TERM`

- Finds a task that contains a specific `SEARCH_TERM`

### Mark and unmark task

Marks or unmarks a task as done.

Format: `mark INDEX`

- Marks a task as done at the specified `INDEX`
- The index refers to the index number shown in the displayed person list.

Format: `unmark INDEX`

- Marks a task as **not** done at the specified `INDEX`
- The index refers to the index number shown in the displayed person list.

### Exit

Exit from Duke.

Format: `bye`

## Usage

### Adding tasks

Add task to Duke

Example of usage:

`deadline return book /by 2022-12-02 1800`

Expected outcome:

```
____________________________________________________________
Got it. I've added this task:
[D][ ] return book (by: 02 Dec 2022 06:00 PM)
Now you have 1 tasks in the list
____________________________________________________________
```

### Deleting tasks

Remove task from Duke

Example of usage:

`delete 1`

Expected outcome:

```
____________________________________________________________
Noted. I've removed this task:
[D][ ] return book (by: 12 Feb 2022 06:00 PM)
Now you have 0 tasks in the list
____________________________________________________________
```

### Marking tasks

Marking task status

Example of usage:

`mark 1`

Expected outcome:

```
____________________________________________________________
Nice! I've marked this task as done:
[D][X] return book (by: 12 Feb 2022 06:00 PM)
____________________________________________________________
```

### Listing tasks

List out tasks in the task list

Example of usage:

`list`

Expected outcome:

```
____________________________________________________________
Here are the tasks in your list:
1. [T][ ] borrow book
2. [D][ ] return book (by: 02 Dec 2022 06:00 PM)
3. [E][ ] project meeting (at: Monday 2-4pm)
____________________________________________________________
```

### Finding tasks

Find a task

Example of usage:

`find book`

Expected outcome:

```
____________________________________________________________
Here are the matching tasks in your list:
1. [T][ ] borrow book
2. [D][ ] return book (by: 02 Dec 2022 06:00 PM)
____________________________________________________________
```
