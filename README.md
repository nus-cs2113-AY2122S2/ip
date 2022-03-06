# User Guide

Duke is a program that helps you keep track of various things,
optimized for use via a Command Line Interface (CLI).In this programme we will be using BOB who is our friendly task Bot to be able to organize the tasks required.

                ╮╭┻┻╮╭┻┻╮╭▕╮ 
                ▕╯┃╭╮┃┃╭╮┃╰▕╯╭▏
                ▕╭┻┻┻┛┗┻┻┛ ▕ ╰▏
                ▕╰━━━┓┈┈┈╭╮▕╭╮▏
                ▕╭╮╰┳┳┳┳╯╰╯▕╰╯▏
                ▕╰╯┈┗┛┗┛┈╭╮▕╮┈▏
## Quick Start
1. Ensure that you have [Java 11](https://www.oracle.com/java/technologies/downloads/) or above installed in your computer.
2. Download the latest `Duke.jar` 
3. Run Duke by typing in `java -jar Duke.jar` in your Command Prompt.

## Features 

### Enter a task

Enter a new task of different types such as a todo, deadline or event. The new task is added to your task list for you to track.

### List all tasks

Displays all the tasks created on screen for viewing.

### Mark tasks

Mark task(s) as done once you have completed it

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


### `event` - Create an Event task

Creates a new task that is an event. An event includes the time of event.

Syntax:
`event <description> /at <date> <time>`

| Parameter       | Description                                                                                                                |
|-----------------|----------------------------------------------------------------------------------------------------------------------------|
| `<description>` | Description of your event.                                                                                                 |
| `<date>`        | When the event date begins. The format is: `yyyy-mm-dd` whereby `yyyy` is the year, `mm` is the month and `dd` is the day. |
| `<time>`        | What time the event starts. The format is: `kk` whereby `kk` is the time in 24 hours format ranging from 01 - 24.          |


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

### `list` - Display all tasks

Displays all the tasks in your task list on screen.

Syntax:
`list`


### `mark` - Mark a task as completed

Mark a task as completed once you are done with the task.

Syntax:
`mark <task number>`

| Parameter       | Description                                    |
|-----------------|------------------------------------------------|
| `<task number>` | The task number you want to mark as completed. |


### `bye` - Exit Duke

Bids you goodbye and exits the program.

| Action          | Format, Examples                                                                                                    |
|-----------------|---------------------------------------------------------------------------------------------------------------------|
| Create todo     | `todo <description>`<br/>e.g., `todo shower`                                                                        |
| Create event    | `event <description> /at <date> <time>`<br/>e.g., `event housewarming /at 2022-03-01 10`                            |
| Create deadline | `deadline <description> /by <date> <time>`<br/>e.g., `deadline coding project /by 2022-05-03 11`                    |
| Delete task     | `delete <task number>`<br/>e.g., `delete 2`                                                                         |
| Mark task       | Mark task: `mark <task number>`<br/>e.g., `mark 2`<br/><br/>Unmark task: `unmark <task number>`<br/>e.g., `unmark 2`|
| List task       | `list`                                                                                                              |
| Exit            | `bye`                                                                                                               |
