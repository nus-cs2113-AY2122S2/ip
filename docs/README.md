# User Guide
Jarvis is a Command-Line application for managing tasks, built for CS2113T Individual Project


## Quick Start
1. Ensure you have `Java11` or above installed in your computer.
2. Download the latest `Jarvis.jar` from [here](https://github.com/GlendonNotGlen/ip/releases/tag/A-UserGuide)
3. Move the downloaded file to a folder of your choice
4. Navigate a command-line application to the folder you have chosen
5. Type `java -jar Jarvis.jar` to run the program. If successful, you should see the following message:
![Welcome Message](./images/welcome.PNG)
6. Enter commands on the command-line for the program to start managing your tasks!
7. Refer to `Features` below for more information on each command

## Commands Summary <br>
| Command  | Format, examples                                                                            |<br>
|----------|---------------------------------------------------------------------------------------------|
| todo     | `todo <description`<br/> e.g todo submit assignment                                         |
| event    | `event <description> /at DD/MM/YYYY HHMM`<br/>e.g event attend lecture /at 04/03/2022 1600  |
| deadline | `deadline <description> /by DD/MM/YYYY HHMM`<br/>e.g deadline push code /by 03/03/2022 0000 |
| list     | `list`                                                                                      | 
| delete   | `delete <task index>`<br/>e.g delete 2<br/>to remove second task on your list               |
| mark     | `mark <task index>`<br/>e.g mark 1<br/>to mark the first task on your list as done          |
| unmark   | `unmark <task index>`<br/>e.g unmark 4<br/>to unmark the fourth task on your list           |
| find     | `find <keyword(s)>`<br/>e.g find lecture                                                    |
| bye      | `bye`                                                                                       |
 

## Features <br>
The following are features of the Jarvis bot. Please ensure that the number and format of arguments given is accurate<br>

### Add a Task to your list:`todo`<br>
Adds a task to your personal list. **One** task can only be added per command.<br>
Format:`todo <task>` <br>
Example:`todo submit assignment`<br>
![todo screenshot](./images/todo.PNG)

### Add an Event to your list:`event` <br>
Adds an Event to your personal list. **One** event can only be added per command<br>
Note that the format of date and time has to follow as `DD/MM/YYYY` and `HHMM` exactly.<br>
The use of `/at` has to be included as well to separate your event's description from the date/time <br>
Format:`event <event description> /at DD/MM/YYYY HHMM`<br>
For example: `event attend lecture /at 04/03/2022 1600`<br>
![event screenshot](./images/event.PNG)<br>

### Add a Deadline to your list:`deadline` <br>
Adds a Deadline to your personal list. **One** deadline can only be added per command<br>
Note that the format of date and time has to follow as `DD/MM/YYYY` and `HHMM` exactly.<br>
The use of `/by` has to be included as well to separate your deadline's description from the date/time<br>
Format:`deadline <deadline description> /by DD/MM/YYYY HHMM`<br>
For example: `deadline push code /by 03/03/2022 0000`<br>
![deadline screenshot](./images/deadline.PNG)<br>

### Printing all current tasks/events/deadlines:`list`<br>
Shows a list of all current tasks, events and deadlines in your list.<br>
Format:`list`<br>
![list screenshot](./images/list.PNG)<br>

### Delete a task/event/deadline from your list:`delete`<br>
Delete a specified task/event/deadline from your list using its index.<br>
Use the `list` command to display the indices of your list.<br>
Format:`delete <index>`<br>
![delete screenshot](./images/delete.PNG)<br>

### Mark a task/event/deadline:`mark`<br>
Mark a task/event/deadline in your list as completed. You can only mark unmarked tasks, or a warning message will appear.<br>
Use the `list` command to display the indices of your list.<br>
Format:`mark <index>`<br>
![mark screenshot](./images/mark.PNG)<br>

### Unmark a task/event/deadline:`unmark`<br>
Unmark a task/event/deadline in your list. You can only unmark marked tasks, or a warning message will appear.<br>
Use the `list` command to display the indices of your list.<br>
Format:`unmark <index>`<br>
![unmark screenshot](./images/unmark.PNG)<br>

### Finding a task:`find`<br>
Finds a task/event/deadline description using the keyword(s) provided.<br>
Format:`find <keyword>`<br>
Example:`find code`<br>
![find screenshot](./images/find.PNG)<br>

### Saving your data<br>
Saving your current list is automated only at the end when you use the `bye` command.<br>

### Exiting the program:`bye`<br>
Saves your current list data inside a data folder and terminates the program.<br>
Format:`bye`<br>
![bye screenshot](./images/bye.PNG)<br>
