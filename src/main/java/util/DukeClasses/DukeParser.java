package util.DukeClasses;

import java.util.ArrayList;

import util.miscellaneous.Chatbot;
import util.miscellaneous.CommandType;
import util.task.Task;
import util.exception.NoDateException;
import util.exception.NoTaskException;
import util.exception.NoItemException;

public class DukeParser implements Chatbot {
    public static CommandType findCommandType(String line) {
        CommandType c;

        if (line.startsWith(ADD_TODO_CMD)) {
            c = CommandType.TODO;
        } else if (line.startsWith(ADD_DEADLINE_CMD)) {
            c = CommandType.DEADLINE;
        } else if (line.startsWith(ADD_EVENT_CMD)) {
            c = CommandType.EVENT;
        } else if (line.startsWith(MARK_TASK_CMD)) {
            c= CommandType.MARK;
        } else if (line.startsWith(UNMARK_TASK_CMD)){
            c = CommandType.UNMARK;
        } else if (line.equals(LIST_TASKS_CMD)){
            c = CommandType.LIST;
        } else if (line.startsWith(DELETE_CMD)){
            c = CommandType.DEL;
        } else if (line.equals(SAVE_CMD)) {
            c = CommandType.SAVE;
        } else if (line.startsWith(FIND_CMD)) {
            c = CommandType.FIND;
        } else {
            c = CommandType.NIL;
        }

        return c;
    }

    public static void checkCommand(ArrayList<Task> tasks,String line, CommandType c) throws NoTaskException, NoDateException, NoItemException {
        switch (c) {
        case TODO:
            String todo = line.substring(TODO_TASK_INDEX);

            if ((todo.trim()).isEmpty()) {
                throw new NoTaskException();
            }

            break;
        case DEADLINE:
            String by = line.substring(line.indexOf(DEADLINE_OF_TASK_CMD) + TIME_INDEX);
            String deadline = line.substring(DEADLINE_TASK_INDEX, line.indexOf(DEADLINE_OF_TASK_CMD));

            if ((deadline.trim()).isEmpty()) {
                throw new NoTaskException();
            }

            if ((by.trim()).isEmpty()) {
                throw new NoDateException();
            }
            break;
        case EVENT:
            String at = line.substring(line.indexOf(DURATION_OF_EVENT_CMD) + TIME_INDEX);
            String event = line.substring(EVENT_TASK_INDEX, line.indexOf(DURATION_OF_EVENT_CMD));

            if ((event.trim()).isEmpty()) {
                throw new NoTaskException();
            }

            if ((at.trim()).isEmpty()) {
                throw new NoDateException();
            }

            break;
        case MARK:
            int markedItem = Integer.parseInt(line.substring(MARKED_ITEM_INDEX)) - 1;

            if ((markedItem < 0) || (markedItem >= tasks.size())) {
                throw new NoItemException();
            }

            break;
        case UNMARK:
            int unmarkedItem = Integer.parseInt(line.substring(UNMARKED_ITEM_INDEX)) - 1;

            if ((unmarkedItem < 0) || (unmarkedItem >= tasks.size())) {
                throw new NoItemException();
            }

            break;
        case DEL:
            int index = Integer.parseInt(line.substring(DELETE_INDEX)) - 1;

            if ((index < 0) || (index >= tasks.size())) {
                throw new NoItemException();
            }

            break;
        default:
            break;
        }
    }
}
