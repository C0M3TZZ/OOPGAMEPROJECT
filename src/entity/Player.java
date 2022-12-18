package entity;

import main.AnimationLoader;
import main.GamePanel;
import main.KeyHandler;
import weapon.BigSword;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Player extends Entity {
    KeyHandler keyH;
    public final int screenX, screenY;
    public int dashingCounter;
    public boolean dashing;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;

        this.screenX = (gp.screenWidth / 2) - gp.tileSize;
        this.screenY = (gp.screenHeight / 2) - gp.tileSize;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "idle";
    }

    public abstract void getPlayerImage();

    public void update() {
        gp.cChecker.checkTile(this);

        // RUN
        if (keyH.upPressed) {
            direction = "up";
            if (!topHit) {
                worldY -= speed;
            }
        }
        if (keyH.downPressed) {
            direction = "down";
            if (!bottomHit) {
                worldY += speed;
            }
        }
        if (keyH.rightPressed) {
            direction = "right";
            if (!rightHit) {
                worldX += speed;
            }
        }
        if (keyH.leftPressed) {
            direction = "left";
            if (!leftHit) {
                worldX -= speed;
            }
        }
        if (!keyH.upPressed && !keyH.downPressed && !keyH.leftPressed && !keyH.rightPressed) {
            direction = "idle";
        }

        // DASH
        gp.cChecker.checkTile(this);
        if (keyH.shiftPressed && direction != "idle") {
            if (dashingCounter == 0) {
                spriteNum = 0;
            }
            dashingCounter++;
            dashing = true;
        }
        if (dashingCounter > 0 && dashingCounter <= 20) {
            switch (direction) {
                case "up":
                    direction = "dashUp";
                    if (!topHit) {
                        worldY -= speed;
                    }
                    break;
                case "down":
                    direction = "dashDown";
                    if (!bottomHit) {
                        worldY += speed;
                    }
                    break;
                case "left":
                    direction = "dashLeft";
                    if (!leftHit) {
                        worldX -= speed;
                    }
                    break;
                case "right":
                    direction = "dashRight";
                    if (!rightHit) {
                        worldX += speed;
                    }
                    break;
            }
        }
        if (!keyH.shiftPressed) {
            if (dashingCounter < 20 && dashing) {
                dashingCounter++;
            } else {
                dashingCounter = 0;
                dashing = false;
            }
        }

        // update animation
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
        BufferedImage image = animationLoader.getAnimation(direction).get(spriteNum);
        if (direction.equals("left") || direction.equals("dashLeft")) {
            g2.drawImage(image, screenX + (gp.tileSize * 2), screenY, -gp.tileSize * 2, gp.tileSize * 2, null);
        } else {
            g2.drawImage(image, screenX, screenY, gp.tileSize * 2, gp.tileSize * 2, null);
        }

        g2.setColor(Color.RED);
        g2.draw(new Rectangle(screenX + solidAreaY.x, screenY + solidAreaY.y, solidAreaY.width, solidAreaY.height));
        g2.setColor(Color.GREEN);
        g2.draw(new Rectangle(screenX + solidAreaX.x, screenY + solidAreaX.y, solidAreaX.width, solidAreaX.height));
    }
}
