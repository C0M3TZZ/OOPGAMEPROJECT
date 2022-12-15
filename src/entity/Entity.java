package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {

    public int worldX, worldY;
    public int speed;

    public String id;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 0;
    
    public Rectangle solidArea;
    public boolean collisionOn = false;
}
