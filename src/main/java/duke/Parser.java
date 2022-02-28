package duke;

import command.*;

import java.util.ArrayList;

public class Parser {
    public Command parseCommand(String userInput, TaskList taskList){
        int taskIndex;
        ArrayList<Task> tasks = taskList.getTasks();
        if(userInput.startsWith("mark")){
            taskIndex = Integer.parseInt(userInput.substring(5));
            return new MarkCommand(taskList, taskIndex);
        }else if(userInput.startsWith("unmark")){
            taskIndex = Integer.parseInt(userInput.substring(7));
            return new UnmarkCommand(taskList,taskIndex);
        }else if(userInput.startsWith("delete")) {
            taskIndex = Integer.parseInt(userInput.substring(7));
            return new DeleteCommand(taskList, taskIndex);
        }else if(userInput.startsWith("find")){
            String keyword = userInput.split(" ")[1];
            return new FindCommand(taskList, keyword);
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
