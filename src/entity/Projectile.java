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

//    public void set(int worldX, int worldY, String direction, boolean alive, Entity user) {
//        System.out.println("WorldX: " + worldX + " WorldY: " + worldY);
//        this.worldX = worldX;
//        this.worldY = worldY;
//        this.direction = direction;
//        this.alive = alive;
//        this.user = user;
//        this.life = this.maxLife;
//
//    }

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
//        switch (direction) {
//            case "up":
//                worldY -= speed;
//                break;
//            case "down":
//                worldY += speed;
//                break;
//            case "left":
//                worldX -= speed;
//                break;
//            case "right":
//                worldX += speed;
//                break;
//        }

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

}
