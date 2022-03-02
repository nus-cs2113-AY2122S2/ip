# User Guide

This is a product named _Duke_, a Personal Assistant Chatbot that helps a person to keep track of various tasks.

      ____        _        
     |  _ \ _   _| | _____ 
     | | | | | | | |/ / _ \
     | |_| | |_| |   <  __/
     |____/ \__,_|_|\_\___|

## Quick Start
1. Please make sure you have _Java 11_ or above installed in your computer. The link to _Java 11_ installer is [here](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html) .
2. Download the _v2.0 ip.jar_ from [here](https://github.com/Yzkkk/ip/releases).
3. Copy the jar file into an empty folder.
4. Open Command Prompt on your laptop, and go to the working directory where the file is saved.
5. Run the command  `java -jar ip.jar` to start the program.
6. You can now enter different commands.

Note: The program will not stop until you have entered `bye`. See [exit program](#exit-program---bye).


## Features
_Duke_ is able to 
- add tasks
- mark tasks as done
- list all tasks
- delete tasks
- search tasks using keyword
- exit program
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

Sample input: 
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

For tasks with deadline, you can input `deadline` to add a new task.

Format: `deadline [TASK_DESCRIPTION] /by [DUE_DATE]`

Sample input:
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

Sample input:
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

For tasks that you have completed, and you wish to mark it as done, you can input `mark` followed by its task number.

Format: `mark [TASK_NUMBER]`

Sample input:
```
mark 2
```

Expected output:
```
Nice! I've marked this task as done:
  (D)(X) lumiNUS quiz (by: Fri 4pm)
```

### List all tasks - `list`
If you wish to list all existing tasks, you can input `list`.

Format: `list`

Sample input:
```
list
```

Expected output:
```
Here are the task(s) in your list:
1.  (T)( ) visit new theme park
2.  (D)(X) lumiNUS quiz (by: Fri 4pm)
3.  (E)( ) project meeting (at: Tue 2-4pm)
```

### Delete tasks - `delete`

If you wish to remove existing task on task list, you can input `delete`.

Format: `delete [TASK_NUMBER]`

Sample input:
```
delete 2
```

Expected output:
```
Noted. I've removed this task:
  (D)(X) lumiNUS quiz (by: Fri 4pm)
Now you have 2 task(s) in the list.
```

### Search tasks using keyword - `find`
If you wish to search for a task from your task list quickly, you can input `find`.

Format: `find [KEYWORD]`

Sample input:
```
find proj
```

Expected output:
```
Here are the matching task(s) in your list:
1.  (E)( ) project meeting (at: Tue 2-4pm)
```

### Exit program - `bye`
If you wish to end the session, besides closing your terminal, you can also input `bye`.

Format: `bye`

Sample input:
```
bye
```

Expected output:
```
Bye. Hope to see you again soon! :)
```

### Save and load tasks

The most updated task list will automatically be saved in the hard disk, and the saved data will be loaded when the program starts. This is to ensure that even if you close the program, you will not lose any data, and you can continue with the existing list of tasks when you start a new session. 