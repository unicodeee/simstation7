package simstation;

import mvc.Command;
import mvc.Model;
import mvc.Utilities;

public class StatsCommand extends Command {
    public StatsCommand(Model model) {
        super(model);
    }

    public void execute() {
        World w = (World) model;
        String info = w.getStatus();
        Utilities.inform(info);
    }
}
