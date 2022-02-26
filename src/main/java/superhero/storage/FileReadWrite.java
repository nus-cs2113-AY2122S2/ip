package Superhero.storage;

import Superhero.wordlist.VocabList;
import Superhero.wordlist.Vocabulary;
import Superhero.wordlist.ToLearn;
import Superhero.wordlist.Deadline;
import Superhero.wordlist.Event;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

/**
 * Contains methods that aid in the reading and writing of data in the txt file
 */
public class FileReadWrite {

    private static final Path FILE_PATH = Path.of("data", "VocabularySuperhero.txt");

    /**
     * Loads txt file to retrieve saved VocabList
     * @return VocabList
     */
    public static VocabList loadFile() {
        try {
            checkFile();
            List<String> vocabs = Files.readAllLines(FILE_PATH);
            VocabList loadList = new VocabList();
            for (String vocab : vocabs) {
                // Split each Vocabulary by "/"
                String[] parts = Arrays.stream(vocab.split("/")).map(String::trim).toArray(String[]::new);
                try {
                    Vocabulary t;
                    switch (parts[0]) {
                    case "T":
                        Vocabulary loadTolearnWord = new ToLearn(parts[2]);
                        //check if it is marked
                        if (parts[1].equals("X")) {
                            loadTolearnWord.setDone(true);
                        }
                        loadList.appendList(loadTolearnWord);
                        break;
                    case "D":
                        Vocabulary loadDeadlineWord = new Deadline(parts[2], parts[3]);
                        //check if it is marked
                        if (parts[1].equals("X")) {
                            loadDeadlineWord.setDone(true);
                        }
                        loadList.appendList(loadDeadlineWord);
                        break;
                    case "E":
                        Vocabulary loadEventWord = new Event(parts[2], parts[3]);
                        //check if it is marked
                        if (parts[1].equals("X")) {
                            loadEventWord.setDone(true);
                        }
                        loadList.appendList(loadEventWord);
                        break;
                    default:
                        throw new IndexOutOfBoundsException();
                    }
                } catch (Exception e) {
                    System.out.println("Incorrect formatting (Vocabulary not read): " + parts);
                }
            }
            System.out.println("File successfully read/created!");
            return loadList;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Saves VocabList back into text file
     * @param saveList
     */
    public static void saveFile(VocabList saveList) {
        try {
            checkFile();
            StringBuilder saveText = new StringBuilder();
            for (Vocabulary vocab : saveList.getList()) {
                if (vocab == null) {
                    break;
                }
                saveText.append(vocab.fileFormatString() + "\n");
            }
            Files.write(FILE_PATH, saveText.toString().getBytes());
            System.out.println("File successfully saved!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if file exists and creates files and directory if it does not
     */
    private static void checkFile() {
        try {
            Files.createDirectories(FILE_PATH.getParent());
            Files.createFile(FILE_PATH);
        } catch (FileAlreadyExistsException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
