package simstation;

import mvc.AppFactory;
import mvc.Command;
import mvc.Model;
import mvc.View;


public class WorldFactory implements AppFactory {
    @Override
    public String getTitle() {
        return "";
    }

    @Override
    public String[] getEditCommands() {
        return new String[0];
    }

    @Override
    public String[] getHelp() {
        return new String[]{
                    "Start --> Creates a new world",
                    "Stop --> Stops the current world",
                    "Pause --> Pauses all agents in current world",
                    "Resume --> Resumes all agents in current world",
                    "Stats --> Shows the # of agents, # of agents living, and time in seconds"
        };
    }

    @Override
    public String about() {
        return "";
    }

    @Override
    public Model makeModel() {
        return new World();
    }

    @Override
    public View makeView(Model m) {
        return new WorldView((World) m);
    }

    @Override
    public Command makeEditCommand(Model model, String type, Object source) {

        if (type == "Start") {
            return new StartCommand(model);
        }
        else if(type == "Pause") {
            return new SuspendCommand(model);
        }
        else if(type == "Resume") {
            return new ResumeCommand(model);
        }
        else if(type == "Stop") {
            return new StopCommand(model);
        }
        else if(type == "Stats") {
            return new StatsCommand(model);
        }
        return null;
    }
}
