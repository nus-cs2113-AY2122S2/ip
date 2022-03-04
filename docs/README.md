# Duke User Guide

Duke is a command line application that helps users manage their tasks.

## How to Run Duke
1. Download the latest release (`ip.jar`) of Duke <a href="https://github.com/chihyingho/ip/releases">here</a>.
2. In your Command Prompt, navigate to the folder where `ip.jar` is located and key in this command and press enter:<br>
`java -jar ip.jar`
3. If Duke is running correctly, you should see this greeting message:
<pre><code>---------------------------------------------------------------
 ____        _        
|  _ \ _   _| | _____ 
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|
Hello, I'm Duke!
What can I do for you?
---------------------------------------------------------------</code></pre>
4. Enjoy using Duke to manage your tasks! 

## Features 

<ul>
    <li><a href="#load">1. Load Your Own Task List File </a></li>
    <li><a href="#add">2. Add New Tasks </a></li>
    <li><a href="#list">3. Display Existing Tasks <code>list</code></a></li>
    <li><a href="#mark">4. Mark Tasks as Done <code>mark</code></a></li>
    <li><a href="#unmark">5. Mark Tasks as Not Done <code>unmark</code></a></li>
    <li><a href="#delete">6. Delete Tasks <code>delete</code></a></li>
    <li><a href="#find">7. Find Tasks with Keyword <code>find</code></a></li>
    <li><a href="#exit">8. Exit Duke <code>bye</code></a></li>
</ul>
<br>

<h3 id="load">1. Load Your Own Task List File </h3>
If you would like to load your own task list file, store it in this file path: ./data/task.txt<br>
Tasks in your task list file must be in this format: 
<pre><code>T|1|Eat breakfast
E|0|Eat lunch|04/03/2022 1200
D|1|Eat dinner|midnight
</code></pre>

<h3 id="add">2. Add New Tasks </h3>
- Add Todo<br>

This adds a new task of type "todo", which does not have a deadline or duration.<br>

Command Format: <code>todo</code><br>
Example Command: <code>todo Eat breakfast</code><br>
Example Outcome:
<pre><code>---------------------------------------------------------------
Got it. I've added this task: 
[T][ ] Eat breakfast 
Now you have 1 task(s) in the list.
---------------------------------------------------------------</code></pre>
- Add Event<br>

This adds a new task of type "event", which has a duration.<br>

Command Format: <code>event</code><br>
Example Command: <code>event Eat breakfast /at noon</code><br>
Example Outcome:
<pre><code>---------------------------------------------------------------
Got it. I've added this task: 
[E][ ] Eat breakfast (at: noon)
Now you have 1 task(s) in the list.
---------------------------------------------------------------</code></pre>
- Add Deadline<br>

This adds a new task of type "deadline", which has a deadline.<br>

Command Format: <code>deadline</code><br>
Example Command: <code>deadline Eat breakfast /by 04/03/2022 1200</code><br>
Note that the deadline must be entered in the format: dd/MM/yyyy HHmm (24-hour clock).<br>
Example Outcome:
<pre><code>---------------------------------------------------------------
Got it. I've added this task: 
[D][ ] Eat breakfast (by: 04 March 2022 12:00)
Now you have 1 task(s) in the list.
---------------------------------------------------------------</code></pre>

<h3 id="list">3. Display Existing Tasks <code>list</code></h3>
Displays all existing tasks in your task list.<br> 
Command Format: <code>list</code><br>
Example Command: <code>list</code><br>
Example Outcome:
<pre><code>---------------------------------------------------------------
Here are the task(s) in your list:
1.[T][ ] Eat breakfast 
Now you have 1 task(s) in the list.
---------------------------------------------------------------</code></pre>

<h3 id="mark">4. Mark Tasks as Done <code>mark</code></h3>
Marks an existing task as done.<br>
Command: <code>mark</code><br>
Example Command: <code>mark 1</code><br>
Example Outcome:
<pre><code>---------------------------------------------------------------
Nice! I've marked this task as done:
[D][X] Eat lunch (by: 04 Mar 2022 12:00)
---------------------------------------------------------------</code></pre>

<h3 id="unmark">5. Mark Tasks as Not Done <code>unmark</code></h3>
Marks an existing task as not done yet.<br>
Command: <code>unmark</code><br>
Example Command: <code>unmark 1</code><br>
Example Outcome:
<pre><code>---------------------------------------------------------------
I've marked this task as not done yet:
[D][ ] Eat lunch (by: 04 Mar 2022 12:00)
---------------------------------------------------------------</code></pre>

<h3 id="delete">6. Delete Tasks <code>delete</code></h3>
Deletes an existing task.<br>
Command: <code>delete</code><br>
Example Command: <code>delete 1</code><br>
Example Outcome:
<pre><code>---------------------------------------------------------------
Noted. I've removed this task:
[D][X] Eat lunch (by: 04 Mar 2022 12:00)
---------------------------------------------------------------</code></pre>

<h3 id="find">7. Find Tasks with Keyword <code>find</code></h3>
Searches for tasks with the keyword specified by the user.<br>
Returns the list of tasks and the number of tasks that contain the search keyword.<br>
Command: <code>find</code><br>
Example Command: <code>find eat</code><br>
Example Outcome:
<pre><code>---------------------------------------------------------------
Here are your search results:
[D][X] Eat lunch (by: 04 Mar 2022 12:00)
[T][ ] Eat dinner
There were 2 tasks that matched your search.
---------------------------------------------------------------</code></pre>

<h3 id="exit">8. Exit Duke <code>bye</code></h3>
Exits Duke application. <br>
Command: <code>bye</code><br>
Example Command: <code>bye</code><br>
Example Outcome:
<pre><code>---------------------------------------------------------------
Bye. Hope to see you again soon!
---------------------------------------------------------------</code></pre>

