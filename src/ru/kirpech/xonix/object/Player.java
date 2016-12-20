package ru.kirpech.xonix.object;

import ru.kirpech.xonix.core.GameXonix;
import ru.kirpech.xonix.core.Object;

import static ru.kirpech.xonix.core.GameXonix.*;

public abstract class Player extends Object {
    protected boolean isWater;
    private boolean isSelfCross;
    int direction, countLives = 3;

    public void move(Balls balls) {
        if (direction == LEFT) x--;
        if (direction == RIGHT) x++;
        if (direction == UP) y--;
        if (direction == DOWN) y++;
        if (x < 0) x = 0;
        if (y < 0) y = 0;
        if (y > FIELD_HEIGHT - 1) y = FIELD_HEIGHT - 1;
        if (x > FIELD_WIDTH - 1) x = FIELD_WIDTH - 1;
        isSelfCross = GameXonix.field.getColor(x, y) == COLOR_TRACK;
        if (GameXonix.field.getColor(x, y) == COLOR_LAND && isWater) {
            direction = 0;
            isWater = false;
            GameXonix.field.tryToFill(balls);
        }
        if (GameXonix.field.getColor(x, y) == COLOR_WATER) {
            isWater = true;
            GameXonix.field.setColor(x, y, COLOR_TRACK);
        }
    }
    public boolean isSelfCrosed() { return isSelfCross; }
}
