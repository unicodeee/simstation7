package simstation;

import mvc.Model;
import mvc.Utilities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class World extends Model {
    protected static int SIZE = 500;
    private int clock = 0;
    private int alive = 0;
    private List<simstation.Agent> agents;
    private boolean statsUpdaterAdded = false;


    public World() {
        agents = new ArrayList<>();
    }

    public synchronized void addAgent(simstation.Agent a) {
        // Set random initial position if not already set
        if (!a.isPositionSet()) {
            a.setXc(Utilities.rng.nextInt(SIZE));
            a.setYc(Utilities.rng.nextInt(SIZE));
        }
        a.world = this;
        agents.add(a);
    }

    public synchronized void startAgents() {
        clock = 0;
        alive = agents.size();

        if (!statsUpdaterAdded) {
            addAgent(new ObserverAgent(this));
            statsUpdaterAdded = true;
        }

        agents.clear();
        statsUpdaterAdded = false;  // reset the flag if needed

        populate();

        for (simstation.Agent a : agents) {
            a.start();
        }
        changed();
    }


    public synchronized void stopAgents() {
        for (simstation.Agent a : agents) {
            a.stop();
        }
        changed();
    }

    public synchronized void pauseAgents() {
        for (simstation.Agent a : agents) {
            a.pause();
        }
        changed();
    }

    public synchronized void resumeAgents() {
        for (simstation.Agent a : agents) {
            a.resume();
        }
        changed();
    }

    public void populate() {
        //empty method to be overridden in subclasses
    }

    public String getStatus() {
        int agentCount = 0;
        for (simstation.Agent a : agents) {
            if (!(a instanceof ObserverAgent)) {
                agentCount++;
            }
        }
        return "#agents: " + agentCount + "\n"+
                "#living: " + alive + "\n" +
                "#clock: " + clock;
    }

    public synchronized void updateStatistics() {
        clock++;
        alive = 0;
        for (simstation.Agent a : agents) {
            if (!(a instanceof ObserverAgent)) {
                alive++;
            }
        }
        changed();
    }

    public simstation.Agent getNeighbor(simstation.Agent caller, int radius) {
        List<simstation.Agent> nearby = new ArrayList<>();
        for (simstation.Agent a : agents) { // will probably change implementation to match the professor's recommendation
            int dx = a.getXc() - caller.getXc();
            int dy = a.getYc() - caller.getYc();
            double distance = Math.sqrt(dx*dx + dy*dy);
            if (distance <= radius) {
                nearby.add(a);
            }
        }
        return nearby.get(Utilities.rng.nextInt(nearby.size()));
    }

    public int getClock() { return clock; }

    public synchronized List<simstation.Agent> getAgents() {
        return agents;
    }

    public Iterator<Agent> iterator() {
        return agents.iterator();
    }
}
