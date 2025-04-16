package simstation.prisonersDilemmaTournament;

import mvc.*;
import simstation.*;
import simstation.prisonersDilemmaTournament.Strategies.Cheat;
import simstation.prisonersDilemmaTournament.Strategies.Cooperate;
import simstation.prisonersDilemmaTournament.Strategies.RandomlyCooperate;
import simstation.prisonersDilemmaTournament.Strategies.Tit4Tat;

import java.util.List;

public class PrisonerSimulation extends World {
    private List<Prisoner> prisoners;

    public PrisonerSimulation() {
        super();
    }

    @Override
    public void populate() {
        for (int i = 0; i < 10; i++) {

            addAgent(new Prisoner(new Cooperate()));
            addAgent(new Prisoner(new Cheat()));
            addAgent(new Prisoner(new RandomlyCooperate()));
//            addAgent(new Prisoner(new Tit4Tat()));
//            Prisoner newPrisoner = new Prisoner(null);
//            newPrisoner.setStrategy(new Tit4Tat(newPrisoner));
//            addAgent(newPrisoner);
            addAgent(new Prisoner(new Tit4Tat()));

        }
    }

    @Override
    public String getStatus() {
        int agentCount = 0;
        int clock = getClock();
        double cooperateFitness = 0;
        double cheatFitness = 0;
        double randomlyCooperateFitness = 0;
        double tit4TatFitness = 0;

        for (Agent a : getAgents()) {
            if (!(a instanceof ObserverAgent)) {
                agentCount++;

                Prisoner p = (Prisoner) a;
                String strategyName = p.getStrategy().getStrategyName();
                switch (strategyName) {
                    case "Cooperate":
                        cooperateFitness += p.getFitness();
                        break;
                    case "Cheat":
                        cheatFitness += p.getFitness();
                        break;
                    case "RandomlyCooperate":
                        randomlyCooperateFitness += p.getFitness();
                        break;
                    case "Tit4Tat":
                        tit4TatFitness += p.getFitness();
                        break;
                }

            }
        }
        double cooperateAvg = cooperateFitness / 10;
        double cheatAvg = cheatFitness / 10;
        double randomlyCooperateAvg = randomlyCooperateFitness / 10;
        double tit4TatAvg = tit4TatFitness / 10;

        return "#agents: " + agentCount + "\n"+
                "#clock: " + clock + "\n" + "\n" +
                "Averages ----------\n" +
                "Cooperate: " + cooperateAvg + "\n" +
                "Cheat: " + cheatAvg + "\n" +
                "Randomly Cooperate: " + randomlyCooperateAvg + "\n" +
                "Tit for Tat: " + tit4TatAvg;
    }

//    @Override
//    public synchronized void updateStatistics() {
//        setClock(getClock() + 1);
//        for (Agent a : getAgents()) {
//            if (!(a instanceof ObserverAgent)) {
//                Prisoner p = (Prisoner) a;
//                p.
//            }
//        }
//    }

    public static void main(String[] args) {
        AppPanel panel = new WorldPanel(new PrisonerFactory());
        panel.display();
    }
}
