package entity;

import main.AnimationLoader;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX, worldY, speed;
    public String direction = "left";
    public int spriteCounter, spriteNum;
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

    public Entity(GamePanel gp) {
        this.gp = gp;
        this.animationLoader = new AnimationLoader(gp);
    }

    public void setAction() {

    }

    public void update() {
        setAction();
        spriteCounter++;
        if (spriteCounter > 10) {
            if (spriteNum != animationLoader.getAnimation(direction).size() - 1) {
                System.out.println("SpriteNum: " + spriteNum);
                spriteNum++;
            } else {
                spriteNum = 0;
            }
            spriteCounter = 0;
        }
        gp.cChecker.checkTile(this);
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = animationLoader.getAnimation(this.direction).get(this.spriteNum);
        this.screenX = worldX - gp.player.worldX + gp.player.screenX;
        this.screenY = worldY - gp.player.worldY + gp.player.screenY;
        if (direction.equals("left")) {
            g2.drawImage(image, screenX + (gp.tileSize * 2), screenY, -gp.tileSize * 2, gp.tileSize * 2, null);
        } else {
            g2.drawImage(image, screenX, screenY, gp.tileSize * 2, gp.tileSize * 2, null);
        }
//        g2.setColor(Color.RED);
//        g2.draw(new Rectangle(this.screenX + this.solidAreaX.x, this.screenY + this.solidAreaX.y, this.solidAreaX.width, this.solidAreaX.height));
//        g2.setColor(Color.GREEN);
//        g2.draw(new Rectangle(this.screenX + this.solidAreaY.x, this.screenY + this.solidAreaY.y, this.solidAreaY.width, this.solidAreaY.height));
    }
}
