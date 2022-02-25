package duke;

import duke.exception.DukeException;
import duke.exception.DukeExceptionCause;
import java.util.Scanner;

public class Ui {
    private Scanner in;
    public Ui() {
        in = new Scanner(System.in);
    }

    public void printHorizontalLine() {
        System.out.println("    ------------------------------------------------------------");
    }
    public String readUserInput(){
        String userInput = in.nextLine();
        return userInput;
    }
    public void showLoadingError(DukeException DE){
        if(DE.getExceptionCause() == DukeExceptionCause.FOLDERCREATIONFAIL){
            System.out.println("File was not found and the parent folder of the file is unable to be created.");
        }else if(DE.getExceptionCause() == DukeExceptionCause.FILECREATIONFAIL) {
            System.out.println("File was not found and the file is unable to be created.");
        }else{
            System.out.println("Failure to load data from file.");
        }
        return;
    }

    public void echoInvalidCommandMessage() {
        System.out.println("\t ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void echoMissingTaskNameMessage(String typeOfTask) {
        System.out.println("\t ☹ OOPS!!! The description of a " + typeOfTask + " cannot be empty.");
    }

    public void echoMissingTaskIndexMessage() {
        System.out.println("\t ☹ OOPS!!! The index of a task cannot be empty.");
    }

    public void echoTaskIndexOutOfRangeMessage() {
        System.out.println("\t ☹ OOPS!!! You have entered an invalid index of a task.");
    }

    public void showParsingError(DukeException de) {
        DukeExceptionCause causeOfException = de.getExceptionCause();
        switch (causeOfException) {
        case INVALIDCOMMAND:
            echoInvalidCommandMessage();
            break;
        case TODOTASKNAMEEMPTY:
            echoMissingTaskNameMessage("todo");
            break;
        case EVENTTASKNAMEEMPTY:
            echoMissingTaskNameMessage("event");
            break;
        case DEADLINETASKNAMEEMPTY:
            echoMissingTaskNameMessage("deadline");
            break;
        case EMPTYTASKINDEX:
            echoMissingTaskIndexMessage();
        case TASKINDEXOUTOFRANGE:
            echoTaskIndexOutOfRangeMessage();
        default:
            break;
        }
    }

    public void showAcknowledgementMessage(String message){
        System.out.println(message);
    }

    public void showFileWritingError(){
        System.out.println("Error Occurred!! Unable to write to file.");
    }

    public void showIndexOutOfBoundError(){
        System.out.println("Error Occurred!! Index provided is invalid. Kindly provide a valid one.");
    }

    public void printTaskAndIndex (int taskNumber,String taskDescription){
        System.out.println("\t " + taskNumber + "." + taskDescription);
    }

    public void echoFarewellGreeting() {
        System.out.println("    Good bye.See you soon :)) !");
    }

    public void showInvalidTaskTypeMessage(){
        System.out.println("Error Occurred!! Invalid Task Type found in file. Skipping that particular task.");
    }
}
