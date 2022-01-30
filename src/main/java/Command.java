public class Command {
    private String commandType;

    public Command(String type) {
        this.commandType = type;
    }

    public void execute(TaskList tasklist) {
        String line = "____________________________________________________________\n";
        if(commandType.equals("list")) {
            System.out.println(line + tasklist + line);
        }
        else if(commandType.contains("unmark ")) {
            String dummy = commandType.trim();
            int taskOrder;
            taskOrder = Integer.parseInt(dummy.substring(dummy.length() - 1));
//            System.out.println(String.format("qwqwqwqw: %d", taskOrder));
            Task thisTask = tasklist.getTask(taskOrder);
            thisTask.markAsNotDone();
            System.out.println(line + "Nice! I've marked this task as not done yet:\n" +
                    tasklist.getTask(taskOrder) + "\n");
        }
        else if(commandType.contains("mark ")) {
            String dummy = commandType.trim();
            int taskOrder;
            taskOrder = Integer.parseInt(dummy.substring(dummy.length() - 1));
//            System.out.println(String.format("qwqwqwqw: %d", taskOrder));
            Task thisTask = tasklist.getTask(taskOrder);
            thisTask.markAsDone();
            System.out.println(line + "Nice! I've marked this task as done:\n" +
                    tasklist.getTask(taskOrder) + "\n");
        }
        else if(commandType.contains("deadline")) {
            String dummy = commandType.trim();
            int splitPosition = dummy.indexOf("/by");
            String description = dummy.substring(9, splitPosition);
            String by = dummy.substring(splitPosition + 4);
            Deadlines deadline = new Deadlines(description, by);
            tasklist.add(deadline);
            System.out.println(line);
            System.out.println(deadline);
            System.out.println(String.format("Now you have %d tasks in you list.", tasklist.size()));
            System.out.println(line);
        }
        else if(commandType.contains("event")) {
            String dummy = commandType.trim();
            int splitPosition = dummy.indexOf("/at");
            String description = dummy.substring(6, splitPosition);
            String duration = dummy.substring(splitPosition + 4);
            Events event = new Events(description, duration);
            tasklist.add(event);
            System.out.println(line);
            System.out.println(event);
            System.out.println(String.format("Now you have %d tasks in you list.", tasklist.size()));
            System.out.println(line);
        }
        else if(commandType.contains("todo")) {
            String dummy = commandType.trim();
            String description = dummy.substring(5);
            Task task = new Task(description);
            tasklist.add(task);
            System.out.println(line);
            System.out.println(task);
            System.out.println(String.format("Now you have %d tasks in you list.", tasklist.size()));
            System.out.println(line);
        }
    }
}
