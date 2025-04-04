package mineField;

import mvc.Command;
import mvc.Model;
import mvc.Utilities;

public class MoveCommand extends Command {
    Heading heading;
    public MoveCommand(Model model, Heading heading) {
        super(model);
        this.heading = heading;
    }

    public void execute() {
        try {
            MineField minefield = (MineField) model;
            minefield.move(heading);
        } catch (MineField.GameIsFinishedException | MineField.OutOfBoundsException | MineField.IsAMineException | MineField.DestinationReachedException e) {
            Utilities.error(e.getMessage());
        }
    }

    public enum Heading {
        NORTH, NORTHWEST, NORTHEAST, WEST, EAST, SOUTH, SOUTHWEST, SOUTHEAST
    }
}
