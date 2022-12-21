package weapon;

import entity.Player;
import main.AnimationLoader;
import main.GamePanel;

public class BigSword extends Weapon {
    public BigSword(GamePanel gp) {
        this.gp = gp;
        this.animationLoader = new AnimationLoader(gp);
        getWeaponImage();
    }

    @Override
    public void getWeaponImage() {
        try {
            animationLoader.LoadAnimation("weapon/BigSword.png", 0, 3, "up");
            animationLoader.LoadAnimation("weapon/BigSword.png", 4, 7, "left");
            animationLoader.LoadAnimation("weapon/BigSword.png", 4, 7, "right");
            animationLoader.LoadAnimation("weapon/BigSword.png", 8, 11, "down");
            animationLoader.LoadAnimation("weapon/BigSword.png", 12, 15, "idle");
            animationLoader.LoadAnimation("weapon/BigSword.png", 16, 19, "dashUp");
            animationLoader.LoadAnimation("weapon/BigSword.png", 20, 23, "dashLeft");
            animationLoader.LoadAnimation("weapon/BigSword.png", 20, 23, "dashRight");
            animationLoader.LoadAnimation("weapon/BigSword.png", 24, 27, "dashDown");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
