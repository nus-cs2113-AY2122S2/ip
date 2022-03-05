package duke;

import static duke.UI.DASHED_LINE;

public class Searcher {

    static void searchInList(String keyWord){
        System.out.println(DASHED_LINE);
        System.out.print("\t Here are the matching tasks in your list:");
        for (int j = 0; j < TaskList.taskList.size(); j++){
            if (TaskList.taskList.get(j).getDescription().contains(keyWord)){
                System.out.print("\n");
                System.out.print("\t" + (j+1) + "." + TaskList.taskList.get(j).toString());
            }
        }
        System.out.println("\n" + DASHED_LINE);
    }
}
