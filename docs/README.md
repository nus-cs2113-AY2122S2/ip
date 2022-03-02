# User Guide

Duke is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI).

## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest duke.jar from [here](https://github.com/cczhouqi/ip/releases).
3. Copy the file to the folder you want to use as the home folder for your Duke.
4. Run the command `java -jar ip.jar` in the same folder as the jar file.

## Features 

### Add task

Add different type of tasks by using `todo`, `deadline`, and `event`.

### Delete task

Delete existing task by using `delete`

### View task

View existing tasks by using `list`

### Find task

Search for tasks with a keyword by using `find`

### Mark task

Mark a task as finished/unfinished by using `mark` and `unmark`

## Usage

### `list` - View current task list

Example of usage:

`list`

Expected outcome:
The tasks you have in your current task list.
```
____________________________________________________________
Here are the tasks in your list:
1. [E][X] that event (at: this time)
2. [T][ ] something
3. [D][ ] java practice (by: Sep 1 2023)
Now you have 3 tasks in the list.
____________________________________________________________
```

### `todo` - Add a Todo task

Add a Todo task with description to the task list.

Format: `todo DESCRIPTION`

Example of usage:

`todo CS2113 assignment`

Expected outcome:
Message showing the task is added successfully.
```
____________________________________________________________
Got it. I've added this task: 
[T][ ] CS2113 assignment
Now you have 4 tasks in the list.
____________________________________________________________
```

### `event` - Add an Event

Add an Event task with description and event time.

Format: `event EVENTDESCRIPTION /at TIMEDESCRIPTION`

Example of usage:

`event CS2113 lecture /at this afternoon`

Expected outcome:
Message showing the task is added successfully.
```
____________________________________________________________
Got it. I've added this task: 
[E][ ] CS2113 lecture (at: this afternoon)
Now you have 5 tasks in the list.
____________________________________________________________
```

### `deadline` - Add a deadline

Add a deadline task with description and due date.

Format: `deadline DESCRIPTION /by YYYY-MM-DD`

Example of usage:

`deadline CS2113 quiz /by 2022-3-5`

Expected outcome:
Message showing the task is added successfully.
```
____________________________________________________________
Got it. I've added this task: 
[D][ ] CS2113 quiz (by: Mar 5 2022)
Now you have 6 tasks in the list.
____________________________________________________________
```

### `mark` - Mark a task as done

Mark the task with the given index as finished.

Format: `mark INDEX`

Example of usage:

`mark 6`

Expected outcome:
Message showing the task is marked as done.
```
____________________________________________________________
Nice! I've marked this task as done:
[D][X] CS2113 quiz (by: Mar 5 2022)
____________________________________________________________
```

### `unmark` - Mark a task as not done yet

Mark the task with the given index as not finished.

Format: `unmark INDEX`

Example of usage:

`unmark 6`

Expected outcome:
Message showing the task is marked as not done yet.
```
____________________________________________________________
OK, I've marked this task as not done yet:
[D][ ] CS2113 quiz (by: Mar 5 2022)
____________________________________________________________
```

### `find` - Find tasks with keyword

Find tasks by searching for a keyword.

Format: `find KEYWORD`

Example of usage:

`find 2113`

Expected outcome:
The list of matching tasks in current task list.
```
____________________________________________________________
Here are the matching tasks in your list:
[T][ ] CS2113 assignment
[E][ ] CS2113 lecture (at: this afternoon)
[D][ ] CS2113 quiz (by: Mar 5 2022)
____________________________________________________________
```

### `delete` - Delete a task

Delete the task with the given index.

Format: `delete INDEX`

Example of usage:

`delete 3`

Expected outcome:
Message showing the task is deleted successfully.
```
____________________________________________________________
Noted. I've removed this task:
[T][ ] something
Now you have 5 tasks in the list.
____________________________________________________________
```

### `bye` - Exit

Exit the program.

Format: `bye`
