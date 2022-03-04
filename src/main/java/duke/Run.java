package duke;

import java.util.Scanner;

public class Run {
    public static void run(){
        String line;
        Scanner in = new Scanner(System.in);

        System.out.println("Hello from\n" + UI.logo);

        FileAccess.loadFromFile();

        System.out.println(UI.greeting);

        line = in.nextLine();

        while (!line.contains("bye")){
            try{
                UI.processLine(line);

            } catch (DukeIllegalKeyword e){
                UI.printIllegalKeyword();

            } catch (DukeIllegalDescription e){
                UI.printIllegalDescription();
            }

            line = in.nextLine();
        }
        System.out.print(UI.bye);
        FileAccess.saveToFile();
    }
}
