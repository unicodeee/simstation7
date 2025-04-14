package greed;

import mvc.AppPanel;
import simstation.World;
import simstation.WorldPanel;

import static greed.Patch.patchSize;

public class Meadow extends World {

    int waitPenalty = 5;
    int moveEnergy = 10;
    int numCows = 50;
    int dim = SIZE / patchSize; // dim is how patch many per row

    public void populate() {
        for(int i = 0; i < dim; i++){
            for (int j = 0; j < dim; j++){
                addAgent(new Patch(i, j));
//                addAgent(new Drunk());
            }
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
