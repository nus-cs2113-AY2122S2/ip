package boba.exception;

import boba.command.Command;
import boba.response.BobaResponse;

/**
 * Utility Class that handles unexpected inputs from command
 */
public class ErrorHandler {

    /**
     * Print the respective error message based on the Command
     * @param operation The Command that was inputted
     */
    public static void printErrorMessage(Command operation) {
        BobaResponse.addResponse("Uh oh... Something went wrong!");
        switch (operation) {
        case TODO:
            BobaResponse.addResponse("Please provide a description");
            break;
        case DEADLINE:
            BobaResponse.addResponse("Please provide a description");
            BobaResponse.addResponse("And remember to include the /by command");
            break;
        case EVENT:
            BobaResponse.addResponse("Please provide a description");
            BobaResponse.addResponse("And remember to include the /at command");
            break;
        case MARK:
        case UNMARK:
        case DELETE:
            BobaResponse.addResponse("Please provide a valid number");
            break;
        case NONE:
        default:
            BobaResponse.addResponse("Invalid Command. Please try again!");
        }
        BobaResponse.printResponse();
    }

}
