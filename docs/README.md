# User Guide

## Features 

### Create a New Task

Create a new task that is a deadline, an event, or a to-do, and add it to Sora's 
list for her to keep track of your tasks.

### Display Your Tasks

Get Sora to display all the tasks that you have created and given to her to keep track on.

### Mark Your Tasks as Completed

Tell Sora that you have completed a task and she will update it accordingly in her list of tasks.

### Delete an Existing Task

Remove a task that you no longer wish Sora to keep track of.

### Search for an Existing Task

Enter a search query and Sora will show you the tasks that matches your search keywords.

### Automatic Saving of Task Data to Local Storage

Every time you create, update, or delete a task, Sora automatically saves these changes into a file
that is stored locally on your system's storage. This way, your tasks will remain after you bid
Sora farewell for the day and will be available the next time you start her up.

---

## Usage

### `deadline` - Create a new deadline task

Creates a new task that is a deadline.

**Syntax**
```
deadline <task description> /by <due date>
```
| Parameters | Description |
| --- | --- |
| `<task description>` | Information about this deadline task. |
| `<due date>` | When this task is due. Format: `DD/MM/YYYY HHmm`, where `HHmm` is the time in 24-hour format. |

**Example**
```
> deadline Project submission /by 12/04/2022 2359
```
**Expected Outcome**
```
------------------------------------------------------------
Okay, I've added your new task to my list:

	[D][ ] Project submission (due on: Tue, 12 Apr 2022, 11:59 PM)

------------------------------------------------------------
```
A new deadline task called 'Project submission' with a deadline of 12 April 2022 at 11:59pm is created
and added to Sora's list.





---

### `Keyword` - Describe action

Describe the action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```
---
## File Storage Information

When you start Sora for the first time on a computer, the following are created:
- A directory named `CS2113T_iP_Sora_Resources`
- A text file named `data.txt` residing within the aforementioned directory.

On subsequent boot ups, Sora will automatically read the file to load the tasks created in previous
sessions onto the current one.

### Caution

The directory and text file will be created in your terminal's **current working directory**.
Thus, it is highly recommended that you **set your working directory to the directory where
Sora.jar is located in** before starting up Sora.

### Recommendation
1. Create an empty directory and place `Sora.jar` inside it.
2. Set your terminal's current working directory to the aforementioned directory.
3. Run `Sora.jar` from the directory.

This way, `Sora.jar` and your file data will be stored within the same directory.