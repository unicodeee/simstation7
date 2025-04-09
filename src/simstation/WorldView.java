package simstation;

import mvc.Model;
import mvc.View;

import java.awt.*;

public class WorldView extends View {
    public WorldView(Model model) {
        super(model);
    }

    @Override
    public void paintComponents(Graphics gc) {
        super.paintComponents(gc);
        World world = (World) model;
        for (Agent a : world.getAgents()) {
            drawAgent(a, gc);
        }
    }

    public void drawAgent(Agent a, Graphics gc){
        //  The default implementation simply draws a diameter
        //  10 red filled oval at the Agent's location, but this can
        //  easily be overridden in a subclass.
        gc.setColor(Color.RED);
        gc.fillOval(a.getXc(), a.getYc(), 10, 10);
        System.out.println("Drawing agent " + a.getAgentName());
    }
}
