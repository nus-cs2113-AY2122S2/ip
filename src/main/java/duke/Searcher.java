package duke;

import static duke.UI.DASHED_LINE;

/**
 * Class used to search for a keyword inside the list of tasks.
 */
public class Searcher {

    /**
     * This method prints out any task whose description contains
     * the keyword we are looking for.
     * @param keyWord the string user searched for
     */
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
