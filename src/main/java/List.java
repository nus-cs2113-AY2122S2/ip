import java.util.Arrays;

public class List {
    private String[] list;
    private int numberOfItems;

    public String[] getList() {
        return list;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public List() {
        this.list = new String[100];
        this.numberOfItems = 0;
    }

    public void addToList(String line) {
        this.list[numberOfItems] = (line.substring(3)).trim();
        this.numberOfItems += 1;
    }

    public String[] listTheList() {
        String[] items = Arrays.copyOf(list, numberOfItems);

        for (int i = 1; i <= items.length; i++) {
            items[i - 1] = Integer.toString(i) + ". " + items[i - 1];
        }

        return items;
    }
}
