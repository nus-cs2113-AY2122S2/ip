# User Guide
The Duke chat-bot is a **desktop app for managing tasks, optimized for use 
via a Command Line Interface** (CLI). If you can type fast, 
Duke can get your contact management tasks done faster than 
traditional GUI apps.

## Quick start
1. Ensure you have `Java 11` or above installed on your computer
2. Download the latest `ip.jar` from [here](https://github.com/wli-linda/ip/releases)
3. Copy the file to the folder you want to use as the _home folder_ for your Duke chat-bot
4. Open a command window in that folder
5. Run the command `java -jar ip.jar` to start the app
6. Refer to the [Features](#features) below to try out some commands!

## Features 

### Adding a task: `todo`, `deadline`, or `event`

Adds a task to the list of tasks. This could be a todo, deadline, or event.

Format:
* `todo TASK_DESCRIPTION`
* `deadline TASK_DESCRIPTION /by TASK_TIME`
* `event TASK_DESCRIPTION /at TASK_TIME`

Examples:
* `todo submit iP UG`
* `deadline ship iP /by 3/3/2022`
* `event SE tutorial /at 10AM`


### Listing all tasks: `list`

Lists all tasks in the current task list.

Format: `list`


### Removing a task: `delete`

Removes a task from the task list.

Format: `delete TASK_NUMBER`
* The task number must be in the task list, i.e. displayed when the command `list` is entered

Example: `list` followed by `delete 1` deletes the first task in the task list

### Finding a task: `find`

Finds a task with the relevant keyword in the task list.

Format: `find TASK_KEYWORD`

Example: 

### Exiting the program: `bye`

Exits the program.

Format: `bye`

## Command Summary
| Action       | Format, Examples                                                                    |
|--------------|-------------------------------------------------------------------------------------|
| Add todo     | `todo TASK_DESCRIPTION` <br/> e.g. `todo submit iP UG`                              |
| Add deadline | `deadline TASK_DESCRIPTION /by TASK_TIME`<br/> e.g. `deadline ship iP /by 3/3/2022` |
| Add event    | `event TASK_DESCRIPTION /at TASK_TIME`<br/> e.g. `event SE tutorial /at 10AM`       |
| List         | `list`                                                                              |
| Delete       | `delete TASK_NUMBER`<br/> e.g. `delete 2`                                           |
| Find         | `find TASK_KEYWORD` <br/> e.g. `find iP`                                            |
| Exit         | `bye`                                                                               |