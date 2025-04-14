package greed;

import mvc.Utilities;
import simstation.Agent;
import simstation.MobileAgent;

public class Patch extends Agent {

    static public int patchSize  = 10; // TO DO: change back to 10 later
    public int energy = 5;
    public int growBackRate = 1;

//    final Point position;

    public Patch(int x, int y) {
        super();
        setXc(x * patchSize);
        setYc(y * patchSize);
    }


    public void setEnergy(int newEnergy) throws Exception {
        if (0 < newEnergy && newEnergy < 100) {
            this.energy = newEnergy; // because  0 < energy < 100 required
        }
        throw new Exception("0 < energy < 100 required, get " + String.valueOf(newEnergy));
    }

    public void eatMe(Cow cow, int amt) {  // amt = amount
        // TO DO
    }


    MobileAgent.Heading heading = MobileAgent.Heading.random();
    int steps = Utilities.rng.nextInt(20) + 1;

    @Override
    public void update() {

    }
}
