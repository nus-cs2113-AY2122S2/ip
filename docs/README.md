Add Task
Adds a task to the current list for KaiKai to handle.

Delete Task
Delete a task from the current list.

List Tasks
Lists all tasks.

Mark Task
Mark a task as done.

Unmark Task
Mark a task as undone.

Find Task
Find all tasks matching the search term.

Exit
Closes the app.

Usage
add - Adding a task
Add a task to the task list

Example of usage:

Adding Tasks
Adds a task to Duke to manage

To-do item

Format: todo DESCRIPTION

Adds a todo with a DESCRIPTION
Event item

Format: event DESCRIPTION /at DATE

Adds an event with a DESCRIPTION and a DATE representing when the event is happening
Deadline item

Format: deadline DESCRIPTION /by DATETIME

Adds a deadline task with a DESCRIPTION and a deadline DATETIME
DATETIME must be in the format yyyy-MM-dd HHmm
e.g. deadline assignment /by 2022-02-14 1800
delete - Deleting a task
Delete a task from the task list

Add task to Duke

Example of usage:

deadline return book /by 2022-12-02 1800

Expected outcome:

____________________________________________________________
Got it. I've added this task:
[D][ ] return book (by: 02 Dec 2022 06:00 PM)
Now you have 1 tasks in the list
____________________________________________________________

Example of usage:

delete

Expected outcome:

Deleting tasks
Remove task from Duke

Example of usage:

delete 1

Expected outcome:

____________________________________________________________
Noted. I've removed this task:
[D][ ] return book (by: 12 Feb 2022 06:00 PM)
Now you have 0 tasks in the list
____________________________________________________________

list - Listing all tasks
Delete a task from the task list

Example of usage:

list

Expected outcome (example list):

1. [T][X] read book
2. [E][ ] mug (start: 01/01/2001 01:01, end: 01/01/2001 01:01)
3. [D][ ] mug more (by: 01/01/2001 01:01)
4. [E][ ] submit ip (start: 11/12/1234 11:11, end: 11/02/1234 00:00)
mark - Mark a task
Mark a task as done.

Example of usage:

mark

Expected outcome (example list):

____________________________________________________________
Here are the tasks in your list:
1. [T][ ] borrow book
2. [D][ ] return book (by: 02 Dec 2022 06:00 PM)
3. [E][ ] project meeting (at: Monday 2-4pm)
_________________________________________________
Which task would you like to mark as completed?
Shows the current list of tasks and prompts for which task to mark (by index).

unmark - Unmark a task
Mark a task as undone.


bye - Exit
Closes and app

Example of usage:

bye

Expected outcome:

Bye! Hope to see you again!
