package entity;

import main.GamePanel;
import main.KeyHandler;
import main.MouseHandler;
import object.OBJ_BookAtk;
import weapon.BigSword;

import java.awt.*;

public class PY_BankSensei extends Player {
    BigSword bigSword;
    public PY_BankSensei(GamePanel gp) {
        super(gp);
        bigSword = new BigSword(gp);
        this.projectile = new OBJ_BookAtk(gp);
        this.name = "Bank Sensei";
    }

    @Override
    public void getPlayerImage() {
        try {
            // BASIC
            animationLoader.LoadAnimation("player/bankSensei/basic.png", 0, 7, "idle");
            animationLoader.LoadAnimation("player/bankSensei/basic.png", 8, 15, "up");
            animationLoader.LoadAnimation("player/bankSensei/basic.png", 16, 23, "left");
            animationLoader.LoadAnimation("player/bankSensei/basic.png", 16, 23, "right");
            animationLoader.LoadAnimation("player/bankSensei/basic.png", 24, 31, "down");
            animationLoader.LoadAnimation("player/bankSensei/basic.png", 32, 39, "dashUp");
            animationLoader.LoadAnimation("player/bankSensei/basic.png", 40, 47, "dashLeft");
            animationLoader.LoadAnimation("player/bankSensei/basic.png", 40, 47, "dashRight");
            animationLoader.LoadAnimation("player/bankSensei/basic.png", 48, 55, "dashDown");

            // PRESS ULTI
            animationLoader.LoadAnimation("player/bankSensei/pressUlti.png", 0, 7, "pressUlti");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getUltimate() {
        if (keyH.xPressed && !pressUltimate) {
            spriteNum = 0;
            pressUltimate = true;
            ultimateStart = System.currentTimeMillis();
        }
        if (ultimate) {
            ultimateEnd = System.currentTimeMillis();
            if ((ultimateEnd-ultimateStart)/1000 == 10) {
                ultimate = false;
                direction = "idle";
            }
        }
    }

    public void update() {
        //RUN
        if (!ultimate) {
            super.getDirection();
        }

        // ULTIMATE
//        this.getUltimate();

        //DASHING
        if (!ultimate) {
            super.getDashing();
        }

        if (life <= 0) {
            gp.gameState = gp.dideState;
        }
        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 30) {
                System.out.println("Turning invincible off");
                invincible = false;
                invincibleCounter = 0;
            }
        }

        int objectIndex = gp.cChecker.checkObject(this, true);
        touchObject(objectIndex);

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

        // UPDATE

        // ULTIMATE
        if (pressUltimate) {
            direction = "pressUlti";
        }

        // update animation
        if (!ultimate) {
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
                        ultimate = true;
                    }
                    spriteNum = 0;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {
        if (!ultimate) {
            image = animationLoader.getAnimation(direction).get(spriteNum);
        }

        if (invincible) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        }
        if (direction.equals("left") || direction.equals("dashLeft") || direction.equals("leftUlti") || direction.equals("dashLeftUlti")) {
            g2.drawImage(image, screenX + (gp.tileSize * 2), screenY, -gp.tileSize * 2, gp.tileSize * 2, null);
        }
        else if (!pressUltimate && ultimate) {
            image = animationLoader.getAnimation("pressUlti").get(7);
            g2.drawImage(image, screenX, screenY, gp.tileSize * 2, gp.tileSize * 2, null);
        }
        else {
            g2.drawImage(image, screenX, screenY, gp.tileSize * 2, gp.tileSize * 2, null);
        }

        super.drawShadow(g2);

//        g2.setColor(Color.RED);
//        g2.draw(new Rectangle(screenX + solidAreaY.x, screenY + solidAreaY.y, solidAreaY.width, solidAreaY.height));
//        g2.setColor(Color.GREEN);
//        g2.draw(new Rectangle(screenX + solidAreaX.x, screenY + solidAreaX.y, solidAreaX.width, solidAreaX.height));
    }
}
