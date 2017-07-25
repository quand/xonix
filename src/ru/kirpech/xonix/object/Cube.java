package ru.kirpech.xonix.object;

import java.awt.*;

import static ru.kirpech.xonix.core.GameXonix.*;

public class Cube extends Enemy {

    public Cube() {
        init();
    }

    public void init() {
        x = dx = dy = 1;
    }


    public void paint(Graphics g) {
        g.setColor(new Color(COLOR_WATER));
        g.fillRect(x * POINT_SIZE, y * POINT_SIZE, POINT_SIZE, POINT_SIZE);
        g.setColor(new Color(COLOR_LAND));
        g.fillRect(x * POINT_SIZE + 2, y * POINT_SIZE + 2, POINT_SIZE - 4, POINT_SIZE - 4);
    }

    @Override
    public void updateDXandDY() {
        if (field.getColor(x + dx, y) == COLOR_WATER) dx = -dx;
        if (field.getColor(x, y + dy) == COLOR_WATER) dy = -dy;
    }
}