public class ChatBot {
    private final String BOT_NAME = "Big Bob";
    private String[] listOfTasks = new String[100];
    private int numOfTaskInList = 0;
    public ChatBot() {
        System.out.println("    Greetings Human! I'm " + BOT_NAME + ".");
        System.out.println("    How may i be of service to you?");
    }
    public void printList() {
        for (int i = 0; i < numOfTaskInList; i++) {
            int taskNumber = i + 1;
            System.out.println("    " + taskNumber + ". " + listOfTasks[i]);
        }
    }
    public void addTaskToList(String taskName) {
        listOfTasks[numOfTaskInList] = taskName;
        numOfTaskInList++;
        System.out.println("    added: " + taskName);
    }
    public void echoFarewellGreeting() {
        System.out.println("    Good bye.See you soon :)) !");
    }
}
