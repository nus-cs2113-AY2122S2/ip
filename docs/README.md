# User Guide
> Spooky is a **Personal Assistant Chatbot** that helps a person keep track of various things. 
> It can be accessed via a **Command Line Interface (CLI)**.

---

## Starting the program
> Follow these steps to download, set-up and run the program.

1. Download the JAR file from the releases.
2. Place the JAR file in a folder of your choice.
   - A text file will be created when you run the jar file to save your tasks for future uses.
3. Download jdk 11 or update your current version to Java 11.
   - You may wish to download the switchJava.bat file and change the file path to reflect the file path where 
   jdk is stored at.
   - Open Command Prompt in the folder which you have placed the switchJava.bat file.
   - Run the switchJava.bat to make sure that Java 11 is activated by entering the follow command:
   `switchJava.bat`
4. Open Command Prompt in the folder which you have placed the JAR file and enter the following command to
run the jar file.
   `java -jar spooky.jar`
   ![Welcome]()

---

## Features
> In order for the features to be used, the format has to be followed strictly, else error will be thrown.

### Feature list:
1. Add a ToDo: `todo`
2. Add a Deadline: `deadline`
3. Add an Event: `event`
4. List the tasks: `list`
5. Delete a task: `delete`
6. Mark a task as done: `mark`
7. Mark a task as NOT done: `unmark`
8. Find tasks using keyword: `find`
9. Filters tasks using date: `date`
10. Exit the program: `bye`

---

### Feature - Add a ToDo
> A ToDo is a task that is to be done and does not come with a deadline or a venue.

- Format: `todo \[DESCRIPTION\]`
- Example: 
  ```todo CS2113T ip```
  ![Add ToDo]()
- Arguments:
  - \[DESCRIPTION\]: The description of the ToDo.
- Errors:
  - If no description is provided, the following error message will be printed:
    _What are you going to do? LOL_

### Feature - Add a Deadline
> A Deadline is a task that is to be done by a certain date.

