package main;

import entity.Entity;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {
    public final int originalTileSize = 8;
    final int scale = 8;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    MouseHandler mouseH = new MouseHandler(this);
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public Player player = new Player(this, keyH, mouseH);
    public SuperObject obj[] = new SuperObject[10];
    public Entity monster[] = new Entity[10];
    public ArrayList<Entity> projectileList = new ArrayList<>();

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.addMouseListener(mouseH);
        this.setFocusable(true);
    }

    public void setUpGame() {
        assetSetter.setObject();
        assetSetter.setMonster();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {

        player.update();
        for (int i = 0; i < monster.length; i++) {
            if (monster[i] != null) {
                if(monster[i].alive) {
                    monster[i].update();
                }
                if (!monster[i].alive) {
                    monster[i] = null;
                }
            }
        }

        for (int i = 0; i < projectileList.size(); i++) {
            if (projectileList.get(i) != null) {
                if (projectileList.get(i).alive) {
                    projectileList.get(i).update();
                }
                if (!projectileList.get(i).alive) {
                    projectileList.remove(i);
                }
            }
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        tileM.draw(g2);

        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }

        for (int i = 0; i < monster.length; i++) {
            if (monster[i] != null) {
                monster[i].draw(g2);
            }
        }

        for (int i = 0; i < projectileList.size(); i++) {
            if (projectileList.get(i) != null) {
                projectileList.get(i).draw(g2);
            }
        }

        player.draw(g2);

        g2.dispose();
    }
}
