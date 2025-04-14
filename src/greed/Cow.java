package greed;

import mvc.Utilities;
import simstation.MobileAgent;

public class Cow extends MobileAgent {
    int energy = 100;
    int greediness = 25;
    @Override
    public void update() {
        // TO DO: change to cow move logic
        setHeading(Heading.random());
        int steps = Utilities.rng.nextInt(20) + 1;
        move(steps);
    }

    public void setEnergy(int newEnergy) {
        if (0 < newEnergy && newEnergy < 100) {
            this.energy = newEnergy; // because  0 < energy < 100 required
        }
    }
}
