package ui;

import main.GamePanel;

import java.awt.*;

public class DiedScreen {
    GamePanel gp;
    Color fadeColor = new Color(0, 0, 0, 150);
    public DiedScreen(GamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(fadeColor);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        int width = g2.getFontMetrics().stringWidth("Game Over");
        g2.setColor(Color.white);
        g2.drawString("Game Over", gp.screenWidth/2 - width/2 , gp.screenHeight/2 + 60);
    }
}
