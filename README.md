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

#Duke
Thank you for downloading Duke!

Duke is the friendliest task manager you will find.

You can use him and his processes to store your tasks in the form of to-do's, events, and deadline. While using Duke, you will be
able to add, delete, and search for tasks at will.

#How to Use Duke

##Necessary files to provide:
'taskList.txt' - Please provide a text file named taskList.txt in the *same directory as Duke*
This file will store all 

taskList.txt should have the following file path:  /src/main/java/Duke/taskList.txt

Duke should have the following:                    /src/main/java/Duke/Duke.java

##Running Duke

Upon starting Duke, he should greet you with the following message:

Hello! I'm Duke. What can I do for you?

After this point, commands can be entered to Duke via the command line

##Commands and their Formats

1) 'list' - This will list out all current tasks present in Duke with their corresponding task numbers

2) 'todo [description of task]' - When formatted correctly, a new task will be added with the description provided

3) 'deadline [description of deadline] /by [due date of deadline]' - When formatted correctly, a new deadline with a due date will
be added to Duke. **NOTE**: if the due date is formatted in dd/mm/yyyy (dd = 2 digit day, mm = 2 digit month, yyyy = 4 digit
year) and is a valid date, Duke will refactor this and post the day of the week, month, and the year to your taskList.txt

4) 'event [description of deadline] /at [do date of event]' - When formatted correctly, a new event with a do date will
be added to Duke. **NOTE**: if the due date is formatted in dd/mm/yyyy (dd = 2 digit day, mm = 2 digit month, yyyy = 4 digit
year) and is a valid date, Duke will refactor this and post the day of the week, month, and the year to your taskList.txt

5) 'mark [task number]' - If a valid task number is provided, Duke will mark it as done

6) 'unmark [task number]' - If a valid task number is provided, Duke will mark it as no longer done

7) 'delete [task Number]' - If a valid task number is provided, Duke will remove it from your task list

8) 'find [item to search for]' - Duke will search your tasks for the item provided. Duke will return every task that has a matching
item for *any* word provided in your search, including dates and times.

9) 'bye' - Exits the Duke program


