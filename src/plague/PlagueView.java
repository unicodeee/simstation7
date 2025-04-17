package plague;

import greed.Meadow;
import greed.Patch;
import simstation.Agent;
import simstation.*;
import mvc.*;

import java.awt.*;
import java.util.ArrayList;

public class PlagueView extends WorldView {

    private simstation.World world;

    public PlagueView(PlagueSimulation model) {
        super(model);
        world = (World) model;
    }

    @Override
    public void update() { repaint(); }

    @Override
    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        for (simstation.Agent a : world.getAgents()) {
            if((!(a instanceof ObserverAgent))) {
                if (((Host) a).isInfected() && ((Host) a).getTimeInfected() > 1000) {
                    if(((PlagueSimulation)world).isFatal())
                        ((Host) a).setAlive(false);
                    ((Host) a).setInfected(false);
                }

                if (((Host) a).isAlive() && !((Host)a).isInfected() ) {
                    for (simstation.Agent b : world.getAgents()) {
                        if((!(b instanceof ObserverAgent))) {
                            /*System.out.println("ax: "+ a.getXc() + " bx: "+ b.getXc());
                            System.out.println("ay: "+ a.getYc() + " by: "+ b.getYc());
                            System.out.println("a infected : " + ((Host)a).isInfected());
                            System.out.println("b infected : " + ((Host)b).isInfected());*/
                            if (Math.abs(a.getXc() - b.getXc()) < 10 && Math.abs(a.getYc() - b.getYc()) < 2 && ((Host) b).isInfected()
                                    /*&& ((Host) b).isAlive()*/) {
                                //System.out.println("is here***************************");
                                ((Host) a).startInfectionTime();
                                ((Host) a).setInfected(true);
                            }
                            else {
                                System.out.println("else");
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

        if (!(a instanceof ObserverAgent)) {
            Host host = (Host) a;
            if(!host.isInfected() && host.isAlive())
                gc.setColor(Color.GREEN);
            else if(host.isInfected() && host.isAlive())
                gc.setColor(Color.RED);
            else
                gc.setColor(Color.BLACK);
            gc.fillOval(a.getXc(), a.getYc(),  a.getAgentSize(), a.getAgentSize());
        }

    }

}
