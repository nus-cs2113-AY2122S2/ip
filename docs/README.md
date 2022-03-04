# User Guide

## Features 

### Manage different types of tasks

Using Duke, you can keep track of the following types of tasks:

1) To-do`s: tasks without specific dates or times
2) Deadlines: tasks to be done by certain dates/times
3) Events: tasks to do at specific dates/times

### Store your tasks in a separate file

Using Duke, you are able to keep all of these tasks stored in a separate file, so that 
you will not lose your tasks upon closing your Duke session

## Using Duke for the first time: Required Files

`taskList.txt` - Please provide a text file named taskList.txt in the *same directory as the .jar file*
This file will store all tasks provided, and can be empty upon creation.

If taskList.txt is *not* provided, Duke will create it upon ending its first run. However, during this first
run it will start with the message "file not found".

After the file is provided, Duke will write and read tasks to it in the proper format.

## Usage

### `Keyword` - Describe action

1) `list` - This will list out all current tasks present in Duke with their corresponding task numbers


3) `todo [description of task]` - When formatted correctly, a new task will be added with the description provided
    - Example: todo eat dinner
   

4) `deadline [description of deadline] /by [due date of deadline]` - When formatted correctly, a new deadline with a due date will
   be added to Duke. **NOTE**: if the due date is formatted in dd/mm/yyyy (dd = 2 digit day, mm = 2 digit month, yyyy = 4 digit
   year) and is a valid date, Duke will refactor this and post the day of the week, month, and the year to your taskList.txt
    - Example: deadline read my book /by 12/12/2030
   

5) `event [description of deadline] /at [do date of event]` - When formatted correctly, a new event with a do date will
   be added to Duke. **NOTE**: if the due date is formatted in dd/mm/yyyy (dd = 2 digit day, mm = 2 digit month, yyyy = 4 digit
   year) and is a valid date, Duke will refactor this and post the day of the week, month, and the year to your taskList.txt
    - Example: event attend party /at 10/03/2022
   

6) `mark [task number]` - If a valid task number is provided, Duke will mark it as done
    - Example: mark 1
   

7) `unmark [task number]` - If a valid task number is provided, Duke will mark it as no longer done
    - Example: unmark 2
   

8) `delete [task Number]` - If a valid task number is provided, Duke will remove it from your task list
    - Example: delete 5
   

9) `find [item to search for]` - Duke will search your tasks for the item provided. Duke will return every task that has a matching
   item for *any* word provided in your search, including dates and times. This includes partial matching. For example,
   the search `wed` will return a task with the date `WEDNESDAY`
    - Example: find wed
   

10) `bye` - Exits the Duke program
