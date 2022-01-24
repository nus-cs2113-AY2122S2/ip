import java.util.Arrays;

public class List {
    private String[][] list;
    private int numberOfItems;

    public String[][] getList() {
        return list;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public List() {
        this.list = new String[2][100];
        this.numberOfItems = 0;
    }

    public void addToList(String line) {
        this.list[0][numberOfItems] = (line.substring(3)).trim();
        this.list[1][numberOfItems] = "[ ]";
        this.numberOfItems += 1;
    }

    public String[] listTheList() {
        String[] items = Arrays.copyOf(list[0], numberOfItems);

        for (int i = 1; i <= items.length; i++) {
            items[i - 1] = Integer.toString(i) + "." + list[1][i-1] + " " + items[i - 1];
        }

        return items;
    }

    public String mark(String line) {
        String ans = null;
        int addIndex = Integer.parseInt(line.substring(5)) - 1;

        if ((addIndex >= 0) && (addIndex < numberOfItems)) {
            this.list[1][addIndex] = "[X]";
            ans = this.list[1][addIndex] + " " + this.list[0][addIndex];
        }

        return ans;
    }

    public String unmark(String line) {
        String ans = null;
        int addIndex = Integer.parseInt(line.substring(7)) - 1;

        if ((addIndex >= 0) && (addIndex < numberOfItems)) {
            this.list[1][addIndex] = "[ ]";
            ans = this.list[1][addIndex] + " " + this.list[0][addIndex];
        }

        return ans;
    }
}
