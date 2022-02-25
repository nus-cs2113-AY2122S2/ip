package duke.util;

import duke.tasks.*;

import java.util.List;



public class TaskDecoder {

    /**
     * Decode a string into a {@code TaskList}.
     * @param taskListString string read from storage file
     * @return task list which contains all tasks stored.
     */
    public static TaskList decodeTaskList(List<String> taskListString){
        TaskList taskList = new TaskList();
        for (String taskString :taskListString){
            Task task = decodeStringToTask(taskString);
            taskList.addTask(task);
        }
        return taskList;
    }

    /**
     * Decode a string into a {@code Task}.
     * @param taskString string read from storage file
     * @return a task
     */
    public static Task decodeStringToTask(String taskString){
        String[] arrOfText = taskString.split("\\|");
        if (arrOfText.length == 3) {
            Todo todo = new Todo(arrOfText[2]);
            if (Integer.parseInt(arrOfText[1]) == 1){
                todo.markAsDone();
            }
            return todo;
        }
        else if (arrOfText.length == 4) {
            switch (arrOfText[0]){
            case "D":
                Deadline deadline = new Deadline(arrOfText[2],arrOfText[3]);
                if (Integer.parseInt(arrOfText[1]) == 1){
                    deadline.markAsDone();
                }
                return deadline;
            case "E":
                Event event = new Event(arrOfText[2],arrOfText[3]);
                if (Integer.parseInt(arrOfText[1]) == 1){
                    event.markAsDone();
                }
                return event;
            default:
                System.out.println("Invalid string.");
                break;
            }
        }
        return null;
    }

}
