package simstation;

import mvc.*;

public class MobileAgent extends Agent {
    private Heading heading;

    public MobileAgent() {
        super();
        // Initialize with a random heading
        heading = Heading.random();
    }

    /**
     * Moves the agent in its current heading by the specified number of steps
     * @param steps Number of steps to move
     */
    public void move(int steps) {
        // Calculate new position based on heading
        int newX = getXc();
        int newY = getYc();

        switch (heading) {
            case NORTH:
                newY -= steps;
                break;
            case EAST:
                newX += steps;
                break;
            case SOUTH:
                newY += steps;
                break;
            case WEST:
                newX -= steps;
                break;
        }

        // Ensure new position is within world bounds
        newX = Math.max(0, Math.min(World.SIZE - 1, newX));
        newY = Math.max(0, Math.min(World.SIZE - 1, newY));

        // Update position
        setXc(newX);
        setYc(newY);
    }

    /**
     * Changes the agent's heading
     * @param dir New heading direction
     */
    public void turn(Heading dir) {
        heading = dir;
    }

    public Heading getHeading() {
        return heading;
    }

    @Override
    protected void update() {
        // Mobile agents might move randomly in their update
        // This is a simple example of behavior
        if (Math.random() < 0.1) {
            // Occasionally change direction
            turn(Heading.random());
        }

        // Move 1-3 steps in the current direction
        move(1 + (int)(Math.random() * 3));
    }
}
