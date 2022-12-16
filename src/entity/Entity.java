package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX;
    public int worldY;
    public int speed;

    public String direction;

    public int flip = 1;

    public int spriteCounter = 0;
    public int spriteNum = 0;
    public Rectangle solidArea;

    public Rectangle hitBoxX;

    public boolean topHit = false;
    public boolean bottomHit = false;
    public boolean leftHit = false;
    public boolean rightHit = false;
}
