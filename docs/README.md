# User Guide

## Table of Contents
- [Getting Started](#getting-started)
- [Features](#features)
- [Usage](#usage)

## Getting Started

Prerequisites: Make sure you have JDK 11 (Java) installed.

1. Get Dook from [here](https://github.com/IncompetentDev/ip/releases/tag/A-Release)
2. Run in the terminal, the jar file with `java -jar ip.jar`. Make sure the jar file is in the same directory.
3. Dook is now running. You can view commands with `help`, and exit with `bye`. Any task changes are automatically saved.

Example output:
```
PS C:\ip_jar> java -jar ip.jar
 ____        _
|  _ \ _   _| | _____
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|

____________________________________________________________
Hello! I'm Dook!
What can I do for you?
____________________________________________________________

```

## Features 
Dook comes with the following:
1. Add different tasks
  - `todo`
  - `deadline`
  - `event`
2. Mark task as completed `mark`
3. Unmark completed tasks `unmark`
4. List all tasks `list`
5. Find tasks by keywords `find`
6. Delete unwanted tasks `delete`
7. Get help on list of commands `help`
8. Exit `bye`

## Usage

### `todo` - Create a todo task.

This command creates a todo and adds it to the list of tasks to be done. The task type is indicated as `T`.

Example of usage: 

`todo <description>`

Expected outcome:

Creates a new todo and adds it to the list of tasks.

```
> todo MA2001
____________________________________________________________
added: [T][ ] MA2001
____________________________________________________________
```

### `deadline` - Create a deadline task.

This command creates a deadline task and adds it to the list of tasks to be done. The task type is indicated as `D`.

Example of usage:

`deadline <description> /by dd/MM/yyyy hhmm`

Expected outcome:

Creates a new deadline task and adds it to the list of tasks.

```
> deadline Homework 1 /by 1/12/2019 2359
____________________________________________________________
added: [D][ ] Homework 1 (by: 1/12/2019 2359)
____________________________________________________________
```

### `event` - Create an event task.

This command creates an event and adds it to the list of tasks to be done. The task type is indicated as `E`.

Example of usage:

`event <description> /at dd/MM/yyyy hhmm-hhmm`

Expected outcome:

Creates a new event and adds it to the list of tasks.

```
> event interview /at 1/12/2019 1800-1900
____________________________________________________________
added: [E][ ] interview (at: 1/12/2019 1800-1900)
____________________________________________________________
```

### `mark` - Mark a task as completed.

This command marks an event as completed, indicated with an `X`.

Example of usage:

`mark <event number>`

Expected outcome:

Marks a task as done.

```
> mark 2
____________________________________________________________
Nice! I've marked this task as done:
[D][X] Homework 1 (by: 1/12/2019 2359)
____________________________________________________________
```

### `unmark` - Unmarks task completion. 

This command marks an event as not completed.

Example of usage:

`unmark <event number>`

Expected outcome:

Marks a task as not done.

```
> unmark 2
____________________________________________________________
OK, I've marked this task as not done yet:
[D][ ] Homework 1 (by: 1/12/2019 2359)
____________________________________________________________
```

### `list` - List tasks.

This command shows the list of tasks that have been created.

Example of usage:

`list`

Expected outcome:

Show all created tasks.

```
> list
____________________________________________________________
Here are the tasks in your list
1. [T][ ] asd
2. [D][X] Homework 1 (by: 1/12/2019 2359)
3. [E][ ] interview (at: 1/12/2019 1800-1900)
____________________________________________________________
```

### `find` - Find tasks

This command finds a created task based on their description.

Example of usage:

`find <description to match>`

Expected outcome:

List out all tasks that matches the description to search for.

```
> find Homework
____________________________________________________________
Here are the tasks that were found:
2. [D][X] Homework 1 (by: 1/12/2019 2359)
____________________________________________________________
```

### `delete` - Remove unwanted tasks

This command removes a created task.

Example of usage:

`delete <event number>`

Expected outcome:

Deletes the `n`-th task in the list.

```
> delete 2
____________________________________________________________
Noted. I've removed this task:
[D][X] Homework 1 (by: 1/12/2019 2359)
Now you have 2 tasks in the list.
____________________________________________________________
```

### `help` - Show list of commands

This command shows a list of commands available in Duke.

Example of usage:

`help`

Expected outcome:

List out all tasks that matches the description to search for.

```
> help
____________________________________________________________
List of commands for Duke: 
todo <description>
deadline <description> /by <date>
event <description> /at <date>
mark <task number>
unmark <task number>
list
find <description>
delete <task number>
help
bye
____________________________________________________________
```

### `bye` - Exit

This command stops the program after some time.

Example of usage:

`bye`

Expected outcome:

The program exits shortly.

```
> bye
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```