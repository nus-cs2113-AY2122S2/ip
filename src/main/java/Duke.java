import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String boundary = "____________________________________________________________" + System.lineSeparator();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(boundary+ logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?" + System.lineSeparator() + boundary);
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        Task[] taskList = new Task[100];
        int countTask = 0;

        while (!line.equalsIgnoreCase("bye")) {
            if (line.equalsIgnoreCase("list")) {
                System.out.print(boundary);
                for (int i = 0; i < countTask; i ++) {
                    System.out.println((i + 1) + ". [" + taskList[i].getStatusIcon() + "] " + taskList[i].description);
                }
                System.out.print(boundary);
            } else if (line.toLowerCase().startsWith("mark")) {
                int toMark = Integer.parseInt(line.substring(5)) - 1;
                taskList[toMark].markDone();
                System.out.println(boundary + "Nice! I've marked this task as done:");
                System.out.println("[" + taskList[toMark].getStatusIcon() + "] " + taskList[toMark].description + System.lineSeparator() + boundary);
            } else if (line.toLowerCase().startsWith("unmark")) {
                int toMark = Integer.parseInt(line.substring(7)) - 1;
                taskList[toMark].markNotDone();
                System.out.println(boundary + "OK, I've marked this task as not done yet:");
                System.out.println("[" + taskList[toMark].getStatusIcon() + "] " + taskList[toMark].description + System.lineSeparator() + boundary);
            } else {
                taskList[countTask] = new Task(line);
                countTask ++;
                System.out.println(boundary + "added: " + line + System.lineSeparator() + boundary);
            }
            in = new Scanner(System.in);
            line = in.nextLine();
        }
        System.out.print(boundary + "Bye. Hope to see you again soon!" + System.lineSeparator() + boundary);
    }
}
