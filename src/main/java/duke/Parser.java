package duke;

import java.time.LocalDate;

public class Parser {

    /**
     * Parses a string to a Task object by figuring out the kind of Task,
     * and all other fields required for the relevant class.
     *
     * @param line the string that needs to be parsed as a Task
     * @return Task the task object which can be added to the TaskList
     */
    static Task lineToTask(String line){
        Task task = null;
        int isDoneInt;
        char indicator = line.charAt(0);
        switch (indicator) {
        case 'D':
            int byIndex = line.indexOf("|", 8);
            String deadlineDescription = line.substring(8, byIndex - 1);
            String by = line.substring(byIndex + 2);
            task = new Deadline(deadlineDescription, LocalDate.parse(by));
            isDoneInt = Integer.parseInt(line.substring(4, 5));
            if (isDoneInt != 0) task.setDone(true);
            break;
        case 'E':
            int atIndex = line.indexOf("|", 8);
            String eventDescription = line.substring(8, atIndex - 1);
            String at = line.substring(atIndex + 2);
            task = new Event(eventDescription, at);
            isDoneInt = Integer.parseInt(line.substring(4, 5));
            if (isDoneInt != 0) task.setDone(true);
            break;
        case 'T':
            String todoDescription = line.substring(8);
            task = new Todo(todoDescription);
            isDoneInt = Integer.parseInt(line.substring(4, 5));
            if (isDoneInt != 0) task.setDone(true);
            break;
        }
        return task;
    }

    /**
     * Appropriately converts a Task to a string object in a way that all
     * information about the Task object is preserved. Every field of the
     * object is converted to a string and the final line is constructed.
     *
     * @param task the Task object which needs to be converted to string
     * @return the string that can be written to a text file as a single line
     */
    static String taskToLine(Task task) {
        String line = "";
        // code block
        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            line = "D" + " | " +
                    boolToInt(deadline.isDone()) +
                    " | " + deadline.description +
                    " | " + deadline.by.toString();
        } else if (task instanceof Event) {
            Event event = (Event) task;
            line = "E" + " | " +
                    boolToInt(event.isDone()) +
                    " | " + event.description +
                    " | " + event.getAt();
        } else if (task instanceof Todo) {
            Todo todo = (Todo) task;
            line = "T" + " | " +
                    boolToInt(todo.isDone()) +
                    " | " + todo.description;
        }
        return line;
    }

    /**
     * Converts boolean to integer.
     *
     * @param b the boolean value to be turned either to 0 or 1
     * @return 0 or 1
     */
    private static int boolToInt(boolean b) {
        return Boolean.compare(b, false);
    }


    /**
     * Each line that the user inputs is parsed and processed.
     *
     * @param line the line from the command line that needs to be parsed in order
     *             for appropriate action to be taken
     * @throws DukeIllegalKeyword throws exception if invalid keyword is typed
     * @throws DukeIllegalDescription throws exception if no description is added
     */
    static void processLine(String line) throws DukeIllegalKeyword, DukeIllegalDescription {
        if (line.equals("list")){
            UI.printList();
        } else if (line.startsWith("mark")){
            int indexToMark = Integer.parseInt(line.substring(5));
            UI.markTask(indexToMark);
        } else if(line.startsWith("unmark")){
            int indexToUnmark = Integer.parseInt(line.substring(7));
            UI.unMarkTask(indexToUnmark);
        } else if (line.startsWith("todo")){
            if (line.length() < 5){
                throw new DukeIllegalDescription();
            }
            String todoDescription = line.substring(4);
            Todo task = new Todo(todoDescription);
            TaskList.taskList.add(task);
            UI.printAddedItem(task);
        } else if (line.startsWith("deadline")){
            if (line.length() < 9 || !line.contains("/by")){
                throw new DukeIllegalDescription();
            }
            int byIndex = line.indexOf("/by");
            String deadlineDescription = line.substring(8, byIndex - 1);
            String by = line.substring(byIndex + 4);
            Deadline task = new Deadline(deadlineDescription, LocalDate.parse(by));
            TaskList.taskList.add(task);
            UI.printAddedItem(task);
        } else if (line.startsWith("event")){
            if (line.length() < 6 || !line.contains("/at")){
                throw new DukeIllegalDescription();
            }
            int atIndex = line.indexOf("/at");
            String eventDescription = line.substring(5, atIndex - 1);
            String at = line.substring(atIndex + 3);
            Event task = new Event(eventDescription, at);
            TaskList.taskList.add(task);
            UI.printAddedItem(task);
        } else if (line.startsWith("delete")) {
            if (line.length() < 8) {
                throw new DukeIllegalDescription();
            }
            int indexToDelete = Integer.parseInt(line.substring(7));
            Task deletedTask = TaskList.taskList.get(indexToDelete - 1);
            TaskList.taskList.remove(indexToDelete - 1);
            UI.printDeletedItem(deletedTask);
        } else if (line.startsWith("find")) {
            if (line.length() < 6) {
                throw new DukeIllegalDescription();
            }
            String stringToSearch = line.substring(5);
            Searcher.searchInList(stringToSearch);
        }else throw new DukeIllegalKeyword();
    }
}
