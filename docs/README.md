# User Guide

## Features 

### Feature- Add Todo

Add a task to list

### Feature- Add Deadline

Add a deadline to list

### Feature- Add Event

Add a event to list

### Feature- Delete Task

Delete any type of task in the list



## Usage

### `todo` - Add Todo

Add a task into the list

Example of usage: 

`todo read book`

Expected outcome:
````
Got it. I've added this task: 
[T][ ] read book 
Now you have 1 tasks in the list.
````

### `deadline` - Add Deadline

Add a deadline into the list

Example of usage:

`deadline read book /by 02/02/1998`

Expected outcome:
````
Got it. I've added this task: 
[D][ ] read book (by: 02/02/1998)
Now you have 1 tasks in the list.
````

### `event` - Add Event

Add an event into the list

Example of usage:

`event read book /at 02/02/1998`

Expected outcome:
````
Got it. I've added this task: 
[E][ ] read book (at: 02/02/1998)
Now you have 1 tasks in the list.
````

### `delete` - Delete Task

Delete Task at particular index

Example of usage:

`delete 2`

Expected outcome:
````
Noted. I've removed this task: 
[D][ ] read book (by: 02/02/1998)
Now you have 1 tasks in the list.
````

### `mark` - Mark Task

Mark the selected index of task as done

Example of usage:

`mark 1`

Expected outcome:
````
Nice! I've marked this task as done: 
[T][X] read book
````

### `unmark` - Unmark Task

Unmark the selected index of task as not done

Example of usage:

`mark 1`

Expected outcome:
````
OK, I've marked this task as not done yet:
[T][ ] read book
````

### `list` - Show all tasks in list

Show all tasks in list

Example of usage:

`list`

Expected outcome:
````
Here are the tasks in your list:
1.[T] [ ] read book
2......
3......
4......
````

### `find` - Search tasks with keywords

Show all tasks containing provided keywords

Example of usage:

`find ea`

Expected outcome:
````
Here are the matching tasks in your list:
1.[T] [ ] read book
2.[D] [ ] read book (by: 02/02/1998)
````

### `bye` - Exit Duke

Terminate Duke

Example of usage:

`bye`

Expected outcome:
````
Bye. Hope to see you again soon!
````
