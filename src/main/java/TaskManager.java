import java.util.ArrayList;

public class TaskManager {

    String horiLine = "____________________________________________________________\n";

    private final ArrayList<Task> tasks = new ArrayList<>();

    public TaskManager() {
    }

    public void welcome() {
        String greeting = "  Hello, I'm Baymax.\n"+
                "  Your personal task managing companion. \n" +
                "  What can I do for you? \n";
        System.out.println(horiLine + greeting + horiLine);
    }

    public void bye() {
        String goodbye = "  Bye. Hope to see you again soon! \n";
        System.out.println(horiLine + goodbye + horiLine);//bye
    }

    public void addTask(Task newT) {
        tasks.add(newT);
        System.out.println(horiLine);
        System.out.println(" Got it. I've added this task: \n"+ newT.getStatusIcon()+
                            newT.getDescription()+"\n" +
                            "Now you have " +  + tasks.size() + " tasks in the list.");
        System.out.println(horiLine);
    }

    public void printTaskList() {
        System.out.println(horiLine);
        System.out.println("Here are the tasks in your list:");
        for (Task task: tasks) {
            String text = String.format("%d. %s %s", tasks.indexOf(task)+1, task.getStatusIcon(), task.getDescription());
            System.out.println(text);
        }
        System.out.println(horiLine);
    }

    public void markTask (int taskIndex) {
        Task temp = tasks.get(taskIndex);
        temp.markTaskDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(temp.getStatusIcon() + " " +
                           temp.getDescription());
    }

    public void unmarkTask (int taskIndex) {
        Task temp = tasks.get(taskIndex);
        temp.unmarkTaskDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(temp.getStatusIcon() + " " +
                           temp.getDescription());
    }

    public void deleteTask (int taskIndex) {

        Task deleted = tasks.get(taskIndex);
        System.out.println("Noted. I've removed this task:");
        String text = String.format("%s %s", deleted.getStatusIcon(), deleted.getDescription());
        System.out.println(text);
        tasks.remove(taskIndex);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");

    }
}

