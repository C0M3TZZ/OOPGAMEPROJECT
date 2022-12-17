package main;

import entity.Monster;
import entity.Player;

public class AssetSetter {
    GamePanel gp;
    Player player;
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setMonster() {
        gp.monster[0] = new Monster(gp);
        gp.monster[0].worldX = gp.tileSize*21;
        gp.monster[0].worldY = gp.tileSize*21;
    }
}
