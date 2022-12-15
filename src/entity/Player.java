package entity;

import main.AnimationLoader;
import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    AnimationLoader animationLoader;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        this.animationLoader = new AnimationLoader(gp);

        screenX = gp.screenWidth/2 - (gp.tileSize / 2);

        screenY = gp.screenHeight/2 - (gp.tileSize / 2);

        solidArea = new Rectangle(8, 16, 32, 32);

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "idle";
    }

    public void getPlayerImage() {
        try {
            animationLoader.LoadAnimation("player/player1/walk/spriteSheetTest.png", 0, 3, "down");
            animationLoader.LoadAnimation("player/player1/walk/spriteSheetTest.png", 0, 3, "left");
            animationLoader.LoadAnimation("player/player1/walk/spriteSheetTest.png", 0, 3, "right");
            animationLoader.LoadAnimation("player/player1/walk/spriteSheetTest.png", 0, 3, "up");
            animationLoader.LoadAnimation("player/player1/idle/spriteSheetTest.png", 0, 3, "idle");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        collisionOn = false;
        gp.cChecker.checkTile(this);
        direction = "idle";
        if (keyH.upPressed){
            direction = "up";
            if (!gp.cChecker.topHit && !collisionOn){
                worldY -= speed;
            }
        }
        if (keyH.downPressed) {
            direction = "down";
            if (!gp.cChecker.bottomHit && !collisionOn){
                worldY += speed;
            }
        }
        if (keyH.rightPressed) {
            direction = "right";
            flip = 0;
            if (!gp.cChecker.rightHit && !collisionOn){
                worldX += speed;
            }
        }
        if (keyH.leftPressed) {
            direction = "left";
            flip = 1;
            if (!gp.cChecker.leftHit && !collisionOn){
                worldX -= speed;
            }
        }

        spriteCounter++;
        if (spriteCounter > 10){
            if (spriteNum != animationLoader.getAnimation(direction).size() - 1){
                spriteNum++;
            } else {
                spriteNum = 0;
            }

            spriteCounter = 0;
        }

    }
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        try {
            image = animationLoader.getAnimation(direction).get(spriteNum);
        } catch (NullPointerException e) {
            System.err.println("ERR: Animation name '" + direction + "' not found");
        }
        g2.drawImage(image, screenX + (flip * gp.tileSize), screenY, gp.tileSize + ((-gp.tileSize * flip) * 2), gp.tileSize, null);
    }
}
