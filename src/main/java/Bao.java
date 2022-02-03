import Components.Task;
import Components.Todo;
import Components.Deadline;
import Components.Event;

import java.util.Scanner;

public class Bao {
    private static Scanner in = new Scanner(System.in);
    private static Task[] tasks = new Task[100];
    private static int numTasks=0;

    private static void greet(){
        System.out.println("______________________________________________________________________________________");
        System.out.println("Hello there! Bao here!");
        System.out.println("How can I help?");
        System.out.println("______________________________________________________________________________________");
    }

    private static void addTask(Task task){
        System.out.println("______________________________________________________________________________________");
        tasks[numTasks++]=task;
        System.out.println("Yup yup yup, " + System.lineSeparator() +
                task.toString() + "," + System.lineSeparator() +
                "annnd there we go, it's been added!" + System.lineSeparator() +
                "You have " + numTasks + " tasks in the list.");
        System.out.println("______________________________________________________________________________________");
    }

    private static void addToDo(String msg){
        String description;
        final int TODO_LENGTH = "todo".length();
        description = msg.substring(TODO_LENGTH).trim();
        addTask(new Todo(description));
    }

    private static void addDeadline(String msg){
        String description, dateTime;
        final int DEADLINE_LENGTH = "deadline".length();
        description = msg.substring(DEADLINE_LENGTH,msg.indexOf("/by")).trim();
        dateTime = msg.substring(msg.indexOf("/by")+"/by".length()).trim();
        addTask(new Deadline(description,dateTime));
    }

    private static void addEvent(String msg){
        String description, dateTime;
        final int EVENT_LENGTH = "event".length();
        description = msg.substring(EVENT_LENGTH,msg.indexOf("/at")).trim();
        dateTime = msg.substring(msg.indexOf("/at")+"/at".length()).trim();
        addTask(new Event(description,dateTime));
    }

    private static void markTask(String msg){
        System.out.println("______________________________________________________________________________________");
        int ind = Integer.parseInt(msg.substring(msg.indexOf(' ')+1));
        tasks[--ind].setIsDone(true);
        System.out.println(tasks[ind].toString());
        System.out.println("Boom! Another task done already???");
        System.out.println("______________________________________________________________________________________");
    }

    private static void unmarkTask(String msg){
        System.out.println("______________________________________________________________________________________");
        int ind = Integer.parseInt(msg.substring(msg.indexOf(' ')+1));
        tasks[--ind].setIsDone(false);
        System.out.println(tasks[ind].toString());
        System.out.println("Oh oops, overlooked that one did we?");
        System.out.println("______________________________________________________________________________________");
    }

    private static void listTasks(){
        int i=0;
        System.out.println("______________________________________________________________________________________");
        System.out.println("Here you go:");
        while(i<numTasks){
            System.out.println(i+1 + ". " + tasks[i++].toString());
        }
        System.out.println("______________________________________________________________________________________");
    }

    private static void farewell(){
        System.out.println("______________________________________________________________________________________");
        System.out.println("Alright, goodbye. See you later alligator!");
        System.out.println("______________________________________________________________________________________");
    }

    public static void main(String[] args) {
        String userInput;
        String logo = "  ____       _       ___  " + System.lineSeparator() +
                      " | __ )     / \\     / _ \\" + System.lineSeparator() +
                      " |  _ \\    / _ \\   | | | |" + System.lineSeparator() +
                      " | |_) |  / ___ \\  | |_| |" + System.lineSeparator() +
                      " |____/  /_/   \\_\\  \\___/";

        System.out.println("Hello from" + System.lineSeparator() + logo);
        greet();
        userInput = in.nextLine();
        while(!userInput.equals("bye")){
            if(userInput.equals("list")){
                listTasks();
            } else if(userInput.startsWith("mark")){
                markTask(userInput);
            } else if(userInput.startsWith("unmark")){
                unmarkTask(userInput);
            } else if(userInput.startsWith("todo")){
                addToDo(userInput);
            } else if(userInput.startsWith("deadline")){
                addDeadline(userInput);
            } else if(userInput.startsWith("event")){
                addEvent(userInput);
            }
            userInput = in.nextLine();
        }
        farewell();
    }
}
