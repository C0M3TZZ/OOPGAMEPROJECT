package ui;

import main.GamePanel;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font GhastlyPixe;
    public TitleScreen titleScreen;
    public PauseScreen pauseScreen;
    public DiedScreen diedScreen;
    public PlayerDetails playerDetails;

    public UI(GamePanel gp) {
        this.gp = gp;
        titleScreen = new TitleScreen(gp);
        pauseScreen = new PauseScreen(gp);
        playerDetails = new PlayerDetails(gp);
        diedScreen = new DiedScreen(gp);
        try {
            InputStream is = getClass().getResourceAsStream("/font/GhastlyPixe.ttf");
            GhastlyPixe = Font.createFont(Font.TRUETYPE_FONT, is);
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

        GhastlyPixe = GhastlyPixe.deriveFont(Font.TRUETYPE_FONT, 50);
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }
        if (gp.gameState == gp.dideState) {
            GhastlyPixe = GhastlyPixe.deriveFont(Font.TRUETYPE_FONT, 150);
            drawDiedScreen();
        }

        if (gp.gameState == gp.playState) {
            GhastlyPixe = GhastlyPixe.deriveFont(Font.TRUETYPE_FONT, 40);
            drawPlayerDetails();
        }
    }

    public void drawTitleScreen() { titleScreen.draw(g2); }
    public void drawPlayerDetails() {
        playerDetails.draw(g2);
    }
    public void drawPauseScreen() {
        pauseScreen.draw(g2);
    }
    public void drawDiedScreen() {
        diedScreen.draw(g2);
    }
}
