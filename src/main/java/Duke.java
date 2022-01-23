import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] inputList = new String[100];
        int inputAmount = 0;

        initialDisplay();

        //get reply from user and change
        String reply = input.nextLine();

        while(!reply.equalsIgnoreCase("BYE")){

            if(reply.equalsIgnoreCase("LIST")){
                printInputs(inputList);
                reply = input.nextLine();
                continue;
            }
            //save it into the input list
            inputList[inputAmount++] = reply;

            System.out.println("____________________________________________________________");
            System.out.println("added: "+reply);
            System.out.println("____________________________________________________________");

            reply = input.nextLine();
        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void initialDisplay(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public static void printInputs(String[] inputs){
        System.out.println("____________________________________________________________");
        for(int i=1;i<=inputs.length && inputs[i-1]!=null;i++){
            System.out.println(i+". "+inputs[i-1]);
        }
        System.out.println("____________________________________________________________");
    }


}
