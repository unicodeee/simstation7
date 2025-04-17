package simstation;

import mvc.Model;
import mvc.View;

import java.awt.*;

public class WorldView extends View {
    private World world;
    public WorldView(Model model) {
        super(model);
        world = (World) model;
    }

    @Override
    public void update() {
        repaint();
    }

    @Override
    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        for (Agent a : world.getAgents()) {
            drawAgent(a, gc);
        }
    }

    public void drawAgent(Agent a, Graphics gc) {
        if (!(a instanceof ObserverAgent)) {
            gc.setColor(Color.RED);
            gc.fillOval(a.getXc(), a.getYc(),  a.getAgentSize(), a.getAgentSize());
        }
    }
}
