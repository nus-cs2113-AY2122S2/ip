# User Guide for Aeon

Aeon is a personalized chat bot that helps tracks tasks for the user through a Command Line Interface (CLI).

---
## Quickstart
1. Ensure that you have __Java 11__ or above installed in your Computer.
2. Download the latest Aeon.jar from [releases](https://github.com/yithien/ip/releases).
3. Copy the file to the folder you want to use as the _home folder_ for Aeon.
4. Open a terminal with its working directory in the same folder as where Aeon is stored.
5. Run Aeon by entering `java -jar Aeon.jar`.
6. Type your commands to Aeon and press Enter to execute.
7. Refer to the [Features](#Features) section below for some examples.

## Features 

### 'todo' - Add a new Todo task

Adds a new task without a specified timing.
The keyword `todo` is used followed by a short description of the task.

Format: `todo TASKDESC`

Example of usage:

`todo Revise for upcoming midterms`

Expected outcome:

The Todo task will be added to the list of tasks.

```
[T] [ ] Revise for upcoming midterms

Total: 1 task(s) in the list!
Task added!
```


### `deadline` - Add a new Deadline task

Adds a new task with a specified due date. 
The keyword `deadline` is used followed by a short description and a delimiter of `/by`,
followed by the due date, in YYYY-MM-DD format.

Format: `deadline TASKDESC /by DUEDATE`

Example of usage:

`deadline Finish CS2113 iP /by 2022-02-18`

Expected outcome:

The Deadline task will be added to the list of tasks.

```
[D] [ ] Finish CS2113 iP (by: Feb 18 2022))

Total: 1 task(s) in the list!
Task added!
```

### `event` - Add a new Event task

Adds a new task with a specified time of occurrence.
The keyword `event` is used followed by a short description and a delimiter of `/at`,
followed by the date of the event , in YYYY-MM-DD format.

Format: `event TASKDESC /at EVENTDATE`

Example of usage:

`event Attend family gathering /at 20-02-2022`

Expected outcome:

The Event task will be added to the list of tasks.

```
[E] [ ] Attend family gathering (at: Feb 20 2022)

Total: 2 task(s) in the list!
Task added!
```

### `list` - List out all currently saved tasks

Prints out the list of currently tracked tasks using the keyword `list`.
Each entry shows its current index in the list, followed by the type of task, whether it has been marked as Done
and the description of the task.

Format: `list`

Example of usage:

`list`

Expected outcome (assuming 3 tasks in list):
```
1. [D] [ ] Finish CS2113 iP (by: Feb 18 2022)

2. [E] [ ] Attend family gathering (at: Feb 20 2022)

3. [T] [ ] Revise for upcoming midterms
```

### `delete` - Deletes a task

Deletes a specific task that currently exists in the list using its index. Users may choose to `list` out the
tasks first before deleting to verify its index.
After deleting a task, the task is shown before it is removed from the list.

Format: `delete INDEX`

Example of usage:

`delete 1`

Expected outcome (assuming same example from `list`):
```
[D] [ ] Finish CS2113 iP (by: Feb 18 2022)

Total: 2 task(s) in the list!
Task deleted!
```

### `find` - Look for a task

Use search terms to look for all tasks that contain a particular keyword. A list of tasks will be
displayed containing the keyword.
- The keyword is case-sensitive! 
- Partial matches on the search term will also be returned. 

Format: `find KEYWORD`

Example of usage:

`find midterms`

Expected outcome (assuming same example from `list`):
```
[T] [ ] Revise for upcoming midterms
```

### `mark`/`unmark` - Mark or unmark a task as Done/Not Done

Mark or unmark a specific task as Done or Not Done using its current index in the list.
Users are advised to use `list` to verify the task's index before attempting to mark or unmark it.

- A character `X` will be used to denote the task as Done. This can be seen by using the `list` command.

Format: `mark INDEX / unmark INDEX`

Example of usage:

`mark 2`

Expected outcome (assuming same example from `list`):
```
[T] [X] Revise for upcoming midterms

Congrats on completing this task!
```
### `bye` - Exits the program

Close the chatbot by using the keyword `bye`

Format: `bye`

Example of usage:

`bye`

Expected outcome:
```
Bye. Hope to see you again soon!
____________________________________________________________
```
---

## FAQ
__Q__: How do I transfer my data to another Computer?

__A__: Download Aeon in the other computer and overwrite the empty data file it creates with the
file that contains the data of the Aeon home folder that you wish to import.

---
## Command Summary

- All dates are to be in YYYY-MM-DD format

| Action | Format                                                                               |
|--------|--------------------------------------------------------------------------------------|
| Add    | `todo TASK` <br/>`deadline TASKDESC /by DUEDATE` <br/>`event TASKDESC /at EVENTDATE` |
| List   | `list`                                                                               |
| Delete | `delete INDEX`                                                                       |
| Find   | `find KEYWORD`                                                                       |
| Mark   | `mark INDEX`<br/>`unmark INDEX`                                                      |
| Exit   | `bye`                                                                                |