- Format: `deadline \[DESCRIPTION\] /by \[DATE]\`
- Example:
  ```deadline CS2113T ip /by 2022-03-04```
  ![Add Deadline]()
- Arguments:
    - \[DESCRIPTION\]: The description of the ToDo.
    - /by \[DATE]\: The date of the deadline. The format of the date has to be in the YYYY-MM-DD format.
- Errors:
  - If `/by` is missing, the following error message will be printed:
    _You don't know basic grammar or what?_
  - If no description is provided, the following error message will be printed:
    _What is your deadline for..?_
  - If no date is provided, the following error message will be printed:
    _You have a deadline but you don't have a deadline?_
  - If the date is in the wrong format, the following error message will be printed:
    _Please input the date in the following format: yyyy-mm-dd_


### Feature - Add an Event
> An Event is a task that is to be done at a certain location on a certain date.

- Format: `event \[DESCRIPTION\] /at \[LOCATION\] /on \[DATE\]`
- Example:
  ```event CS2113T Practical Exam /at NUS COM1 /on 2022-04-16```
  ![Add Event]()
- Arguments:
  - \[DESCRIPTION\]: The description of the Event.
  - /by \[DATE]\: The date of the Event. The format of the date has to be in the YYYY-MM-DD format.
- Errors:
  - If `/at` is missing, the following error message will be printed:
    _Yea... I know you don't have an event because you're a loner._
  - If no description is provided, the following error message will be printed:
    _What is your deadline for..?_
  - If `/on` is missing, the following error messgae will be printed:
    _The event has to be ON a certain date right?_
  - If no location is provided, the following message will be printed:
    _So your event is at nowhere-land?_
  - If no date is provided, the following error message will be printed:
    _YAY! Your event is never going to happen :DDDD_
  - If the date is in the wrong format, the following error message will be printed:
    _Please input the date in the following format: yyyy-mm-dd_


---

### Feature - List the tasks
> Lists all the tasks that is saved by the program.

- Format: `list`
- Example:
  ```list```
  ![List Tasks]()

---

### Feature - Delete a task
> Deletes a task based on the index of the task.

- Format: `delete \[INDEX\]`
- Example:
  ```delete 1```
  ![Delete Task]()
- Arguments:
    - \[INDEX\]: The index of the task to be deleted.
- Errors:
  - If no index or more than 1 index is provided, the following error message will be printed:
    _Please input the index and only the index_
  - If the index provided is out of range, the following error message will be printed:
    _The index is out of range, please try again._
  - If the index provided is not an integer, the following error message will be printed:
    _The index should be an integer, please try again._

---

### Feature - Mark a task as done
> Marks a task, based on the index, as done.

- Format: `mark \[INDEX\]`
- Example:
  ```mark 1```
  ![Mark Task As Done]()
- Arguments:
    - \[INDEX\]: The index of the task to be marked as done.
- Errors:
  - If no index or more than 1 index is provided, the following error message will be printed:
    _Please input the index and only the index_
  - If the index provided is out of range, the following error message will be printed:
    _The index is out of range, please try again._
  - If the index provided is not an integer, the following error message will be printed:
    _The index should be an integer, please try again._

---

### Feature - Mark a task as NOT done
> Marks a task, based on the index, as NOT done.

- Format: `unmark \[INDEX\]`
- Example:
  ```unmark 1```
  ![Mark Task As NOT Done]()
- Arguments:
    - \[INDEX\]: The index of the task to be marked as NOT done.
- Errors:
  - If no index or more than 1 index is provided, the following error message will be printed:
    _Please input the index and only the index_
  - If the index provided is out of range, the following error message will be printed:
    _The index is out of range, please try again._
  - If the index provided is not an integer, the following error message will be printed:
    _The index should be an integer, please try again._


---

### Feature - Find tasks using keyword
> Finds the tasks in the list of tasks that contains the keyword provided.

- Format: `find \[KEYWORD\]`
- Example:
  ```find CS2113T```
  ![Find Tasks]()
- Arguments:
  - \[KEYWORD\]: The keyword to be searched for among the list of tasks.
- Errors:
  - If no keyword is provided, the following error message will be printed:
    _You want me to find nothing or everything...?_

---

### Feature - Filters tasks using date
> Filters the tasks in the list of tasks according to the date provided.

- Format: `date \[DATE\]`
- Example:
  ```date 2022-03-04```
  ![Filter Tasks]()
- Arguments:
  - \[DATE\]: The date used to filter the tasks. The format of the date has to be in the YYYY-MM-DD format.
- Errors:
  - If no date is provided or more than one 1 date is provided, the following error will be printed:
    _Please input the date in the correct format and only the date_
  - If the date provided is in the wrong format, the following error will be printed:
    _Please input the date in the following format: yyyy-mm-dd_

---

### Feature - Exit the program
> Exits the program and prints the goodbye message.

- Format: `bye`
- Example:
  ```bye```
  ![Exit Program]()

---

## Command Summary
|        **Action**        |                      **Format**                       |
|:------------------------:|:-----------------------------------------------------:|
|        Add a ToDo        |                `todo \[DESCRIPTION\]`                 |
|      Add a Deadline      |        `deadline \[DESCRIPTION\] /by \[DATE\]`        |
|       Add an Event       | `event \[DESCRIPTION\] /at \[LOCATION\] /on \[DATE\]` |
|      List the tasks      |                        `list`                         |
|      Delete a task       |                  `delete \[INDEX\]`                   |
|   Mark a task as done    |                   `mark \[INDEX\]`                    |
| Mark a task as NOT done  |                  `unmark \[INDEX\]`                   |
| Find tasks using keyword |                  `find \[KEYWORD\]`                   |
| Filters tasks using date |                    `date \[DATE\]`                    |
|     Exit the program     |                         `bye`                         |