package duke;

import duke.task.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class Duke {

    static Task[] taskList = new Task[100];
    static int taskCount = 0;
    static boolean isModified = false;      // isModified refers to whether taskList is modified or not.

    // method prints a horizontal line.
    public static void displayLine(){
        System.out.println("____________________________________________________________");
    }

    // method prints Duke greeting.
    public static void greeting(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("You are entering the\n" + logo + "\nZone...\n");

        displayLine();
        System.out.println("Hey there! Duke here!");
        System.out.println("How can I serve you today?");
        displayLine();
    }

    // method prints Duke termination.
    public static void goodbye(){
        System.out.println("Goodbye. See you in the funny papers.");
        displayLine();
    }


    // method prints taskList.
    public static void printList(){
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++){
            System.out.print(String.format("%d. ", i + 1));
            System.out.println(taskList[i]);
        }
        displayLine();
    }

    public static boolean isDeadline(String userInput){
        if(userInput.length()>=8){
            // return true if first 8 letters of userInput spell "deadline", else false
            return userInput.substring(0,8).equals("deadline");
        }
        return false;
    }

    public static boolean isTodo(String userInput){
        if(userInput.length()>=4){
            // return true if first 4 letters of userInput spell "todo", else false
            return userInput.substring(0,4).equals("todo");
        }
        return false;
    }

    public static boolean isEvent(String userInput){
        if(userInput.length()>=5){
            // return true if first 5 letters of userInput spell "event", else false
            return userInput.substring(0,5).equals("event");
        }
        return false;
    }

    public static String getTodoFromUserInput(String userInput){
        return userInput.substring(5,userInput.length());
    }

    public static String getEventFromUserInput(String userInput, int eventTimeIdx){
        return userInput.substring(6,eventTimeIdx-1);
    }

    public static String getDeadlineFromUserInput(String userInput, int dueDateIdx){
        return userInput.substring(9,dueDateIdx-1);
    }

    public static String getEventTimeFromUserInput(String userInput, int eventTimeIdx){
        // ignore /by in the userInput
        return userInput.substring(eventTimeIdx+4,userInput.length());
    }

    public static String getDueDateFromUserInput(String userInput, int dueDateIdx){
        // ignore /at in the userInput
        return userInput.substring(dueDateIdx+4,userInput.length());
    }

    private static void printAddedTask() {
        System.out.println("Noted. I've added:");
        System.out.println(taskList[taskCount-1]);
        System.out.println("Now you have "+Integer.toString(taskCount)+" tasks in the list.");
        displayLine();
    }

    private static void printDeletedTask(int taskNumber) {
        System.out.println("Noted. I've removed:");
        System.out.println(taskList[taskNumber-1]);
        System.out.println("Now you have "+Integer.toString(taskCount-1)+" tasks in the list.");
        displayLine();
    }

    public static void addTodoToList(String todoDescription){
        //create a new Todo object.
        Todo newTodo = new Todo(todoDescription);
        //add the new object to taskList.
        taskList[taskCount] = newTodo;
        //keep track of the number of tasks in taskList.
        taskCount++;

        printAddedTask();
    }

    public static void addTodoToList(String todoDescription,boolean isUserMode){
        //create a new Todo object.
        Todo newTodo = new Todo(todoDescription);
        //add the new object to taskList.
        taskList[taskCount] = newTodo;
        //keep track of the number of tasks in taskList.
        taskCount++;
    }

    public static void addEventToList(String eventDescription, String eventTime){
        // create a new Event object.
        Event newEvent = new Event(eventDescription, eventTime);
        // add the new object to taskList.
        taskList[taskCount] = newEvent;
        // keep track of the number of tasks in taskList.
        taskCount++;

        printAddedTask();
    }

    public static void addEventToList(String eventDescription, String eventTime, boolean isUserMode){
        // create a new Event object.
        Event newEvent = new Event(eventDescription, eventTime);
        // add the new object to taskList.
        taskList[taskCount] = newEvent;
        // keep track of the number of tasks in taskList.
        taskCount++;
    }

    public static void addDeadlineToList(String deadlineDescription, String dueDate){
        // create a new Deadline object.
        Deadline newDeadline = new Deadline(deadlineDescription, dueDate);
        // add the new object to taskList.
        taskList[taskCount] = newDeadline;
        // keep track of the number of tasks in taskList.
        taskCount++;

        printAddedTask();
    }

    public static void addDeadlineToList(String deadlineDescription, String dueDate, boolean isUserMode){
        // create a new Deadline object.
        Deadline newDeadline = new Deadline(deadlineDescription, dueDate);
        // add the new object to taskList.
        taskList[taskCount] = newDeadline;
        // keep track of the number of tasks in taskList.
        taskCount++;
    }

    public static boolean isInvalidTodo(String todo){
        int lengthOfTodoDescription = todo.length() - 5;
        return (lengthOfTodoDescription<=0);
    }

    public static boolean isInvalidEvent(String event){
        int lengthOfEventDescription = event.length() - 6;
        if (lengthOfEventDescription<=0){
            return true;
        }
        // invalid format
        if(event.indexOf("/")==-1){
            return true;
        }

        String[] eventTimeSplit = event.substring(event.indexOf("/"),event.length()).split(" ");

        // invalid format or event time is not specified.
        if(eventTimeSplit.length<2 || !(eventTimeSplit[0].equals("/at"))){
            return true;
        }
        return false;
    }

    public static boolean isInvalidDeadline(String deadline){
        int lengthOfDeadlineDescription = deadline.length() - 9;
        if (lengthOfDeadlineDescription<=0){
            return true;
        }
        // invalid format
        if(deadline.indexOf("/")==-1){
            return true;
        }

        String[] dueDateSplit = deadline.substring(deadline.indexOf("/"),deadline.length()).split(" ");

        // invalid format or due date is not specified.
        if(dueDateSplit.length<2||!(dueDateSplit[0].equals("/by"))){
            return true;
        }
        return false;
    }

    // method adds task to task_list.
    public static void addTaskToList(String userInput){
        if(isTodo(userInput)){
            if (isInvalidTodo(userInput)){
                System.out.println("OOPS! The description for todo cannot be empty.");
                displayLine();
                return;
            }

            String todoDescription = getTodoFromUserInput(userInput);

            // add Todo task to List
            addTodoToList(todoDescription);
        }
        else if(isEvent(userInput)){
            if(isInvalidEvent(userInput)){
                System.out.println("OOPS! Either the description or event time or both are empty for this event. Please try again.");
                displayLine();
                return;
            }

            int eventTimeIdx = userInput.indexOf("/");
            String eventDescription = getEventFromUserInput(userInput,eventTimeIdx);
            String eventTime = getEventTimeFromUserInput(userInput,eventTimeIdx);

            // add Event task to List
            addEventToList(eventDescription,eventTime);
        }
        else if(isDeadline(userInput)){
            if(isInvalidDeadline(userInput)){
                System.out.println("OOPS! Either the description or due date or both are empty for this deadline. Please try again.");
                displayLine();
                return;
            }

            int dueDateIdx = userInput.indexOf("/");
            String deadlineDescription = getDeadlineFromUserInput(userInput,dueDateIdx);
            String dueDate = getDueDateFromUserInput(userInput,dueDateIdx);

            // add Deadline task to List
            addDeadlineToList(deadlineDescription,dueDate);
        }

        // If not a recognizable command, inform user
        else{
            System.out.println("OOPS! I'm sorry but I don't know what you mean :(");
            return;
        }
        isModified = true;
    }

    //method marks task in list with taskNumber as done.
    public static void markTaskAsDone(int taskNumber){
        if(taskNumber == -1){
            System.out.println("Please provide a valid task number for me to mark as done.");
            return;
        }
        // mark task with taskNumber as done.
        taskList[taskNumber-1].markAsDone();
        System.out.println("Great Job! I've marked the following task as done:");
        // display updated task entry in list.
        System.out.println(taskList[taskNumber-1]);
        displayLine();
        isModified = true;
    }

    //method marks task in list with taskNumber as not yet done.
    public static void unmarkTaskAsDone(int taskNumber){
        if(taskNumber == -1){
            System.out.println("Please provide a valid task number for me to mark as not yet done.");
            return;
        }
        // mark task with taskNumber as yet to be done.
        taskList[taskNumber-1].unmarkAsDone();
        System.out.println("Ok, I've marked the following task as yet to be done:");
        // display updated task entry in list.
        System.out.println(taskList[taskNumber-1]);
        displayLine();
        isModified = true;



    }

    public static void deleteTask(int taskNumber){
        if(taskNumber == -1){
            System.out.println("Please provide a valid task number for me to delete.");
            return;
        }
        printDeletedTask(taskNumber);
        while(taskNumber<taskCount){
            taskList[taskNumber-1] = taskList[taskNumber];
            taskNumber++;
        }
        taskCount--;
        isModified = true;
    }

    public static boolean isMarkCommand(String userInput){
        if(userInput.length()>=4){
            // return true if first 4 letters of userInput spell "mark", else false
            return userInput.substring(0,4).equals("mark");
        }
        return false;
    }

    public static boolean isUnmarkCommand(String userInput){
        if(userInput.length()>=6){
            // return true if first 6 letters of userInput spell "unmark", else false
            return userInput.substring(0,6).equals("unmark");
        }
        return false;
    }

    public static boolean isListCommand(String userInput){
        // return true if command is list, else false
        return userInput.equals("list");
    }

    public static boolean isDeleteCommand(String userInput){
        // return true if command is list, else false
        if(userInput.length()>=6) {
            return userInput.substring(0,6).equals("delete");
        }
        return false;
    }

    public static boolean isByeCommand(String userInput){
        // return true if command is list, else false
        return userInput.equals("bye");
    }

    // method returns task number else -1 if any errors encountered.
    public static int getTaskNumber(String userInput){
        String[] splitInput=userInput.split(" ");
        if(splitInput.length<=1){
            return -1;
        }
        try {
            return Integer.parseInt(splitInput[1]);
        } catch (NumberFormatException e){
            return -1;
        }
    }

    public static String getUserInput() {
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        displayLine();
        return userInput;
    }

    public static void createDirectory(String directory){
        String projectPath = System.getProperty("user.dir");
        String directoryPath = projectPath + directory;
        //System.out.println(directoryPath);
        File dir = new File(directoryPath);
        boolean isMade = dir.mkdir();
        if(!isMade){
            System.out.println("Error making directory \"data\".");
        }
    }

    public static void createFile(String file){
        String projectPath = System.getProperty("user.dir");
        System.out.println(projectPath);
        String directory = "\\data";
        File dir = new File("data");
        while(!dir.exists()){
            createDirectory(directory);
        }
        File f = new File(projectPath+file);

        try {
            f.createNewFile();
        } catch (IOException e){
            System.out.println("An error occurred making file.");
        }
    }

    public static String getTaskInFileFormat(int listIndex){
        Task task = taskList[listIndex];
        if(task.getTaskTypeSymbol()=="T") {
            return task.getTaskTypeSymbol() + "/" + task.isDone() + "/" + task.getDescription();
        }
        DynamicTask dTask = (DynamicTask) task;
        return dTask.getTaskTypeSymbol() + "/" + dTask.isDone() + "/" + dTask.getDescription()
                + "/" + dTask.getTime();
    }

    public static void writeToFile() throws IOException{
        FileWriter fWrite = new FileWriter("data/duke.txt");
        fWrite.write(getTaskInFileFormat(0)+"\n");
        fWrite.close();
        fWrite = new FileWriter("data/duke.txt",true);
        for(int i = 1; i < taskCount; i++){
            fWrite.write(getTaskInFileFormat(i)+"\n");
        }
        fWrite.close();
    }

    public static void saveData(){
        String file = "data\\duke.txt";
        File f = new File("data/duke.txt");
        while(!f.exists()){
            createFile(file);
        }
        try {
            writeToFile();
        } catch (IOException e){
            System.out.println("Something went wrong writing to file.");
        }
    }



    public static void transferDataFromFileToList() throws FileNotFoundException {
        File f = new File("data/duke.txt");
        Scanner fileReader = new Scanner(f);
        while(fileReader.hasNext()) {
            String fileRow = fileReader.nextLine();
            String[] taskArguments = fileRow.split("/",5);
            if(taskArguments[0].equals("T")){
                addTodoToList(taskArguments[2],false);
            }
            else if(taskArguments[0].equals("D")){
                addDeadlineToList(taskArguments[2],taskArguments[3],false);
            }
            else if(taskArguments[0].equals("E")){
                addEventToList(taskArguments[2],taskArguments[3],false);
            }
            else{
                System.out.println("Corrupted entry detected in file.");
                continue;
            }

            if(taskArguments[1].equals("true")){
                taskList[taskCount-1].markAsDone();
            }
        }
        System.out.println("Now you have "+Integer.toString(taskCount)+" tasks in the list.");
        displayLine();
    }

    public static void loadData() {
        String file = "data\\duke.txt";
        File f = new File("data/duke.txt");
        try{
            transferDataFromFileToList();
        } catch (FileNotFoundException e){
            while(!f.exists()) {
                createFile(file);
            }
        }
    }

    // method runs main echo functionality of duke.
    public static void echo(){
        while(true){
            isModified = false;
            //read input from user.
            String userInput = getUserInput();

            // Check userInput for respective command.

            // Exit if "bye" is entered by user.
            if(isByeCommand(userInput)) {
                return;
            }
            else if(isListCommand(userInput)){
                printList();
            }
            else if (isMarkCommand(userInput)){
                int taskNumber=getTaskNumber(userInput);
                markTaskAsDone(taskNumber);
            }
            else if (isUnmarkCommand(userInput)){
                int taskNumber=getTaskNumber(userInput);
                unmarkTaskAsDone(taskNumber);
            }
            else if (isDeleteCommand(userInput)){
                int taskNumber=getTaskNumber(userInput);
                deleteTask(taskNumber);
            }
            // else it is an addition command or some unknown command
            else{
                addTaskToList(userInput);
            }

            if(isModified){
                saveData();
            }
        }
    }

    public static void main(String[] args) {
        // opening sequence.
        greeting();

        // if data exists, load.
        loadData();

        // echo loop between user and Dukebot.
        echo();

        // ending sequence.
        goodbye();
    }
}
