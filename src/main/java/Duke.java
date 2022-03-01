import duke.task.TaskList;

import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser();
        Storage storage = new Storage();
        TaskList taskList = new TaskList();

        Ui.greet();

        storage.loadPastTasks(parser, storage, taskList);

        Ui.askForInput(scanner, parser, storage, taskList);

        Ui.bye();

    }


}
