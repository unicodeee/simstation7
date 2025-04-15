package greed;

import mvc.Utilities;
import simstation.Agent;

public class Patch extends Agent {

    static public int patchSize  = 50; // TO DO: change back to 10 later
    public int energy = 100;
    public int growBackRate = 1;

//    final Point position;
    public boolean beingGrazed = false;
    public Patch() {
        super();
    }


    public void setEnergy(int newEnergy) {
        if (newEnergy > 100) {
            this.energy = 100;
        }
        else if (newEnergy < 0) {
            this.energy = 0;
        }
        else this.energy = newEnergy;
    }

    public synchronized void eatMe(Cow cow, int amt) {
        // Only one cow at a time can execute this code per patch
        if (amt <= energy) {
            this.setEnergy(energy - amt);
            cow.setEnergy(cow.energy + amt);
        } else {
            cow.moveEnergy(); // Not enough grass, move
        }
    }


    @Override
    public void update() {
        this.setEnergy(energy + growBackRate);
    }
}
