# User Guide

## Features 

### Feature- Add a Task

Add a Todo/Event or Deadline

### Feature- Find Tasks

Find tasks based on a keyword

### Feature- Delete Tasks

Delete tasks

### Feature- Mark/Unmark Tasks

Mark or unmark task completion status

## Usage

#### 1. `list` List all available tasks <br>
List all tasks <br>

Example of usage:
`list`

Expected Outcome: <br>
List of All Tasks displayed

#### 2. `todo` Create a Todo<br>
Create a todo task <br>

Example of usage:
`todo (todo_description)`

Expected outcome: <br>
Todo created successfully.

#### 3. `event` Create an Event
Create an event task <br>

Example of usage:
`event (event_description) \at (event_time)`

Expected outcome: <br>
Event created successfully.

#### 4. `deadline` Create a Deadline
Create a deadline task <br>

Example of usage:
`deadline (deadline_description) \by (date_time)`

Expected outcome: <br>
Deadline created successfully.

#### 5. `mark` Mark a Task
Mark a task as completed <br>

Example of usage:
`mark (task_number)`

Expected outcome: <br>
Task marked successfully.

#### 6. `unmark` Unmark a Task
unmark a task which has not been completed <br>

Example of usage:
`unmark (task_number)`

Expected outcome: <br>
Task unmarked successfully.

#### 7. `delete` Delete a Task
delete a task from task list <br>

Example of usage:
`delete (task_number)`

Expected outcome: <br>
Task deleted successfully.

#### 8. `find` Find a Task
Search for a task in the task list by keyword <br>

Example of usage:
`find (keyword)`

Expected outcome: <br>
Display list of task found that matches keyword.

#### 9. `bye` Exit Program
To close program and store task list. <br>

Example of usage:
`bye`

Expected outcome: <br>
Program quit successfully.
