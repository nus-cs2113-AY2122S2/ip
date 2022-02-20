# User Guide
Duke is a desktop app, that allows you to track all your tasks.
As Duke is designed to be use in the Command Line (CLI), 
this application would benefit users who are able to type fast.
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
   - `delete 1` : Deletes the 1st task in the list.
---

### Features

>**Notes about command format**:
>- Words in `UPPER_CASE` are parameters to be supplied by the user.
    e.g. in `todo TASKNAME`, `TASKNAME` is a parameter which can be used as 
   `todo Read Book`.
>- Parameters order must follow the command format.
   e.g. given the command `deadline TASKNAME /by TASKDATE`, the command must be
   `deadline Assignment /by 2022-02-20`

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
### Feature - Listing all tasks


Description of the feature.

## Usage

### `Keyword` - Describe action

Describe the action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```
