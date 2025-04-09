package simstation;

import mvc.Model;
import mvc.ObserverAgent;

import java.util.ArrayList;
import java.util.List;

public class World extends Model {
    protected static final int SIZE = 500;
    private int clock = 0;
    private int alive = 0;
    private boolean observerAgentAdded = false;
    private List<Agent> agents = new ArrayList<>();

    /**
     * Adds an agent to the world
     * @param a The agent to add
     */
    public void addAgent(Agent a) {
        agents.add(a);
        alive++;
    }

    public List<Agent> getAgents() {
        return agents;
    }

    /**
     * Starts all agents in the world
     */
    public void startAgents() {
        if (!observerAgentAdded) {
            ObserverAgent obs = new ObserverAgent(this);
            obs.setAgentName("handsome");
            addAgent(obs);
            observerAgentAdded = true;
            obs.start();
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
    }

    /**
     * Pauses all agents in the world
     */
    public void pauseAgents() {
        for (Agent agent : agents) {
            agent.pause();
        }
    }

    /**
     * Resumes all paused agents in the world
     */
    public void resumeAgents() {
        for (Agent agent : agents) {
            agent.resume();
        }
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
        return "Clock: " + clock + ", Alive: " + alive;
    }

    /**
     * Updates statistics about the world
     */
    public void updateStatistics() {
        // Update relevant statistics
        // This might include recounting alive agents, etc.
        alive = agents.size(); // Simple implementation
        clock++;

        changed();
    }

    /**
     * Gets a neighboring agent within the specified radius
     * @param caller The agent looking for a neighbor
     * @param radius The search radius
     * @return The neighboring agent, or null if none found
     */
    public Agent getNeighbor(Agent caller, int radius) {
        // Implementation would depend on how agents are positioned
        // This is a placeholder implementation
        for (Agent agent : agents) {
            if (agent != caller) {
                // Calculate distance between caller and this agent
                // If within radius, return this agent
                // This would require position information on agents
                return agent; // Placeholder return
            }
        }
        return null;
    }
}