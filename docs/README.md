# User Guide
Duke is a **desktop app for tracking tasks, 
optimized for use via a Command Line Interface** (CLI).
As Duke is designed to be use in a CLI, this 
application would benefit users who are able to type fast.

---

## Quick Start
1. Ensure you have Java 11 or above installed on your Computer
2. Download the latest JAR file from GitHub.
3. Copy the file to the folder you want to use as the home folder for your Duke application. 
Note that the data file would also be created in the same folder.
4. Open the CLI at where you have placed the jar file and run it using the command
   `java -jar ip.jar`
   <br>![Run Sample](https://raw.githubusercontent.com/froststein/ip/master/images/runSample.PNG)
5. Type the commands in the box and press enter to execute it. 
Here are some commands you can try:
   - `list` : List all current tasks.
   - `todo Read Book` : Adds a todo task of reading book.
   - `delete 1` : Deletes the first task from list.

---

## Features

>  **Notes about command format**:
>- Words in `UPPER_CASE` are parameters to be supplied by the user.
    e.g. in `todo TASK_DESCRIPTION`, `TASK_DESCRIPTION` is a parameter which can be used as 
   `todo Read Book`.
>- Parameters order must follow the command format.
   e.g. given the command `deadline TASK_DESCRIPTION /by TASKDATE`, the command must be
   `deadline Assignment /by 2022-02-20`


#### Feature list:
* Listing all tasks: `list`
* Adding a new task:
   * Todo task: `todo`
   * Deadline task: `deadline`
   * Event task: `event`
* Marking a task as done: `mark`
* Marking a task as not done: `unmark`
* Deleting a task: `delete`
* Finding a task: `find`
* Exiting the program: `bye`

---

### Feature - Adding a new Task

To add a task, the task has to be one of the following types, `todo`,`deadline` or `event`.
Each task types has its own format. For the `todo` task it does not have a date associated with it
, whilst `deadline` and `event` do.

* Todo
  * Format: `todo TASK_DESCRIPTION`
  * Example: `todo Programming Homework`
    <br>![Adding Todo Task](https://raw.githubusercontent.com/froststein/ip/master/images/todoCommand.PNG)
  

* Deadline
  * Format: `deadline TASK_DESCRIPTION /by DATE`
    * The `DATE` **must be in the format of** `yyyy-mm-dd` 
    (e.g. For the date `25th of Feb, 2022` you would key in `2022-02-25`)
  * Example: `deadline Programming Assignment /by 2022-02-25`
    <br>![Adding Deadline Task](https://raw.githubusercontent.com/froststein/ip/master/images/deadlineCommand.PNG)   


* Event
  * Format: `event TASK_DESCRIPTION /at DATE`
    * The `DATE` **must be in the format of** `yyyy-mm-dd`
      (e.g. For the date `26th of Feb, 2022` you would key in `2022-02-26`)
  * Example: `event Class Outing /at 2022-02-26`
    <br>![Adding Event Task](https://raw.githubusercontent.com/froststein/ip/master/images/eventCommand.PNG)


---

### Feature - Marking a Task as done
Marks a task as done.
* Format : `mark TASK_NUMBER`
  * The `TASK_NUMBER` refers to the number shown when listing task. 
    * The index can be retrieved with the `list` command. 
    * The index **must be a positive number** 1,2,3,...
* Example : `mark 2` - Marks the 2nd task in the list as done.
  <br>![Marking Task](https://raw.githubusercontent.com/froststein/ip/master/images/markCommand.PNG)

---

### Feature - Marking a Task as not done
Marks a task as not done.
* Format : `unmark TASK_NUMBER`
    * The `TASK_NUMBER` refers to the number shown when listing task.
        * The index can be retrieved with the `list` command.
        * The index **must be a positive number** 1,2,3,...
* Example : `unmark 2` - Marks the 2nd task in the list as done.
  <br>![Unmarking Task](https://raw.githubusercontent.com/froststein/ip/master/images/unmarkCommand.PNG)


---

### Feature - Deleting a Task
Delete a task from list of task.
* Format: `delete TASK_NUMBER`
  * The `TASK_NUMBER` refers to the number shown when listing task.
    * The index can be retrieved with the `list` command.
    * The index **must be a positive number** 1,2,3,...
* Example: `delete 3` - Deletes the 3rd task in the list.
  <br>![Deleting Task](https://raw.githubusercontent.com/froststein/ip/master/images/deleteCommand.PNG)

---

### Feature - Finding a Task
Finds all task from the list of task that matches the given keyword.
* Format: `find KEYWORD`
  * The `KEYWORD` refers to the search parameter. If the keyword is part 
  of any task's description, it would return part as the result. 
  * The keyword is **case sensitive** such that `Morning` would only match with `Morning` and not `morning`.
* Example: `find Programming`
  <br>![Finding Task](https://raw.githubusercontent.com/froststein/ip/master/images/findCommand.PNG)


---

### Feature - Listing all current Tasks
List all the current Tasks that Duke is tracking.
* Format: `list`
  <br>![Listing Task](https://raw.githubusercontent.com/froststein/ip/master/images/listCommand.PNG)


---

### Feature - Closing the program
Closes the Duke program.
* Format: `bye`


---

## Saving the data
Tasks data are saved in the hard disk automatically after any command that changes the Task.
There is no need to save it manually.

---
## Editing the data file
Duke data are saved as a CSV file `[JAR file location]/localStorage.csv`. 
Advanced users are welcome to update data directly by editing the data file.

> **Caution**: If your changes to the data file makes its format invalid, 
> Duke will discard that row of task and not load it into the program.
---

## Command Summary
|      **Action**       | **Format, Examples**                                                                  |
|:---------------------:|:--------------------------------------------------------------------------------------|
|         List          | `list`                                                                                |
|     Add todo task     | `todo TASK_DESCRIPTION` <br/>(e.g. `todo Borrow Book`)                                |
|   Add deadline task   | `deadline TASK_DESCRIPTION /by DATE`<br/>(e.g. `deadline Return book /by 2022-02-25`) |
|    Add event task     | `event TASK_DESCRIPTION /at DATE`<br/>(e.g. `event Project Meeting /at 2022-02-26`)   |
|   Mark task as done   | `mark TASK_NUMBER`<br/>(e.g. `mark 2`)                                                |
| Mark task as not done | `unmark TASK_NUMBER`<br/>(e.g. `unmark 2`)                                            |
|        Delete         | `delete TASK_NUMBER`<br/>(e.g. `delete 2`)                                            |
|         Find          | `find KEYWORD`<br/>(e.g. `find Book`)                                                 |
