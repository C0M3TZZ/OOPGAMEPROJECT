package main;

import entity.Entity;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int hitXLeft = (entity.worldX + entity.solidAreaX.x) / (gp.tileSize);
        int hitXRight = (entity.worldX + entity.solidAreaX.x + entity.solidAreaX.width) / (gp.tileSize);
        int hitXTop = (entity.worldY + entity.solidAreaX.y) / (gp.tileSize);
        int hitXBottom = (entity.worldY + entity.solidAreaX.y + entity.solidAreaX.height) / (gp.tileSize);

        int hitYLeft = (entity.worldX + entity.solidAreaY.x) / (gp.tileSize);
        int hitYRight = (entity.worldX + entity.solidAreaY.x + entity.solidAreaY.width) / (gp.tileSize);
        int hitYTop = (entity.worldY + entity.solidAreaY.y) / (gp.tileSize);
        int hitYBottom = (entity.worldY + entity.solidAreaY.y + entity.solidAreaY.height) / (gp.tileSize);

        int topHitPos1 = gp.tileM.mapTileNum[hitYLeft][hitYTop];
        int topHitPos2 = gp.tileM.mapTileNum[hitYRight][hitYTop];

        int bottomHitPos1 = gp.tileM.mapTileNum[hitYLeft][hitYBottom];
        int bottomHitPos2 = gp.tileM.mapTileNum[hitYRight][hitYBottom];

        int leftHitPos1 = gp.tileM.mapTileNum[hitXLeft][hitXTop];
        int leftHitPos2 = gp.tileM.mapTileNum[hitXLeft][hitXBottom];

        int rightHitPos1 = gp.tileM.mapTileNum[hitXRight][hitXTop];
        int rightHitPos2 = gp.tileM.mapTileNum[hitXRight][hitXBottom];


        if (gp.tileM.tile[topHitPos1].collision || gp.tileM.tile[topHitPos2].collision) {
            entity.topHit = true;
        } else {
            entity.topHit = false;
        }

        if (gp.tileM.tile[bottomHitPos1].collision || gp.tileM.tile[bottomHitPos2].collision) {
            entity.bottomHit = true;
        } else {
            entity.bottomHit = false;
        }

        if (gp.tileM.tile[leftHitPos1].collision || gp.tileM.tile[leftHitPos2].collision) {
            entity.leftHit = true;
        } else {
            entity.leftHit = false;
        }

        if (gp.tileM.tile[rightHitPos1].collision || gp.tileM.tile[rightHitPos2].collision) {
            entity.rightHit = true;
        } else {
            entity.rightHit = false;
        }
    }
}