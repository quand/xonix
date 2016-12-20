package ru.kirpech.xonix.core;

import ru.kirpech.xonix.object.Field;

import javax.swing.*;

import static ru.kirpech.xonix.core.GameXonix.*;


public abstract class Object extends JPanel {
    protected int x, y, dx, dy;


    public boolean isHitXonix(Object xonix) {
        updateDXandDY();
        if (x + dx == xonix.getX() && y + dy == xonix.getY()) return true;
        return false;
    }

    protected abstract void updateDXandDY();

    public int getX() { return x; }
    public int getY() { return y; }

}
