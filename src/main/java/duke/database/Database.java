package duke.database;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.IOException;

public interface Database {
    public ArrayList read(String fileName) throws FileNotFoundException;
    public void save(String filename, ArrayList items) throws IOException;
}
