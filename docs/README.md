# User Guide

Brave is a **Command Line Interface (CLI) application** for you to manage your daily task
Todo - Event - Deadline

- [Quick Start](#quick-start)
- [Features](#features)
    - [Adding a Todo: `todo`](#adding-a-todo-todo)
    - [Adding a Deadline: `deadline`](#adding-a-deadline-deadline)
    - [Adding an Event: `event`](#adding-an-event-event)
    - [Listing all tasks: `list`](#listing-out-all-tasks-list)
    - [Marking a task as done: `mark`](#marking-a-task-as-completed-mark)
    - [Marking a task as incomplete : `unmark`](#marking-a-task-as-incomplete-unmark)
    - [Deleting a task: `delete`](#deleting-a-task-delete)
    - [Searching for tasks: `find`](#searching-for-tasks-find)
    - [Exiting the program: `bye`](#exiting-the-program-bye)

## Quick Start
1. Prerequisites: JDK 11, update Intellij to the most recent version.
2. Download the latest copy of `Brave.jar` from the [Releases](https://github.com/johnsuharjono/ip/releases) page.
3. Open your Command Prompt (Windows) or Terminal (Mac/Linux), and navigate to the folder you created.
4. Run the command `java -jar Brave.jar` 

## Features 

### Adding a Todo: `todo`

Create a To-Do type task

**Format:** `todo <description>`

**Example Input:**
```
todo learn web development
```

**Example Output:**
```
----------------------------------------------
GOTT IT!! I have added task below!
[T][ ] learn web development
You currently have (n) task in the list
----------------------------------------------
```

### Adding a Deadline: `deadline`

Create a Deadline type task

**Format:** `deadline <description> /by <time>`

**Example Input:**
```
deadline finish math homework /by Thursday Morning
```

**Example Output:**
```
----------------------------------------------
GOTT IT!! I have added task below!
[D][ ] finish math homework (by: Thursday Morning)
You currently have (n) task in the list
----------------------------------------------
```

### Adding an Event: `event`

Add an Event type task

**Format:** `event <description> /at <time>`

**Example Input:**
```
event Brave's birthday /at 4 March 2022
```

**Example Output:**
```
----------------------------------------------
GOTT IT!! I have added task below!
[E][ ] Brave's birthday (at: 4 March 2022)
You currently have (n) task in the list
----------------------------------------------
```

### Listing out all tasks: `list`

Shows all currently stored task by Brave

**Format:** `list`

**Example Output:**
```
----------------------------------------------
1 [T][X] read book
2 [D][X] return book (by: June 6th)
3 [E][ ] project meeting (at: Aug 6th 2-4pm)
4 [T][X] join sports club
5 [T][ ] cs2113 lecture
----------------------------------------------
```

### Marking a task as completed: `mark`

Brave will mark the given task as completed

**Format:** `mark <index>`

> **️ Note:** index can be seen from calling `list` command


**Example Input:**
```
mark 3
```

**Example Output:**
```
Nice! I've marked this task as done:
[E][X] project meeting (at: Aug 6th 2-4pm)
```

### Marking a task as incomplete: `unmark`

Brave will mark the given task as incomplete

**Format:** `unmark <index>`

> **️ Note:** index can be seen from calling `list` command


**Example Input:**
```
unmark 3
```

**Example Output:**
```
OK, I've marked this task as not done yet:
[E][ ] project meeting (at: Aug 6th 2-4pm)
```

### Deleting a task: `delete`

Remove a task stored by Brave.

**Format:** `delete <index>`

> **Note:**  
> `<index>` can be referenced from the `list` command.

**Example Input:**
```
delete 6
```

**Example Output:**
```
----------------------------------------------
I managed to delete task below!
[T][ ] learn web development
You now have (n) task remaining
----------------------------------------------
```

### Searching for tasks: `find`

Filter the task base on `<keyword>`

**Format:** `find <keyword>`

**Example Input:**
```
find book
```

**Example Output:**
```
----------------------------------------------
1 [T][X] read book
2 [D][X] return book (by: June 6th)
----------------------------------------------
```

### Exiting the program: `bye`

Quits the program.

**Format:** `bye`

**Output:**
```
----------------------------------------------
Bye, Hope to see you again soon!
----------------------------------------------
```

