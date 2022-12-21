package main;

<<<<<<< HEAD
import object.OBJ_HealthPotion;
=======
import entity.Monster;
import entity.Player;
import object.OBJ_001;
>>>>>>> b118af326173c0b4da0b20a4ad53e539b211e5ca

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new OBJ_HealthPotion(gp);
        gp.obj[0].worldX = gp.tileSize * 21;
        gp.obj[0].worldY = gp.tileSize * 21;
    }

    public void setObject() {
        gp.obj[0] = new OBJ_001();
        gp.obj[0].worldX = gp.tileSize*21;
        gp.obj[0].worldY = gp.tileSize*21;
    }
}
