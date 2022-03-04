# User Guide of Duke

## Outline
* [Outline](#outline)
* [Introduction](#introduction)
* [Setup Guide](#setup-guide)
* [Features](#features)
    * [Task Type](#task-type)
        * [Event](#event)
        * [Deadline](#deadline)
        * [Todo](#todo)
    * [Add](#features-add)
    * [Delete](#features-delete)
    * [Mark/Unmark](#features-markunmark)
    * [Search](#features-search)
    * [List](#features-list)
* [Tutorial](#tutorial)
    * [Exit](#tutorial-exit)
    * [Add Event](#tutorial-add-event)
    * [Add Deadline](#tutorial-add-deadline)
    * [Add Todo](#tutorial-add-todo)
    * [Delete Task](#tutorial-delete-task)
    * [Mark/Unmark Task](#tutorial-markunmark-task)
    * [Find Task](#tutorial-find-task)
    * [List Task](#tutorial-list-task)
* [Command Summary](#command-summary)
* [Trouble Shooting](#trouble-shooting)

---

##Introduction
Duke is a flexible command line based task manager that helps you to manage your todo task, event task and deadline task. You can follow this guide to explore more features about duke.

---

##Setup Guide
Prerequisite: JDK 11
1. Please ensure that JDK 11 is installed before running Duke. You can check it in terminal by typing
   ````shell
   java -v
   ````
   and you should see something similar as following:
   ````shell
   openjdk version "11.0.13" 2021-10-19 LTS
   OpenJDK Runtime Environment Corretto-11.0.13.8.1 (build 11.0.13+8-LTS)
   OpenJDK 64-Bit Server VM Corretto-11.0.13.8.1 (build 11.0.13+8-LTS, mixed mode)
   ````
2. Download the latest release. You can use command to download it or simply download it from the website:
   ````
   https://github.com/hlwang56/ip/releases
   ````
3. Open a terminal in the folder when the jar file is placed and run following command
   ````shell
   java -jar ip.jar
   ````
   
---

##Features

###Task Type
There are three types of task that are acceptable by duke:
* ####Event
  *Event* is a task type that contains task name and event time. It has marked with *E* when displaying on the interface.
   ````text
   [E][ ]<Event Name>(at: <Event Time>)
   ````
* ####Deadline
  *Deadline* is also a task type that contains task name and deadline time. It has marked with *D* when displaying on the interface
    ````text
    [D][ ]<Deadline Name>(by: <Deadline Time>)
    ````
* ####Todo
  *Todo* is another task type that only contains task name. It has marked with *T* when displaying on the interface.
    ````text
    [T][ ]<Todo Name>
    ````
  
---

###Features: Add
You can add any todo task, event task and deadline task without limit in numbers. All the tasks are marked as undone initially like following:
````text
[Task Type][Task Status]<Task Name><Task Time(if available)>
````
Check [Tutorial](#tutorial) or [Command Summary](#command-summary) for adding command usage.

---

###Features: Delete
You can delete any task from the list by giving the index of the task you want to delete.

Check [Tutorial](#tutorial-delete-task) or [Command Summary](#command-summary) for delete command usage.

---

###Features: Mark/Unmark
You can mark any task in the list as done or undone by giving the index of the task you want to mark/unmark. The marked task will display *X* on the second column when displaying on the interface, like following example:

````text
[Task Type][X]<Task Name><Task time> //this is a marked task
````
Check [Tutorial](#tutorial-markunmark-task) or [Command Summary](#command-summary) for marking/unmarking command usage.

---

###Features: Search
You can use keywords to find all the task that have relevant task name to the given keyword.

Check [Tutorial](#tutorial-find-task) or [Command Summary](#command-summary) for searching command usage.

---

###Features: List
You can list all the task that duke has stored. Check [Tutorial](#tutorial-list-task) or [Command Summary](#command-summary) for searching command usage.

---

###Features: Save
All the tasks that duke has will be saved to an external file. Don't worry if you exit the program without saving file! Duke will save it after every operation. Next time when you start the program again, you could see the tasks from the last use.

---
##Tutorial
####Tutorial: Exit
Use following command to exit the program:
````text
Bye
````

---

###Tutorial: Add Event
Type following command in the console by replacing <Task name> and <Event time> with your own task information:
````shell
Event <Task Name> /at <Task Time>
````
You will get following message if the action is success.
````shell
ψ(._. )> Got it. I've added this task:
[E][ ]<Task Name>(at: <Task Time>)
Now you have <numbers of task after adding> tasks in your list.
````

---

###Tutorial: Add Deadline
Type following command in the console by replacing <Task name> and <Event time> with your own task information:
````shell
Deadline <Task Name> /by <Task Time>
````
You will get following message if the action is success.
````shell
ψ(._. )> Got it. I've added this task:
[D][ ]<Task Name>(by: <Task Time>)
Now you have <numbers of task after adding> tasks in your list.
````

---

###Tutorial: Add Todo
Type following command in the console by replacing <Task name> and <Event time> with your own task information:
````shell
Todo <Task Name>
````
You will get following message if the action is success.
````shell
ψ(._. )> Got it. I've added this task:
[T][ ]<Task Name>
Now you have <numbers of task after adding> tasks in your list.
````
***IMPORTANT NOTE***

Please use acceptable time format when entering time. Acceptable time formats are following:
````text
y-M-d H:m
y/M/d H:m
d-M-y H:m
d/M/y H:m
(y -> year, M -> month, d -> day, H -> hour, m -> minute)
Year, Hour and Minute fields are optional
There's no requirement for the number of digit but please ensure that the date and the time is valid.
````

---

###Tutorial: List Task
Use following command to list all the task:
````shell
list
````
You should see following message if there's any task in the list:
````text
o(≧v≦)o Here are the tasks in your list:
1. [Task Type][Status]<Task Name>(<Task Time if any>)
2. [Task Type][Status]<Task Name>(<Task Time if any>)
3. ...
````
Otherwise
````text
The list is empty currently (￣3￣)a
````
***IMPORTANT NOTE***

Please use the index from listing task for deleting and marking/unmarking task!

---

###Tutorial: Delete Task
Type following command by replacing <Index of Task> to the index of the task that you would like to delete:
````text
delete <Index of Task>
````
You will get following message if the action is success.
````text
ψ(._. )> Okay! I've deleted this task:
[Type of Task][Status of Task]<Task Name>(<Task Time if any>)
Now you have <numbers of task after deleting> tasks in your list.
````
***IMPORTANT NOTE***

For the index of the task, please refer to the index when listing all the tasks. The index may change after adding task or deleting task. **Be aware that this action cannot be retrieved!**

---

###Tutorial: Mark/Unmark Task
To mark a task in the list, type following command by replacing <Index of Task> to the index of the task that you would like to delete:
````text
mark <Index of Task>
````
You will get following message if the action is success.
````shell
ψ(._. )> Okay! I've deleted this task:
[Type of Task][X]<Task Name>(<Task Time if any>)
````
Use following command to unmark a task in the list:
````shell
unmark <Index of Task>
````
You will get following message if the action is success.
````shell
ψ(._. )> Okay! I've deleted this task:
[Type of Task][ ]<Task Name>(<Task Time if any>)
````
***IMPORTANT NOTE***

For the index of the task, please refer to the index when listing all the tasks. The index may change after adding task or deleting task.

---

###Tutorial: Find Task
Use following command to find a task that match the keyword from the list:
````shell
find <keyword>
````
Duke will list all the tasks that match the keyword (if there's any!)
````TEXT
o(≧v≦)o Here are the matching tasks in your list:
1. [Task Type][Status]<Task Name>(<Task Time if any>)
2. [Task Type][Status]<Task Name>(<Task Time if any>)
3. ...
...
````
If there's no task which matches to the keyword, duke will print following message on the interface:
````TEXT
(￣ε￣；) Sorry, I can't find any result from the list.
````
***IMPORTANT NOTE***

**Please do not refer to this index when deleting or marking/unmarking task.** This index is only for your reference about how many matching task in the list.

Duke only supports to find matching task according to the keyword and task name. **Searching by time is not supported currently.**

---

##Command Summary
| **Command Type** | Command Format                         | 
|:-----------------|:---------------------------------------|
| **add todo**     | `todo <Task Name>`                     |    
| **add event**    | `event <Task Name> /at <Task Time>`    |     
| **add deadline** | `deadline <Task Name> /by <Task Time>` |     
| **mark**         | `mark <Task Index>`                    |      
| **unmark**       | `unmark <Task Index>`                  |      
| **find**         | `find <Keywords>`                      |
| **delete**       | `delete <Task Index>`                  |
| **list**         | `list`                                 | 
| **exit**         | `bye`                                  |

---

##Trouble Shooting
**Q**: What should I do if the program reports loading error?

**A**: The external file for saving and loading is broken. In this case you need to reinstall the programme.

**Q**: What is acceptable time format?

**A**: The acceptable time formats are following:
````text
y-M-d H:m
y/M/d H:m
d-M-y H:m
d/M/y H:m
(y -> year, M -> month, d -> day, H -> hour, m -> minute)
Year, Hour and Minute fields are optional
````
There's no requirement for the number of digit but please ensure that the date and the time is valid.

**Q**: What if I accidentally delete a task that I want to keep?

**A**: Delete action cannot be retrieved. You can add it again, but the tasks will have different index.

**TIPS**: Always list all the task before deleting and marking/unmarking.
