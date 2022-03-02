# User Guide

The duke program is a task tracker application and consists of a task list with various features such as adding tasks, listing out all the tasks, finding tasks, marking tasks as done or not done and deleting tasks.

There are three types of tasks found within the duke program and they are todo, event and deadline. The definitions of each of these tasks can be found in the glossary section below.

- Feature
  - Adding a ToDo Task
  - Adding a Deadline Task
  - Adding an Event Task
  - Listing out all the Tasks within the tasklist
  - Marking a Task as done
  - Marking a Task as not done
  - Deleting a Task
  - Finding a Task
  - Exiting the Program
- Glossary
- Command Summary

## Features

### Adding a ToDo Task: `todo`
Adds a ToDo Task to the task list.

Format: ```todo NAME OF TASK```

Example: 
```
todo Borrow books from the library.
```

Expected Outcome:
After entering the todo command, the program would show an acknowledgement message with the following components
- Name of the task added
- The type of the task added which in this example is [T] signifiying that it is a todo task.
- The status of the task added which in this case is [ ] signifiying that the task is not yet done 
- The total amount of tasks currently within the tasklist.

```
    -----------------------------------------------------------------
	 Got it. I've added this task:
	   [T][ ] Borrow books from the libaray
	 Now you have 1 tasks in the list.
    -----------------------------------------------------------------
```

### Adding a Deadline Task: `deadline`
Adds a Task with a Deadline to the task list:

Format: ```deadline NAME OF TASK /by DEADLINE OF THE TASK```

Example: 
```
deadline Finish CS2113 IP /by 4th March 2359
```

Expected Outcome:
After entering the deadline command, the program would show an acknowledgement message with the following components
- Name of the task added and its deadline.
- The type of the task added which in this example is [D] signifiying that it is a deadline task.
- The status of the task which in this case is [ ] signifiying that the task is not yet done 
- The total amount of tasks currently within the tasklist.

```
    -----------------------------------------------------------------
	 Got it. I've added this task:
	   [D][ ] Finish CS2113 IP (by: 4th March 2359)
	 Now you have 2 tasks in the list.
    -----------------------------------------------------------------
```

### Adding an Event Task: `event`
Adds a Task which is an Event to the task list.

Format: ```event NAME OF TASK /at DATE TIME THE EVENT STARTS-TIME THE EVENT ENDS```

Example: 
```
event Birthday Party /at 19th March 12am-2pm
```

Expected Outcome:
After entering the event command, the program would show an acknowledgement message with the following components
- Name of the task added and its date time.
- The type of the task added which in this example is [E] signifiying that it is an event task.
- The status of the task which in this case is [ ] signifiying that the task is not yet done 
- The total amount of tasks currently within the tasklist.

```
    -----------------------------------------------------------------
	 Got it. I've added this task:
	   [E][ ] Birthday Party (at: 19th March 12am-2pm)
	 Now you have 3 tasks in the list.
    -----------------------------------------------------------------
```

### Listing out all the Tasks within the tasklist:`list`
Lists all the Tasks within the tasklist.

Format: ``list``

Tip: Ensure that there isnt a space behind the list command.

Expected Outcome:
After entering the list command, a list of task along with its type, status and index within the tasklist would be shown.

```
    -----------------------------------------------------------------
	 Here are the tasks in your list:
	 1.[T][ ] Borrow books from the libaray
	 2.[D][ ] Finish CS2113 IP (by: 4th March 2359)
	 3.[E][ ] Birthday Party (at: 19th March 12am-2pm)
    -----------------------------------------------------------------
```

### Marking a Task as done:`mark`
Marks a Task within the tasklist as done.

Format: ```mark Index Of The Task Within The List```

Example: 
```
mark 1
```
Tip: To view what is the index of the task within the list, we would use the list command which shows all the tasks added so far as well as its index.

Expected Outcome:
After entering the mark command, the program would show an acknowledgement message with the following components.
- Description of the task marked
- Type of the task marked
- The status of the task marked which would be [X] signifiying the task is marked as done.

```
    -----------------------------------------------------------------
	 Nice! I've marked this task as done:
	   [T][X] Borrow books from the libaray
    -----------------------------------------------------------------
```

### Marking a Task as not done: `unmark`
Mark a Task within the tasklist as not done.

Format ```unmark Index Of The Task Within The List```

Example: 
```
unmark 2
```

Expected Outcome:
After entering the unmark command, the program would show an acknowledgement message with the following components.
- Description of the task marked
- Type of the task marked
- The status of the task marked which would be [ ] signifiying the task is marked as not done.

```
    -----------------------------------------------------------------
	 OK, I've marked this task as not done yet:
	   [T][ ] Borrow books from the libaray
    -----------------------------------------------------------------
```

### Deleting a Task:`delete`
Deletes a Task within the task list.

Format: ```delete Index Of The Task Within The List```

Example: 
```
delete 3
```

Expected Outcome:
After entering the delete command, the program would show an acknowledgement message with the following components.
- Description, type and status of the task deleted 
- The total amount of tasks remaining within the tasklist.

```
	 Noted. I've removed this task:
	   [T][ ] Borrow books from the libaray
	 Now you have 2 tasks in the list.
```

### Finding a Task:`find`
Search for a particular Task in the task list.

Format: ```find Keyword To Search For```

Example: 
```
find CS2113
```

Expected Outcome:
After entering the find command, the program would list all the tasks whose description contains the keyword along with its description, type and status.

```
    -----------------------------------------------------------------
	 Here are the matching tasks in your list:
	 1.[D][ ] Finish CS2113 IP (by: 4th March 2359)
    -----------------------------------------------------------------
```

### Exiting the Program:`bye`
Exits the program.

Format: ```bye```

Expected Outcome:

After entering the `bye` command, you should see the following output indicating that you have successfully exited the program.

```
    -----------------------------------------------------------------
    Good bye.See you soon :)
    -----------------------------------------------------------------
```
Tip: Ensure that there isnt a space behind the bye command.

## Glossary
- event: tasks that start at a specific time and ends at a specific time e.g., team project meeting on 2/10/2019 2-4pm

- deadline: tasks that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm

- todo: tasks without any date/time attached to it e.g., visit new theme park

## Command Summary
| Name     | Format, Example |
| ---      | ---       |
| todo | ``todo NAME OF TASK`` Eg, ``todo Borrow books from the library`` |
| deadline | ``deadline NAME OF TASK /by DEADLINE OF THE TASK ``  Eg, ``deadline Finish CS2113 IP /by 4th March 2359`` |
| event | ``event NAME OF TASK /at DATE TIME THE EVENT STARTS-TIME THE EVENT ENDS``  Eg, ``deadline Finish CS2113 IP /by 4th March 2359`` |
| list | ``list`` |
| mark | ``mark Index Of The Task Within The List`` Eg, ``mark 1``|
| unmark | ``unmark Index Of The Task Within The List`` Eg, ``unmark 2``|
| delete | ``delete Index Of The Task Within The List`` Eg, `delete 3`|
| find | ``find Keyword To Search For`` Eg, ``find CS2113`` |
| bye | ``bye`` |


