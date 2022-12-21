package object;

import main.GamePanel;

public class OBJ_HealthPotion extends SuperObject {
    public OBJ_HealthPotion(GamePanel gp) {
        super(gp);
        this.name = "Health Potion";
        this.pickUpable = true;
        getImage();
    }

    @Override
    public void action() {
        if ((gp.player.life += 1) > gp.player.maxLife) {
            gp.player.life = gp.player.maxLife;
        } else {
            gp.player.life += 1;
        }
    }

    public void getImage() {
        try {
            animationLoader.LoadAnimation("objects/healthPotion.png", 0, 3, "healthPotion");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
