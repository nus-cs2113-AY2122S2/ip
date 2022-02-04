public class UI {
    // String assets for Dukebot GUI
    final static String BORDER = "__________________________________________________";
    final static String INTRO_LOGO = "   ___             _               ___             _     \n" +
            "  |   \\   _  _    | |__    ___    | _ )    ___    | |_   \n" +
            "  | |) | | +| |   | / /   / -_)   | _ \\   / _ \\   |  _|  \n" +
            "  |___/   \\_,_|   |_\\_\\   \\___|   |___/   \\___/   _\\__|  \n" +
            "_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"| \n" +
            "\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-' ";
    final static String OUTRO_LOGO = "                               __\n" +
            "                            _ / /\n" +
            "  __ ___      ____      __ (_) |\n" +
            " / _` \\ \\ /\\ / /\\ \\ /\\ / /   | |\n" +
            "| (_| |\\ V  V /  \\ V  V /   _| |\n" +
            " \\__,_| \\_/\\_/    \\_/\\_/   (_) |\n" +
            "                              \\_\\";

    public static void printIntro() {
        System.out.println(INTRO_LOGO);
        printBorder();
        System.out.println(" Good Morning sir, I am DukeBot, your personal assistant! " +
                "\n What can I do for you today?");
        printBorder();
    }

    public static void printOutro() {
        System.out.println(OUTRO_LOGO);
        printBorder();
        System.out.println(" I guess it's time to say good bye..." +
                "\n Please come again soon!");
        printBorder();
    }

    public static void printBorder(){
        System.out.println(BORDER);
    }
}
