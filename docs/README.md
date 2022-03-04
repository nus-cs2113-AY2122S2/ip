# User Guide
A personal assistant chatbot to help you keep track of your tasks

## Quick Start
1. Do something

## Features
1. Add 3 types of Task: Todo / Deadline / Event
2. Mark a specific task as completed
3. Unmark a specific task if it's not completed yet
4. Delete a specific task
5. Display all tasks
6. Find all tasks that contain a search keyword
7. Exit the program

## Usage

### Todo: `todo [description]`

Add a Todo task.

**Sample Input**:

`todo borrow book`

**Expected outcome**:

Creates a new Todo task and marks it as incomplete as its initial state.

```
____________________________________________________________
Got it. I've added this task:
 [T][ ] borrow book
Now you have 4 task(s) in the list.
____________________________________________________________
```

### Deadline: `deadline [description] /by [YYYY-DD-MM]`

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

### Event: `event [description] /at [YYYY-DD-MM]`

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