# User Guide

Welcome to User Guide for Domo the chat bot.

* [Quick start](#quick-start)
* [Features](#features)
  * [Add Todo task](#feature---add-todo-task-todo)
  * [Add Deadline task](#feature---add-deadline-task-deadline)
  * [Add Event task](#feature---add-event-task-event)
  * [List tasks](#feature---list-tasks-list)
  * [Mark task as done](#feature---mark-task-as-done-mark)
  * [Mark task as undone](#feature---unmark-tasks-unmark)
  * [Delete a task](#feature---delete-a-task-delete)
  * [Find tasks](#feature---find-tasks-find)
  * [Exiting the program](#feature---exiting-the-program-bye)

## Quick start
1. Check that you are running `Java 11`.
2. Download the latest [jar file](https://github.com/laiisaac/ip/releases/tag/A-Release).
3. Move the file to the appropriate folder location you want.
4. Open the terminal/command line and run the file with `java -jar IP.jar`
5. Refer to the available features below and enjoy the chat bot.

## Features 

#### Notes about command format 

* All commands are case-sensitive, including descriptions and parameters of the commands
* Required parameters are in `[PARAMETER]` format.
e.g. `todo [DESCRIPTION]`

Example:
Adding a Todo task with "Cook breakfast" as the parameter

`todo Cook breakfast`

### Feature - Add Todo task: `todo`

Adds a Todo task to the task list.

Format: `todo [DESCRIPTION]`

Example of usage: 

`todo Study for quiz`

### Feature - Add Deadline task: `deadline`

Adds a Deadline task with a required parameter `DEADLINE` to the task list.

Format: `deadline [DESCRIPTION] /by [DEADLINE]`

Example of usage:

`deadline buy groceries /by tomorrow`

### Feature - Add Event task: `event`

Adds an Event task with a required parameter `TIME_PERIOD` to the task list.

Format: `event [DESCRIPTION] /at [TIME_PERIOD]`

Example of usage:

`event CS2113 Lecture /at Fri 4-6pm`

### Feature - List tasks: `list`

Lists all current tasks in the task list.

Format: `list`

### Feature - Mark task as done: `mark`

Marks a specific task in the task list as done. `TASK_NUMBER` is a mandatory parameter.

Format: `mark [TASK_NUMBER]`

Example of usage: `mark 3`

### Feature - Unmark tasks: `unmark`

Marks a specific task in the task list as undone. `TASK_NUMBER` is a mandatory parameter.

Format: `unmark [TASK_NUMBER]`

Example of usage: `unmark 3`

### Feature - Delete a task: `delete`

Deletes a specific task specified by the user. `TASK_NUMBER` is a mandatory parameter.

Format: `delete [TASK_NUMBER]`

Example of usage: `delete 4`

### Feature - Find tasks: `find`

Find tasks with specified keyword(s). `CONTENT` is a mandatory parameter and can be made up of multiple words.

Format: `find [CONTENT]`

Example of usage: 

`find CS2113 Tutorial`

### Feature - Exiting the program: `bye`

Exits the program by passing the command `bye`.

Format: `bye`

### Saving the data

Changes to your tasks are saved automatically when you exit the program. There is no need for manual changes to the save file. 
