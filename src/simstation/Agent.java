package simstation;

import java.io.Serializable;

public class Agent implements Runnable, Serializable {
    private int xc;
    private int yc;
    private boolean paused = false;
    private boolean stopped = false;
    private String agentName;
    private Thread myThread;
    protected World world;

    public Agent(World world, String name, int x, int y) {
        this.world = world;
        this.agentName = name;
        this.xc = x;
        this.yc = y;
    }

    /**
     * Starts the agent thread
     */
    public void start() {
        if (myThread == null) {
            myThread = new Thread(this, agentName);
            myThread.start();
        }
    }

    /**
     * Stops the agent thread
     */
    public void stop() {
        stopped = true;
        if (myThread != null) {
            myThread.interrupt();
        }
    }

    /**
     * Pauses the agent
     */
    public void pause() {
        paused = true;
    }

    /**
     * Resumes the agent if paused
     */
    public void resume() {
        paused = false;
        synchronized (this) {
            this.notify();
        }
    }

    /**
     * Updates the agent's state
     */
    protected void update() {
        // Default implementation does nothing
        // Subclasses should override this
    }

    /**
     * The agent's run method
     */
    @Override
    public void run() {
        while (!stopped) {
            // Check if paused
            synchronized (this) {
                while (paused) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        if (stopped) return;
                    }
                }
            }

            // Perform the agent's update
            update();

            // Sleep for a bit
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                if (stopped) return;
            }
        }
    }

    // Getters and setters
    public int getXc() {
        return xc;
    }

    public void setXc(int xc) {
        this.xc = xc;
    }

    public int getYc() {
        return yc;
    }

    public void setYc(int yc) {
        this.yc = yc;
    }

    public boolean isPaused() {
        return paused;
    }

    public boolean isStopped() {
        return stopped;
    }

    public String getAgentName() {
        return agentName;
    }
}