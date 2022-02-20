# User Guide
Duke is a *desktop app for tracking tasks, 
optimized for use via a Command Line Interface* (CLI).
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
    e.g. in `todo TASKDESCRIPTION`, `TASKDESCRIPTION` is a parameter which can be used as 
   `todo Read Book`.
>- Parameters order must follow the command format.
   e.g. given the command `deadline TASKDESCRIPTION /by TASKDATE`, the command must be
   `deadline Assignment /by 2022-02-20`

####Feature list:
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
  * Format: `todo TASKDESCRIPTION`
  * Example: `todo Programming Homework`
    <br>![Adding Todo Task](https://raw.githubusercontent.com/froststein/ip/master/images/todoCommand.PNG)


* Deadline
  * Format: `deadline TASKDESCRIPTION /by TASKDATE`
  * Example: `deadline Programming Assignment /by 2022-02-25`
    <br>![Adding Deadline Task](https://raw.githubusercontent.com/froststein/ip/master/images/deadlineCommand.PNG)   


* Event
  * Format: `event TASKDESCRIPTION /at TASKDATE`
  * Example: `event Class Outing /at 2022-02-26`
    <br>![Adding Event Task](https://raw.githubusercontent.com/froststein/ip/master/images/eventCommand.PNG)


---

