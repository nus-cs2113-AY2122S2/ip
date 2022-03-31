# Hello, I'm Duke, Your Personal Task Manager

   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```

I am capable of managing tasks that you need to keep track of.

## Features

### 1. Create a New Task
Create a new task that can be a to-do, a deadline or an event. The
new task will be added to my task list.

### 2. List Tasks
List the tasks that I currently have in my tasklist.

### 3. Mark Task
Once you have finished a task I can mark it as completed.

### 4. Unmark Task
If you want to unmark a previously completed task, it can be updated.

### 5. Delete Task
Delete an existing task from the task list.

### 6. Find task
Find an existing task from the task list using a search keyword.
All suitable matches will be listed.

### 7. Data File Read and Write
Automatically updates the data file to store changes made to a task list.
All tasks can be restored at the start of a new session.

## Getting Started
1. Download the JAR file from the repository's release page.
2. Create a new folder and transfer Duke.jar into it.
3. From terminal navigate to the new folder.
4. Start up Duke with ```java -jar Duke.jar ```

## User Manual

## ```todo``` - Create a new todo task
Creates a new task that is a todo.

#### Syntax
```todo <description>```

| Parameter | Description |
| ----------- | ----------- |
| ```<description> ```| Description of the todo task |

#### Example
    todo make a reservation

#### Expected Outcome
    ____________________________________
    Got it. I've added this task:
        [T][ ] make a reservation
    Now you have 4 tasks in the list.
    ____________________________________

A new todo has been added to Duke's task list.

## ```deadline``` - Create a new deadline task
Creates a new task that is a deadline.

#### Syntax
```deadline <description> /<by>```

| Parameter | Description |
| ----------- | ----------- |
| ```<description> ```| Description of the deadline task|
| ```<by> ```| Due date of the deadline|

#### Example
    deadline submission /23/02/2022

#### Expected Outcome
    ____________________________________
    Got it. I've added this task:
        [D][ ] submission (by: 23/02/2022 00:00)
    Now you have 5 tasks in the list.
    ____________________________________

A new deadline has been added to Duke's task list, with a due date of 23/02/2022
at 00:00 hours.

## ```event``` - Create a new event task
Creates a new task that is an event.

#### Syntax
```event <description> /<eventTime>```

| Parameter | Description |
| ----------- | ----------- |
| ```<description> ```| Description of the event task|
| ```<eventTime> ```| Time that the event is scheduled for|

#### Example
    event meeting /4pm to 6pm

#### Expected Outcome
    ____________________________________
    Got it. I've added this task:
        [E][ ] meeting (at: 4pm to 6pm)
    Now you have 6 tasks in the list.
    ____________________________________

A new event has been added to Duke's task list, with the specified event time.

## ```list``` - List tasks
Lists all the tasks in the task list.

#### Syntax
```list```

#### Example
    list

#### Expected Outcome
    ____________________________________
    Here are the tasks in your list:
    1: [T][ ] make a reservation
    2: [D][ ] submission (by: 23/02/2022 00:00)
    3: [E][ ] meeting (at: 4pm to 6pm)
    ____________________________________

## ```mark``` - Mark Task as Completed
Mark a task as completed.

#### Syntax
```mark <task index>```

| Parameter | Description |
| ----------- | ----------- |
| ```<task index> ```| The numerical index of the task to be marked|

#### Example
    mark 1

#### Expected Outcome
    ____________________________________
    Nice! I've marked this task as done:
    [T][X] make a reservation
    ____________________________________

## ```unmark``` - Unmark Task as Completed
Unmarks a task that was previously marked as completed.

#### Syntax
```unmark <task index>```

| Parameter | Description |
| ----------- | ----------- |
| ```<task index> ```| The numerical index of the task to be unmarked|

#### Example
    unmark 1

#### Expected Outcome
    ____________________________________
    OK, I've marked this task as not done yet:
    [T][ ] make a reservation
    ____________________________________

## ```delete``` - Delete a Task
Deletes a task from the task list.

#### Syntax
```delete <task index>```

| Parameter | Description |
| ----------- | ----------- |
| ```<task index> ```| The numerical index of the task to be deleted|

#### Example
    delete 1

#### Expected Outcome
    ____________________________________
    Noted. I've removed this task:
    [T][ ] make a reservation
    Now you have 2 tasks in the list
    ____________________________________

## ```find``` - Find a Task
Searches for task(s) that match the search from the task list.

#### Syntax
```find <search query>```

| Parameter | Description |
| ----------- | ----------- |
| ```<search query> ```| The keyword used to search for matching task(s)|

#### Example
    find m

#### Expected Outcome
    ____________________________________
    Here are the matching tasks in your list:
    1: [D][ ] submission (by: 23/02/2022 00:00)
    2: [E][ ] meeting (at: 4pm to 6pm)
    ____________________________________

## ```bye``` - Exit Duke
Stops the Duke application and saves the tasks for a future session.

#### Syntax
```bye```

#### Expected Outcome
    ____________________________________
    Bye. Hope to see you again soon!
    ____________________________________