package duke.util;

public class Ui {
    public Ui(){
        PatternGenerator.generateArrows();
        System.out.println("Hello! I'm your buddy Coeus. (•◡•) /");
        System.out.println("What are you going to do next?");
        PatternGenerator.generateLine();
    }

    public void showLoadingError(){
        PatternGenerator.generateLine();
        System.out.println("I'm sorry, but the data is loaded unsuccessfully.");
        PatternGenerator.generateLine();
    }
}
