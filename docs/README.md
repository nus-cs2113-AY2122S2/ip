# User Guide
Duke is a chat-bot that manages your tasks. It is accessed via a Command Line Interface (CLI).

#Quick start
1. Ensure that you have **Java 11** installed on your system.
2. Download the latest [ip.jar](https://github.com/cheshire-doge/ip)
3. Open a **Command Line** and change your directory to the folder containing **ip.jar**
4. Enter `java -jar ip.jar` to start Duke
5. Refer to **Usage** below or enter `commands` into the Command Line to view commands.

## Features

### Storing of Tasks `ToDo`, `Deadline`, `Event`

**ToDos** are tasks that do not have a date they have to be accomplished by.

**Deadlines** are tasks that have a deadline they have to be accomplished by.

**Events** are tasks that run for a period of time, with a starting time and ending time.

### Mark/Unmark Tasks

Mark or Unmark tasks to remind you if you have completed them.

### Find Tasks

Search for tasks using specific keywords.

### Other Features!

**Delete**, **Check Date**, **List**

## Usage

### `todo` - Creates a ToDo

Example: 

`todo DESCRIPTION` - Adds a ToDo with DESCRIPTION onto the task list

```
todo Groceries
____________________________________________________________
Got it. I've added this task:
  [T][ ] Groceries
Now you have 1 tasks in the list.
```

### `deadline` - Creates a Deadline

`deadline DESCRIPTION /by DD/MM/YYYY time` - Adds a deadline with DESCRIPTION and deadline

```
deadline iP /by 03/03/2022 2359
____________________________________________________________
Got it. I've added this task:
  [D][ ] iP (by: Mar 3 2022 2359)
Now you have 6 tasks in the list.
```

### `event` - Creates an Event

`event DESCRIPTION /at DD/MM/YYYY time /to DD/MM/YYYY time` - Adds an event with DESCRIPTION, start time and end time.

```
event Project Meeting /at 05/03/2022 2000 /to 05/03/2022 2200
____________________________________________________________
Got it. I've added this task:
  [E][ ] Project Meeting (at: Mar 5 2022 2000 to Mar 5 2022 2200)
Now you have 3 tasks in the list.
```

### `list` - Lists out all tasks

`list` - All tasks will be listed down

### `mark`/`unmark` `NUMBER` - marks/unmarks task NUMBER in the list

`mark 3` - Task 3 in the list would be marked

### `find KEYWORD` - searches the list based on KEYWORD

`find project` - Lists down every Task in the list containing `project`

### `check date DD/MM/YYYY` - checks the list if any task is due or happens on that day
`check date 04/03/2021` - Checks if any task happens or is due on 04/03/2021

### `delete NUMBER` - deletes task NUMBER in the list
`delete 1` - Deletes the first task on the list

### `bye` - closes the bot

### `commands` - lists down all commands