package simstation;

import mvc.Model;
import mvc.View;

import java.awt.*;

public class WorldView extends View {
    public WorldView(Model model) {
        super(model);
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
    }

    public void drawAgent(Agent a, Graphics gc){
        //  The default implementation simply draws a diameter
        //  10 red filled oval at the Agent's location, but this can
        //  easily be overridden in a subclass.
    }
}
