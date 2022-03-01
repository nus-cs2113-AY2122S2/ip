package duke;

import command.*;

import java.util.ArrayList;

/**
 * Parse user input and translate into suitable commands
 */
public class Parser {
    /**
     * parse user input to be returned with suitable command
     * @param userInput string that user has input to interact with application
     * @param taskList TaskList object which maintains the list of tasks in the application
     * @return suitable command for execution
     */
    public Command parseCommand(String userInput, TaskList taskList){
        int taskIndex;
        ArrayList<Task> tasks = taskList.getTasks();
        if(userInput.startsWith("mark")){
            taskIndex = Integer.parseInt(userInput.substring(5));
            return new MarkCommand(taskList, taskIndex);
        }else if(userInput.startsWith("unmark")){
            taskIndex = Integer.parseInt(userInput.substring(7));
            return new UnmarkCommand(taskList,taskIndex);
        }else if(userInput.startsWith("delete")){
            taskIndex = Integer.parseInt(userInput.substring(7));
            return new DeleteCommand(taskList, taskIndex);

        } else{
            switch(userInput){
            case "bye":
                return new ByeCommand();
            case "list":
                return new ListCommand(taskList);
            default:
                return new AddCommand(taskList, userInput);
            }
        }
    }


}
