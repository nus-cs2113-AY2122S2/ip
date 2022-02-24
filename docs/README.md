# User Guide

Serene is a **desktop app for the storing and viewing of tasks, optimised for use via a Command Line Interface** (CLI).
If you can type fast, Serene can help you note down your tasks faster than you can ever accomplish with pen and paper.
Do not worry about missing out details of your tasks, as Serene will notice when you do not supply a proper description or time!

- [Quick start](#quick-start)
- [Features](#features)
  - [Viewing help: `help`](#viewing-help-help)

## Quick start
1. Ensure that you are running Java `11` on your system.
2. Download the latest `Serene.jar` from [here]().
3. Copy the file to the folder you want to use as the *home folder* for Serene.
4. Double-click the file to start the app. The interface similar to the one below should appear in a few seconds.
5. Type the command in the command box and press Enter to execute it. e.g. typing `help` and pressing Enter will open the help window.
Some example commands you can try:
* `list`: Lists all recorded tasks.
* `todo Try out Serene`: Adds a todo labelled `Try out Serene` to the task list.
* `mark 1`: Marks the 1st task shown in the current list.
* `delete 1`: Deletes the 1st task shown in the current list.
* `bye`: Exits the app.
6. Refer to the [Features](#features) below for details of each command.

## Features 

:exclamation: ***Notes about the command format***
* Words in `UPPER_CASE` are parameters to be supplied by the user.
e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo Try out Serene`.
* Extraneous parameters for commands that do not take in parameters (such as `help` and `list`) will be ignored.
e.g. if the command specifies help 123, it will be interpreted as help.

### Viewing help: `help`

Shows a list of commands which the user can input.
Format: `help`

### Feature-XYZ

Description of the feature.

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
