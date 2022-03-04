import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

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
            String typeIcon = task.getLetter();
            System.out.println(j + ". "  + typeIcon +  " [" +  statusIcon + "] " +  description);
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

    // Check if the given task is valid
    public Boolean isValidTask (String type){
        String DONE = "done";
        String DEADLINE = "deadline";
        String TODO = "todo";
        String EVENT = "event";
        String LISTE = "list";

        if (type.equals(DONE) || type.equals(TODO) || type.equals(DEADLINE)
                || type.equals(EVENT) || type.equals(LISTE) ){
            return true;
        }
        return false;
    }
    //return the string in the formatted version

    public String returnStringFormat(String line, String type) {
        if (type.equals("todo") ){
            String[] arrOfStr = line.split(type);
            String toReturn= arrOfStr[1].trim();
            return toReturn;
        } else {
            String preposition;
            String splitter;
            if (type.equals("deadline")){
                preposition = "by: ";
                splitter = "/by";
            }
            else {
                preposition = "at: ";
                splitter = "/at";
            }
            String[] arrOfStr = line.split(splitter);
            String[] toReturn = new String[2];
            String[] todo = arrOfStr[0].split(type);
            toReturn[0] = (todo[1].substring(1, todo[1].length()));       // task

            toReturn[1] = arrOfStr[1].substring(1, arrOfStr[1].length()); // date
            return toReturn[0] + "(" + preposition + toReturn[1] + ")";
        }
    }


    //Add a task
    public void addTask(String line, ArrayList<Task> taskList) {

        String ogString = line;
        while (!ogString.toUpperCase().equals("BYE")) {
            String[] words = ogString.split(" ");
            String eventType = words[0];
            System.out.println(eventType);
            if (!isValidTask(eventType)) {
                System.out.println("Please specify (eg: done, todo, deadline, list, event)");
            }
            else {
                if (doneCheck(ogString, taskList)){
                    System.out.println("___________________________________________________________________________\n");
                } else if (ogString.toUpperCase().equals("LIST")) {
                    System.out.println("___________________________________________________________________________\n");
                    this.printTaskList(taskList);
                    System.out.println("___________________________________________________________________________\n");
                } else {
                    System.out.println("___________________________________________________________________________\n");
                    String todoOrDeadlineOrEvent = returnStringFormat(ogString, eventType);
                    if (eventType.equals("event") || eventType.equals("deadline")) {
                        if (eventType.equals("event")) {
                            Event t = new Event(todoOrDeadlineOrEvent);
                            taskList.add(t);
                        } else {
                            Deadline t = new Deadline(todoOrDeadlineOrEvent);
                            taskList.add(t);
                        }
                    } else {
                        Todo e = new Todo(todoOrDeadlineOrEvent);
                        taskList.add(e);
                    }

                    Task t = taskList.get(taskList.size() - 1);
                    String description = t.getDescription();
                    String statusIcon = t.getStatusIcon();
                    String typeIcon = t.getLetter();
                    System.out.println("Got it. I have added this task: \n" + todoOrDeadlineOrEvent);
                    System.out.println(typeIcon +  " [" +  statusIcon + "] " +  description + "\n");
                    System.out.println("Now you have " + taskList.size() +  " items in the list \n");
                    System.out.println("___________________________________________________________________________\n");
                }
            }

            Scanner scanner = new Scanner(System.in);
            ogString = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!\n");
        System.out.println("___________________________________________________________________________\n");
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