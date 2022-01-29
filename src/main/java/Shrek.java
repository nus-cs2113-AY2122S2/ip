import java.util.Scanner;


public class Duke {
    private static UserContent[] lists = new UserContent[100];
    private static int listIndex = 1;
    private static String line = PrintStrings.line;

    public static void printGreeting() {
        String logo = "███████╗██╗  ██╗██████╗ ███████╗██╗  ██╗\n" +
                "██╔════╝██║  ██║██╔══██╗██╔════╝██║ ██╔╝\n" +
                "███████╗███████║██████╔╝█████╗  █████╔╝ \n" +
                "╚════██║██╔══██║██╔══██╗██╔══╝  ██╔═██╗ \n" +
                "███████║██║  ██║██║  ██║███████╗██║  ██╗\n" +
                "╚══════╝╚═╝  ╚═╝╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝\n" +
                "                                        \n";

        String shrekLogo = "⢀⡴⠑⡄⠀⠀⠀⠀⠀⠀⠀⣀⣀⣤⣤⣤⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ \n" +
                "⠸⡇⠀⠿⡀⠀⠀⠀⣀⡴⢿⣿⣿⣿⣿⣿⣿⣿⣷⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀ \n" +
                "⠀⠀⠀⠀⠑⢄⣠⠾⠁⣀⣄⡈⠙⣿⣿⣿⣿⣿⣿⣿⣿⣆⠀⠀⠀⠀⠀⠀⠀⠀ \n" +
                "⠀⠀⠀⠀⢀⡀⠁⠀⠀⠈⠙⠛⠂⠈⣿⣿⣿⣿⣿⠿⡿⢿⣆⠀⠀⠀⠀⠀⠀⠀ \n" +
                "⠀⠀⠀⢀⡾⣁⣀⠀⠴⠂⠙⣗⡀⠀⢻⣿⣿⠭⢤⣴⣦⣤⣹⠀⠀⠀⢀⢴⣶⣆ \n" +
                "⠀⠀⢀⣾⣿⣿⣿⣷⣮⣽⣾⣿⣥⣴⣿⣿⡿⢂⠔⢚⡿⢿⣿⣦⣴⣾⠁⠸⣼⡿ \n" +
                "⠀⢀⡞⠁⠙⠻⠿⠟⠉⠀⠛⢹⣿⣿⣿⣿⣿⣌⢤⣼⣿⣾⣿⡟⠉⠀⠀⠀⠀⠀ \n" +
                "⠀⣾⣷⣶⠇⠀⠀⣤⣄⣀⡀⠈⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀ \n" +
                "⠀⠉⠈⠉⠀⠀⢦⡈⢻⣿⣿⣿⣶⣶⣶⣶⣤⣽⡹⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀ \n" +
                "⠀⠀⠀⠀⠀⠀⠀⠉⠲⣽⡻⢿⣿⣿⣿⣿⣿⣿⣷⣜⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀ \n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣷⣶⣮⣭⣽⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀ \n" +
                "⠀⠀⠀⠀⠀⠀⣀⣀⣈⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠇⠀⠀⠀⠀⠀⠀⠀ \n" +
                "⠀⠀⠀⠀⠀⠀⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀ \n" +
                "⠀⠀⠀⠀⠀⠀⠀⠹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀ \n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠛⠻⠿⠿⠿⠿⠛⠉";

        String greet = " Oh! Hello there! I'm Shrek\n" +
                " What can I do for you?";
        System.out.print(line);
        System.out.println(logo + "\n" + shrekLogo + "\n" + line + greet);
    }

    public static void printGoodbye() {
        String bye = " Bye bye! See you later!";
        System.out.print(line + bye + "\n" + line);
    }

    public static void printList() {
        System.out.println("Go finish these task, NOW:");
        for (int i = 1; i < listIndex; i++) {
            System.out.println(lists[i]);
        }
    }

    public static void addToList(String input, String taskName) {
        String[] words;
        String content;
        String date;
        boolean isSuccessful = true;

        switch (taskName) {
        case "T":
            lists[listIndex] = new ToDo(input, listIndex);
            break;
        case "D":
            if (input.contains("/by ")) {
                words = input.split("/by ", 2);
                content = words[0];
                date = words[1];
                lists[listIndex] = new Deadlines(content, date, listIndex);
            } else {
                isSuccessful = false;
            }
            break;
        case "E":
            if (input.contains("/at ")) {
                words = input.split("/at ", 2);
                content = words[0];
                date = words[1];
                lists[listIndex] = new Events(content, date, listIndex);
            } else {
                isSuccessful = false;
            }
            break;
        default:
            lists[listIndex] = new UserContent(input, listIndex);
            System.out.println("Did you type the task properly? Anyways it will still be placed in the list");
        }
        if (isSuccessful) {
            System.out.println("Done putting this in the list:");
            System.out.println(lists[listIndex]);
            System.out.println("Go do the " + listIndex + " task(s)!!");
            listIndex++;
        } else {
            System.out.println("Did you type the task properly? Re-enter your task");
        }
    }

    public static void markTask(String number) {
        System.out.println("So you've done this task, that's great I guess?");
        lists[Integer.parseInt(number)].setMark();
        System.out.println(lists[Integer.parseInt(number)]);
    }

    public static void unmarkTask(String number) {
        System.out.println("What do you mean you've undone");
        lists[Integer.parseInt(number)].setUnmark();
        System.out.println(lists[Integer.parseInt(number)]);
    }

    public static void takeInput(String userInput) {
        System.out.println(line);
        String[] words = userInput.split(" ", 2);
        switch (words[0]) {
        case "list":
            printList();
            break;
        case "unmark":
            unmarkTask(words[1]);
            break;
        case "mark":
            markTask(words[1]);
            break;
        case "todo":
            addToList(words[1], "T");
            break;
        case "deadline":
            addToList(words[1], "D");
            break;
        case "event":
            addToList(words[1], "E");
            break;
        case "":
            System.out.println("Type something ya prick");
            break;
        default:
            addToList(userInput, "");
        }
        System.out.print(line);
    }

    public static void main(String[] args) {
        printGreeting();
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        while (!userInput.equals("bye")) {
            takeInput(userInput);
            userInput = in.nextLine();
        }
        printGoodbye();
    }
}
