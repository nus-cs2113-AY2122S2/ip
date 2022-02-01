public class TaskManager {
    private static int maxTaskCount = 100;
    private int taskCount = 0;
    private Task[] taskList = new Task[maxTaskCount];

    public TaskManager() {
    }

    public void addTask(String userInput) {
        taskList[taskCount] = new Task(userInput, taskCount);
        printMessageForAdding(taskList[taskCount], taskCount);
        taskCount++;
    }

    public void addTodo(String userInput) {
        int indexOfDescription = userInput.indexOf(" ");
        String description = userInput.substring(indexOfDescription);
        taskList[taskCount] = new Todo(description, taskCount);
        printMessageForAdding(taskList[taskCount], taskCount);
        taskCount++;
    }

    public void addDeadline(String userInput) {
        int indexOfDescription = userInput.indexOf(" ");
        int indexOfSlash = userInput.indexOf("/");
        String description = userInput.substring(indexOfDescription, indexOfSlash);
        String by = userInput.substring(indexOfSlash + 4);
        taskList[taskCount] = new Deadline(description, taskCount, by);
        printMessageForAdding(taskList[taskCount], taskCount);
        taskCount++;
    }

    public void addEvent(String userInput) {
        int indexOfDescription = userInput.indexOf(" ");
        int indexOfSlash = userInput.indexOf("/");
        String description = userInput.substring(indexOfDescription, indexOfSlash);
        String at = userInput.substring(indexOfSlash + 4);
        taskList[taskCount] = new Event(description, taskCount, at);
        printMessageForAdding(taskList[taskCount], taskCount);
        taskCount++;
    }

    private void printMessageForAdding(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.print((taskCount + 1) + ".");
        System.out.println(task.toString());
        System.out.println("Now you have " + (taskCount + 1) + " tasks in the list.");
    }

    public void listTasks() {
        if (taskCount > 0) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println(i + 1 + taskList[i].toString());
            }
        } else if (taskCount == 0) {
            System.out.println("Wow, such empty");
        }
    }

    public void markTask(String taskNumber) {
        if (validateTaskNumber(taskNumber)) {
            int number = Integer.parseInt(taskNumber) - 1;
            if (number < taskCount) {
                taskList[number].setIsMarked();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(taskList[number].getId() + 1 + "[." + taskList[number].getStatusIcon() + "] "
                        + taskList[number].getDescription());
            }
        } else if (!validateTaskNumber(taskNumber)) {
            System.out.println("Oops! Seems like a number was not given or it is invalid. Try again!");
        }
    }

    public void unmarkTask(String taskNumber) {
        if (validateTaskNumber(taskNumber)) {
            int number = Integer.parseInt(taskNumber) - 1;
            if (number < taskCount) {
                taskList[number].unsetIsMarked();
                System.out.println("Nice! I've unmarked this task:");
                System.out.println(taskList[number].getId() + 1 + "[." + taskList[number].getStatusIcon() + "] "
                        + taskList[number].getDescription());
            }
        } else if (!validateTaskNumber(taskNumber)) {
            System.out.println("Oops! Seems like a number was not given or it is invalid. Try again!");
        }
    }


    private boolean validateTaskNumber(String taskNumber) {
        try {
            int number = Integer.parseInt(taskNumber);
            if (number < 1 || number > taskCount) {
                return false;
            } else {
                return true;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

