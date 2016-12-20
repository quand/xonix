package ru.kirpech.xonix.object;

import ru.kirpech.xonix.core.*;

import java.awt.*;
import static ru.kirpech.xonix.core.GameXonix.*;

public class Xonix extends Player {

    public Xonix() {
        init();
    }

    public void init() {
        y = 0;
        x = FIELD_WIDTH / 2;
        direction = 0;
        isWater = false;
    }

    public int getCountLives() { return countLives; }

    public void setCountLives() {  countLives=3; }

    public void decreaseCountLives() { countLives--; }

    public void setDirection(int direction) { this.direction = direction; }

    public void paint(Graphics g) {
        g.setColor((field.getColor(x, y) == COLOR_LAND) ? new Color(COLOR_TRACK) : Color.white);
        g.fillRect(x*POINT_SIZE, y*POINT_SIZE, POINT_SIZE, POINT_SIZE);
        g.setColor((field.getColor(x, y) == COLOR_LAND) ? Color.white : new Color(COLOR_TRACK));
        g.fillRect(x*POINT_SIZE + 3, y*POINT_SIZE + 3, POINT_SIZE - 6, POINT_SIZE - 6);
    }

    @Override
    protected void updateDXandDY() {

    }
}
