# User Guide

## Features 

### Add a new To-Do task

Users may add a task that is to be done without a particular deadline.

### Add a new Deadline task

Users may add tasks that have a set due date by indicating that it is a Deadline task.

### Add a new Event task

Users may add events that are scheduled to occur some time in the future.

### List out all tasks

Users may list out all added tasks in the order that they were added.

### Remove a task

Users may remove a task based on its current index in the list. Users are advised to use `list` to verify the
task's index before attempting to remove a task.

### Look for a task using a keyword

Users may use search terms to look for all tasks that contain a particular keyword. A list of tasks will be
displayed containing the keyword.

### Mark or unmark a task as Done/Not Done

Users may mark or unmark a specific task as Done or Not Done using its current index in the list.
Users are advised to use `list` to verify the task's index before attempting to mark or unmark it.

## Usage

### `deadline` - Add a new Deadline task

To add a new Deadline task, the keyword `deadline` is used followed by a short description and a delimiter of `/by`,
followed by the due date, in DD-MM-YYYY format.

Example of usage: 

`deadline Finish CS2113 iP /by 18-02-2022`

Expected outcome:

The Deadline task will be added to the list of tasks.

```
[D] [ ] Finish CS2113 iP (by: 18-02-2022)

Total: 1 task(s) in the list!
Task added!
```



### `list` - List out all currently saved tasks

Example of usage:

`list`

Expected outcome:

```
1. [D] [ ] Finish CS2113 iP (by: 18-02-2022)
```

### `delete` - Delete a particular task that exists in the list

After deleting a task, the task is shown before it is removed from the list.

Example of usage:

`delete 1`

Expected outcome:
```
[D] [ ] Finish CS2113 iP (by: 18-02-2022)

Total: 0 task(s) in the list!
Task deleted!

```