package object;

import main.AnimationLoader;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
    GamePanel gp;
    AnimationLoader animationLoader;
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public int spriteCounter, spriteNum;
    String animationName;

    public SuperObject(GamePanel gp) {
        this.gp = gp;
        this.animationLoader = new AnimationLoader(gp);
    }

    public void update() {
        spriteCounter++;
        if (spriteCounter > 8) {
            if (spriteNum != animationLoader.getAnimation(animationName).size() - 1) {
                spriteNum++;
            } else {
                spriteNum = 0;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2, GamePanel gp, String animationName) {
        this.animationName = animationName;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        image = animationLoader.getAnimation(animationName).get(spriteNum);
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
