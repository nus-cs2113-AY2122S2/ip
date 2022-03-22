import java.util.ArrayList;

public class DukeList {
    private ArrayList<String> list = new ArrayList<String>();

    // Accessors
    public ArrayList<String> getList() {
        return this.list;
    }

    public boolean addText(String text) {
        // Adds a string to the list
        boolean isSuccess = list.add(text);

        if (isSuccess) {
            return true;
        }

        return false;
    }
}
