package shrek.storage;

import shrek.data.ErrorCount;
import shrek.exception.InvalidCommandException;
import shrek.helper.FileHandler;
import shrek.helper.Time;
import shrek.task.Deadlines;
import shrek.task.Events;
import shrek.task.Task;
import shrek.data.TaskList;

import java.io.IOException;
import java.text.ParseException;

/**
 * Saves list to output file.
 */
public class SaveToOutput {
    private static final String TODO_TASK_NAME = "T";
    private static final String DEADLINE_TASK_NAME = "D";
    private static final String EVENT_TASK_NAME = "E";

    /**
     * Decides which task is contained in the list and passes the perimeters to its respective handlers.
     *
     * @throws InvalidCommandException If task, task name or task time is invalid, there are typecasting issues
     *                                 or file writing issues.
     */
    public static void saveData() throws InvalidCommandException {
        try {
            FileHandler.clearOutput();
            for (Task task : TaskList.lists) {
                String taskName = task.getTaskName();
                switch (taskName) {
                case TODO_TASK_NAME:
                    saveTodoToOutput(task);
                    break;
                case DEADLINE_TASK_NAME:
                    Deadlines deadlineTask = (Deadlines) task;
                    saveDeadlineToOutput(deadlineTask);
                    break;
                case EVENT_TASK_NAME:
                    Events eventTask = (Events) task;
                    saveEventToOutput(eventTask);
                    break;
                default:
                    throw new InvalidCommandException("Not a valid task to save!", ErrorCount.errorCount);
                }
            }
        } catch (IOException e) {
            throw new InvalidCommandException("Cannot write to file!", ErrorCount.errorCount);
        } catch (NullPointerException e) {
            throw new InvalidCommandException("Invalid time or task name", ErrorCount.errorCount);
        } catch (ClassCastException e) {
            throw new InvalidCommandException("Cannot anyhow typecast", ErrorCount.errorCount);
        } catch (ParseException e) {
            throw new InvalidCommandException("Date time error", ErrorCount.errorCount);
        } catch (InvalidCommandException e) {
            ErrorCount.errorCount++;
        }
    }

    /**
     * Saves a Todo task to output.
     *
     * @param task Todo task to be saved.
     * @throws IOException When todo task is improperly formatted.
     */
    public static void saveTodoToOutput(Task task) throws IOException {
        String baseString = "todo";
        String mark = FileHandler.convertMark(task);
        String taskContent = task.getContent();
        baseString = mark + " " + baseString + " " + taskContent;
        FileHandler.writeToFile(baseString);
    }

    /**
     * Saves deadline task to output.
     *
     * @param task Deadline task to be saved
     * @throws IOException    When deadline task is improperly formatted.
     * @throws ParseException If fail to parse datetime.
     */
    public static void saveDeadlineToOutput(Deadlines task) throws IOException, ParseException {
        String baseString = "deadline";
        String mark = FileHandler.convertMark(task);
        String taskContent = task.getContent();
        String taskBy = task.getTaskDueBy();
        String userInputStyleDatetime = Time.revertDatetime(taskBy);
        baseString = mark + " " + baseString + " " + taskContent + "/by " + userInputStyleDatetime;
        FileHandler.writeToFile(baseString);
    }

    /**
     * Saves deadline task to output.
     *
     * @param task Event task to be saved
     * @throws IOException    When event task is improperly formatted.
     * @throws ParseException If fail to parse datetime.
     */
    public static void saveEventToOutput(Events task) throws IOException, ParseException {
        String baseString = "event";
        String mark = FileHandler.convertMark(task);
        String taskContent = task.getContent();
        String taskAt = task.getEventOccurAt();
        String userInputStyleDatetime = Time.revertDatetime(taskAt);
        baseString = mark + " " + baseString + " " + taskContent + "/at " + userInputStyleDatetime;
        FileHandler.writeToFile(baseString);
    }
}
