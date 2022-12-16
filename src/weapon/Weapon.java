package weapon;

import entity.Player;
import main.AnimationLoader;
import main.GamePanel;
import main.KeyHandler;

import java.awt.*;

public abstract class Weapon {
    GamePanel gp;
    KeyHandler keyH;
    Player player;
    AnimationLoader animationLoader;
    public int damage;
    public int spriteCounter = 0;
    public int spriteNum = 0;

    public void setDamage() {
        this.damage = damage;
    }

    public int getDamage() {
        return this.damage;
    }

    public abstract void getWeaponImage();

    public abstract void draw(Graphics2D g2);
}
