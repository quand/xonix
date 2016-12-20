package ru.kirpech.xonix.object;

import ru.kirpech.xonix.core.Object;

public abstract class Enemy extends Object {
    public void move() {
        updateDXandDY();
        x += dx;
        y += dy;
    }

    public abstract void updateDXandDY();
}
