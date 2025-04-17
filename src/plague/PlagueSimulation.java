package plague;
import mvc.*;
import simstation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

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
        for(int i = 0; i < INITIAL_INFECTED; i++) {
            /*int temp = new Random().nextInt(agents.size());
           if(!(agents.get(temp) instanceof ObserverAgent)) {
               if (!((Host) agents.get(temp)).isInfected()) {
                   ((Host) agents.get(temp)).setInfected(true);
                   ((Host) agents.get(temp)).startInfectionTime();
                   //System.out.println("time started");
               }
               else
                   i--;
           } */
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

   /* @Override
    public String getStatus() {
        // Calculate current infection percentage
        int total = 0;
        int infected = 0;

        Iterator<Agent> it = iterator();
        while (it.hasNext()) {
            Agent agent = it.next();
            if (agent instanceof Plague) {
                total++;
                if (((Plague) agent).isInfected()) {
                    infected++;
                }
            }
        }
*/

}
