package duke;

import duke.exception.DukeException;
import task.Deadlines;
import task.Events;
import task.Task;

public class Command {
    private String commandType;

    public Command(String type) {
        this.commandType = type;
    }

    public void execute(TaskList taskList) throws DukeException {
        String line = "____________________________________________________________\n";
        if(commandType.equals("list") && commandType.length() == 4) {
            System.out.println(line + taskList + line);
        }
        else if(commandType.contains("unmark ")) {
            String dummy = commandType.trim();
            int taskOrder;
            taskOrder = Integer.parseInt(dummy.substring(dummy.length() - 1));
//            System.out.println(String.format("qwqwqwqw: %d", taskOrder));
            Task thisTask = taskList.getTask(taskOrder);
            thisTask.markAsNotDone();
            System.out.println(line + "Nice! I've marked this task as not done yet:\n" +
                    taskList.getTask(taskOrder) + "\n");
        }
        else if(commandType.contains("mark ")) {
            String dummy = commandType.trim();
            int taskOrder;
            taskOrder = Integer.parseInt(dummy.substring(dummy.length() - 1));
//            System.out.println(String.format("qwqwqwqw: %d", taskOrder));
            Task thisTask = taskList.getTask(taskOrder);
            thisTask.markAsDone();
            System.out.println(line + "Nice! I've marked this task as done:\n" +
                    taskList.getTask(taskOrder) + "\n");
        }
        else if(commandType.contains("delete")) {
            String dummy = commandType.trim();
            if(dummy.length() == 6) {
                throw new DukeException("The description of delete operation cannot be empty");
            }
            int taskOrder = Integer.parseInt(dummy.substring(dummy.length() - 1));
            System.out.println(line + "Noted. I've removed this task:\n" + taskList.getTask(taskOrder) + "\n" +
                    "Now you have " + taskList.amountOfTasks() +" tasks in the list.\n" + line);
            taskList.delete(taskOrder);
        }
        else if(commandType.contains("deadline")) {
            String dummy = commandType.trim();
            if(dummy.length() == 8) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            int splitPosition = dummy.indexOf("/by");
            String description = dummy.substring(9, splitPosition);
            String by = dummy.substring(splitPosition + 4);
            Deadlines deadline = new Deadlines(description, by);
            taskList.add(deadline);
            System.out.println(line);
            System.out.println(deadline);
            System.out.println(String.format("Now you have %d tasks in you list.", taskList.size()));
            System.out.println(line);
        }
        else if(commandType.contains("event")) {

            String dummy = commandType.trim();
            if(dummy.length() == 5) {
                throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
            }
            int splitPosition = dummy.indexOf("/at");
            String description = dummy.substring(6, splitPosition);
            String duration = dummy.substring(splitPosition + 4);
            Events event = new Events(description, duration);
            taskList.add(event);
            System.out.println(line);
            System.out.println(event);
            System.out.println(String.format("Now you have %d tasks in you list.", taskList.size()));
            System.out.println(line);
        }
        else if(commandType.contains("todo")) {
            String dummy = commandType.trim();
            if(dummy.length() == 4) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            String description = dummy.substring(5);
            Task task = new Task(description);
            taskList.add(task);
            System.out.println(line);
            System.out.println(task);
            System.out.println(String.format("Now you have %d tasks in you list.", taskList.size()));
            System.out.println(line);
        }
        else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
