public class ChatBot {
    private final String BOT_NAME = "Big Bob";
    private Task[] listOfTasks = new Task[100];
    private int numOfTaskInList = 0;
    private final String DONE_SYMBOL = "[X] ";
    private final String NOT_DONE_SYMBOL = "[ ] ";

    public ChatBot() {
        System.out.println("    Greetings Human! I'm " + BOT_NAME + ".");
        System.out.println("    How may i be of service to you?");
    }

    public void printList() {
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < numOfTaskInList; i++) {
            int taskNumber = i + 1;
            System.out.print("    " + taskNumber + ".");
            if (listOfTasks[i].getDone() == true) {
                System.out.print(DONE_SYMBOL);
            } else {
                System.out.print(NOT_DONE_SYMBOL);
            }
            System.out.println(listOfTasks[i].getTaskName());
        }
    }

    public void addTaskToList(String taskName) {
        Task freshTask = new Task(taskName);
        listOfTasks[numOfTaskInList] = freshTask;
        numOfTaskInList++;
        System.out.println("    added: " + taskName);
    }

    public void updateTaskStatusInList(boolean taskIsDone, int taskIndex) {
        listOfTasks[taskIndex].setDone(taskIsDone);
    }

    public void echoFarewellGreeting() {
        System.out.println("    Good bye.See you soon :)) !");
    }

}
