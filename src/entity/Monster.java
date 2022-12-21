package entity;

import main.AnimationLoader;
import main.GamePanel;
import object.OBJ_HealthPotion;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Monster extends Entity {
    public Monster(GamePanel gp) {
        super(gp);

        this.speed = 1;
        this.maxLife = 3;
        life = maxLife;
        this.solidAreaX = new Rectangle(4 + 20, 8 + 56 + 28, 40, 50 - 28);

        this.solidAreaXDefaultX = solidAreaX.x;
        this.solidAreaXDefaultY = solidAreaX.y;

        this.solidAreaY = new Rectangle(8 + 20, 5 + 56 + 28, 32, 55 - 28);

        this.solidAreaYDefaultX = solidAreaY.x;
        this.solidAreaYDefaultY = solidAreaY.y;

        getMonsterImage();
    }

    public void getMonsterImage() {
        try {
            animationLoader.LoadAnimation("monster/monster1.png", 0, 5, "left");
            animationLoader.LoadAnimation("monster/monster1.png", 0, 5, "right");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onDeath() {
        Random random = new Random();
        int randomNum = random.nextInt(100);
        if (randomNum < 10) {
            OBJ_HealthPotion healthPotion = new OBJ_HealthPotion(gp);
            healthPotion.worldX = worldX;
            healthPotion.worldY = worldY;
            gp.obj.add(healthPotion);
        }
        alive = false;
    }

    public void setAction() {
        if (this.worldX < gp.player.worldX && !rightHit) {
            this.worldX += this.speed;
            this.direction = "right";
            this.solidAreaX.x = 24 + 12;
            this.solidAreaY.x = 28 + 12;
        } else if (this.worldX > gp.player.worldX && !leftHit) {
            this.worldX -= this.speed;
            this.direction = "left";
            this.solidAreaX.x = 24 + 12 + 12;
            this.solidAreaY.x = 28 + 12 + 12;
        }
        if (this.worldY < gp.player.worldY && !bottomHit) {
            this.worldY += this.speed;
        } else if (this.worldY > gp.player.worldY && !topHit) {
            this.worldY -= this.speed;
        }
        if (gp.cChecker.checkPlayer(this, gp.player) && !gp.player.dashing) {
            gp.player.attacked(1);
        }
    }



}