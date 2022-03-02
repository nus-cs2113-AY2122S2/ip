/**
 * The CommandHandler class executes commands according to the user's input
 */
public class CommandHandler {

    public static final String DEADLINE_INDICATOR = " /by ";
    public static final String EVENT_INDICATOR = " /at ";

    /**
     * Call the Parser class to identify the target Task, and mark it as done
     * If IndexOutOfBoundsException is caught, either the user did not specify task number or task does not exist
     *
     * @param userInput String containing "mark" followed by the task number
     */
    public static void markTask(String userInput) {
        try {
            int taskIndex = Parser.getTaskIndex(userInput);
            Task targetTask = TaskList.get(taskIndex);
            targetTask.markAsDone();
        } catch (IndexOutOfBoundsException e) {
            Ui.printWrongFormat();
        }
    }

    /**
     * Call the Parser class to identify the target Task, and mark it as not done
     * If IndexOutOfBoundsException is caught, either the user did not specify task number or task does not exist
     *
     * @param userInput String containing "unmark" followed by the task number
     */
    public static void unmarkTask(String userInput) {
        try {
            int taskIndex = Parser.getTaskIndex(userInput);
            Task targetTask = TaskList.get(taskIndex);
            targetTask.markAsUndone();
        } catch (IndexOutOfBoundsException e) {
            Ui.printWrongFormat();
        }
    }

    /**
     * Call the Parser class to identify the target Task, and delete it
     * If IndexOutOfBoundsException is caught, either the user did not specify task number or task does not exist
     *
     * @param userInput String containing "delete" followed by the task number
     */
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

    /**
     * Add a new ToDo Task
     *
     * @param todoTask String containing description of ToDo
     * @throws IndexOutOfBoundsException If task description is not explicated
     */
    public static void addTodo(String todoTask) {
        todoTask = todoTask.trim();
        if (todoTask.length() == 0) {
            throw new IndexOutOfBoundsException();
        }
        TaskList.add(new ToDo(todoTask));
    }

    /**
     * Add a new Deadline Task, with parameters derived with the help of Parser class
     *
     * @param deadlineTask String containing description of Deadline and deadline time
     * @throws IndexOutOfBoundsException If task description or deadline time is not explicated
     */
    public static void addDeadline(String deadlineTask) {
        String[] deadlineInputs = Parser.parseAdditionalParameters(deadlineTask, DEADLINE_INDICATOR);
        if (deadlineInputs[0].length() == 0 || deadlineInputs[1].length() == 0){
            throw new IndexOutOfBoundsException();
        }
        TaskList.add(new Deadline(deadlineInputs[0], deadlineInputs[1]));
    }

    /**
     * Add a new Event Task, with parameters derived with the help of Parser class
     *
     * @param eventTask String containing description of Event and event time
     * @throws IndexOutOfBoundsException If task description or event time is not explicated
     */
    public static void addEvent(String eventTask) {
        String[] eventInputs = Parser.parseAdditionalParameters(eventTask, EVENT_INDICATOR);
        if (eventInputs[0].length() == 0 || eventInputs[1].length() == 0){
            throw new IndexOutOfBoundsException();
        }
        TaskList.add(new Event(eventInputs[0], eventInputs[1]));
    }

}
