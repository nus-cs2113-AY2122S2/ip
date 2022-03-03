# User Guide for Duke
Duke is a simple app that allows one to keep track of tasks at hand.

## Features

### Add Task
Tasks that can be added include ToDo, Deadline and Event.

Upon initialising a new task, the new task will automatically be added to the user's list, which collates all the user's tasks.

### Delete Task
Any task in the user's list can be deleted.

### Mark Task as Done
A task can be marked as done upon completion.

### Mark Task as Not Done
If a task has been marked as done, but the user realises that it has not been completed fully yet, the task can be unmarked.

### Find Task
The user can search for a Task based on a given keyword. The output will list all tasks that contain the keyword.

### List Tasks
The user can list all the tasks stored in his/her personal list of tasks.

### Persistent Storage
Upon exiting Duke, all tasks will be saved on your local device. When the user runs Duke again, the list of tasks will be initialised according to the list of tasks that was saved.

## Usage

### `todo` - Add a new ToDo Task

A new ToDo task will be initialised based on the description supplied by the user. The task will then be added to the user's list of tasks.

Example of usage: 

`todo Get A for CS2113`

Expected outcome:

A new Todo Task will be created, with its description being `Get A for CS2113`.

```
Got it. I've added this task:
[T][ ] Get A for CS2113
Now you have 1 task in the list
```

### `deadline` - Add a new Deadline Task

A new Deadline task will be initialised based on the description supplied by the user. The user will also need to indicate the deadline of the task. The task will then be added to the user's list of tasks.

Example of usage:

`deadline Complete CS2113 User Guide /by Friday 2359`

Expected outcome:

A new Deadline Task will be created, with its description being `Complete CS2113 User Guide` and deadline being `Friday 2359`.

```
Got it. I've added this task:
[D][ ] Complete CS2113 User Guide (by: Friday 2359)
Now you have 2 tasks in the list.
```

### `event` - Add a new Event Task

A new Event task will be initialised based on the description supplied by the user. The user will also need to indicate the time of the event. The task will then be added to the user's list of tasks.

Example of usage:

`event CS2113 meeting /at Monday 1400`

Expected outcome:

A new Event Task will be created, with its description being `CS2113 meeting` and time being `Monday 1400`.

```
Got it. I've added this task:
[E][ ] CS2113 meeting (at: Monday 1400)
Now you have 3 tasks in the list.
```

### `mark` - Mark Task as Done

The task indicated by the user will be marked as done.

Example of usage:

`mark 2`

Expected outcome:

The second task on the user's list will be marked as done.

```
Nice! I've marked this task as done:
[D][X] Complete CS2113 User Guide (by: Friday 2359)
```

### `unmark` - Mark Task as Not Done

If the task indicated by the user has been marked as done, it will be unmarked instead.

Example of usage:

`unmark 2`

Expected outcome:

The second task on the user's list will not be marked as done.

```
OK, I've marked this task as not done yet:
[D][ ] Complete CS2113 User Guide (by: Friday 2359)
```

### `delete` - Delete a Task

The task indicated by the user will be deleted.

Example of usage:

`delete 2`

Expected outcome:

The second task on the user's list will be deleted.

```
Got it. Removing this task:
[D][ ] Complete CS2113 User Guide (by: Friday 2359)
Now you have 2 tasks in the list.
```

### `find` - Find a Task based on keyword

The set of tasks containing the keyword supplied by the user will be printed.

Example of usage:

`find CS2113`

Expected outcome:

All tasks containing the keyword `CS2113` will be printed.

```
Here are the matching tasks in your list:
[T][ ] Get A for CS2113
[E][ ] CS2113 meeting (at: Monday 1400)
```

### `list` - List all Tasks in user's list of tasks

All tasks will be printed, including their status, description and additional parameters like time. 

Example of usage:

`list`

Expected outcome:

All tasks in user's task list will be shown on screen.

```
1.[T][ ] Get A for CS2113
2.[E][ ] CS2113 meeting (at: Monday 1400)
```

### `bye` - Save all tasks and exit Duke

All tasks will be saved in a human-readable state.

Example of usage:

`bye`

Expected outcome:

All tasks in user's task list will be saved.

```
Task File Updated
Bye! Hope to see you again soon :)

Process finished with exit code 0
```
