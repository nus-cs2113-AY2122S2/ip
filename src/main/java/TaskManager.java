import java.util.ArrayList;
public class TaskManager {
    private static final ArrayList<Task> Tasks = new ArrayList<Task>();
    private String addResponse = "Got it. I've added this task:\n";
    Chatbox chatbox = new Chatbox();

    public void addTask(String name){
        Task newTask = new Task(name);
        Tasks.add(newTask);
        chatbox.setContent("added: " + name);
        chatbox.chatboxPrinter();
    }

    public void addDeadline(String name, String by){
        Deadline newDeadline = new Deadline(name, by);
        Tasks.add(newDeadline);
        int s = Tasks.size();
        String response = addResponse + newDeadline.getListName() + "\n" + "Now you have " + String.valueOf(s) + " tasks in your list.";
        chatbox.setContent(response);
        chatbox.chatboxPrinter();
    }

    public void addEvent(String name, String by){
        Event newEvent = new Event(name, by);
        Tasks.add(newEvent);
        int s = Tasks.size();
        String response = addResponse + newEvent.getListName() + "\n" + "Now you have " + String.valueOf(s) + " tasks in your list.";
        chatbox.setContent(response);
        chatbox.chatboxPrinter();
    }

    public void addToDo(String name){
        ToDo newToDo = new ToDo(name);
        Tasks.add(newToDo);
        int s = Tasks.size();
        String response = addResponse + newToDo.getListName() + "\n" + "Now you have " + String.valueOf(s) + " tasks in your list.";
        chatbox.setContent(response);
        chatbox.chatboxPrinter();
    }

    public void listTask(){
        String content = "Here are the tasks in your list:\n";
        if(Tasks.size() == 0){
            content = "Sorry, there's no task in the list :(";
        }else {
            for (int i = 0; i < Tasks.size(); i++) {
                String index = String.valueOf(i + 1);
                String name = index + ". " + Tasks.get(i).getListName();
                content += name;
                if(i < Tasks.size() - 1){
                    content += "\n";
                }
            }
        }
        chatbox.setContent(content);
        chatbox.chatboxPrinter();
    }

    public void markTask(int n){
        System.out.println(n);
        String content = "";
        if(n < 1 || n > Tasks.size()){
            content = "Sorry, I could not find the task :/";
        }else {
            //System.out.println(n);
            Tasks.get(n - 1).mark();
            content = "Nice! I've marked this task as done:\n" + Tasks.get(n - 1).listName;
        }
        chatbox.setContent(content);
        chatbox.chatboxPrinter();
    }

    public void unmarkTask(int n){
        String content = "";
        if(n < 1 || n > Tasks.size()){
            content = "Sorry, I could not find the task :/";
        }else {
            Tasks.get(n - 1).unmark();
            content = "OK, I've marked this task as not done yet:\n" + Tasks.get(n - 1).listName;
        }
        chatbox.setContent(content);
        chatbox.chatboxPrinter();
    }


}
