# Vera User Guide

## Features (Types of commands available)

1. `help`: provides a list of supported commands.
2. `list`: shows the list of added tasks.
3. `mark`: marks a task as done.
4. `unmark`: marks a task as undone.
5. Task adding:
   1. `todo`: creates a todo task.
   2. `deadline`: creates a deadline task.
   3. `event`: creates an event task.
6. `delete`: deletes a task from the list.
7. `clear`: deletes all tasks from the list.
8. `find`: searches for task(s) that match the given keyword(s).
9. `bye`: exits the application.

## Note on syntax

The above commands mentioned will have examples of the correct syntax to use. 

Parameters introduced will have two forms, either as `[optional parameter]` or `<must-include parameter>`.


## Feature Details

### 1. `help`

This command displays a list of commands supported by Vera.

Each command displayed shows a detailed description about its usage.

#### **Syntax**

```
help [command] / [quick start]
```

| Parameter       | Description                                                                                                                           |
|-----------------|---------------------------------------------------------------------------------------------------------------------------------------|
| `[command]`     | Adding the optional `[command]` displays information specific to that command. Otherwise, information for all commands will be shown. |
| `[quick start]` | Adding the optional`[quick start]` displays a brief summary of all command syntax.                                                    |


	
#### **Example #1**

```
> help list
```

#### **Expected Outcome #1**

```
____________________________________________________________
List: Displays a list of tasks added and shows 
whether or not certain tasks are marked.
____________________________________________________________
```

#### **Example #2**

```
> help quick start
```

#### **Expected Outcome #2**

```
____________________________________________________________
Command input quick start guide:
1) List: list
2) Mark: mark <list_index>
3) Unmark: unmark <list_index>
4) Todo: todo <task_description>
5) Deadline: deadline <task_description> /by <task_date>
6) Event: event <task_description> /at <task_date>
7) Delete: delete <list_index>
8) Find: find <keyword>
9) Clear: clear
9) Bye: bye

For more information on the command you wish to execute,
enter 'help <command>' e.g. help todo
____________________________________________________________
```


### 2. `list`

This command displays a list of all added tasks.

#### **Syntax**

```
list
```
#### **Sample Outcome**

When there are some tasks added:

```
____________________________________________________________
Here are the tasks in your list:
1. [T] [ ] read book
2. [D] [ ] return book (by: 06 Jun 2022, Mon)
3. [E] [ ] project meeting (at: 06 Aug 2022, Sat 02:00 PM)
____________________________________________________________
A total of 3 item(s) have been found!
____________________________________________________________
```

### 3. `mark`

This command marks a task as done.

#### **Syntax**

```
mark <task_index>
```

	
| Parameter      | Description                                                                                   |
|----------------|-----------------------------------------------------------------------------------------------|
| `<task_index>` | The index of the task (when referencing to its number placement in the list) to mark as done. |


#### **Example**

Using the list of task from `list` feature as an example,

```
> mark 1
```

#### **Sample Outcome**

```
____________________________________________________________
Nice! I've marked this task as done:
  [T] [X] read book
____________________________________________________________
```

### 4. `unmark`

This command marks a task as undone.

#### **Syntax**

```
mark <task_index>
```
	
	

| Parameter      | Description                                                                                     |
|----------------|-------------------------------------------------------------------------------------------------|
| `<task_index>` | The index of the task (when referencing to its number placement in the list) to mark as undone. |

	
#### **Example**

Using the list of task from `list` feature as an example,

```
> unmark 1
```

#### **Sample Outcome**

```
____________________________________________________________
Ok, I've marked this task as not done yet:
  [T] [ ] read book 
____________________________________________________________
```

### 5.i. `todo`

This command adds a *todo* task to the list. A `todo` contains only a task description. 

To save other forms of data such as task date and/or time, refer to the `deadline` and `event` commands.

### **Syntax**

```
todo <task_description>
```

	
| Parameter            | Description                       |
|----------------------|-----------------------------------|
| `<task_description>` | Information about the *todo* task |
	


### **Example**

```
> todo join sports club
```

### **Sample Outcome**

```
____________________________________________________________
Got it. I've added this task:
  [T] [ ] join sports club
Now you have 3 task(s) in the list.
____________________________________________________________
```


	

Note: The number of tasks in the expected outcome depends on the number of tasks the user has previously added to the task list.


### 5.ii. `deadline`

This command adds a *deadline* task to the list. A `deadline` accepts both a task description and a task date to complete the task by.

### **Syntax**

```
deadline <task_description> /by <task_date> [task_time]
```

	
| Parameter            | Description                                                                                                                  |
|----------------------|------------------------------------------------------------------------------------------------------------------------------|
| `<task_description>` | Information about the *deadline* task.                                                                                       |
| `<task_date>`        | Information about the due date of the task. Format is in `yyy/MM/dd `, where year is in 4-digits, month and day in 2-digits. |
| `[task_time]`        | Information on when to complete the task by. Format is in `[HHmm]`, where `HHmm` is the time given in 24-hour format.        |
	


