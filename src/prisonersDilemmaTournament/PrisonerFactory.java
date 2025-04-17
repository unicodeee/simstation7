package prisonersDilemmaTournament;

import mvc.*;
import simstation.*;

public class PrisonerFactory extends WorldFactory {
    public Model makeModel() { return new PrisonerSimulation(); }
    public String getTitle() { return "Prisoners Dilemma Tournament"; }
}
