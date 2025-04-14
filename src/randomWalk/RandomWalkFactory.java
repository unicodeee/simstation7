package randomWalk;

import simstation.WorldFactory;
import mvc.*;

class RandomWalkFactory extends WorldFactory {
    public Model makeModel() {
        return new RandomWalkSimulation();
    }

    public String getTitle() {
        return "Random Walks";
    }
}
