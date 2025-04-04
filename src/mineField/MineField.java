package mineField;

import mvc.Model;
import mvc.Utilities;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class MineField extends Model {

    private final int gridViewSize = 20;  // grid to be displayed is 20 x 20
    private final int percentMined = 5;

    private boolean done = false;

    private Point currentPosition = new Point(0,0);

    private final int mineAmount = gridViewSize * gridViewSize * percentMined / 100;
    private Set<Point> mines = new HashSet<>(mineAmount);
    private List<Point> path = new ArrayList<>();

    private boolean showMineCount = false; // DEBUG: flip to true to debug
    private boolean showMineSolution = false;

    private final Color color = Color.GRAY;
    private final Color mineColor = Color.RED;
    private final Color pathColor = Color.LIGHT_GRAY;
    private final Color currentPositionColor = Color.LIGHT_GRAY;

    public boolean showSolution() {
        return showMineCount;
    }


    public Color getColor() {
        return color;
    }

    public Color getMineColor() {
        return mineColor;
    }

    public Color getPathColor() {
        return pathColor;
    }

    public Color getCurrentPositionColor() {
        return currentPositionColor;
    }

    public int getGridViewSize() {
        return gridViewSize;
    }

    public Set<Point> getMines() {
        return mines;
    }

    public List<Point> getPath() {
        return path;
    }
    public Point getCurrentPosition() {
        return currentPosition;
    }

    public MineField() {
        super();
        seedMines();
        path.add(new Point(0, 0));
    }

    public boolean isAMine(Point point) {
        return mines.contains(point);
    }

    public boolean isReached(Point point) {
        return path.contains(point);
    }

    public int getNumNeighboringMines(Point current) {
        int count = 0;
        Point tempt;

        tempt = new Point(current);
        tempt.x -= 1;
        if (isAMine(tempt)) {
            count++;
        }

        tempt = new Point(current);
        tempt.x += 1;
        if (isAMine(tempt)) {
            count++;
        }

        tempt = new Point(current);
        tempt.y -= 1;
        if (isAMine(tempt)) {
            count++;
        }

        tempt = new Point(current);
        tempt.y += 1;
        if (isAMine(tempt)) {
            count++;
        }

        tempt = new Point(current);
        tempt.x -= 1;
        tempt.y -= 1;
        if (isAMine(tempt)) {
            count++;
        }

        tempt = new Point(current);
        tempt.x += 1;
        tempt.y += 1;
        if (isAMine(tempt)) {
            count++;
        }

        tempt = new Point(current);
        tempt.x += 1;
        tempt.y -= 1;
        if (isAMine(tempt)) {
            count++;
        }

        tempt = new Point(current);
        tempt.x -= 1;
        tempt.y += 1;
        if (isAMine(tempt)) {
            count++;
        }

        return count;
    }

    public Point seedMine() {
        Random random = new Random();
        int row = random.nextInt(gridViewSize);
        int col = random.nextInt(gridViewSize);
        Point p = new Point(row, col);


        while (mines.contains(p)) { // handle overlapped coordinates
            p.x += 1;
            if (p.x > gridViewSize) {
                p.x = p.x % gridViewSize;
                p.y += 1;
                if (p.y > gridViewSize) {
                    p.y = p.y % gridViewSize;
                }
            }
        }
        return p;
    }

    public void seedMines() {
        mines.clear();

        Point edgeCase1 = new Point(0, 0);
        Point edgeCase2 = new Point(19, 19);
        for (int i = 0; i < mineAmount; i++) {
            mines.add(seedMine());
        }

        if (mines.contains(edgeCase1)) {
            mines.remove(edgeCase1);
        }
        if (mines.contains(edgeCase2)) {
            mines.remove(edgeCase2);
        }
    }

    public boolean showMineSolution() {
        return showMineSolution;
    }

    public void move(MoveCommand.Heading heading) throws GameIsFinishedException, OutOfBoundsException, IsAMineException, DestinationReachedException {
        if (done) {
            throw new GameIsFinishedException("Cannot move, game is finished.");
        }

        Point newPosition = new Point(currentPosition);

        switch (heading) {
            case NORTH: newPosition.y -= 1; break;
            case NORTHWEST:
                newPosition.y -= 1;
                newPosition.x -= 1;
                break;
            case NORTHEAST:
                newPosition.y -= 1;
                newPosition.x += 1;
                break;
            case WEST: newPosition.x -= 1; break;
            case EAST: newPosition.x += 1; break;
            case SOUTHWEST:
                newPosition.y += 1;
                newPosition.x -= 1;
                break;
            case SOUTH: newPosition.y += 1; break;
            case SOUTHEAST:
                newPosition.y += 1;
                newPosition.x += 1;
                break;
        }

        if (newPosition.x > gridViewSize - 1 || newPosition.x < 0 || newPosition.y > gridViewSize - 1 || newPosition.y < 0) {
            throw new OutOfBoundsException("Out of bounds");
        }
        currentPosition = newPosition;
        if (isAMine(currentPosition)) {
            done = true;
            throw new IsAMineException("Stepped on a mine, you lost!");
        }
        else if (currentPosition.x == gridViewSize - 1 && currentPosition.y == gridViewSize - 1) {
            done = true;
            throw new DestinationReachedException("Destination reached, you won!");
        }

        if (!isAMine(currentPosition)) {
            path.add(new Point(currentPosition));
        }
        changed();
    }

    public class GameIsFinishedException extends Exception {
        public GameIsFinishedException(String message) {
            super(message);
        }
    }
    public class OutOfBoundsException extends Exception {
        public OutOfBoundsException(String message) {
            super(message);
        }
    }
    public class IsAMineException extends Exception {
        public IsAMineException(String message) {
            super(message);
            showMineSolution = true;
            // changed();
        }
    }
    public class DestinationReachedException extends Exception {
        public DestinationReachedException(String message) {
            super(message);
            showMineSolution = true;
        }
    }
}
