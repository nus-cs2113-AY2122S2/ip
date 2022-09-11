import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void printMsg(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }

    public static void saveTasks(ArrayList<Task> al) {
        try {
            String root = System.getProperty("user.dir");
            FileWriter saveFile = new FileWriter(root + "/data/duke.txt");
            for (int i = 0; i < al.size(); i++) {
                saveFile.write(al.get(i).toSave() + "\n");
            }
            saveFile.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving file.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greeting = " Hello! I'm Duke\n" +
                " What can I do for you?";
        String bye = " Bye. Hope to see you again soon!";

        String root = System.getProperty("user.dir");
        File saveFile = new File(root + "/data/duke.txt");
        saveFile.getParentFile().mkdirs();

        ArrayList<Task> array = new ArrayList<>();

        try {
            if (saveFile.createNewFile()) {
                System.out.println("Save file created: " + saveFile.getName());
            } else {
                System.out.println("Save file already exists, loading...");
                BufferedReader br = new BufferedReader(new FileReader(root + "/data/duke.txt"));
                String line = br.readLine();
                while (line != null) {
                    String[] saved = line.split(",");
                    for (int i = 0; i < saved.length; i++) {
                        System.out.print(saved[i] + ",");
                    }
                    System.out.print("\n");
                    switch (saved[0]) {
                        case "D":
                            Deadline d = new Deadline(saved[2], saved[3]);
                            if (saved[1].equals("1")) {
                                d.setDone();
                            }
                            array.add(d);
                            break;
                        case "T":
                            ToDo t = new ToDo(saved[2]);
                            array.add(t);
                            break;
                        case "E":
                            Event e = new Event(saved[2], saved[3]);
                            if (saved[1].equals("1")) {
                                e.setDone();
                            }
                            array.add(e);
                            break;
                    }
                    line = br.readLine();
                }
                br.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred when creating/loading save file");
            e.printStackTrace();
        }

        System.out.println("Hello from\n" + logo);
        printMsg(greeting);
        Scanner myObj = new Scanner(System.in);

        String input = "";
        String[] curCommand = {"First", "Second"};
        int index = 0;

        while (!curCommand[0].equals("bye")) {
            input = myObj.nextLine();
            curCommand = input.split(" ", 2);
            System.out.println("____________________________________________________________");
            switch (curCommand[0]) {
                case "bye":
                    System.out.println(bye);
                    break;
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < array.size(); i++) {
                        System.out.println((i + 1) + "." + array.get(i).toString());
                    }
                    break;
                case "mark":
                    System.out.println("Nice! I've marked this task as done:");
                    index = Integer.parseInt(curCommand[1]) - 1;
                    array.get(index).setDone();
                    System.out.println(array.get(index).toString());
                    saveTasks(array);
                    break;
                case "unmark":
                    System.out.println("OK, I've marked this task as not done yet:");
                    index = Integer.parseInt(curCommand[1]) - 1;
                    array.get(index).setNotDone();
                    System.out.println(array.get(index).toString());
                    saveTasks(array);
                    break;
                case "todo":
                    if (curCommand.length < 2) {
                        System.out.println("OOPS!!! The description of a todo cannot be empty.");
                        break;
                    }
                    array.add(new ToDo(curCommand[1]));
                    System.out.println("Got it. I've added this task:");
                    System.out.println(array.get(array.size() - 1).toString());
                    System.out.println("Now you have " + (array.size()) + " tasks in the list.");
                    saveTasks(array);
                    break;
                case "deadline":
                    if (curCommand.length < 2) {
                        System.out.println("OOPS!!! The description of a deadline cannot be empty.");
                        break;
                    }
                    String[] deadlineList = curCommand[1].split(" /by ", 2);
                    array.add(new Deadline(deadlineList[0], deadlineList[1]));
                    System.out.println("Got it. I've added this task:");
                    System.out.println(array.get(array.size() - 1).toString());
                    System.out.println("Now you have " + (array.size()) + " tasks in the list.");
                    saveTasks(array);
                    break;
                case "event":
                    if (curCommand.length < 2) {
                        System.out.println("OOPS!!! The description of an event cannot be empty.");
                        break;
                    }
                    String[] eventList = curCommand[1].split(" /at ", 2);
                    array.add(new Event(eventList[0], eventList[1]));
                    System.out.println("Got it. I've added this task:");
                    System.out.println(array.get(array.size() - 1).toString());
                    System.out.println("Now you have " + (array.size()) + " tasks in the list.");
                    saveTasks(array);
                    break;
                default:
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    break;
            }
            System.out.println("____________________________________________________________");
        }
    }
}
