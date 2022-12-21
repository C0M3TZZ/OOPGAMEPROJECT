package entity;

import main.GamePanel;
import main.KeyHandler;
import main.MouseHandler;
import weapon.BigSword;

import java.awt.*;

public class PY_Cyborg extends Player {
    BigSword bigSword;
    public PY_Cyborg(GamePanel gp) {
        super(gp);
        bigSword = new BigSword(gp);
    }

    @Override
    public void getPlayerImage() {
        try {
            animationLoader.LoadAnimation("player/cyborg.png", 0, 3, "up");
            animationLoader.LoadAnimation("player/cyborg.png", 4, 7, "left");
            animationLoader.LoadAnimation("player/cyborg.png", 8, 11, "down");
            animationLoader.LoadAnimation("player/cyborg.png", 12, 15, "right");

            animationLoader.LoadAnimation("player/cyborg.png", 16, 19, "dashUp");
            animationLoader.LoadAnimation("player/cyborg.png", 20, 23, "dashLeft");
            animationLoader.LoadAnimation("player/cyborg.png", 24, 27, "dashDown");
            animationLoader.LoadAnimation("player/cyborg.png", 28, 31, "dashRight");

            animationLoader.LoadAnimation("player/cyborg.png", 32, 39, "pressUlti");

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
        g2.drawImage(image, screenX, screenY, gp.tileSize * 2, gp.tileSize * 2, null);
        bigSword.draw(g2);

        g2.setColor(Color.RED);
        g2.draw(new Rectangle(screenX + solidAreaY.x, screenY + solidAreaY.y, solidAreaY.width, solidAreaY.height));
        g2.setColor(Color.GREEN);
        g2.draw(new Rectangle(screenX + solidAreaX.x, screenY + solidAreaX.y, solidAreaX.width, solidAreaX.height));
    }
}
