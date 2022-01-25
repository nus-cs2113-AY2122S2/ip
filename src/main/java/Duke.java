import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String command;
        Task[] allAbilities = new Task[100];
        int abilitiesCount = 0;
        boolean notQuit = true;
        DialogGenerator dialog = new DialogGenerator();

        dialog.greeting();

        while (notQuit) {
            Scanner in = new Scanner(System.in);
            command = in.nextLine();

            switch (command.split(" ")[0]) {
                case "bye": {
                    dialog.sayGoobye();
                    notQuit = false;
                }
                    break;

                case "mark": {
                    int index = Integer.parseInt(command.split(" ")[1]) - 1;
                    Task t = allAbilities[index];
                    dialog.markTask(t);
                }
                    break;

                case "unmark": {
                    int index = Integer.parseInt(command.split(" ")[1]) - 1;
                    Task t = allAbilities[index];
                    dialog.unmarkTask(t);
                }
                    break;

                case "list": {
                    dialog.displayListWithStatus(allAbilities, abilitiesCount);
                }
                    break;

                default: {
                    Task t = new Task(command);
                    allAbilities[abilitiesCount] = t;
                    abilitiesCount += 1;
                }
                    break;
            }
        }
    }
}
