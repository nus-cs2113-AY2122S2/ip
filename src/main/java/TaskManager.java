import java.util.ArrayList;
public class TaskManager {
    private static ArrayList<Task> Tasks = new ArrayList<Task>();
    Chatbox chatbox = new Chatbox();

    public void addTask(String name){
        Task newTask = new Task();
        newTask.setName(name);
        Tasks.add(newTask);
        chatbox.setContent("added: " + name);
        chatbox.chatboxPrinter();
    }

    public void listTask(){
        String content = "";
        if(Tasks.size() == 0){
            content = "Sorry, there's no task in the list :(";
        }else {
            for (int i = 0; i < Tasks.size(); i++) {
                String index = String.valueOf(i + 1);
                String name = index + ". " + Tasks.get(i).getTaskName();
                content += name;
                if(i < Tasks.size() - 1){
                    content += "\n";
                }
            }
        }
        chatbox.setContent(content);
        chatbox.chatboxPrinter();
    }


}
