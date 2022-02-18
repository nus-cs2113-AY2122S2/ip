package controller;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.*;
import java.util.*;

public class TaskManager {
    private static final String INDENT = "    ";
    private static final String LINE="-------------------------------------------";
    public static final String SPACE = " ";
    private static final int MAX_TASK_COUNT = 100;
    private static final String DIR = "data/task-file";
    private static final String FILE_SEPARATOR = " | ";


    private static int taskCount=0;
    private static Task[] taskList = new Task[MAX_TASK_COUNT] ;
    private static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {
        greet();
        String input = getInput();
        String action = getAction(input);
        printLine();
        
        while(!action.equals("bye")){
            switch(action){
            case "list":
                listTask();
                break;
            case "mark":
                updateTask(Integer.parseInt(input.split(SPACE)[1])-1,true);
                break;
            case "unmark":
                updateTask(Integer.parseInt(input.split(SPACE)[1])-1,false);
                break;
            case "todo":
            case "deadline":
            case "event":
                addTaskByMessage(action, input);
                break;
            default:
                printErrorInputMessage();
            }
            printLine();
            input = getInput();
            action = getAction(input);
            printLine();
        }
        bye();
    }

    private static void bye() {
        save(DIR, new ArrayList<>(Arrays.asList(taskList)));
        System.out.println(INDENT + "Bye. Hope to see you again soon!");
        printLine();
    }

    private static void listTask() {
        System.out.println(INDENT+"Here are the tasks in your list:");
        for(int i =0 ; i<taskCount; i++){
            System.out.println(INDENT+(i+1) + "." + taskList[i]);
        }
    }

    private static void printLine(){
        System.out.println(INDENT + LINE);
    }

    public static void printErrorInputMessage(){
        System.out.println(INDENT+"OOPS!! I'm sorry, but i don;t know what that means :(");
    }

    public static void addTask(Task t){
        taskList[taskCount] = t;
        System.out.println(INDENT+"Got it. I've added this task:");
        System.out.println(INDENT+t);
        taskCount++;
        System.out.println(INDENT+"Now you have " + taskCount + " tasks in the list.");

    }

    private static void greet(){
        printLine();
        System.out.println(INDENT + "Hi, I am XiaoAi TongXue ;D");
        printLine();
    }
    
    private static String getInput(){
        String input = sc.nextLine().trim();
        return input;
    }
    
    private static String getAction(String input){
        String action = input.split(SPACE)[0].toLowerCase();
        return action;
    }
    
    private static void updateTask(int idx, boolean isMark){
        if(isMark){
            System.out.println(INDENT+"Nice! I have marked this task as done:");
            taskList[idx].markAsDone();
        }else{
            System.out.println(INDENT+"Nice! I have marked this task as done:");
            taskList[idx].markAsUndone();
        }
        printTask(taskList[idx]);
    }
    
    private static void printTask(Task task){
        System.out.println(INDENT + task);
    }
    
    private static void addTaskByMessage(String action, String input){
        String[] inputWord = input.split(SPACE);
        if(inputWord.length == 1){
            System.out.println(INDENT + "OOPS! The task doesn't have description :(");    // check description
            return;
        }
        int desIdx = input.indexOf(" ")+1;
        Task newTask = null;
        switch(action){
        case "todo":
            String description = input.substring(desIdx);
            newTask = new Todo(description);
            break;
        case "deadline":
        case "event":
            if(!input.contains("/")){
                System.out.println(INDENT + "OOPS! the task doesn't have time :(");       // check time
                return;
            }

            int timeIdx = input.indexOf("/")+4;
            int desEndIdx = input.indexOf("/")-1;
            description = input.substring(desIdx,desEndIdx);
            String time = input.substring(timeIdx);
            if(action.equals("deadline")){
                newTask = new Deadline(description,time);
            }else{
                newTask = new Event(description,time);
            }
            break;
        }
        addTask(newTask);
    }

//    private static void retrieveData() {
//        File f = new File(DIR);
//        if(f.exists()){
//            taskList = load(f);
//        }else{
//            f.getParentFile().mkdir();
//            f.createNewFile();
//            taskList = new ArrayList<Task>();
//            save(DIR, taskList);
//        }
//    }

