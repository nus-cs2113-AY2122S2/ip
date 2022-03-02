# User Guide for EM
Em is a program that helps to keep track of all tasks created by the user via a Command Line Interface (CLI).

## Features
### Create a New Task
Create a new task that is either a deadline, an event, or a todo, and add it 
to EM's list. Em's list keep track of all other tasks that you have previously created.

### Mark Tasks as Complete/Incomplete
EM is able to update your task completion status in EM's list accordingly to what you have told her. 

### Display Tasks 
Display all the tasks that you have created and saved in EM's list. 

### Delete an Existing Task 
Remove a task that you no longer wish to see in EM's list. 

### Search for an Existing Task
Enter a keyword search and EM will show you all the tasks that matches your keyword search. 

### Automatic Saving of Task Data to Local em.Storage
Whenever you create, update or delete a task, EM will automatically save these changes into a file that is stored
locally on your system. The file can be found in "../database/database.txt".

---

## Quick Start

1. Ensure that you have installed Java 11 or above in your computer.
2. Download the JAR file for EM from *[here](https://github.com/emilysim00/ip/releases/latest)*.
3. Create a new folder in your computer and copy `ip.jar` file into it.
4. Open your terminal/command prompt and set the path of the new folder as your working directory.
5. Start EM with `java -jar ip.jar` in the terminal/command prompt.

---

## Usage

### `todo` - Create a Todo task

Creates a new Todo task. A Todo task only requires task description and does not have a deadline or time. 

Syntax: `todo <task description>`

| Parameters           | Description                      |
|----------------------|----------------------------------|
| `<task description>` | Information about the Todo task. |
**Example**
```
> todo read a book
```
**Expected Outcome**
```
------------------------------------------------------------
Got it. I've added this task:
[T][ ] read a book
Now you have 1 tasks in the list.
------------------------------------------------------------
```
A new todo task "read a book" has been successfully created and added to EM's list.

### `event` - Create an Event task

Creates a new Event task. An Event task requires both task description and a date and time.


Syntax: `event <task description> /at <date time>`

| Parameter            | Description                                                                                       |
|----------------------|---------------------------------------------------------------------------------------------------|
| `<task description>` | Information about the Event task.                                                                 |
| `<date time>`        | When the event is set to occur. Format: YYYY-MM-DD HHmm, where HHmm is the time in 24-hour format. |
**Example**
```
> event meet kelly @ finefoods /at 2021-04-11 1400
```
**Expected Outcome**
```
------------------------------------------------------------
Got it. I've added this task:
[E][ ] meet kelly @ finefoods  (at: Apr-11-2021 2:00PM)
Now you have 2 tasks in the list.
------------------------------------------------------------
```
A new event task "meet kelly @ finefoods" has been successfully created and added to EM's list.The 24-hour format is being converted to 12-hour format. 

### `deadline` - Create a Deadline task

Creates a new deadline task. A Deadline task requires task description and a deadline. The deadline consists of both date and time. 

Syntax: `deadline <task description> /by <date time>`

| Parameter             | Description                                                                                          |
|-----------------------|------------------------------------------------------------------------------------------------------|
| `<task description>`  | Information about the Deadline task.                                                                 |
| `<date time>` | When the deadline is set. Format: YYYY-MM-DD HHmm, where HHmm is the time in 24-hour format. |
**Example**
```
> deadline assignment 3 /by 2022-01-02 1330
```
**Expected Outcome**
```
------------------------------------------------------------
Got it. I've added this task:
[D][ ] assignment 3  (by: Jan-2-2022 1:30PM)
Now you have 3 tasks in the list.
------------------------------------------------------------
```
A new deadline task "assignment 3" with a deadline on "Jan-2-2022 1:30PM" has been successfully 
created and added to EM's list.The 24-hour format is being converted to 12-hour format. 

### `mark` - Mark a task as completed

Mark a task in EM's list as completed.

Syntax: `mark <task number>`


| Parameter      | Description                                                                       |
|----------------|-----------------------------------------------------------------------------------|
| `<task number>` | The numbering of the task stored in EM's list that you want to mark as completed. |

**Example**

Assume, your task list contains the following tasks (obtain from `list` command):
```
------------------------------------------------------------
Here are the tasks in your list:
1. [T][ ] read a book
2. [E][ ] meet kelly @ finefoods  (at: Apr-11-2021 2:00PM)
3. [D][ ] assignment 3  (by: Jan-2-2022 1:30PM)
------------------------------------------------------------
```
To mark the second task as completed, enter the following command:
```
> mark 2
```
**Expected Outcome**
```
------------------------------------------------------------
OK, I've marked this task as done:
[E][X] meet kelly @ finefoods  (at: Apr-11-2021 2:00PM)
------------------------------------------------------------
```
If you run `list` command again, you will observe that the marked task (second task) is being 
updated from `[ ]` to `[X]`, indicating the task as being completed.
```
------------------------------------------------------------
Here are the tasks in your list:
1. [T][ ] read a book
2. [E][X] meet kelly @ finefoods  (at: Apr-11-2021 2:00PM)
3. [D][ ] assignment 3  (by: Jan-2-2022 1:30PM)
------------------------------------------------------------
```

### `unmark` - Unmarked a task as incomplete

Mark a task in EM's list as incomplete.

Syntax: `unmark <task number>`


| Parameter      | Description                                                                          |
|----------------|--------------------------------------------------------------------------------------|
| `<task number>` | The numbering of the task stored in EM's list that you want to unmark as incomplete. |

**Example**

Initially, your task list contains the following tasks (obtain from `list` command):
```
------------------------------------------------------------
Here are the tasks in your list:
1. [T][ ] read a book
2. [E][X] meet kelly @ finefoods  (at: Apr-11-2021 2:00PM)
3. [D][ ] assignment 3  (by: Jan-2-2022 1:30PM)
------------------------------------------------------------
```
To mark the second task as incomplete, enter the following command:
```
> unmark 2
```
**Expected Outcome**
```
------------------------------------------------------------
OK, I've marked this task as not done yet:
[E][ ] meet kelly @ finefoods  (at: Apr-11-2021 2:00PM)
------------------------------------------------------------
```
If you run `list` command again, you will observe that the previously marked task (second task) is being
updated from `[X]` to `[ ]`, indicating the task as being incomplete.
```
------------------------------------------------------------
Here are the tasks in your list:
1. [T][ ] read a book
2. [E][ ] meet kelly @ finefoods  (at: Apr-11-2021 2:00PM)
3. [D][ ] assignment 3  (by: Jan-2-2022 1:30PM)
------------------------------------------------------------
```

### `list` - Show all tasks

List down all the tasks that have been created and stored in EM's list.

Syntax: `list`

**Example**
```
> list
```
**Expected Outcome**
```
------------------------------------------------------------
Here are the tasks in your list:
1. [T][ ] read a book
2. [E][ ] meet kelly @ finefoods  (at: Apr-11-2021 2:00PM)
3. [D][ ] assignment 3  (by: Jan-2-2022 1:30PM)
------------------------------------------------------------
```

### `delete` - Delete a task 

Delete a task stored in EM's list.

Syntax: `delete <task number>`


| Parameter      | Description                                                            |
|----------------|------------------------------------------------------------------------|
| `<task number>` | The numbering of the task stored in EM's list that you want to delete. |

**Example**

Assume your current task list contains the following tasks (obtain from `list` command):
```
------------------------------------------------------------
Here are the tasks in your list:
1. [T][ ] read a book
2. [E][ ] meet kelly @ finefoods  (at: Apr-11-2021 2:00PM)
3. [D][ ] assignment 3  (by: Jan-2-2022 1:30PM)
------------------------------------------------------------
```
To delete the second task, you can enter the following command:
```
> delete 2
```
**Expected Outcome**
```
------------------------------------------------------------
Noted. I've removed this task:
[E][ ] meet kelly @ finefoods (at: Apr-11-2021 2:00PM)
Now you have 2 tasks in the list.
------------------------------------------------------------
```
If you run `list` command again, you will observe that the deleted task have been removed from EM's list.
```
------------------------------------------------------------
Here are the tasks in your list:
1. [T][ ] read a book
2. [D][ ] assignment 3  (by: Jan-2-2022 1:30PM)
------------------------------------------------------------
```

### `find` - Search for task(s) in the list

Searches EM's list to find tasks that match the keyword search. The keyword search can be a word/phrase. 
The search function is case-sensitive and will match on the texts as listed in EM's list. 

Syntax: `delete <task number>`


| Parameter      | Description                                                            |
|----------------|------------------------------------------------------------------------|
| `<task number>` | The numbering of the task stored in EM's list that you want to delete. |

**Example**

Assume your current task list contains the following tasks (obtain from `list` command):
```
------------------------------------------------------------
Here are the tasks in your list:
1. [T][ ] read a book
2. [D][ ] assignment 3  (by: Jan-2-2022 1:30PM)
3. [T][ ] buy book
4. [E][ ] borrow book  (at: Nov-11-2022 3:00PM)
------------------------------------------------------------
```
To find task(s) that contains the word "book", you can enter the following command:
```
> find book
```
**Expected Outcome**
```
------------------------------------------------------------
Remainder: Search is case sensitive
Here are the matching tasks in your list:
1. [T][ ] read a book
2. [T][ ] buy book
3. [E][ ] borrow book  (at: Nov-11-2022 3:00PM)
------------------------------------------------------------
```
From EM's list there are only 3 tasks that contains the word "book". 

### `bye` - Exit the program

Exits the application.

**Syntax**
```
bye
```
**Expected Outcome**
```
------------------------------------------------------------
Bye. Hope to see you again soon!
------------------------------------------------------------

Process finished with exit code 0
```
---
## File storage information
When EM is run for the first time, a directory and a file would be created to store all the task information.
"database/database.txt" will be created in the same directory as where ip.jar file is being saved. 

On subsequent run of EM, the data stored in database.txt will be loaded into the program. EM's list will store 
tasks information of previous session as well as the current session. 


### Caution

During the booting of EM, the database file wil be loaded. If modifications have been 
made to the file and is in invalid format, EM will fail to load the data into the program.
As a result, you would have to delete the compromised file.
---

## Command summary
| Action                  | Format, Examples                                                                                  |
|-------------------------|---------------------------------------------------------------------------------------------------|
| Create a todo task      | `todo <description>`<br/>e.g., `todo read book`                                                   |
| Create a event task     | `event <description> /at <date time>`<br/>e.g., `event meet kelly /at 2022-02-02 1230`            |
| Create a deadline task  | `deadline <description> /by <date time>`<br/>e.g., `deadline assignment 3 /by 2022-04-01 2359` |
| Delete a task           | `delete <task number>`<br/>e.g., `delete 2`                                                       |
| Find task               | `find <keyword>`<br/>e.g., `find book`                                                            |
| Mark task as completed  | `mark <task number>`<br/>e.g., `mark 2`<br/>                                                      |
| Mark task as incomplete | `unmark <task number>`<br/>e.g., `unmark 2`                                                       |
| List all task           | `list`                                                                                            |
| Exit                    | `bye`                                                                                             |
