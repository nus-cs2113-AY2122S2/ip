# User Guide
Duke is a **desktop app for tracking tasks,
optimized for use via a Command Line Interface** (CLI).
As Duke is designed to be use in a CLI, this
application would benefit users who are able to type fast.

## Quick Start
1. Ensure you have Java 11 or above installed on your Computer
2. Download the latest JAR file from GitHub.
3. Copy the file to the folder you want to use as the home folder for your Duke application.
   Note that the data file would also be created in the same folder.
4. Open the CLI at where you have placed the jar file and run it using the command
   `java -jar ip.jar`
5. Type the commands in the box and press enter to execute it.
   Here are some commands you can try:
    - `list` : List all current tasks.
    - `todo Read Book` : Adds a todo task of reading book.
    - `delete 1` : Deletes the first task from list.
### Feature list:
* Listing all tasks: `list`
* Adding a new Todo task: `todo`
* Adding a new Deadline task: `deadline`
* Adding a new Event task: `event`
* Marking a task as done: `mark`
* Marking a task as not done: `unmark`
* Deleting a task: `delete`
* Finding a task: `find`
* Exiting the program: `bye`

## Feature
### Adding a Task
There are three kinds of Tasks: Todos, Deadlines, and Events.
### Adding a Todo
Adds a Todo to the list of tasks. <br />
Format: `todo DESCRIPTION` <br />
Words in `UPPER_CASE` are the parameters. <br />
Examples:
* todo go to class
* todo buy fruits

### Adding a Deadline
Adds a Deadline to the list of tasks. <br />
Format: `deadline DESCRIPTION /by DEADLINE` <br />
Words in `UPPER_CASE` are the parameters. <br />
Examples: <br />
* deadline finish problem set /by Saturday
* deadine return book /by Sunday

### Adding an Event
Adds an Event to the list of tasks. <br />
Format: `event DESCRIPTION /at TIME` <br />
Words in `UPPER_CASE` are the parameters. <br />
Examples: <br />
* event project meeting /at Saturday 8:00PM
* event attend CS2113 lecture /at Friday 4:00PM

### Listing all Tasks
Displays all tasks that the user has added to their list. <br />
Format: `list` <br />
Output could look like the following: <br />
```
 1: [T][ ] go shopping
 2: [D][ ] finish 2113 iP (by: Friday)
 3: [E][ ] 2113 project meeting (at: Monday 1:00PM)
 ```
`T` denotes a task of type Todo, `D` denotes a task of type Deadline, and `E` denotes a task of type Event.

### Marking a Task as complete
You have the option of marking (i.e. checking off) a task that has been completed. <br />
Format: `mark TASK_NUMBER` <br />
`TASK_NUMBER` is the numerical label assigned to the task (that you can find from the output of the `list` command). <br />
Example: <br />
Assuming the following task list: <br />
```
 1: [T][ ] go shopping
 2: [D][ ] finish 2113 iP (by: Friday)
 3: [E][ ] 2113 project meeting (at: Monday 1:00PM)
```
`mark 2` would result in the following list: <br />
```
 1: [T][ ] go shopping
 2: [D][X] finish 2113 iP (by: Friday)
 3: [E][ ] 2113 project meeting (at: Monday 1:00PM)
```

### Unmarking a Task
Alternatively, you may also unmark a task that has previously been marked. <br />
Format: `unmark TASK_NUMBER` <br />
Example: <br />
Assuming the following task list: <br />
```
 1: [T][ ] go shopping
 2: [D][ ] finish 2113 iP (by: Friday)
 3: [E][X] 2113 project meeting (at: Monday 1:00PM)
```
`unmark 3` would result in the following list: <br />
```
 1: [T][ ] go shopping
 2: [D][ ] finish 2113 iP (by: Friday)
 3: [E][ ] 2113 project meeting (at: Monday 1:00PM)
```

### Deleting a Task
Users may also delete a task from the list of tasks. <br />
Format `delete TASK_NUMBER` <br />
Example: <br />
Assuming the following task list: <br />
```
 1: [T][ ] go shopping
 2: [D][ ] finish 2113 iP (by: Friday)
 3: [E][ ] 2113 project meeting (at: Monday 1:00PM)
```
`delete 2` would result in the following list: <br />
```
 1: [T][ ] go shopping
 2: [E][ ] 2113 project meeting (at: Monday 1:00PM)
```


### Finding a Task
Users can search for Tasks in their task list with a specific keywords. <br />
Format `find KEYWORD` <br />
Example: <br />
Assuming the following task list: <br />
```
 1: [T][ ] go shopping
 2: [D][ ] finish 2113 iP (by: Friday)
 3: [E][X] 2113 project meeting (at: Monday 1:00PM)
```
`find 2113` would result in the following list:  <br />
```
 1: [T][ ] go shopping
 2: [D][ ] finish 2113 iP (by: Friday)
 3: [E][ ] 2113 project meeting (at: Monday 1:00PM)
 ```