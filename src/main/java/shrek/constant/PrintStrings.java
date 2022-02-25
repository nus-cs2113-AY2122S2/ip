package shrek.constant;

/**
 * Container for constant Strings to be printed.
 */
public class PrintStrings {
    private PrintStrings() {
    }

    public static final String LINE = "____________________________________________________________"
            + System.lineSeparator();

    public static final String shrekLogo = "⢀⡴⠑⡄⠀⠀⠀⠀⠀⠀⠀⣀⣀⣤⣤⣤⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ \n" +
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

    public static final String logo = "███████╗██╗  ██╗██████╗ ███████╗██╗  ██╗\n" +
            "██╔════╝██║  ██║██╔══██╗██╔════╝██║ ██╔╝\n" +
            "███████╗███████║██████╔╝█████╗  █████╔╝ \n" +
            "╚════██║██╔══██║██╔══██╗██╔══╝  ██╔═██╗ \n" +
            "███████║██║  ██║██║  ██║███████╗██║  ██╗\n" +
            "╚══════╝╚═╝  ╚═╝╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝\n" +
            "                                        \n";
    public static final String help = "Quick guide to using Shrek!" + System.lineSeparator() + System.lineSeparator() +
            "help: Displays this page" + System.lineSeparator() +
            "   Example: help" + System.lineSeparator() +
            "list: Lists all tasks in the task list" + System.lineSeparator() +
            "   Example: list" + System.lineSeparator() +
            "todo: Adds a Todo task to the task list" + System.lineSeparator() +
            "   Example: todo Homework" + System.lineSeparator() +
            "deadline: Adds a task and its deadline to the task list" + System.lineSeparator() +
            "   Example: deadline Wash Clothes /by 2022-04-01 23:59" + System.lineSeparator() +
            "event: Adds a task and its occurrence date to the task list" + System.lineSeparator() +
            "   Example: event ORD /at 2020-11-03 23:59" + System.lineSeparator() +
            "mark: Marks a task as complete" + System.lineSeparator() +
            "   Example: mark 1" + System.lineSeparator() +
            "unamrk: Unmarks a task that was previously marked" + System.lineSeparator() +
            "   Example: unmark 2" + System.lineSeparator() +
            "delete: Deletes a task from the task list" + System.lineSeparator() +
            "   Example: delete 3" + System.lineSeparator() +
            "find task: Finds all tasks containing a matching keyword from the task list" + System.lineSeparator() +
            "   Example: find task work" + System.lineSeparator() +
            "find time: Finds time in the tasks containing a matching keyword from the task list"
            + System.lineSeparator() + "   Example: find time 59" + System.lineSeparator() +
            "bye: Exits the program" + System.lineSeparator() +
            "   Example: bye";
}
