# User Guide

## Table of Contents

* [Quick start](#quick-start)
* [Features and Usage](#features-and-usage)
    * [Adding a task](#adding-a-task)
        * [Todo: `todo`](#todo-task-todo)
        * [Deadline: `deadline`](#deadline-task-deadline)
        * [Event: `event`](#event-task-event)
    * [Listing all tasks: `list`](#listing-all-tasks-list)
    * [Marking a task as done: `mark`](#marking-a-task-as-done-mark)
    * [Marking a task as not done: `unmark`](#marking-a-task-as-not-done-unmark)
    * [Deleting a task: `delete`](#deleting-a-task-delete)
    * [Finding tasks by keyword: `find`](#finding-tasks-by-keyword-find)
    * [Exiting the app: `bye`](#exiting-the-app-bye)
    * [Automatic saving of data](#automatic-saving-of-data)

## Quick start

1. Ensure you have **Java `11` or above** installed in your Computer.
2. Download the latest `Duke.jar` from [here](https://github.com/TianaiYan/ip/).
3. Copy the file to the folder you want to use as the *home folder* for your Duke.
4. Open a terminal window and type `java -jar Duke.jar`.
5. Type the command below the greeting and press <kbd>Enter</kbd> to execute it. Some example commands you can try:
6. Refer to the [Usage](#Usage) below for details of each command.

## Feature and Usage
❗️**Notes:**
* Commands are case-sensitive. For example, `mark` and `Mark` are not equal. 
* A space is required after each command. For exmaple, `mark1` is not a correct command format. Instead, `mark 1' is correct command format.


### Adding a task
* ### Todo Task: `todo`
  Adds a todo task to the task list.

  A todo task contains only a description.<br><br>
  **Format:** `todo <description>` <br><br>
  **Example with expected outcomes:**
  ```
  todo read book
  -----------------------------------------
   Got it. I've added this task:
      [T][ ] read book
   Now you have 1 tasks in the list.
  -----------------------------------------
  ```
* ### Deadline Task: `deadline`
  Adds a deadline task to the task list. <br>
  A deadline task contains a description and a time that the task need to be done before that.<br><br>
  **Format:** `deadline <description> /by <time/date>` <br><br>
  **Example with expected outcomes:**
  ```
  deadline return book /by Saturday
  -----------------------------------------
   Got it. I've added this task:
      [D][ ] return book (by: Saturday)
   Now you have 2 tasks in the list.
  -----------------------------------------
  ```
* ### Event Task: `event`
  Adds a event task to the task list. <br>
  A event task contains a description and a time of when the task begins and ends.<br><br>
  **Format:** `event <description> /at <time>` <br><br>
  **Example with expected outcomes:**
  ```
  event watch Anya's interview /at Feb 25 5pm
  -----------------------------------------
   Got it. I've added this task:
      [E][ ] watch Anya's interview (at: Feb 25 5pm)
   Now you have 3 tasks in the list.
  -----------------------------------------
  ```
  
### Listing all tasks: `list`
List down all the tasks that you have added.

**Format:** `list` 

**Example with expected outcomes:**
```
list
-----------------------------------------
 Here are the tasks in your list:
 1.[T][ ] read book
 2.[D][ ] return book (by: Saturday)
 3.[E][ ] watch Anya's interview (at: Feb 25 5pm)
-----------------------------------------
```
| Examples       | Attribute Name   | Description                                                    |
|----------------|------------------|----------------------------------------------------------------|
| 1.             | task index       | The task index in the task list                                |
| [T]            | task type        | [T] for Todo Task; [D] for Deadline Task; [E] for Event Task   |
| read book      | task description | the content of the task                                        |
| (by: Saturday) | task date/time   | (for deadline and event task only) date/time given to the task |

### Marking a task as done: `mark`
Mark a task as finished.

**Format:** `mark <task index>`

**Example with expected outcomes:**
```
mark 2
-----------------------------------------
 Nice! I've marked this task as done:
	[D][X] return book (by: Saturday)
-----------------------------------------
```

### Marking a task as not done: `unmark`
Mark a task as not finished yet.

**Format:** `unmark <task index>`

**Example with expected outcomes:**
```
unmark 2
-----------------------------------------
 OK, I've marked this task as not done yet:
	[D][ ] return book (by: Saturday)
-----------------------------------------
```

### Deleting a task: `delete`
Delete a task from list.

**Format:** `delete <task index>`

**Example with expected outcomes:**
```
delete 2
-----------------------------------------
 Noted. I've removed this task: 
	[D][ ] return book (by: Saturday)
 Now you have 2 tasks in the list.
-----------------------------------------
```
Now if you run 'list' again, it will shows:
```
-----------------------------------------
 Here are the tasks in your list:
 1.[T][ ] read book
 2.[E][ ] watch Anya's interview (at: Feb 25 5pm)
-----------------------------------------
```

### Finding tasks by keyword: `find`
Search tasks in the list which contains the keyword.

**Format:** `find <keyword>`

**Example with expected outcomes:**

Now we have the following tasks:
```
list
-----------------------------------------
 Here are the tasks in your list:
 1.[T][ ] read book
 2.[E][ ] watch Anya's interview (at: Feb 25 5pm)
 3.[D][X] return book (by: Saturday)
 4.[T][ ] buy book
-----------------------------------------
```
If you would like to find task with the word 'book':
```
find book
-----------------------------------------
 Here are the matching tasks in your list:
 1.[T][ ] read book
 3.[D][X] return book (by: Saturday)
 4.[T][ ] buy books
 You have 3 tasks contains "book" in the list.
-----------------------------------------
```

### Exiting the app: `bye`
Exit the program.

**Format:** `bye`

**Example with expected outcomes:**
```
bye
-----------------------------------------
 Bye. Hope to see you again soon!
-----------------------------------------
```

### Automatic saving of data
When you start Duke for the first time, a txt file named `data.txt` will be created to save your task.

When you say `bye` to the program, Duke will automatically save any changes you made to the txt file.
