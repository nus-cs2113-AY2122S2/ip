public class Command {
    public static void greetUser(){
        PatternGenerator.generateArrows();
        System.out.println("Hello! I'm your buddy Coeus. (•◡•) /");
        System.out.println("What are you going to do next?");
        PatternGenerator.generateLine();
//        System.out.println("Bye. Hope to see you again soon!");
//        System.out.println("____________________________________________________________");
    }

    public static void exit(){
        PatternGenerator.generateArrows();
        System.out.println("Goodbye! Can't wait to see you soon. :D");
        PatternGenerator.generateLine();
    }

    public static void echo(String s){
        PatternGenerator.generateArrows();
        System.out.println("    "+s);
        PatternGenerator.generateLine();
    }

    public static void add(String s){
        Task.addTask(s);
        PatternGenerator.generateDoubleLines();
        System.out.println("    Added: "+ s);
        PatternGenerator.generateDoubleLines();
    }

    public static void list(){
        Task.listTasks();
    }
}
