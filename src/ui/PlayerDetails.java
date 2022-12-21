package ui;

import main.GamePanel;

import java.awt.*;
import java.util.ArrayList;

public class PlayerDetails {
    GamePanel gp;
    public Button button;
    ArrayList<String> buttonNameArr = new ArrayList<String>();
    Color fadeColor = new Color(0, 0, 0, 150);

    public PlayerDetails(GamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.drawString(gp.player.name, 25, 50);
        g2.setColor(Color.RED);
        g2.fillRect(25, 75, gp.player.life*25, 10);
    }
}
