# User Guide

Duke is a desktop app for managing tasks, optimized for use via Command Line Interface (CLI).
It allows users to add different types of task and view them in a very easy to read manner.

- [Quick Start](#quick-start)
- [Features](#features)
  - [Todo](#adding-a-todo-task-todo)
  - [Deadline](#adding-a-deadline-task-deadline)
  - [Event](#adding-an-event-task-event)
  - [List](#list-all-your-tasks-list)
  - [Mark](#mark-a-task-as-completed-mark)
  - [Unmark](#mark-a-task-as-not-completed-unmark)
  - [Delete](#remove-a-task-from-the-list-delete)
  - [Find](#find-a-task-from-the-list-find)
  - [Bye](#exiting-from-the-program-bye)
- [Command Summary](#command-summary)

## Quick Start

1. Download and install Java 11 in your Computer.


2. Download the latest "Duke.jar" from [here](https://github.com/LDerpy/ip/releases/tag/A-Release).


3. To use existing data file (optional):
    - Create a "data" folder in the same folder as "Duke.jar".
    - Copy existing to "data" folder and name it "Duke.txt".


4. Open terminal/command prompt and run "Duke.jar" with following command:

   `java -jar Duke.jar`


5. Type a command in terminal/command prompt and press Enter to execute it.


6. Refer to the [Features](#features) below for details of each command.

## Features 

### Important things to note
- Words in full upper case (e.g. DESCRIPTION) refer to required parameters in the command.
- Missing these parameters may cause unintended function.
- Examples will show the example command followed by its output on the CLI.

### Adding a todo task: `todo`

Adds a todo task which only requires the description of said task.

Format: 

`todo DESCRIPTION`

Parameters: 

`DESCRIPTION`: Refers to the description of task to be added

Example:

`todo house chores`

```
__________________________________________________
Added to new thing to do for ya!
	[T][ ] house chores
__________________________________________________
```

### Adding a Deadline task: `deadline`

Adds a deadline task where it has a deadline alongside its description.

Format:

`deadline DESCRIPTION /by TASK_DEADLINE`

Parameters:

`DESCRIPTION`: Refers to the description of task to be added

`TASK_DEADLINE`: Refers to the deadline set for that task and any other information if needed

Example:

`deadline CS2113T Quiz /by this friday`

```
__________________________________________________
Added to thing for ya, and ya gotta do it soon!
	[D][ ] CS2113T Quiz (by: this friday)
__________________________________________________
```

### Adding an Event task: `event`

Adds an event task where it has an event location alongside its description.

Format:

`event DESCRIPTION /at TASK_LOCATION`

Parameters:

`DESCRIPTION`: Refers to the description of task to be added

`TASK_LOCATION`: Refers to the location of the event and any other information if needed

Example:

`event CS2113T Finals /at COM1 NUS`

```
__________________________________________________
Added to thing for ya at some place and time!
	[E][ ] CS2113T Finals (at: COM1 NUS)
__________________________________________________
```

### List all your tasks: `list`

List out all your tasks onto the screen.

Format:

`list`

Parameters:

None

Example:

`list`

```
__________________________________________________
Here are the tasks you want!
	1. [T][ ] house chores
	2. [D][ ] CS2113T Quiz (by: this friday)
	3. [E][ ] CS2113T Finals (at: COM1 NUS)
__________________________________________________
```

### Mark a task as completed: `mark`

Marks a task that you have added as completed.

Format:

`mark TASK_INDEX`

Parameters:

`TASK_INDEX`: Refers to the index of the task in the list

Example:

```
mark 1
__________________________________________________
Marked this task as done!
	[T][X] house chores
__________________________________________________


list
__________________________________________________
Here are the tasks you want!
	1. [T][X] house chores
	2. [D][ ] CS2113T Quiz (by: this friday)
	3. [E][ ] CS2113T Finals (at: COM1 NUS)
__________________________________________________
```

### Mark a task as not completed: `unmark`

Marks a task that you have added as not completed.

Format:

`unamrk TASK_INDEX`

Parameters:

`TASK_INDEX`: Refers to the index of the task in the list

Example:

```
unmark 1
__________________________________________________
Guess you messed up huh? Reverted that task!
	[T][ ] house chores
__________________________________________________


list
__________________________________________________
Here are the tasks you want!
	1. [T][ ] house chores
	2. [D][ ] CS2113T Quiz (by: this friday)
	3. [E][ ] CS2113T Finals (at: COM1 NUS)
__________________________________________________
```

### Remove a task from the list: `delete`

Removes and deletes a task from the list.

Format:

`delete TASK_INDEX`

Parameters:

`TASK_INDEX`: Refers to the index of the task in the list

Example:

```
delete 1
__________________________________________________
Either you're done with that or you gave up. Anyways, it's gone!
	[T][ ] house chores
__________________________________________________

list
__________________________________________________
Here are the tasks you want!
	1. [D][ ] CS2113T Quiz (by: this friday)
	2. [E][ ] CS2113T Finals (at: COM1 NUS)
__________________________________________________
```

### Find a task from the list: `find`

Searches for a task in the list based on the user input keyword.

Format:

`find KEYWORD`

Parameters:

`KEYWORD`: Refers to the specific item you are searching for in the task list (Case-Sensitive)

Example:

`find Quiz`

```
__________________________________________________
Here are the tasks you want!
	1. [D][ ] CS2113T Quiz (by: this friday)
__________________________________________________
```

### Exiting from the program: `bye`

Exits from the program and saves the tasks onto file.
- Warning: Tasks are saved onto storage only if the `bye` command is used.

Format:

`bye`

Parameters:

None

Example:

`bye`

```
__________________________________________________
	 Goodbye. See you next time!
__________________________________________________
```

## Command Summary

| Action    | Format                                   | Example                                  |
|-----------|------------------------------------------|------------------------------------------|
| todo      | `todo DESCRIPTION`                       | `todo user guide`                        |
| deadline  | `deadline DESCRIPTION /at TASK_DEADLINE` | `event meeting /at 20/02/2020 1700-1900` |
| event     | `event DESCRIPTION /by TASK_LOCATION`    | `deadline report /by 24/10/2020 2359`    |
| list      | `list`                                   | `list`                                   |
| mark      | `done TASK_INDEX`                        | `mark 1`                                 |
| unmark    | `unmark TASK_INDEX`                      | `unmark 1`                               |
| delete    | `delete TASK_INDEX`                      | `delete 2`                               | 
| find      | `find KEYWORD`                           | `find Quiz`                              |
| bye       | `bye`                                    | `bye`                                    |


