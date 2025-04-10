package simstation;

import mvc.*;

public class ResumeCommand extends Command {
    public ResumeCommand(Model model) {
        super(model);
    }

    public void execute() {
        World w = (World) model;
        w.resumeAgents();
    }
}
