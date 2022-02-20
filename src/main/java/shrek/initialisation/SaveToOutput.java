package shrek.initialisation;

import shrek.data.ErrorCount;
import shrek.exception.InvalidCommandException;
import shrek.helper.FileLogic;
import shrek.helper.Time;
import shrek.task.Deadlines;
import shrek.task.Events;
import shrek.task.UserContent;
import shrek.data.ListOfTasks;

import java.io.IOException;
import java.text.ParseException;

public class SaveToOutput {
    private static final String TODO_TASK_NAME = "T";
    private static final String DEADLINE_TASK_NAME = "D";
    private static final String EVENT_TASK_NAME = "E";

    public static void saveData() throws InvalidCommandException {
        try {
            FileLogic.clearOutput();
            for (UserContent task : ListOfTasks.lists) {
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
            throw new InvalidCommandException("Invalid task name", ErrorCount.errorCount);
        } catch (ClassCastException e) {
            throw new InvalidCommandException("Cannot anyhow typecast leh", ErrorCount.errorCount);
        } catch (ParseException e) {
            throw new InvalidCommandException("date time error", ErrorCount.errorCount);
        } catch (InvalidCommandException e) {
            ErrorCount.errorCount++;
        }
    }

    public static void saveTodoToOutput(UserContent task) throws IOException {
        String baseString = "todo";
        String mark = FileLogic.convertMark(task);
        String taskContent = task.getContent();
        baseString = mark + " " + baseString + " " + taskContent;
        FileLogic.writeToFile(baseString);
    }

    public static void saveDeadlineToOutput(Deadlines task) throws IOException, ParseException {
        String baseString = "deadline";
        String mark = FileLogic.convertMark(task);
        String taskContent = task.getContent();
        String taskBy = task.getTaskDueBy();
        String userInputStyleDatetime = Time.revertDatetime(taskBy);
        baseString = mark + " " + baseString + " " + taskContent + "/by " + userInputStyleDatetime;
        FileLogic.writeToFile(baseString);
    }

    public static void saveEventToOutput(Events task) throws IOException, ParseException {
        String baseString = "event";
        String mark = FileLogic.convertMark(task);
        String taskContent = task.getContent();
        String taskAt = task.getEventOccurAt();
        String userInputStyleDatetime = Time.revertDatetime(taskAt);
        baseString = mark + " " + baseString + " " + taskContent + "/at " + userInputStyleDatetime;
        FileLogic.writeToFile(baseString);
    }
}
