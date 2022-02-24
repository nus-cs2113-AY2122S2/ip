import DukeException.DukeInvalidInputException;

import java.util.ArrayList;

public class Parser {
    /**
     * Parse user input into readable command by Duke.
     * It returns an empty ArrayList if the user input is partly valid (only have command type without correct format).
     * @param userInput rough user input from user.
     * @throws DukeInvalidInputException if command type is invalid.
     */
    public static ArrayList<String> parseInput(String userInput) throws DukeInvalidInputException {
        String[] splitInput = userInput.split(" ");
        String upperChoice = splitInput[0].toUpperCase();
        ArrayList<String> parseInput = new ArrayList<>();

        switch(upperChoice){
        case "LIST":
        case "SAVE":
            parseInput.add(upperChoice);
            return parseInput;
        case "UNMARK":
        case "MARK":
        case "DELETE":
            try{
                int index = Integer.parseInt(splitInput[1]);
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("OOPS!!! The " + upperChoice + " index should not be empty!");
                return parseInput;
            } catch (NumberFormatException e) {
                System.out.println("OOPS!!! The " + upperChoice + " index you've entered is invalid!");
                return parseInput;
            }
            parseInput.add(upperChoice);
            parseInput.add(splitInput[1]);
            return parseInput;
        case "TODO":
        case "FIND":
            try{
                String content = splitInput[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("OOPS! The description of " + upperChoice + " should not be empty!");
                return parseInput;
            }
            parseInput.add(upperChoice);
            parseInput.add(userInput.substring(upperChoice.length()+1));
            return parseInput;
        case "DEADLINE":
        case "EVENT":
            String byat = upperChoice.equals("DEADLINE")? "/by":"/at";
            int startIndex = userInput.indexOf(byat);
            if(startIndex == -1){
                System.out.println("OOPS! The time of " + upperChoice + " should not be empty!");
                printTip(upperChoice);
                return parseInput;
            }
            try {
                String description = userInput.substring(upperChoice.length() + 1, startIndex - 1);
            }catch(IndexOutOfBoundsException e){
                System.out.println("OOPS! The description of " + upperChoice + " should not be empty!");
                printTip(upperChoice);
                return parseInput;
            }
            try{
                String time = userInput.substring(startIndex+4);
            }catch(IndexOutOfBoundsException e){
                System.out.println("OOPS! The time of " + upperChoice + " should not be empty!");
                printTip(upperChoice);
                return parseInput;
            }
            String description = userInput.substring(upperChoice.length() + 1, startIndex - 1);
            String time = userInput.substring(startIndex+4);
            parseInput.add(upperChoice);
            parseInput.add(description);
            parseInput.add(time);
            return parseInput;
        default:
            DukeInvalidInputException e = new DukeInvalidInputException("Invalid choice");
            throw e;
        }
    }

    /**
     * Printing tips for users to make them know better on the format of input command.
     * @param choice command type, e.g., DEADLINE, EVENT.
     */
    private static void printTip(String choice){
        System.out.println("You must follow the format strictly. For example: ");
        String byat = choice.equals("DEADLINE")? "/by":"/at";
        System.out.println(choice.toLowerCase()+" read a book "+byat+ " tomorrow");
    }
}
