package greed;

import mvc.Utilities;
import simstation.MobileAgent;

public class Cow extends MobileAgent {
    int energy = 100;
    int greediness = 25;
    @Override
    public void update() {
//        if (energy <= 0) {
//            // dies
//            notifyAll();
//            return;
//        }
//
//        if hasaccess to grass:
//            --> eat --> check no other cows eating --> no --> incrase energy, grass eatme
//                                                    --> yes --> decide to move, wait
//        // if greediness is high (near 100), wantsToMove is more likely
//        boolean wantsToMove = Utilities.rng.nextInt(100 + 1) <= greediness;

        // TO DO: change to cow move logic
        setHeading(Heading.random());
        int steps = Utilities.rng.nextInt(20) + 1;

//        setEnergy(energy - steps);
        move(steps);
    }

    public void setEnergy(int newEnergy) {
        if (0 <= newEnergy && newEnergy <= 100) {
            this.energy = newEnergy; // because  0 < energy < 100 required
        }
    }
}
