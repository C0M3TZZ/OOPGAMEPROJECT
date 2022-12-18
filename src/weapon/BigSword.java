package weapon;

import entity.Player;
import main.AnimationLoader;
import main.GamePanel;
import main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BigSword extends Weapon {
    public BigSword(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        this.animationLoader = new AnimationLoader(gp);

        getWeaponImage();
    }

    @Override
    public void getWeaponImage() {
        try {
            animationLoader.LoadAnimation("weapon/BigSword.png", 0, 3, "up");
            animationLoader.LoadAnimation("weapon/BigSword.png", 4, 7, "left");
            animationLoader.LoadAnimation("weapon/BigSword.png", 4, 7, "right");
            animationLoader.LoadAnimation("weapon/BigSword.png", 8, 11, "down");
            animationLoader.LoadAnimation("weapon/BigSword.png", 12, 15, "idle");
            animationLoader.LoadAnimation("weapon/BigSword.png", 16, 19, "dashUp");
            animationLoader.LoadAnimation("weapon/BigSword.png", 20, 23, "dashLeft");
            animationLoader.LoadAnimation("weapon/BigSword.png", 20, 23, "dashRight");
            animationLoader.LoadAnimation("weapon/BigSword.png", 24, 27, "dashDown");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        BufferedImage image = animationLoader.getAnimation(gp.player.direction).get(gp.player.spriteNum);
        if (gp.player.direction.equals("left") || gp.player.direction.equals("dashLeft")) {
            g2.drawImage(image, gp.player.screenX + (gp.tileSize * 2), gp.player.screenY, -gp.tileSize * 2, gp.tileSize * 2, null);
        } else {
            g2.drawImage(image, gp.player.screenX, gp.player.screenY, gp.tileSize * 2, gp.tileSize * 2, null);
        }
    }
}
