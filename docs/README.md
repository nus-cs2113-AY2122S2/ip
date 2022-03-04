

# Welcome to Duke!
Duke is a CLI-based todo application.
# Getting started
After running the JAR file, an output file named "Duke.txt" will be created in the same directory of the JAR.  Output will be saved into the file before the program exits, and the file will be reread the next time Duke runs.

To run the file, from the terminal, go to the same directory as your Duke.jar file, and run the command `java -jar Duke.jar`


# Features and usage
| Notation | Meaning  |
|--|--|
| [T] | todo  |
| [D] | deadline|
| [E] | event |
| [ ] | incomplete task
| [X] | completed task




## Add
There are three types of tasks to be added. Namely, todo, deadline and event.
### Todo

To add a todo,  command should be typed in the format
`todo (taskname)`
Example of a valid input:
`todo get groceries`

Sample usage:

    todo buy groceries
        ____________________________________________________________
	    Got it. I've added this task:
	    [T][ ] buy groceries
	    Now you have 1 task in the list.
	    ____________________________________________________________



### Event / Deadline

To add an event or a deadline, command should be typed in the format `event (taskname) /(dd-MM-yyyy HH:mm)` or
`event (taskname) /(additional info)`

:white_check_mark: Sample usage with valid input:


    deadline cs2113t project /tomorrow
        ____________________________________________________________
        Got it. I've added this task:
        [D][ ] cs2113t project (by: tomorrow)
        Now you have 2 tasks in the list.
        ____________________________________________________________
    event Mark's birthday party /07-03-2022 22:00
        ____________________________________________________________
        Got it. I've added this task:
        [E][ ] Mark's birthday party (on: Mar 07 2022 22:00)
        Now you have 3 tasks in the list.
        ____________________________________________________________



:x: Example of a invalid input:

	deadline hello /
	event / 




## List
To list all the tasks, simply type
`list`


Sample usage:

    list
	    ____________________________________________________________   
	    1. [T][ ] buy groceries
	    2. [D][ ] cs2113t project (by: tomorrow)
	    3. [E][ ] Mark's birthday party (on: Mar 07 2022 22:00)
	    ____________________________________________________________

## Delete
To delete a task, type in the command `delete (task number)`

Sample usage: 

    delete 2
	    ____________________________________________________________
	    Noted. I've removed this task:
	    [D][ ] cs2113t project (by: tomorrow)
	    Now you have 2 tasks in the list.
	    ____________________________________________________________




## Mark/Unmark
To mark the task as completed, type in `mark (task number)`.
Similarly, type in `unmark (task number)` to mark the task as incomplete.

Sample usage:

    mark 1
	    ____________________________________________________________
	    [T][X] buy groceries
	    ____________________________________________________________
	unmark 1
	    ____________________________________________________________
	    [T][ ] buy groceries
	    ____________________________________________________________

## Find
To find task(s) containing a specific keyword, type in `find (keyword)`

Sample usage:

    find groceries
	    ____________________________________________________________
	    Here are the matching tasks in your list:
	    1. [T][ ] buy groceries
	    ____________________________________________________________

## Bye
To exit the program, simply type "bye". The program terminates and the current state of the tasks is saved into "Duke.txt".

Sample usage:

    bye
	    ____________________________________________________________
	    Bye. Hope to see you again soon!
	    ____________________________________________________________


    

