package object;

<<<<<<< HEAD
import main.AnimationLoader;
=======
>>>>>>> b118af326173c0b4da0b20a4ad53e539b211e5ca
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

<<<<<<< HEAD
public class SuperObject {
    GamePanel gp;
    AnimationLoader animationLoader;
=======
abstract public class SuperObject {
>>>>>>> b118af326173c0b4da0b20a4ad53e539b211e5ca
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
<<<<<<< HEAD
    public int spriteCounter, spriteNum;
    String animationName;

    public SuperObject(GamePanel gp) {
        this.gp = gp;
        this.animationLoader = new AnimationLoader(gp);
    }

    public void update() {
        spriteCounter++;
        if (spriteCounter > 8) {
            if (spriteNum != animationLoader.getAnimation(animationName).size() - 1) {
                spriteNum++;
            } else {
                spriteNum = 0;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2, GamePanel gp, String animationName) {
        this.animationName = animationName;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        image = animationLoader.getAnimation(animationName).get(spriteNum);
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
=======
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
>>>>>>> b118af326173c0b4da0b20a4ad53e539b211e5ca
}
