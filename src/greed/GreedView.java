package greed;


import simstation.Agent;
import simstation.WorldView;

import java.awt.*;

public class GreedView extends WorldView {
    public GreedView(Meadow model) {
        super(model);
    }

    @Override
    public void drawAgent(Agent a, Graphics gc) {
        if ((a instanceof Patch)) {
            gc.setColor(Color.GREEN);
            gc.fillRect(a.getXc(), a.getYc(), Patch.patchSize, Patch.patchSize);
            gc.setColor(Color.WHITE);
            gc.drawRect(a.getXc(), a.getYc(), Patch.patchSize, Patch.patchSize);
        }
    }
}
