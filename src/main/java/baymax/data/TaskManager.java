package baymax.data;

import java.util.ArrayList;

import baymax.storage.Storage;
import baymax.ui.Ui;

public class TaskManager {

    String horiLine = "____________________________________________________________\n";
    private final ArrayList<Task> tasks;
    private Ui ui;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public TaskManager(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task newT) {
        tasks.add(newT);
        System.out.println(horiLine);
        System.out.println(" Got it. I've added this task: \n"+ newT.getStatusIcon()+
                            newT.getDescription()+"\n" +
                            "Now you have " +  + tasks.size() + " tasks in the list.");
        System.out.println(horiLine);
    }


    public void markTask (int taskIndex) {
        System.out.println(horiLine);
        Task temp = tasks.get(taskIndex);
        temp.markTaskDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(temp.getStatusIcon() + " " +
                           temp.getDescription());
        System.out.println(horiLine);
    }

    public void unmarkTask (int taskIndex) {
        System.out.println(horiLine);
        Task temp = tasks.get(taskIndex);
        temp.unmarkTaskDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(temp.getStatusIcon() + " " +
                           temp.getDescription());
        System.out.println(horiLine);
    }

    public void deleteTask (int taskIndex) {
        System.out.println(horiLine);
        Task deleted = tasks.get(taskIndex);
        System.out.println("Noted. I've removed this task:");
        String text = String.format("%s %s", deleted.getStatusIcon(), deleted.getDescription());
        System.out.println(text);
        tasks.remove(taskIndex);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(horiLine);
    }

//    public void initialiseNewFile() throws IOException {
//        System.out.println(horiLine);
//        System.out.println("Record accessing......");
//        File dir = new File("data");
//        if(!dir.exists()){
//            dir.mkdir();
//            System.out.println("create a new directory \"data\"...... ");
//        }
//        File file = new File("data/Baymax.txt");
//        if(!file.exists()) {
//            file.createNewFile();
//            System.out.println("create a new file \"Baymax.txt\"...... ");
//            System.out.println(horiLine);
//            return;
//        }
//        Scanner initTask = new Scanner(file);
//        int taskIndex = -1;
//        while(initTask.hasNext()){
//            taskIndex++;
//            Task temp;
//            String[] word_split = initTask.nextLine().split(" / ");
//            //System.out.println(word_split[0]+" sdf " + word_split[1] + " df "+ word_split[2]+ " df "+ word_split[3]);
//            switch(word_split[0]){
//                case "T":
//                    tasks.add(new Todo(word_split[2]));
//                    if (word_split[1].equals("1")) {
//                        temp = tasks.get(taskIndex);
//                        temp.markTaskDone();
//                    }
//                    break;
//                case "D":
//                    tasks.add(new Deadline(word_split[2],word_split[3]));
//                    if (word_split[1].equals("1")) {
//                        temp = tasks.get(taskIndex);
//                        temp.markTaskDone();
//                    }
//                    break;
//                case "E":
//                    tasks.add(new Event(word_split[2],word_split[3]));
//                    if (word_split[1].equals("1")) {
//                        temp = tasks.get(taskIndex);
//                        temp.markTaskDone();
//                    }
//                    break;
//                default:
//                    System.out.println(horiLine);
//                    System.out.println("Error accur, please check.");
//                    System.out.println(horiLine);
//                    break;
//            }
//        }
//        System.out.println(horiLine);
//    }
//
//    public void saveTask() throws IOException {
//
//        FileWriter fw = new FileWriter("data/Baymax.txt");
//        for(int i = 0; i < tasks.size(); i++){
//            fw.write(tasks.get(i).saveInfo() + "\n");
//        }
//        fw.close();
//    }
}

