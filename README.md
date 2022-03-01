# User Guide



## Outline
* [Outline](#outline)
* [Quick Start](#quick-start)
* [Features](#features)
	* [Feature: Add task](#feature-add-task)
	* [Feature: List all tasks](#feature-list-all-tasks)
	* [Feature: Mark task](#feature-mark-task)
	* [Feature: Find task(s)](#feature-find-tasks)
	* [Feature: Delete tasks](#feature-delete-tasks)
	* [Feature: Delete tasks](#feature-delete-tasks)
* [Usage](#usage)
	* [todo - add a todo task](#todo---add-a-todo-task)
	* [event - add a event task](#event---add-a-event-task)
	* [deadline - add a deadline task](#deadline---add-a-deadline-task)
	* [list - list all tasks](#list---list-all-tasks)
	* [mark - mark a task as completed](#mark---mark-a-task-as-completed)
	* [unmark - mark a task as uncompleted](#unmark---mark-a-task-as-uncompleted)
	* [find - search task(s) with keywords](#find---search-tasks-with-keywords)
	* [delete - delete a task from the list](#delete---delete-a-task-from-the-list)
	* [save - save the task records i](#save---save-the-task-records-i)
	* [bye - Exit the program](#bye---exit-the-program)

## Quick Start

1. Ensure Java 11 is installed and applied. 

   A quick check in CMD is as follows: 

   ```sh
   java -v
2. Download the latest `ip.jar` from [Duke](https://github.com/Ch40gRv1-Mu/ip/releases/tag/A-Jar). A command line is as follows:

   ```sh
    wget "https://github.com/Ch40gRv1-Mu/ip/releases/download/A-Jar/ip.jar"
   ```

3. Run the Duke:

   ```sh
   java -jar ip.jar
   ```

## Features 

### Feature: Add task

Task is generally organized in the following components:

There are three possible task types that can be added:

- `todo`: A task without time constraint, denoted as `[T]`
- `deadline`: A task needs to be completed **before certain time**, denoted as  `[D]`
- `event`: A task needs to be complete **at certain time**, denoted as  `[E]`

Each task may have the following two states:

- `[X]`: A task is completed 
- `[ ]`: A task is uncompleted
Check [Usage-todo](#todo---add-a-todo-task),  [Usage-event](#event---add-a-event-task),  [Usage-deadline](#deadline---add-a-deadline-task), for further usage guide of adding tasks.



### Feature: List all tasks

Users can list of all tasks with one command. Check [Usage-list](#list---list-all-tasks) for further usage guide of list.



### Feature: Mark task

A task can be marked as completed or uncompleted. Check [Usage-mark](#mark---mark-a-task-as-completed), [Usage-unmark](#unmark---mark-a-task-as-uncompleted) for further usage guide of marking tasks.



### Feature: Find task(s)
Give users a way to find a task by searching for a keyword. Check [`Usage-find`](#feature-find-tasks) for further usage guide of marking tasks.



### Feature: Delete tasks

Duke supports for deleting tasks from the list. Check [`Usage-delete`](#delete---delete-a-task-from-the-list)

## Usage

### `todo` - add a todo task


This command helps you create a todo task!

##### Syntax:

`todo <task_name>`




### `event` - add a event task

This command helps you create an event!

##### Syntax:

```sh
event <task_name> /at <time_in_any_format>
```


### `deadline` - add a deadline task


This command helps you create a deadline!

##### Syntax:

`deadline <task_name> /by <YYYY-MM-DD>`


This should create a new deadline in your task list.



### `list` - list all tasks


This command helps you list down all the tasks in your list.

##### Syntax:

`list`



### `mark` - mark a task as completed


This command helps you mark a task as done.
##### syntax
```sh
mark <task_id>
```
This should mark the task with the specified task id as done, if the id exists. It will throw error if the task id does not exist.



### `unmark` - mark a task as uncompleted


This command helps you mark a task as uncompleted.
##### syntax
```sh
unmark <task_id>
```
This should mark the task with the specified task id as uncompleted, if the id exists. It will throw error if the task id does not exist.




### `find` - search task(s) with keywords


This command helps you search for a task with the specified keyword.
##### syntax
```sh
find <keyword>
```
A list of tasks will be displayed that contain the provided keyword.

### `delete` - delete a task from the list

This command helps you delete a task from your list.
##### syntax
```sh
delete <task_id>
```
This should mark the task with the specified task id as uncompleted, if the id exists. It will throw error if the task id does not exist.




### `save` - save the task records

This command save your current records so that the record can be revealed the next time you use Duke.
##### Syntax:
```sh
save
```




### `bye` - Exit the program

This command helps you quit the application.
##### Syntax:
```sh
bye
```

### `help` - Display the help summary
Simply type help and hit enter if you're not sure what syntax to use. After that, you'll see Duke's syntax overview!
##### Syntax:
```sh
help
```
