package plague;

import mvc.Utilities;
import simstation.MobileAgent;

public class Host extends MobileAgent {

    private boolean infected;
    private long timeInfectedStart;
    //private long timeInfectedStop;
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
    public void setTimeStarted(boolean ts) { timeStarted = ts; }
    public long getTimeInfected() {
        System.out.println("infected: " + infected);
        System.out.println("timeInfectedStart: " + timeInfectedStart);
        System.out.println("current time: " + world.getClock());
        if(infected) return world.getClock() - timeInfectedStart;
        else return 0;
        //else return timeInfectedStop - timeInfectedStart;
    }

    public void startInfectionTime() {
        timeInfectedStart = world.getClock();
        System.out.println("time started");
    }
    //public void stopInfectionTime() { timeInfectedStop = System.currentTimeMillis() / 1000; }


    @Override
    public void update() {
        setHeading(Heading.random());
        int steps = Utilities.rng.nextInt(10) + 1;
        if(alive)
            move(steps);
    }
}
