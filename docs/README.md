# User Guide

## Features 

### Feature 1: Add Tasks 

There are 3 types of tasks: **Todo, Event, and Deadline**.

- Create a Todo item by entering `todo DESCRIPTION`
  
  - e.g. todo Write User Guide
  
- Create an Event item by entering `event DESCRIPTION /at EVENTDATE`
  
  - e.g. event CS2113 Tutorial /at 04032022
  
- Create a Deadline item by entering `deadline DESCRIPTION /by DUEDATETIME`
  
  - e.g. Submit IP /by 04032022 0000

### Feature 2: List all Tasks

Print a list of all tasks and their statuses.

`list`

### Feature 3: Mark/Unmark Tasks

Mark tasks as completed as you complete them!

- `mark INDEX`
  - e.g. `mark 1`
  - e.g. `unmark 2`


### Feature 4: Delete Tasks

Remove a task that shouldn't be there!

`delete INDEX`

### Feature 5: List all Tasks

Print a list of all tasks and their statuses.

`list`

## Usage

`todo Write User Guide`
`event CS2113 Tutorial /at 04032022`
`Submit IP /by 04032022 0000`

```Expected Outcome
____________________________________________________________
Here are the tasks in your list:
1. [T][ ] Write User Guide
2. [E][ ] CS2113 Tutorial (at: 03 Mar 2022)
3. [D][ ] Submit IP (by: 04 Mar 2022 00:00)
____________________________________________________________
```

`mark 2`
```
____________________________________________________________
Nice! I've marked this task as done:
[E][X] CS2113 Tutorial (at: 03 Mar 2022)
____________________________________________________________
```

`delete 2`
```
Noted. I've removed this task:
[E][X] CS2113 Tutorial (at: 03 Mar 2022)
Now you have 2 tasks in the list
____________________________________________________________
```

