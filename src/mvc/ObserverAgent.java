package mvc;

import simstation.Agent;
import simstation.World;

public class ObserverAgent extends Agent {
    World world;
    public ObserverAgent(World world) {
        super();
        this.world = world;
    }

    @Override
    protected void update() {
        world.updateStatistics();
    }

}