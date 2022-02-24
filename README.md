# Duke project by Yaxin

Duke is a personal assistant chatbot that helps a person to keep track of various tasks, optimized for use via a Command Line Interface. If you type fast, Duke can help you manage your tasks faster.

   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \____|_|\_\___|
   ```

# User Guide

Duke is a personal assistant chatbot that helps a person to keep track of various tasks, 
optimized for use via a **Command Line Interface**. 
If you type fast, Duke can help you manage your tasks faster.

You can also view the User Guide [here](https://yaxinjoy.github.io/ip/).

## Contents

- [Features](#Features)
  - [Add a task](#add-a-task-tasktype-taskname-)
  - [List all tasks](#list-all-tasks-list)
  - [Mark a task](#mark-a-task-mark)
  - [Unmark a task](#unmark-a-task-unmark)
  - [Find a task](#find-a-task-find)
  - [Delete a task](#delete-a-task-delete)
  - [Save data](#save-data-save)
  - [Exit the program](#exit-the-program-bye)
- [Command Summary](#command-summary)

## Features

All command types ignore the case.

### Add a task: `taskType taskName ...` 

Add a task based on its task type. Currently supporting types are `TODO`, `EVENT` and `DEADLINE`.

Format:

- TODO: `TODO taskName`. Example: `TODO read book`

- EVENT: `EVENT eventName /at eventTime`. Example: `EVENT project meeting /at tomorrow`

- DEADLINE: `DEADLINE deadlineName /by deadlineTime`. Example: `DEADLINE play the piano /by June 16`

Expected outcome (TODO example):


```
Got it. I've added this task: 
[T][ ] your task
Now you have x tasks in the list. 
```

### List all tasks: `LIST`

Show a list of current tasks in the format: `[taskType][isDone] task`

Format:

`LIST`

Expected outcome (example):

```
Here are the tasks in your list:
1. [T][ ] read book
2. [D][ ] return book (by: June 6th)
3. [E][ ] project meeting (at: Aug 6th 2-4pm)
4. [T][X] join sports club
```

### Mark a task: `MARK`

Mark certain task as done based on its index.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer and does not exceed the range as displayed.

Format:

`MARK taskIndex`

Expected outcome (example):

```
Nice! I've marked this task as done: 
[T][X] your task
```

### Unmark a task: `UNMARK`

Unmark certain task as not done based on its index.

- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer and does not exceed the range as displayed.

Format:

`UNMARK taskIndex`

Expected outcome (example):

```
OK, I've marked this task as not done yet:
[T][ ] your task
```

### Find a task: `FIND`

Find tasks containing a given keyword. The system will print all 

- The search is case-insensitive. 
e.g `Book` will match `book`
- Only task description is searched.

Format:

`FIND keyword`

Expected outcome (example):
1. Find results for keyword
```
Here are the matching tasks in your list:
1. [T][ ] your task1
2. [D][ ] your task2
...
```
2. Find no result for keyword
```
Here are the matching tasks in your list:
No result found. Try to change your keyword!
```

### Delete a task: `DELETE`

Delete certain task based on its index.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer and does not exceed the range as displayed.

Format:

`DELETE taskIndex`

Expected outcome (example):
```
Noted. I've removed this task: 
[T][ ] your task
Now you have x tasks in the list.
```

### Save data: `SAVE`

Save current data list into local file.
- It will display "Saved successfully" once finished.
- The system will automatically save data once the execution ends. 
User can also choose to save manually if she/ he wants.

Format:

`SAVE`

Expected outcome:

```
Saved successfully!
```

### Exit the program: `BYE`

Exit the project by typing `BYE`

Format:

`BYE`

Expected outcome:
```
Bye. Hope to see you again soon!
```

## Command Summary

| Action              | Format                                   |
|---------------------|------------------------------------------|
| Add `DEADLINE` task | `DEADLINE deadlineName /by deadlineTime` |
| Add `EVENT` task    | `EVENT eventName /at eventTime`          |
| Add `TODO` task     | `TODO taskName`                          |
| Delete              | `DELETE taskIndex`                       |
| Exit                | `BYE`                                    |
| Find                | `FIND keyword`                           |
| List                | `LIST`                                   |
| Mark                | `MARK taskIndex`                         |
| Save                | `SAVE`                                   |
| Unmark              | `UNMARK taskIndex`                       |



