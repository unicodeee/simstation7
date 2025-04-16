package simstation.prisonersDilemmaTournament.Strategies;

import simstation.prisonersDilemmaTournament.Prisoner;
import simstation.prisonersDilemmaTournament.Strategy;

public class Tit4Tat extends Strategy {

    public Tit4Tat() {
        super();
        setPrisoner(null);
    }

    public Tit4Tat(Prisoner p) {
        super();
        setPrisoner(p);
    }

    @Override
    public boolean cooperate() {
        return !myPrisoner.getPartnerCheated();
    }

    @Override
    public String getStrategyName() {
        return "Tit4Tat";
    }
}
