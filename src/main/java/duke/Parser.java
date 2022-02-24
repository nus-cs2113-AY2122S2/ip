package duke;
import duke.Storage;
import duke.tasklist.*;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Parser helps to make sense of the user input and uses methods from TaskList to do operations on the ArrayList of tasks.

 */
public class Parser {
    public static int parse(String userInput, TaskList l,Storage s) throws DukeException {
        String desc;
        int index;
        String by;
        switch (userInput.split(" ")[0]) {
            case "find":
                String keyword = userInput.split(" ")[1];
                l.find(keyword);
                return 1;


            case "todo":
                desc = userInput.substring(5);
                if (desc.isEmpty() || desc.isBlank()) {
                    throw new DukeException("Oops! Description cannot be empty.");
                }
                desc = l.addTodo(desc);
                try {
                    s.appendData(desc);
                } catch (IOException e) {
                    System.out.println("Error while appending to text file.");
                }

                return 1;


            case "event":
                index = userInput.indexOf("/");
                desc = userInput.substring(6,index);
                if (desc.isEmpty() || desc.isBlank()) {
                    throw new DukeException("Oops! Description cannot be empty.");
                }
                index++;
                by = userInput.substring(index);
                if (by.isEmpty() || by.isBlank()) {
                    throw new DukeException("Oops! You need a date for event.");
                }
                desc = l.addEvent(desc,by);
                try {
                    s.appendData(desc);
                } catch (IOException e) {
                    System.out.println("Error while appending to text file.");
                }
                return 1;


            case "deadline":
                index = userInput.indexOf("/");
                desc = userInput.substring(9,index);
                if (desc.isEmpty() || desc.isBlank()) {
                    throw new DukeException("Oops! Description cannot be empty.");
                }
                index++;
                by = userInput.substring(index);
                if (by.isEmpty() || by.isBlank()) {
                    throw new DukeException("Oops! You need a date for deadline.");
                }
                desc = l.addDeadline(desc,by);
                try {
                    s.appendData(desc);
                } catch (IOException e) {
                    System.out.println("Error while appending to text file.");
                }
                return 1;



            case "mark":
                String[] arrOfStr = userInput.split(" ", 0);
                String indexValue = arrOfStr[1];
                int indexValue2 = Integer.parseInt(indexValue) - 1;
                //index given is out of array size
                if (indexValue2 > l.getTaskSize()-1 || indexValue2 < 0){
                    throw new DukeException("Index given is out of bounds.");
                }
                ArrayList<Task> taskList = l.markTask(indexValue2);
                s.updateFile(taskList);
                return 1;

            case "unmark":
                arrOfStr = userInput.split(" ", 0);
                indexValue = arrOfStr[1];
                indexValue2 = Integer.parseInt(indexValue) - 1;
                //index given is out of array size
                if (indexValue2 > l.getTaskSize()-1 || indexValue2 < 0){
                    throw new DukeException("Index given is out of bounds.");
                }
                taskList = l.unmarkTask(indexValue2);
                s.updateFile(taskList);
                return 1;

            case "delete":
                arrOfStr = userInput.split(" ", 0);
                indexValue = arrOfStr[1];
                indexValue2 = Integer.parseInt(indexValue) - 1;
                //index given is out of array size
                if (indexValue2 > l.getTaskSize()-1 || indexValue2 < 0){
                    throw new DukeException("Index given is out of bounds.");
                }
                taskList = l.deleteTask(indexValue2);
                s.updateFile(taskList);
                return 1;

            case "list":
                l.printList();
                return 1;



            case "bye":
                return 0;

            default:
                throw new DukeException("Oops! I do not recognize this command. Please try again.");
        }
    }
}
