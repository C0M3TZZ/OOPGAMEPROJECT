package main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class AnimationLoader {
    HashMap<String, ArrayList<BufferedImage>> animationMap = new HashMap<>();
    GamePanel gp;
    BufferedImage spriteSheet, image;
    public AnimationLoader(GamePanel gp) {
        this.gp = gp;
    }

    public ArrayList<BufferedImage> getAnimation(String animationName) {
        return animationMap.get(animationName);
    }

    public void LoadAnimation(String path, int start, int end, String name) {
        this.LoadAnimation(path, start, end, name, gp.tileSize);
    }

    public void LoadAnimation(String path, int start, int end, String name, int tileSize) {
        try {
            System.out.println("Loading animation: " + name + " from " + path);
            spriteSheet = ImageIO.read(getClass().getClassLoader().getResourceAsStream(path));
            for (int i = start; i <= end; i++) {
                image = spriteSheet.getSubimage(i * tileSize, 0, tileSize, tileSize);
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
