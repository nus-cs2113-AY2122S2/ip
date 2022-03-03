#### User Guide

Notes: 
- Words in UPPER_CASE are the parameters to be supplied by the user.
- Most of the example expected outcomes demonstrated for each feature follow the demonstrated outcomes before it.

### Features

## Adding a task: 

Adds a task to the list of tasks. Tasks can be of 3 types.

# 1. Todo: `todo`

Adds a "Todo" type task to the list of tasks. 

Format: `todo DESCRIPTION`

Examples of usage:
- `todo Homework`
- `todo Geography assignment`

Example expected outcome of adding an event:
```
Noted. I've added:
[T][ ] Homework
Now you have 1 tasks in the list.
```

# 2. Event: `event`

Adds an "Event" type task to the list of tasks. 

Format: `event DESCRIPTION /at TIME`

Examples of usage:
- `event meeting with boss /at 3pm Monday`
- `event lunch with Richard Hendricks /at 2pm to 3pm 21st December `

Example expected outcome of adding an event:
```
Noted. I've added:
[E][ ] meeting with boss (at: 3pm Monday)
Now you have 2 tasks in the list.
```

# 3. Deadline: `deadline`

Adds a "Deadline" type task to the list of tasks.

Format: `deadline DESCRIPTION /at DUE_DATE_OR_TIME`

Examples of usage:
- `deadline finish homework /by tomorrow`
- `deadline take the dog out for a walk /by 12am Midnight`

Example expected outcome of adding a deadline:
```
Noted. I've added:
[D][ ] take the dog out for a walk (by: 12am Midnight)
Now you have 3 tasks in the list.
```

## Viewing tasks currently in list: `list`

Allows user to view the tasks that are currently in the list. 

Format: `list`

Example of usage:
- `list`

Example expected outcome:
```
Here are the tasks in your list:
1. [T][ ] Homework
2. [E][ ] meeting with boss (at: 3pm Monday)
3. [D][ ] take the dog out for a walk (by: 12am Midnight)
```
`T`, `E` and `D` signify a "Todo" task, an "Event" task and a "Deadline" task respectively.

## Marking tasks currently in list as done: `mark`

Allows user to mark tasks that are currently in the list as completed.

Format: `mark TASK_NUMBER`

Example of usage:
- `mark 3`

Expected outcome:
```
Great Job! I've marked the following task as done:
[D][X] take the dog out for a walk (by: 12am Midnight)
```

Expected outcome when `list` is used after marking a task as done:
```
Here are the tasks in your list:
1. [T][ ] Homework
2. [E][ ] meeting with boss (at: 3pm Monday)
3. [D][X] take the dog out for a walk (by: 12am Midnight)
```
We can see here that task 3 is marked with an `X` indicated it is completed.


## Marking tasks currently in list as not yet done: `unmark`

Allows user to mark tasks that are currently in the list as completed.

Format: `unmark TASK_NUMBER`

Example of usage:
- `mark 3`

Expected outcome:
```
Ok, I've marked the following task as yet to be done:
[D][ ] take the dog out for a walk (by: 12am Midnight)
```

Expected outcome when `list` is used after marking a task as not yet done:
```
Here are the tasks in your list:
1. [T][ ] Homework
2. [E][ ] meeting with boss (at: 3pm Monday)
3. [D][ ] take the dog out for a walk (by: 12am Midnight)
```
Task 3 that used to be marked with an `X` is no longer marked as done. 

## Finding tasks currently in list: `find`

Allows user to find tasks are currently in the list that match with keyword provided.
If there are multiple tasks with the user-provided keyword in their description, all such tasks be displayed. 

Format: `find KEYWORD`

Example of usage:
- `find meeting`
- `find take`

Example expected outcome:
```
Here are the matching tasks in your list:
2. [E][ ] meeting with boss (at: 3pm Monday)
```

## Deleting tasks currently in list: `delete`

Allows user to delete tasks that are currently in the list.

Format: `delete TASK_NUMBER`

Example of usage:
- `delete 3`

Expected outcome:
```
Noted. I've removed:
[D][ ] take the dog out for a walk (by: 12am Midnight)
Now you have 2 tasks in the list.
```

Expected outcome when `list` is used after deleting a task:
```
Here are the tasks in your list:
1. [T][ ] Homework
2. [E][ ] meeting with boss (at: 3pm Monday)
```
Task 3 that used to be present in the list is no longer there as it has been deleted.

## Saving and loading the data:

Data in the list is saved automatically every time it is updated and loaded automatically when the program is launched.
There is no need to do anything manually. 

## Terminating the program: `bye`

Allows user to properly terminate the program. 

Format: `bye`

Example of usage:
- `bye`

Expected outcome:
```
Goodbye. See you in the funny papers.
```
