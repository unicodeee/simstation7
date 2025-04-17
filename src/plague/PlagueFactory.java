package plague;


import mineField.MineField;
import mineField.MineFieldView;
import mvc.Model;
import mvc.View;
import randomWalks.RandomWalkSimulation;
import simstation.WorldFactory;

public class PlagueFactory extends WorldFactory {
    public Model makeModel() { return new PlagueSimulation(); }
    public String getTitle() { return "Plague Simulation"; }
    @Override
    public View makeView(Model m) {
        return new PlagueView((PlagueSimulation) m);
    }
}
