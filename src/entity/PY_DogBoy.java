package entity;

import main.GamePanel;
import main.KeyHandler;
import weapon.BigSword;

import java.awt.*;;

public class PY_DogBoy extends Player {
    BigSword bigSword;

    public PY_DogBoy(GamePanel gp) {
        super(gp);
        bigSword = new BigSword(gp);
        this.name = "Wolf Boy";
    }

    public void getPlayerImage() {
        try {
            // BASIC
            animationLoader.LoadAnimation("player/dogBoy/basic.png", 0, 7, "idle");
            animationLoader.LoadAnimation("player/dogBoy/basic.png", 8, 15, "up");
            animationLoader.LoadAnimation("player/dogBoy/basic.png", 16, 23, "left");
            animationLoader.LoadAnimation("player/dogBoy/basic.png", 16, 23, "right");
            animationLoader.LoadAnimation("player/dogBoy/basic.png", 24, 31, "down");
            animationLoader.LoadAnimation("player/dogBoy/basic.png", 32, 39, "dashUp");
            animationLoader.LoadAnimation("player/dogBoy/basic.png", 40, 47, "dashLeft");
            animationLoader.LoadAnimation("player/dogBoy/basic.png", 40, 47, "dashRight");
            animationLoader.LoadAnimation("player/dogBoy/basic.png", 48, 55, "dashDown");

            // PRESS ULTI
            animationLoader.LoadAnimation("player/dogBoy/pressUlti.png", 0, 9, "pressUlti");

            // ULTI
            animationLoader.LoadAnimation("player/dogBoy/ulti.png", 0, 7, "idleUlti");
            animationLoader.LoadAnimation("player/dogBoy/ulti.png", 8, 15, "upUlti");
            animationLoader.LoadAnimation("player/dogBoy/ulti.png", 16, 23, "leftUlti");
            animationLoader.LoadAnimation("player/dogBoy/ulti.png", 16, 23, "rightUlti");
            animationLoader.LoadAnimation("player/dogBoy/ulti.png", 24, 31, "downUlti");
            animationLoader.LoadAnimation("player/dogBoy/ulti.png", 32, 39, "dashUpUlti");
            animationLoader.LoadAnimation("player/dogBoy/ulti.png", 40, 47, "dashLeftUlti");
            animationLoader.LoadAnimation("player/dogBoy/ulti.png", 40, 47, "dashRightUlti");
            animationLoader.LoadAnimation("player/dogBoy/ulti.png", 48, 55, "dashDownUlti");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        //RUN
        super.getDirection();

        // ULTIMATE
//        super.getUltimate();

        //DASHING
        super.getDashing();

        // UPDATE
        super.update();
    }

    public void useUlti() {
        this.projectile.attack = 3;
    }

    public void endUlti() {
        this.projectile.attack = attack;
    }

    public void draw(Graphics2D g2) {
        super.draw(g2);
        bigSword.draw(g2);
        super.drawShadow(g2);
    }
}
