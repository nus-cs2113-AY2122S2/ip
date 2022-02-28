<h1 id="top">Alexis User Guide</h1> 

Alexis is a **desktop app for managing a user's tasks, optimized for use via a Command Line Interface** (CLI). 
The clean interface helps the user to be focused and productive.

<ul>
<li><a href="#quickstart">Quick start</a></li>
<li><a href="#features">Features</a></li>
<ul>
    <li>Create a New Task:</li>
    <ul>
        <li><a href="#todo"><code>todo</code></a></li>
        <li><a href="#deadline"><code>deadline</code></a></li>
        <li><a href="#event"><code>event</code></a></li>
    </ul>
    <li><a href="#list">Display Your Tasks <code>list</code></a></li>
    <li>Mark Your Tasks as Completed:</li>
    <ul>
        <li><a href="#mark"><code>mark</code></a></li>
        <li><a href="#unmark"><code>unmark</code></a></li>
    </ul>
    <li><a href="#delete">Delete an Existing Task <code>delete</code></a></li>
    <li><a href="#show">Search for all Tasks Occurring on a Date <code>show</code></a></li>    
    <li><a href="#find">Search for a Task easily <code>find</code></a></li>
    <li><a href="#save">Automatic Saving of Task Data to Local Storage</a></li>
</ul>
<li><a href="#faq">FAQ</a></li>
<li><a href="#summary">Command Summary</a></li>
</ul>

<hr>

<h2 id="quickstart">Quick start</h2> 
<ol>
<li>Click on the <a href="https://github.com/BradenTeo/ip/releases">latest release</a> of this application  and download the `ip.jar` file.</li><br>
<li>Locate the `ip.jar` file in your directory.<br>
   <img src="https://i.imgur.com/VeWCrGc.png" /></li><br>
<li>Right-click and open up your terminal.<br>
   <img src="https://i.imgur.com/i84PHUP.png" /><br>
   The terminal will pop-up. Do double-check that you are in the same folder as the downloaded <code>ip.jar</code> file.<br><br>

Alternatively, open your command prompt and navigate to the folder containing the <code>ip.jar</code> file.</li><br>
<img src="https://i.imgur.com/3pzfe7J.png" /><br>

