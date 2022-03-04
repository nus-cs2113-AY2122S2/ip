#Duke
Thank you for downloading Duke!

Duke is the friendliest task manager you will find.

You can use him and his processes to store your tasks in the form of to-do's, events, and deadline. While using Duke, you will be
able to add, delete, and search for tasks at will.

#How to Use Duke

##Necessary files to provide:
'taskList.txt' - Please provide a text file named taskList.txt in the *same directory as the .jar file*
This file will store all tasks provided, and can be empty upon creation.

If taskList.txt is *not* provided, Duke will create it upon ending its first run. However, during this first
run it will start with the message "file not found".

After the file is provided, Duke will write and read tasks to it in the proper format.

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
item for *any* word provided in your search, including dates and times. This includes partial matching. For example,
the search 'wed' will return a task with the date 'WEDNESDAY'

10) 'bye' - Exits the Duke program


