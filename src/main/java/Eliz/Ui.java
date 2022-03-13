package Eliz;

import java.io.*;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/** Represents the start of the program. Displays whatever is needed to be seen at the start of the program*/
public class Ui {

    public static void Ui() {};

    public static void botIntroduction() {
        String logo = " ____    __       __     ______ \n"
                + "|  __|  |  |     |  |   |___  /\n"
                + "| |__   |  |     |  |      / /  \n"
                + "| |__|  |  |     |  |     / /  \n"
                + "| |__   |  |__   |  |    / /___\n"
                + "|____|  |_____|  |__|   |______|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Eliz");
        System.out.println("I am your personal task list recorder! If you need help, type [/help]");
        System.out.println("What task would you like me to note down for you?");
    }

    public static void showLoadingError() {
        System.out.println("Sorry there has been some problems and Eliz cannot start up. Please restart the program!");
    }
}
