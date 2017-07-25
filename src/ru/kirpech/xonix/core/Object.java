package ru.kirpech.xonix.core;

import javax.swing.*;


public abstract class Object extends JPanel {
    protected int x, y, dx, dy;


    public boolean isHitXonix(Object xonix) {
        updateDXandDY();
        if (x + dx == xonix.getX() && y + dy == xonix.getY()) return true;
        return false;
    }

    protected abstract void updateDXandDY();

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
