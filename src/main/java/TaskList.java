import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TaskList {
    private final String SEPARATOR = "-------------------------------------------";
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void printTasks() {
        String entryNumber;
        System.out.println("Hemre are the tamsks im youmr limst:");
        for (int i = 0; i < tasks.size(); i++) {
            entryNumber = i + 1 + ". ";
            System.out.print(entryNumber);
            System.out.println(tasks.get(i));
        }
        System.out.println(SEPARATOR);
    }

    public void handleMark(String command) {
        try{
            int taskPosition = Integer.parseInt(command.substring(5)) - 1;
            Task task = tasks.get(taskPosition);
            task.setDone(true);
            System.out.println("Ok! I hamve markemd the tamsk:");
            System.out.println(task);
        } catch (NullPointerException e) {
            System.out.println("Error! You don't have that many tasks");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error! Please enter a valid task number");
        } catch (NumberFormatException e) {
            System.out.println("Error! Please input a number");
        } finally {
            System.out.println(SEPARATOR);
        }
    }

    public void handleUnmark(String command) {
        try{
            int taskPosition = Integer.parseInt(command.substring(7)) - 1;
            Task task = tasks.get(taskPosition);
            task.setDone(false);
            System.out.println("Oof! I hamve unmarkemd the tamsk: ");
            System.out.println(task);
        } catch (NullPointerException | NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("Error! Please enter a valid task number");
        } finally {
            System.out.println(SEPARATOR);
        }
    }

    public void addDeadline(String command) {
        try{
            int dueDateIndex = command.indexOf("/") + 1;
            if (dueDateIndex == 0) {
                throw new CheemsException();
            }
            String dueDate = command.substring(dueDateIndex);
            String description = command.substring(9, dueDateIndex - 1);
            Deadline newDeadline = new Deadline(description, dueDate);
            tasks.add(newDeadline);
            System.out.println("I hamve addemd: ");
            System.out.println(newDeadline);
        } catch (CheemsException e) {
            System.out.println("Error! Please follow the format given");
        } finally {
            System.out.println(SEPARATOR);
        }
    }

    public void addToDo(String command) {
        try {
            String description = command.substring(5);
            ToDo newToDo = new ToDo(description);
            tasks.add(newToDo);
            System.out.println("I hamve addemd: ");
            System.out.println(newToDo);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Error! Tomdo cannomt be empty");
        } finally {
            System.out.println(SEPARATOR);
        }
    }

    public void addEvent(String command) {
        try {
            int timingIndex = command.indexOf("/") + 1;
            if (timingIndex == 0) {
                throw new CheemsException();
            }
            String timing = command.substring(timingIndex);
            String description = command.substring(6, timingIndex - 1);
            Event newEvent = new Event(description, timing);
            tasks.add(newEvent);
            System.out.println("I hamve addemd: ");
            System.out.println(newEvent);
        } catch (CheemsException e) {
            System.out.println("Error! Please follow the format given");
        } finally {
            System.out.println(SEPARATOR);
        }
    }

    public void deleteTask(String command) {
        int taskNumber = Integer.parseInt(command.substring(7));
        Task task = tasks.remove(taskNumber - 1);
        System.out.println("Succesfully removed: ");
        System.out.println(taskNumber + ". " + task);
        System.out.println(SEPARATOR);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void findTask(String fullText) {
        String keyWord = fullText.substring(5);
        ArrayList<Task> filteredTask = (ArrayList<Task>) tasks.stream()
                .filter(t -> t.getDescription().contains(keyWord))
                .collect(Collectors.toList());
        System.out.println("Hemre are the matchimng tamsks in youmr limst: ");
        for (int i = 0; i < filteredTask.size(); i++) {
            System.out.print((i + 1) + ". ");
            System.out.println(filteredTask.get(i));
        }
        System.out.println(SEPARATOR);
    }
}
