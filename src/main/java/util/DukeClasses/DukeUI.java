package util.DukeClasses;

import util.exception.NoDateException;
import util.exception.NoItemException;
import util.exception.NoTaskException;
import util.miscellaneous.Chatbot;
import util.miscellaneous.CommandType;
import util.task.Task;

import java.util.ArrayList;

public class DukeUI implements Chatbot {
    public static int handleError(ArrayList<Task> tasks, String line, CommandType c) {
        try {
            DukeParser.checkCommand(tasks, line, c);
        } catch (IndexOutOfBoundsException e01) {
            DukePrinter.echo(ITEM_NOT_EXIST_MSG);
            return 1;
        } catch (NoDateException e02) {
            DukePrinter.echo(NO_DATE_MSG);
            return 1;
        } catch (NoTaskException e03) {
            DukePrinter.echo(NO_TASK_MSG);
            return 1;
        } catch (NoItemException e04) {
            DukePrinter.echo(ITEM_NOT_EXIST_MSG);
            return 1;
        } finally {
            return 0;
        }
    }

    public static void loadAndRun(DukeTaskList list, String line, boolean isLoadingData, boolean needUpdateTaskStatus) {
        CommandType command = DukeParser.findCommandType(line);

        int i = handleError(list.tasks, line, command);
        if (i == ERROR_INDICATION_NUMBER) {
            return;
        }

        switch (command) {
        case TODO:
            String todo = line.substring(TODO_TASK_INDEX);

            list.addTodo(todo);

            if(needUpdateTaskStatus) {
                (list.getTask(list.size() - 1)).mark();
            }

            if (!isLoadingData) {
                DukePrinter.echo("Added " + list.getTask(list.size() - 1) + " to the list");
            }

            break;
        case DEADLINE:
            String by = line.substring(line.indexOf(DEADLINE_OF_TASK_CMD) + TIME_INDEX);
            String deadline = line.substring(DEADLINE_TASK_INDEX, line.indexOf(DEADLINE_OF_TASK_CMD));
            list.addDeadline(deadline, by);

            if(needUpdateTaskStatus) {
                (list.getTask(list.size() - 1)).mark();
            }

            if (!isLoadingData) {
                DukePrinter.echo("Added " + list.getTask(list.size() - 1) + " to the list");
            }

            break;
        case EVENT:
            String at = line.substring(line.indexOf(DURATION_OF_EVENT_CMD) + TIME_INDEX);
            String event = line.substring(EVENT_TASK_INDEX, line.indexOf(DURATION_OF_EVENT_CMD));
            list.addEvent(event, at);

            if(needUpdateTaskStatus) {
                (list.getTask(list.size() - 1)).mark();
            }

            if (!isLoadingData) {
                DukePrinter.echo("Added " + list.getTask(list.size() - 1) + " to the list");
            }

            break;
        case MARK:
            int markedItem = Integer.parseInt(line.substring(MARKED_ITEM_INDEX)) - 1;
            list.mark(markedItem);
            DukePrinter.printMark(list.tasks, markedItem);
            break;
        case UNMARK:
            int unmarkedItem = Integer.parseInt(line.substring(UNMARKED_ITEM_INDEX)) - 1;
            list.unmark(unmarkedItem);
            DukePrinter.printUnmark(list.tasks, unmarkedItem);
            break;
        case LIST:
            DukePrinter.printList(list.tasks);
            break;
        case DEL:
            int index = Integer.parseInt(line.substring(DELETE_INDEX)) - 1;
            DukePrinter.printDelete(list.tasks, index);
            list.deleteItem(index);
            break;
        case SAVE:
            DukeStorage.saveData(list.tasks);
            DukePrinter.printSave();
            break;
        case NIL:
            DukePrinter.echo(line);
            break;
        default:
            break;
        }
    }
}
