package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public BufferedImage run[], idle[];
    public final int screenX;
    public final int screenY;
    public String filePath;

    public Player(GamePanel gp, KeyHandler keyH, String id) {
        this.gp = gp;
        this.keyH = keyH;
        this.id = id;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle(0, 0, gp.tileSize, gp.tileSize);

        setDefaultValues();
        getPlayerImage(id);
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(String id) {
        try {
            run = new BufferedImage[4];
            idle = new BufferedImage[4];
            
            for (int i = 1; i <= 4; i++) {
                filePath = "res/player/" + id + "/run/" + i + ".png";
                run[i-1] = ImageIO.read(new FileInputStream(filePath));
                
                filePath = "res/player/" + id + "/idle/" + i + ".png";
                idle[i-1] = ImageIO.read(new FileInputStream(filePath));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed) {
                direction = "up";
                worldY -= speed;
            } else if (keyH.downPressed) {
                direction = "down";
                worldY += speed;
            }
            if (keyH.leftPressed) {
                direction = "left";
                worldX -= speed;
            } else if (keyH.rightPressed) {
                direction = "right";
                worldX += speed;
            }

            collisionOn = false;
            gp.cChecker.checkTile(this);

            updateSprite();
        } else if (!keyH.upPressed && !keyH.downPressed && !keyH.leftPressed && !keyH.rightPressed) {
            direction = "idle";
            updateSprite();
        }
    }

    public void updateSprite() {
        spriteCounter++;
        if (spriteCounter > 8) {
            spriteNum++;
            if (spriteNum > 3) {
                spriteNum = 0;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "right":
                image = run[spriteNum];
                g2.drawImage(image, screenX, screenY - (gp.tileSize/2), gp.tileSize, gp.tileSize * 2, null);
                break;
            case "left":
                image = run[spriteNum];
                g2.drawImage(image, screenX + gp.tileSize, screenY - (gp.tileSize/2), -gp.tileSize, gp.tileSize * 2, null);
                break;
            case "up":
                image = run[spriteNum];
                g2.drawImage(image, screenX, screenY - (gp.tileSize/2), gp.tileSize, gp.tileSize * 2, null);
                break;
            case "idle":
                image = idle[spriteNum];
                g2.drawImage(image, screenX + gp.tileSize, screenY - (gp.tileSize/2), -gp.tileSize, gp.tileSize * 2, null);
                break;
        }
    }
}
