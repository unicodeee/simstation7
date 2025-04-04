package simstation;

import mvc.Command;
import mvc.Model;

public class StopCommand extends Command {
    public StopCommand(Model model, Heading heading) {
        super(model);
    }

    public void execute() {
    }
}
