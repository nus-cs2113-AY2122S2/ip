import java.util.Scanner;

public class Ui {

    public void showWelcome(){
        System.out.println("Hello! I'm KaiKai.");
        System.out.println("What can I do for you?");
        System.out.println("______________________________________");
    }

    public void showBye(){
        System.out.println("Bye! Hope to see you again!");
    }

    public void showUnknownCommand(){
        System.out.println("Sorry, I don't recognise that command. Please try again!");
        System.out.println("______________________________________");
    }

    public void showLineBreak(){
        System.out.println("______________________________________");
    }

    public void showAddOptions(){
        System.out.println("Okie, what type of task is this?");
        System.out.println("1.Todo");
        System.out.println("2.Deadline");
        System.out.println("3.Event");
    }

    public String getInput(){
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
