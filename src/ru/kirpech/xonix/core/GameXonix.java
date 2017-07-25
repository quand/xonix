package ru.kirpech.xonix.core;

import ru.kirpech.xonix.object.Balls;
import ru.kirpech.xonix.object.Cube;
import ru.kirpech.xonix.object.Field;
import ru.kirpech.xonix.object.Xonix;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GameXonix {

    public static final int POINT_SIZE = 10;
    static final public int FIELD_WIDTH = 640 / POINT_SIZE;
    static final public int FIELD_HEIGHT = 460 / POINT_SIZE;
    static final public int LEFT = 37; // key codes
    static final public int UP = 38;
    static final public int RIGHT = 39;
    static final public int DOWN = 40;
    static final public int COLOR_TEMP = 1; // for temporary filling
    static final public int COLOR_WATER = 0;
    static final public int COLOR_LAND = 0x00a8a8;
    static final public int COLOR_TRACK = 0x901290;
    static final int FIELD_DX = 6;
    private static final int FIELD_DY = 28 + 28;
    private static final int START_LOCATION = 200;
    private static final int SHOW_DELAY = 60; // delay for animation
    private static final int PERCENT_OF_WATER_CAPTURE = 75;
    private static final String FORMAT_STRING = "Score: %d %20s %d %20s %2.0f%%";
    private static final Font font = new Font("", Font.BOLD, 21);
    public static Field field = new Field();
    public Random random = new Random();
    private JLabel board = new JLabel();
    private Delay delay = new Delay();
    private Xonix xonix = new Xonix();
    private Balls balls = new Balls(random);
    private Cube cube = new Cube();
    private GameOver gameover = new GameOver();
    private boolean gameRunning = false;
    private boolean newGame = true;
    private Canvas canvas = new Canvas();

    private GameXonix() {
        // Инициализируем свойства окна
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        String TITLE_OF_PROGRAM = "Xonix";
        frame.setTitle(TITLE_OF_PROGRAM);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        final JMenuItem startMenuItem = new JMenuItem("Start");
        menuBar.add(startMenuItem);
        final JMenuItem pauseMenuItem = new JMenuItem("Pause");
        pauseMenuItem.setEnabled(false);
        menuBar.add(pauseMenuItem);
        final JMenuItem restartMenuItem = new JMenuItem("Restart");
        menuBar.add(restartMenuItem);
        final JMenuItem stopMenuItem = new JMenuItem("Stop");
        menuBar.add(stopMenuItem);
        frame.setBounds(START_LOCATION, START_LOCATION, FIELD_WIDTH * POINT_SIZE + FIELD_DX,
                (FIELD_HEIGHT + menuBar.getHeight()) * POINT_SIZE + FIELD_DY);


        board.setFont(font);
        board.setOpaque(true);
        board.setBackground(Color.black);
        board.setForeground(Color.white);
        board.setHorizontalAlignment(JLabel.CENTER);
        frame.add(BorderLayout.CENTER, canvas);
        frame.add(BorderLayout.SOUTH, board);
        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() >= LEFT && e.getKeyCode() <= DOWN)
                    xonix.setDirection(e.getKeyCode());
            }
        });
        // Подписываемся на клики по меню
        startMenuItem.addActionListener(e -> {
            gameRunning = true;
            startMenuItem.setEnabled(false);
            pauseMenuItem.setEnabled(true);
        });
        pauseMenuItem.addActionListener(e -> {
            gameRunning = false;
            startMenuItem.setEnabled(true);
            pauseMenuItem.setEnabled(false);
        });
        restartMenuItem.addActionListener(e -> {
            gameRunning = false;
            gameover.gameLosed = false;
            canvas.repaint();
            startMenuItem.setEnabled(true);
            pauseMenuItem.setEnabled(false);
            field.init();
            xonix.init();
            xonix.setCountLives();
            cube.init();
            balls.reset(random);
            delay.wait(SHOW_DELAY * 10);
        });
        stopMenuItem.addActionListener(e -> {
            gameRunning = false;
            gameover.gameOver = true;
            newGame = false;
            startMenuItem.setEnabled(true);
            pauseMenuItem.setEnabled(false);
        });
    }

    public static void main(String[] args) {
        new GameXonix().go();
    }

    private void go() { // main loop of game
        while (!gameover.isGameOver()/*||newGame*/) {
            canvas.repaint();
            if (gameRunning) {
                xonix.move(balls);
                balls.move();
                cube.move();
                board.setText(String.format(FORMAT_STRING, field.getCountScore(), "Xn:", xonix.getCountLives(),
                        "Full:", field.getCurrentPercent()));
                delay.wait(SHOW_DELAY);
                if (xonix.isSelfCrosed() || balls.isHitTrackOrXonix(field, xonix) || cube.isHitXonix(xonix)) {
                    xonix.decreaseCountLives();
                    if (xonix.getCountLives() > 0) {
                        xonix.init();
                        field.clearTrack();
                        delay.wait(SHOW_DELAY * 10);
                    } else {
                        gameRunning = false;
                        gameover.gameLosed = true;
                    }
                }
                if (field.getCurrentPercent() >= PERCENT_OF_WATER_CAPTURE) {
                    field.init();
                    xonix.init();
                    cube.init();
                    balls.add(random);
                    delay.wait(SHOW_DELAY * 10);
                }
            }
        }
        canvas.repaint();
    }


    class Delay {
        void wait(int milliseconds) {
            try {
                Thread.sleep(milliseconds);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class Canvas extends JPanel { // my canvas for painting
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            field.paint(g);
            xonix.paint(g);
            balls.paint(g);
            cube.paint(g);
            gameover.paint(g);
        }
    }


}