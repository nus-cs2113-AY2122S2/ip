# The ultimate guide to Duke

_By Duke_

## Features
> First, let me tell you about what I can do

### Create new task

I can keep track of every task you have to do, as long as they fall into one of the three different categories:
 - **Todo**: just a regular task without starting time or ending time
 - **Deadline**: a task that you need to complete before a specific time
 - **Event**: a task that happens at a certain period of time 

### Display existing tasks

I can show you all the tasks you have told me about.

### Mark/unmark a task

I can mark a task as done for you and unmark it if you want.

### Delete existing task

If you do not want keep track of any task, I can delete it for you.

### Find task

If you want to find a task with a given keyword, I can do that for you.

### Save data lo a local file

I do not have a good memory but if you tell me to I can remember all the tasks you ask me to manage for the next time we meet.

## Usage
> English is not my first language, so make sure to communicate with me using these format

> Oh and by the way everything in `[]` is your input 
### `Todo` - Create a new todo task

Create a new todo task to manage.

**Syntax:** 

> Todo [task]

**Example of usage:** 

`Todo clean my room`

**Expected outcome:**
```
	───────────────────────────────────────────────────────────────────────
	 Added [T][ ] clean my room to the list
	───────────────────────────────────────────────────────────────────────
```
### `Deadline` - Create a new deadline task

Create a new deadline task to manage.

**Syntax:**

> Deadline ]task] /by [time]

**Example of usage:**

`Deadline submit homework /by Thursday 23:59`

**Expected outcome:**
```
	───────────────────────────────────────────────────────────────────────
	 Added [D][ ] submit homework (by: Thursday 23:59) to the list
	───────────────────────────────────────────────────────────────────────
```
### `Event` - Create a new event task

Create a new event task to manage.

**Syntax:**

> Event [task] /at [time]

**Example of usage:**

`Event NUS Open Day /at March 5, 9:00`

**Expected outcome:**
```
	───────────────────────────────────────────────────────────────────────
	 Added [E][ ] NUS Open Day (at: March 5, 9:00) to the list
	───────────────────────────────────────────────────────────────────────
```

### `List` - Display all existing task

List all existing tasks

**Syntax:**

> List

**Example of usage:**

`List`

**Expected outcome:**
```
	───────────────────────────────────────────────────────────────────────
	 1 [T][ ] clean my room
	 2 [D][ ] submit homework (by: Thursday 23:59)
	 3 [E][ ] NUS Open Day (at: March 5, 9:00)
	───────────────────────────────────────────────────────────────────────
```

### `Mark` - Mark task as done

Mark a task as done

**Syntax:**

> Mark [index of the task]

**Example of usage:**

`Mark 1`

**Expected outcome:**
```
	───────────────────────────────────────────────────────────────────────
	 Aha! An interesting selection!
	   [T][X] clean my room
	───────────────────────────────────────────────────────────────────────
```

### `Unmark` - Unmark a task

Unmark a marked task

**Syntax:**

> Unmark [index of the task]

**Example of usage:**

`Unmark 1`

**Expected outcome:**
```
	───────────────────────────────────────────────────────────────────────
	 Ah... What a shame!
	   [T][ ] clean my room
	───────────────────────────────────────────────────────────────────────
```

### `Delete` - Delete a task

Remove an existing task from the current list

**Syntax:**

> Delete [index of the task]

**Example of usage:**

`Delete 1`

**Expected outcome:**
```
	───────────────────────────────────────────────────────────────────────
	 So you have chosen to delete this task!
	   [T][ ] clean my room
	───────────────────────────────────────────────────────────────────────
```

### `Find` - Fine a task

Find an existing task using a given keyword 

**Syntax:**

> Find [keyword]

**Example of usage:**

`Find NUS`

**Expected outcome:**
```
	───────────────────────────────────────────────────────────────────────
	 1 [E][ ] NUS Open Day (at: March 5, 9:00)
	───────────────────────────────────────────────────────────────────────
```

### `Save` - Save the current list

Save the data to a local file

**Syntax:**

> Save

**Example of usage:**

`Save`

**Expected outcome:**
```
	───────────────────────────────────────────────────────────────────────
	 You have saved your current list.
	───────────────────────────────────────────────────────────────────────
```

### `Bye` - Say goodbye to Duke

Exit the programme

**Syntax:**

> Bye

**Example of usage:**

`Bye`

**Expected outcome:**
```
	───────────────────────────────────────────────────────────────────────
	 Good day, then!
	───────────────────────────────────────────────────────────────────────
```

## Error message
> Ah...The task you choose doesn't exist on your list.

_This message appears when the task you try to choose does not exist._

**Possible fix:** add that task to the list or try to find that task manually by using `List`.

> Sorry but you haven't input a task here.

_This message appears when you try to add a task without the content ~~of the task~~._

**Possible fix:** try to add the task again but with the content.

> Sorry but you haven't input a date here.

_This message appears when you try to add a deadline task or an event task without the deadline or time period._

**Possible fix:** try to add the task as using `Todo` or input a task with a deadline or a time period.

> Sorry but I cannot understand your command.

_This message appears when your command is not recognized._

**Possible fix:** try the command again following the syntax given in this guide.

> Oh no! File IO error just occurred.

_This message appears when there is a problem with saving and loading the data file._

**Possible fix:** try to manually create a data folder in the same folder the jar file is in and add a data.txt file in the data folder.

