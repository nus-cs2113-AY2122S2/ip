package duke;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    static ArrayList<Task> taskList = new ArrayList<Task>();
//    static int currentCount = 0;
    final static String DASHED_LINE = "\t____________________________________________________________";

    private static void markTask(int index){
        taskList.get(index - 1).setDone(true);
        String message = DASHED_LINE + "\n" +
                "\t Nice! I've marked this task as done: \n" +
                "\t \t" + taskList.get(index - 1).toString() + "\n" +
                DASHED_LINE;
        System.out.println(message);
    }

    private static void unMarkTask(int index){
        taskList.get(index - 1).setDone(false);
        String message = DASHED_LINE + "\n" +
                "\tOK, I've marked this task as not done yet: \n" +
                "\t \t" + taskList.get(index - 1).toString() + "\n" +
                DASHED_LINE;
        System.out.println(message);
    }

    private static void printAddedItem(Task task){
        String message = DASHED_LINE + "\n" +
                "\t Got it. I've added this task:" + "\n" +
                "\t \t" + task.toString() + "\n" +
                "\t Now you have " + taskList.size() +
                " tasks in the list." + "\n" +
                DASHED_LINE;
        System.out.println(message);
    }

    private static void printList(){
        System.out.print(DASHED_LINE);
        for (int j = 0; j < taskList.size(); j++){
            System.out.print("\n");
            System.out.print("\t" + (j+1) + "." + taskList.get(j).toString());
        }
        System.out.println("\n" + DASHED_LINE);
    }

    private static void printIllegalKeyword(){
        String message = DASHED_LINE + "\n" +
                "\t You typed an illegal keyword." + "\n" +
                "\t I have no idea what you are trying to say. :(" + "\n" +
                DASHED_LINE;
        System.out.println(message);
    }

    private static void printIllegalDescription(){
        String message = DASHED_LINE + "\n" +
                "\t You did not add any description." + "\n" +
                "\t I can't do much. Try again!" + "\n" +
                DASHED_LINE;
        System.out.println(message);
    }



    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String greeting = DASHED_LINE + "\n" +
                "\t Hello! I'm Duke\n" +
                "\t What can I do for you?\n" +
                DASHED_LINE;

        String bye = DASHED_LINE + "\n" +
                "\t Bye. Hope to see you again soon!\n" +
                DASHED_LINE;

        System.out.println(greeting);

        line = in.nextLine();


        while (!line.contains("bye")){
            try{
                processLine(line);

            } catch (DukeIllegalKeyword e){
                printIllegalKeyword();

            } catch (DukeIllegalDescription e){
                printIllegalDescription();
            }
            
            line = in.nextLine();
        }
        System.out.print(bye);
    }

    private static void processLine(String line) throws DukeIllegalKeyword, DukeIllegalDescription {
        if (line.equals("list")){
            printList();
        } else if (line.startsWith("mark")){
            int indexToMark = Integer.parseInt(line.substring(5));
            markTask(indexToMark);
        } else if(line.startsWith("unmark")){
            int indexToUnmark = Integer.parseInt(line.substring(7));
            unMarkTask(indexToUnmark);
        } else if (line.startsWith("todo")){
            if (line.length() < 5){
                throw new DukeIllegalDescription();
            }
            String todoDescription = line.substring(4);
            Todo task = new Todo(todoDescription);
            taskList.add(task);
//            currentCount += 1;
            printAddedItem(task);
        } else if (line.startsWith("deadline")){
            if (line.length() < 9 || !line.contains("/by")){
                throw new DukeIllegalDescription();
            }
            int byIndex = line.indexOf("/by");
            String deadlineDescription = line.substring(8, byIndex - 1);
            String by = line.substring(byIndex + 3);
            Deadline task = new Deadline(deadlineDescription, by);
            taskList.add(task);
//            currentCount += 1;
            printAddedItem(task);
        } else if (line.startsWith("event")){
            if (line.length() < 6 || !line.contains("/at")){
                throw new DukeIllegalDescription();
            }
            int atIndex = line.indexOf("/at");
            String eventDescription = line.substring(5, atIndex - 1);
            String at = line.substring(atIndex + 3);
            Event task = new Event(eventDescription, at);
            taskList.add(task);
//            currentCount += 1;
            printAddedItem(task);
        } else throw new DukeIllegalKeyword();
    }
}
