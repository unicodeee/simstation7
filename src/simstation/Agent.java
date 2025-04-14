package simstation;

import java.io.Serializable;
import mvc.*;


public abstract class Agent implements Runnable, Serializable {
    private int xc;
    private int yc;
    private boolean paused = false;
    private boolean stopped = false;
    private String agentName;
    transient protected Thread myThread;
    protected World world;

    /**
     * Starts the agent thread
     */
    public void start() {
        if (myThread == null) {
            myThread = new Thread(this);
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
    public synchronized void resume() {
        paused = false;
        synchronized (this) {
            this.notify();
        }
    }

    /**
     * Updates the agent's state
     */
    // Default implementation does nothing
    // Subclasses should override this
    protected abstract void update();

    /**
     * The agent's run method
     */
    @Override
    public void run() {
        myThread = Thread.currentThread();
        onStart();
        while (!stopped) {
            // Check if paused
            try {
                update();
                Thread.sleep(10);
                checkPaused();
            } catch (InterruptedException e) {
                onInterrupted();
            }
        }
        onExit();
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

    public void setAgentName(String s) {
        agentName = s;
    }

    protected synchronized void onExit() {
    }

    protected synchronized void onStart() {
    }

    protected synchronized void onInterrupted() {
    }

    private synchronized void checkPaused() {
        try {
            while(!stopped && paused) {
                wait();
                paused = false;
            }
        } catch (InterruptedException e) {
            Utilities.error(e.getMessage());
        }
    }
}