### **Example**

```
> deadline return boook /by 2022/06/06
```

### **Sample Outcome**

````
____________________________________________________________
Got it. I've added this task:
  [D] [ ] return book (by: 06 Jun 2022, Mon)
Now you have 2 task(s) in the list.
____________________________________________________________
````

### 5.iii. `event`

This command adds an *event* task to the list. An `event` accepts both a task description and a task date of when the *event* happens.

### **Syntax**

```
event <task_description> /at <task_date> [task_time]
```

	
| Parameter            | Description                                                                                                                |
|----------------------|----------------------------------------------------------------------------------------------------------------------------|
| `<task_description>` | Information about the *event* task.                                                                                        |
| `<task_date>`        | Information about of the *event* occurs. Format is in `yyyy/MM/dd `, where year is in 4-digits, month and day in 2-digits. |
| `[task_time]`        | Information on the time the *event* occurs. Format is in `[HHmm]`, where `HHmm` is the time given in 24-hour format.       |

	

### **Example**

```
> event project meeting /at 2022/08/06 1400
```

### **Sample Outcome**

````
____________________________________________________________
Got it. I've added this task:
  [E] [ ] project meeting (at: 06 Aug 2022, Sat 02:00 PM)
Now you have 3 task(s) in the list.
____________________________________________________________
````

### 6. `delete`

This command deletes a task in the list.

### **Syntax**

```
delete <task_index>
```


| Parameter      | Description                                                                            |
|----------------|----------------------------------------------------------------------------------------|
| `<task_index>` | The index of the task (when referencing to its number placement in the list) to delete |


### **Example**

```
> delete 1
```

### **Sample Outcome**

Using the list of tasks from `list` feature as an example,

```
____________________________________________________________
Okay. I've removed this task:
  [T] [ ] read book
Now you have 2 task(s) in the list.
____________________________________________________________
```

### 7. `clear`

This command deletes all tasks in the list.

### **Syntax**

```
clear
```

### **Sample Outcome**

Assuming that there are tasks inside the list,

```
____________________________________________________________
Are you sure you want to delete all tasks? [Y/N]
You will not be able to recover them after deleting. 
____________________________________________________________
```

After confirming deletion by entering `Y`,

```
____________________________________________________________
Understood. Proceeding to delete
all current tasks in the list..........
____________________________________________________________
Done! Now you have 0 task(s) in the list.
____________________________________________________________
```

### 8. `find`

This command searches and outputs relevant tasks based on the search keyword

### **Syntax**

```
find [task_description] /date [task_date]
```

Note: At least one search keyword must be present in the command input, i.e. either `[task_description]` or `[task_date]`


| Parameter            | Description                                                                                                                                                                    |
|----------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `[task_description]` | Information about the task.                                                                                                                                                    |
| `[task_date]`        | Information about the date of the task. Format is in `yyyy/MM/dd [HHmm]`, where year is in 4-digits, month and day in 2-digits and `HHmm` is the time given in 24-hour format. |


	
### **Example #1**

Using the list of tasks from `list` feature as an example,

```
> find book
```

### **Sample Outcome #1**

```
____________________________________________________________
Here are the matching tasks in your list:
1. [T] [ ] read book
2. [D] [ ] return book (by: 06 Jun 2022, Mon)
____________________________________________________________
```

### **Example #2**

Using the list of tasks from `list` feature as an example,

```
> find /date 2022/06/06
```

### **Sample Outcome #2**

```
____________________________________________________________
Here are the matching tasks in your list:
3. [D] [ ] return book (by: 06 Jun 2022, Mon)
____________________________________________________________
```

### **Example #3**

Using the list of tasks from `list` feature as an example,

```
> find /date 2022/08/06 1400
```

```
____________________________________________________________
Here are the matching tasks in your list:
[E] [ ] project meeting (at: 06 Aug 2022, Sat 02:00 PM)
____________________________________________________________
```

### 9. `bye`

This command exits the application

### **Syntax**

```
bye
```

### **Expected Outcome**

```
____________________________________________________________
Bye. Hope to see you again soon! :)
____________________________________________________________
```


## Saving tasks

There is no need to manually save the tasks. Vera will automatically save any tasks that has been added or modified.

### Editing save file

All saved tasks are stored inside a text file name "vera.txt' under the address path "JAR_file_location/data/vera.txt". 

Advanced users are welcome to update the data directly by editing that save file.

Caution: Should any changes cause the format of the data to be invalid, Vera will discard all saved data and start with an empty data file on the next run.
