# Duke project: the task management chatbot
## project user guide

* This is a CLI(command line interface) task management application named Baymax.
*

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
2. Open the project into Intellij as follows:
    1. Click `Open`.
    2. Select the project directory, and click `OK`.
    3. If there are any further prompts, accept the defaults.
3. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
4. After that, locate the `src/main/java/Baymax.java` file, right-click it, and choose `Run Baymax.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:

```
Hello, I'm Baymax.
Your personal task managing companion. 
What can I do for you? 
```

5. The application now should run in the console.
6. Now you can interact with the program in the console.


*Using Command Line*

1. 'Build' the project using IntelliJ.
2. Open the `Terminal`/`Command Prompt`.
3. `cd` into the project's `out\production\ip` directory
4. Type `java Baymax`, then press enter to execute.
5. Now you can interact with the program through the CLI..


## Features
### three types of tasks
### 1. Feature-event:
- event is a subclass of task, with description and date.
### 2. Feature-deadline:
- deadline is a subclass of task, with description and due date.
### 3. Feature-todo:
- todo is a subclass of task, with description of the task.

## Usage
1. Adding a todo task
    * Add a todo task to the list of tasks.
    * Format: `todo <description>`
    * Words inside `< >` are parameters. There is no need to type the `<` and `>`.
    * Example of usage:`todo Read textbook`
    * Expected oucome:
```
Got it. I've added this task:
[T][ ] Read textbook
Now you have 1 tasks tasks in the list.
```

2. Adding a deadline task
    * Add a deadline task to the list of tasks.
    * Format: `deadline <task> /by <MM dd yyyy>`
    * Example:
        * `deadline Submit lab report /by 03 04 2022`
    * Expected oucome:
```
Got it. I've added this task:
[D][ ] Submit lab report /by 03 04 2022`
Now you have 2 tasks tasks in the list.
```
3. Adding a event task
    * Add an event task to the list of tasks.
    * Format: `event <task> /at <MM dd yyyy>`
    * Example:
        * `event Attend meeting /at 03 04 2022`
    * Expected oucome:
```
Got it. I've added this task:
[E][ ] Submit lab report /by 03 04 2022`
Now you have 3 tasks tasks in the list.
```

4. Listing all tasks: `list`
    * Display the entire list of tasks.
    * Format: `list`
    * Expected oucome:

```
Here are the tasks in your list:
1.[T][ ] Read textbook
2.[D][ ] Submit lab report /by 03 04 2022
3.[E][ ] Submit lab report /by 03 04 2022`
```
5. Finding all tasks containing a keyword in their description: `find`
    * Find all the tasks whose description contains the specified keyword.
    * Format: `find <keyword>`
    * Example:
        * `find meeting` +
          Returns all tasks containing `meeting` in description.

6. Deleting a task: `delete`
    * Delete a task by specifying the index of it.
    * Format: `delete <index>`
    * Example:
        * `delete 2` +
          Deletes the second task in the task list.

7. Marking a task: `mark`
    * Mark a task as done by specifying the index of it.
    * Format: `mark <index>`
    * Example:
        * `mark 3` +
          Marks the third task in the task list as done.

8. Marking a task: `unmark`
    * Unmark a task as not done yet by specifying the index of it.
    * Format: `unmark <index>`
    * Example:
        * `mark 1` +
          Unmark the first task in the task list.

9. Exiting the program: `bye`
    * Exit the program.
    * Format: `bye`
    * Expected output:`Bye, Hope to see you again soon!`
