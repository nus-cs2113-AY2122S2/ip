package alexis.commands;

import alexis.main.Alexis;

public class MarkCommand extends Command{

    public static final String MARK_EXCEPTION_TEXT_ONE = " Oops!! Please input a task number after 'mark'";
    public static final String MARK_EXCEPTION_TEXT_TWO = " Oops!! Please input a valid task number to mark";

    public MarkCommand(String description) {
        try {
            int inputTaskNumber = Integer.parseInt(description) - 1;
            Alexis.tasks.getTask(inputTaskNumber).markAsDone();
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println(MARK_EXCEPTION_TEXT_ONE);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println(MARK_EXCEPTION_TEXT_TWO);
        }
    }

}
