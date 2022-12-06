package entity;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;
public class Player extends Entity{
	GamePanel gp;
    KeyHandler keyH;
    
    public final int screenX;
    public final int screenY;
    
    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        
        screenX = gp.screenWidth/2 - (gp.tileSize /2);
        screenY = gp.screenHeight/2  - (gp.tileSize /2);
        
        solidArea = new Rectangle();
        solidArea.x = 13;
        solidArea.y = 26;
        solidArea.width = 38;
   		solidArea.height = 38;
        
        setDefaultValues();
        getPlayerImage();
    }
    
    public void setDefaultValues(){
        worldX = gp.tileSize * 14;
        worldY = gp.tileSize * 14;
        speed = 4;
        direction = "down";
    }
    public void getPlayerImage(){
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/test_up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/test_up2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/test_down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/test_down2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/test_left.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/test_left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/test_right.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/test_right2.png"));
            idle = ImageIO.read(getClass().getResourceAsStream("/player/test_idle.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void update(){
    	if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
    		
    	if (keyH.upPressed == true && keyH.rightPressed == true){
            direction = "upright";
        }
    	else if (keyH.upPressed == true && keyH.leftPressed == true){
            direction = "upleft";
        }
    	else if (keyH.downPressed == true && keyH.rightPressed == true){
            direction = "downright";
        }
    	else if (keyH.downPressed == true && keyH.leftPressed == true){
            direction = "downleft";
        }
    	else if (keyH.upPressed == true){
            direction = "up";
        }
        else if (keyH.downPressed == true){
            direction = "down";
        }
        else if (keyH.leftPressed == true){
            direction = "left";
        }
        else if (keyH.rightPressed == true){
            direction = "right";
        }
        else {
        	direction = "idle";
        }
        // CHECK TILE COLLISION
        collisionOn = false;
        gp.cChecker.checkTile(this);
        
        // IF COLLISION IS FALSE, PLAYER CAN MOVE
        if(collisionOn == false) {
        	switch(direction) {
        	case "upright":
        		worldY -= speed;
        		worldX += speed;
        		break;
        	case "upleft":
        		worldY -= speed;
        		worldX -= speed;
        		break;
        	case "downright":
        		worldY += speed;
        		worldX += speed;
        		break;
        	case "downleft":
        		worldY += speed;
        		worldX -= speed;
        		break;
        	case "up":
        		worldY -= speed;
        		break;
        	case "down":
        		worldY += speed;
        		break;
        	case "left":
        		worldX -= speed;
        		break;
        	case "right":
        		worldX += speed;
        		break;
        	}
        	
        }
        
        spriteCounter++;
        if(spriteCounter > 10) {
        	if(spriteNum == 1) {
        		spriteNum  = 2;
        		spriteCounter = 0;
        	}
        	else if(spriteNum == 2) {
        		spriteNum  = 1;
        		spriteCounter = 0;
        	}
        }
    }
    }
    public void draw(Graphics2D g2){
        BufferedImage image  = null;
        switch(direction){
        	case "upright":
        	if(spriteNum == 1) {
        		image = up1;
        	}
        	else if (spriteNum == 2){
        		image = up2;
        	}
        	break;
        	case "upleft":
            	if(spriteNum == 1) {
            		image = up1;
            	}
            	else if (spriteNum == 2){
            		image = up2;
            	}
            	break;
        	case "downright":
            	if(spriteNum == 1) {
            		image = down1;
            	}
            	else if (spriteNum == 2){
            		image = down2;
            	}
            	break;
        	case "downleft":
            	if(spriteNum == 1) {
            		image = down1;
            	}
            	else if (spriteNum == 2){
            		image = down2;
            	}
            	break;
            case "up":
            	if(spriteNum == 1) {
            		image = up1;
            	}
            	else if (spriteNum == 2){
            		image = up2;
            	}
            	break;
            case "down": 
            	if(spriteNum == 1) {
            		image = down1;
                }
                else if (spriteNum == 2){
                	image = down2;
                }
            	break;
            case "left": 
            	if(spriteNum == 1) {
            		image = left1;
                }
                else if (spriteNum == 2){
                	image = left2;
                }
            	break;
            case "right": 
            	if(spriteNum == 1) {
            		image = right1;
                }
                else if (spriteNum == 2){
                	image = right2;
                }
            	break;
            case "idle": 
            	image = idle;
            	break;
        }
        
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
