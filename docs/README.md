# User Guide

Eliz (a friend of Duke) is a desktop application for managing tasks (todos, deadlines, events), optimized for use using 
a Command Line Interface (CLI), for users who are task orientated and need to keep track of their tasks.

* [Features] (#Features)
  * [1. Adding todos, deadlines, and events](#1-adding-todos-deadlines-and-events)
    * [1.1 Add a todo](#1-1-add-a-todo-todo)
    * [1.2 Add a deadline](#1-2-add-a-deadline-deadline)
    * [1.3 Add an event](#1-3-add-an-event-event)
  * [2. Deleting tasks](#2-delete-a-task-delete)
  * [3. Listing current tasks](#3-listing-tasks-list)
  * [4. Marking tasks as done](#4-marking-tasks-as-done)
    * [4.1 Mark a task](#4-1-mark-as-done-mark)
    * [4.2 Unmark a task](#4-2-unmark-as-not-done-unmark)
  * [5. Finding task with keywords](#5-find-tasks-find)
  * [6. Exiting the program and saving the data](#6-exit-bye)
* [FAQ](#FAQ)


## Features

### 1. Adding todos, deadlines and events
To add tasks of different types

###1.1 Add a todo: `todo`;
Adding a todo task into your task list.

Format: `todo [TASK_NAME]`

Example:

`todo read book`

Expected output:
```
Got it. I've added this task 

[T][ ] read book

Now you have 1 tasks in the list.
```

### 1.2 Add a deadline: `deadline`;
Adding a deadline task into your task list.

Format: `deadline [TASK_NAME]/[DATE] (optional)`

Example:

`deadline assignment/wednesday 2359`

Expected outcome:
```
Got it. I've added this task 

[D][ ] assignment (wednesday 2359)

Now you have 1 tasks in the list.
```
### 1.3 Add an event: `event`;
Adding an event task into your task list.

Format: `event [TASK_NAME]`

Example:

`event Eliz's birthday`

Expected outcome:
```
Got it. I've added this task 

[E][ ] Eliz's birthday

Now you have 1 tasks in the list.
```

### 2. Delete a task: `delete`
Deleting a task from your task list.

Format: `delete [INDEX_OF_TASK_IN_LIST ]`

Example:
````
1. [T][ ] read book
2. [D][ ] assignment(wednesday 2359)
3. [E][ ] Eliz's birthday
````
Input: `delete 3`

Expected outcome:
```
Noted I have deleted this task

[E][ ] Eliz's birthday

You have 2 tasks left in the list.
````

### 3. Listing tasks: `list`
Listing tasks from your task list.

Format: `list`

Example:
`list`

Expected outcome:
````
1. [T][ ] read book
2. [D][ ] assignment(wednesday 2359)
````


### 4. Marking tasks as done: 
Marking and unmarking tasks.

### 4.1. Mark as done: `mark`
Marking a task as done.

Format: `mark [INDEX_OF_TASK_IN_LIST]`

Example:
````
1. [T][ ] read book
2. [D][ ] assignment(wednesday 2359)
````
Input: `mark 1`

Expected outcome:
````
Nice! I've marked this task as done:

[T][X] read book
````

### 4.2. Unmark as not done: `unmark`
Unmarking a task as not done.

Format: `unmark [INDEX_OF_TASK_IN_LIST]`

Example:
````
1. [T][X] read book
2. [D][ ] assignment(wednesday 2359)
````
Input: `unmark 1`

Expected outcome:
````
OK, I've marked this task as not done yet:

[T][ ] read book
````

### 5. Find tasks: `find`
Finding a task from your task list.

Format: `find [KEYWORD]`

Example:
````
1. [T][ ] read book
2. [D][ ] assignment(wednesday 2359)
3. [E][ ] Eliz's birthday
````
Input: `find read`

Expected outcome:
```
Here are the matching tasks in your list: 

1. [T][ ] read book
````

### 6. Exit: `bye`
Exiting the task.

Format: `bye`

Example:
`bye`

Expected outcome:
````
Bye. Hope to see you again soon!
````

## FAQ
```1. Can I mark events and deadlines as well?```
Yes you may!

```2. What if I face a problem starting the program?```
You might want to check if you have Java11 intalled on your device and close the file and restart.



