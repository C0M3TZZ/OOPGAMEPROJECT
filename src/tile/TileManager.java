package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];
    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[65];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage("/maps/map01_data.txt");
        loadMap("/maps/map01.txt");
    }

    public void getTileImage(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            for (int i = 0; i < 65; i++) {
                String line = br.readLine();
                String data[] = line.split(" ");
                tile[i] = new Tile();
                tile[i].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/"+data[0]));
                tile[i].collision = Boolean.parseBoolean(data[1]);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();
//                System.out.println(line);

                while (col < gp.maxWorldCol) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
//            System.out.println("End Load!");
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize * 2;
            int worldY = worldRow * gp.tileSize * 2;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

//            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
//                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
//                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
//                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY ){
            g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize*2, gp.tileSize*2, null);
//            }
            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}