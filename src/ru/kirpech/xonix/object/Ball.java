package ru.kirpech.xonix.object;

import java.awt.*;
import java.util.Random;

import ru.kirpech.xonix.core.*;
import ru.kirpech.xonix.core.Object;

import static ru.kirpech.xonix.core.GameXonix.*;

public class Ball extends Enemy {

    Ball(Random random) {
        do {
            x = random.nextInt(GameXonix.FIELD_WIDTH);
            y = random.nextInt(FIELD_HEIGHT);
        } while (field.getColor(x, y) > COLOR_WATER);
        dx = random.nextBoolean()? 1 : -1;
        dy = random.nextBoolean()? 1 : -1;
    }


    boolean isHitTrackOrXonix(Xonix xonix) {
        updateDXandDY();
        if (field.getColor(x + dx, y + dy) == COLOR_TRACK) return true;
        if (x + dx == xonix.getX() && y + dy == xonix.getY()) return true;
        return false;
    }

    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(x*POINT_SIZE, y*POINT_SIZE, POINT_SIZE, POINT_SIZE);
        g.setColor(new Color(COLOR_LAND));
        g.fillOval(x*POINT_SIZE + 2, y*POINT_SIZE + 2, POINT_SIZE - 4, POINT_SIZE - 4);
    }
    @Override
    public void updateDXandDY(){
        if (field.getColor(x + dx, y) == COLOR_LAND) dx = -dx;
        if (field.getColor(x, y + dy) == COLOR_LAND) dy = -dy;
    }
}

