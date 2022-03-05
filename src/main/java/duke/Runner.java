package duke;

import java.io.IOException;
import java.util.Scanner;

public class Runner {

    public static void run() throws IOException {
        String line;
        Scanner in = new Scanner(System.in);

        System.out.println("Hello from\n" + UI.logo);

        FileAccess.loadFromFile();

        System.out.println(UI.greeting);

        line = in.nextLine();

        while (!line.contains("bye")){
            try{
                Parser.processLine(line);

            } catch (DukeIllegalKeyword e){
                UI.printIllegalKeyword();

            } catch (DukeIllegalDescription e){
                UI.printIllegalDescription();
            } catch (Exception e){
                UI.printIllegalTerm();
            }

            line = in.nextLine();
        }
        System.out.print(UI.bye);
        FileAccess.saveToFile();
    }
}
