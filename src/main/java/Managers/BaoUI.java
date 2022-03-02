package Managers;

import java.io.IOException;
import java.util.Scanner;

import Commands.Command;
import Commands.UnknownCommand;

import Exceptions.BadDateTimeFormatException;
import Exceptions.NoDateTimeException;
import Exceptions.NoTaskDescriptionException;

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
            } catch (NoDateTimeException e) {
                printWithLine("When is this again?");
            } catch (BadDateTimeFormatException e) {
                printWithLine("");
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
