public class CommandHandler {

    public static final String DEADLINE_INDICATOR = " /by ";
    public static final String EVENT_INDICATOR = " /at ";

    public static void markTask(String userInput) {
        try {
            int taskIndex = Parser.getTaskIndex(userInput);
            TaskList.get(taskIndex).markAsDone();
        } catch (IndexOutOfBoundsException e) {
            Ui.printWrongFormat();
        }
    }

    public static void unmarkTask(String userInput) {
        try {
            int taskIndex = Parser.getTaskIndex(userInput);
            TaskList.get(taskIndex).markAsUndone();
        } catch (IndexOutOfBoundsException e) {
            Ui.printWrongFormat();
        }
    }

    public static void deleteTask(String userInput) {
        try {
            int taskIndex = Parser.getTaskIndex(userInput);
            Ui.printDeleteFromList(taskIndex);
            TaskList.remove(taskIndex);
            Ui.printListSize();
        } catch (IndexOutOfBoundsException e) {
            Ui.printWrongFormat();
        }
    }

    public static void addTodo(String todoTask) {
        todoTask = todoTask.trim();
        if (todoTask.length() == 0) {
            throw new IndexOutOfBoundsException();
        }
        TaskList.add(new ToDo(todoTask));
    }

    public static void addDeadline(String deadlineTask) {
        String[] deadlineInputs = Parser.parseAdditionalParameters(deadlineTask, DEADLINE_INDICATOR);
        if (deadlineInputs[0].length() == 0 || deadlineInputs[1].length() == 0){
            throw new IndexOutOfBoundsException();
        }
        TaskList.add(new Deadline(deadlineInputs[0], deadlineInputs[1]));
    }

    public static void addEvent(String eventTask) {
        String[] eventInputs = Parser.parseAdditionalParameters(eventTask, EVENT_INDICATOR);
        if (eventInputs[0].length() == 0 || eventInputs[1].length() == 0){
            throw new IndexOutOfBoundsException();
        }
        TaskList.add(new Event(eventInputs[0], eventInputs[1]));
    }

}
