package entity;

import main.AnimationLoader;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Projectile extends Entity{

    Entity user;

    double angle;


    public Projectile(GamePanel gp) {
        super(gp);
        this.animationLoader = new AnimationLoader(gp);
    }

    public void set(int worldX, int worldY, double angle, String direction, boolean alive, Entity user) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.angle = angle;
        this.alive = alive;
        this.user = user;
        this.life = this.maxLife;
    }

    public void update() {
        worldX += Math.cos(angle) * speed;
        worldY += Math.sin(angle) * speed;

        life--;
        if (life <= 0) {
            alive = false;
        }

        spriteCounter++;
        if (spriteCounter > 10) {
            if (spriteNum != animationLoader.getAnimation(direction).size() - 1) {
                spriteNum++;
            } else {
                spriteNum = 0;
            }

            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = animationLoader.getAnimation(this.direction).get(this.spriteNum);

        this.screenX = worldX - gp.player.worldX + gp.player.screenX;
        this.screenY = worldY - gp.player.worldY + gp.player.screenY;

        //make a new image that is rotated
        BufferedImage rotatedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = rotatedImage.createGraphics();
        g.rotate(angle, image.getWidth() / 2, image.getHeight() / 2);
        g.drawImage(image, null, 0, 0);
        g.dispose();
        g2.drawImage(rotatedImage, screenX, screenY, gp.tileSize * 2, gp.tileSize * 2, null);
    }

}
