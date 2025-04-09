package simstation;

import mineField.MoveCommand;
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
        return null;
    }
}
