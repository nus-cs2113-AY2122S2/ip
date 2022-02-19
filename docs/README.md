# User Guide
**Duke** is a desktop app for managing tasks, optimized for use via a Command Line Interface.
## Quick start
1. Ensure you have Java `11` or above installed in your computer.
2. Download the latest `duke.jar` from [here]().
3. Copy the file to the folder you want to use as the home folder for your Duke.
4. Open cmd.exe type: java -jar [absolute path of Duke] will start Duke.
5. Type the command in the command box and press Enter to execute it.
6. Refer to the Features below for details of each command.
## Features

### 1. Add tasks

User can add 3 different types of tasks: Todo, Event, Deadline.

### 2. Show task list

Duke can show the task list which edited by User

### 3. Delete tasks

User can delete tasks which is added before

### 4. Find tasks

User can find tasks which existed in the task list by searching keywords.

### 5. Mark tasks as Done

User can mark tasks as done, which will display a tick in the box in front of the description of that task.

Description of the feature.

### 6. Exit from program

User can exit from program, and duke will automatically save the data in duke.txt file

## Usage

### `1. todo\deadline\event /by\at yyyy-mm-dd` - Add tasks to the list

User can add tasks to the list.

Example of usage:

`deadline return book /by 2002-02-01`

```
Got it. I've added this task:
[D][] return book  (by: Feb 1 2002)
Now you have 1 tasks in the list

```
### `2. list` - Show the current task list

User gets the current list.

Example of usage:

`list`

```
[D] [ ] return book  (by: Feb 1 2002)

```

### `3. delete [order number]` - delete the task

User delete a task in the task list.

Example of usage:

`delete 1`

```
Noted. I've removed this task:
[D][ ] return book  (by: Feb 1 2002)

Now you have 0 tasks in the list.

```
### `4. find [keywords]` - find tasks which include keywords

User can find tasks by keywords.

Example of usage:

`find book`

```
find book
[T] [ ] return book
[T] [ ] buy book

```
### `5. unmark/mark [order number]` - mark a task as done

User can mark a task as done

Example of usage:

`mark 1`

```
Nice! I've marked this task as done yet:
[T][X] read book
```

### `6. bye` - exit from the program
User can exit from the program

Example of usage:
`bye`

```
bye
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________

```