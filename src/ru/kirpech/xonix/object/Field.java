package ru.kirpech.xonix.object;

import java.awt.*;

import static ru.kirpech.xonix.core.GameXonix.*;

public class Field {
    private final int WATER_AREA = (FIELD_WIDTH - 4)*(FIELD_HEIGHT - 4);
    private int[][] field = new int[FIELD_WIDTH][FIELD_HEIGHT];
    private float currentWaterArea;
    private int countScore = 0;

    public Field() {
        init();
    }

    public void init() {
        for (int y = 0; y < FIELD_HEIGHT; y++)
            for (int x = 0; x < FIELD_WIDTH; x++)
                field[x][y] = (x < 2 || x > FIELD_WIDTH - 3 || y < 2 || y > FIELD_HEIGHT - 3)? COLOR_LAND : COLOR_WATER;
        currentWaterArea = WATER_AREA;
    }

    public int getColor(int x, int y) {
        if (x < 0 || y < 0 || x > FIELD_WIDTH - 1 || y > FIELD_HEIGHT - 1) return COLOR_WATER;
        return field[x][y];
    }

    void setColor(int x, int y, int color) { field[x][y] = color; }

    public int getCountScore() { return countScore; }
    public float getCurrentPercent() { return 100f - currentWaterArea / WATER_AREA * 100; }

    public void clearTrack() { // clear track of Xonix
        for (int y = 0; y < FIELD_HEIGHT; y++)
            for (int x = 0; x < FIELD_WIDTH; x++)
                if (field[x][y] == COLOR_TRACK) field[x][y] = COLOR_WATER;
    }

    private void fillTemporary(int x, int y) {
        if (field[x][y] > COLOR_WATER) return;
        field[x][y] = COLOR_TEMP; // filling temporary color
        for (int dx = -1; dx < 2; dx++)
            for (int dy = -1; dy < 2; dy++) fillTemporary(x + dx, y + dy);
    }

    void tryToFill(Balls balls) {
        currentWaterArea = 0;
        for (Ball ball : balls.getBalls()) fillTemporary(ball.getX(), ball.getY());
        for (int y = 0; y < FIELD_HEIGHT; y++)
            for (int x = 0; x < FIELD_WIDTH; x++) {
                if (field[x][y] == COLOR_TRACK || field[x][y] == COLOR_WATER) {
                    field[x][y] = COLOR_LAND;
                    countScore += 10;
                }
                if (field[x][y] == COLOR_TEMP) {
                    field[x][y] = COLOR_WATER;
                    currentWaterArea++;
                }
            }
    }

    public void paint(Graphics g) {
        for (int y = 0; y < FIELD_HEIGHT; y++)
            for (int x = 0; x < FIELD_WIDTH; x++) {
                g.setColor(new Color(field[x][y]));
                g.fillRect(x*POINT_SIZE, y*POINT_SIZE, POINT_SIZE, POINT_SIZE);
            }
    }
}
