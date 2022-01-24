public class Duke {

    public static void displayGreeting() {
        String border = "_____________________________________________________";
        String logo = " _______     __   __  \n"
                + "|   _   |[x]| | / / [x]  \n"
                + "|  | |  || ||  / /  | |\n"
                + "|  | |  || ||  <    | |\n"
                + "|__/ |__||_||_| |_| |_|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(border);
        System.out.println("Hello! I'm Niki");
        System.out.println("What can I do for you?");
        System.out.println(border);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(border);
    }

    public static void main(String[] args) {
        displayGreeting();
    }
}
