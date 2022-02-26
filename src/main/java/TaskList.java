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

    public static String processDateAndTime(String timeAndDate) {
        String finalDateAndTime = timeAndDate;
        String date, time;
        try {
            String[] arrayOfTimeAndDate = timeAndDate.split(" ");
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

    public static String processTime(String timeInput) {
        try {
            if (!isTimeProcessed(timeInput)) {
                int hourOfDay = Integer.parseInt(timeInput) / 100;
                int minute = Integer.parseInt(timeInput) % 100;
                if (minute > 59) {
                    throw new InvalidUserInputException(INVALID_TIME_OR_DATE);
                }
                String time = ((hourOfDay > 12) ? hourOfDay % 12 : hourOfDay)
                        + ":" + (minute < 10 ? ("0" + minute) : minute) + ((hourOfDay >= 12) ? "PM" : "AM");
                return time;
            }
        } catch (InvalidUserInputException e) {
            System.out.println(e.getMessage());
        }
        return timeInput;
    }

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

    public static boolean isTimeProcessed(String inTime) {
        if (inTime.contains("PM") || inTime.contains("AM")) {
            return true;
        }
        return false;
    }

    public static ArrayList findContent (String keyword) {
        ArrayList<Integer> keywordList = new ArrayList<>();
        List<String> fileContentLines = null;
        try {
            fileContentLines = Files.readAllLines(Storage.databasePath);
            int taskNumber = 1;
            for (String lines : fileContentLines) {
                String[] contentsInALine = lines.split(",", -1);
                if (contentsInALine.length < 1) {
                    throw new InvalidUserInputException(INVALID_INPUT);
                }
                if (contentsInALine[2].contains(keyword)) {
                    keywordList.add(taskNumber);
                }
                taskNumber += 1;
            }
            Ui.displayFoundTask(taskList, keywordList);
        } catch (InvalidUserInputException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return keywordList;
    }

}
