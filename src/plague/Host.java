package plague;

import mvc.Utilities;
import simstation.MobileAgent;

public class Host extends MobileAgent {

    private boolean infected;
    private long timeInfectedStart;
    private boolean alive;
    private boolean timeStarted;


    public Host() { infected = false; timeInfectedStart = 0; alive = true; timeStarted = false; }

    public void setInfected(boolean infected) {
        if (Math.random() * 100 < PlagueSimulation.VIRULENCE) {
            if (Math.random() * 100 >= PlagueSimulation.RESISTANCE) {
                infected = true;
            }
        }
        this.infected = infected;
    }
    public boolean isInfected() { return infected; }
    public void setAlive(boolean alive) { this.alive = alive; }
    public boolean isAlive() { return alive; }
    public long getTimeInfected() {
        if(infected) return world.getClock() - timeInfectedStart;
        else return 0;
    }

    public void startInfectionTime() {
        if (!timeStarted && world.getClock() > 0) {
            timeInfectedStart = world.getClock();
            timeStarted = true;
            System.out.println("infection timer started at: " + timeInfectedStart);
        }
    }


    public void tryToInfect() {
        if (!infected && Math.random() * 100 < PlagueSimulation.VIRULENCE) {
            if (Math.random() * 100 >= PlagueSimulation.RESISTANCE) {
                infected = true;
                startInfectionTime();
            }
        }
    }


    @Override
    public void update() {
        setHeading(Heading.random());
        int steps = Utilities.rng.nextInt(10) + 1;
        if(alive)
            move(steps);
    }
}
