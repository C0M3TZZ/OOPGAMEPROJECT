package object;

import entity.Projectile;
import main.GamePanel;

import java.awt.*;

public class OBJ_UltPG extends Projectile {
    public OBJ_UltPG(GamePanel gp) {
        super(gp);
        this.attack = 10;
        this.speed = 20;
        this.maxLife = 50;
        this.alive = false;
        this.solidAreaX = new Rectangle(0, 0, 128, 128);
        this.solidAreaY = new Rectangle(0, 0, 128, 128);
        getProjectileImage();
    }


    public void getProjectileImage() {
        try {
            animationLoader.LoadAnimation("player/psychicGirl/pressUlti.png", 7, 7, "down");
            animationLoader.LoadAnimation("player/psychicGirl/pressUlti.png", 7, 7, "up");
            animationLoader.LoadAnimation("player/psychicGirl/pressUlti.png", 7, 7, "left");
            animationLoader.LoadAnimation("player/psychicGirl/pressUlti.png", 7, 7, "right");
            animationLoader.LoadAnimation("player/psychicGirl/pressUlti.png", 7, 7, "idle");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
