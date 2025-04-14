package simstation;

import mvc.*;

import java.util.Objects;


public class WorldFactory implements AppFactory {
    @Override
    public String getTitle() {
        return "";
    }

    @Override
    public String[] getEditCommands() {
        return new String[]{"Start", "Pause", "Resume", "Stop", "Stats"};
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
        return "Simstation version 1.0. Copyright 2025 by Naina Talasu, Luis Archundia, & Jonathan Aye";
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

        if (Objects.equals(type, "Start")) {
            return new StartCommand(model);
        }
        else if(Objects.equals(type, "Pause")) {
            return new SuspendCommand(model);
        }
        else if(Objects.equals(type, "Resume")) {
            return new ResumeCommand(model);
        }
        else if(Objects.equals(type, "Stop")) {
            return new StopCommand(model);
        }
        else if(Objects.equals(type, "Stats")) {
            return new StatsCommand(model);
        }
        return null;
    }
}
