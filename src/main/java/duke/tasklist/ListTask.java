package duke.tasklist;

import duke.exception.AdditionalException;

public class ListTask extends TaskList{

    private static final String LINE = "-----------------------------";

    public static void printList() throws AdditionalException {
        if (listOfTasks.isEmpty()) {
            throw new AdditionalException("YAY!!! you do not have any tasks at the moment hehe");
        }
        for (int i = 0; i < listOfTasks.size(); i++) {
            int numbering = i + 1;
            System.out.println(numbering + ". " + listOfTasks.get(i));
        }
        System.out.println(LINE);
    }

}
