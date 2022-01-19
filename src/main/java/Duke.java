public class Duke {

    public static void greet(){
        System.out.println("__________________________________________________");
        System.out.println("Hello there! Bao here!");
        System.out.println("How can I help?");
        System.out.println("__________________________________________________");
    }

    public static void farewell(){
        System.out.println("Alright, goodbye. See you later alligator!");
        System.out.println("__________________________________________________");
    }

    public static void main(String[] args) {
        String logo = "  ____       _       ___  \n" +
                      " | __ )     / \\     / _ \\ \n" +
                      " |  _ \\    / _ \\   | | | | \n" +
                      " | |_) |  / ___ \\  | |_| | \n" +
                      " |____/  /_/   \\_\\  \\___/";

                System.out.println("Hello from\n" + logo);
        greet();
        farewell();
    }
}
