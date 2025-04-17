package simstation.prisonersDilemmaTournament;

public abstract class Strategy {
    protected Prisoner myPrisoner;

    public void setPrisoner(Prisoner p) {
        myPrisoner = p;
    }

    public abstract boolean cooperate();

    public abstract String getStrategyName();
}
