# Duke Bot User Guide

## Features in app

### Create, list, delete and find tasks of types todo, deadline and event

### Mark a task as complete/incomplete

Mark your task as complete or incomplete as deemed necessary


### Store tasks data locally

Store the data into a text file so that you dont need to retype

## Usage of commands

### **command:** `todo`

**Format:** `todo TASK_DESCRIPTION` - Create a new task of type todo

**Description of command :**  Add a todo task with a description.

Example of usage: 

`todo read book`

Expected outcome:

Acknowledgement and total number of tasks in list.

```
Got it. I've added this task:
[T][ ] read book
Now you have 1 tasks in the list.
```
### **command:** `deadline`

**Format:** `deadline TASK_DESCRIPTION /by DUE_DATE DUE_TIME` - Create a new task of type deadline

**Description of command :**  Add a deadline task with a description.

Example of usage: 

`deadline return book /by 2019-10-15 1800`

Expected outcome:

Acknowledgement and total number of tasks in list.

```
Got it. I've added this task:
[D][ ] return book (by: Oct 15 2019)
Now you have 2 tasks in the list.
```

### **command:** `event`

**Format:** `event TASK_DESCRIPTION /at DAY TIME` - Create a new task of type event

**Description of command :**  Add a event task with a description.

Example of usage: 

`event attend CS2113T lecture /at Friday 4-6pm`

Expected outcome:

Acknowledgement and total number of tasks in list.

```
Got it. I've added this task:
[E][ ] attend CS2113T lecture (at: Friday 4-6pm)
Now you have 3 tasks in the list.
```

### **command:** `list`

**Format:** `list` - Show all tasks

**Description of command :**  list all tasks with its status
Example of usage: 

`list`

Expected outcome:

All tasks, its status and total number of tasks in list.

```
Here are the tasks in your list:
1.[T][ ] read book
2.[D][ ] return book (by: Oct 15 2019)
3.[E][ ] attend CS2113T lecture (at: Friday 4-6pm)
Now you have 3 tasks in the list.
```

### **command:** `find`

**Format:** `find SEARCH_TERM` 

**Description of command :**  Show all tasks with descriptions containing the search term
Example of usage: 

`find book`

Expected outcome:

All tasks matching search term

```
Here are the tasks in your list:
1.[T][ ] read book
2.[D][ ] return book (by: Oct 15 2019)
Now you have 2 tasks in the list.
```

### **command:** `mark`

**Format:** `mark TASK_NUM` 

**Description of command :**  Mark task number in list as complete
Example of usage: 

`mark 2`

Expected outcome:

Task 2 will be marked as complete

```
[D][X] return book (by: Oct 15 2019)
Nice! I've marked this task as done:
```

### **command:** `unmark`

**Format:** `unmark TASK_NUM` 

**Description of command :**  Mark task number in list as imcomplete
Example of usage: 

`umark 2`

Expected outcome:

Task 2 will be marked as incomplete

```
[D][ ] return book (by: Oct 15 2019)
Ok I have marked this task as incomplete:
```

### **command:** `delete`

**Format:** `delete TASK_NUM` 

**Description of command :**  Delete task number in list
Example of usage: 

`delete 2`

Expected outcome:

Task 2 will deleted

```
Ok I have deleted this task as requested:
```

`list`
```
Here are the tasks in your list:
1.[T][ ] read book
2.[E][X] attend CS2113T lecture (at: Friday 4-6pm)
```


### **command:** `exit`

**Format:** `exit` 
**Description of command :**  Exit program and save list to file
Example of usage: 

`exit`

Expected outcome:

Says bye and exit program

```
Bye. Hope to see you again soon!

Process finished with exit code 0

```

### **command:** `bye`

**Format:** `bye` 
**Description of command :**  Exit program 
Example of usage: 

`bye`

Expected outcome:

Says bye and exit program

```
Bye. Hope to see you again soon!
```
