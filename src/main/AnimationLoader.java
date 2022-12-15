package main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class AnimationLoader {
    HashMap<String, ArrayList<BufferedImage>> animationMap = new HashMap<>();
    GamePanel gp;

    public AnimationLoader(GamePanel gp) {
        this.gp = gp;
    }

    public ArrayList<BufferedImage> getAnimation(String animationName) {
        return animationMap.get(animationName);
    }

    public void LoadAnimation(String path, int start, int end, String name) {
        try {
            System.out.println("Loading animation: " + name + " from " + path);
            BufferedImage spriteSheet = ImageIO.read(getClass().getClassLoader().getResourceAsStream(path));
            for (int i = start; i <= end; i++) {
                BufferedImage image = spriteSheet.getSubimage(i * gp.originalTileSize, 0, gp.originalTileSize, gp.originalTileSize);
                if (animationMap.containsKey(name)) {
                    animationMap.get(name).add(image);
                } else {
                    ArrayList<BufferedImage> images = new ArrayList<>();
                    images.add(image);
                    animationMap.put(name, images);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
