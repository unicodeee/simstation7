package greed;

import simstation.MobileAgent;

public class Cow extends MobileAgent {
    int energy = 100;
    int greediness = 25;
    @Override
    public void update() {

    }

    public void setEnergy(int newEnergy) throws Exception {
        if (0 < newEnergy && newEnergy < 100) {
            this.energy = newEnergy; // because  0 < energy < 100 required
        }
        throw new Exception("0 < energy < 100 required, get " + String.valueOf(newEnergy));
    }
}
