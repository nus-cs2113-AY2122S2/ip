# User Guide

Duke, a **Command Line Interface (CLI) application** to help you list all your tasks

-   [Quick Start](#quick-start)
-   [Features](#features)
    -   [Adding a Todo: `todo`](#adding-a-todo-todo)
    -   [Adding a Deadline: `deadline`](#adding-a-deadline-deadline)
    -   [Adding an Event: `event`](#adding-an-event-event)
    -   [Listing all tasks: `list`](#listing-out-all-tasks-list)
    -   [Marking a task as done: `mark`](#marking-a-task-as-completed-mark)
    -   [Marking a task as incomplete : `unmark`](#marking-a-task-as-incomplete-unmark)
    -   [Deleting a task: `delete`](#deleting-a-task-delete)
    -   [Searching for tasks: `find`](#searching-for-tasks-find)
    -   [Exiting the program: `bye`](#exiting-the-program-bye)

## Quick Start

1. Prerequisites: JDK 11, update Intellij to the most recent version.
2. Download the latest copy of `ip.jar` from the [Releases](https://github.com/cristoforows/ip/releases) page.
3. Open your Command Prompt (Windows) or Terminal (Mac/Linux), and navigate to the folder you created.
4. Run the command `java -jar Duke.jar`, this will be shown in your terminal if successfull


    -----------------------------------------
    Hello I'm Duke.Duke
    What can I help you with?
    -----------------------------------------


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
-----------------------------------------
learn web development
     added:  [T][ ] learn web development
     there are currently 1 tasks 
-----------------------------------------
```

### Adding a Deadline: `deadline`

Create a Deadline type task

**Format:** `deadline <description> /by <time>`

**Example Input:**

```
deadline finish ip /by 2359 today
```

**Example Output:**

```
-----------------------------------------
     added:  [D][ ] finish ip (by: 2359 today)
     there are currently 1 tasks 
-----------------------------------------
```

### Adding an Event: `event`

Add an Event type task

**Format:** `event <description> /at <time>`

**Example Input:**

```
event meeting project /at monday 1330
```

**Example Output:**

```
-----------------------------------------
     added:  [E][ ] meeting project (at: monday 1330)
     there are currently 1 tasks 
-----------------------------------------
```

### Listing out all tasks: `list`

Shows all currently stored task by Brave

**Format:** `list`

**Example Output:**

```
-----------------------------------------
    Here the task you've written m'lord:
    1  [T][ ] this and that
    2  [D][ ] that and this (by: tomorrow)
    3  [E][ ] these and those (at: next week)
    4  [T][ ] this
    5  [T][ ] another this and that
    6  [D][ ]  another that and this (by: the day  after today)
    7  [E][ ] meeting project (at: monday 1330)
-----------------------------------------
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
-----------------------------------------
    I've marked the task as done m'lord:
    1  [T][ ] this and that
    2  [D][ ] that and this (by: tomorrow)
    3  [E][X] these and those (at: next week)
    4  [T][ ] this
    5  [T][ ] another this and that
    6  [D][ ]  another that and this (by: the day  after today)
    7  [E][ ] meeting project (at: monday 1330)
-----------------------------------------

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
-----------------------------------------
    I've marked the task as done m'lord:
    1  [T][ ] this and that
    2  [D][ ] that and this (by: tomorrow)
    3  [E][ ] these and those (at: next week)
    4  [T][ ] this
    5  [T][ ] another this and that
    6  [D][ ]  another that and this (by: the day  after today)
    7  [E][ ] meeting project (at: monday 1330)
-----------------------------------------
```

### Deleting a task: `delete`

Remove a task stored by Brave.

**Format:** `delete <index>`

> **Note:**  
> `<index>` can be referenced from the `list` command.

**Example Input:**

```
delete 5
```

**Example Output:**

```
-----------------------------------------
     deleted:  [T][ ] another this and that
     there are currently 6 tasks 
-----------------------------------------
```

### Searching for tasks: `find`

Filter the task base on `<keyword>`

**Format:** `find <keyword>`

**Example Input:**

```
find this
```

**Example Output:**

```
-----------------------------------------
    1  [T][ ] this and that
    2  [D][ ] that and this (by: tomorrow)
    3  [T][ ] this
    4  [D][ ]  another that and this (by: the day  after today)
-----------------------------------------
```

### Exiting the program: `bye`

Quits the program.

**Format:** `bye`

**Output:**

```
-----------------------------------------
Bye. Hope to see you again soon!
-----------------------------------------
```
