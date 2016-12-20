package ru.kirpech.xonix.object;

import ru.kirpech.xonix.core.Object;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Balls {
    private ArrayList<Ball> balls = new ArrayList<>();

    public Balls(Random random) {
        add(random);
    }

    public void add(Random random) { balls.add(new Ball(random)); }
    public void reset(Random random) {balls.clear(); balls.add(new Ball(random));}


    ArrayList<Ball> getBalls() { return balls; }

    public boolean isHitTrackOrXonix(Field field, Xonix xonix) {
        for (Ball ball : balls) if (ball.isHitTrackOrXonix(xonix)) return true;
        return false;
    }
    public void move() { for (Ball ball : balls) ball.move(); }

    public void paint(Graphics g) { for (Ball ball : balls) ball.paint(g); }
}