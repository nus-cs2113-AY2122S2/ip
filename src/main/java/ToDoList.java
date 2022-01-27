import java.util.ArrayList;

public class ToDoList {
    private String item;
    private static ArrayList<String> items = new ArrayList<String>();
    private static int count = 0;

    public static void displayAllTasks() {
        if (items.isEmpty()) {
            // do nothing
        } else {
            int count = 0;
            for (int i = 0; i < items.size(); ++i) {
                System.out.println(i + 1 + ". " + items.get(i));
            }
        }
    }

    public ToDoList(String item) {
        this.item = item;
        items.add(item);
        count++;
    }
}
