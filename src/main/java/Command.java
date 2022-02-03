import java.util.Scanner;

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

//    public static void add(String s){
//        Task.addTask(s);
//        PatternGenerator.generateDoubleLines();
//        System.out.println("    Added: "+ s);
//        PatternGenerator.generateDoubleLines();
//    }

    public static void addTodo(String s){
        Task todo = new Todo(s);
        Task.addTask(todo);
    }

    public static void addDDL(String s){
        String[] arrOfS = s.split("/");
        Task ddl = new Deadline(arrOfS[0], arrOfS[1]);
        Task.addTask(ddl);

    }

    public static void addEvent(String s){
        String[] arrOfS = s.split("/");
        Task event = new Event(arrOfS[0], arrOfS[1]);
        Task.addTask(event);

    }

    public static void list(){
        PatternGenerator.generateLine();
        System.out.println("Here are the tasks in your list.");
        Task.listTasks();
        PatternGenerator.generateLine();
//        Scanner sc = new Scanner(System.in);
//        String input = sc.nextLine();
//        while (!input.equals("exit")){
//            modifyList(input);
//            //sc.nextLine();
//            input = sc.nextLine();
//        }
    }

    public static void modifyList(String input){
        String[] words = input.split(" ");
        switch (words[0]){
        case "mark":
            mark(Integer.parseInt(words[1]));
            break;
        case "unmark":
            unmark(Integer.parseInt(words[1]));
            break;
        default:
            break;
        }
    }

    public static void mark(int index){
        PatternGenerator.generateLine();
        System.out.println("This task has been marked as done.");
        Task.mark(index);
        PatternGenerator.generateLine();
    }

    public static void unmark(int index){
        PatternGenerator.generateLine();
        System.out.println("This task has been marked as not done yet. Please remember to complete this task.");
        Task.unmark(index);
        PatternGenerator.generateLine();
    }
}
