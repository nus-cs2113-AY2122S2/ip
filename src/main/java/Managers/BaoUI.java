package Managers;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import Commands.Command;
import Commands.UnknownCommand;

import Exceptions.*;

import static Constants.BaoConstants.LOGO;

import Functions.Parser;

import Interfaces.UI;

import static Functions.MessageDisp.printWithLine;

public class BaoUI implements UI {
    private Scanner in = new Scanner(System.in);
    private TaskManager taskManager;

    public BaoUI(String dirPath, String filePath) {
        taskManager = new TaskManager(new Storage(dirPath,filePath));
    }

    private void greet() {
        printWithLine("You have" + System.lineSeparator()
                        + LOGO + System.lineSeparator()
                        + "\t\t\t\t\tat your service",
                        "Hello there! Bao here!" + System.lineSeparator()
                        + "How can I help?");
    }

    private void serveUser(){
        Command command = new UnknownCommand();

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
            } catch (BadIndexException e) {
                printWithLine("I've checked and double checked. There is no such task.");
            }  catch (MaxTaskException e) {
                printWithLine("Hey! Calm down, Charlie Brown. You've too many on your plate right now.");
            } catch (Exception e) {
                printWithLine("AHH. Sorry, I glitched. Can you try that again?");
            }
        } while (!command.isExit());
    }

    private void farewell() {
        printWithLine("Alright, goodbye. See you later alligator!");
    }

    private void loadTasks() {
        try {
            taskManager.loadTasklist();
        } catch (IOException e) {
            System.out.println("Loading failed. Let's start on a clean slate.");
        }
    }

    public void initiateBao() {
        loadTasks();
        greet();
        serveUser();
        farewell();
    }
}
