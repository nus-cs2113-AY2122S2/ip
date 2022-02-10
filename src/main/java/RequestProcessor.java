public class RequestProcessor {

<<<<<<< HEAD
    public static boolean filterRequest(String request) throws AdditionalException {
=======
    public static boolean filterRequest(String request) {
>>>>>>> 798b7cee3680aa228d25a6a3b90e13594330c8d5
        boolean isBye = false;
        String[] words = request.split(" ");
        String command = words[0].toLowerCase();
        switch(command) {
        case "bye":
            isBye = true;
            break;
        case "list":
            TaskManager.printList();
            break;
        case "mark":
            TaskManager.markItem(words);
            break;
        case "unmark":
            TaskManager.unmarkItem(words);
            break;
        case "todo":
            TaskManager.addTask(request, "todo");
            TaskManager.printConfirmationForAddingTasks();
            TaskManager.incrementIndex();
            break;
        case "deadline":
            TaskManager.addTask(request, "deadline", "/by");
            TaskManager.printConfirmationForAddingTasks();
            TaskManager.incrementIndex();
            break;
        case "event":
            TaskManager.addTask(request, "event", "/at");
            TaskManager.printConfirmationForAddingTasks();
            TaskManager.incrementIndex();
            break;
        default:
<<<<<<< HEAD
            throw new AdditionalException("OOPS!!! I'm sorry, but I don't know what that means :-(");
=======
            System.out.println("Bad command");
            break;
>>>>>>> 798b7cee3680aa228d25a6a3b90e13594330c8d5
        }
        return isBye;
    }
}