<li>Type in <code>java -jar ip.jar</code> and hit <code>Enter</code>.<br>
You should be able to see this if you have followed the steps correctly:<br>

   <pre><code>
   Hello! I'm Alexis, your trusty helper!<font color="purple">
         __      ___       _______  ___  ___   __      ________
        /""\    |"  |     /"     "||"  \/"  | |" \    /"       ) 
       /    \   ||  |    (: ______) \   \  /  ||  |  (:   \___/ 
      /' /\  \  |:  |     \/    |    \\  \/   |:  |   \___  \
     //  __'  \ \\  |___  // ___)_   /\.  \   |.  |    __/  \\ 
    /   /  \\  \( \_|:  \(:      "| /  \   \  /\  |\  /" \   :) 
   (___/    \___)\_______)\_______)|___/\\__|(__\_|_)(_______/
   </font></code></pre>
</li>
<li>Enjoy!! :D</li>
</ol>
<hr>

<h2 id="features">Features</h2> 

<div class="feature-note" style="background-color:#e0f2cb">
    <img class="emoji" title=":information_source:" alt=":information_source:" src="https://github.githubassets.com/images/icons/emoji/unicode/2139.png" height="20" width="20">
    <b>Notes about the command format:</b>
    <ul>
        <li>Words in <code>UPPER_CASE</code> are the parameters to be supplied by the user.
    e.g. in <code>todo TASK</code>, <code>TASK</code> is a parameter which can be used as <code>todo return book</code>. </li>
        <li>Expected format of inputted date is <code>dd/MM/yyyy</code>. e.g. <code>28/02/2022</code> </li>
    <br></ul>
</div><hr>

<h3>1. Create a New Task </h3>
Create a new task that is a deadline, an event, or a to-do, and add it to Alexis's list for him to keep track of your tasks.
<ul>
    <li><h4 id="todo">Adding a Todo Task: <code>todo</code></h4></li>
        Format: <code>todo TASK</code><br><br>
        Example of usage: <code>todo return book</code><br>
        Expected outcome: New todo task is created<br>
        <pre><code>---------------------------------------------------------------
Got it! I've added this task:
  [T][ ] return book
---------------------------------------------------------------</code></pre><br>
    <li><h4 id="deadline">Adding a Deadline Task: <code>deadline</code></h4></li>
        Format: <code>deadline TASK /by DATE</code><br>
        <i>Note: expected format of <code>DATE</code> is <code>dd/MM/yyyy</code></i><br><br>
        Example of usage: <code>deadline finish assignment /by 28/02/2022</code><br>
        Expected outcome: New deadline task is created<br>
        <pre><code>---------------------------------------------------------------
Got it! I've added this task:
  [D][ ] finish assignment (by: Feb 28 2022)
---------------------------------------------------------------</code></pre><br>
   <li><h4 id="event">Adding an Event Task: <code>event</code></h4></li>
        Format: <code>event TASK /at DATE</code><br>
        <i>Note: expected format of <code>DATE</code> is <code>dd/MM/yyyy</code></i><br><br>
        Example of usage: <code>event lecture /at 01/03/2022</code><br>
        Expected outcome: New event task is created<br>
        <pre><code>---------------------------------------------------------------
Got it! I've added this task:
  [E][ ] lecture (at: Mar 01 2022)
---------------------------------------------------------------</code></pre>
</ul><br>


<h3 id="list">2. Display Your Tasks: <code>list</code></h3>
- Get Alexis to display all the tasks that you have created and given to him to keep track on.
<ul>
     Format: <code>list</code><br><br>
     Example of usage: <code>list</code><br>
     Expected outcome: Task list is displayed<br>
     <pre><code>---------------------------------------------------------------
Here are the tasks in your list:
1.[T][ ] return book
2.[D][ ] finish assignment (by: Feb 28 2022)
3.[E][ ] lecture (at: Mar 1 2022)
---------------------------------------------------------------</code></pre>
</ul><br>

<h3>3. Mark Your Tasks as Completed</h3>
Tell Alexis that you have completed a task, and he will update it accordingly in his list of tasks.
<ul>
    <li><h4 id="mark">Mark a task: <code>mark</code></h4></li>
     Format: <code>mark TASK_NUMBER</code><br><br>
     Example of usage: <code>mark 1</code><br>
     Expected outcome: Task number 1 is marked<br>
     <pre><code>---------------------------------------------------------------
Great! I've marked this task as done:
  [T][X] return book
---------------------------------------------------------------</code></pre><br>
    <li><h4 id="unmark">Unmark a task: <code>unmark</code></h4></li>
     Format: <code>unmark TASK_NUMBER</code><br><br>
     Example of usage: <code>unmark 1</code><br>
     Expected outcome: Task number 1 is unmarked<br>
     <pre><code>---------------------------------------------------------------
Ok. I've marked this task as not done yet:
  [T][ ] return book
---------------------------------------------------------------</code></pre>
</ul><br>

<h3 id="delete">4. Delete an Existing Task: <code>delete</code></h3>
- Removes a task.
<ul>
     Format: <code>delete TASK_NUMBER</code><br><br>
     Example of usage: <code>delete 1</code><br>
     Expected outcome: Task number 1 is deleted<br>
     <pre><code>---------------------------------------------------------------
Noted. I've removed this task:
  [T][ ] return book
Now, you have 2 tasks in the list.
---------------------------------------------------------------</code></pre>
</ul><br>


<h3 id="show">5. Search for all Tasks Occurring on a Date: <code>show</code></h3>
- Enter a valid date and Alexis will show you the deadlines/events that lie on that date.
<ul>
     Format: <code>show DATE</code><br>
     <i>Note: expected format of <code>DATE</code> is <code>dd/MM/yyyy</code></i><br><br>
     Example of usage: <code>show 01/03/2022</code><br>
     Expected outcome: Deadlines/events on 01/03/2022 are displayed<br>
     <pre><code>---------------------------------------------------------------
Here are the events/deadlines on Mar 1 2022:
1.[E][ ] lecture (at: Mar 1 2022)
---------------------------------------------------------------</code></pre>
</ul>
<ul>
     Example of usage: <code>show 02/03/2022</code><br>
     Expected outcome: No deadlines/events fall on the specified date<br>
     <pre><code>---------------------------------------------------------------
There are no events/deadlines on Mar 2 2022
---------------------------------------------------------------</code></pre>
</ul><br>


<h3 id="find">6. Search for a Task easily: <code>find</code></h3>
- Enter a search query and Alexis will show you the tasks that matches your search keywords.
<ul>
     Format: <code>find KEYWORD</code><br><br>
     Example of usage: <code>find assignment</code><br>
     Expected outcome: Displays tasks which contain "assignment" in their description<br>
     <pre><code>---------------------------------------------------------------
Here are the matching tasks in your list:
1.[D][ ] finish assignment (by: Feb 28 2022)
---------------------------------------------------------------</code></pre>
</ul>
<ul>
     Example of usage: <code>find exam</code><br>
     Expected outcome: There are no tasks that contain "exam" in their description<br>
     <pre><code>---------------------------------------------------------------
There are no matching tasks in your list
---------------------------------------------------------------</code></pre>
</ul><br>

<h3 id="bye">7. Exiting the Program: <code>bye</code></h3>
<ul>
   Format: <code>bye</code><br><br>
   Example of usage: <code>bye</code><br>
   Expected outcome: Exits the program<br>
   <pre><code>---------------------------------------------------------------
Bye. Hope to see you again soon!
---------------------------------------------------------------</code></pre>
</ul><br>

<h3 id="save">8. Automatic Saving of Task Data to Local Storage</h3>
- Every time you create, update, or delete a task, Alexis automatically saves these changes into a file that is stored locally on your system's storage. This way, your tasks will remain after you exit Alexis for the day and will be available the next time you start him up.
- The data is stored in the <code>./data/tasks.txt</code> file relative to the location of the <code>ip.jar</code> file.
<br><br>
<hr>

<h2 id="faq">FAQ</h2>
<p>
    <b>Q:</b> How do I transfer my data to another computer?<br>
    <b>A:</b> Install the app in the other computer and overwrite the empty data file 
              it creates with the data in the previous <code>./data/task.txt</code> file
</p>
<br>
<p>
    <i>More questions will be added when I gather more feedback upon releasing v1.0 of Alexis.</i>
</p>

<hr>

<h2 id="summary">Command Summary</h2>
<table>
    <thread>
        <th>Action</th>
        <th>Format, Examples</th>
    </thread>
    <tbody>
        <tr>
            <td>todo</td>
            <td>
               <code>todo TASK</code><br>
               e.g., <code>todo read</code>
            </td>
        </tr>
        <tr>
            <td>deadline</td>
            <td>
               <code>deadline TASK /by DATE</code><br>
               e.g., <code>deadline return book /by 28/02/2022</code>
            </td>
        </tr>
        <tr>
            <td>event</td>
            <td>
               <code>event TASK /at DATE</code><br>
               e.g., <code>event career fair /at 01/03/2022</code>
            </td>
        </tr>
        <tr>
            <td>mark</td>
            <td>
               <code>mark TASK_NUMBER</code><br>
               e.g., <code>mark 2</code>
            </td>
        </tr>
        <tr>
            <td>unmark</td>
            <td>
               <code>unmark TASK_NUMBER</code><br>
               e.g., <code>unmark 5</code>
            </td>
        </tr>
        <tr>
            <td>list</td>
            <td>
               <code>list</code><br>
            </td>
        </tr>
        <tr>
            <td>delete</td>
            <td>
               <code>delate TASK_NUMBER</code><br>
               e.g., <code>delete 1</code>
            </td>
        </tr>
        <tr>
            <td>show</td>
            <td>
               <code>show DATE</code><br>
               e.g., <code>show 08/05/2022</code>
            </td>
        </tr>
        <tr>
            <td>find</td>
            <td>
               <code>find KEYWORD</code><br>
               e.g., <code>find book</code>
            </td>
        </tr>
        <tr>
            <td>bye</td>
            <td>
               <code>bye</code><br>
            </td>
        </tr>
    </tbody>
</table>

<a href="#top">Go back to top</a>