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
    public String getHelp() {
        return "";
    }

    @Override
    public String about() {
        return "";
    }

    @Override
    public Model makeModel() {
        return null;
    }

    @Override
    public View makeView(Model m) {
        return null;
    }

    @Override
    public Command makeEditCommand(Model model, String type, Object source) {
        return null;
    }
}
