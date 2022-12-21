package weapon;

import main.AnimationLoader;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Weapon {
    GamePanel gp;
    AnimationLoader animationLoader;
    BufferedImage image;

    public abstract void getWeaponImage();

    public void draw(Graphics2D g2) {
        if (!gp.player.ultimate) {
            image = animationLoader.getAnimation(gp.player.direction).get(gp.player.spriteNum);
        }
        else if (!gp.player.direction.equals("pressUlti")) {
            image = animationLoader.getAnimation(gp.player.direction.substring(0, gp.player.direction.length()-4)).get(gp.player.spriteNum);
        }
        if (!gp.player.direction.equals("pressUlti")) {
            if (gp.player.direction.equals("left") || gp.player.direction.equals("dashLeft") || gp.player.direction.equals("leftUlti") || gp.player.direction.equals("dashLeftUlti")) {
                g2.drawImage(image, gp.player.screenX + (gp.tileSize * 2), gp.player.screenY, -gp.tileSize * 2, gp.tileSize * 2, null);
            } else {
                g2.drawImage(image, gp.player.screenX, gp.player.screenY, gp.tileSize * 2, gp.tileSize * 2, null);
            }
        }
    }
}
