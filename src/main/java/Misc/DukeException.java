package Misc;

public class DukeException extends Exception {
    private String errorMsg;

    /**
     * Constructor for class DukeException.
     * @param mode Error mode.
     */
    public DukeException(int mode) {
        super();
        switch (mode) {
            case 0:
                errorMsg = "OOPS!!! I'm sorry, but I don't know what that means :-(";
                break;
            case 1:
                errorMsg = "OOPS!!! The description of a todo cannot be empty.";
                break;
            case 2:
                errorMsg = "OOPS!!! The description or date of a deadline cannot be empty.";
                break;
            case 3:
                errorMsg = "OOPS!!! The description or date of an event cannot be empty.";
                break;
            case 4:
                errorMsg = "OOPS!!! Failed to create or read save file.";
                break;
            case 5:
                errorMsg = "OOPS!!! The parameters provided are not enough.";
                break;
            case 6:
                errorMsg = "OOPS!!! The index provided is not valid.";
                break;
            default:
                break;
        }
    }

    /**
     * Returns message string.
     * @return  Message string.
     */
    @Override
    public String getMessage() {
        return errorMsg;
    }
}