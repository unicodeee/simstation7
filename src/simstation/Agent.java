package simstation;

import java.io.Serializable;

public class Agent implements Runnable, Serializable {
    @Override
    public void run() {
        update();
    }

    public void update() {
    }

    public void resume() {
    }

    public void stop() {
    }

    public void start() {
    }

    public void pause() {
    }
}
