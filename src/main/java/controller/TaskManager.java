package controller;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.util.Scanner;

public class TaskManager {
    private static final String INDENT = "    ";
    private static final String LINE="-------------------------------------------";
    public static final String SPACE = " ";
    private static final int MAX_TASK_COUNT = 100;


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
    
}
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