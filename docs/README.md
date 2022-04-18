# User Guide

Robit is a desktop application that can help you keep track of and manage your outstanding tasks.

## Features

Robit supports three types of tasks, each with their own attributes:
* **Todos** are the most basic form of task, and only consist of a short description of the task.
* **Deadlines** contain a short description of the task, as well as a due date or time.
* **Events** contain a short description of the event, as well as the event date or time.

Robit also supports saving and loading of task data from a storage file on your device. Your changes are automatically saved after every command, so don't worry about losing your work!

---
## Commands

> **About the command format:**
> 
>* All user-supplied arguments can contain whitespace.
>* Arguments given in `UPPER_CASE` are to be user-supplied.
>* Items in `[square brackets]` are optional and may be omitted.
>* Commands that do not exactly follow the required command format will be rejected.

### Adding tasks: `todo`, `deadline`, `event`

Format:
* `todo TASK_DESCRIPTION`
* `deadline TASK_DESCRIPTION /by TASK_DEADLINE`
* `event EVENT_DESCRIPTION /at EVENT_TIME`

Adds a todo, deadline, or event task to the task list respectively.

> **About task-related dates:**
> 
> `TASK_DEADLINE` and `EVENT_TIME` parameters accept any user-provided input. However, Robit is smart enough to understand dates and times given in `d/M/yyyy [hh:mm]` format (e.g. `25/12/2022 13:00` for 25 Dec 2022 at 1pm).

Examples:
* `todo clean my room`
* `deadline luminus quiz /by Sunday morning`
* `event project meeting /at 22/2/2022 14:00`

### Listing tasks: `list`

Format: `list [DATE]`

Displays a list of tasks.

* If used, `DATE` must be specified in `d/M/yyyy` format (e.g. `1/2/2022` for 1 Feb 2022).
* If `DATE` is not specified, `list` returns all tasks currently being kept track of by Robit.
* If `DATE` is specified, `list` returns only the tasks which fall on the specified date.

> **Note:**
> 
> This functionality is only compatible with task dates that Robit understands!

### Searching for tasks: `find`

Format: `find SEARCH_PATTERN`

Searches for tasks with description containing the provided search pattern as a substring.

Example: `find luminus quiz`

### Marking and unmarking tasks: `mark`, `unmark`

Format:
* `mark TASK_NUMBER`
* `unmark TASK_NUMBER`

Sets the completion status of a task in the task list.

* `TASK_NUMBER` refers to the index of the task shown in the task list (obtained via the output of the `list` or `find` commands).
* `mark` sets the task as completed while `unmark` sets it as uncompleted.

### Deleting a task: `delete`

Format: `delete TASK_NUMBER`

Deletes a task from the task list.

* `TASK_NUMBER` refers to the index of the task shown in the task list (obtained via the output of the `list` or `find` commands).

> **WARNING:**
> 
> Deleting a task could cause the task number of all subsequent tasks to change! If you run multiple delete commands in quick succession, you could delete tasks other than the ones you were intending to. When in doubt, verify with `list` first before proceeding.

### Exiting the program: `bye`

Format: `bye`

Terminates the program.

---
## For advanced users

### Manually editing the data file

Robit's task data is stored in `data/duke.txt` (relative to where you ran the program). Each task is stored on a new line, in the following format:

```text
(T/D/E)`(0/1)`TASK_DESCRIPTION`TIME
```
For example, ``D`1`luminus quiz`Sunday`` represents a completed Deadline task with description `luminus quiz` and task deadline `Sunday`.

Robit will ignore any lines containing invalid task data when reading from the data file, and notify you of their corresponding line numbers.

---
## FAQ:

**Q:** How do I reset the application?
<br>
**A:** Simply delete the storage file at `data/duke.txt`. A new, blank storage file will be created when Robit is next launched.

**Q:** How do I transfer my task data to another machine?
<br>
**A:** Run the application on the second machine. Then overwrite the newly-created storage file with the one transferred over from the previous machine.

---
## Command summary

| Action         | Format |
|----------------|---|
| Add            | `todo TASK_DESCRIPTION`<br>`deadline TASK_DESCRIPTION /by TASK_DEADLINE`<br>`event EVENT_DESCRIPTION /at EVENT_TIME` |
| List           | `list [DATE]` |
| Search         | `find SEARCH_PATTERN ` |
| Mark<br>Unmark | `mark TASK_NUMBER`<br>`unmark TASK_NUMBER` |
| Delete         | `delete TASK_NUMBER` |
| Exit           | `bye` |