    private static void save(String filename, ArrayList<Task> al){
        StringBuilder sb = new StringBuilder();
        List newList = new ArrayList();
        for(int i = 0; i < al.size(); i++){
            Task task = al.get(i);
            if(task == null) break;
            String taskType = task.getTypeIcon();
            String taskStatus = task.getStatusIcon();
            String taskDetails = task.getDescription();
            sb.append(taskType);
            sb.append(FILE_SEPARATOR);
            sb.append(taskStatus);
            sb.append(FILE_SEPARATOR);
            sb.append(taskDetails);
            if(taskType.equals("D") || taskType.equals("E")){
                sb.append(FILE_SEPARATOR);
                sb.append(task.getTime());
            }
            sb.append(System.lineSeparator());
        }
        try {
            writeToFile(filename, sb.toString());
        } catch (IOException e) {
            System.out.println("Something went wrong:" + e.getMessage());
        }
    }

    public static void writeToFile(String fileName, String data) throws IOException {
        FileWriter fw = new FileWriter(fileName);
        fw.write(data);
        fw.close();
    }


//
//    public static List read(String filename) throws IOException{
//        File f = new File(filename);
//        Scanner s = new Scanner(f);
//        while(s.hasNext()) {
//            String newLine = s.nextLine();
//            Task newTask;
//            StringTokenizer st = new StringTokenizer(newLine, FILE_SEPARATOR);
//
//            String taskType = st.nextToken();
//            String taskStatus = st.nextToken();
//            String description = st.nextToken();
//            if(taskType.equals("D")) {
//                String time = st.nextToken();
//                newTask = new Deadline(description,time);
//                newTask.
//            }
//
//
//
//        }
//        try{
//            List data = new ArrayList();
//            Scanner scanner = new Scanner(new FileInputStream(filename));
//            try {
//                while (scanner.hasNextLine()) {
//                    data.add(scanner.nextLine());
//                }
//            } finally {
//                scanner.close();
//            }
//            return data;
//        }catch(IOException e){
//            System.out.println("reading file unsuccessfully");
//            e.printStackTrace();
//            return null;
//        }
//    }




//    try {
//        File file = new File(dir);
//        if (file.exists()) {
//            orders = load(dir);
//        } else {
//            file.getParentFile().mkdir();
//            file.createNewFile();
//            orders = new ArrayList<Order>();
//            save(dir, orders);
//        }
//    } catch (IOException e) {
//        e.printStackTrace();
//    }
}


//
//    /**
//     * This method is to load orders from external files
//     * @param filename
//     *            specifies where the external files stored
//     * @return all reservations read from the file
//     */
//    @Override
//    public ArrayList load(String filename) {
//        ArrayList stringArray = (ArrayList) read(filename);
//        ArrayList alr = new ArrayList();
//
//        for (int i = 0; i < stringArray.size(); i++) {
//            String st = (String) stringArray.get(i);
//            StringTokenizer star = new StringTokenizer(st, "|");
//
//            int orderId = Integer.parseInt(star.nextToken().trim());
//            int staffId = Integer.parseInt(star.nextToken().trim());
//            int tableId = Integer.parseInt(star.nextToken().trim());
//            int numberOfPax = Integer.parseInt(star.nextToken().trim());
//            int orderSize = Integer.parseInt(star.nextToken().trim());  // write the orderSize in the file in order to read different size of order items
//            boolean isActive = Boolean.parseBoolean(star.nextToken().trim());
//
//            //create order with no order item
//            Order order = new Order(orderId, staffId, tableId, numberOfPax, isActive);
//            //add order item in order
//            for (int j = 0; j < orderSize; j++) {
//                int itemId = Integer.parseInt(star.nextToken().trim());
//                String name = star.nextToken().trim();
//                int quantity = Integer.parseInt(star.nextToken().trim());
//                double price = Double.parseDouble(star.nextToken().trim());
//                order.addOrderItem(itemId, quantity, name, price);
//            }
//            //add order to order list
//            alr.add(order);
//        }
//        return alr;
//
//    }
    

//
//public class Cup<T>{
//    private T item;
//    public void set(T item){
//        this.item = item;
//    }
//    public T get(){
//        return item;
//    }
//}