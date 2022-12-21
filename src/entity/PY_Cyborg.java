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
            // BASIC
            animationLoader.LoadAnimation("player/cyborg/basic.png", 0, 7, "idle");
            animationLoader.LoadAnimation("player/cyborg/basic.png", 8, 15, "up");
            animationLoader.LoadAnimation("player/cyborg/basic.png", 16, 23, "left");
            animationLoader.LoadAnimation("player/cyborg/basic.png", 24, 31, "down");
            animationLoader.LoadAnimation("player/cyborg/basic.png", 32, 39, "right");
            animationLoader.LoadAnimation("player/cyborg/basic.png", 40, 47, "dashUp");
            animationLoader.LoadAnimation("player/cyborg/basic.png", 48, 55, "dashLeft");
            animationLoader.LoadAnimation("player/cyborg/basic.png", 56, 63, "dashDown");
            animationLoader.LoadAnimation("player/cyborg/basic.png", 64, 71, "dashRight");

            // PRESS ULTI
            animationLoader.LoadAnimation("player/cyborg/pressUlti.png", 0, 7, "pressUlti");

            // ULTI
            animationLoader.LoadAnimation("player/cyborg/ulti.png", 0, 7, "idleUlti", 128);
            animationLoader.LoadAnimation("player/cyborg/ulti.png", 0, 7, "upUlti", 128);
            animationLoader.LoadAnimation("player/cyborg/ulti.png", 0, 7, "leftUlti", 128);
            animationLoader.LoadAnimation("player/cyborg/ulti.png", 0, 7, "downUlti", 128);
            animationLoader.LoadAnimation("player/cyborg/ulti.png", 0, 7, "rightUlti", 128);
            animationLoader.LoadAnimation("player/cyborg/basic.png", 40, 47, "dashUpUlti");
            animationLoader.LoadAnimation("player/cyborg/basic.png", 48, 55, "dashLeftUlti");
            animationLoader.LoadAnimation("player/cyborg/basic.png", 56, 63, "dashDownUlti");
            animationLoader.LoadAnimation("player/cyborg/basic.png", 64, 71, "dashRightUlti");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        //RUN
        super.getDirection();

        // ULTIMATE
        this.getUltimate();

        //DASHING
        super.getDashing();

        // UPDATE
        super.update();
    }

    public void draw(Graphics2D g2) {
        image = animationLoader.getAnimation(direction).get(spriteNum);

        if (direction.equals("upUlti") || direction.equals("downUlti") || direction.equals("leftUlti") || direction.equals("rightUlti") || direction.equals("idleUlti")) {
            g2.drawImage(image, screenX - gp.tileSize, screenY - gp.tileSize, gp.tileSize * 2 * 2, gp.tileSize * 2 * 2, null);
        } else {
            g2.drawImage(image, screenX, screenY, gp.tileSize * 2, gp.tileSize * 2, null);
        }

        super.drawShadow(g2);

//        g2.setColor(Color.RED);
//        g2.draw(new Rectangle(screenX + solidAreaY.x, screenY + solidAreaY.y, solidAreaY.width, solidAreaY.height));
//        g2.setColor(Color.GREEN);
//        g2.draw(new Rectangle(screenX + solidAreaX.x, screenY + solidAreaX.y, solidAreaX.width, solidAreaX.height));
    }
}
