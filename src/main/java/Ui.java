import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ui {

    /**
     * Shows a welcome message.
     *
     */
    public void showWelcome(){
        System.out.println("Hello! I'm KaiKai.");
        System.out.println("What can I do for you?");
        System.out.println("______________________________________");
    }

    /**
     * Shows a bye message when user leaves the app.
     *
     */
    public void showBye(){
        System.out.println("Bye! Hope to see you again!");
    }

    /**
     * Shows an error message.
     *
     */
    public void showUnknownCommand(){
        System.out.println("Sorry, I don't recognise that command. Please try again!");
        System.out.println("______________________________________");
    }

    /**
     * Shows an error message for incorrect date input.
     *
     */
    public void showUnknownDate(){
        System.out.println("Sorry, please enter the date in the correct format!");
        System.out.println("______________________________________");
    }

    public void showLineBreak(){
        System.out.println("______________________________________");
    }

    /**
     * Shows options for adding tasks.
     *
     */
    public void showAddOptions(){
        System.out.println("Okie, what type of task is this?");
        System.out.println("1.Todo");
        System.out.println("2.Deadline");
        System.out.println("3.Event");
    }

    /**
     * Takes in user input with scanner
     *
     * @return input String
     */
    public String getInput(){
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Takes in user input for date with scanner
     *
     * @return input Date
     */
    public Date getDate(){
        Scanner sc = new Scanner(System.in);
        String date = sc.nextLine();
        SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try{
            Date date1 = formatter1.parse(date);
            return date1;
        } catch (Exception e){
            showUnknownDate();
        }
        return null;
    }
}
