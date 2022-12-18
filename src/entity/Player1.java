package entity;

import main.GamePanel;
import main.KeyHandler;
import weapon.BigSword;

import java.awt.*;;

public class Player1 extends Player {
    BigSword bigSword;
    public Player1(GamePanel gp, KeyHandler keyH) {
        super(gp, keyH);
        bigSword = new BigSword(gp, keyH);
        this.solidAreaX = new Rectangle(4 + 32 + 4 + 4, 8 + 56 + 28, 40, 50 - 28);
        this.solidAreaY = new Rectangle(8 + 32 + 4 + 4, 5 + 56 + 28, 32, 55 - 28);
    }

    public void getPlayerImage() {
        try {
            animationLoader.LoadAnimation("player/player1.png", 0, 3, "up");
            animationLoader.LoadAnimation("player/player1.png", 4, 7, "left");
            animationLoader.LoadAnimation("player/player1.png", 4, 7, "right");
            animationLoader.LoadAnimation("player/player1.png", 8, 11, "down");
            animationLoader.LoadAnimation("player/player1.png", 12, 15, "idle");
            animationLoader.LoadAnimation("player/player1.png", 16, 19, "dashUp");
            animationLoader.LoadAnimation("player/player1.png", 20, 23, "dashLeft");
            animationLoader.LoadAnimation("player/player1.png", 20, 23, "dashRight");
            animationLoader.LoadAnimation("player/player1.png", 24, 27, "dashDown");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        super.draw(g2);
        bigSword.draw(g2);
    }
}
