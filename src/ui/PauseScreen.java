package ui;

import main.GamePanel;

import java.awt.*;
import java.util.ArrayList;

public class PauseScreen {
    GamePanel gp;
    public Button button;
    ArrayList<String> buttonNameArr = new ArrayList<String>();
    Color fadeColor = new Color(0, 0, 0, 150);

    public PauseScreen(GamePanel gp) {
        this.gp = gp;
        buttonNameArr.add("continue");
        buttonNameArr.add("quit to menu");
        button = new Button(gp, buttonNameArr);
    }

    public void draw(Graphics2D g2) {
        g2.setColor(fadeColor);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        button.draw(g2);
    }
}
