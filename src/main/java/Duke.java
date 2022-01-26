import java.util.Scanner;

public class Duke {

    public static String INDENT_LINE = "____________________________________________________________";
    public static String GREET_STRING = "     Hello! I'm Duke\n" +
            "     What can I do for you?";
    public static String BYE_STRING = "     Bye. Hope to see you again soon!";

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        TaskList taskList = new TaskList();

        String input = "";

        printIndentLine();
        System.out.println(GREET_STRING);
        printIndentLine();

        input = s.nextLine();
        while (true){
            if(input.equals("bye")){
                break;
            } else if (input.equals("list")){
                printIndentLine();
                taskList.displayTasks();
                printIndentLine();
            } else {
                printIndentLine();
                System.out.println("    " + taskList.addTask(input));
                printIndentLine();
            }
            input = s.nextLine();
        }

        printIndentLine();
        System.out.println(BYE_STRING);
        printIndentLine();
    }

    public static void printIndentLine(){
        System.out.println(INDENT_LINE);
    }

}
