package duke;


import duke.cmds.Cmds;
import duke.deadline.Deadline;
import duke.duke_exception.DukeException;
import duke.event.Event;
import duke.event.ToDo;
import duke.task.Task;
import netscape.javascript.JSObject;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class Duke {
    private static final String MARK_CMD = "mark";
    private static final String UNMARK_CMD = "unmark";
    private static final String LIST_CMD = "list";
    private static final String ADD_CMD = "add";
    private static final String DELETE_CMD = "delete";
    private static final String OUT_DIR = "output";    // Honestly create a config file or smth
    private static final Boolean isDebugMode = false;
    private static final String SAVE_PATH = String.format("%s/%s", OUT_DIR, "SAVE.json");

    private static List<Task> toDoList = new ArrayList<Task>();

    private static void serializeTaskList() {
        JSONArray toStore = new JSONArray();
        for (Task task : toDoList) {
            toStore.add(task.serialize());
        }
        String storeStr = toStore.toString();
        System.out.println(toStore);
        Path dir = Paths.get(OUT_DIR);
//        File f = new File(OUT_DIR);
        if (!Files.exists(dir)) {   //createTempDirectory
            try {
                Files.createDirectory(Path.of(OUT_DIR));
            } catch (IOException e) {
                System.err.println("Failed to create directory!" + e.getMessage());
            }
        }
//        String filePath = String.format("%s/%s", OUT_DIR, "SAVE.json");
        try {
            FileWriter fw = new FileWriter(SAVE_PATH);
            fw.write(storeStr);
            fw.close();
        } catch (IOException e) {
            System.err.println("Failed to write to save file!" + e.getMessage());
        }
    }

    private static void loadTaskList() {
        String saveStr = "";
        try {
            FileReader fr = new FileReader(SAVE_PATH);
            int i;
            while ((i=fr.read()) != -1){
                saveStr += ((char)i);
            }
            System.out.println(saveStr);
            fr.close();
        } catch (IOException e){
            System.err.println("Failed to open save file!" + e.getMessage());
            return;
        }
        // ATTEMPTING PARSE

        JSONArray ja = (JSONArray) JSONValue.parse(saveStr);
//        Object[] ja = ((JSONArray) JSONValue.parse(saveStr)).toArray();
//        for (int i = 0; i < ja.length; i++){
//            Object curItem = ja[i];
//            toDoList.add(new Task())
//        }
        for(Object o: ja){
            if ( o instanceof JSONObject) {
                JSONObject jo = (JSONObject)o;
                String type = (String)jo.get("type");
                System.out.println("type: " + type);
                Task toAdd = null;
                if (type.equals("TODO")) {
                    toAdd = new ToDo((String)jo.get("description"));
                } else if (type.equals("DEADLINE")) {
                    toAdd = new Deadline((String)jo.get("description"),(String)jo.get("time"));
                } else {
                    toAdd = new Event((String)jo.get("description"),(String)jo.get("time"));
                }
                toDoList.add(toAdd);
            }
        }

    }

    private static Integer getTodoListSize() {
        return toDoList.size();
    }

    private static void listToDo(List<Task> tdList) {
        System.out.println("Here are the tasks in your list:");
        int idx = 1;
        for (Task todo : tdList) {
            System.out.println(String.format("%d. %s", idx, todo.getStatus()));
            idx += 1;
        }
    }

    public static void printHello() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello, I'm \n" + logo);
        System.out.println("What can I do for you?\n");
    }

    public static void printBye() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    public static void updateMark(String isMark, String[] cmd) {
        int idx;
        try {
            idx = Integer.parseInt(cmd[1]) - 1;
        } catch (NumberFormatException e) {
            System.out.println("OOPS, param provided is NOT an integer");
            return;
        }

        try {
            if (isMark.equals(MARK_CMD)) {
                System.out.println("Nice! I've marked this task as done: ");
                toDoList.get(idx).markDone();
            } else {
                System.out.println("OK, I've marked this task as not done yet: ");
                toDoList.get(idx).unmarkDone();
            }
            System.out.println(toDoList.get(idx).getStatus());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(String.format("OOPS, %d exceed your list of size %d", idx, getTodoListSize()));
            return;
        }


    }

    public static Task addTodo(String params) throws DukeException {
        if (params.strip().length() == 0) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        Task newTask = new ToDo(params);
        return newTask;
    }

    public static Task addDeadline(String params) throws DukeException {
        if (params.strip().length() == 0) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        String[] params2 = params.split("/");
        if (params2.length != 2) {
            throw new DukeException("☹ OOPS!!! Deadlines should have two parts");
        }
        return new Deadline(params2[0], params2[1]);
    }

    public static Task addEvent(String params) throws DukeException {
        if (params.strip().length() == 0) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        String[] params2 = params.split("/");
        if (params2.length != 2) {
            throw new DukeException("☹ OOPS!!! Events should have two parts");
        }
        return new Event(params2[0], params2[1]);
    }

    public static void handleAdd(String input, String main_cmd) throws DukeException {

        Task newTask;
        String params = input.substring(main_cmd.length());
        if (main_cmd.equals("todo")) {
            newTask = addTodo(params);
        } else if (main_cmd.equals("deadline")) {
            newTask = addDeadline(params);
        } else if (main_cmd.equals("event")) {
            newTask = addEvent(params);
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        System.out.println("Got it. I've added this task: ");
        toDoList.add(newTask);
        System.out.println(newTask.getStatus());
        System.out.println(String.format("Now you have %d tasks in the list.", getTodoListSize()));
    }

    public static void handleDelete(String input, String[] cmd) throws DukeException {
        if (cmd.length < 2) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but delete requires an index");
        }
        int idx = 0;
        try {
            idx = Integer.parseInt(cmd[1]) - 1;
        } catch (NumberFormatException e) {
            System.out.println("OOPS, param provided is NOT an integer");
        }
        Task removed = null;
        try {
            removed = toDoList.remove(idx);
            System.out.println(String.format("Noted. I've removed this task"));
            System.out.println(String.format("%s", removed.getStatus()));
            System.out.println(String.format("Now you have %d tasks in the list", getTodoListSize()));
        } catch (IndexOutOfBoundsException e) {
            System.out.println(String.format("OOPS, %d exceed your list of size %d", idx, getTodoListSize()));
        }


    }

    public static void main(String[] args) throws DukeException {
        printHello();
        loadTaskList();
        Scanner in = new Scanner(System.in);
        while (true) {
            String input = in.nextLine();
            String[] cmd = input.split(" ");
            String main_cmd = cmd[0];
            if (cmd.length == 0) {
                System.out.println("Please type something\n");
                continue;
            }
            if (main_cmd.equals("bye")) {
                break;
            } else if (main_cmd.equals(LIST_CMD)) {
                listToDo(toDoList);
            } else if (main_cmd.equals(MARK_CMD)) {
                updateMark(MARK_CMD, cmd);
            } else if (main_cmd.equals(UNMARK_CMD)) {
                updateMark(UNMARK_CMD, cmd);
            } else if (main_cmd.equals(ADD_CMD)) {
                handleAdd(input, main_cmd);
            } else if (main_cmd.equals(DELETE_CMD)) {
                handleDelete(input, cmd);

            } else {
                handleAdd(input, main_cmd);
                //throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
        serializeTaskList();
        printBye();
    }
}
