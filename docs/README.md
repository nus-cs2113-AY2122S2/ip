# User Guide
**About the Program**: A personal assistant chatbot to help you keep track of your tasks

## Pre-Requisites
* Java 11 installed on your system

## Quick Start
1. Download the v0.2 IP.jar file from [here](https://github.com/lestersimjj/ip/releases/download/A-Release/IP.jar).
2. Place the .jar file into an empty folder.
3. Open Command Prompt (for Windows) or Terminal (for Mac) and navigate to the directory where the newly created folder is.
4. Run the command `java -jar IP.jar` to start the program.
5. Enter the commands below.
6. Terminate the program by entering `bye`.

## Features
1. Add 3 types of Task: Todo / Deadline / Event
2. Mark a specific task as completed
3. Unmark a specific task if it's not completed yet
4. Delete a specific task
5. Display all tasks
6. Find all tasks that contain a search keyword
7. Exit the program

## Usage

### `todo` - Create a Todo
`todo [DESCRIPTION]`

Add a Todo task.

**Sample Input**:

`todo borrow book`

**Expected outcome**:

Creates a new Todo task and marks it as incomplete as its 
initial state as shown by the second empty bracket.

```
____________________________________________________________
Got it. I've added this task:
 [T][ ] borrow book
Now you have 4 task(s) in the list.
____________________________________________________________
```

### `deadline` - Create a Deadline
`deadline [DESCRIPTION] /by [YYYY-DD-MM]`

Add a Deadline task with the description and due date specified.

**Sample Input**:

`deadline project BBA /by 2022-02-12`

**Expected outcome**:

Creates a new Deadline task and marks it as incomplete as its 
initial state with the due date marked in brackets.

```
____________________________________________________________
Got it. I've added this task:
 [D][ ] project BBA (by: 2022-02-12)
Now you have 3 task(s) in the list.
____________________________________________________________
```

### `event` - Create an Event
`event [DESCRIPTION] /at [YYYY-DD-MM]`

Add an Event task with the event description and date specified.

**Sample Input**:

`event opening ceremony /at 2022-05-05`

**Expected outcome**:

Creates a new Event task and marks it as incomplete as its 
initial state with the event date in brackets.

```
____________________________________________________________
Got it. I've added this task:
 [E][ ] opening ceremony (at: 2022-05-05)
Now you have 5 task(s) in the list.
____________________________________________________________
```

### `mark` - Mark a Task
`mark [TASK_INDEX]`

Mark a task as completed by specifying the task index found on the list of tasks.

**Sample Input**:

`mark 2`

**Expected outcome**:

Marks the second task in the list of tasks as completed as 
shown by `[X]` in the sample output below

```
____________________________________________________________
Nice! I've marked this task as done:
 [E][X] project meeting (at: 2019-10-16)
____________________________________________________________
```

### `unmark` - Unmark a Task
`unmark [TASK_INDEX]`

Unmark a task as not completed yet by specifying the task index found on the list of tasks.

**Sample Input**:

`unmark 2`

**Expected outcome**:

Unmark the second task in the list of tasks as not completed as
shown by the empty bracket `[ ]` in the sample output below

```
____________________________________________________________
OK, I've marked this task as not done yet:
 [E][ ] project meeting (at: 2019-10-16)
____________________________________________________________
```

### `delete` - Delete a Task
`delete [TASK_INDEX]`

Delete a task by specifying the task index found on the list of tasks.

**Sample Input**:

`delete 2`

**Expected outcome**:

Delete the second task in the list of tasks.

```
____________________________________________________________
Noted. I've removed this task:
 [E][ ] project meeting (at: 2019-10-16)
Now you have 4 tasks in the list
____________________________________________________________
```

### `list` - List All Tasks

List all existing tasks.

**Sample Input**:

`list`

**Expected outcome**:

```
____________________________________________________________
1. [T][X] borrow book
2. [D][ ] project BBA (by: 2022-02-12)
3. [E][ ] opening ceremony (at: 2022-05-05)
____________________________________________________________
```

### `find` - Finding Keywords
`find [KEYWORDS]`

Find task(s) by searching for a keyword.

**Sample Input**:

`find books`

**Expected outcome**:

Delete the second task in the list of tasks.

```
list
____________________________________________________________
1. [E][ ] opening ceremony (at: 2022-05-05)
2. [T][ ] read books
3. [D][ ] return book (by: 2022-05-06)
____________________________________________________________
find book 
____________________________________________________________
1. [T][ ] read books
2. [D][ ] return book (by: 2022-05-06)
____________________________________________________________
```

### `bye` - Terminating Duke

Terminate the program.

**Sample Input**:

`bye`

**Expected outcome**:

```
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```