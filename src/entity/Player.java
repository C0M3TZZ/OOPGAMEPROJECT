package entity;

import main.AnimationLoader;
import main.GamePanel;
import main.KeyHandler;
import main.MouseHandler;
import object.OBJ_SwordAtk;
import weapon.BigSword;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {
    KeyHandler keyH;

    MouseHandler mouseH;
    BigSword bigSword;
    public final int screenX;
    public final int screenY;
    public int dashingCounter;
    public boolean dashing;
    public Projectile projectile;

    int attackCounter = 0;

    public Player(GamePanel gp, KeyHandler keyH, MouseHandler mouseH) {
        super(gp);
        this.keyH = keyH;
        this.mouseH = mouseH;
        this.maxLife = 10;
        this.life = this.maxLife;
        this.animationLoader = new AnimationLoader(gp);
        bigSword = new BigSword(gp, keyH);

        screenX = (gp.screenWidth / 2) - gp.tileSize;

        screenY = (gp.screenHeight / 2) - gp.tileSize;

        solidAreaX = new Rectangle(4 + 32 + 4 + 4, 8 + 56 + 28, 40, 50 - 28);

        solidAreaXDefaultX = solidAreaX.x;
        solidAreaXDefaultY = solidAreaX.y;

        solidAreaY = new Rectangle(8 + 32 + 4 + 4, 5 + 56 + 28, 32, 55 - 28);

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

        projectile = new OBJ_SwordAtk(gp);
    }

    public void getPlayerImage() {
        try {
            animationLoader.LoadAnimation("player/player1.png", 0, 3, "up");
            animationLoader.LoadAnimation("player/player1.png", 4, 7, "left");
            animationLoader.LoadAnimation("player/player1.png", 4, 7, "right");
            animationLoader.LoadAnimation("player/player1.png", 8, 11, "down");
            animationLoader.LoadAnimation("player/player1.png", 12, 15, "idle");
            animationLoader.LoadAnimation("player/player1.png", 16, 19, "dashUp");
            animationLoader.LoadAnimation("player/player1.png", 20, 23, "dashLeft");
            animationLoader.LoadAnimation("player/player1.png", 20, 23, "dashRight");
            animationLoader.LoadAnimation("player/player1.png", 24, 27, "dashDown");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void touchObject(int index) {
        if (index != -1) {
            if (gp.obj[index].pickUpable) {
                pickUpItem(index);
            } else {
                gp.obj[index].action();
                System.out.println("You can't pick up this item");
            }
        }
    }

    public void pickUpItem(int index) {
        if (index != -1) {
            gp.obj[index].action();
            gp.obj[index] = null;
        }
    }

    public void update() {
        if (life <= 0) {
            return;
        }
        gp.cChecker.checkTile(this);
        int objectIndex = gp.cChecker.checkObject(this, true);

        touchObject(objectIndex);

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

        if (mouseH.leftClick && !projectile.alive && attackCounter <= 0) {
            Point mousePos = gp.getMousePosition();
            if (mousePos != null) {
                double mouseX = mousePos.getX();
                double mouseY = mousePos.getY();

                double angle = Math.atan2(mouseY - screenY, mouseX - screenX);
                projectile.set(worldX, worldY, angle, direction, true, this);
                attackCounter = 30;

                gp.projectileList.add(projectile);
            }
        }
        if (attackCounter > 0) {
            attackCounter--;
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
        bigSword.draw(g2);
        g2.setColor(Color.RED);
        g2.draw(new Rectangle(screenX + solidAreaY.x, screenY + solidAreaY.y, solidAreaY.width, solidAreaY.height));
        g2.setColor(Color.GREEN);
        g2.draw(new Rectangle(screenX + solidAreaX.x, screenY + solidAreaX.y, solidAreaX.width, solidAreaX.height));
    }
}
