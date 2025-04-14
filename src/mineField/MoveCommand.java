package mineField;

import mvc.*;

public class MoveCommand extends Command {
    private MineField model;
    private String direction;

    public MoveCommand(Model model, String direction) {
        super(model);
        this.model = (MineField) model;
        this.direction = direction;
    }
    public void execute ()  throws MineFieldException{
        model.move(direction);
        model.changed();
    }

}
