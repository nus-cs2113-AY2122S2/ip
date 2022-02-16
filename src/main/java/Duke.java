import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);
        String GREET = "Hello! I'm Duke\n" + "What can I do for you?\n";
        String EXIT = "Bye. Hope to see you again soon!\n";
        String BYE = "bye";
        String LIST = "list";
        String MARK = "mark";
        String TODO = "todo";
        String DEADLINE = "deadline";
        String EVENT = "event";
        String BLANK = "";

        System.out.println(GREET);

        String[] instructions = new String[100];

        Task task = new Task("hello");
        Task[] tasks = new Task[100];
        task.number = 0;

        Deadline deadline = new Deadline("return book", "holi");
        Event event = new Event("test", "7pm");

        for (int i = 0; i < 200; i++) { // can have 200 input lines (including wrong command)
            Scanner in = new Scanner(System.in);
            //System.out.print("Type something: ");
            task.instruction = in.nextLine();
            String instructionLine = task.instruction.replaceAll("todo|deadline|event", "");
            String updatedInstructionLine; //updated instruction line is in the form of [][] instructionline


            Task t = new Task("read book");
            String[] arrOfStr = task.instruction.split(" ", 50);
            String[] arrOfDeadline = instructionLine.split("/by ", 2);
            String[] arrOfEvent = instructionLine.split("/at ", 2);

            boolean isBye = arrOfStr[0].equals(BYE); //if true then exit
            boolean isList = arrOfStr[0].equals(LIST);
            boolean isMark = arrOfStr[0].equals(MARK);
            boolean isTodo = arrOfStr[0].equals(TODO);
            boolean isDeadline = arrOfStr[0].equals(DEADLINE);
            boolean isEvent = arrOfStr[0].equals(EVENT);


            String instructionNum;

            try {

                if (isBye) {

                    System.out.println(EXIT);
                    break;

                } else if (isList) {

                    System.out.println("Here are the task(s) in your list:");
                    for (int j = 1; j <= task.number; j++) {
                        System.out.print(j + ". ");
                        System.out.println(instructions[j - 1]);
                    }

                } else if (isMark) {

                    if (task.instruction.equals("mark") || task.instruction.equals("mark ")){
                        throw new DukeException("☹ OOPS!!! You have not entered the task number!");
                    }

                    System.out.println("Nice! I've marked this task as done:");
                    instructionNum = arrOfStr[1];
                    t.setStatusIcon(true);
                    String prefix = "  \\[T]\\[ ]";
                    instructions[Integer.parseInt(instructionNum) - 1] = instructions[Integer.parseInt(instructionNum) - 1].replaceAll(prefix, "  [T][X]");
                    System.out.println(instructions[Integer.parseInt(instructionNum) - 1]);

                } else if (isTodo) {

                    if (task.instruction.equals("todo") || task.instruction.equals("todo ")){
                        throw new DukeException("☹ OOPS!!! You have not entered your task!");
                    }

                    updatedInstructionLine = "  [T][ ]" + instructionLine;
                    tasks[task.number] = new Task(updatedInstructionLine);
                    instructions[task.number] = updatedInstructionLine;
                    task.number++;

                    System.out.println("Got it. I've added this task: ");
                    System.out.println(updatedInstructionLine);
                    System.out.println("Now you have " + task.number + " task(s) in the list.");

                } else if (isDeadline) {

                    if (task.instruction.equals("deadline") || task.instruction.equals("deadline ")){
                        throw new DukeException("☹ OOPS!!! You have not entered your task!");
                    }
                    if (arrOfDeadline.length == 1){
                        throw new DukeException("Hey! You have not entered the due date! hint: use '/by'");
                    }
                    deadline.instruction = arrOfDeadline[0];
                    deadline.setBy(arrOfDeadline[1]);
                    updatedInstructionLine = deadline.toString();
                    tasks[task.number] = new Task(updatedInstructionLine);
                    instructions[task.number] = updatedInstructionLine;
                    task.number++;

                    System.out.println("Got it. I've added this task: ");
                    deadline.getBy();
                    System.out.println(deadline);
                    System.out.println("Now you have " + task.number + " task(s) in the list.");

                } else if (isEvent) {

                    if (task.instruction.equals("event") || task.instruction.equals("event ")){
                        throw new DukeException("☹ OOPS!!! You have not entered your event!");
                    }
                    if (arrOfEvent.length == 1){
                        throw new DukeException("Hey! You have not entered the event date! hint: use '/at'");
                    }

                    event.instruction = arrOfEvent[0];
                    event.setAt(arrOfEvent[1]);
                    updatedInstructionLine = event.toString();
                    tasks[task.number] = new Task(updatedInstructionLine);
                    instructions[task.number] = updatedInstructionLine;
                    task.number++;

                    System.out.println("Got it. I've added this task: ");
                    event.getAt();
                    System.out.println(event);
                    System.out.println("Now you have " + task.number + " task(s) in the list.");

                } else {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means.");
                }

            } catch(DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
