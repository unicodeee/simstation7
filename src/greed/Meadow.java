package greed;

import mvc.AppPanel;
import simstation.Agent;
import simstation.World;
import simstation.WorldPanel;

public class Meadow extends World {

    int waitPenalty = 5;
    int moveEnergy = 5;
    int numCows = 50;

    int greediness = 25;
    int patchSize = 25;
    int dim = SIZE / patchSize; // dim is how patch many per row

    public Patch getPatchAt(int cowX, int cowY) {
        for (Agent patch : getAgents()) {
            if (patch instanceof Patch
                    && patch.getXc() / patchSize == cowX / patchSize
                    && patch.getYc() / patchSize == cowY / patchSize) {
                return (Patch) patch;
            }
        }
        return null;
    }

    public int getMoveEnergy() {
        return moveEnergy;
    }

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
        for(int i = 0; i < numCows; i++){
                addAgent(new Cow());
        }
    }

    public int getDim() {
        return dim;
    }

    public void setGreediness(int greediness) {
        this.greediness = greediness;
    }

    public int getGreediness() {
        return greediness;
    }

    public static void main(String[] args) {
        AppPanel panel = new WorldPanel(new GreedFactory());

        panel.display();
    }
}
