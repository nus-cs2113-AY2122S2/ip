public class Command {
    private String type;

    public Command(String type) {
        this.type = type;
    }

    public void execute(TaskList tasklist) {
        String line = "____________________________________________________________\n";
        if(type.equals("list")) {
            System.out.println(line + tasklist + line);
        }
        else if(type.contains("unmark ")) {
            String dummy = type.trim();
            int taskOrder;
            taskOrder = Integer.parseInt(dummy.substring(dummy.length() - 1));
//            System.out.println(String.format("qwqwqwqw: %d", taskOrder));
            Task thisTask = tasklist.getTask(taskOrder);
            thisTask.markAsNotDone();
            System.out.println(line + "Nice! I've marked this task as not done yet:\n" +
                    tasklist.getTask(taskOrder) + "\n");
        }
        else if(type.contains("mark ")) {
            String dummy = type.trim();
            int taskOrder;
            taskOrder = Integer.parseInt(dummy.substring(dummy.length() - 1));
//            System.out.println(String.format("qwqwqwqw: %d", taskOrder));
            Task thisTask = tasklist.getTask(taskOrder);
            thisTask.markAsDone();
            System.out.println(line + "Nice! I've marked this task as done:\n" +
                    tasklist.getTask(taskOrder) + "\n");
        }
        else {
            Task task = new Task(type);
            tasklist.add(task);
            System.out.println(line + "added: " + type + "\n" + line);
        }
    }
}
