package duke;

import duke.exception.IllegalCommandException;
import duke.exception.IllegalFormatException;
import duke.exception.NonExistentCommandException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.util.Command;
import duke.util.PatternGenerator;
import java.io.File;
import java.io.FileWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Duke {

    private static String dataPath;
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        Command.greetUser();
        dataPath = getPath();
        loadData(dataPath);
        Scanner sc = new Scanner(System.in);
        String input;
        while (true){
            input = sc.nextLine();
            try{
                getCommand(input);
            }catch (IllegalCommandException e){
                PatternGenerator.generateArrows();
                System.out.println("OOPS!!! The description cannot be empty.");
                PatternGenerator.generateLine();
            }
            catch (NonExistentCommandException e){
                PatternGenerator.generateArrows();
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                PatternGenerator.generateLine();
            }

            if(input.equals("bye")){
                break;
            }
        }
        saveData(dataPath);
    }

    public static String getPath(){
        String workingDir = System.getProperty("user.dir");
        String path = workingDir + "/Data/duke.txt";
        return path;
    }

    public static void saveData(String dataPath){
        FileWriter fw = null;
        try {
            fw = new FileWriter(dataPath);
            String textToAdd = Task.getData();
            System.out.println(textToAdd);
            fw.write(textToAdd);
            fw.close();
            System.out.println("Saved successfully.");
            //if the file does not exist
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadData(String dataPath){
        File f = new File(dataPath);
        System.out.println("full path: " + f.getAbsolutePath());
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()){
                loadTask(s.nextLine());

            }
            //if the file does not exist
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void loadTask(String taskText){
        String[] arrOfText = taskText.split("\\|");
        if (arrOfText.length == 3) {
            Todo todo = new Todo(arrOfText[2]);
            if (Integer.parseInt(arrOfText[1]) == 1){
                todo.markAsDone();
            }
            Task.addTask(todo);
        }
        else if (arrOfText.length == 4) {
            switch (arrOfText[0]){
            case "D":
                Deadline deadline = new Deadline(arrOfText[2],arrOfText[3]);
                if (Integer.parseInt(arrOfText[1]) == 1){
                    deadline.markAsDone();
                }
                Task.addTask(deadline);
                break;
            case "E":
                Event event = new Event(arrOfText[2],arrOfText[3]);
                if (Integer.parseInt(arrOfText[1]) == 1){
                    event.markAsDone();
                }
                Task.addTask(event);
                break;
            default:
                System.out.println("Invalid case.");
                break;
            }
        }
        else {
            System.out.println("No task left.");
        }
    }

    public static void getCommand(String input) throws IllegalCommandException, NonExistentCommandException{
        String[] arrOfInput = input.split(" ",2);
        switch (arrOfInput[0]) {
        case "bye":
            Command.exit();
            break;
        case "list":
            Command.list();
            break;
        case "todo":
            if (arrOfInput.length < 2){
                throw new IllegalCommandException();
            }

            Command.addTodo(arrOfInput[1]);
            break;
        case "deadline":
            if (arrOfInput.length < 2){
                throw new IllegalCommandException();
            }
            try {
                Command.addDDL(arrOfInput[1]);
            }
            catch (IllegalFormatException e){
                System.out.println("OOPS!!! The format is not correct.");
            }

            break;
        case "event":
            if (arrOfInput.length < 2){
                throw new IllegalCommandException();
            }
            try {
                Command.addEvent(arrOfInput[1]);
            }
            catch (IllegalFormatException e){
                System.out.println("OOPS!!! The format is not correct.");
            }

            break;
        case "mark":
            if (arrOfInput.length < 2){
                throw new IllegalCommandException();
            }
            Command.mark(Integer.parseInt(arrOfInput[1]));

        default:
            throw new NonExistentCommandException();

        }
    }


}
