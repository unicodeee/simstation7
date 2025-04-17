package plague;
import simstation.*;
import java.util.List;

public class PlagueSimulation extends World {

    public static int POPULATION_SIZE = 50;
    public static int VIRULENCE = 2;
    public static int RESISTANCE = 50;
    public static int INITIAL_INFECTED = 10;
    public static int RECOVERY_TIME = 0;
    public static boolean fatal = false;


    public void changeFatal() { fatal = !fatal; }
    public boolean isFatal() { return fatal; }
    public void setRecoveryTime(int value) { RECOVERY_TIME = value; }
    public void setPopulationSize(int value) { POPULATION_SIZE = value; }
    public void setInitialInfectedPercent(int value) { INITIAL_INFECTED = value; }


    public void populate() {
        for(int i = 0; i < POPULATION_SIZE; i++)
            addAgent(new Host());

        List<Agent> agents = getAgents();
        for(int i = 0; i < (INITIAL_INFECTED*POPULATION_SIZE)/100; i++) {
            if(!((agents.get(i)) instanceof ObserverAgent)) {
                ((Host)agents.get(i)).setInfected(true);
            }
            else
                i--;
        }
    }

    public void setFatal(boolean isFatal) {
        this.fatal = isFatal;
    }

    public static void main(String[] args) {
        PlaguePanel panel = new PlaguePanel(new PlagueFactory());
        panel.display();
    }

}
