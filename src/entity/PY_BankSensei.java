package entity;

import main.GamePanel;
import main.KeyHandler;
import main.MouseHandler;
import weapon.BigSword;

import java.awt.*;

public class PY_BankSensei extends Player {
    BigSword bigSword;
    public PY_BankSensei(GamePanel gp, KeyHandler keyH) {
        super(gp, keyH);
        bigSword = new BigSword(gp);
    }

    @Override
    public void getPlayerImage() {
        try {
            animationLoader.LoadAnimation("player/bankSensei.png", 0, 3, "up");
            animationLoader.LoadAnimation("player/bankSensei.png", 4, 7, "left");
            animationLoader.LoadAnimation("player/bankSensei.png", 4, 7, "right");
            animationLoader.LoadAnimation("player/bankSensei.png", 8, 11, "down");

            animationLoader.LoadAnimation("player/bankSensei.png", 12, 15, "dashUp");
            animationLoader.LoadAnimation("player/bankSensei.png", 16, 19, "dashLeft");
            animationLoader.LoadAnimation("player/bankSensei.png", 16, 19, "dashRight");
            animationLoader.LoadAnimation("player/bankSensei.png", 20, 23, "dashDown");

            animationLoader.LoadAnimation("player/bankSensei.png", 24, 31, "pressUlti");

            animationLoader.LoadAnimation("player/dogBoy.png", 12, 15, "idle");;

            animationLoader.LoadAnimation("player/dogBoy.png", 38, 41, "upUlti");
            animationLoader.LoadAnimation("player/dogBoy.png", 42, 45, "leftUlti");
            animationLoader.LoadAnimation("player/dogBoy.png", 42, 45, "rightUlti");
            animationLoader.LoadAnimation("player/dogBoy.png", 46, 49, "downUlti");
            animationLoader.LoadAnimation("player/dogBoy.png", 50, 53, "idleUlti");

            animationLoader.LoadAnimation("player/dogBoy.png", 54, 57, "dashUpUlti");
            animationLoader.LoadAnimation("player/dogBoy.png", 58, 61, "dashLeftUlti");
            animationLoader.LoadAnimation("player/dogBoy.png", 58, 61, "dashRightUlti");
            animationLoader.LoadAnimation("player/dogBoy.png", 62, 65, "dashDownUlti");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        super.draw(g2);
        bigSword.draw(g2);
    }
}
