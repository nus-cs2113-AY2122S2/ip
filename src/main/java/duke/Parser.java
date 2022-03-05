package duke;

public class Parser {

    static Task lineToTask(String line){
        Task task = null;
        int isDoneInt;
        char indicator = line.charAt(0);
        switch (indicator) {
        case 'D':
            int byIndex = line.indexOf("|", 8);
            String deadlineDescription = line.substring(8, byIndex - 1);
            String by = line.substring(byIndex + 2);
            task = new Deadline(deadlineDescription, by);
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

    static String taskToLine(Task task) {
        String line = "";
        // code block
        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            line = "D" + " | " +
                    boolToInt(deadline.isDone()) +
                    " | " + deadline.description +
                    " | " + deadline.getBy();
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

    private static int boolToInt(boolean b) {
        return Boolean.compare(b, false);
    }

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
            String by = line.substring(byIndex + 3);
            Deadline task = new Deadline(deadlineDescription, by);
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
