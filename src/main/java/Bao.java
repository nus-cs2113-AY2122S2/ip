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

    private static void echo(String msg){
        System.out.println("______________________________________________________________________________________");
        System.out.println(msg);
        System.out.println("______________________________________________________________________________________");
    }

    private static void addTask(String msg){
        System.out.println("______________________________________________________________________________________");
        tasks[numTasks++]=new Task(msg);
        System.out.println("Yup yup yup, " + msg + ", annnd there we go, it's been added!");
        System.out.println("______________________________________________________________________________________");
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
        int x=0;
        System.out.println("______________________________________________________________________________________");
        System.out.println("Here you go:");
        while(x<numTasks){
            System.out.println(x+1 + ". " + tasks[x++].toString());
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
        String logo = "  ____       _       ___  \n" +
                      " | __ )     / \\     / _ \\ \n" +
                      " |  _ \\    / _ \\   | | | | \n" +
                      " | |_) |  / ___ \\  | |_| | \n" +
                      " |____/  /_/   \\_\\  \\___/";

        System.out.println("Hello from\n" + logo);
        greet();
        userInput = in.nextLine();
        while(!userInput.equals("bye")){
            if(userInput.equals("list")){
                listTasks();
            } else if(userInput.startsWith("mark")){
                markTask(userInput);
            } else if(userInput.startsWith("unmark")){
                unmarkTask(userInput);
            } else {
                addTask(userInput);
            }
            userInput = in.nextLine();
        }
        farewell();
    }
}
