public class Alexis {
    private static String[] list = new String[100];
    private static int listCounter = 0;

    public static void greet() {
        System.out.println("-----------------------------------------------------");
        System.out.println("Hello! I'm Alexis, your trusty helper");
        System.out.println("What can I do for you?");
        System.out.println("-----------------------------------------------------");
    }
/*
    public static void echo(String input) {
        System.out.println("-----------------------------------------------------");
        System.out.println(input);
        System.out.println("-----------------------------------------------------");
    }
*/
    public static void addToList(String input){
        System.out.println("-----------------------------------------------------");
        System.out.println("added :" + input);
        list[listCounter] = input;
        listCounter++;
        System.out.println("-----------------------------------------------------");
    }

    public static void readList(){
        System.out.println("-----------------------------------------------------");
        for (int i = 0; i < listCounter; i++){
            System.out.println((i+1) + ". " + list[i]);
        }
        System.out.println("-----------------------------------------------------");
    }

    public static void exit() {
        System.out.println("-----------------------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("-----------------------------------------------------");
    }

}
