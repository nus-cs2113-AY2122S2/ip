import em.exception.InvalidUserInputException;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.io.IOException;
import java.util.ArrayList;

import static em.exception.InvalidUserInputException.NO_DESCRIPTION;

public class TaskList {
    public static final String LINE_SEPARATOR = "____________________________________________________________\n";
    private static Parser parser;
    public static ArrayList<Task> taskList = new ArrayList<>();

    public TaskList(ArrayList taskList) {
        this.taskList = taskList;
    }

    public static void addTask(String userInput, ArrayList<Task> taskList, Boolean isUserCommand) {
        String[] taskDescription = userInput.split(" ", 2);
        Task newTask = new ToDo(taskDescription[1]);
        taskList.add(newTask);

        if (isUserCommand) {
            System.out.println(LINE_SEPARATOR + "Got it. I've added this task:");
            System.out.println(taskList.get(taskList.size() - 1).toString());
            System.out.println("Now you have " + taskList.size() + " tasks in the list.\n" + LINE_SEPARATOR);
            try {
                Storage.writeToFile(parser.formulateDatabaseInput(taskDescription));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void addTaskAndTime(String userInput, ArrayList<Task> taskList, Boolean isUserCommand) {
        String[] arrayOfUserInput = userInput.split(" ", 2);
        arrayOfUserInput = userInput.split("/", 2);
        String taskLongDesc = arrayOfUserInput[0];
        String[] taskDescription = taskLongDesc.split(" ", 2);
        String timeWithTaskType = arrayOfUserInput[1];
        String[] timing = timeWithTaskType.split(" ", 2);
        Task newTask;
        try {
            if (taskDescription[1].trim().equals("")) {
                throw new InvalidUserInputException(NO_DESCRIPTION);
            }

            if (timing[0].equals("by")) {
                newTask = new Deadline(taskDescription[1], timing[1]);
            } else {
                newTask = new Event(taskDescription[1], timing[1]);
            }
            taskList.add(newTask);
            if (isUserCommand) {
                System.out.println(LINE_SEPARATOR + "Got it. I've added this task:");
                System.out.println(taskList.get(taskList.size() - 1).toString());
                System.out.println("Now you have " + taskList.size() + " tasks in the list.\n" + LINE_SEPARATOR);
                try {
                    String[] databaseInput = new String[3];
                    if (timing[0].equals("by")) {
                        databaseInput[0] = "deadline";
                    } else {
                        databaseInput[0] = "event";
                    }
                    databaseInput[1] = taskDescription[1];
                    databaseInput[2] = timing[1];
                    Storage.writeToFile(parser.formulateDatabaseInput(databaseInput));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (InvalidUserInputException e) {
            System.out.println(e.getMessage());
        }
    }
}
