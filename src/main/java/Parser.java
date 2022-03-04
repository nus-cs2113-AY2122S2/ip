public class Parser {
    private final String SEPARATOR = "-------------------------------------------";
    private final TaskList taskList;
    private final Storage storage;

    public Parser(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    public void parseCommand(String fullText) {
        String[] words = fullText.split(" ");
        String firstWord = words[0];

        switch (firstWord) {

        case "list":
            taskList.printTasks();
            break;

        case "mark":
            taskList.handleMark(fullText);

            break;

        case "unmark":
            taskList.handleUnmark(fullText);
            break;

        case "todo":
            taskList.addToDo(fullText);
            break;

        case "deadline":
            taskList.addDeadline(fullText);
            break;

        case "event":
            taskList.addEvent(fullText);
            break;

        case "delete":
            taskList.deleteTask(fullText);
            break;

        case "find":
            taskList.findTask(fullText);
            break;

        default:
            System.out.println("What mean");
            System.out.println(SEPARATOR);
        }
        storage.writeData(taskList);
    }
}
