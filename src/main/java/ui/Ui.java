package ui;
import common.Messages;

import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showGreetingMessage() {
        System.out.println(Messages.GREETING);
    }

    public void showByeMessage() {
        System.out.println(Messages.BYE);
    }

    public String getUserCommand() {
        String fullInputLine = sc.nextLine();
        return fullInputLine;
    }

}
