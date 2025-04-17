package simstation;

import mvc.Command;
import mvc.Model;

public class StartCommand extends Command {
    public StartCommand(Model model) {
        super(model);
    }

    @Override
    public void execute() {
        System.out.println("StartCommand executing...");
        simstation.World world = (World) model;
        world.startAgents();
    }

}
