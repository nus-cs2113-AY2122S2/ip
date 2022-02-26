import em.exception.InvalidUserInputException;
import em.exception.StorageException;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static em.exception.InvalidUserInputException.*;
import static em.exception.StorageException.INVALID_FILE_INPUT;

public class TaskList {
    public static final String LINE_SEPARATOR = "____________________________________________________________\n";
    private static Parser parser;
    public static ArrayList<Task> taskList = new ArrayList<>();

    public TaskList(ArrayList taskList) {
        this.taskList = taskList;
    }

    /**
     * To add a task with no date and/or time into the task list.
     * Only add task type of "todo".
     * Upon successful adding the task into the task list, a message will
     * be displayed to indicate the command is processed successfully and also
     * indicates the total number of tasks that exist in the task list.
     * In addition, the newly added task will also be written into the database file
     * which stores all the task information in the task list.
     *
     * @param userInput command entered by user.
     * @param taskList arrayList to store all tasks created.
     * @param isUserCommand states whether command is user input or file input.
     * @throws IOException If the input and output have error.
     */
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
                System.out.println(IO_EXCEPTION);
            }
        }
    }

    /**
     * To add a task with date and/or time into the task list.
     * Only add task type of "deadline" and "event".
     * It will ensure that the date and time of the task meets the required format.
     *
     * Upon successful adding the task into the task list, a message will
     * be displayed to indicate the command is processed successfully and also
     * indicates the total number of tasks that exist in the task list.
     * In addition, the newly added task will also be written into the database file
     * which stores all the task information in the task list.
     *
     * @param userInput command entered by user.
     * @param taskList arrayList to store all tasks created.
     * @param isUserCommand states whether command is user input or file input.
     * @throws InvalidUserInputException If the task description is empty.
     * @throws IOException If the input and output have error.
     */
    public static void addTaskAndTime(String userInput, ArrayList<Task> taskList, Boolean isUserCommand) {
        String[] arrayOfUserInput = userInput.split("/", 2);
        String[] taskDescription = arrayOfUserInput[0].split(" ", 2);
        String[] timing = arrayOfUserInput[1].split(" ", 2);
        String formattedTimeAndDate = processDateAndTime(timing[1]);
        Boolean hasTimeAndDateFormattedFromFile = true;
        Boolean needToChangeContent = false;
        String oldTimeAndDate = null;

        if (formattedTimeAndDate.equals(timing[1]) && isUserCommand) {
            return;
        }
        //file date and time input is not formatted
        if (!(formattedTimeAndDate.equals(timing[1])) && !isUserCommand) { //date in file is not formatted
            hasTimeAndDateFormattedFromFile = false;
            oldTimeAndDate = timing[1];
            needToChangeContent = true;
        }
        timing[1] = formattedTimeAndDate;
        Task newTask;
        try {
            if (taskDescription[1].trim().equals("")) {
                throw new InvalidUserInputException(NO_DESCRIPTION); //check if there is task description
            }

            if (timing[0].equals("by")) {
                newTask = new Deadline(taskDescription[1], timing[1]);
            } else {
                newTask = new Event(taskDescription[1], timing[1]);
            }
            taskList.add(newTask); //adding of new task
            if (isUserCommand || !hasTimeAndDateFormattedFromFile) { // need to write in database
                try {
                    String[] databaseInput = new String[3];
                    switch (timing[0]) {
                    case "by":
                        databaseInput[0] = "deadline";
                        break;
                    case "at":
                        databaseInput[0] = "event";
                        break;
                    default:
                        break;
                    }

                    databaseInput[1] = taskDescription[1];
                    databaseInput[2] = timing[1];

                    String newData = parser.formulateDatabaseInput(databaseInput);
                    if (needToChangeContent){
                        Storage.replaceContent(userInput, newData, oldTimeAndDate); //replace database format of unformatted files;
                    } else {
                        System.out.println(LINE_SEPARATOR + "Got it. I've added this task:");
                        System.out.println(taskList.get(taskList.size() - 1).toString());
                        System.out.println("Now you have " + taskList.size() + " tasks in the list.\n" + LINE_SEPARATOR);
                        Storage.writeToFile(newData);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (InvalidUserInputException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Return the correct format of the date and time as a string that can be
     * processed and be added into the task list.
     * Ensure that the date is in "MMM-d-yyyy" format and the time in 12-hour format.
     *
     * @param DateAndTime time and date inputted by the user.
     * @return finalDateAndTime correct format of the date and time to be processed.
     * @throws InvalidUserInputException If the time and date entered is invalid such as not a time/date.
     * @throws ArrayIndexOutOfBoundsException If DateAndTime length is out of bound.
     * @throws DateTimeException If the format of the date is not in the right format/invalid.
     * @throws NullPointerException If no date and time is being parsed in.
     */
    public static String processDateAndTime(String DateAndTime) {
        String finalDateAndTime = DateAndTime;
        String date, time;
        try {
            String[] arrayOfTimeAndDate = DateAndTime.split(" ");
            switch (arrayOfTimeAndDate.length) {
            case (1):
                date = processDate(arrayOfTimeAndDate[0]);
                finalDateAndTime = date;
                break;
            case (2):
                date = processDate(arrayOfTimeAndDate[0]);
                time = processTime(arrayOfTimeAndDate[1]);
                finalDateAndTime = date + " " + time;
                break;
            default:
                throw new InvalidUserInputException(INVALID_TIME_OR_DATE);
            }
        } catch (InvalidUserInputException e) {
            System.out.println(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(INVALID_TIME_OR_DATE);
        } catch (DateTimeException e) {
            System.out.println(INVALID_TIME_OR_DATE);
        } catch (NullPointerException e) {
            System.out.println(INVALID_TIME_OR_DATE);
        }
        return finalDateAndTime;
    }

    /**
     * Return the correct format of the time that can be processed and be
     * added into the task list.
     * Take in timeInput which can be in 24 hour or 12-hour format, and convert it to
     * 12-hour format. If the format of timeInput is not in the correct format, it will formulate it
     * to be in the correct format (in 12-hour format) such as "12:20PM".
     *
     * @param timeInput time inputted by the user.
     * @return time correct format of the time to be processed.
     */
    public static String processTime(String timeInput) {
        String time = timeInput;
        try {
            if (!isTimeProcessed(timeInput)) {
                int hourOfDay = Integer.parseInt(timeInput) / 100;
                int minute = Integer.parseInt(timeInput) % 100;
                if (minute > 59) {
                    throw new InvalidUserInputException(INVALID_TIME_OR_DATE);
                }
                time = ((hourOfDay > 12) ? hourOfDay % 12 : hourOfDay)
                        + ":" + (minute < 10 ? ("0" + minute) : minute) + ((hourOfDay >= 12) ? "PM" : "AM");
            }
        } catch (InvalidUserInputException e) {
            System.out.println(e.getMessage());
        }
        return time;
    }

    /**
     * Return the correct format of the date that can be processed and be
     * added into the task list.
     * If the format of dateInput is not in the correct format, it will formulate it
     * to be in the correct format ("MMM-d-yyyy").
     *
     * @param dateInput date inputted by the user.
     * @return date correct format of the date to be processed.
     */
    public static String processDate(String dateInput) {
        String date;
        Boolean isDateProcessed = isDateProcessed(dateInput);
        if (!isDateProcessed) {
            LocalDate date1 = LocalDate.parse(dateInput);
            date = date1.format(DateTimeFormatter.ofPattern("MMM-d-yyyy"));
        } else {
            date = dateInput;
        }
        return date;
    }

    /**
     * Check if the date is in the correct format, if it is in the correct
     * format then there is no need for to change the format of the date,
     * else otherwise.
     *
     * @param inDate date inputted by the user.
     * @return true if the date input matches the stated format.
     * @return false if the date input does not match the stated format.
     * @throws ParseException If date is not in the stated format.
     */
    public static boolean isDateProcessed(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM-d-yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    /**
     * Check if the time is in the correct format, if it is in the correct
     * format then there is no need for to change the format of the time,
     * else otherwise.
     *
     * @param inTime time inputted by the user.
     * @return true if the time input matches the stated format.
     * @return false if the time input does not match the stated format.
     */
    public static boolean isTimeProcessed(String inTime) {
        if (inTime.contains("PM") || inTime.contains("AM")) {
            return true;
        }
        return false;
    }

    /**
     * Returns a list containing all the task that contains the keywords.
     * Search the entire list of tasks to look for tasks that matched the keyword
     * stated.
     *
     * @param keyword word or a short term to be matched in the entire task list.
     * @return List of task that matched with keyword search.
     * @throws InvalidUserInputException If keyword to be searched is not stated.
     */
    public static ArrayList findContent (String keyword) {
        ArrayList<Integer> keywordList = new ArrayList<>();
        List<String> fileContentLines = null;
        try {
            fileContentLines = Files.readAllLines(Storage.databasePath);
            int taskNumber = 1;
            for (String lines : fileContentLines) {
                String[] contentsInALine = lines.split(",", -1);
                if (contentsInALine.length < 1) {
                    throw new InvalidUserInputException(NO_DESCRIPTION);
                }
                if (contentsInALine[2].contains(keyword)) {
                    keywordList.add(taskNumber);
                }
                taskNumber += 1;
            }
            Ui.displayFoundTask(taskList, keywordList);
        } catch (InvalidUserInputException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return keywordList;
    }

}
