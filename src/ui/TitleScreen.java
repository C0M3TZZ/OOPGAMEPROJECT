package ui;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class TitleScreen {
    GamePanel gp;
    BufferedImage logo, titleScreen;
    Color background = new Color(25, 24, 40, 250);
    int counter = 0;

    public TitleScreen(GamePanel gp) {
        this.gp = gp;
        try {
            logo = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/ui/logo.png")));
            titleScreen = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/ui/titleScreen.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics2D g2) {
        g2.setColor(background);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.drawImage(titleScreen, 0, 0, gp.screenWidth, gp.screenHeight, null);
        gp.player.drawShadow(g2);
        g2.drawImage(logo, gp.screenWidth/2 - 320/2, 150, 320, 277, null);
        counter++;
        if (counter < 20) {
            g2.setColor(Color.white);
        }
        else {
            g2.setColor(Color.black);
            if (counter > 40) {
                counter = 0;
            }
        }
        g2.drawString("press space to start", gp.screenWidth/2 - 285/2, 600);
    }
}
