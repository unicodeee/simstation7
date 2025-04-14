package simstation.randomWalk;

import mvc.*;
import simstation.*;

class Drunk extends MobileAgent {

    public Drunk() {
        super();
    }

    public void update(Heading heading) {
        setHeading(Heading.random());
        int steps = Utilities.rng.nextInt(20) + 1;
        move(steps);
    }

}


