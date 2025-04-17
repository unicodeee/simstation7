package simstation.prisonersDilemmaTournament.Strategies;

import simstation.prisonersDilemmaTournament.Strategy;

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
