
# Duke User Guide

This is Duke, an application for busy people to keep track of what they need to do. 

# Starting the program 
## Using Intellij
1. Find the project in the `Project Explorer` (usually located at the left side). 
2. Go to the src folder and locate the `Duke.java` file (`src --> main --> java --> Duke.java`). 
3. Right click on the file and select `Run Duke.main()`.
4. The program now should run on the Console (usually located on the bottom. You can now interact with the program through the Console. 

## Using JAR file

# Adding a Task 
There are three kinds of Tasks: Todos, Deadlines, and Events. 
## Adding a Todo
Adds a Todo to the list of tasks. <br />
Format: `todo DESCRIPTION` <br />
Words in `UPPER_CASE` are the parameters. <br />
Examples: 
* todo buy sunscreen 
* todo buy bubble tea 

## Adding a Deadline
Adds a Deadline to the list of tasks. <br />
Format: `deadline DESCRIPTION /by DEADLINE` <br />
Words in `UPPER_CASE` are the parameters. <br />
Examples: <br />
* deadline finish econometrics problem set /by Saturday 
* deadine return book /by Sunday 12:30PM

## Adding an Event
Adds an Event to the list of tasks. <br />
Format: `event DESCRIPTION /at TIME` <br />
Words in `UPPER_CASE` are the parameters. <br />
Examples: <br />
* event project meeting /at Saturday 8:00PM
* event attend CS2113 lecture /at Friday 4:00PM 

# Listing all Tasks
Displays all tasks that the user has added to their list. <br />
Format: `list` <br />
Output could look like the following: <br />
```
 1: [T][ ] buy sunscreen 
 2: [T][ ] buy bubble tea 
 3: [D][ ] finish econometrics problem set (by: Saturday)
 4: [D][ ] return book (by: Sunday 12:30PM)
 5: [E][ ] project meeting (at: Saturday 8:00PM)
 6: [E][ ] attend CS2113 lecture (at: Friday 4:00PM)
 ```
 `T` denotes a task of type Todo, `D` denotes a task of type Deadline, and `E` denotes a task of type Event. 
 
# Marking a Task as complete 
You have the option of marking (i.e. checking off) a task that has been completed. <br /> 
Format: `mark TASK_NUMBER` <br />
`TASK_NUMBER` is the numerical label assigned to the task (that you can find from the output of the `list` command). <br />
Example: <br />
Assuming the following task list: <br />
```
 1: [T][ ] buy sunscreen
 2: [T][ ] buy bubble tea 
 3: [D][ ] finish econometrics problem set (by: Saturday)
```
`mark 2` would result in the following list: <br />
```
 1: [T][ ] buy sunscreen
 2: [T][X] buy bubble tea 
 3: [D][ ] finish econometrics problem set (by: Saturday)
```

# Unmarking a Task 
Alternatively, you may also unmark a task that has previously been marked. <br />
Format: `unmark TASK_NUMBER` <br />
Example: <br />
Assuming the following task list: <br />
```
 1: [T][X] buy sunscreen
 2: [T][ ] buy bubble tea 
 3: [D][ ] finish econometrics problem set (by: Saturday)
```
`unmark 1` would result in the following list: <br />
```
 1: [T][ ] buy sunscreen
 2: [T][ ] buy bubble tea 
 3: [D][ ] finish econometrics problem set (by: Saturday)
```
 
 # Deleting a Task
 Users may also delete a task from the list of tasks. <br />
 Format `delete TASK_NUMBER` <br />
 Example: <br />
 Assuming the following task list: <br />
```
 1: [T][ ] buy sunscreen
 2: [T][X] buy bubble tea 
 3: [D][ ] finish econometrics problem set (by: Saturday)
```
 `delete 2` would result in the following list: <br />
```
 1: [T][ ] buy sunscreen
 2: [D][ ] finish econometrics problem set (by: Saturday)
```

 
 # Finding a Task
 Users can search for Tasks in their task list with a specific keywords. <br />
 Format `find KEYWORD` <br />
 Example: <br />
 Assuming the following task list: <br />
```
 1: [T][ ] buy sunscreen
 2: [T][X] buy bubble tea 
 3: [D][ ] finish econometrics problem set (by: Saturday)
```
 `find buy` would result in the following list:  <br />
```
  1: [T][ ] buy sunscreen
  2: [T][X] buy bubble tea 
 ```
  
 # Accessing saved list of Tasks
 Duke automatically saves the list of tasks every time the user makes a modification to it, and loads this list of tasks when the application starts up. The user may access and change this list if they are running in the application in Intellij. 
1. In the `Project Explorer` (usually located at the left side), go to `data --> duke.txt`. Open the `duke.txt` file. 
2. You may now edit the `duke.txt` file directly. Make sure to follow the given formatting! 
 
 
 
























