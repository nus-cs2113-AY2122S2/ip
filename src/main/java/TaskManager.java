import java.util.ArrayList;
public class TaskManager {
    private static final ArrayList<Task> Tasks = new ArrayList<Task>();
    //response of adding
    protected String ADD_RES = "Got it. I've added this task:\n";
    Chatbox chatbox = new Chatbox();

    /**
    public void saveTask() throws DukeException{
        String content = "";
        for(int i = 0;i < Tasks.size();i++){
            content += Tasks.get(i).getListName();
            content += "\n";
        }
        if(null != content){
            try{
                File file = new File("data/Duke.txt");
                if(!file.exists()){
                    File dir = new File(file.getParent());
                    dir.mkdirs();
                    file.createNewFile();
                }
                FileWriter outStream = new FileWriter(file);
                outStream.write(content);
                outStream.close();
            } catch (Exception e){
                throw new IllegalSavingAction();
            }
        }

    }
     */
    /**
     * Adds a general task in the list
     * @param name refers to the name of the task
     */
    public void addTask(String name) {
        Task newTask = new Task(name);
        Tasks.add(newTask);
        chatbox.setContent("added: " + name);
        chatbox.chatboxPrinter();
    }

    /**
     * Adds a deadline task to the list
     * @param name refers to the name of the task
     * @param by refers to the deadline of the task
     */
    public void addDeadline(String name, String by) {
        Deadline newDeadline = new Deadline(name, by);
        Tasks.add(newDeadline);
        int s = Tasks.size();
        String response = ADD_RES + newDeadline.getListName() +
                "\n" + "Now you have " + String.valueOf(s) + " tasks in your list.";
        chatbox.setContent(response);
        chatbox.chatboxPrinter();
    }

    /**
     * Adds an event task to the list
     * @param name refers to the name of the task
     * @param at refers to the happening time of the event
     */
    public void addEvent(String name, String at) {
        Event newEvent = new Event(name, at);
        Tasks.add(newEvent);
        int s = Tasks.size();
        String response = ADD_RES + newEvent.getListName() +
                "\n" + "Now you have " + String.valueOf(s) + " tasks in your list.";
        chatbox.setContent(response);
        chatbox.chatboxPrinter();
    }

    /**
     * Adds a todo task to the list
     * @param name refers to the name of the todo task
     */
    public void addToDo(String name) {
        ToDo newToDo = new ToDo(name);
        Tasks.add(newToDo);
        int s = Tasks.size();
        String response = ADD_RES + newToDo.getListName() + "\n" +
                "Now you have " + String.valueOf(s) + " tasks in your list.";
        chatbox.setContent(response);
        chatbox.chatboxPrinter();
    }

    /**
     * Lists all tasks in the list with adding time order
     */
    public void listTask() {
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

    /**
     * Marks specific task in the list as done
     * @param n refers to the index of the task in adding time order
     */
    public void markTask(int n) {
        System.out.println(n);
        String content = "";
        if(n < 1 || n > Tasks.size()) {
            //Beyonds boundaries
            content = "Sorry, I could not find the task :/";
        }else {
            Tasks.get(n - 1).mark();
            content = "Nice! I've marked this task as done:\n" + Tasks.get(n - 1).listName;
        }
        chatbox.setContent(content);
        chatbox.chatboxPrinter();
    }

    /**
     * Unmarks specific task in the list as undone
     * @param n refers to the index of the task in adding time order
     */
    public void unmarkTask(int n) {
        String content = "";
        if(n < 1 || n > Tasks.size()){
            // Beyonds boundaries
            content = "Sorry, I could not find the task :/";
        }else {
            Tasks.get(n - 1).unmark();
            content = "OK, I've marked this task as not done yet:\n" + Tasks.get(n - 1).listName;
        }
        chatbox.setContent(content);
        chatbox.chatboxPrinter();
    }

    public void deleteTask(int n) {
        String content = "";
        if(n < 1 || n > Tasks.size()) {
            //Beyonds boundaries
            content = "Sorry, I could not find the task :/";
        }else {
            content = "Okay! I've deleted this task:\n" + Tasks.get(n - 1).listName;
            Tasks.remove(n-1);
            int s = Tasks.size();
            content += "\nNow you have " + String.valueOf(s) + " tasks in your list";
        }
        chatbox.setContent(content);
        chatbox.chatboxPrinter();
    }


}
