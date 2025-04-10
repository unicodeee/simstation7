package simstation;

import java.awt.*;
import mvc.*;

public class WorldView extends View {
    public WorldView(Model model) {
        super(model);
    }

    @Override
    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        World world = (World) model;
        for (Agent a : world.getAgents()) {
            drawAgent(a, gc);
        }
    }

    @Override
    public void update() { repaint(); }

    public void drawAgent(Agent a, Graphics gc){
        //  The default implementation simply draws a diameter
        //  10 red filled oval at the Agent's location, but this can
        //  easily be overridden in a subclass.
        gc.setColor(Color.RED);
        gc.fillOval(a.getXc(), a.getYc(), 10, 10);
        update();
        System.out.println("Drawing agent " + a.getAgentName());
    }
}
