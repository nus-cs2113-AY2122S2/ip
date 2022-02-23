import jrobo.storage.Storage;
import jrobo.task.TaskManager;
import jrobo.ui.UI;

import java.util.Scanner;

public class JRobo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager manager = new TaskManager();
        Storage storage = new Storage(manager);
        UI ui = new UI(scanner, manager);
        run(storage, ui);
        scanner.close();
    }

    public static void run(Storage storage, UI ui) {
        storage.load();
        ui.setView();
        storage.save();
    }
}
