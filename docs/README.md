# User Guide

Marites is a bot designed to assist tracking the completion of your tasks. 

## Features 

### Task List

Maintains an list of tasks for easy viewing.

### Task Completion

Marites can mark tasks as complete or incomplete, allowing you to get a bird's eye view of what you need to accomplish.

### Different Task Types

Marites can differentiate between tasks you can do anytime (todos), and events with a specific date attached (deadlines and events). 

### Task Search

Finds all tasks with a given keyword in their description. This is useful if you tag these descriptions in some way.

### Persistent Storage

Marites can reload your task list from a file it has saved before, allowing for easy access even after shutting down the bot.

## Usage

### `todo (description)` - Adds todo to task list

Adds a todo to your task list.

Example of usage: 

`> todo Finish increment`

Expected outcome:
```
Alright, task added:
  [T][ ] Finish increment
Your list currently has 1 tasks.
========================================
```
The output displays the added task, as well as its type (here, a `todo`), and how many tasks are now in the list. 

### `event (description) --by (time)` - Adds event to task list

Adds an event to your task list. An _event_ is a task which is attended to at a given time.
This command requires a `--by` parameter, after which the date and time of the event 
should be given in `YYYY-MM-DD HH:SS` format. Note that the time must be 24-hour time.

Example of usage:

`> event Attend group meeting --by 2022-04-15 15:00`

Expected outcome:

```
Alright, task added:
  [E][ ] Attend group meeting (at: 15:00 at 2022-04-15)
Your list currently has 1 tasks.
========================================
```
The output displays the added task, as well as its type (here, an `event`), and how many tasks are now in the list. 


### `deadline (description) --by (time)` - Adds deadline to task list

Adds a deadline to your task list. A _deadline_ is a task which must be attended to before a given time.
This command requires a `--by` parameter, after which the date and time of the event 
should be given in `YYYY-MM-DD HH:SS` format. Note that the time must be 24-hour time.

Example of usage:

`> deadline CS3230 assignment --by 2022-03-14 11:59`

Expected outcome:

```
Alright, task added:
  [D][ ] CS3230 assignment (by: 11:59 at 2022-03-14)
Your list currently has 1 tasks.
========================================
```
The output displays the added task, as well as its type (here, a `deadline`), and how many tasks are now in the list. 

### `list` - Lists all tasks 

Prints out a list of all tasks Marites is tracking. It will also tell you whether or not a task has been completed.

Example of usage (assuming these 3 tasks are in the task list):

`> list`

Expected outcome:
```
1. [T][ ] Finish increment
2. [E][ ] Attend group meeting (at: 15:00 at 2022-04-15)
3. [D][ ] CS3230 assignment (by: 11:59 at 2022-03-14)
========================================
```
The output displays all tasks. The empty box is allocated for marking and unmarking tasks.

### `mark (index)` - Mark a task as complete

Marks the task with the given number as complete.

Example of usage:

`> mark 2`

Expected outcome:

```
Good job on getting this done!  
  [E][X] Attend group meeting (at: 15:00 at 2022-04-15)
========================================
```
The output shows the task, now marked as complete.

### `unmark (index)` - Mark a task as incomplete

Marks the task with the given number as incomplete.

Example of usage:

`> unmark 1`

Expected outcome:

```
Okay, I've marked this as not yet done:
  [T][ ] Finish increment
========================================
```
The output shows the task, now marked as incomplete.

### `delete (index)` - Delete a task

Remove the task with the given index from your list.

Example of usage:

`> delete 2`

Expected outcome:

```
Alright, task deleted:
  [E][X] Attend group meeting (at: 15:00 at 2022-04-15)
Your list currently has 2 tasks.
========================================
```
The output shows the task deleted from the list, as well as how many tasks are now in the list.

### `find (query)` - Search for tasks

Prints out a list of all tasks whose descriptions match the given string.
The indices in the result are their indices in the original list, so it may be out of order.

Example of usage:

`> find CS3230`

Expected outcome:

```
Here are the matching tasks in your list:
2. [D][ ] CS3230 assignment (by: 11:59 at 2022-03-14)
4. [E][ ] CS3230 midterm (at: 14:00 at 2022-03-03)
========================================
```
Here, task 2 and task 4 contain "CS3230" in their descriptions, so they are included in the output.

### `bye` - Exit the bot

Shuts down the bot.

Example of usage:

`> bye`

Expected outcome:

```
See you next time!
========================================
``` 
