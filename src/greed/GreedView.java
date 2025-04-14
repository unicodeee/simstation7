package greed;


import simstation.Agent;
import simstation.ObserverAgent;
import simstation.WorldView;

import java.awt.*;

public class GreedView extends WorldView {
    public GreedView(Meadow model) {
        super(model);
    }

    @Override
    public void drawAgent(Agent a, Graphics gc) {
        super.drawAgent(a, gc);

        if ((a instanceof Patch)) {

            int greenNess = (255/100) * ((Patch) a).energy;   // 255/100 helps map values in 100 range to 255 range for RBG value
            Color colorBasedOnEnergy = new Color(10, greenNess, 10);

            gc.setColor(colorBasedOnEnergy);
            gc.fillRect(a.getXc(), a.getYc(), Patch.patchSize, Patch.patchSize);
            gc.setColor(Color.WHITE);
            gc.drawRect(a.getXc(), a.getYc(), Patch.patchSize, Patch.patchSize);
        }
    }
}
