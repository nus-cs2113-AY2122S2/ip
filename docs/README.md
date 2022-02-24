# User Guide

Serene is a **desktop app for the storing and viewing of tasks, optimised for use via a Command Line Interface** (CLI).
If you can type fast, Serene can help you note down your tasks faster than you can ever accomplish with pen and paper.
Do not worry about missing out details of your tasks, as Serene will notice when you do not supply a proper description or time!

- [Quick start](#quick-start)
- [Features](#features)
  - [Viewing help: `help`](#viewing-help-help)
  - [Adding a todo: `todo`](#adding-a-todo-todo)
  - [Adding a deadline: `deadline`](#adding-a-deadline-deadline)
  - [Adding an event: `event`](#adding-an-event-event)
  - [Marking a task: `mark`](#marking-a-task-mark)
  - [Unmarking a task: `unmark`](#unmarking-a-task-unmark)
  - [Deleting a task: `delete`](#deleting-a-task-delete)
  - [Listing all tasks: `list`](#listing-all-tasks-list)
  - [Finding a task: `find`](#finding-a-task-find)
  - [Exiting the program: `bye`](#exiting-the-program-bye)
  - [Saving the data](#saving-the-data)

## Quick start
1. Ensure that you are running Java `11` on your system.
2. Download the latest `Serene.jar` from [here]().
3. Copy the file to the folder you want to use as the *home folder* for Serene.
4. Double-click the file to start the app. The interface similar to the one below should appear in a few seconds.  
   ![Startup_interface](/assets/Startup.png)
5. Type the command in the command box and press Enter to execute it. e.g. typing `help` and pressing Enter will open the help window.
Some example commands you can try:
* `list`: Lists all recorded tasks.
* `todo Try out Serene`: Adds a todo labelled `Try out Serene` to the task list.
* `mark 1`: Marks the 1st task shown in the current list.
* `delete 1`: Deletes the 1st task shown in the current list.
* `bye`: Exits the app.
6. Refer to the [Features](#features) below for details of each command.

## Features 

***Notes about the command format***
* Words in `UPPER_CASE` are parameters to be supplied by the user.
e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo Try out Serene`.
* Extraneous parameters for commands that do not take in parameters (such as `help` and `list`) will be ignored.
e.g. if the command specifies `help 123`, it will be interpreted as `help`.

### Viewing help: `help`

Shows a list of commands which the user can input.  
Format: `help`

### Adding a todo: `todo`

Adds a todo type task to the task list.  
Format: `todo DESCRIPTION`  
Examples:
- `todo Try out Serene`
- `todo Drink water`  
  ![Add_ToDo](/assets/Add_ToDo.png)

### Adding a deadline: `deadline`

Adds a deadline type task to the task list.  
Format: `deadline DESCRIPTION /by TIME`  
Examples:
- `deadline Implement CS2113T increments /by Next week`
- `deadline Read book /by Tonight 6pm`
  ![Add_ToDo](/assets/Add_Deadline.png)

### Adding an event: `event`

Adds an event type task to the task list.  
Format: `event DESCRIPTION /by TIME`  
Examples:
- `event Watch anime /at 7pm tonight`
- `event Practice Hack the Box /at Tomorrow`
  ![Add_ToDo](/assets/Add_Event.png)

### Marking a task: `mark`

Marks the specified task.  
Format: `mark INDEX`

### Unmarking a task: `unmark`

Unmarks the specified task.  
Format: `unmark INDEX`

### Deleting a task: `delete`

Deletes the specified task.  
Format: `delete INDEX`

### Listing all tasks: `list`

Shows a list of all tasks in the task list.  
Format: `list`  
Example list:  
![List_example](/assets/List.png)

### Finding a task: `find`

Find tasks whose descriptions contain the given keywords.  
Format: `find KEYWORD(S)`
- The search is case-sensitive. e.g. `work` will **NOT** match `Work`.
- The order of the keywords matter. e.g. `bake cake` will **NOT** match `cake bake`.
- Only the description is searched.
- Partial words can be matched e.g. `read` will match `reading`.  
Examples:
- `find Read` returns `Read book`
- `find Serene` returns `Try out Serene`
  ![Find_example](/assets/Find.png)

### Exiting the program: `bye`

Exits the program.  
Format: `exit`

### Saving the data

Serene data are saved in the hard disk automatically after any command that changes the data.  
There is no need to save manually.

## Usage

### `Keyword` - Describe action

Describe the action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```
