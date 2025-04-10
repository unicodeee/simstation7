package randomWalk;


import mvc.Utilities;
import simstation.Heading;
import simstation.MobileAgent;

class Drunk extends MobileAgent {
    public Drunk() {
        super();
        setAgentName("Agent " + String.valueOf(this.hashCode()));

    }

    public void update() {
        turn(Heading.random());
//        heading = Heading.random();
        int steps = Utilities.rng.nextInt(20) + 1;
        move(steps);
        System.out.printf("%s: %d, %d\n", getAgentName(), getXc(),  getYc()); // to do
    }
}

