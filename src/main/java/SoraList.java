import java.util.ArrayList;

public class SoraList {
    private ArrayList<String> list = new ArrayList<String>();

    // Accessors
    public ArrayList<String> getList() {
        return this.list;
    }

    public boolean addText(String text) {
        // Adds a string to the list
        boolean addSuccess = list.add(text);

        if (addSuccess) {
            return true;
        }

        return false;
    }


}
