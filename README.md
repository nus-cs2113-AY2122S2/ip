# Duke project template

This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```

## User Guide

#### 1. List all available tasks: list <br>
List all tasks <br>
Format: list <br>

#### 2. Create a Todo: todo <br>
Create a todo task <br>
Format: todo td/TASK_DESCRIPTION

#### 3. Create an Event: event <br>
Create a event task <br>
Format: event ed/EVENT_DESCRIPTION /at d/DURATION


#### 4. Create a Deadline: deadline <br>
Create a deadline task <br>
Format: deadline dd/DEADLINE_DESCRIPTION /by d/DUEDATE

#### 5. Mark a Task: mark <br>
Mark a task as completed <br>
Format: mark tn/TASK_NUMBER

#### 6. Unmark a Task: unmark <br>
unmark a task which has not been completed <br>
Format: unmark tn/TASK_NUMBER

#### 7. Delete a Task: delete <br>
delete a task from task list <br>
Format: delete tn/TASK_NUMBER

#### 8. Find a Task: find <br>
Search for a task in the task list by keyword <br>
Format: find kw/KEYWORD

#### 9. Exit Program: bye <br>
To close program and store task list.
Format: bye
