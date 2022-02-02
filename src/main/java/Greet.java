import java.util.Scanner;

public class Greet {
    public static void main(String[] args){
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);

        String[] input = new String[100];
        int idx = 0;
        input[0] = sc.nextLine();
        while(!input[idx].equals("bye")){
            if(input[idx].equals("list")){
                idx--;
                for(int i =0; i<=idx ;i++){
                    System.out.println("  " + (i+1) + ". " + input[i]);
                }
            }
            else {
                if (idx >= 100) {
                    break;
                }
                System.out.println("   added: " + input[idx]);
            }
            idx++;
            input[idx] = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon！");
    }
}
