import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String userInput = "";

        String[] textList = new String[100];
        int count = 0;

        String line = "------------------------------";
        System.out.println(line);
        System.out.println("Hello! I'm Duke.");
        System.out.println("What can I do for you?");
        System.out.println(line);

        while (!userInput.equals("bye")) {
            userInput = in.nextLine();
            String[] words = userInput.split(" ");
            switch (words[0]) {
            case "list":
                System.out.println(line);
                listTextList(textList,count);
                System.out.println(line);
                break;
            case "bye":
                System.out.println(line);
                System.out.println("Bye. Hope to see you again soon!");
                break;
            default:
                System.out.println(line);
                System.out.println("added: " + userInput);
                System.out.println(line);
                addToTextList(textList, userInput, count);
                count++;
                break;
            }
        }
    }

    private static void addToTextList(String[] textList, String userInput, int count) {
        textList[count] = userInput;
    }

    private static void listTextList(String[] textList, int count) {
        for (int i = 0;i < count;i++) {
            System.out.println(i + 1 + ". " + textList[i]);
        }
    }
}
