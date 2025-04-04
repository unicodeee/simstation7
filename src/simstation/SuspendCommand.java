package simstation;

import mvc.Command;
import mvc.Model;

public class SuspendCommand extends Command {
    public SuspendCommand(Model model, Heading heading) {
        super(model);
    }

    public void execute() {
    }
}
