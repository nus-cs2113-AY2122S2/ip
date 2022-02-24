# User Guide

## Features 

### Feature - Adding of Task - Todo, Event, Deadline 

You can now add a task of any one of the three types. 


### Feature - Deletion of Task

Delete any task according to its index in the list of tasks. 

### Feature - Unmark/mark any task. 

Mark or unmark any task in the list of task. 

### Feature - List all tasks. 

List all the current tasks in the list. 

### Feature - Find a task, given a specific search word. 

Enter a word and search for all tasks that contain this word. 

## Usage

### `todo` - Describe action

Describe the action and its outcome.

Example of usage: 

`todo enter description here`

Expected outcome:

Description of the outcome.

```
Got it. I've added this task:
[T][ ]enter description here
Now you have 1 tasks in the list.
```

### `deadline` - Describe action

Describe the action and its outcome.

Example of usage: 

`deadline ip assignment /by today 2359`

Expected outcome:

Description of the outcome.

```
Got it. I've added this task:
[D][ ]ip assignment (by today 2359)
Now you have 1 tasks in the list.
```

### `event` - Describe action

Describe the action and its outcome.

Example of usage: 

`event final exam /at 29 February 9pm`

Expected outcome:

Description of the outcome.

```
Got it. I've added this task:
[E][ ]final exam (at 29 February 9pm)
Now you have 3 tasks in the list.
```


### `find` - Describe action

Describe the action and its outcome.

Example of usage: 

`find final`

Expected outcome:

Description of the outcome.

```
Here are the matching tasks in your list: 
3.[E][ ]final exam (at 29 February 9pm)
```

### `mark` - Describe action

Describe the action and its outcome.

Example of usage: 

`mark 3`

Expected outcome:

Description of the outcome.

```
[T][ ]enter description here
[D][ ]ip assignment (by today 2359)
[E][X]final exam (at 29 February 9pm)
```

### `unmark` - Describe action

Describe the action and its outcome.

Example of usage: 

`unmark 3`

Expected outcome:

Description of the outcome.

```
[T][ ]enter description here
[D][ ]ip assignment (by today 2359)
[E][ ]final exam (at 29 February 9pm)
```

### `list` - Describe action

Describe the action and its outcome.

Example of usage: 

`list`

Expected outcome:

Description of the outcome.

```
Here are the tasks in your list : 
1.[T][ ]enter description here
2.[D][ ]ip assignment (by today 2359)
3.[E][ ]final exam (at 29 February 9pm)
```

### `delete` - Describe action

Describe the action and its outcome.

Example of usage: 

`delete 2`

Expected outcome:

Description of the outcome.

```
Noted. I've removed this task:
[D][ ]ip assignment (by today 2359)
Now you have 2 tasks in the list.
[T][ ]enter description here
[E][ ]final exam (at 29 February 9pm)

```

