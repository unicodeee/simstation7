package simstation.prisonersDilemmaTournament;

import mvc.Utilities;
import simstation.*;

public class Prisoner extends MobileAgent{
    private int fitness = 0;
    private boolean partnerCheated = false;
    private Strategy strategy;

    public Prisoner(Strategy strategy) {
        super();
        this.strategy = strategy;
        this.strategy.setPrisoner(this);
    }

    public boolean cooperate(){
        return strategy.cooperate();
    }

    public void updateFitness(int amt){
        fitness += amt;
    }

    public int getFitness(){
        return fitness;
    }

    public void setPartnerCheated(boolean partnerCheated){
        this.partnerCheated = partnerCheated;
    }

    public boolean getPartnerCheated(){
        return partnerCheated;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void update() {
        // Picks a random neighbor to interact with
        Prisoner partner = (Prisoner) world.getNeighbor(this, 10);

        if (partner != null) {
            boolean myMove = cooperate();
            boolean partnerMove = partner.cooperate();

            // updates partnerCheated flag (from previous tournament round)
            setPartnerCheated(!partnerCheated);
            partner.setPartnerCheated(!myMove);

            //Tournament logic

            if(myMove && partnerMove){
                this.updateFitness(3);
                partner.updateFitness(3);
            }else if(!myMove && !partnerMove){
                this.updateFitness(5);
            }else if(myMove && !partnerMove){
                partner.updateFitness(5);
            }else{
                this.updateFitness(1);
                partner.updateFitness(1);
            }
        }

        setHeading(Heading.random());
        int steps = Utilities.rng.nextInt(20) + 1;
        move(steps);
    }
}
