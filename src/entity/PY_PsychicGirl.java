package entity;

import main.GamePanel;
import weapon.BigSword;

import java.awt.*;;

public class PY_PsychicGirl extends Player {
    BigSword bigSword;

    public PY_PsychicGirl(GamePanel gp) {
        super(gp);
        bigSword = new BigSword(gp);
        this.name = "Psychic Girl";
    }

    public void getPlayerImage() {
        try {
            // BASIC
            animationLoader.LoadAnimation("player/psychicGirl/basic.png", 0, 7, "idle");
            animationLoader.LoadAnimation("player/psychicGirl/basic.png", 8, 15, "up");
            animationLoader.LoadAnimation("player/psychicGirl/basic.png", 16, 23, "left");
            animationLoader.LoadAnimation("player/psychicGirl/basic.png", 24, 31, "down");
            animationLoader.LoadAnimation("player/psychicGirl/basic.png", 32, 39, "right");
            animationLoader.LoadAnimation("player/psychicGirl/basic.png", 40, 47, "dashUp");
            animationLoader.LoadAnimation("player/psychicGirl/basic.png", 48, 55, "dashLeft");
            animationLoader.LoadAnimation("player/psychicGirl/basic.png", 56, 63, "dashDown");
            animationLoader.LoadAnimation("player/psychicGirl/basic.png", 64, 71, "dashRight");

            // PRESS ULTI
            animationLoader.LoadAnimation("player/psychicGirl/pressUlti.png", 0, 7, "pressUlti", 128);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getUltimate() {
        if (keyH.xPressed && !pressUltimate) {
            spriteNum = 0;
            ultimate = true;
            pressUltimate = true;
        }
        if (ultimate) {
            if (!pressUltimate) {
                ultimate = false;
                direction = "idle";
            }
        }
    }

    public void update() {
        //RUN
        super.getDirection();

        // ULTIMATE
        this.getUltimate();

        //DASHING
        super.getDashing();

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
                    direction = "idle";
                }
                spriteNum = 0;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {
        image = animationLoader.getAnimation(direction).get(spriteNum);

        if (invincible) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        }
        if (direction.equals("pressUlti")) {
            g2.drawImage(image, screenX - gp.tileSize, screenY - gp.tileSize * 2, gp.tileSize * 2 * 2, gp.tileSize * 2 * 2, null);
        } else {
            g2.drawImage(image, screenX, screenY, gp.tileSize * 2, gp.tileSize * 2, null);
        }

        super.drawShadow(g2);

//        g2.setColor(Color.RED);
//        g2.draw(new Rectangle(screenX + solidAreaY.x, screenY + solidAreaY.y, solidAreaY.width, solidAreaY.height));
//        g2.setColor(Color.GREEN);
//        g2.draw(new Rectangle(screenX + solidAreaX.x, screenY + solidAreaX.y, solidAreaX.width, solidAreaX.height));
    }
}
