package Managers;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import Commands.Command;
import Commands.UnknownCommand;

import Exceptions.BadDateTimeFormatException;
import Exceptions.NoDateTimeException;
import Exceptions.NoKeywordException;
import Exceptions.NoTaskDescriptionException;
import Exceptions.MaxTaskException;

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

        farewell();
    }

    /**
     * Displays exit message.
     */
    private void farewell() {
        printWithLine("Alright, goodbye. See you later alligator!");
    }
}
