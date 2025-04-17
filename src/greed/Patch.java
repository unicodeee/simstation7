package greed;

import simstation.Agent;

public class Patch extends Agent {

    public int energy = 100;

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
        this.setEnergy(energy + ((Meadow)world).getGrowBackRate());
    }
}
