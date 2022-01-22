import java.util.Scanner;

public class Duke {
    private static String line = "____________________________________________________________\n";
    private static userContent[] list = new userContent[100];
    private static int listIndex = 0;

    public static void greeting() {
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

    public static void goodbye(){
        String bye = " Bye bye! See you later!";
        System.out.print(line + bye + "\n" + line);
    }

    public static void printList(){
        System.out.println("Go finish these task, NOW:");
        for(int i = 1; i <= listIndex; i++){
            System.out.println(i + ". " + "[" + (list[i].getMark()? "X":" ") + "] " + list[i].getContent());
        }
    }

    public static void addToList(String input){
        listIndex++;
        list[listIndex] = new userContent(input, listIndex);
        System.out.println("Added: " + input);
    }

    public static void mark(String number){
        System.out.println("So you've done this task, that's great I guess? ");
        list[Integer.parseInt(number)].setMark();
        System.out.println(number + ". " + "[X] " + list[Integer.parseInt(number)].getContent());
    }

    public static void unmark(String number){
        System.out.println("What do you mean you've undone");
        list[Integer.parseInt(number)].setUnmark();
        System.out.println(number + ". " + "[ ] " + list[Integer.parseInt(number)].getContent());

    }

    public static void takeInput(String userInput){
        System.out.println(line);
        String[] words = userInput.split(" ");
        switch (words[0]){
        case "list":
            printList();
            break;
        case "unmark":
            unmark(words[1]);
            break;
        case "mark":
            mark(words[1]);
            break;
        case "":
            System.out.println("Type something ya prick");
            break;
        default:
            addToList(userInput);
        }
        System.out.print(line);
    }

    public static void main(String[] args) {
        greeting();
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine().toLowerCase();
        while (!userInput.equals("bye")){
            takeInput(userInput);
            userInput = in.nextLine();
        }
        goodbye();
    }
}
