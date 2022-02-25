package duke;

import errors.Errors;
import task.Task;

import java.util.Scanner;

import static duke.TaskList.*;

public class Parser {
    public static String getFirstWordOfCommand(String s) {
        int spaceIndex = s.indexOf(" ");
        if (spaceIndex == -1) {
            return s;
        }
        String firstWord = s.substring(0, spaceIndex);
        return firstWord.trim();
    }

    public static String getTaskDetailsForFind(String s) {
        int spaceIndex = s.indexOf(" ");
        String taskDetail = s.substring(spaceIndex);
        return taskDetail.trim();
    }

    public static int getTaskNumberArgument(String input) {
        int spaceIndex = input.indexOf(" ");
        String taskNum = input.substring(spaceIndex + 1);
        System.out.println(taskNum);
        return Integer.parseInt(taskNum.trim());
    }

}
