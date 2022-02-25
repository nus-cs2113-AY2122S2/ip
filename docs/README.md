# User Guide for Duke
**Duke** is a desktop app for managing tasks via a **Command Line Interface**.

## Features 

### Feature

1. View all tasks in task list.
2. Add a todo/deadline/event.
3. Delete a task from task list.
4. Search for a task by keyword in task list.
5. Mark a task as done or unmark it as undone.
6. Exit the program.


## Usage

### `list` - Listing tasks

List all the tasks in task list.

#### Format:

`list`

#### Example of usage: 

`list`

#### Expected outcome: 

```
____________________________________________________________
Here are the tasks in your list.
1.[T][ ]attend a meeting at 2am
____________________________________________________________
```



### `todo` - Adding a todo

Adds a todo to the task list.

#### Format:
`todo DESCRIPTION`

#### Example of usage:

`todo make a breakfast`

#### Expected outcome:

```
____________________________________________________________
Got it. I've added this task:
  [T][ ]make a breakfast
Now you have 1 tasks in the list.
____________________________________________________________
```

### `event` - Adding an event

Adds an event to the task list.

#### Format:
`event DESCRIPTION /PREP TIME`

#### Example of usage:

`event attend a meeting /from 10am to 12pm, Thursday`

#### Expected outcome:

```
____________________________________________________________
Got it. I've added this task:
  [E][ ]attend a meeting (from: 10am to 12pm, Thursday)
Now you have 3 tasks in the list.
____________________________________________________________
```

### `deadline` - Adding a deadline

Adds a deadline to the task list.

#### Format:
`deadline DESCRIPTION /PREP TIME`

#### Example of usage:

`deadline make a breakfast /by 10am`

#### Expected outcome:

```
____________________________________________________________
Got it. I've added this task:
  [D][ ]make a breakfast (by: 10am)
Now you have 4 tasks in the list.
____________________________________________________________
```
### `mark` - Marking a task
Marks a task as done.

#### Format:
`mark INDEX`

#### Example of usage:

`mark 3`

#### Expected outcome:

```
____________________________________________________________
Nice! I've marked this task as done: 
[X]attend a meeting from 10am to 12pm, Thursday
____________________________________________________________
```

### `unmark` - Unmarking a task
Unmarks a task as undone.

#### Format:
`unmark INDEX`

#### Example of usage:

`unmark 3`

#### Expected outcome:

```
____________________________________________________________
OK, I've marked this task as not done yet: 
[ ]attend a meeting from 10am to 12pm, Thursday
____________________________________________________________
```

### `delete` - Deleting a task
Delete a task from task list.
#### Format:
`delete INDEX`

#### Example of usage:

`delete 3`

#### Expected outcome:
```
____________________________________________________________
Noted. I've removed this task: 
[E][ ]attend a meeting from 10am to 12pm, Thursday
Now you have 3 tasks in the list.
____________________________________________________________
```
### `find` - Searching for a task
Searches for a task by keyword.

#### Format:
`find KEYWORD`

#### Example of usage:

`find breakfast`

#### Expected outcome:
```
____________________________________________________________
Here are the matching tasks in your list: 
1.[T][ ]make a breakfast
1.[E][ ]make a breakfast at 2am
1.[D][ ]make a breakfast by 10am
____________________________________________________________
```

### `exit` - Exiting the program
Terminates the program and saves all the data.

#### Format:
`exit`

#### Example of usage:

`exit`

#### Expected outcome:
```
>>> 
Goodbye! Can't wait to see you soon. :D
____________________________________________________________
Data is saved successfully.
```
