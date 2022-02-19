# User Guide: Duke
Duke application aims to help users manage their tasks and store them in a local
 text file via a Command Line Interface.

##Quick start
1. Ensure you have Java `11` installed in your computer.
2. Download the latest release file from [.jar](https://github.com/wang1351/ip/releases/download/A-Jar/ip.jar) or [.tar.gz](https://github.com/wang1351/ip/archive/refs/tags/A-Jar.tar.gz) or [.zip](https://github.com/wang1351/ip/archive/refs/tags/A-Jar.zip).
3. Click
Refer to the [Features]() below for all provided features.

## Features 
Duke application supports a variety of features which facilitate the management of user tasks. User tasks are categorized
into todo, deadline and event.
1. Add a todo
2. Add a deadline
3. Add an event
4. Mark a task as done
5. Mark a task as undone
6. Display current task list
7. Delete a task from task list
8. Find all tasks which contain a specified string
9. Quit
10. Error handling
11. Duke application automatically saves all the tasks into [duke.txt](../data/duke.txt) in the `data` folder.

After adding a task (feature [1](), [2](), [3]()), the status of the task is set to undone by default.

Please view the [Usage]() below for details of each command.

## Usage

### Adding a todo: `todo` 
Add a todo to the task list.

Format: `todo description`

- `description` refers to the description of the todo 

Example: `todo read book`

Expected outcome:


```
Got it. I've added this task: 
[T][ ] read book
Now you have 1 tasks in the list.
```

### Adding a deadline: `deadline` 
Add a deadline to the task list.

Format: `deadline description /by time`

- `time` refers to the time of the deadline 

Example: `deadline borrow book /by today`

Expected outcome:


```
Got it. I've added this task: 
[D][ ] borrow book by: today
Now you have 2 tasks in the list.
```

### Adding an event: `event` 
Add an event to the task list.

Format: `event description /at time`

Example: `event make presentation /at Mondays`

Expected outcome:


```
Got it. I've added this task: 
[E][ ] make presentation at: Mondays
Now you have 3 tasks in the list.
```

### Displaying the task list: `list` 
Displaying all the tasks added to the list.

Example: `list`

Expected outcome:


```
Here are the tasks in your list:
1. [T][ ] read book
2. [D][ ] borrow book by: today
3. [E][ ] make presentation at: Mondays
```

### Finding all tasks containing a keyword: `find` 
Displaying all the tasks in the list which contain a specified string.

Format: `find keyword`
 
Example: `find book`

Expected outcome:


```
Here are the matching tasks in your list:
1. [T][ ] read book
2. [D][ ] borrow book by: today
```

### Marking a task as done: `mark` 
Set status of a task as done.

Format: `mark index`

- `index` refers to the index of the task (as shown in the displayed list) to be marked as done, positive integer from `1` to task list size

Example: `mark 1`

Expected outcome:

```
Nice! I've marked this task as done:
[T][X] read book
```

### Marking a task as undone: `unmark` 
Set status of a task as undone.

Format: `unmark index`

Example: `unmark 1`

Expected outcome:

```
OK, I've marked this task as not done yet:
[T][ ] read book
```

### Deleting a task: `delete` 
Remove a task from the list.

Format: `delete index`

Example: `delete 1`

Expected outcome:

```
Noted. I've removed this task:
[T][ ] read book
Now you have 2 tasks in the list.
``` 
### Quitting: `bye` 
Terminate the application and automatically update the text file.

Example: `bye`

Expected outcome:
```
Bye. Hope to see you again soon!
```



## FAQ
**Q**: What if my input is in wrong format?

**A**: When encountering unfamiliar format of input, Duke application provides a warm notice to the user
and allows user to key in again.
