
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

---

### Feature: List all tasks

Users can list of all tasks with one command. Check [Usage-list](#list---list-all-tasks) for further usage guide of list.

---

### Feature: Mark task

A task can be marked as completed or uncompleted. Check [Usage-mark](#mark---mark-a-task-as-completed), [Usage-unmark](#unmark---mark-a-task-as-uncompleted) for further usage guide of marking tasks.

---

### Feature: Find task(s)
Give users a way to find a task by searching for a keyword. Check [`Usage-find`](#feature-find-tasks) for further usage guide of marking tasks.

---

### Feature: Delete tasks

Duke supports for deleting tasks from the list. Check [`Usage-delete`](#delete---delete-a-task-from-the-list)

---
### Feature: Time
Duke support the following format of time input:
```sh
<YYYY-MM-DD> [<hh:mm>] 
<YYYY-MM-DD> [<hhmm>]
<DD/MM/YYYY> [<hh:mm>] 
<DD/MM/YYYY> [<hhmm>] 
```
Where the \<hh:mm\> and \<hhmm\> are optional.

## Usage

### `todo` - add a todo task


This command helps you create a todo task!

##### Syntax:

`todo <task_description>`

| Parameter | Description |
| --- | --- |
| `<task_description>` | Description about this todo task. |


##### Example:
```sh
> todo CS2113T quiz
```
##### Expected Output:
```sh
____________________________________________________________
Got it. I've added this task:
 [T][ ] CS2113T quiz
Now you have 1 tasks in the list.
____________________________________________________________
```
---
### `event` - add a event task

This command helps you create an event!

##### Syntax:

```sh
event <task_description> /at <start_time> [/to <end_time>]
```
| Parameter | Description |
| --- | --- |
| `<task_description>` | Description about this event task. |
| `<start_time>` | The time/start time of the event task, check [time](#feature-time) format |
| `<end_time>` | The end time of the event task, /to <end_time> is optional |

##### Example 1:
```sh
> event CS2113T tutorial /at 2022-03-03 1000
```
##### Expected Output 1:
```sh
____________________________________________________________
Got it. I've added this task:
 [E][ ] CS2113T tutorial (at: 2022-03-03 10:00)
Now you have 2 tasks in the list.
____________________________________________________________
```
##### Example 2:
```sh
> event CS2113T tutorial /at 03/10/2022 10:00
```
##### Expected Output 2:
```sh
____________________________________________________________
Got it. I've added this task:
 [E][ ] CS2113T tutorial (at: 2022-10-03 10:00)
Now you have 3 tasks in the list.
____________________________________________________________
```
##### Example 3:
```sh
> event CS2113T lecture /at 2022-03-04 16:00 /to 2022-03-04 18:00
```
##### Expected Output 3:
```sh
____________________________________________________________
Got it. I've added this task:
 [E][ ] CS2113T lecture (from: 2022-03-04 16:00 to 2022-03-04 18:00)
Now you have 4 tasks in the list.
____________________________________________________________
```
---

### `deadline` - add a deadline task


This command helps you create a deadline!

##### Syntax:

`deadline <task_name> /by <time>`
| Parameter | Description |
| --- | --- |
| `<task_description>` | Description about this event task. |
| `<time>` | The time of the event task, check [time](#feature-time) format |


This should create a new deadline in your task list.
##### Example:
```sh
> deadline CS2113T Exercise /by 2022-03-03 1000
```
##### Expected Output:
```sh
____________________________________________________________
Got it. I've added this task:
 [D][ ] CS2113T Exercise (by: 2022-03-03 10:00)
Now you have 5 tasks in the list.
____________________________________________________________
```

---

### `list` - list all tasks


This command helps you list down all the tasks in your list.

##### Syntax:

`list`

---
##### Example:
```sh
> list
```
##### Expected Output:
```sh
____________________________________________________________
1. [T][ ] CS2113T quiz
2. [E][ ] CS2113T tutorial (at: 2022-03-03 10:00)
3. [E][ ] CS2113T tutorial (at: 2022-10-03 10:00)
4. [E][ ] CS2113T lecture (from: 2022-03-04 16:00 to 2022-03-04 18:00)
5. [D][ ] CS2113T Exercise (by: 2022-03-03 10:00)
____________________________________________________________
```
### `mark` - mark a task as completed


This command helps you mark a task as done.
##### syntax
```sh
mark <task_id>
```
This should mark the task with the specified task id as done, if the id exists. It will throw error if the task id does not exist.
##### Example:
```sh
> mark 1
```
##### Expected Output:
```sh
____________________________________________________________
Nice! I've marked this task as done: 
 [T][X] CS2113T quiz
____________________________________________________________
```

---

### `unmark` - mark a task as uncompleted


This command helps you mark a task as uncompleted.
##### syntax
```sh
unmark <task_id>
```
This should mark the task with the specified task id as uncompleted, if the id exists. It will throw error if the task id does not exist.
##### Example:
```sh
> unmark 1
```
##### Expected Output:
```sh
____________________________________________________________
OK, I've marked this task as not done yet: 
 [T][ ] CS2113T quiz
____________________________________________________________
```

---

### `find` - search task(s) with keywords


This command helps you search for a task with the specified keyword.
##### syntax
```sh
find <keyword>
```
A list of tasks will be displayed that contain the provided keyword.
##### Example:
```sh
> find quiz
```
##### Expected Output:
```sh
____________________________________________________________
Here are the matching tasks in your list:
1. [T][ ] CS2113T quiz
____________________________________________________________
```
---
### `delete` - delete a task from the list

This command helps you delete a task from your list.
##### syntax
```sh
delete <task_id>
```
This should mark the task with the specified task id as uncompleted, if the id exists. It will throw error if the task id does not exist.
##### Example:
```sh
> delete 1
```
##### Expected Output:
```sh
____________________________________________________________
Noted. I've removed this task: 
[T][ ] CS2113T quiz
Now you have 4 tasks in the list.
____________________________________________________________
```
---

### `save` - save the task records

This command save your current records so that the record can be revealed the next time you use Duke.
##### Syntax:
```sh
save
```
##### Example:
```sh
> save
```
##### Expected Output:
```sh
____________________________________________________________
Received! Already saved the records for you! ^-^
____________________________________________________________
```
---


### `bye` - Exit the program

This command helps you quit the application.
##### Syntax:
```sh
bye
```
##### Example:
```sh
> bye
```
##### Expected Output:
```sh
____________________________________________________________
Do you want to save the modification? y/n
____________________________________________________________

> yes
____________________________________________________________
Received! Already saved the records for you! ^-^
____________________________________________________________

____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```
---
---
### `help` - Display the help summary
Simply type help and hit enter if you're not sure what syntax to use. After that, you'll see Duke's syntax overview!
##### Syntax:
```sh
help
```
##### Example:
```sh
> help
```
##### Expected Output:
```sh
____________________________________________________________
Usage: 

     bye                                                --exit Duke
     list                                               --list all tasks
     mark <i>                                           --set the ith task as completed (1-based)
     unmark <i>                                         --set the ith task as uncompleted (1-based)
     todo <task description>                            --add a todo type task
     deadline <task description> /by <YYYY-MM-DD> <hh:mm>  --add a deadline type task (hh:mm is optional, or simply HHMM is also accepted; <YYYY-MM-DD> can also be in <DD/MM/YYYY> format) 
     event <task_description> /at <YYYY-MM-DD> <HH:MM>  /to <YYYY-MM-DD> <HH:MM>  --add a event type task  
       -- event: (hh:mm is optional, or simply HHMM is also accepted; <YYYY-MM-DD> can also be in <DD/MM/YYYY> format)  
       -- event: (/to <YYYY-MM-DD> <HH:MM> is optional)
     delete <i>                                         --delete the ith task (1-based)
     save                                               --save the current records
     find <keyword>                                     --find task(s) containing the keyword
     help                                               --list guide for all operations
     
____________________________________________________________
```

## Command Summary
| **Command**   | **Format **                                                          | 
|:--------------|:---------------------------------------------------------------------------------|
| **list**      | `list`      |                                                                    
| **todo**      | `todo <task_description>`                               |    
| **event**     | `event <task_description> /at <start_time> [/to <end_time>]`    |     
| **deadline**  | `deadline <task_description> /by <time>` |     
| **mark**      | `mark <task_index>`                                |      
| **unmark**    | `unmark <task_index>`                                   |      
| **find**      | `find <string>`                                      |     
| **help**      | `help`                                                                           |     
| **delete**    | `delete <task_index>`                           |   
| **save**    | `save`                                                                       |      
| **bye**       | `bye`                                                                            | 

