package ui;

import main.GamePanel;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Button {
    GamePanel gp;
    public ArrayList<Rectangle2D> buttonR2Arr = new ArrayList<Rectangle2D>();
    public ArrayList<String> buttonNameArr = new ArrayList<String>();
    int buttonWidth;
    int buttonHeight = 50;
    int buttonX, buttonY;
    Rectangle2D r2;
    Color transparent = new Color(0, 0, 0, 0);

    public Button(GamePanel gp, ArrayList<String> buttonNameArr) {
        this.gp = gp;
        this.buttonNameArr = buttonNameArr;
        this.buttonY = (gp.screenHeight / 2) + (buttonHeight / 2);
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.red);
        for (int i = 0; i < buttonNameArr.size(); i++) {
            buttonWidth = g2.getFontMetrics().stringWidth((String) buttonNameArr.get(i));
            buttonX = (gp.screenWidth / 2) - (buttonWidth / 2);

            r2 = new Rectangle(buttonX, buttonY + (i * buttonHeight * 2) - ((buttonNameArr.size() - 1) * buttonHeight) - 38, buttonWidth, buttonHeight);
            if (!buttonR2Arr.contains(r2)) {
                buttonR2Arr.add(r2);
            }

            g2.setColor(Color.WHITE);
            g2.drawString((String) buttonNameArr.get(i), buttonX, buttonY + (i * buttonHeight * 2) - ((buttonNameArr.size() - 1) * buttonHeight));

            g2.setColor(transparent);
            g2.draw(r2);
        }
    }
}
