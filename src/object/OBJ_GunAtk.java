package object;

import entity.Projectile;
import main.GamePanel;

import java.awt.*;

public class OBJ_GunAtk extends Projectile {

    GamePanel gp;

    public OBJ_GunAtk(GamePanel gp) {
        super(gp);
        this.speed = 10;
        this.maxLife = 80;
        this.alive = false;
        this.solidAreaX = new Rectangle(0, 0, 64, 64);
        this.solidAreaY = new Rectangle(0, 0, 64, 64);
        getImage();
    }

    public void getImage() {
        try {
            animationLoader.LoadAnimation("effects/gunProjectileSheet.png", 0, 4, "up");
            animationLoader.LoadAnimation("effects/gunProjectileSheet.png", 0, 4, "left");
            animationLoader.LoadAnimation("effects/gunProjectileSheet.png", 0, 4, "right");
            animationLoader.LoadAnimation("effects/gunProjectileSheet.png", 0, 4, "down");
            animationLoader.LoadAnimation("effects/gunProjectileSheet.png", 0, 4, "idle");
        } catch (Exception e) {
            e.printStackTrace();
}}}
