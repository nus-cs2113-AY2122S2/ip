package duke.tasklist;

import duke.exception.AdditionalException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

public class AddTask extends TaskList {

    public static void addTask(String request, String typeOfTask, boolean isNewRequest) throws AdditionalException {
        String description = getDescription(request, typeOfTask);
        listOfTasks.add(new ToDo(description, typeOfTask));
        if (isNewRequest) {
            saveDataToFile(typeOfTask);
        }
    }

    public static void addTask(String request, String typeOfTask, String preposition, boolean isNewRequest)
            throws AdditionalException {
        String description = getDescription(request, typeOfTask, preposition);
        String timing = getTiming(request, preposition);
        switch (typeOfTask) {
        case "deadline":
            listOfTasks.add(new Deadline(description, timing, typeOfTask));
            break;
        case "event":
            listOfTasks.add(new Event(description, timing, typeOfTask));
            break;
        default:
            System.out.println("Oh no D: There seems to be a problem creating the task");
        }
        if (isNewRequest) {
            saveDataToFile(typeOfTask);
        }
    }

    private static void saveDataToFile(String typeOfTask) {
        int indexOfLastTask = listOfTasks.size() - 1;
        Storage.saveData(typeOfTask, listOfTasks.get(indexOfLastTask), listOfTasks);
    }

}
