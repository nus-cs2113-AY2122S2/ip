import java.util.Scanner;

public class Duke {

    public static String INDENT_LINE = "____________________________________________________________";
    public static String GREET_STRING = "     Hello! I'm Duke" + System.lineSeparator() +
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
            } else if (input.contains("unmark")){
                printIndentLine();
                // Uses Regex to replace all instances of non digit characters to be parsed as int
                int index = Integer.parseInt(input.replaceAll("\\D+","")) - 1;
                taskList.setTaskStatus(index, false);
                printIndentLine();
            } else if (input.contains("mark")) {
                printIndentLine();
                // Uses Regex to replace all instances of non digit characters to be parsed as int
                int index = Integer.parseInt(input.replaceAll("\\D+", "")) - 1;
                taskList.setTaskStatus(index, true);
                printIndentLine();
            } else {
                printIndentLine();
                taskList.addTask(input);
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
