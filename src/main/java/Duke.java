//import java.io.BufferedWriter;
//import java.io.FileOutputStream;
//import java.io.OutputStreamWriter;
//import java.io.Writer;
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.NoSuchElementException;
//import java.util.Scanner;
//import java.io.InputStreamReader;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//
//
//public class Duke {
//    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
//        Scanner sc = new Scanner(System.in);
//        ArrayList<Task> tasks = new ArrayList<>();
//        initTasks(tasks);
//
//        System.out.print("____________________\n"+
//                          "Hello! I'm Duke\n"+
//                          "What can I do for you?\n");
//        boolean isToQuit = false;
//        String input;
//
//        while(!isToQuit){
//            input = sc.nextLine();
//            String commandWord = getCommand(input);
//            String info = getInfo(input);
//
//            switch(commandWord){
//            case "todo":
//                try{
//                    addTodo(info, tasks);
//                }catch (DukeEmptyStringException e){
//                    System.out.println("Task cannot be empty");
//                }
//                break;
//            case "deadline":
//                try{
//                    addDeadline(info, tasks);
//                }catch (DukeEmptyStringException e){
//                    System.out.println("Task cannot be empty");
//                }catch (DukeInsufficientInfoException f){
//                    System.out.println("Deadline should have /by field");
//                }
//                break;
//            case "event":
//                try{
//                    addEvent(info, tasks);
//                }catch (DukeEmptyStringException e){
//                    System.out.println("Task cannot be empty");
//                }catch (DukeInsufficientInfoException f){
//                    System.out.println("Event should have /at field");
//                }
//                break;
//            case "mark":
//                mark(info, tasks);
//                break;
//            case "unmark":
//                unmark(info, tasks);
//                break;
//            case "list":
//                list(tasks);
//                break;
//            case "delete":
//                delete(info, tasks);
//                break;
//            case "save":
//                save(tasks);
//                break;
//            case "bye":
//                isToQuit = true;
//                break;
//            default:
//                System.out.println("Not a valid command");
//                break;
//            }
//        }
//
//        System.out.print("____________________\n"+
//                "Bye. Hope to see you again soon!\n"+
//                "____________________\n");
//    }
//
//    private static void initTasks(ArrayList<Task> tasks){
//        Scanner fileIn;
//        try{
//            fileIn = new Scanner(new InputStreamReader(new FileInputStream("duke.txt"), StandardCharsets.UTF_8));
//        }catch(FileNotFoundException e){
//            System.out.println("// Warning: File could not be opened\n");
//            return;
//        }
//        String info, curLine;
//        try{
//            curLine = fileIn.nextLine();
//        }catch(NoSuchElementException e){
//            return;
//        }
//
//        while(curLine != null){
//            try{
//                info = curLine.substring(7);
//            }catch(IndexOutOfBoundsException e){
//                curLine = fileIn.nextLine();
//                continue;
//            }
//
//            if(info.equals("")){
//                curLine = fileIn.nextLine();
//                continue;
//            }
//            if(info.contains("(at:")){
//                addEventfromText(info, tasks);
//            }else if(info.contains("(by:")){
//                addDeadlinefromText(info, tasks);
//            }else{
//                addTodofromText(info, tasks);
//            }
//            try{
//                curLine = fileIn.nextLine();
//            }catch(NoSuchElementException e){
//                return;
//            }
//        }
//        fileIn.close();
//    }
//
//    private static void addEventfromText(String info, ArrayList<Task> list){
//        String description, at;
//        try{
//            description = info.substring(0,info.indexOf("(at:")-1);
//        }catch(IndexOutOfBoundsException e){
//            System.out.println("// Warning: Event has no description");
//            return;
//        }
//        try{
//            at = info.substring(info.indexOf("(at:")+5, info.length()-1);
//        }catch(IndexOutOfBoundsException e){
//            System.out.println("// Warning: Event has no 'at' field");
//            return;
//        }
//        if(at.equals("")){
//            System.out.println("// Warning: Event has no 'at' field");
//            return;
//        }
//
//        Event newEvent = new Event(description, at);
//        list.add(newEvent);
//    }
//
//    private static void addDeadlinefromText(String info, ArrayList<Task> list){
//        String description, by;
//        try{
//            description = info.substring(0,info.indexOf("(by:")-1);
//        }catch(IndexOutOfBoundsException e){
//            System.out.println("// Warning: Deadline has no description");
//            return;
//        }
//        try{
//            by = info.substring(info.indexOf("(by:")+5, info.length()-1);
//        }catch(IndexOutOfBoundsException e){
//            System.out.println("// Warning: Deadline has no 'by' field");
//            return;
//        }
//        if(by.equals("")){
//            System.out.println("// Warning: Deadline has no 'by' field");
//            return;
//        }
//
//        Deadline newDeadline = new Deadline(description, by);
//        list.add(newDeadline);
//    }
//
//    private static void addTodofromText(String info, ArrayList<Task> list){
//        Task newTodo = new Task(info);
//        list.add(newTodo);
//    }
//
//    private static void delete(String info, ArrayList<Task> list){
//        int index;
//        Task removedTask;
//        try{
//            index = Integer.parseInt(info);
//        } catch (NumberFormatException e){
//            System.out.println("Delete should be followed by a number");
//            return;
//        }
//        try{
//            removedTask = list.remove(index-1);
//        }catch (IndexOutOfBoundsException f){
//            System.out.println("This task does not exist");
//            return;
//        }
//        System.out.printf("I've deleted this task:\n" +
//                "\t%s" +
//                "Now you have %d in the list.\n", removedTask, list.size());
//    }
//
//    private static void save(ArrayList<Task> list){
//        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
//                new FileOutputStream("duke.txt"), StandardCharsets.UTF_8))) {
//            for(Task t: list){
//                writer.write(t.toString());
//            }
//        }catch(FileNotFoundException e){
//            System.out.println("File could not be opened. Please try again.\n");
//            return;
//        }catch(IOException f){
//            System.out.println("Cannot write to file. Please try again.\n");
//            return;
//        }
//        System.out.println("File saved successfully");
//    }
//
//    private static void addTodo(String info, ArrayList<Task> list) throws DukeEmptyStringException{
//        if(info == null){
//            throw new DukeEmptyStringException();
//        }
//        Task newTask = new Task(info);
//        list.add(newTask);
//        System.out.printf("Got it. I've added this task:\n" +
//                "%s" +
//                "Now you have %d in the list.\n", newTask, list.size());
//    }
//
//    private static void addDeadline(String info, ArrayList<Task> list) throws DukeEmptyStringException, DukeInsufficientInfoException{
//        if(info == null){
//            throw new DukeEmptyStringException();
//        }
//        if(!info.contains("/by")){
//            throw new DukeInsufficientInfoException();
//        }
//
//        String by, task;
//        try{
//            by = info.substring(info.indexOf("/by")+4);
//        }catch (StringIndexOutOfBoundsException e){
//            System.out.println("/by field is empty");
//            return;
//        }
//        try{
//            task = info.substring(0, info.indexOf("/by")-1);
//        }catch (StringIndexOutOfBoundsException e){
//            System.out.println("description field is empty");
//            return;
//        }
//
//        Deadline newDeadline = new Deadline(task, by);
//        list.add(newDeadline);
//        System.out.printf("Got it. I've added this task:\n" +
//                "%s" +
//                "Now you have %d in the list.\n", newDeadline, list.size());
//    }
//
//    private static void addEvent(String info, ArrayList<Task> list) throws DukeEmptyStringException, DukeInsufficientInfoException{
//        if(info == null){
//            throw new DukeEmptyStringException();
//        }
//        if(!info.contains("/at")){
//            throw new DukeInsufficientInfoException();
//        }
//        String at, task;
//        try{
//            at = info.substring(info.indexOf("/at")+4);
//        }catch (StringIndexOutOfBoundsException e){
//            System.out.println("/at field is empty");
//            return;
//        }
//        try{
//            task = info.substring(0, info.indexOf("/at")-1);
//        }catch (StringIndexOutOfBoundsException e){
//            System.out.println("description field is empty");
//            return;
//        }
//
//        Event newEvent = new Event(task, at);
//        list.add(newEvent);
//        System.out.printf("Got it. I've added this task:\n" +
//                "%s" +
//                "Now you have %d in the list.\n", newEvent, list.size());
//    }
//
//    private static void list(ArrayList<Task> list){
//        System.out.println("Here are the tasks in your list:");
//        for(int i = 0; i< list.size(); i++){
//            System.out.printf("%d. %s", i+1, list.get(i).toString());
//        }
//    }
//
//    private static void mark(String info, ArrayList<Task> list){
//        int index;
//        try{
//            index = Integer.parseInt(info);
//        } catch (NumberFormatException e){
//            System.out.println("Mark should be followed by a number");
//            return;
//        }
//        try{
//            list.get(index-1).setDone(true);
//        }catch (IndexOutOfBoundsException f){
//            System.out.println("This task does not exist");
//            return;
//        }
//        System.out.printf("Nice! I've marked this task as done:\n [X] %s\n", list.get(index-1).getDescription());
//    }
//
//    private static void unmark(String info, ArrayList<Task> list){
//        int index;
//        try{
//            index = Integer.parseInt(info);
//        } catch (NumberFormatException e){
//            System.out.println("Unmark should be followed by a number");
//            return;
//        }
//
//        try{
//            list.get(index-1).setDone(false);
//        }catch (IndexOutOfBoundsException f){
//            System.out.println("This task does not exist");
//            return;
//        }
//        System.out.printf("OK, I've marked this task as not done yet:\n [ ] %s\n", list.get(index-1).getDescription());
//    }
//
//    private static String getCommand(String input){
//        if(input.contains(" ")){
//            return input.substring(0, input.indexOf(" "));
//        }else{
//            return input;
//        }
//    }
//
//    private static String getInfo(String input){
//        if(input.contains(" ")){
//            return input.substring(input.indexOf(" ")+1);
//        }else{
//            return null;
//        }
//    }
//}
