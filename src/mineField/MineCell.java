package mineField;

import java.awt.*;

public class MineCell {

    private MineField mineField;
    private int size, xc, yc;
    private Point point;

    public MineCell(MineField mineField, Point point, int size) {
        this.mineField = mineField;
        this.size = size;
        this.point = point;
        this.xc = point.x * size;
        this.yc = point.y * size;
    }


    public int getSize() {
        return size;
    }

    public int getXc() {
        return xc;
    }

    public int getYc() {
        return yc;
    }

    public boolean isDestinationCell(Point point) {
        return point.x == mineField.getGridViewSize() - 1 && point.y == mineField.getGridViewSize() - 1;
    }

    public boolean isCurrentCell(Point point) {
      return point == mineField.getCurrentPosition();
    }

    public void draw(Graphics2D gc) {
        gc.fillRect(xc, yc, size, size);

        gc.setColor(Color.BLACK);
        
        if (isCurrentCell(point)) {
            gc.setColor(Color.WHITE);
        }
        if (isDestinationCell(point)) {
            gc.setColor(Color.GREEN);
        }
        if (isCurrentCell(point) && mineField.isAMine(point)) {
            gc.setColor(Color.WHITE);
        }
        gc.drawRect(xc, yc, size, size);
        gc.setColor(Color.BLACK);
        String count = String.valueOf(mineField.getNumNeighboringMines(point));
        if ((mineField.showSolution() || mineField.isReached(point)) && !mineField.isAMine(point)) {
            gc.drawString(count, xc + size / 3, yc + (2 * size) / 3);
        } else {
            gc.drawString("?", xc + size / 3, yc + (2 * size) / 3);
        }
    }

}
