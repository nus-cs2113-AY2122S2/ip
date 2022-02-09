package duke;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner mainScan = new Scanner(System.in);
        UserInterface ui = new UserInterface(mainScan);
        ui.start();
    }
}
