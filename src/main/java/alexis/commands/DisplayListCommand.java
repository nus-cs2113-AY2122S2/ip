package alexis.commands;

import alexis.main.Alexis;

import static alexis.ui.Ui.DISPLAY_TASK_MESSAGE;
import static alexis.ui.Ui.EMPTY_LIST_MESSAGE;

public class DisplayListCommand extends Command{

    public DisplayListCommand() {
        try {
            int numOfTasks = Alexis.tasks.getListSize();
            System.out.println(DISPLAY_TASK_MESSAGE);
            for (int i = 0; i < numOfTasks; i++) {
                char typeOfTask = Alexis.tasks.getTask(i).typeOfTask();
                String statusOfTask = Alexis.tasks.getTask(i).getStatusIcon();
                String descriptionOfTask = Alexis.tasks.getTask(i).getFullDescription();
                System.out.println((i + 1) + ".[" + typeOfTask + "][" + statusOfTask + "] " + descriptionOfTask);
            }
        } catch (NullPointerException e) {
            System.out.println(EMPTY_LIST_MESSAGE);
        }
    }

}
