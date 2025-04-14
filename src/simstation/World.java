package simstation;

import mvc.*;
import mvc.ObserverAgent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class World extends Model {
    protected static final int SIZE = 500;
    private int clock = 0;
    private int alive = 0;
    private boolean observerAgentAdded = false;
    private List<Agent> agents;

    public World(){
        this.agents = new ArrayList<>();
    }

    /**
     * Adds an agent to the world
     * @param a The agent to add
     */
    public void addAgent(Agent a) {
        //Randomly positions agents to ensure canvas is randomly populated
        if (a.getXc() == 0 && a.getYc() == 0) {
            a.setXc(Utilities.rng.nextInt(SIZE));
            a.setYc(Utilities.rng.nextInt(SIZE));
        }

        agents.add(a);
        alive++;
    }

    /**
     * Starts all agents in the world
     */
    public void startAgents() {
        //Adds an observer agent if not already present.
        if (!observerAgentAdded) {
            addAgent(new ObserverAgent(this));
            observerAgentAdded = true;
        }
        populate();
        for (Agent agent : agents) {
            agent.start();
        }
        changed();
    }

    /**
     * Stops all agents in the world
     */
    public void stopAgents() {
        for (Agent agent : agents) {
            agent.stop();
        }
        changed();
    }

    /**
     * Pauses all agents in the world
     */
    public void pauseAgents() {
        for (Agent agent : agents) {
            agent.pause();
        }
        changed();
    }

    /**
     * Resumes all paused agents in the world
     */
    public void resumeAgents() {
        for (Agent agent : agents) {
            agent.resume();
        }
        changed();
    }

    /**
     * Populates the world with agents
     */
    public void populate() {
        // Implementation would depend on specific requirements
        // for how agents should be created and placed

    }

    /**
     * Gets the status of the world
     * @return A string representing the current world status
     */
    public String getStatus() {
        int agentCount = 0;
        for (Agent agent : agents) {
            agentCount++;
        }
        return "#agents: " + agentCount + "\n" +
                "#alive: " + alive + "\n" +
                "#clock: " + clock;
    }

    /**
     * Updates statistics about the world
     */
    public void updateStatistics() {
        // Update relevant statistics
        // This might include recounting alive agents, etc.
//        alive = agents.size(); // Simple implementation
//        clock++;
//
//        changed();
        clock++;
        alive = 0;
        for (Agent a : agents) {
            if (!(a instanceof simstation.ObserverAgent)) {
                alive++;
            }
        }
        changed();
    }

    /**
     * Gets a neighboring agent within the specified radius
     * @param caller The agent looking for a neighbor
     * @param radius The search radius
     * @return The neighboring agent, or null if none found
     */
    public Agent getNeighbor(Agent caller, int radius) {
//        // Implementation would depend on how agents are positioned
//        // This is a placeholder implementation
//        for (Agent agent : agents) {
//            if (agent != caller) {
//                // Calculate distance between caller and this agent
//                // If within radius, return this agent
//                // This would require position information on agents
//                return agent; // Placeholder return
//            }
//        }
//        return null;
        List<Agent> nearby = new ArrayList<>();
        //May require some adjusting
        for (Agent a : agents) {
            int dx = a.getXc() - caller.getXc();
            int dy = a.getYc() - caller.getYc();
            double distance = Math.sqrt(dx*dx + dy*dy);
            if (distance <= radius) {
                nearby.add(a);
            }
        }
        return nearby.get(Utilities.rng.nextInt(nearby.size()));
    }

    public Iterator<Agent> iterator() {
        return agents.iterator();
    }

    public synchronized List<Agent> getAgents(){
        return agents;
    }
}