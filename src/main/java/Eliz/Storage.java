package Eliz;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/*Deals with the loading of tasks from file and saving tasks in file*/
public class Storage {

    private static String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    };

    public static ArrayList<Task> load() throws FileNotFoundException {
        try {
            File f = new File(filePath);
            ArrayList<Task> listOfTasks = new ArrayList<>();
            Scanner s = new Scanner(f); //create scanner using file as a source
            Task input;
            while (s.hasNext()) { //while the file is not empty
                String line = s.nextLine();
                String[] arrayOfTaskComponents = line.split("|", 3);
                arrayOfTaskComponents[2] = arrayOfTaskComponents[2].substring(1);
                if (arrayOfTaskComponents[0].contains("T")) {
                    input = new Todo(arrayOfTaskComponents[2]);
                    if (arrayOfTaskComponents[1].contains("X")) {
                        input.setAsDone();
                    }
                } else if (arrayOfTaskComponents[0].contains("D")) {
                    input = new Deadline(arrayOfTaskComponents[2]);
                } else if (arrayOfTaskComponents[0].contains("E")) {
                    input = new Event(arrayOfTaskComponents[2]);
                } else {
                    input = null; //error
                }
                listOfTasks.add(input);
            }
            return listOfTasks;
        } catch (FileNotFoundException e) {
            System.out.println("File is not created, please try again!");
            return null;
        }
    }

    /*To append to file whenever there is a change in tasks*/
    public static void appendToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath,true);
        fw.write(textToAdd);
        fw.close();
    }

    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

}
