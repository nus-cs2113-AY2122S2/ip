
# Welcome to Duke!
Duke is a CLI-based todo application. 
# Getting started
After running the JAR file, an output file named "Duke.txt" will be created in the same directory of the JAR.  Outputs will be saved into the file before the program exits, and the file will be reread the next time Duke runs.

# Commands
## Add
There are three types of tasks to be added. Namely, todo, deadline and event.  
### Todo
   
   To add a todo,  command should be typed in the format "*todo taskname*"
   Example of a valid input:
	   

     todo get groceries

### Event / Deadline
   
   To add an event or a deadline, command should be typed in the format "event taskname /dd-MM-yyyy HH:mm" or "event taskname /additional info"
   
   :white_check_mark: Example of a valid input:
   

    event Mark's birthday /14-02-2021 07:00 
    event Mark's birthday /three days from now
    deadline CS2113T submission /03-02-21 00:00
 :x: Example of a invalid input:
 
	deadline hello /
	event / 

 ## Delete 
 To delete a task, type in "delete" followed by task number.
 

    delete 2

 ## List
 To list tasks, simply type "list"
 

    list

 ## Mark/Unmark 
 To mark the task as completed, type in "mark" followed by task number.
 

    mark 3

 ## Find
 To find task(s) containing a specific keyword, type in "find" followed by the keyword.

    find birthday

 ## Bye
To exit the program, simply type "bye". The program terminates and the current state of the tasks is saved into "Duke.txt".

    bye
