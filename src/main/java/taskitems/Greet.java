public class Greet {
    public static void printDecoration(){
        System.out.println("-----------------------------------------------------");
    }
    public static void sayHi(){
        printDecoration();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");
    }
    public static void sayBye(){
        printDecoration();
        System.out.println("Bye! Hope to see you again soon!\n");
        printDecoration();
    }
}
