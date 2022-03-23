# User Guide

## Features 

### Add, Remove and Mark Tasks

Duke allows for one to keep track of tasks by allowing one to add, remove and mark tasks as done. For each task, one can also set it to be a simple To-Do task, an Event task with an associated date and/or time as well as a Deadline task that can store an associated date and/or time. Once a task is added, it can then be set as completed with an option to set it as pending afterwards. Additionally, should tasks be addded by mistake, the task can be deleted entirely.

### Local Storage

Duke also allows for the local storage of the associated tasks. Every update to the existing task list will be backed up locally such that one need not keep Duke running in the background. Along with storage, Duke can also recall past tasks saved from a previous session. As the files are stored in exactly the same format as what is displayed to the user, modification of the task backup file is easy.

### Search Task

Duke allows for one to query if a specific string is found within the task information (excludes event/deadline information). Duke returns a view of the subset returned. To perform operations on this, access the index as given by Duke.

## Usage

### `list` - View all saved tasks

Access all tasks saved and prints them to the user with an associated index. Tasks are not sorted and will be displayed in the order which they are added.

Example of usage: 

`list`

Expected outcome:

```
total 4 tasks to be completed:
1. [T] [X] try 3x
2. [E] [ ] try this (at: now)
3. [D] [ ] try that (by: noon)
4. [D] [ ] do homework (by: tomorrow)
```

### `todo TASK` - Create To-Do Task

Creates a simple task with no associated due date or event date. 

Example of usage: 

`task try and try and try again`

Expected outcome:

```
Added: [T] [ ] try and try and try again
```

### `deadline TASK /by DUE_DATE` - Create Deadline Task

Creates a deadline task with an associated due date. Due date is taken in and stored as a string.

Example of usage: 

`deadline finish trying /by tomorrow`

Expected outcome:

```
Added: [D] [ ] finish trying (by: tomorrow)
```

### `event TASK /at EVENT_DATE` - Create Event Task

Creates a deadline task with an associated event date. Event date is taken in and stored as a string.

Example of usage: 

`event try some debugging /at 10am`

Expected outcome:

```
Added: [E] [ ] try some debugging (at: 10am)
```

### `mark INDEX` - Mark Task as Complete

Marks task as complete by the index shown to the user. Index begins at 1, not 0. User may mark the same task as complete multiple times. 

Example of usage: 

`mark 6`

Expected outcome:

```
Ok, task done!
   [D] [X] finish trying (by: tomorrow)

```
  
### `unmark INDEX` - Marks Task as Incomplete

Unmarks task as complete by the index shown to the user. Index begins at 1, not 0. User may mark the same task as incomplete multiple times.

Example of usage: 

`unmark 6`

Expected outcome:

```
Ok, you didn't actually do the task!
   [D] [ ] finish trying (by: tomorrow)

```

### `delete INDEX` - Delete Task 

Delete tasks by passing in the index shown to the user. Index begins at 1, not 0. Deletes are permanent and will result in the index changing for subsequent tasks.

Example of usage: 

`delete 6`

Expected outcome:

```
Task removed: 
[D] [ ] finish trying (by: tomorrow)

```

### `find STRING` - Find a specific Task

Searches through the existing list of tasks to find a particular string. Exact string matching is used with indexes returned relative to the whole list of tasks.

Example of usage:

`find debugging`

Expected outcome:

```
The matching tasks are as follows:
6. [E] [ ] try some debugging (at: 10am)

```

### `bye` - Quit

Quits Duke. Tasks will be backed up to the same folder as the application.

Example of usage:

`bye`
       
Expected outcome:

```
Bye. Hope to see you again soon!

```
