# User Guide

Duke is a program that helps you keep track of various things,
optimized for use via a Command Line Interface (CLI).

## Quick Start
1. Ensure that you have [Java 11](https://www.oracle.com/java/technologies/downloads/) or above installed in your computer.
2. Download the latest `Duke.jar` [here](https://github.com/Musfirahe0556596/ip/releases).
3. In your file explorer, create a new folder and copy the `Duke.jar` file there.
4. Open [Command Prompt](https://www.lifewire.com/command-prompt-2625840) from the directory you placed your `Duke.jar` file.
   1. On your file explorer file path bar, type in 'cmd'. Click [here](https://www.addictivetips.com/windows-tips/command-prompt-window-in-file-explorer-windows-10/) for steps.
5. Run Duke by typing in `java -jar Duke.jar` in your Command Prompt.

## Features 

### Create a task

Create a new task of different types such as a todo, deadline or event. The new task is added to your task list for you to track.

### Display all tasks

Displays all the tasks created on screen for viewing.

### Mark or unmark tasks

Mark task(s) as done once you have completed it. The unmark feature is also included in case you want to mark a task as incomplete.

### Find existing task

Search your task list for existing task(s) that matches your keyword search.

### Delete existing task

Remove an existing task from your task list.

### Save your data to local storage

There is no need to save manually as the Duke data are automatically saved into a file stored locally whenever you made modifications to your task list.

## Usage

### `todo` - Create a Todo task

Creates a new task that is a Todo. A Todo task does not have a deadline or time.

Syntax:
`todo <description>`

| Parameter       | Description               |
|-----------------|---------------------------|
| `<description>` | Description of your todo. |

Example of usage: 
```
todo shower
```

Expected outcome:

A todo task (indicated as 'T') is created and added to your task list with the following message:

```
_____________________________________________________
Task added:
	[T][ ] shower 
Now you have 1 tasks in your list!
_____________________________________________________
```


### `event` - Create an Event task

Creates a new task that is an event. An event includes the time of event.

Syntax:
`event <description> /at <date> <time>`

| Parameter       | Description                                                                                                                |
|-----------------|----------------------------------------------------------------------------------------------------------------------------|
| `<description>` | Description of your event.                                                                                                 |
| `<date>`        | When the event date begins. The format is: `yyyy-mm-dd` whereby `yyyy` is the year, `mm` is the month and `dd` is the day. |
| `<time>`        | What time the event starts. The format is: `kk` whereby `kk` is the time in 24 hours format ranging from 01 - 24.          |
Example of usage:

```
event housewarming /at 2022-03-01 10
```

Expected outcome:

An event (indicated as 'E') is created and added to your task list with the following message:

```
_____________________________________________________
Task added:
	[E][ ] housewarming  (at: Mar 1 2022 10AM)
Now you have 2 tasks in your list!
_____________________________________________________
```

### `deadline` - Create a Deadline task

Creates a new task that is a deadline. A deadline task includes the due deadline.

Syntax:
`deadline <description> /by <date> <time>`

| Parameter       | Description                                                                                                              |
|-----------------|--------------------------------------------------------------------------------------------------------------------------|
| `<description>` | Description of your deadline.                                                                                            |
| `<date>`        | When the deadline is due. The format is: `yyyy-mm-dd` whereby `yyyy` is the year, `mm` is the month and `dd` is the day. |
| `<time>`        | What time the deadline ends. The format is: `kk` whereby `kk` is the time in 24 hours format ranging from 01 - 24.       |

Example of usage:
```
deadline coding project /by 2022-05-11 11
```

Expected outcome:

A deadline (indicated as 'D') is created and added to your task list with the following message:

```
_____________________________________________________
Task added:
	[D][ ] coding project  (by: May 11 2022 11AM)
Now you have 3 tasks in your list!
_____________________________________________________
```

### `list` - Display all tasks

Displays all the tasks in your task list on screen.

Syntax:
`list`

Example of usage:

```
list
```

Expected outcome:

All tasks that were added into your task list is shown as below:

```
_____________________________________________________
1.[T][ ] shower 
2.[E][ ] housewarming  (at: Mar 1 2022 10AM)
3.[D][ ] coding project  (by: May 11 2022 11AM)
_____________________________________________________
```

### `mark` - Mark a task as completed

Mark a task as completed once you are done with the task.

Syntax:
`mark <task number>`

| Parameter       | Description                                    |
|-----------------|------------------------------------------------|
| `<task number>` | The task number you want to mark as completed. |

Example of usage:

Initially, your task list contains the following tasks:
```
_____________________________________________________
1.[T][ ] shower 
2.[E][ ] housewarming  (at: Mar 1 2022 10AM)
3.[D][ ] coding project  (by: May 11 2022 11AM)
_____________________________________________________
```
In order to mark task number 2 as completed, the following command is entered:
```
mark 2
```

Expected outcome:

Task number 2 in your task list is marked as completed as indicated by the 'X' symbol:

```
_____________________________________________________
Fantastic! This task is done:
[E][X] housewarming  (at: Mar 1 2022 10AM)
_____________________________________________________
```

If the `list` command is reentered, the task list is shown as follow:
```
_____________________________________________________
1.[T][ ] shower 
2.[E][X] housewarming  (at: Mar 1 2022 10AM)
3.[D][ ] coding project  (by: May 11 2022 11AM)
_____________________________________________________
```


### `unmark` - Mark a task as incomplete

Mark a task as incomplete if you are not done with it.

Syntax:
`unmark <task number>`

| Parameter       | Description                                     |
|-----------------|-------------------------------------------------|
| `<task number>` | The task number you want to mark as incomplete. |

Example of usage:

Initially, your task list contains the following tasks, whereby task 2 is marked as done:
```
_____________________________________________________
1.[T][ ] shower 
2.[E][X] housewarming  (at: Mar 1 2022 10AM)
3.[D][ ] coding project  (by: May 11 2022 11AM)
_____________________________________________________
```
In order to mark task number 2 as incomplete, the following command is entered:
```
unmark 2
```
Expected outcome:

Task number 2 in your task list is marked as incomplete and the 'X' symbol is removed:

```
_____________________________________________________
Uh oh! This task is undone:
[E][ ] housewarming  (at: Mar 1 2022 10AM)
_____________________________________________________
```

If the `list` command is reentered, the task list is shown as follow:
```
_____________________________________________________
1.[T][ ] shower 
2.[E][ ] housewarming  (at: Mar 1 2022 10AM)
3.[D][ ] coding project  (by: May 11 2022 11AM)
_____________________________________________________
```

### `find` - Search for task(s)

Search for task(s) that matches your keyword search. Additionally, the search keyword is not case-sensitive.

Syntax:
`find <keyword>`

| Parameter   | Description                                 |
|-------------|---------------------------------------------|
| `<keyword>` | The task you want to search for by keyword. |


Example of usage:

```
find coding
```
Expected outcome:

The task(s) which contains the word 'coding' in their description are displayed:

```
_____________________________________________________
Here are the matching task(s) in your list:
3.[D][ ] coding project  (by: May 11 2022 11AM)
_____________________________________________________
```

### `bye` - Exit Duke

Bids you goodbye and exits the program.

Syntax:
`bye`

Example of usage:
```
bye
```
Expected outcome:

The program bids you goodbye and exits the program.

```
_____________________________________________________
Bye. Hope to see you again soon!
_____________________________________________________

Process finished with exit code 0
```

## Editing the Duke data file
The Duke data file is saved as a text file `[Duke.jar file location]/data/duke.txt`. You may modify the Duke data directly by editing the `duke.txt` file.

###Caution
If modifications made to the data file causes its format to be invalid, the Duke program will not load the data into the program. Thus, you have to start on a clean slate.


## Command summary
| Action      | Format, Examples                                                                                                                                                                                                                                                                                |
|-------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Create task | Todo task: `todo <description>`<br/>e.g., `todo shower`<br/><br/>Event task: `event <description> /at <date> <time>`<br/>e.g., `event housewarming /at 2022-03-01 10`<br/><br/> Deadline task: `deadline <description> /by <date> <time>`<br/>e.g., `deadline coding project /by 2022-05-03 11` |
| Delete task | `delete <task number>`<br/>e.g., `delete 2`                                                                                                                                                                                                                                                     |
| Find task   | `find <keyword>`<br/>e.g., `find coding`                                                                                                                                                                                                                                                        |
| Mark task   | Mark task: `mark <task number>`<br/>e.g., `mark 2`<br/><br/>Unmark task: `unmark <task number>`<br/>e.g., `unmark 2`                                                                                                                                                                            |
| List task   | `list`                                                                                                                                                                                                                                                                                          |
| Exit        | `bye`                                                                                                                                                                                                                                                                                           |