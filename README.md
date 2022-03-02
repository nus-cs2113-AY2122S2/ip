# Maahes Duke 

* This is a Command Line Interface (CLI) Task Management application. 
* It's named _Maahes Duke_. Given below are instructions on how to use it.

## Starting the program

**Using IntelliJ**
Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
2. Open the project into Intellij as follows:
   1. Click `Open`.
   2. Select the project directory, and click `OK`.
   3. If there are any further prompts, accept the defaults.
3. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
4. Locate the `src/main/java/Duke.java` file. Right-click it, and choose `Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). 
5. The application now should run in the console.
6. Now you can interact with the program in the console.

**Using Command Line**

1. 'Build' the project using IntelliJ.
2. Open the `Terminal`/`Command Prompt`.
3. `cd` into the project's `out\production\IP` directory
4. Type `java Duke`, then press enter to execute.
5. Now you can interact with the program through the CLI.

## Supported commands
1. **Viewing help: `help`**
   * Format: `help`

2. **Adding a todo task: `todo`**
   * Add a todo task to the list of tasks.
   * Format: `todo <description>`
   * Words inside `< >` are parameters. There is no need to type the `<` and `>`.
   * Example:
     * `todo Read textbook`

3. **Adding a deadline task: `deadline`**
   * Add a deadline task to the list of tasks.
   * Format: `deadline <task> /by <yyyy-MM-dd HH:mm>`
   * Example:
     * `deadline Submit lab report /by 2022-03-05 23:59`
   
4. **Adding a event task: `event`**
   * Add an event task to the list of tasks.
   * Format: `event <task> /at <yyyy-MM-dd HH:mm>`
   * Example:
     * `event Attend meeting /at 2022-03-29 14:00`

5. **Listing all tasks: `list`**
   * Display the entire list of tasks.
   * Format: `list`

6. **Finding all tasks containing a keyword in their description: `find`**
   * Find all the tasks whose description contains the specified keyword.
   * Format: `find <keyword>`
   * Example:
     * `find meeting` + 
      Returns all tasks containing `meeting` in description.

7. **Deleting a task: `delete`**
   * Delete a task by specifying the index of it.
   * Format: `delete <index>`
   * Example:
     * `delete 2` +
      Deletes the second task in the task list.

8. **Marking a task: `mark`**
   * Mark a task as done by specifying the index of it.
   * Format: `mark <index>`
   * Example:
      * `mark 3` +
        Marks the third task in the task list as done.

9. **Marking a task: `unmark`**
   * Unmark a task as not done yet by specifying the index of it.
   * Format: `unmark <index>`
   * Example:
      * `mark 1` +
        Unmark the first task in the task list.
      
10. **Exiting the program: `bye`**
    * Exit the program.
      * Format: `bye`