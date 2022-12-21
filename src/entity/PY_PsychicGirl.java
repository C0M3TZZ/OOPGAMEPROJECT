package entity;

import main.GamePanel;
import main.KeyHandler;
import main.MouseHandler;
import weapon.BigSword;

import java.awt.*;;

public class PY_PsychicGirl extends Player {
    BigSword bigSword;

    public PY_PsychicGirl(GamePanel gp) {
        super(gp);
        bigSword = new BigSword(gp);
    }

    public void getPlayerImage() {
        try {
            animationLoader.LoadAnimation("player/psychicGirl.png", 0, 3, "up");
            animationLoader.LoadAnimation("player/psychicGirl.png", 4, 7, "left");
            animationLoader.LoadAnimation("player/psychicGirl.png", 8, 11, "down");
            animationLoader.LoadAnimation("player/psychicGirl.png", 12, 15, "right");

            animationLoader.LoadAnimation("player/psychicGirl.png", 16, 23, "dashUp");
            animationLoader.LoadAnimation("player/psychicGirl.png", 24, 31, "dashDown");
            animationLoader.LoadAnimation("player/psychicGirl.png", 32, 38, "dashLeft");
            animationLoader.LoadAnimation("player/psychicGirl.png", 39, 46, "dashRight");

            animationLoader.LoadAnimation("player/psychicGirl_pressUlti.png", 0, 7, "pressUlti", 128);

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
        image = animationLoader.getAnimation(direction).get(spriteNum);
        if (direction.equals("pressUlti")) {
            g2.drawImage(image, screenX - gp.tileSize, screenY - gp.tileSize * 2, gp.tileSize * 2 * 2, gp.tileSize * 2 * 2, null);
        } else {
            g2.drawImage(image, screenX, screenY, gp.tileSize * 2, gp.tileSize * 2, null);
        }

        g2.setColor(Color.RED);
        g2.draw(new Rectangle(screenX + solidAreaY.x, screenY + solidAreaY.y, solidAreaY.width, solidAreaY.height));
        g2.setColor(Color.GREEN);
        g2.draw(new Rectangle(screenX + solidAreaX.x, screenY + solidAreaX.y, solidAreaX.width, solidAreaX.height));

        bigSword.draw(g2);
    }
}
