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
            animationLoader.LoadAnimation("weapon/bigSword.png", 0, 7, "idle");
            animationLoader.LoadAnimation("weapon/bigSword.png", 8, 15, "up");
            animationLoader.LoadAnimation("weapon/bigSword.png", 16, 23, "left");
            animationLoader.LoadAnimation("weapon/bigSword.png", 16, 23, "right");
            animationLoader.LoadAnimation("weapon/bigSword.png", 24, 31, "down");
            animationLoader.LoadAnimation("weapon/bigSword.png", 32, 39, "dashUp");
            animationLoader.LoadAnimation("weapon/bigSword.png", 40, 47, "dashLeft");
            animationLoader.LoadAnimation("weapon/bigSword.png", 40, 47, "dashRight");
            animationLoader.LoadAnimation("weapon/bigSword.png", 48, 55, "dashDown");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
