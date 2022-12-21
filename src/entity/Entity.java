package entity;

import main.AnimationLoader;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX, worldY, speed;
    public String direction = "left";
    public int spriteCounter, spriteNum;
    public int spriteCounterLimit = 8;
    public Rectangle solidAreaX, solidAreaY;
    public int solidAreaXDefaultX, solidAreaXDefaultY;
    public int solidAreaYDefaultX, solidAreaYDefaultY;
    public boolean topHit, bottomHit, leftHit, rightHit;
    public int screenX, screenY;
    GamePanel gp;
    public AnimationLoader animationLoader;

    public boolean alive = true;
    public int life = 0;
    public int maxLife = 0;
    boolean invincible = false;
    int invincibleCounter = 0;
    public Entity(GamePanel gp) {
        this.gp = gp;
        this.animationLoader = new AnimationLoader(gp);
    }

    public void setAction() {

    }

    public void attacked(int damage) {
        if (!invincible) {
            life -= damage;
            System.out.println("Life: " + life);
            System.out.println("Attack: " + damage);
            invincible = true;
            System.out.println("Turning invincible on");
        }
    }

    public void update() {
        setAction();
        spriteCounter++;
        if (spriteCounter > 10) {
            if (spriteNum != animationLoader.getAnimation(direction).size() - 1) {
                spriteNum++;
            } else {
                spriteNum = 0;
            }
            spriteCounter = 0;
        }
        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 30) {
                System.out.println("Turning invincible off");
                invincible = false;
                invincibleCounter = 0;
            }
        }
        gp.cChecker.checkTile(this);
        if (life <= 0) {
            onDeath();
        }
    }

    public void onDeath() {
        alive = false;
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = animationLoader.getAnimation(this.direction).get(this.spriteNum);
        this.screenX = worldX - gp.player.worldX + gp.player.screenX;
        this.screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (direction.equals("right")) {
            g2.drawImage(image, screenX + (gp.tileSize * 2), screenY, -gp.tileSize * 2, gp.tileSize * 2, null);
        } else {
            g2.drawImage(image, screenX, screenY, gp.tileSize * 2, gp.tileSize * 2, null);
        }
    }

}
