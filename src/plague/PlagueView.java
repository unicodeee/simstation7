package plague;

import simstation.Agent;
import simstation.*;

import java.awt.*;

public class PlagueView extends WorldView {

    private simstation.World world;

    public PlagueView(PlagueSimulation model) {
        super(model);
        world = model;
    }

    @Override
    public void update() { repaint(); }

    @Override
    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        for (simstation.Agent a : world.getAgents()) {
            if((!(a instanceof ObserverAgent))) {
                if (((Host) a).isInfected() && ((Host) a).getTimeInfected() > PlagueSimulation.RECOVERY_TIME) {
                    if(((PlagueSimulation)world).isFatal())
                        ((Host) a).setAlive(false);
                    ((Host) a).setInfected(false);
                }

                if (((Host) a).isAlive() && !((Host)a).isInfected() ) {
                    for (simstation.Agent b : world.getAgents()) {
                        if((!(b instanceof ObserverAgent))) {
                            if (Math.abs(a.getXc() - b.getXc()) < 10 && Math.abs(a.getYc() - b.getYc()) < 10 && ((Host) b).isInfected()) {
                                ((Host) a).tryToInfect();
                            }
                        }
                    }
                }
            }
            drawAgent(a, gc);
        }
    }

    @Override
    public void drawAgent(Agent a, Graphics gc) {
        if (a instanceof Host host) {
            if (!host.isInfected() && host.isAlive())
                gc.setColor(Color.GREEN);
            else if (host.isInfected() && host.isAlive())
                gc.setColor(Color.RED);
            else
                gc.setColor(Color.BLACK);

            gc.fillOval(host.getXc(), host.getYc(), host.getAgentSize(), host.getAgentSize());
        }
    }


}
