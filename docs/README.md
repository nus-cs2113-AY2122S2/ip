# User Guide

This is a product named _Duke_, a Personal Assistant Chatbot that helps a person to keep track of various tasks.

      ____        _        
     |  _ \ _   _| | _____ 
     | | | | | | | |/ / _ \
     | |_| | |_| |   <  __/
     |____/ \__,_|_|\_\___|

## Quick Start
1. Please make sure you have _Java 11_ or above installed in your computer.
2.

## Features
_Duke_ is able to 
- add tasks
- mark tasks as done
- list all tasks
- delete tasks
- search tasks using keyword
- save tasks and load the saved tasks when program starts

### Add tasks - `todo`, `deadline`, `event`

You are able to add 3 types of tasks to your task list.
They are:
- Todo
- Deadline
- Event

#### `todo`
For general tasks _without_ date/time attached to it, you can input `todo` to add a new task.

Format: `todo [TASK_DESCRIPTION]`

Example input: 
```
todo visit new theme park
```

Expected output: 
```
Got it. I've added this task:
  (T)( ) visit new theme park
Now you have 1 task(s) in the list.
```

#### `deadline`

For tasks _with_ deadline, you can input `deadline` to add a new task.

Format: `deadline [TASK_DESCRIPTION] /by [DUE_DATE]`

Example input:
```
deadline lumiNUS quiz /by Fri 4pm
```

Expected output:
```
Got it. I've added this task:
  (D)( ) lumiNUS quiz (by: Fri 4pm)
Now you have 2 task(s) in the list.
```

#### `event`
For tasks that start at a specific time and/or ends at a specific time, you can input `event` to add a new task.

Format: `event [TASK_DESCRIPTION] /at [EVENT_DATE]`

Example input:
```
event project meeting /at Tue 2-4pm
```

Expected output:
```
Got it. I've added this task:
  (E)( ) project meeting (at: Tue 2-4pm)
Now you have 3 task(s) in the list.
```

### Mark tasks - `mark`

For "Todo" tasks that you have completed, and you wish to mark it as done, you can input `mark` to mark the task as completed.

Format: `mark [TASK_NUMBER]`

Example input:
```
mark 1
```

Expected output:
```
Nice! I've marked this task as done:
  (T)(X) visit new theme park
```
### List all tasks - `list`
If you wish to list all existing tasks, you can input `list`.

Format: `list`

Example input:
```
list
```

Expected output:
```
Here are the task(s) in your list:
1.  (T)(X) visit new theme park
2.  (D)( ) lumiNUS quiz (by: Fri 4pm)
3.  (E)( ) project meeting (at: Tue 2-4pm)
```

### Delete tasks - `delete`

If you wish to remove existing task on task list, you can input `delete`.

Format: `delete [TASK_NUMBER]`

Example input:
```
delete 1
```

Expected output:
```
Noted. I've removed this task:
  (T)(X) visit new theme park
Now you have 2 task(s) in the list.
```

### Search tasks using keyword - `find`
If you wish to search for a task from your task list quickly, you can input `find`.

Format: `find [KEYWORD]`

Example input:
```
find proj
```

Expected output:
```
Here are the matching task(s) in your list:
1.  (E)( ) project meeting (at: Tue 2-4pm)
```

### Save and load tasks

The most updated task list will automatically be saved in the hard disk, and the saved data will be loaded when the program starts. This is to ensure that even if you close the program, you will not lose any data, and you can continue with the existing list of tasks when you start a new session. 