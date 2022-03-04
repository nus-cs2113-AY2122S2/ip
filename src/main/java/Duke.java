import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    //Class variables
    private ArrayList<Task> taskList;


    //Constructor
    public Duke() {
        this.taskList = new ArrayList<Task>();

    }

    //Print all available tasks
    public void printTaskList(ArrayList<Task> taskList){
        int j = 1;
        for (Task task : taskList){
            String description = task.getDescription();
            String statusIcon = task.getStatusIcon();
            System.out.println(j + ". "  + "[" +  statusIcon + "]" + " " + description);
            j += 1;
        }
    }

    //Mark as done
    public Boolean doneCheck(String line, ArrayList<Task> taskList) {
        String[] words = line.split(" ");
        int length = words.length;
        if (length == 1){
            if (words[0].toUpperCase().equals("MARK")) {
                System.out.println("Please specify (eg: done 2) or just add a new one");
                return true;
            }
        }
        Boolean isMarked= false;
        if (words[0].toUpperCase().equals("MARK")) {
            int intIndex = Integer.parseInt(words[1]) - 1;
            if (words[1] == null){
                return isMarked;
            }
            if ( intIndex < 0 || intIndex > taskList.size()){
                System.out.println("Index is out of bounds");
                return true;
            }
            taskList.get(Integer.parseInt(words[1]) - 1).markIt();
            String statusIcon = taskList.get(Integer.parseInt(words[1]) - 1).getStatusIcon();
            String description = taskList.get(Integer.parseInt(words[1]) - 1).getDescription();
            System.out.println((Integer.parseInt(words[1])) + ". "+ "[" + statusIcon + "]" + " " + description);
            System.out.println("Done! We have marked task " + words[1] + "!");
            isMarked = true;
        }
        return isMarked;
    }


    //Add a task
    public void addTask(String line, ArrayList<Task> taskList) {

        String convertInput = line.toUpperCase();
        String originalString = line;
        while (!convertInput.equals("BYE")) {
            if (doneCheck(originalString, taskList)){
                System.out.println("___________________________________________________________________________\n");
            }
            else if (convertInput.equals("LIST")) {
                System.out.println("___________________________________________________________________________");
                this.printTaskList(taskList);
            }
            else {
                System.out.println("___________________________________________________________________________");
                Task t = new Task(originalString);
                taskList.add(t);
                System.out.println("added: " + originalString + "\n");
            }
            Scanner scanner = new Scanner(System.in);
            originalString = scanner.nextLine();
            convertInput= originalString.toUpperCase();

        }
        System.out.println("Bye. Hope to see you again soon!\n");
        System.out.println("___________________________________________________________________________");

    }

    //Get things started
    public void startProgramme(){
        String logo = "╮╭┻┻╮╭┻┻╮╭▕╮╲\n" +
                "▕╯┃╭╮┃┃╭╮┃╰▕╯╭▏\n" +
                "▕╭┻┻┻┛┗┻┻┛ ▕ ╰▏\n" +
                "▕╰━━━┓┈┈┈╭╮▕╭╮▏\n" +
                "▕╭╮╰┳┳┳┳╯╰╯▕╰╯▏\n" +
                "▕╰╯┈┗┛┗┛┈╭╮▕╮┈▏" ;
        System.out.println(logo);

        Scanner scanner = new Scanner(System.in);
        String line;
        System.out.println("___________________________________________________________________________");
        System.out.println("Hello! I'm Bob");
        System.out.println("Lets get started on planning your tasks ahead mate! Things are looking good.\n");
        System.out.println("___________________________________________________________________________");
        line = scanner.nextLine();
        addTask(line, taskList);
    }

    public static void main(String[] args) {
        Duke level3 = new Duke();
        level3.startProgramme();
    }
}