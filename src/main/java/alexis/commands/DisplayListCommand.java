package alexis.commands;

import alexis.exceptions.EmptyListException;
import alexis.main.Alexis;

import static alexis.ui.Ui.DISPLAY_TASK_MESSAGE;
import static alexis.ui.Ui.EMPTY_LIST_MESSAGE;

public class DisplayListCommand extends Command{

    public DisplayListCommand() {
        try {
            int numOfTasks = Alexis.tasks.getListSizeForDisplayList();
            System.out.println(DISPLAY_TASK_MESSAGE);
            for (int i = 0; i < numOfTasks; i++) {
                System.out.println((i + 1) + "." + Alexis.tasks.getTask(i).toString());
            }
        } catch (NullPointerException | EmptyListException e) {
            System.out.println(EMPTY_LIST_MESSAGE);
        }
    }

}
