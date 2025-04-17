package prisonersDilemmaTournament.Strategies;

import prisonersDilemmaTournament.*;

public class Cheat extends Strategy {

    @Override
    public boolean cooperate() {
        return false;
    }

    @Override
    public String getStrategyName() {
        return "Cheat";
    }
}
