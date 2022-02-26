package alexis.commands;

import alexis.storage.Storage;
import alexis.task.Task;
import alexis.taskList.TaskList;

import java.util.ArrayList;

public class FindCommand extends Command {
    protected String keyword;

    public FindCommand(String fullDescription) {
        keyword = fullDescription;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        ArrayList<Integer> indexesWithKeywordArr = new ArrayList<>(100);
        int keywordCounter = 0;
        int taskCounter = 0;
        for (Task task : taskList.taskArrayList) {
            if (task.getDescription().contains(keyword)) {
                indexesWithKeywordArr.add(taskCounter);
                keywordCounter++;
            }
            taskCounter++;
        }

        if (keywordCounter > 0) {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < indexesWithKeywordArr.size(); i++) {
                Task task = taskList.taskArrayList.get(indexesWithKeywordArr.get(i));
                System.out.println((i + 1) + "." + "[" + task.typeOfTask() + "][" + task.getStatusIcon()
                        + "] " + task.getFullDescription());
            }
        } else {
            System.out.println("There are no matching tasks in your list");
        }
    }
}