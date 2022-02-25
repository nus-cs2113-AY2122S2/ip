package alexis.commands;

import static alexis.ui.Ui.showGoodbye;

public class ByeCommand extends Command{

    public ByeCommand() {
        showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
