package main;

import entity.Entity;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int hitXLeft = (entity.worldX + entity.solidAreaX.x) / (gp.tileSize*2);
        int hitXRight = (entity.worldX + entity.solidAreaX.x + entity.solidAreaX.width) / (gp.tileSize*2);
        int hitXTop = (entity.worldY + entity.solidAreaX.y) / (gp.tileSize*2);
        int hitXBottom = (entity.worldY + entity.solidAreaX.y + entity.solidAreaX.height) / (gp.tileSize*2);

        int hitYLeft = (entity.worldX + entity.solidAreaY.x) / (gp.tileSize*2);
        int hitYRight = (entity.worldX + entity.solidAreaY.x + entity.solidAreaY.width) / (gp.tileSize*2);
        int hitYTop = (entity.worldY + entity.solidAreaY.y) / (gp.tileSize*2);
        int hitYBottom = (entity.worldY + entity.solidAreaY.y + entity.solidAreaY.height) / (gp.tileSize*2);

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

    public int checkObject(Entity entity, boolean player){
        int index = -1;

        for (int i = 0; i < gp.obj.length; i++) {
            if (gp.obj[i] != null) {
                entity.solidAreaX.x = entity.worldX + entity.solidAreaX.x;
                entity.solidAreaX.y = entity.worldY + entity.solidAreaX.y;

                entity.solidAreaY.x = entity.worldX + entity.solidAreaY.x;
                entity.solidAreaY.y = entity.worldY + entity.solidAreaY.y;

                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                if (entity.direction.equals("up")) {
                    entity.solidAreaX.y -= entity.speed;
                    entity.solidAreaY.y -= entity.speed;
                    if (entity.solidAreaX.intersects(gp.obj[i].solidArea) || entity.solidAreaY.intersects(gp.obj[i].solidArea)) {
                        if (gp.obj[i].collision) {
                            entity.topHit = true;
                        }
                        if (player) {
                            index = i;
                        }
                    }
                }

                if (entity.direction.equals("down")) {
                    entity.solidAreaX.y += entity.speed;
                    entity.solidAreaY.y += entity.speed;
                    if (entity.solidAreaX.intersects(gp.obj[i].solidArea) || entity.solidAreaY.intersects(gp.obj[i].solidArea)) {
                        if (gp.obj[i].collision) {
                            entity.bottomHit = true;
                        }
                        if (player) {
                            index = i;
                        }
                    }
                }

                if (entity.direction.equals("left")) {
                    entity.solidAreaX.x -= entity.speed;
                    entity.solidAreaY.x -= entity.speed;
                    if (entity.solidAreaX.intersects(gp.obj[i].solidArea) || entity.solidAreaY.intersects(gp.obj[i].solidArea)) {
                        if (gp.obj[i].collision) {
                            entity.leftHit = true;
                        }
                        if (player) {
                            index = i;
                        }
                    }
                }

                if (entity.direction.equals("right")) {
                    entity.solidAreaX.x += entity.speed;
                    entity.solidAreaY.x += entity.speed;
                    if (entity.solidAreaX.intersects(gp.obj[i].solidArea) || entity.solidAreaY.intersects(gp.obj[i].solidArea)) {
                        if (gp.obj[i].collision) {
                            entity.rightHit = true;
                        }
                        if (player) {
                            index = i;
                        }
                    }
                }

                entity.solidAreaX.x = entity.solidAreaXDefaultX;
                entity.solidAreaX.y = entity.solidAreaXDefaultY;
                entity.solidAreaY.x = entity.solidAreaYDefaultX;
                entity.solidAreaY.y = entity.solidAreaYDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }

        }

        return index;
    }

    public int checkEntity(Entity entity, Entity[] target) {
        int index = -1;

        for (int i = 0; i < target.length; i++) {
            if (target[i] != null) {
                entity.solidAreaX.x = entity.worldX + entity.solidAreaX.x;
                entity.solidAreaX.y = entity.worldY + entity.solidAreaX.y;

                entity.solidAreaY.x = entity.worldX + entity.solidAreaY.x;
                entity.solidAreaY.y = entity.worldY + entity.solidAreaY.y;

                target[i].solidAreaX.x = target[i].worldX + target[i].solidAreaX.x;
                target[i].solidAreaX.y = target[i].worldY + target[i].solidAreaX.y;

                if (entity.direction.equals("up")) {
                    entity.solidAreaX.y -= entity.speed;
                    entity.solidAreaY.y -= entity.speed;
                    if (entity.solidAreaX.intersects(target[i].solidAreaX) || entity.solidAreaY.intersects(target[i].solidAreaX)) {
                        index = i;
                    }
                }

                if (entity.direction.equals("down")) {
                    entity.solidAreaX.y += entity.speed;
                    entity.solidAreaY.y += entity.speed;
                    if (entity.solidAreaX.intersects(target[i].solidAreaX) || entity.solidAreaY.intersects(target[i].solidAreaX)) {
                        index = i;
                    }
                }

                if (entity.direction.equals("left")) {
                    entity.solidAreaX.x -= entity.speed;
                    entity.solidAreaY.x -= entity.speed;
                    if (entity.solidAreaX.intersects(target[i].solidAreaX) || entity.solidAreaY.intersects(target[i].solidAreaX)) {
                        index = i;
                    }
                }

                if (entity.direction.equals("right")) {
                    entity.solidAreaX.x += entity.speed;
                    entity.solidAreaY.x += entity.speed;
                    if (entity.solidAreaX.intersects(target[i].solidAreaX) || entity.solidAreaY.intersects(target[i].solidAreaX)) {
                        index = i;
                    }
                }

                if (entity.direction.equals("idle")) {
                    if (entity.solidAreaX.intersects(target[i].solidAreaX) || entity.solidAreaY.intersects(target[i].solidAreaX)) {
                        index = i;
                    }
                }

                entity.solidAreaX.x = entity.solidAreaXDefaultX;
                entity.solidAreaX.y = entity.solidAreaXDefaultY;
                entity.solidAreaY.x = entity.solidAreaYDefaultX;
                entity.solidAreaY.y = entity.solidAreaYDefaultY;

                target[i].solidAreaX.x = target[i].solidAreaXDefaultX;
                target[i].solidAreaX.y = target[i].solidAreaXDefaultY;
                target[i].solidAreaY.x = target[i].solidAreaYDefaultX;
                target[i].solidAreaY.y = target[i].solidAreaYDefaultY;

            }

        }
        return index;
    }
}