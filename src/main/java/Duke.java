import java.util.ArrayList;
import java.util.Scanner;
import java.util.ArrayList;
import util.miscellaneous.DukeOperation;
import util.task.Task;
import java.io.File;

public class Duke extends DukeOperation {
    public static void main(String[] args) {
        Scanner input2 = new Scanner(System.in);
        String line;

        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println("Hello from\n" + LOGO);

        printGreeting();

        loadData(tasks);

        line = input2.nextLine();
        while (!line.equalsIgnoreCase("bye")) {
            boolean needUpdateTaskStatus = false;
            boolean isLoadingData = false;

            loadAndRun(tasks, line, isLoadingData, needUpdateTaskStatus);
        }

        exitLine();
    }
}