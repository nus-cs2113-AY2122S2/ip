import java.io.IOException;

public class Duke {
    private static Storage storage;
    private static Ui ui;

    public Duke(){
        storage = new Storage();
        ui = new Ui();
        try{
            storage.readData();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Run the UI and save the data.
     */
    public void run(){

        ui.run();

        try {
            storage.saveData();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }


}
