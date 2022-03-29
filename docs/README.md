# User Guide

Duke is a CLI app for managing tasks that any user might need

* [Quick Start][1]
* [Features][2]
  * [Add task][3]
    * [Todo][11]
    * [Deadline][12]
    * [Event][13]
  * [delete task][4]
  * [list all tasks][5]
  * [mark task as done][6]
  * [unmark task as undone][7]
  * [find a task][8]
  * [exit][9]
* [command summary][10]
* 
## Quick Start
1. Ensure that you have [Java 11](https://www.oracle.com/java/technologies/downloads/#java11) or above installed in your Computer
2. Download the latest `Duke.jar` from [here]()
3. Copy the file to the folder you want to use as the home folder for Duke
4. Open the terminal in the folder which contains `Duke.jar` and run `java -jar Duke.jar`. The Startup should look like below:

```
____________________________________________________________
Hello! I'm Duke`
What can I do for you?`
____________________________________________________________
```
5. Type the commands in the terminal and press Enter to execute them. For example: typing `list` and pressing Enter will show you all the current tasks in the list.
This should show up empty if this is your first time opening Duke. Check [Command Summary][10] for example commands to use.

## Features
### Feature Add Task
A task is considered as either a `Todo`, `Deadline`, or `Event`.
- A `todo` task will contain the description of the task to be done.
- A `Deadline` task will contain the description of the task to be done **and** the due date for when the task must be done.
- An `Event` task similarly will contain the description of the task **and** the time for when the event will happen.

#### Adding a todo task:`todo`
create a new `todo` task and add to the list of tasks.

format: `todo "description of task"`

eg: 
* `todo buy bread` which creates a `todo` task to buy bread

#### Adding a deadline task:`deadline`
Create a new `deadline` task and add to the list of tasks.

format: `deadline "description of task" /by "time which task is due"`

eg:
* `deadline buy bread /by Monday` which creates a `deadline` task to buy bread by monday
* `deadline buy bread /by 2022-02-27` which creates a `deadline` task to buy bread by yyyy-mm-dd specified.
* `deadline buy bread /by 2022-02-27 5pm` which creates a `deadline` task to buy bread by yyyy-mm-dd specified at the time specified (5pm)
* `deadline buy bread /by 2022-02-27 17:00` which creates a `deadline` task to buy bread by yyyy-mm-dd specified at the time specified in 24 hours format (17:00).

#### Adding a event task:`event`
Create a new `event` task and add to the list of tasks.

format: `event "description of task" /at "time which task is due"`

eg:
* `event buy bread /at Monday` which creates a `event` task to buy bread by monday
* `event buy bread /at 2022-02-27` which creates a `event` task to buy bread by yyyy-mm-dd specified.
* `event buy bread /at 2022-02-27 5pm` which creates a `event` task to buy bread by yyyy-mm-dd specified at the time specified (5pm)
* `event buy bread /at 2022-02-27 17:00` which creates a `event` task to buy bread by yyyy-mm-dd specified at the time specified in 24 hours format (17:00).

### Feature Deleting a Task:`delete`
Deletes a task which was previously added from the list of tasks.

format: `delete "task number"`

eg:
* `delete 1` deletes the 1st task
* `delete 5` deletes the 5th task

**Note:** Deletion can only occur after tasks have been added to the list.

### Feature List All Tasks:`list`
List all current tasks added

format: `list`

eg:
* `list` will show all tasks currently added

```
$ list
____________________________________________________________
 here are the tasks in your list:
 1.[T][ ] buy bread
 2.[D][ ] buy bread (by: feb 27 2022 17:00)
 3.[E][ ] buy bread (at: feb 27 2022 17:00)
____________________________________________________________
```
### Feature Mark Task as Done:`mark`
Marks a specific task as completed

format: `mark "task number"`

eg:
* `mark 1` mark the 1st task
* `mark 5` mark the 5th task

```
$ mark 1
$ list
____________________________________________________________
 here are the tasks in your list:
 1.[T][X] buy bread
 2.[D][ ] buy bread (by: feb 27 2022 17:00)
 3.[E][ ] buy bread (at: feb 27 2022 17:00)
____________________________________________________________
```

**Note:** Marking a task can only occur after that task has been added to the list.
### Feature Unmark Task as Not Done:`unmark`
Unmarks a specific task as **not** completed

format: `unmark "task number"`

eg:
* `unmark 1` mark the 1st task
* `unmark 5` mark the 5th task

```
$ list
____________________________________________________________
 here are the tasks in your list:
 1.[T][X] buy bread
 2.[D][ ] buy bread (by: feb 27 2022 17:00)
 3.[E][ ] buy bread (at: feb 27 2022 17:00)
____________________________________________________________
$ unmark 1
$ list
____________________________________________________________
 here are the tasks in your list:
 1.[T][ ] buy bread
 2.[D][ ] buy bread (by: feb 27 2022 17:00)
 3.[E][ ] buy bread (at: feb 27 2022 17:00)
____________________________________________________________
```

**Note:** Unmarking a task can only occur after that task has been marked in the list.

### Feature Find a task:`find`
Find a task based on the task's description

format: `todo "keyword"`

eg:
* `find bread` which will find all tasks which has bread in their description
* `find buy bread` which will find all tasks which has buying bread in their description

### Feature Exit:`bye`
Exit out the program

format: `bye`

eg:
* `bye` exit

```
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```
## Command Summary
 |Action             | Format                             |
 |-------------------|------------------------------------|
 |**[Todo][11]**     | `todo "description"`               |
 |**[Deadline][12]** | `deadline "description" /by "time"`|
 |**[Event][13]**    | `event "description" /at "time"`   |
 |**[Delete][4]**    | `delete "index"`                   |
 |**[List][5]**      | `list`                             |
 |**[Mark][6]**      | `mark "index"`                     |
 |**[Unmark][7]**    | `unmark "index"`                   |
 |**[Find][8]**      | `find "keywords"`                  |
 |**[Exit][9]**      | `bye`                              |

[1]: https://nnythingy.github.io/ip/#quick-start
[2]: https://nnythingy.github.io/ip/#features
[3]: https://nnythingy.github.io/ip/#feature-add-task
[4]: https://nnythingy.github.io/ip/#feature-deleting-a-taskdelete
[5]: https://nnythingy.github.io/ip/#feature-list-all-taskslist
[6]: https://nnythingy.github.io/ip/#feature-mark-task-as-donemark
[7]: https://nnythingy.github.io/ip/#feature-unmark-task-as-not-doneunmark
[8]: https://nnythingy.github.io/ip/#feature-find-a-taskfind
[9]: https://nnythingy.github.io/ip/#feature-exitbye
[10]: https://nnythingy.github.io/ip/#command-summary
[11]: https://nnythingy.github.io/ip/#adding-a-todo-tasktodo
[12]: https://nnythingy.github.io/ip/#adding-a-deadline-taskdeadline
[13]: https:/nnythingy.github.io/ip//#adding-a-event-taskevent
