package object;

import entity.Projectile;
import main.AnimationLoader;
import main.GamePanel;

public class OBJ_SwordAtk extends Projectile {

    GamePanel gp;


    public OBJ_SwordAtk(GamePanel gp) {
        super(gp);
        this.gp = gp;


        this.speed = 10;
        this.maxLife = 80;
        this.alive = false;
        getImage();
    }

    public void getImage() {
        try {
            animationLoader.LoadAnimation("player/player1.png", 0, 3, "up");
            animationLoader.LoadAnimation("player/player1.png", 4, 7, "left");
            animationLoader.LoadAnimation("player/player1.png", 4, 7, "right");
            animationLoader.LoadAnimation("player/player1.png", 8, 11, "down");
            animationLoader.LoadAnimation("player/player1.png", 12, 15, "idle");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
