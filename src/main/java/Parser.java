public class Parser {
    private final UI ui;
    private final TaskList taskList;
    private final Storage storage;

    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String LIST = "list";
    private static final String DELETE = "delete";
    private static final String FIND = "find";
    private static final String SAVE = "save";
    private static final String BYE = "bye";

    public Parser(UI ui, TaskList taskList, Storage storage){
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
    }

    public boolean executeCommand(String rawInput) {
        String commandWord = parseCommand(rawInput);
        String info = parseInfo(rawInput);

        switch(commandWord){
            case TODO:
                taskList.addTodo(info);
                break;
            case DEADLINE:
                taskList.addDeadline(info);
                break;
            case EVENT:
                taskList.addEvent(info);
                break;
            case MARK:
                taskList.mark(info);
                break;
            case UNMARK:
                taskList.unmark(info);
                break;
            case LIST:
                ui.showAllTasks(taskList);
                break;
            case FIND:
                taskList.find(info);
                break;
            case DELETE:
                taskList.delete(info);
                break;
            case SAVE:
                storage.save(taskList);
                break;
            case BYE:
                return true;
            default:
                ui.showInvalidCommandMsg();
                break;
        }
        return false;
    }

    private static String parseCommand(String input){
        if(input.contains(" ")){
            return input.substring(0, input.indexOf(" "));
        }else{
            return input;
        }
    }

    private static String parseInfo(String input){
        if(input.contains(" ")){
            return input.substring(input.indexOf(" ")+1);
        }else{
            return null;
        }
    }
}
