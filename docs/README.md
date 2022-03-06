# User Guide

## Overview
Duke is a Command Line Interface application designed for desktop use.

## Quick Start
1. Install Java, version 11 or above.
2. Download the latest release as a JAR file from www.github.com/matheril/ip/releases.
3. Navigate to the directory where you downloaded the .jar to and run it with the command `java -jar`.
4. Run commands by typing them into the terminal and pressing enter.
The directory Duke is run from is referred to as `$DUKE`.

> ###Command format:
> `UPPERCASE` - represent user-supplied parameters.
> 
> `lowercase` - represent built-in commands.
> 
> `/by` `/at` - separate task description from their timings.
> 
> For example:
> 
> `deadline finish assignment /by 2022-06-03` 
> 
> `deadline` is a built-in command and will be displayed in lowercase in this documentation.
> 
> `finish assignment` and `2022-06-03` are user-supplied parameters and will be displayed in UPPERCASE in this documentation.
> 
## Features 

### Add task
Adds a task.
Tasks can be `todo`, `deadline` or `event`.

- Todo
  - `todo DESCRIPTION`
```
todo read book
_____________________________________
Got it. I've added this task:
  [T][ ] read book
Now you have 1 task in the list.
_____________________________________
```
- Deadline
  - `deadline DESCRIPTION /by DATE`
    - `DATE` - YYYY-MM-DD format
```
deadline return book /by 2022-06-03
_____________________________________
Got it. I've added this task:
  [D][ ] return book (by: Jun 3 2022)
Now you have 1 task in the list.
_____________________________________
```
- Event
  - `event DESCRIPTION /at DATE`
      - `DATE` - YYYY-MM-DD format
```
event company meeting /at 2022-06-03
_____________________________________
Got it. I've added this task:
  [E][ ] company meeting (at: Jun 3 2022)
Now you have 2 tasks in the list.
_____________________________________
```

### List tasks
Lists tasks in a readable format.
- `list`
```list
_____________________________________
Here are the tasks in your list:
1.[D][ ] return book (by: Jun 3 2022)
2.[E][ ] company meeting (at: Jun 3 2022)
3.[T][ ] read book
_____________________________________
```


### Mark task
Marks tasks as done.
- `mark TASKINDEX`
  - `TASKINDEX` is the index of the task shown by `list`.
```
mark 1
_____________________________________
Nice! I've marked this task as done:
[D][X] return book (by: Jun 3 2022)
_____________________________________
```
### Unmark task
Marks a task as undone.
- `unmark TASKINDEX`
  - `TASKINDEX` is the index of the task shown by `list`.
```
unmark 1
_____________________________________
OK, I've marked this task as not done yet:
[D][ ] return book (by: Jun 3 2022)
_____________________________________
```

### Find tasks
Finds tasks with descriptions that match the supplied keyword.
- `find KEYWORD`
  - `KEYWORD` A keyword to search for. Tasks with `DESCRIPTION` containing `KEYWORD` are listed.
```
find book
_____________________________________
Here are the matching tasks in your list:
1.[D][ ] return book (by: Jun 3 2022)
2.[T][ ] read book
_____________________________________
```

### Delete task
Deletes a task.
- `delete TASKINDEX`
  - `TASKINDEX` is index of the task shown by `list`.
```
delete 1
_____________________________________
Noted. I've removed this task:
[D][ ] return book (by: Jun 3 2022)
Now you have 2 tasks in the list.
_____________________________________
```

### Exit
Exit Duke.

- `bye`
### Saves tasks
Duke saves tasks to `$DUKE/data/duke.txt`.

Exiting the program without using `bye` will not save tasks to the save file.