# Duke User Guide
Duke is a desktop app for managing a user's tasks, optimized for use via a Command Line Interface (CLI). 

## Quick Start
1. Click on the latest release of this application and download the `ip.jar` file.
2. Locate the `ip.jar` file in your directory.
3. Right-click and open up your terminal.
4. Type in `java -jar ip.jar` and hit `Enter`.
5. Enjoy!! :D

## Features 
Duke application supports a variety of features which facilitate the management of user tasks. User tasks are categorized into todo, deadline and event.

### Create A New Task
1. Add a todo task    
Add a todo to the task list.      
Format: `todo description`     
2. Add a deadline task      
Add a deadline to the task list.       
Format: `deadline description /by by_description`    
3. Add an event task      
Add an event to the task list.      
Format: `event description /at at_description`      

Examples: 
>>> `todo read book `
>>> `event lecture /at today`      
>>> `deadline finish assignment /by next monday `     

### Display Your Tasks
Displaying all the tasks added to the list.      
Format: `list`      

### Mark Your Tasks as Completed
Set status of a task as done.     
Format: `done `    

Examples:
>>> `done 1 `   
>>> `done 3 `   

### Delete An Existing Task
Remove a task from the list.    
Format: `delete task_index`     

Examples:     
>>> `delete 1`     

### Search for a Task which contain a specified string
Displaying all the tasks in the list which contain a specified string.Note that multiple words are allowed and that the case does not matter. The program will search for tasks per word.     
Format: `find search_terms`    

Examples:      
>>> `find book `   
>>> `find school assignment`     

### Exiting the Program
Terminate the application and automatically update the text file.     
Format: `bye`     

### Automatic Saving of Task Data to Local Storage
Every time you create, update, or delete a task, Duke automatically saves these changes into a file that is stored locally on your system's storage. This way, your tasks will remain after you exit Duke for the day and will be available the next time you start him up.       
The data is stored in the `./data/tasks.txt` file relative to the location of the `ip.jar` file.     

## FAQ

Q: What if my input is in wrong format?      
A: When encountering unfamiliar format of input, Duke application provides a warm notice to the user and allows user to key in again.       
