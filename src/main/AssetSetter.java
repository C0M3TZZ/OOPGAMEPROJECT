package main;

import entity.Monster;
import entity.Player;
import object.OBJ_001;

public class AssetSetter {
    GamePanel gp;
    Player player;
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setMonster() {
        Monster newMon = new Monster(gp);
        newMon.worldX = gp.tileSize*21;
        newMon.worldY = gp.tileSize*21;
        gp.monster.add(newMon);
    }

    public void setObject() {
        gp.obj[0] = new OBJ_001();
        gp.obj[0].worldX = gp.tileSize*21;
        gp.obj[0].worldY = gp.tileSize*21;
    }
}
