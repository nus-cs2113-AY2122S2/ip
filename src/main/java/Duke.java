import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        Command.greetUser();
        Scanner sc = new Scanner(System.in);
        String input;
        while (true){
            input = sc.nextLine();
            try {
                getCommand(input);
            }catch (IllegalCommandException e){
                PatternGenerator.generateArrows();
                System.out.println("OOPS!!! The description cannot be empty.");
                PatternGenerator.generateLine();
            }
            catch (NonExistentCommandException e){
                PatternGenerator.generateArrows();
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                PatternGenerator.generateLine();
            }

            if(input.equals("bye")){
                break;
            }

        }
    }

    public static void getCommand(String input) throws IllegalCommandException, NonExistentCommandException{
        String[] arrOfInput = input.split(" ",2);
        switch (arrOfInput[0]) {
        case "bye":
            Command.exit();
            break;
        case "list":
            Command.list();
            break;
        case "todo":
            if (arrOfInput.length < 2){
                throw new IllegalCommandException();
            }
            Command.addTodo(arrOfInput[1]);
            break;
        case "deadline":
            if (arrOfInput.length < 2){
                throw new IllegalCommandException();
            }
            try {
                Command.addDDL(arrOfInput[1]);
            }
            catch (IllegalFormatException e){
                System.out.println("OOPS!!! The format is not correct.");
            }
            break;
        case "event":
            if (arrOfInput.length < 2){
                throw new IllegalCommandException();
            }
            try {
                Command.addEvent(arrOfInput[1]);
            }
            catch (IllegalFormatException e){
                System.out.println("OOPS!!! The format is not correct.");
            }

            break;
        case "mark":
            if (arrOfInput.length < 2){
                throw new IllegalCommandException();
            }
            Command.mark(Integer.parseInt(arrOfInput[1]));
            break;
        default:
            throw new NonExistentCommandException();
        }
    }


}
