import java.util.Scanner;

public class Bao {
    private static Scanner in = new Scanner(System.in);
    private static String[] lines = new String[100];
    private static int numLines=0;

    private static void greet(){
        System.out.println("__________________________________________________");
        System.out.println("Hello there! Bao here!");
        System.out.println("How can I help?");
        System.out.println("__________________________________________________");
    }

    private static void echo(String msg){
        System.out.println("__________________________________________________");
        System.out.println(msg);
        System.out.println("__________________________________________________");
    }

    private static void addLine(String msg){
        System.out.println("__________________________________________________");
        lines[numLines++]=msg;
        System.out.println("Yup yup yup, " + msg + ", annnd there we go, it's been added!");
        System.out.println("__________________________________________________");
    }

    private static void listLines(){
        int x=0;
        System.out.println("__________________________________________________");
        System.out.println("Here you go:");
        while(x<numLines){
            System.out.println(x+1 + ". " + lines[x++]);
        }
        System.out.println("__________________________________________________");
    }

    private static void farewell(){
        System.out.println("__________________________________________________");
        System.out.println("Alright, goodbye. See you later alligator!");
        System.out.println("__________________________________________________");
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
                listLines();
                userInput=in.nextLine();
                continue;
            }
            addLine(userInput);
            userInput = in.nextLine();

        }
        farewell();
    }
}
