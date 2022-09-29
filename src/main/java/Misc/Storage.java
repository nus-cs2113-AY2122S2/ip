package Misc;

import Tasks.*;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    private final String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the saved task list.
     */
    public ArrayList<Task> load() throws DukeException {
        File saveFile = new File(filePath);
        saveFile.getParentFile().mkdirs();
        ArrayList<Task> loaded = new ArrayList<>();

        try {
            if (saveFile.createNewFile()) {
                System.out.println("Save file created: " + saveFile.getName());
            } else {
                System.out.println("Save file already exists, loading...");
                BufferedReader br = new BufferedReader(new FileReader(filePath));
                String line = br.readLine();
                while (line != null) {
                    String[] saved = line.split(",");
                    switch (saved[0]) {
                        case "D":
                            Deadline d = new Deadline(saved[2], saved[3]);
                            if (saved[1].equals("1")) {
                                d.setDone();
                            }
                            loaded.add(d);
                            break;
                        case "T":
                            ToDo t = new ToDo(saved[2]);
                            loaded.add(t);
                            break;
                        case "E":
                            Event e = new Event(saved[2], saved[3]);
                            if (saved[1].equals("1")) {
                                e.setDone();
                            }
                            loaded.add(e);
                            break;
                    }
                    line = br.readLine();
                }
                br.close();
            }
        } catch (IOException e) {
            throw new DukeException(4);
        }
        return loaded;
    }

    /**
     * Save the task list.
     * @param array Tasks list.
     */
    public void saveTasks(ArrayList<Task> array) throws DukeException {
        try {
            FileWriter saveFile = new FileWriter(filePath);
            for (int i = 0; i < array.size(); i++) {
                saveFile.write(array.get(i).toSave() + "\n");
            }
            saveFile.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving file.");
            e.printStackTrace();
        }
    }
}
