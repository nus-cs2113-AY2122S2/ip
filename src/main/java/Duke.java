import java.util.Date;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadFile());
    }

    public void run() {
        ui.showWelcome();
        String cmd = ui.getInput();
        while (!cmd.equals("bye")) {
            while (!cmd.equals("bye")) {
                switch (cmd) {
                case "list":
                    printTasks(ui, tasks);
                    break;
                case "mark":
                    markTask(ui, tasks);
                    break;
                case "unmark":
                    unmarkTask(ui, tasks);
                    break;
                case "add":
                    ui.showAddOptions();
                    String str = ui.getInput();
                    switch (str) {
                    case "1":
                        addTodo(ui, tasks);
                        break;
                    case "2":
                        addDeadline(ui, tasks);
                        break;
                    case "3":
                        addEvent(ui, tasks);
                        break;
                    default:
                        System.out.println("Uh oh, please enter a valid number!");
                    }
                    ui.showLineBreak();
                    break;
                case "delete":
                    deleteTask(ui, tasks);
                case "save":
                    storage.saveFile(tasks);
                    break;
                case "find":
                    findTask(ui, tasks);
                    break;
                default:
                    ui.showUnknownCommand();
                    break;
                }
                cmd = ui.getInput();
            }
        }
        ui.showBye();

    }

    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }

    /**
     * Prints a list of tasks that are currently in the task list.
     *
     * @param ui
     * @param tasks
     */
    public static void printTasks(Ui ui, TaskList tasks) {
        int numTasks = tasks.getSize();
        if (numTasks == 0) {
            System.out.println("Looks like you don't have any tasks for now!");
            ui.showLineBreak();
            return;
        }
        for (int i = 0; i < numTasks; i++) {
            System.out.print((i + 1) + ". ");
            tasks.getTask(i).printTask();
        }
        ui.showLineBreak();
    }

    /**
     * Prompts the user for input to create a todo.
     *
     * @param ui
     * @param tasks
     */
    public static void addTodo(Ui ui, TaskList tasks) {
        System.out.println("Okie, what should I call the task?");
        String str = ui.getInput();
        if (!str.isEmpty()) {
            Todo t = new Todo(str);
            tasks.addTask(t);
            System.out.println("Added!");
        } else {
            System.out.println("Oops! The description of a Todo cannot be empty.");
        }
    }

    /**
     * Prompts the user for input to create a deadline.
     *
     * @param ui
     * @param tasks
     */
    public static void addDeadline(Ui ui, TaskList tasks) {
        System.out.println("Okie, what should I call the task?");
        String str = ui.getInput();
        if (str.isEmpty()) {
            System.out.println("Oops! The description of a Deadline cannot be empty.");
            return;
        }
        System.out.println("Okie, when is this due by? (dd/mm/yyyy HH:mm)");
        Date by = ui.getDate();
        if (by == null) {
            System.out.println("Oops! The due date of a Deadline cannot be empty.");
            return;
        }
        Deadline d = new Deadline(str, by);
        tasks.addTask(d);
        System.out.println("Added!");
    }

    /**
     * Prompts the user for input to create an event.
     *
     * @param ui
     * @param tasks
     */
    public static void addEvent(Ui ui, TaskList tasks) {
        System.out.println("Okie, what should I call the task?");
        String str = ui.getInput();
        if (str.isEmpty()) {
            System.out.println("Oops! The description of a Deadline cannot be empty.");
            return;
        }
        System.out.println("Okie, when does the event start? (dd/mm/yyyy HH:mm)");
        Date start = ui.getDate();
        if (start == null) {
            return;
        }
        System.out.println("Okie, when does the event end? (dd/mm/yyyy HH:mm)");
        Date by = ui.getDate();
        if (by == null) {
            return;
        }
        Event e = new Event(str, by, start);
        tasks.addTask(e);
        System.out.println("Added!");
    }

    /**
     * Prompts the user for input to mark a specific task.
     *
     * @param ui
     * @param tasks
     */
    public static void markTask(Ui ui, TaskList tasks) {
        printTasks(ui, tasks);
        System.out.println("Which task would you like to mark as completed?");
        String taskNum = ui.getInput();
        if (!isInt(taskNum)) {
            System.out.println("Uh oh! Please enter a valid input!");
            return;
        }
        int num = Integer.parseInt(taskNum);
        int numTasks = tasks.getSize();
        if (num > numTasks || num <= 0) {
            System.out.println("Uh oh! It seems like that task doesn't exist!");
        } else {
            tasks.getTask(num - 1).setDone(true);
            System.out.println("Nice! I've marked this task as done: ");
            tasks.getTask(num - 1).printTask();
        }
    }

    /**
     * Prompts the user for input to unmark a specific task.
     *
     * @param ui
     * @param tasks
     */
    public static void unmarkTask(Ui ui, TaskList tasks) {
        printTasks(ui, tasks);
        System.out.println("Which task would you like to mark as incomplete?");
        String taskNum = ui.getInput();
        if (!isInt(taskNum)) {
            System.out.println("Uh oh! Please enter a valid input!");
            return;
        }
        int num = Integer.parseInt(taskNum);
        int numTasks = tasks.getSize();
        if (num > numTasks || num <= 0) {
            System.out.println("Uh oh! It seems like that task doesn't exist!");
        } else {
            tasks.getTask(num - 1).setDone(false);
            System.out.println("Okie, I've marked this task as not done yet:");
            tasks.getTask(num - 1).printTask();
        }

    }

    /**
     * Prompts the user for input to delete a specific task.
     *
     * @param ui
     * @param tasks
     */
    public static void deleteTask(Ui ui, TaskList tasks) {
        printTasks(ui, tasks);
        System.out.println("Which task would you like to remove?");
        String taskNum = ui.getInput();
        if (!isInt(taskNum)) {
            System.out.println("Uh oh! Please enter a valid input!");
            return;
        }
        int num = Integer.parseInt(taskNum);
        int numTasks = tasks.getSize();
        if (num > numTasks || num <= 0) {
            System.out.println("Uh oh! It seems like that task doesn't exist!");
        } else {
            System.out.println("Okay! I have removed this task: ");
            tasks.getTask(num - 1).printTask();
            tasks.removeTask(num - 1);
        }
    }


    /**
     * Checking for valid input by user.
     *
     */
    private static boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * Prompts the user for input to find a list of matching tasks.
     *
     * @param ui
     * @param tasks
     */
    public static void findTask(Ui ui, TaskList tasks){
        int numTasks = tasks.getSize();
        if (numTasks == 0) {
            System.out.println("Looks like you don't have any tasks for now!");
            ui.showLineBreak();
            return;
        }
        System.out.println("What would you like to find?");
        String task = ui.getInput();
        int index = 1;
        for (int i = 0; i < numTasks; i++) {
            String desc = tasks.getTask(i).getDescription();
            if (desc.contains(task)) {
                System.out.print((index) + ". ");
                index++;
                tasks.getTask(i).printTask();
            }
        }
        ui.showLineBreak();
    }

}
