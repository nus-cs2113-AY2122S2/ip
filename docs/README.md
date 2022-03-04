# Duke User Guide
**Duke** is a Personal Assistant ChatBot that helps you to **keep track of various tasks** such as todo, deadline
and event in daily life. This application allows users to manage their tasks via **Command Line Interface** (CLI)
.

## Quick Start
1. Ensure you have Java 11 or above installed in your computer.
2. Download the latest `duke.jar` from [here](https://github.com/yanjie1017/ip/releases/tag/A-Jar-v0.2)
3. Copy the file to the folder you want to use as _home folder_ for Duke
4. Run `java -jar duke.jar` at the _home folder_ at terminal.
5. Type the command in the terminal and press Enter to execute it.
6. Refer to the *Features* below for the details of different types of command.

## Features

### Listing all tasks: `list`
Shows a list of tasks that has been added.

* T: Todo task
* D: Deadline task
* E: Event task
* X: Task is done

Format: `list`
<br>
Expected output:
```
Here are the tasks in your list:
1. [T][X] read book
2. [D][ ] homework (by: 3 March)
3. [E][ ] exam (at: this Sunday)
4. [T][X] return book
5. [E][ ] birthday party (at: 1 April)
```


### Adding a Todo task: `todo`
Adds a Todo task to the list of tasks.
<br>
Format: `todo <task_description>`
<br>
Example: `todo read book`
<br>
Expected output:
```
Got it. I've added this task:
[T][ ] read book
Now you have 1 tasks in list.
```


### Adding a Deadline task: `deadline`
Adds a Deadline task to the list of tasks.
<br>
Format: `deadline <task> /by <datetime>`
<br>
Example: `deadline homework /by 3 March`
<br>
Expected output:
```
Got it. I've added this task:
[D][ ] homework (by: 3 March)
Now you have 2 tasks in list.
```


### Adding an Event task: `event`
Adds an Event task to the list of tasks.
<br>
Format: `event <task> /at <datetime>`
<br>
Example: `event exam /at this Sunday`
<br>
Expected output:
```
Got it. I've added this task:
[E][ ] exam (at: this Sunday)
Now you have 3 tasks in list.
```


### Deleting a task: `delete`
Deletes a task with task ID (task number shown in the list).
<br>
Format: `delete <task_id>`
<br>
Example: `delete 2`
<br>
Expected output:
```
Got it. I've added this task:
[T][ ] read book
Now you have 1 tasks in list.
```


### Marking a task as done: `mark`
Marks a task with the task ID (task number shown in the list) as done.
<br>
Format: `mark <task_id>`
<br>
Example: `mark 1`
<br>
Expected output:
```
Nice! I've marked this task as done:
[T][X] read book
```


### Marking a task as not done: `unmark`
Marks a task with the task ID (task number shown in the list) as not done.
<br>
Format: `unmark <task_id>`
<br>
Example: `unmark 3`
<br>
Expected output:
```
OK, I've marked this task as not done yet:
[E][ ] exam (at: this Sunday)
```


### Finding a task: `find`
Shows a list of tasks which contains the specific keyword in description.
<br>
Format: `find <keyword>`
<br>
Example: `find book`
<br>
Expected output:
```
Here are the matching tasks in your list:
1. [T][X] read book
2. [T][X] return book
```


### Exiting the application: `bye`
Exits the application. The current list of tasks will be saved.
<br>
Format: `bye`
<br>

## Caution
### Duke will not add duplicate task to the list.
<dl>
<dt>Definition of duplicate task</dt>
<dd>same task type and same task description</dd>
</dl>

Expected output:
```
This task already exists:
[T][X] return book
Now you have 5 tasks in list.
```

## Command summary

| Action   | Format                                       |
|----------|----------------------------------------------|
| List     | `list`                                       |
| Todo     | `todo <task_description>`                    |
| Deadline | `deadline <task_description> /by <datetime>` |
| Event    | `event <task_description> /at <datetime>`    |
| Delete   | `delete <task_index>`                        |
| Mark     | `mark <task_index>`                          |
| Unmark   | `unmark <task_index>`                        |
|  Find    | `find <keyword>`                             |
| Exit     | `bye`                                        |
