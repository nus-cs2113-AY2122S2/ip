import java.util.ArrayList;
import java.util.Scanner;

import util.DukeClasses.*;
import util.task.Task;

public class Duke extends DukePrinter {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String line;

        DukeTaskList list = new DukeTaskList();

        System.out.println("Hello from\n" + LOGO);

        DukePrinter.printGreeting();

        DukeStorage.loadData(list);

        line = input.nextLine();
        while (!line.equalsIgnoreCase("bye")) {
            boolean needUpdateTaskStatus = false;
            boolean isLoadingData = false;

            DukeUI.loadAndRun(list, line, isLoadingData, needUpdateTaskStatus);
            line =input.nextLine();
        }

        DukePrinter.exitLine();
    }
}