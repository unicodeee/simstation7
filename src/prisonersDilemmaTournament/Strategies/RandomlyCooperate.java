package prisonersDilemmaTournament.Strategies;

import prisonersDilemmaTournament.Strategy;
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
