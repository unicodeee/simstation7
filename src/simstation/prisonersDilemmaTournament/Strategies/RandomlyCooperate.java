package simstation.prisonersDilemmaTournament.Strategies;

import simstation.prisonersDilemmaTournament.Strategy;
import mvc.Utilities;

public class RandomlyCooperate extends Strategy {

    @Override
    public boolean cooperate() {
        return Utilities.rng.nextBoolean();
    }

    @Override
    public String getStrategyName() {
        return "RandomlyCooperate";
    }
}
