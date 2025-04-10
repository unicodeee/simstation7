package simstation;

import mvc.Command;
import mvc.Model;

public class StartCommand extends Command {
    public StartCommand(Model model) {
        super(model);
    }

    public void execute() {
        World w = (World) model;
        w.startAgents();
        w.changed();
    }
}
