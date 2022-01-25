import java.util.Scanner;

public class Duke {
    public static class Task
    {
        String title;
        boolean isDone;

        public Task(String title) {
            this.title = title;
            this.isDone = false;
        }

        public boolean isDone() {
            return isDone;
        }

        public void setDone(boolean done) {
            isDone = done;
        }

        public String getStatusIcon() {
            return (isDone? "X" : " ");
        }

        public String getTitle() {
            return title;
        }
    }


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("    ____________________________________");
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?\n");

        Scanner input = new Scanner(System.in);
        String task = "";
        Task[] taskList = new Task [100];
        int index=0;
        while(!task.equals("bye")) {
            task = input.nextLine();
            if(!task.equals("bye")) {
                System.out.println("    ____________________________________");
                if(task.equals("list"))
                {
                    for(int i=0;i< taskList.length;i++) {
                        if(taskList[i] == null) break;
                        System.out.println("    " + (i + 1) + ". " + "[" + taskList[i].getStatusIcon()+"] " + taskList[i].getTitle());
                    }
                }

                else if(task.contains("mark ")&& !task.contains("unmark ")) {
                    task = task.replace("mark ","");
                    int i = Integer.parseInt(task)-1;
                    if(taskList[i]!=null) {
                        taskList[i].setDone(true);
                        System.out.println("Nice! I've marked this task as done:\n  " + "[" + taskList[i].getStatusIcon() + "] " + taskList[i].getTitle());
                    }
                    else
                        System.out.println("Please enter a valid task number");
                }
                else if(task.contains("unmark ")) {
                    task = task.replace("unmark ","");
                    int i = Integer.parseInt(task)-1;
                    if(taskList[i]!=null) {
                        taskList[i].setDone(false);
                        System.out.println("OK, I've marked this task as not done yet:\n  " + "[" + taskList[i].getStatusIcon() + "] " + taskList[i].getTitle());
                    }
                    else
                        System.out.println("Please enter a valid task number");
                }
                else
                {
                    taskList[index++] = new Task(task);
                    System.out.println("added: " + task);
                }
                System.out.println("    ____________________________________");
            }
        }
        System.out.println("    ____________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________");


    }
}
