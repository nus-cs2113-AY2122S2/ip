import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Storage {
    private final Scanner fileIn;
    private final UI ui;

    /** Default file path */
    public static final String DEFAULT_STORAGE_FILEPATH = "duke.txt";

    public Storage(UI ui) throws FileNotFoundException{
        this.ui = ui;
        fileIn = new Scanner(new InputStreamReader(new FileInputStream(DEFAULT_STORAGE_FILEPATH), StandardCharsets.UTF_8));
    }

    public void load(TaskList taskList){
        String info, curLine;
        try{
            curLine = fileIn.nextLine();
        }catch(NoSuchElementException e){
            return;
        }
        while(curLine != null){
            try{
                info = curLine.substring(7);
            }catch(IndexOutOfBoundsException e){
                curLine = fileIn.nextLine();
                continue;
            }
            if(info.equals("")){
                curLine = fileIn.nextLine();
                continue;
            }
            if(info.contains("(at:")){
                taskList.addEventFromText(info);
            }else if(info.contains("(by:")){
                taskList.addDeadlineFromText(info);
            }else{
                taskList.addTodofromText(info);
            }
            try{
                curLine = fileIn.nextLine();
            }catch(NoSuchElementException e){
                return;
            }
        }
    }

    public void save(TaskList taskList){
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(DEFAULT_STORAGE_FILEPATH), StandardCharsets.UTF_8))) {
            for(Task t: taskList.getTasks()){
                writer.write(t.toString());
            }
        }catch(FileNotFoundException e){
            ui.showFileFailedToOpenMsg();
            return;
        }catch(IOException f){
            ui.showFileFailedToWriteMsg();
            return;
        }
        ui.showFileSaveSuccessfulMsg();
    }
}
