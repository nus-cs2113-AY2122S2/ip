package controller;

import java.util.Scanner;

public class UI {
    private static final String INDENT = "    ";
    private static final String LINE="-------------------------------------------";
    public static final String SPACE = " ";
    private static final int MAX_TASK_COUNT = 100;
    private static final String DIR = "data/task-file";
    private static final String FILE_SEPARATOR = " | ";
    private Scanner sc = new Scanner(System.in);
    public UI(){

    }
    public void showWelcome(){
        greet();
    }

    private  void greet(){
        printLine();
        System.out.println(INDENT + "Hi, I am XiaoAi TongXue ;D");
        System.out.println(INDENT + "What can I do for you?");
        printLine();
    }

    private  void printLine(){
        System.out.println(INDENT + LINE);
    }

    public String readCommand(){
        String input = getInput();
        return input;
    }

    private  String getInput(){
        String input = sc.nextLine().trim();
        return input;
    }

    public void showLine(){
        printLine();
    }

    public void showError(String s){
        System.out.println(INDENT + s);
    }

    public void showLoadingError(){
        System.out.println(INDENT + "oops! cannot find file!");
    }

    public void showList() {
        System.out.println(INDENT+"Here are the task(s) in your list:");
    }

    public void showDelete() {
        System.out.println(INDENT+"Noted. I've removed this task:");
    }

    public void showTaskCount(int taskCount){
        System.out.println(INDENT + "Now you have " + taskCount + " task(s) in the list.");
    }

    public void showAdd(){
        System.out.println(INDENT+"Got it. I've added this task:");
 //       System.out.println(INDENT+t);
    }

    public void showErrorTask() {
        System.out.println(INDENT + "OOPS! the task doesn't have description or time :(");
    }

    public void showTask(String t) {
        System.out.println(INDENT + t);
    }

    public void showBye() {
        System.out.println(INDENT + "Bye. Hope to see you again soon!");
        printLine();
    }

    public void showFound() {
        System.out.println(INDENT + "Here are the matching tasks in your list:");
    }

    public void showNotFound() {
        System.out.println(INDENT + "Opps, no matching tasks found in your list :(");
    }

    public void showEmptyList() {
        System.out.println(INDENT + "There are nothing in the task list now");
    }
}

