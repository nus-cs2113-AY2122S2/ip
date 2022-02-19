package shrek;

import shrek.task.UserContent;
import shrek.task.Deadlines;
import shrek.task.Events;
import shrek.task.ToDo;
import shrek.constant.PrintStrings;
import shrek.exception.InvalidCommandException;

import java.text.ParseException;
import java.time.DateTimeException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.temporal.ChronoUnit;


public class Shrek {
    private static final ArrayList<UserContent> lists = new ArrayList<>();
    private static final String LINE = PrintStrings.LINE;
    private static final int INDEX_OF_TASK_CONTENT = 0;
    private static final int INDEX_OF_TASK_NAME = 1;
    private static final int INDEX_OF_TASK_COMMAND = 0;
    private static final int INDEX_OF_TASK_INPUT = 1;
    private static final int FIND_TASK_OR_TIME = 0;
    private static final int FIND_CONTENT = 1;
    private static final int INDEX_OF_TASK_MARKED = 0;
    private static final int INDEX_OF_TIME_INPUT = 1;
    private static final int INDEX_OF_DATE_INPUT = 0;
    private static final int NUMBER_OF_TERMS_IN_SPLIT = 2;
    private static final int LIST_INDEX_CORRECTION = -1;
    public static final String NEW_LINE = System.lineSeparator();
    private static int errorCount = 0;
    private static final String[] listOfCommands = {"todo", "deadline", "event", "mark", "unmark", "delete", "save", "find", "list"};
    private static String OUTPUT_FILE_PATH;
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void initialiseShrek() throws InvalidCommandException {
        try {
            String currentDirectory = System.getProperty("user.dir");
            OUTPUT_FILE_PATH = currentDirectory + "/data/output.txt";
            File dataDirectory = new File(currentDirectory + "/data");
            if (!dataDirectory.exists()) {
                dataDirectory.mkdir();
            }
            File outputFile = new File(OUTPUT_FILE_PATH);
            if (!outputFile.createNewFile()) {
                loadFromOutput();
            }
        } catch (IOException e) {
            throw new InvalidCommandException("IO excepts you!", errorCount);
        }
    }

    public static String convertMark(UserContent task) {
        String mark;
        if (task.getMark()) {
            mark = "marked";
        } else {
            mark = "unmarked";
        }
        return mark;
    }

    public static void loadFromOutput() throws FileNotFoundException {
        File outputFile = new File(OUTPUT_FILE_PATH);
        Scanner lineScanner = new Scanner(outputFile);
        while (lineScanner.hasNext()) {
            String[] splitMarkAndContents = lineScanner.nextLine().split(" ", NUMBER_OF_TERMS_IN_SPLIT);
            takeInput(splitMarkAndContents[INDEX_OF_TASK_INPUT], false);
            if (splitMarkAndContents[INDEX_OF_TASK_MARKED].equals("marked")) {
                lists.get(lists.size() + LIST_INDEX_CORRECTION).setMark();
            }
        }
    }

    public static void saveToOutput() throws InvalidCommandException {
        try {
            clearOutput();
            for (UserContent task : lists) {
                String taskName = task.getTaskName();
                switch (taskName) {
                case "T":
                    saveTodoToOutput(task);
                    break;
                case "D":
                    Deadlines deadlineTask = (Deadlines) task;
                    saveDeadlineToOutput(deadlineTask);
                    break;
                case "E":
                    Events eventTask = (Events) task;
                    saveEventToOutput(eventTask);
                    break;
                default:
                    throw new InvalidCommandException("Not a valid task to save!", errorCount);
                }
            }
        } catch (IOException e) {
            throw new InvalidCommandException("Cannot write to file!", errorCount);
        } catch (NullPointerException e) {
            throw new InvalidCommandException("Invalid task name", errorCount);
        } catch (ClassCastException e) {
            throw new InvalidCommandException("Cannot anyhow typecast leh", errorCount);
        } catch (ParseException e) {
            throw new InvalidCommandException("date time error", errorCount);
        } catch (InvalidCommandException e) {
            errorCount++;
        }
    }

