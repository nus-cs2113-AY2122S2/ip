import java.util.Scanner;

public class Duke {

    static String[] taskList = new String[100];
    static Boolean[] taskStatusList = new Boolean [100];
    static int currentCount = 0;
    static String dashedLine = "\t____________________________________________________________";

    public static void markTask(int index){
        taskStatusList[index - 1] = true;
        String message = dashedLine + "\n" +
                "\t Nice! I've marked this task as done: \n" +
                "\t \t [X] " + taskList[index - 1] + "\n" +
                dashedLine ;
        System.out.println(message);
    }

    public static void unmarkTask(int index){
        taskStatusList[index - 1] = false;
        String message = dashedLine + "\n" +
                "\tOK, I've marked this task as not done yet: \n" +
                "\t \t [ ] " + taskList[index - 1] + "\n" +
                dashedLine ;
        System.out.println(message);
    }

    public static void addToList(String line){
        taskList[currentCount] = line;
        taskStatusList[currentCount] = false;
        currentCount += 1;
        String message = dashedLine + "\n" +
                "\t" + "added:" + line + "\n" +
                dashedLine;
        System.out.println(message);
    }

    public static void printList(){
        System.out.print(dashedLine);
        for (int j = 0; j < currentCount; j++){
            String indicator;
            if (taskStatusList[j]){
                indicator = "[X]";
            } else indicator = "[ ]";
            System.out.print("\n");
            System.out.print("\t" + (j+1) + "." + indicator + " " + taskList[j]);
        }
        System.out.println("\n" + dashedLine);
    }

    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String greeting = dashedLine + "\n" +
                "\t Hello! I'm Duke\n" +
                "\t What can I do for you?\n" +
                dashedLine;

        String bye = dashedLine + "\n" +
                "\t Bye. Hope to see you again soon!\n" +
                dashedLine;

        System.out.println(greeting);

        line = in.nextLine();

        while (!line.contains("bye")){
            if (line.equals("list")){
                printList();
            } else if (line.startsWith("mark")){
                int indexToMark = Integer.parseInt(line.substring(5));
                markTask(indexToMark);
            } else if(line.startsWith("unmark")){
                int indexToUnmark = Integer.parseInt(line.substring(7));
                unmarkTask(indexToUnmark);
            } else addToList(line);
            line = in.nextLine();
        }

        System.out.print(bye);
    }
}
