package object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

abstract public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0, solidAreaDefaultY = 0;

    public boolean pickUpable = false;

    public void draw(Graphics2D g2, GamePanel gp) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        g2.setColor(Color.RED);
        g2.draw(new Rectangle(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height));
    }

    abstract public void action();
}
