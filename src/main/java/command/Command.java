package command;

/**
 * Abstract parent class for all types of commands
 */
// The Command class as well as all of its subclasses was created with reference to
// https://github.com/nus-cs2113-AY2122S2/personbook/tree/master/src/main/java/seedu/personbook/commands
public abstract class Command {
    public void separator(){
        System.out.println("================================================");
    }

    /**
     * Abstract method for all child classes to implement
     */
    public abstract void execute();
}
