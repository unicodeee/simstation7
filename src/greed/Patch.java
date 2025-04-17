package greed;

import mvc.Utilities;
import simstation.Agent;

public class Patch extends Agent {

    static public int patchSize  = 50; // TO DO: change back to 10 later
    public int energy = 5;
    public int growBackRate = 1;

//    final Point position;

    public Patch() {
        super();
    }


    public void setEnergy(int newEnergy) {
        if (0 <= newEnergy && newEnergy <= 100) {
            this.energy = newEnergy; // because  0 < energy < 100 required
        }
    }

    public void eatMe(Cow cow, int amt) {  // amt = amount
        // TO DO
    }

    @Override
    public void update() {
//        this.setEnergy(energy + growBackRate);
    }
}
