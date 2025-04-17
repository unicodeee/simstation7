package prisonersDilemmaTournament.Strategies;

import prisonersDilemmaTournament.Strategy;

public class Cooperate extends Strategy {

    @Override
    public boolean cooperate() {
        return true;
    }

    @Override
    public String getStrategyName() {
        return "Cooperate";
    }
}
