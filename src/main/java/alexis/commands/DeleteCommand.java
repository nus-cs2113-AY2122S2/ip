package alexis.commands;

import alexis.main.Alexis;

import static alexis.ui.Ui.printDeleteOutput;

public class DeleteCommand extends Command{

    public static final String DELETE_EXCEPTION_TEXT_ONE = "Oops!! Please input a task number after 'delete'";
    public static final String DELETE_EXCEPTION_TEXT_TWO = "Oops!! Please input a valid task number to delete";

    public DeleteCommand(String description) {
        try {
            int taskNumber = Integer.parseInt(description.trim()) - 1;
            printDeleteOutput(Alexis.tasks, Alexis.tasks.getListSize(), taskNumber);
            Alexis.tasks.remove(taskNumber);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println(DELETE_EXCEPTION_TEXT_ONE);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println(DELETE_EXCEPTION_TEXT_TWO);
        }
    }

}
