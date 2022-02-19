# User Guide for Bob

Bob is a __Personal Assistant Chatbot__ that will help you track your tasks.

---
## Features 
- Add tasks to be tracked
- Get an overview of your tasks
- Search through your multitude of tasks using keywords
- Check off tasks that have been completed

---
## Quickstart
1. Ensure that you have Java 11 or above installed in your Computer.
2. Download the latest Bob.jar from [releases](https://github.com/1szheng/ip/releases).
3. Copy the file to the folder you want to use as the _home folder_ for Bob.
4. Open a terminal in that folder and launch Bob by entering `java -jar Bob.jar`.
5. Type your commands to Bob and press Enter to execute.
6. Refer to the [Usage](#Usage) below for details of each command.

---
## Usage

### Adding a generic task : `todo`

Adds a generic task (with no timing requirements) to the tracking list using the keyword `todo`.

Format: `todo TASKDETAILS`

Examples:
- `todo Buy groceries`

Expected output:
```
____________________________________________________________
[T][ ] Buy groceries
Task successfully added~!
The number of tasks amounts to: 11
____________________________________________________________
```

### Adding a task with a deadline : `deadline`

Adds a task with a deadline to the tracking list using the keyword `deadline`.
The due date is delimited by a `/by` and in the YYYY-MM-DD format. 

Format: `deadline TASKDETAILS /by DUEDATE`

Examples:
- `deadline Pay bills /by  2022-03-01`

Expected output:
```
____________________________________________________________
[D][ ] Pay bills (by: Tue, Mar 01 2022)
Task successfully added~!
The number of tasks amounts to: 2
____________________________________________________________
```

### Adding a dated event : `event`

Adds an event that occurs on a certain date using the keyword `event`.
The event date is delimited by a `/at` and in the YYYY-MM-DD format.

Format: `event EVENTDETAILS /at EVENTDATE`

Examples:
- `event Buy new phone during 3.3 /at 2022-03-03`

Expected output:
```
____________________________________________________________
[E][ ] Buy new phone during 3.3 (at: Thu, Mar 03 2022)
Task successfully added~!
The number of tasks amounts to: 3
____________________________________________________________
```

### Displaying all tasks : `list`

Obtain the list of currently tracked tasks and events using the keyword `list`

Format: `list`

Expected output:
```
____________________________________________________________
Task list:
1.  [T][ ] Buy groceries
2.  [D][ ] Pay bills (by: Tue, Mar 01 2022)
3.  [E][ ] Buy new phone during 3.3 (at: Thu, Mar 03 2022)
____________________________________________________________
```

### Check or uncheck a task : `mark`/`unmark`

Mark a task as complete by using the `mark` keyword, or incomplete using the `unmark` keyword.
The keyword uses the task's id so make sure to check the `list` first.

Format: `mark TASKID`, `unmark TASKID`

Examples:
- `mark 1`

Expected output:
```
____________________________________________________________
The following task has been checked off:
[T][X] Buy groceries
____________________________________________________________
```

### Locating a task by detail : `find`

Find a task by searching for its details by using the keyword `find`. 
The search string should appear after the keyword and is case sensitive.

Format: `find SEARCHSTRING`

Examples:
- `search Buy`

Expected output:
```
____________________________________________________________
The following tasks matches your search:
1.  [T][X] Buy groceries
3.  [E][ ] Buy new phone during 3.3 (at: Thu, Mar 03 2022)
____________________________________________________________
```

### Deleting a task : `delete`

Delete a task from the tracking list using the keyword `delete` followed by the task id.

Format: `delete TASKID`

Examples:
- `delete 1`

Expected output:
```
____________________________________________________________
The following task has been deleted:
[T][X] Buy groceries
The number of tasks amounts to: 2
____________________________________________________________
```

### Exiting the program : `bye`

Exits the program by saying `bye` to Bob.

Format: `bye`

### Saving the data

The task list data are saved in your hard disk automatically after each command that issues a modification. 
No manual saving is required. 

### Editing the data file

The task data are saved in a text file `[JAR file location]/data/data.txt`. 
Advanced users are welcome to update data by directly editing the text file. 

**Warning**: if changes made are incompatible, Bob will discard all entries starting from the corrupted data.
Saved data will be overwritten from there if any further modifications are made through Bob.

---
## FAQ
**Q**: How do I transfer my data to another Computer?

**A**: Download Bob in the other computer and overwrite the empty data file it creates with the
file that contains the data of the Bob home folder that you wish to import.

---
## Command Summary
| Action | Format                                                                              |
|--------|-------------------------------------------------------------------------------------|
| Add    | `todo TASK` <br/>`deadline TASK /by yyyy-mm-dd` <br/>`event DETAILS /at yyyy-mm-dd` |
| List   | `list`                                                                              |
| Mark   | `mark TASKID`<br/>`unmark TASKID`                                                   |
| Find   | `find SEARCHSTRING`                                                                 |
| Delete | `delete TASKID`                                                                     |
| Exit   | `bye`                                                                               |

