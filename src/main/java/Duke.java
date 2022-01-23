import java.util.Scanner;

public class Duke {

    static String[] textList = new String[100];
    static int currentCount = 0;
    static String dashedLine = "\t____________________________________________________________";

    public static void addToList(String line){
        textList[currentCount] = line;
        currentCount += 1;
        String message = dashedLine + "\n" +
                "\t" + "added:" + line + "\n" +
                dashedLine;
        System.out.println(message);
    }

    public static void printList(){
        System.out.print(dashedLine);
        for (int j = 0; j < currentCount; j++){
            System.out.print("\n");
            System.out.print("\t" + (j+1) + ". " + textList[j]);
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
            } else addToList(line);
            line = in.nextLine();
        }
        System.out.print(bye);
    }
}
