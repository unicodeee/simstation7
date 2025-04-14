package simstation;

import java.awt.*;
import mvc.*;

public class WorldView extends View {
    World world;
    private static int AGENT_SIZE = 10;
    public WorldView(Model model) {
        super(model);
        world = (World)model;
    }

    @Override
    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
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
        gc.fillOval(a.getXc(), a.getYc(), AGENT_SIZE, AGENT_SIZE);
        update();
        System.out.println("Drawing agent " + a.getAgentName());
    }
}
