import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    private final Scanner in;
    private final PrintStream out;

    /** Offset required to convert between 1-indexing and 0-indexing.  */
    public static final int DISPLAYED_INDEX_OFFSET = 1;

    /** A platform independent line separator. */
    private static final String LS = System.lineSeparator();

    public UI(){
        this.in = new Scanner(System.in);
        this.out = new PrintStream(System.out);
    }

    public void showWelcomeMsg(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        out.print("Hello from" + LS + logo + LS +
                "____________________" + LS +
                "Hello! I'm Duke" + LS +
                "What can I do for you?" + LS);
    }

    public void showGoodbyeMsg(){
        out.print("____________________" + LS +
                "Bye. Hope to see you again soon!" + LS +
                "____________________");
    }

    public String getUserCommand(){
        String rawInput = in.nextLine().trim();
        return rawInput;
    }

    public void showFileFailedToOpenMsg(){
        out.print("// Warning: File could not be opened" + LS);
    }

    public void showFileFailedToWriteMsg(){
        out.print("// Warning: Could not write to file" + LS);
    }

    public void showFileSaveSuccessfulMsg(){
        out.print("Saved successfully!" + LS);
    }

    public void showDecodeErrorMsg(){
        out.print("// Warning: Line in text file cannot be decoded and was skipped" + LS);
    }

    public void showIncorrectFormatMsg(){
        out.print("Incorrect formatting. Please try again" + LS);
    }

    public void showNonexistentTaskMsg(){
        out.print("This task does not exist. Please try again" + LS);
    }

    public void showSuccessfulDelete(Task removedTask, int size){
        out.printf("I've deleted this task:" + LS +
                "\t%s" + LS +
                "Now you have %d in the list." + LS, removedTask, size);
    }

    public void showInvalidCommandMsg(){
        out.print("Not a valid command" + LS);
    }

    public void showSuccessfulTaskAdded(Task newTask){
        out.printf("Got it. I've added this task:" + LS +
                "\t%s" + LS, newTask);
    }

    public void showSuccessfulMark(Task task){
        out.printf("Nice! I've marked this task as done: %s" + LS, task);
    }

    public void showSuccessfulUnmark(Task task){
        out.printf("OK, I've marked this task as not done yet: %s" + LS, task);
    }

    public void showAllTasks(TaskList taskList){
        out.print("Here are the tasks in your list:" + LS);
        ArrayList<Task> list = taskList.getTasks();
        for(int i = 0; i< list.size(); i++){
            out.printf("%d. %s" + LS, i + DISPLAYED_INDEX_OFFSET, list.get(i));
        }
    }
}
