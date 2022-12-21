package entity;

import main.GamePanel;
import main.KeyHandler;
import main.MouseHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    KeyHandler keyH;
    MouseHandler mouseH;
    public final int screenX, screenY;
    public boolean dashing;
    public int dashingCounter;
    public boolean ultimate, pressUltimate;
    public long ultimateStart, ultimateEnd;
    BufferedImage image, shadow;

    public Player(GamePanel gp) {
        super(gp);
        this.keyH = gp.keyH;
        this.mouseH = gp.mouseH;
        try {
            this.shadow = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/shadow.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.screenX = (gp.screenWidth / 2) - gp.tileSize;
        this.screenY = (gp.screenHeight / 2) - gp.tileSize;

        solidAreaX = new Rectangle(4 + 32 + 4 + 4, 8 + 56 + 28, 40, 48 - 28);
        solidAreaY = new Rectangle(8 + 32 + 4 + 4, 4 + 56 + 28, 32, 48 - 28 + 4 + 4);

        solidAreaXDefaultX = solidAreaX.x;
        solidAreaXDefaultY = solidAreaX.y;

        solidAreaYDefaultX = solidAreaY.x;
        solidAreaYDefaultY = solidAreaY.y;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "idle";
    }

    public void getPlayerImage() {};

    public void getDirection() {
        gp.cChecker.checkTile(this);
        if (keyH.upPressed) {
            if (!dashing) {
                if (ultimate) {
                    direction = "upUlti";
                }
                else {
                    direction = "up";
                }
            }
            if (!topHit && !pressUltimate) {
                worldY -= speed;
            }
        }
        if (keyH.downPressed) {
            if (!dashing) {
                if (ultimate) {
                    direction = "downUlti";
                }
                else {
                    direction = "down";
                }
            }
            if (!bottomHit && !pressUltimate) {
                worldY += speed;
            }
        }
        if (keyH.rightPressed) {
            if (!dashing) {
                if (ultimate) {
                    direction = "rightUlti";
                }
                else {
                    direction = "right";
                }
            }
            if (!rightHit && !pressUltimate) {
                worldX += speed;
            }
        }
        if (keyH.leftPressed) {
            if (!dashing) {
                if (ultimate) {
                    direction = "leftUlti";
                }
                else {
                    direction = "left";
                }
            }
            if (!leftHit && !pressUltimate) {
                worldX -= speed;
            }
        }
        if (!keyH.upPressed && !keyH.downPressed && !keyH.leftPressed && !keyH.rightPressed) {
            if (!dashing) {
                if (ultimate) {
                    direction = "idleUlti";
                }
                else {
                    direction = "idle";
                }
            }
        }
    }

    public void getUltimate() {
        if (keyH.xPressed && !pressUltimate) {
            spriteNum = 0;
            ultimate = true;
            pressUltimate = true;
            ultimateStart = System.currentTimeMillis();
        }
        if (ultimate) {
            ultimateEnd = System.currentTimeMillis();
            if ((ultimateEnd-ultimateStart)/1000 == 10) {
                ultimate = false;
            }
        }
    }

    public void getDashing() {
        gp.cChecker.checkTile(this);
        if (keyH.shiftPressed && direction != "idle" && !pressUltimate) {
            if (!dashing) {
                spriteNum = 0;
            }
            dashing = true;
            dashingCounter++;
        }
        if (!keyH.shiftPressed) {
            dashingCounter = 0;
        }
        if (dashingCounter > 0 && dashingCounter <= 20) {
            if (keyH.upPressed) {
                if (!ultimate) {
                    direction = "dashUp";
                }
                else {
                    direction = "dashUpUlti";
                }
                if (!topHit) {
                    worldY -= speed;
                }
            }
            if (keyH.downPressed) {
                if (!ultimate) {
                    direction = "dashDown";
                }
                else {
                    direction = "dashDownUlti";
                }
                if (!bottomHit) {
                    worldY += speed;
                }
            }
            if (keyH.leftPressed) {
                if (!ultimate) {
                    direction = "dashLeft";
                }
                else {
                    direction = "dashLeftUlti";
                }
                if (!leftHit) {
                    worldX -= speed;
                }
            }
            if (keyH.rightPressed) {
                if (!ultimate) {
                    direction = "dashRight";
                }
                else {
                    direction = "dashRightUlti";
                }
                if (!rightHit) {
                    worldX += speed;
                }
            }
        }
        if (!keyH.shiftPressed) {
            if (dashingCounter < 20 && dashing) {
                dashingCounter++;
            }
        }
    }

    public void update() {
        // ULTIMATE
        if (pressUltimate) {
            direction = "pressUlti";
        }

        // update animation
        spriteCounter++;
        if (spriteCounter > spriteCounterLimit) {
            if (spriteNum != animationLoader.getAnimation(direction).size() - 1) {
                spriteNum++;
            } else {
                if (dashing) {
                    dashing = false;
                }
                if (pressUltimate) {
                    pressUltimate = false;
                }
                spriteNum = 0;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {
        image = animationLoader.getAnimation(direction).get(spriteNum);
        if (direction.equals("left") || direction.equals("dashLeft") || direction.equals("leftUlti") || direction.equals("dashLeftUlti")) {
            g2.drawImage(image, screenX + (gp.tileSize * 2), screenY, -gp.tileSize * 2, gp.tileSize * 2, null);
        } else {
            g2.drawImage(image, screenX, screenY, gp.tileSize * 2, gp.tileSize * 2, null);
        }

        g2.setColor(Color.RED);
        g2.draw(new Rectangle(screenX + solidAreaY.x, screenY + solidAreaY.y, solidAreaY.width, solidAreaY.height));
        g2.setColor(Color.GREEN);
        g2.draw(new Rectangle(screenX + solidAreaX.x, screenY + solidAreaX.y, solidAreaX.width, solidAreaX.height));
    }

    public void drawShadow(Graphics2D g2) {
        g2.drawImage(shadow, 0, 0, gp.screenWidth, gp.screenHeight, null);
    }
}
