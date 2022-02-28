# User Guide for Duke
**Duke** is a Personal Assistant Chatbot that helps a person to keep track of various tasks via a **Command Line Interface**.

## Features 
1. Add a ToDo task
2. Add a Deadline task
3. Add an event task
4. delete a specific task
5. Mark a task as done
6. Mark a task as undone
7. Display the current task list
8. find the tasks with given keyword
9. Load and save the data automatically
10. Exit the program

## Usage

### Add a task: `taskType description (time)`

**Format**:
 
- TODO: `TODO description`. **Example**: `TODO borrow book`

- DEADLINE: `DEADLINE description /by deadlineTime`. **Example**: `DEADLINE return book /by Sunday`
 
- EVENT: `EVENT description /at eventTime`. **Example**: `EVENT project meeting /at Mon 2-4pm`
 
**Expected outcome (Deadline as an example)**:
 
```
Got it. I've added this task: 
  [D][ ] return book (by: Sunday)
Now you have 6 tasks in the list.
```

### Delete a task: `delete`

Delete certain task based on its index.

**Format**: `delete taskIndex`
 
**Expected outcome**:
```
Noted. I've removed this task: 
  [D][ ] return book (by: Sunday)
Now you have 5 tasks in the list.
```

### Mark a task as done/undone: `mark/unmark`

Mark a task as done/undone based on given index. The default status of a task is **undone**. 

**Format**:
 
- mark: `mark taskIndex`. **Example**: `mark 2`

- unmark: `unmark taskIndex`. **Example**: `unmark 2`

**Expected outcome (mark as example)**:
```
Nice! I've marked this task as done: 
3. [T][X] borrow book
```

### Display the current task list: `list`

**Format**: `list`

**Expected outcome**:
```
Here are the tasks in your list:
1. [T][ ] eat food
2. [D][X] return book  (by: Sunday)
3. [T][X] borrow book
4. [E][ ] study CS2113  (at: Monday)
```

### Find the tasks with given keyword: `find`

**Format**: `find keyword`

**Example**: `find book`
 
**Expected outcome**:
```
Here are the matching tasks in your list:
1. [D][X] return book  (by: Sunday)
2. [T][X] borrow book
```

### Exit the program: `bye`

**Format**: `bye`

**Expected outcome**:
```
Bye.Have a nice day!
```

## FAQ
Q: How do I know if the data are successfully loaded/saved from/into the file?

A: Duke will display corresponding message and show error message in case the loading/saving process is unsuccessful.

Q: What if my input format is wrong?

A: Duke will give hint message and allow users to type in again.



