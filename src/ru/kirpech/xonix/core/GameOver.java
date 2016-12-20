package ru.kirpech.xonix.core;

import java.awt.*;
import ru.kirpech.xonix.core.*;

public class GameOver {
    private final String GAME_OVER_MSG = "GAME OVER";
    protected boolean gameOver,gameLosed=false;

    boolean isGameOver() { return gameOver; }
    boolean isGameLosed() { return gameLosed; }

    void paint(Graphics g) {
        if (isGameLosed()) {
            g.setColor(Color.white);
            g.setFont(new Font("", Font.BOLD, 60));
            FontMetrics fm = g.getFontMetrics();
            g.drawString(GAME_OVER_MSG,
                    (GameXonix.FIELD_WIDTH * GameXonix.POINT_SIZE + GameXonix.FIELD_DX - fm.stringWidth(GAME_OVER_MSG)) / 2,
                    (GameXonix.FIELD_HEIGHT * GameXonix.POINT_SIZE) / 2);
        }
    }
}
