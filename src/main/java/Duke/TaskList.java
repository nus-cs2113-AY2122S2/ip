package Duke;


import static Duke.UserInput.*;
public class TaskList {
    /**
     * Deletes the chosen task
     * @throws InvalidInputException if task not exist
     */
    public static void deleteTask() throws InvalidInputException {
        var taskToBeDeleted = UserInput.userInput.get(Integer.parseInt(line.substring(1 + line.indexOf(" "))) - 1);
        if ((line.substring(1 + line.indexOf(" "))).trim().isEmpty()) throw new InvalidInputException();
        System.out.println("Noted. I've removed this task: ");
        System.out.println("[" + taskToBeDeleted.getIcon() + "]" + "[" + taskToBeDeleted.getStatusIcon()
                + "] " + taskToBeDeleted.description + taskToBeDeleted.showDate());
        inputCount--;
        System.out.println("Now you have " + inputCount + " tasks in the list.");
        userInput.remove(taskToBeDeleted);
    }

    /**
     * Adds the desired task
     * @throws InvalidInputException if task is empty
     */
    public static void addTodo() throws InvalidInputException {
        if ((line.substring(1 + line.indexOf(" "))).trim().isEmpty()) throw new InvalidInputException();
        userInput.add(inputCount, new Todo((line.substring(1 + line.indexOf(" ")))));
        inputCount++;
        System.out.println("Got it. I've added this task: ");
        System.out.println("[" + userInput.get(inputCount - 1).getIcon() + "]" + "["
                + userInput.get(inputCount - 1).getStatusIcon() + "] " + userInput.get(inputCount - 1).description);
        System.out.println("Now you have " + inputCount + " tasks in the list.");
    }

    /**
     * Adds deadline type of task
     * @throws InvalidInputException if deadline input is empty
     * @throws NumberFormatException if format of input deadline is incorrect
     */
    public static void addDeadline() throws InvalidInputException, NumberFormatException {
        if ((line.substring(1 + line.indexOf(" "))).trim().isEmpty()) throw new InvalidInputException();
        else if (!line.contains(" /by ")) throw new NumberFormatException();
        userInput.add(inputCount,
                new Deadline((line.substring(1 + line.indexOf(" "), line.indexOf("/by"))),
                        line.substring(line.indexOf("/by") + 4)));
        inputCount++;
        System.out.println("Got it. I've added this task: ");
        System.out.println("[" + userInput.get(inputCount - 1).getIcon() + "]"
                + "[" + userInput.get(inputCount - 1).getStatusIcon() + "] "
                + userInput.get(inputCount - 1).description + "(by: "
                + userInput.get(inputCount - 1).getBy() + ")");
        System.out.println("Now you have " + inputCount + " tasks in the list.");
    }

    /**
     * Adds event type of task
     * @throws InvalidInputException if event input is empty
     * @throws NumberFormatException if format of input event is incorrect
     */
    public static void addEvent() throws InvalidInputException, NumberFormatException {
        if (((line.substring(1 + line.indexOf(" "))).trim()).isEmpty()) throw new InvalidInputException();
        else if (!line.contains(" /at ")) throw new NumberFormatException();
        userInput.add(inputCount, new Event((line.substring(1 + line.indexOf(" ")
                , line.indexOf("/at"))), line.substring(line.indexOf("/at") + 4)));
        inputCount++;
        System.out.println("Got it. I've added this task: ");
        System.out.println("[" + userInput.get(inputCount - 1).getIcon() + "]"
                + "[" + userInput.get(inputCount - 1).getStatusIcon() + "] "
                + userInput.get(inputCount - 1).description + "(at: "
                + userInput.get(inputCount - 1).getAt() + ")");
        System.out.println("Now you have " + inputCount + " tasks in the list.");
    }

    /**
     * Prints all the tasks in the list
     */
    public static void printList(){
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < inputCount; i++) {
            System.out.println((i+1) +  ".[" +  UserInput.userInput.get(i).getIcon() +"] "
                    + "[" +  UserInput.userInput.get(i).getStatusIcon() +"] "
                    + UserInput.userInput.get(i).description
                    + UserInput.userInput.get(i).showDate());
        }
    }

    /**
     * Returns true if command is unmark
     * @return true if command is unmark
     */
    public static boolean isUnmark(){
        return Parser.getFirstWord().equalsIgnoreCase("unmark");
    }

    /**
     * Returns true if command is mark
     * @return true if command is mark
     */
    public static boolean isMark(){
        return Parser.getFirstWord().equalsIgnoreCase("mark");
    }

    /**
     * Marks the desired task as done
     */
    public static void markAsDone(){
        UserInput.valIndex = Integer.parseInt(line.substring(line.indexOf(" ") + 1));
        UserInput.userInput.get(UserInput.valIndex - 1).markAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("[" + UserInput.userInput.get(UserInput.valIndex - 1).getIcon()
                + "]" +"[" + UserInput.userInput.get(UserInput.valIndex - 1).getStatusIcon()
                + "] " + UserInput.userInput.get(UserInput.valIndex - 1).description
                + UserInput.userInput.get(UserInput.valIndex -1).showDate());
    }

    /**
     * Marks the desired task as not done
     */
    public static void markAsUndone(){
        UserInput.valIndex = Integer.parseInt(line.substring(line.indexOf(" ") + 1));
        UserInput.userInput.get(UserInput.valIndex - 1).markAsNotDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("[" + UserInput.userInput.get(UserInput.valIndex - 1).getIcon()
                + "]" + "[" + UserInput.userInput.get(UserInput.valIndex - 1).getStatusIcon()
                + "] " + UserInput.userInput.get(UserInput.valIndex - 1).description
                + UserInput.userInput.get(UserInput.valIndex -1).showDate());
    }

    /**
     * Finds the tasks that contain the keywards entered by user
     * @throws InvalidInputException if the entered string is empty
     */
    public static void findTask() throws InvalidInputException {
        if (((line.substring(1 + line.indexOf(" "))).trim()).isEmpty()) throw new InvalidInputException();
        String s = line.substring(1 + line.indexOf(" "));
        int indexCount = 0;
        for (int i = 0; i < inputCount; i++){
            if (userInput.get(i).description.contains(s)) {
                indexCount += 1;
                if (indexCount == 1) System.out.println("Here are the matching tasks in your list:");
                System.out.println((indexCount) +  ".[" +  UserInput.userInput.get(i).getIcon() +"] "
                        + "[" +  UserInput.userInput.get(i).getStatusIcon() +"] "
                        + UserInput.userInput.get(i).description
                        + UserInput.userInput.get(i).showDate());
            }
        }
        if (indexCount == 0) System.out.println("Sorry, none of the tasks match with your keywords!");
    }
}
