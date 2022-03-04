# User Guide for Duke
Duke is a simple application that allows users to keep track of their list of tasks such as Todo, Deadline and Event.
## Features 

### Add Task

Tasks such as Todo, Deadline and Event, can be added.

### Mark Task as Done

User can mark a task as done upon completion of the task.

### Mark Task as Not Done

If a task has been marked as done accidentally, user can unmark the task.

### List Tasks

User can list all the tasks stored.

### Delete Task

Task can be deleted from the task list.

### Find Task

User can search for tasks or filter the task list using a keyword.

### Save Data to local machine

Upon exiting Duke, all tasks in the task list will be written to a text file and stored.

### Load Data from local machine

When the user runs Duke again, Duke will load data from the text file saved.

## Usage

### `todo` - Adding a new ToDo Task

The description of task will be added to the task list based on the user input.

Example of usage: 

`todo borrow book`

Expected outcome:

A new Todo task will be created and its description will be added to the task list. 
It will print the total number of tasks in the list.

```
 Got it. I've added this task: 
   [T][ ] borrow book
 Now you have 3 tasks in the list
```

### `deadline` - Adding a new Deadline Task

The description and due date of task will be added to the task list based on the user input.
User must indicate a due date for the task.

Example of usage:

`deadline complete Duke /by Friday`

Expected outcome:

A new Deadline task will be created, its description and due date will be added to the task list.
It will print the total number of tasks in the list.

```
 Got it. I've added this task: 
   [D][ ] complete Duke(by: Friday)
 Now you have 4 tasks in the list
```

### `event` - Adding a new Event Task

The description and date of task will be added to the task list based on the user input.
User must indicate a date for the task.

Example of usage:

`event meeting /at Mon 2-4pm`

Expected outcome:

A new Event task will be created, its description and date will be added to the task list.
It will print the total number of tasks in the list.

```
 Got it. I've added this task: 
   [E][ ] meeting(at: Mon 2-4pm)
 Now you have 5 tasks in the list
```

### `list` - Listing all tasks in the task list

Prints all tasks, including task type, status, description and additional date for deadline and event task.

Example of usage:

`list`

Expected outcome:

All tasks in the task list will be shown to the user.

```
 Here are the tasks in your list:
 1. [E][ ] project meeting(at: Mon 2-4pm)
 2. [D][ ] return book(by: Sunday)
 3. [T][ ] borrow book
 4. [D][ ] complete Duke(by: Friday)
 5. [E][ ] meeting(at: Mon 2-4pm)
```

### `mark` - Mark Task as Done

Marks a selected task as done.

Example of usage:

`mark 4`

Expected outcome:

The fourth task will be marked as done representing using X. 

```
 Nice! I've marked this task as done:
   [D][X] complete Duke(by: Friday)
```

### `unmark` - Mark Task as Not Done

Marks a selected task as not done.

Example of usage:

`unmark 4`

Expected outcome:

X will be removed from the fourth task if it has been marked as not done.

```
 OK, I've marked this task as not done yet:
   [D][ ] complete Duke(by: Friday)
```

### `delete` - Deleting a task

Deletes a selected task.

Example of usage:

`delete 4`

Expected outcome:

The fourth task will be removed from the task list.

```
 Noted. I've removed this task:
   [D][ ] complete Duke(by: Friday)
 Now you have 4 tasks in the list
```

### `find` - Finding tasks 

Find tasks using keyword from user.

Example of usage:

`find book`

Expected outcome:

Finds and prints tasks containing the keyword "book". 
The number in the square bracket indicates the index of the task in the list.

```
 Here are the matching tasks in your list:
 1[2]. [D][ ] return book(by: Sunday)
 2[3]. [T][ ] borrow book
```

### `bye` - Saving all tasks to the local machine and exiting Duke

All tasks in the task list will be saved to local machine in text file format.

Example of usage:

`bye`

Expected outcome:

Data will be saved.

```
 Data saved successfully
____________________________________________________________
 Bye. Hope to see you again soon!
```