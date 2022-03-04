# User Guide for Duke - A Command-Line Interface (CLI) Task Manager

Duke is a Personal Assistant Chatbot in the form of a CLI programme that helps users keep track of various types of tasks. Duke is useful for fast typists who can manage their tasks quickly using Duke as compared to traditional Graphical User Interface (GUI) apps.

- [Quick Start]
- [Features]
  - [Listing all tasks: `list`]
  - [Adding a Task]
    - [Adding a Todo: `todo`]
    - [Adding an Event: `event`]
    - [Adding a Deadline: `deadline`]
  - [Delete a task: `delete`]
  - [Mark task as complete: `mark`]
  - [Mark task as incomplete: `unmark`]
  - [Search for tasks: `find`]
  - [Quit: `bye`]
- [Command Summary]

## Quick Start

1. Ensure you have Java 11 installed in your Computer.
2. Download the latest version of `ip.jar`
3. Copy the file to the folder you would like to use as the *home folder* for Duke
4. Double-click the file. Your terminal or command prompt should open up.
5. Type `java -jar ip.jar` and press Enter to start the app.
6. Type any command in the Features section below and press Enter to execute it.

## Features 

### Listing all tasks: `list`

Shows a list of all the tasks you have in your list.

Format: `list`

### Adding a Todo: `todo`

Adds a Todo to the task list. A Todo is a task with only a description.

Format: `todo [TASK_DESCRIPTION]`

Examples:
- todo read a book
- todo catch up with Bryan

### Adding an Event: `event`

Adds an event to the task list. An event is a task with a description and a time of occurrence.

Format: `event [TASK_DESCRIPTION] /at [TIME]`

Examples:
- event night cycling /at 6.30pm
- event gaming /at tomorrow morning

### Adding a Deadline: `deadline`

Adds a deadline to the task list. A deadline is a task with a description and a due time.

Format: `deadline [TASK_DESCRIPTION] /by [TIME]`

Examples:
- deadline individual project /by 2359h
- deadline stats quiz /by Sunday

### Delete a task: `delete`

Deletes a task from the task list based on its index in the list. You can use the `list` command first if you are unaware of the specific index to use.

Format: `delete [TASK_INDEX]`

Examples:
- delete 1
- delete 2

### Mark task as complete: `mark`

Marks a task, selected via its index, as complete. An 'X' will appear beside the task when using the `list` command

Format: `mark [TASK_INDEX]`

Examples:
- mark 1
- mark 2

### Mark task as incomplete: `unmark`

Marks a task, selected via its index, as incomeplete. There will be no 'X' beside the task when using the `list` command

Format: `unmark [TASK_INDEX]`

Examples:
- unmark 1
- unmark 2

### Search for tasks: `find`

Lists out all relevant tasks based on a search term, along with their status.

Format: `find [SEARCH_TERM]`

Examples:
- find book
- find cycling

### Quit: `bye`

Exits the programme.

Format: `exit`

### Saving the data
Duke data are saved in the hard disk automatically after any command changes the data. There is no need to save manually.

## Command Summary

| Action          | Format                                   |
|-----------------|------------------------------------------|
| Add a Todo      | `todo [TASK_DESCRIPTION]`                |
| Add an Event    | `event [TASK_DESCRIPTION] /at [TIME] `   |
| Add a Deadline  | `deadline [TASK_DESCRIPTION] /by [TIME]` |
| List            | `list`                                   |
| Delete          | `delete [TASK_INDEX]`                    |
| Mark Complete   | `mark [TASK_INDEX]`                      |
| Mark Incomplete | `unmark [TASK_INDEX]`                    |
| Find            | `find [SEARCH_TERM]`                     |
| Quit            | `bye`                                    |