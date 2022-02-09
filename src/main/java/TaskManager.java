public class TaskManager {
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";

    private Task[] tasks = new Task[100];
    private int tasksCount = 0;

    public void addTask(String typeOfTask, String userInput) {

        try {
            String[] inputs = userInput.split(" ");
            String task = inputs[1];

            if (typeOfTask.equalsIgnoreCase(TODO_COMMAND)) {
                tasks[tasksCount] = new ToDo(userInput.substring(userInput.indexOf(" ") + 1));
                System.out.println(" Got it. I've added this task: ");
                System.out.println(tasks[tasksCount]);
                tasksCount++;
                System.out.println(" Now you have " + tasksCount + " tasks in the list");
            }
            else if (typeOfTask.equalsIgnoreCase(DEADLINE_COMMAND)) {
                tasks[tasksCount] = new Deadline(userInput.substring(userInput.indexOf(" ") + 1, userInput.indexOf("/by")), userInput.substring(userInput.indexOf("/by") + 4));
                System.out.println(" Got it. I've added this task: ");
                System.out.println(tasks[tasksCount]);
                tasksCount++;
                System.out.println(" Now you have " + tasksCount + " tasks in the list");
            }
            else if (typeOfTask.equalsIgnoreCase(EVENT_COMMAND)) {
                tasks[tasksCount] = new Event(userInput.substring(userInput.indexOf(" ") + 1, userInput.indexOf("/at")), userInput.substring(userInput.indexOf("/at") + 4));
                System.out.println(" Got it. I've added this task: ");
                System.out.println(tasks[tasksCount]);
                tasksCount++;
                System.out.println(" Now you have " + tasksCount + " tasks in the list");
            }
            else {
                System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

        } catch (IndexOutOfBoundsException e) {
            System.out.println(" ☹ OOPS!!! The description of a task cannot be empty.");
        }

    }

    public void markAsDone(int taskIndex) {
        if (tasks[taskIndex].getStatus() == false) {
            tasks[taskIndex].markAsDone();
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println("    [" + tasks[taskIndex].getStatusIcon() + "] " + tasks[taskIndex].getDescription());
        }
    }

    public void unmarkAsNotDone(int taskIndex) {
        if (tasks[taskIndex].getStatus() == true) {
            tasks[taskIndex].unmarkAsNotDone();
            System.out.println(" OK, I've marked this task as not done yet:");
            System.out.println("    [" + tasks[taskIndex].getStatusIcon() + "] " + tasks[taskIndex].getDescription());
        }
    }

    public void listTasks() {
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasksCount; i++) {
            System.out.println(" " + (i + 1) + "."+ tasks[i]);
        }
    }
}
