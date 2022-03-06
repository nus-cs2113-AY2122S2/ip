# User Guide
_Duke_ is an application designed for managing to-do lists, optimized for use via a __Command Line Interface (CLI)__.

````
Hello from
 ____        _        
|  _ \ _   _| | _____
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|

	____________________________________________________________
	 Hello! I'm Duke
	 What can I do for you?
	____________________________________________________________

````

## Features 

### Adding a deadline

Adds a deadline to the list with the date by which the deadline is due.

### Adding an event

Adds an event and when it's happening to the list.

### Adding a general to-do task

Adds a todo task with a description with no other information required.

### Delete an item from the list

Deletes a task from the main list.

### List all tasks

List all items present in the list on the screen, while indicating what kind of task it is, whether it is done or not and any other information attached to the specific kind of task.

### Search for items in the list

Searches for tasks that match the search string, and displays.

### Mark an item as done

Indicates that the task is done and reflects it when the tasks are printed on screen.

### Re-mark an item as not done yet

Un-marks the task and indicates it as not done again.

## Usage

### `todo` - add a to-do task

Example of usage: 

`todo read 30 pages of Dune`

Expected outcome:

```
	____________________________________________________________
	 Got it. I've added this task:
	 	[T][ ] read 30 pages of Dune
	 Now you have 3 tasks in the list.
	____________________________________________________________
```
The output message shows that the task is added, and prints out the number of tasks that are now present in the list.

### `deadline` - add a deadline task

Takes a deadline's description and the due date in `yyyy-mm-dd` format, and adds the deadline to the list.

Example of usage:

`deadline submit ip /by 2022-03-04`

Expected outcome:

```
	____________________________________________________________
	 Got it. I've added this task:
	 	[D][ ] submit ip (by: Mar 4 2022)
	 Now you have 4 tasks in the list.
	____________________________________________________________
```
The output shows that the deadline is added, and prints out the number of tasks that are now present in the list.

### `event` - add an event task

Takes an event's description and when the event is taking place using the `/at` keyword, and adds the event to the list.

Example of usage:

`event HI lecture /at 12 pm`

Expected outcome:

```
    ____________________________________________________________
	 Got it. I've added this task:
	 	[D][ ] HI lecture (at:  12 pm)
	 Now you have 5 tasks in the list.
	____________________________________________________________
```
The output shows that the event is added, and prints out the number of tasks that are now present in the list.

### `list` - display all items

Example of usage:

`list`

Expected outcome:

```
    ____________________________________________________________
	1.[D][X] something (by: Sep 11 2089)
	2.[T][ ] sljf
	3.[T][ ] read 30 pages of Dune
	4.[D][ ] submit ip (by: Mar 4 2022)
	5.[D][ ] HI lecture (at:  12 pm)
	____________________________________________________________
```
The output shows all the tasks currently in the program, and shows the kind of task it is and whether it is done or not.

- `[X]` indicates done and `[ ]` indicates not done.
- `[D]` indicates that the task is a deadline, `[E]` indicates events, and `[T]` indicates to-do tasks


### `mark` - mark an task as done

Marks a task using the task number.

Example of usage:

`mark 4`

Expected outcome:

```
	____________________________________________________________
	 Nice! I've marked this task as done: 
	 	[D][X] submit ip (by: Mar 4 2022)
	____________________________________________________________
```
The output shows that the task is marked as done and indicates it when the task is printed.

### `umark` - unmark a task as not done yet

Un-marks a task using the task number.

Example of usage:

`unmark 4`

Expected outcome:

```
	____________________________________________________________
	OK, I've marked this task as not done yet: 
	 	[D][ ] submit ip (by: Mar 4 2022)
	____________________________________________________________
```
The output shows that the task is marked as done and indicates it when the task is printed.

### `delete` - deletes a task

Deletes a task using the task number.

Example of usage:

`delete 2`

Expected outcome:

```
	____________________________________________________________
	 Got it. I've removed this task:
	 	[T][ ] sljf
	 Now you have 4 tasks in the list.
	____________________________________________________________
```
The output shows that the task is removed from the list and indicates the remaining number of tasks.

### `find` - searches in the list

Searches inside the list by looking for the search string in the descriptions of all tasks.

Example of usage:

`find Dune`

Expected outcome:

```
	____________________________________________________________
	 Here are the matching tasks in your list:
	2.[T][ ] read 30 pages of Dune
	____________________________________________________________
```

