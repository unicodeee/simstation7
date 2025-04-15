package greed;

import mvc.AppPanel;
import simstation.World;
import simstation.WorldPanel;

public class Meadow extends World {

    int waitPenalty = 5;
    int moveEnergy = 10;
    int numCows = 50;
    int patchSize = 25;
    int dim = SIZE / patchSize; // dim is how patch many per row


    public int getPatchSize() {
        return patchSize;
    }

    public void populate() {

        // add patches
        for(int i = 0; i < dim; i++){
            for (int j = 0; j < dim; j++){
                Patch patch = new Patch();
                patch.setXc(i * patchSize);
                patch.setYc(j * patchSize);
                addAgent(patch);
            }
        }

        // add cows
        for(int i = 0; i < 50; i++){
                addAgent(new Cow());
        }
    }

    public int getDim() {
        return dim;
    }

    public static void main(String[] args) {
        AppPanel panel = new WorldPanel(new GreedFactory());

        panel.display();
    }
}
