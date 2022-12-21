package main;

import entity.Monster;

import java.util.Random;

public class MonsterSpawner {

    GamePanel gp;

    public MonsterSpawner(GamePanel gp){
        this.gp = gp;
    }

    public void spawnMonster() {
        Monster monster = new Monster(gp);

        Random random = new Random();

        int minX = gp.player.worldX + 100 - gp.player.screenX;
        int maxX = gp.player.worldX - 100 + gp.player.screenX;

        boolean from = random.nextBoolean();

        if (minX < 0) {
            minX = 0;
        }

        monster.worldX = random.nextInt(minX + maxX);

        if (monster.worldX <= 0) {
            spawnMonster();
            return;
        }

        if (from) {
            monster.worldY = gp.player.worldY - gp.player.screenY;
        } else {
            monster.worldY = gp.player.worldY + gp.player.screenY;
        }

        gp.cChecker.checkTile(monster);
        if (monster.topHit || monster.bottomHit || monster.leftHit || monster.rightHit) {
            spawnMonster();
        } else {
            gp.monster.add(monster);
        }

    }

}