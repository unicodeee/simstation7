package plague;

import mvc.Command;
import mvc.Model;
import mvc.View;
import simstation.*;


public class PlagueFactory extends WorldFactory {
    public Model makeModel() { return new PlagueSimulation(); }
    public String getTitle() { return "Plague Simulation"; }
    @Override
    public View makeView(Model m) {
        return new PlagueView((PlagueSimulation) m);
    }

    @Override
    public Command makeEditCommand(Model model, String type, Object source) {
        return switch (type) {
            case "Start" -> new StartCommand(model);
            case "Pause" -> new SuspendCommand(model);
            case "Resume" -> new ResumeCommand(model);
            case "Stop" -> new StopCommand(model);
            case "Stats" -> new StatsCommand(model);
            default -> null;
        };
    }


}
