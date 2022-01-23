public class Task {
    private static String[] taskList = new String[100];
    private static int taskCount = 0;
    public static void addTask(String task){
        taskList[taskCount] = task;
        taskCount++;
    }

    public static void listTasks(){
        for(int i = 0; i < taskCount; i++){
            System.out.print(i+1);
            System.out.println(". "+taskList[i]);
        }
    }
}
