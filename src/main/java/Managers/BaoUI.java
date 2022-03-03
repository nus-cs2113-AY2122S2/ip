package Managers;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import Commands.Command;
import Commands.UnknownCommand;

import Components.Task;
import Exceptions.BadDateTimeFormatException;
import Exceptions.NoDateTimeException;
import Exceptions.NoKeywordException;
import Exceptions.NoTaskDescriptionException;
import Exceptions.MaxTaskException;

import static Constants.BaoConstants.LINE_BREAK;
import static Constants.BaoConstants.LOGO;

import Functions.Parser;

import Interfaces.UI;

import static Functions.MessageDisp.printWithLine;

/**
 * User interface of Bao.
 */
public class BaoUI implements UI {
    private Scanner in = new Scanner(System.in);
    private TaskManager taskManager;

    /**
     * Creates a <code>BAOUI</code> object using the specified task list text data file.
     *
     * @param dirPath Path of directory containing data file.
     * @param filePath Path of data file.
     * @see TaskManager TaskManager
     */
    public BaoUI(String dirPath, String filePath) {
        taskManager = new TaskManager(new Storage(dirPath,filePath));
    }

    /**
     * Displays greeting message.
     */
    private void greet() {
        printWithLine("You have" + System.lineSeparator()
                        + LOGO + System.lineSeparator()
                        + "\t\t\t\t\tat your service",
                        "Hello there! Bao here!" + System.lineSeparator()
                        + "How can I help?");
    }

    /**
     * Continuously accept and execute user commands until user quits app.
     */
    public void serveUser() {
        Command command = new UnknownCommand();

        greet();

        do {
            try {
                String userInput = in.nextLine();
                command = Parser.commandParse(userInput);
                command.execute(taskManager, this);
            } catch (NoTaskDescriptionException e) {
                printWithLine("What do you have to do?");
            } catch (NoDateTimeException | BadDateTimeFormatException e) {
                printWithLine("When is this again?");
            } catch (DateTimeParseException e) {
                printWithLine("Enter date and time in dd/mm/yyyy hhmm format!");
            }  catch (NumberFormatException e) {
                printWithLine("So close! You just need to provide me the task number.");
            } catch (IndexOutOfBoundsException e) {
                printWithLine("I've checked and double checked. There is no such task.");
            }  catch (MaxTaskException e) {
                printWithLine("Hey! Calm down, Charlie Brown. You've too many on your plate right now.");
            } catch (NoKeywordException e) {
                printWithLine("What are you looking for mate?");
            }catch (Exception e) {
                printWithLine("AHH. Sorry, I glitched. Can you try that again?");
            }
        } while (!command.isExit());
    }

    /**
     * Displays message for adding task to task list.
     *
     * @param task <code>Task</code> that was added.
     */
    public void newTaskMessage(Task task) {
        printWithLine("Yup yup yup, " + System.lineSeparator()
                + task.toString() + "," + System.lineSeparator()
                + "annnd there we go, it's been added!" + System.lineSeparator()
                + "You have " + taskManager.getNumTasks() + " tasks in the list.");
    }

    /**
     * Displays message for removing task from task list.
     *
     * @param deletedTask <code>Task</code> that was removed.
     */
    public void deleteTaskMessage(Task deletedTask) {
        printWithLine("You wanna see a magic trick? Now you see this: " + System.lineSeparator()
                + deletedTask.toString() + "," + System.lineSeparator()
                + "AND NOW, you don't!" + System.lineSeparator()
                + "Check behind your ear, for you still have " + taskManager.getNumTasks() + " tasks in the list.");
    }

    /**
     * Displays tasks from task list that had descriptions containing a keyword.
     *
     * @param taskStrings Array of matching tasks Strings.
     */
    public void findTasksMessage(ArrayList<String> taskStrings) {
        taskStrings.add(0,"Here are the tasks you asked for: ");
        printWithLine(taskStrings.toArray(new String[0]));
    }

    /**
     * Displays all tasks in task list.
     */
    public void listTasksMessage() {
        System.out.print(LINE_BREAK);
        System.out.println("Here are your tasks:");
        taskManager.listTasks();
        System.out.print(LINE_BREAK);
    }

    /**
     * Displays message for marking task from task list as done.
     *
     * @param taskString Updated task String.
     */
    public void markMessage(String taskString) {
        printWithLine(taskString, "Boom! Another task done already???");
    }

    /**
     * Displays message for marking task from task list as undone.
     *
     * @param taskString Updated task String.
     */
    public void unmarkMessage(String taskString) {
        printWithLine(taskString, "Oh oops, overlooked that one did we?");
    }

    /**
     * Displays error message for commands with no implementation.
     */
    public void unknownCommandMessage() {
        printWithLine("I do not understand that yet!");
    }

    /**
     * Displays exit message.
     */
    public void farewell() {
        printWithLine("Alright, goodbye. See you later alligator!");
    }
}