    public static void saveTodoToOutput(UserContent task) throws IOException {
        String baseString = "todo";
        String mark = convertMark(task);
        String taskContent = task.getContent();
        baseString = mark + " " + baseString + " " + taskContent;
        writeToFile(baseString);
    }

    public static String revertDatetime(String datetime) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date date = new SimpleDateFormat("MMM d yyyy hh:mma").parse(datetime);
        return formatter.format(date);
    }

    public static void saveDeadlineToOutput(Deadlines task) throws IOException, ParseException {
        String baseString = "deadline";
        String mark = convertMark(task);
        String taskContent = task.getContent();
        String taskBy = task.getBy();
        String userInputStyleDatetime = revertDatetime(taskBy);
        baseString = mark + " " + baseString + " " + taskContent + "/by " + userInputStyleDatetime;
        writeToFile(baseString);
    }

    public static void saveEventToOutput(Events task) throws IOException, ParseException {
        String baseString = "event";
        String mark = convertMark(task);
        String taskContent = task.getContent();
        String taskAt = task.getAt();
        String userInputStyleDatetime = revertDatetime(taskAt);
        baseString = mark + " " + baseString + " " + taskContent + "/at " + userInputStyleDatetime;
        writeToFile(baseString);
    }

    public static void writeToFile(String task) throws IOException {
        FileWriter outputFile = new FileWriter(OUTPUT_FILE_PATH, true);
        outputFile.write(task + System.lineSeparator());
        outputFile.close();
    }

    public static void clearOutput() throws IOException {
        FileWriter outputFile = new FileWriter(OUTPUT_FILE_PATH, false);
        outputFile.write("");
        outputFile.close();
    }

    public static void findTaskOrTime(String input) {
        try {
            String[] splitTaskOrTimeInputs = input.split(" ", NUMBER_OF_TERMS_IN_SPLIT);
            if (splitTaskOrTimeInputs[FIND_CONTENT].equals("")) {
                throw new InvalidCommandException("Did you forget to input time or task?", errorCount);
            }
            switch (splitTaskOrTimeInputs[FIND_TASK_OR_TIME]) {
            case "time":
                findTime(splitTaskOrTimeInputs[FIND_CONTENT]);
                break;
            case "task":
                findTask(splitTaskOrTimeInputs[FIND_CONTENT]);
                break;
            default:
                throw new InvalidCommandException("Did you type time or task wrongly?", errorCount);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandException("Did you forget to declare the time or task?", errorCount);
        }
    }

    public static void findTask(String inputToSearchFor) {
        ArrayList<UserContent> listOfFoundTasks = new ArrayList<>();
        for (UserContent item : lists) {
            if (item.getContent().contains(inputToSearchFor)) {
                listOfFoundTasks.add(item);
            }
        }
        if(listOfFoundTasks.size() == 0){
            System.out.println("The task \"" + inputToSearchFor + "\" was not found in the list, sorry!");
        } else {
            printList(listOfFoundTasks, "Here are the tasks containing \""
                    + inputToSearchFor + "\" in the list");
        }
    }

    public static void findTime(String inputToSearchFor) {
        ArrayList<UserContent> listOfFoundTimes = new ArrayList<>();
        for (UserContent item : lists) {
            String taskName = item.getTaskName();
            switch (taskName) {
            case "T":
                break;
            case "D":
                Deadlines deadlineTask = (Deadlines) item;
                if (deadlineTask.getBy().contains(inputToSearchFor)) {
                    listOfFoundTimes.add(item);
                }
                break;
            case "E":
                Events eventTask = (Events) item;
                if (eventTask.getAt().contains(inputToSearchFor)) {
                    listOfFoundTimes.add(item);
                }
                break;
            default:
                throw new InvalidCommandException("Not a valid task to find!", errorCount);
            }
        }
        if(listOfFoundTimes.size() == 0){
            System.out.println("No task has the deadline or occurs at \"" + inputToSearchFor + "\", sorry!");
        } else {
            printList(listOfFoundTimes, "Here are the times containing \""
                    + inputToSearchFor + "\" in the list");
        }
    }

    public static void printGreeting() {
        String logo = "███████╗██╗  ██╗██████╗ ███████╗██╗  ██╗\n" +
                "██╔════╝██║  ██║██╔══██╗██╔════╝██║ ██╔╝\n" +
                "███████╗███████║██████╔╝█████╗  █████╔╝ \n" +
                "╚════██║██╔══██║██╔══██╗██╔══╝  ██╔═██╗ \n" +
                "███████║██║  ██║██║  ██║███████╗██║  ██╗\n" +
                "╚══════╝╚═╝  ╚═╝╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝\n" +
                "                                        \n";

        String shrekLogo = "⢀⡴⠑⡄⠀⠀⠀⠀⠀⠀⠀⣀⣀⣤⣤⣤⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ \n" +
                "⠸⡇⠀⠿⡀⠀⠀⠀⣀⡴⢿⣿⣿⣿⣿⣿⣿⣿⣷⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀ \n" +
                "⠀⠀⠀⠀⠑⢄⣠⠾⠁⣀⣄⡈⠙⣿⣿⣿⣿⣿⣿⣿⣿⣆⠀⠀⠀⠀⠀⠀⠀⠀ \n" +
                "⠀⠀⠀⠀⢀⡀⠁⠀⠀⠈⠙⠛⠂⠈⣿⣿⣿⣿⣿⠿⡿⢿⣆⠀⠀⠀⠀⠀⠀⠀ \n" +
                "⠀⠀⠀⢀⡾⣁⣀⠀⠴⠂⠙⣗⡀⠀⢻⣿⣿⠭⢤⣴⣦⣤⣹⠀⠀⠀⢀⢴⣶⣆ \n" +
                "⠀⠀⢀⣾⣿⣿⣿⣷⣮⣽⣾⣿⣥⣴⣿⣿⡿⢂⠔⢚⡿⢿⣿⣦⣴⣾⠁⠸⣼⡿ \n" +
                "⠀⢀⡞⠁⠙⠻⠿⠟⠉⠀⠛⢹⣿⣿⣿⣿⣿⣌⢤⣼⣿⣾⣿⡟⠉⠀⠀⠀⠀⠀ \n" +
                "⠀⣾⣷⣶⠇⠀⠀⣤⣄⣀⡀⠈⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀ \n" +
                "⠀⠉⠈⠉⠀⠀⢦⡈⢻⣿⣿⣿⣶⣶⣶⣶⣤⣽⡹⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀ \n" +
                "⠀⠀⠀⠀⠀⠀⠀⠉⠲⣽⡻⢿⣿⣿⣿⣿⣿⣿⣷⣜⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀ \n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣷⣶⣮⣭⣽⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀ \n" +
                "⠀⠀⠀⠀⠀⠀⣀⣀⣈⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠇⠀⠀⠀⠀⠀⠀⠀ \n" +
                "⠀⠀⠀⠀⠀⠀⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀ \n" +
                "⠀⠀⠀⠀⠀⠀⠀⠹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀ \n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠛⠻⠿⠿⠿⠿⠛⠉";
        String greet = " Oh! Hello there! I'm Shrek" + NEW_LINE + " What can I do for you?";
        System.out.print(LINE);
        System.out.println(ANSI_GREEN + logo + NEW_LINE);
        System.out.println(shrekLogo + NEW_LINE + ANSI_RESET);
        System.out.println(LINE + greet);
    }

    public static void printGoodbye() {
        String bye = " Bye bye! See you later!";
        System.out.print(LINE + bye + NEW_LINE + LINE);
    }

    public static void printList(ArrayList<UserContent> listToPrint, String messageToPrint) {
        System.out.println(messageToPrint);
        int indexOfList = 1;
        for (UserContent i : listToPrint) {
            System.out.println(indexOfList + ". " + i);
            indexOfList++;
        }
    }

    public static boolean isCommandInList(String input) {
        for (String str : listOfCommands) {
            if (str.equals(input)) {
                return true;
            }
        }
        return false;
    }

    public static String modifyDatetime(String inputTime) throws InvalidCommandException {
        String[] timeAndDate = inputTime.split(" ");
        String refinedDatetimeFormat = "";
        LocalDate date;
        try {
            date = LocalDate.parse(timeAndDate[INDEX_OF_DATE_INPUT]);
            if (date.isBefore(LocalDate.now())) {
                throw new InvalidCommandException("Invalid date! The date is past current date", errorCount);
            }
            refinedDatetimeFormat += date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " ";
        } catch (DateTimeException e) {
            throw new InvalidCommandException("Wrong date format! Remember to input date as yyyy-mm-dd",
                    errorCount);
        }
        try {
            LocalTime time = LocalTime.parse(timeAndDate[INDEX_OF_TIME_INPUT]);
            if (date.isEqual(LocalDate.now()) && time.isBefore(LocalTime.now())) {
                throw new InvalidCommandException("Invalid time! The time is past current time", errorCount);
            }
            refinedDatetimeFormat += time.format(DateTimeFormatter.ofPattern("h:mma"));
        } catch (DateTimeException e) {
            throw new InvalidCommandException("Wrong time format! Remember to input time in 24hr format", errorCount);
        }
        return refinedDatetimeFormat;
    }

    public static void addDeadlineOrEventToList(String input, String taskTimeReference)
            throws InvalidCommandException {
        String[] splitDeadlineOrEventInputs;
        try {
            splitDeadlineOrEventInputs = input.split(taskTimeReference);
            if (splitDeadlineOrEventInputs.length > NUMBER_OF_TERMS_IN_SPLIT) {
                throw new InvalidCommandException("Did you add in more than one \""
                        + taskTimeReference + "\"?", errorCount);
            } else if (splitDeadlineOrEventInputs[INDEX_OF_TASK_CONTENT].equals("") ||
                    splitDeadlineOrEventInputs[INDEX_OF_TASK_NAME].equals("")) {
                throw new InvalidCommandException("Did you forget to add in the time or task?", errorCount);
            }
            String Datetime = modifyDatetime(chunkOfInput[INDEX_OF_TASK_NAME]);
            if (taskTimeReference.equals("/at ")) {
                lists.add(new Events(splitDeadlineOrEventInputs[INDEX_OF_TASK_CONTENT],
                        splitDeadlineOrEventInputs[INDEX_OF_TASK_NAME]));
            } else {
                lists.add(new Deadlines(splitDeadlineOrEventInputs[INDEX_OF_TASK_CONTENT],
                        splitDeadlineOrEventInputs[INDEX_OF_TASK_NAME]));
            }
        } catch (ArrayIndexOutOfBoundsException err) {
            if (!input.contains(taskTimeReference)) {
                throw new InvalidCommandException("Did you forget \"" + taskTimeReference + "\"?", errorCount);
            }
            throw new InvalidCommandException("Did you forget to add in the time or task?", errorCount);
        }
    }

    public static void addToList(String input, String taskName, boolean toPrint) {
        boolean isTaskRanSuccessful = true;
        switch (taskName) {
        case "todo":
            lists.add(new ToDo(input));
            break;
        case "deadline":
            addDeadlineOrEventToList(input, "/by ");
            break;
        case "event":
            addDeadlineOrEventToList(input, "/at ");
            break;
        default:
            System.out.println("Did you type the task properly? Re-enter your task");
            isTaskRanSuccessful = false;
        }
        if (isTaskRanSuccessful) {
            if (toPrint) {
                System.out.println("Done putting this in the list:");
                System.out.println(lists.get(lists.size() + LIST_INDEX_CORRECTION));
                System.out.println("Go do the " + lists.size() + " task(s)!!");
                saveToOutput();
            }

        }
    }

    public static void deleteFromList(String indexOfList) throws InvalidCommandException {
        UserContent taskToBeRemoved;
        try {
            taskToBeRemoved = lists.get(Integer.parseInt(indexOfList) + LIST_INDEX_CORRECTION);
            lists.remove(Integer.parseInt(indexOfList) + LIST_INDEX_CORRECTION);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException("You cannot delete whats not there!", errorCount);
        }
        System.out.println("This task is gone, reduced to ashes:");
        System.out.println(taskToBeRemoved);
        System.out.println(lists.size() + " task(s) remain");
    }

    public static void markTask(String indexOfList) throws InvalidCommandException {
        try {
            if (!lists.get(Integer.parseInt(indexOfList) + LIST_INDEX_CORRECTION).getMark()) {
                lists.get(Integer.parseInt(indexOfList) + LIST_INDEX_CORRECTION).setMark();
                System.out.println("So you've done this task, that's great I guess?");
            } else {
                System.out.println("You have done this task already!");
            }
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Input of mark must be a number!", errorCount);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException("You do not have that many items in the list!", errorCount);
        }
        System.out.println(lists.get(Integer.parseInt(indexOfList) + LIST_INDEX_CORRECTION));
    }

    public static void unmarkTask(String indexOfList) throws InvalidCommandException {
        try {
            if (lists.get(Integer.parseInt(indexOfList) + LIST_INDEX_CORRECTION).getMark()) {
                System.out.println("What do you mean you've undone");
                lists.get(Integer.parseInt(indexOfList) + LIST_INDEX_CORRECTION).setUnmark();
            } else {
                System.out.println("How can you undo something you've never did?");
            }
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Input of unmark must be a number!", errorCount);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException("You do not have that many items in the list!", errorCount);
        }
        System.out.println(lists.get(Integer.parseInt(indexOfList) + LIST_INDEX_CORRECTION));
    }

    public static void takeInput(String userInput, boolean toPrint) throws InvalidCommandException {
        if (toPrint) {
            System.out.print(LINE);
        }
        try {
            String[] splitUserInputs = userInput.split(" ", NUMBER_OF_TERMS_IN_SPLIT);
            if (!isCommandInList(splitUserInputs[INDEX_OF_TASK_COMMAND])) {
                throw new InvalidCommandException("Input a command from the list", errorCount);
            }
            switch (splitUserInputs[INDEX_OF_TASK_COMMAND]) {
            case "list":
                printList(lists, "Go finish these tasks, NOW:");
                break;
            case "save":
                saveToOutput();
                break;
            case "find":
                findTaskOrTime(splitUserInputs[INDEX_OF_TASK_INPUT]);
                break;
            case "unmark":
                unmarkTask(splitUserInputs[INDEX_OF_TASK_INPUT]);
                break;
            case "mark":
                markTask(splitUserInputs[INDEX_OF_TASK_INPUT]);
                break;
            case "todo":
                addToList(splitUserInputs[INDEX_OF_TASK_INPUT], "todo", toPrint);
                break;
            case "deadline":
                addToList(splitUserInputs[INDEX_OF_TASK_INPUT], "deadline", toPrint);
                break;
            case "event":
                addToList(splitUserInputs[INDEX_OF_TASK_INPUT], "event", toPrint);
                break;
            case "delete":
                deleteFromList(splitUserInputs[INDEX_OF_TASK_INPUT]);
                break;
            default:
                if (splitUserInputs.length < NUMBER_OF_TERMS_IN_SPLIT) {
                    throw new InvalidCommandException("Missing input after the command!", errorCount);
                }
                throw new InvalidCommandException("How did you get here?", errorCount);
            }
        } catch (InvalidCommandException e) {
            errorCount++;
        }
        if (toPrint) {
            System.out.print(LINE);
        }
    }

    public static void main(String[] args) {
        initialiseShrek();
        printGreeting();
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        while (!userInput.equals("bye")) {
            takeInput(userInput, true);
            userInput = in.nextLine();
        }
        takeInput("save", true);
        printGoodbye();
    }
}
