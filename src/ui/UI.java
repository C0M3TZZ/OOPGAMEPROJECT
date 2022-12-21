package ui;

import main.GamePanel;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font GhastlyPixe;
    public PauseScreen pauseScreen;
    public DiedScreen diedScreen;

    public UI(GamePanel gp) {
        this.gp = gp;
        pauseScreen = new PauseScreen(gp);
        try {
            InputStream is = getClass().getResourceAsStream("/font/GhastlyPixe.ttf");
            GhastlyPixe = Font.createFont(Font.TRUETYPE_FONT, is);
            GhastlyPixe = GhastlyPixe.deriveFont(Font.TRUETYPE_FONT, 50);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(GhastlyPixe);
        g2.setColor(Color.WHITE);

        if (gp.gameState == gp.playState) {
        }
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }
        if (gp.gameState == gp.dideState) {
            drawDiedScreen();
        }
    }

    public void drawPauseScreen() {
        pauseScreen.draw(g2);
    }

    public void drawDiedScreen() {
        pauseScreen.draw(g2);
    }
}
