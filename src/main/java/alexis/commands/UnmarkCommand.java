package alexis.commands;

import alexis.main.Alexis;

public class UnmarkCommand extends Command {

    public static final String UNMARK_EXCEPTION_TEXT_ONE = " Oops!! Please input a task number after 'unmark'";
    public static final String UNMARK_EXCEPTION_TEXT_TWO = " Oops!! Please input a valid task number to unmark";

    public UnmarkCommand(String description) {
        try {
            int inputTaskNumber = Integer.parseInt(description) - 1;
            Alexis.tasks.getTask(inputTaskNumber).markAsUndone();
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println(UNMARK_EXCEPTION_TEXT_ONE);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println(UNMARK_EXCEPTION_TEXT_TWO);
        }
    }
}
