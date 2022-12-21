package main;

<<<<<<< HEAD
import entity.*;
=======
import entity.Entity;
import entity.Player;
>>>>>>> b118af326173c0b4da0b20a4ad53e539b211e5ca
import object.SuperObject;
import tile.TileManager;
import ui.UI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
<<<<<<< HEAD
import java.util.concurrent.ThreadLocalRandom;
=======
>>>>>>> b118af326173c0b4da0b20a4ad53e539b211e5ca

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

    int FPS = 60;

    TileManager tileM = new TileManager(this);
<<<<<<< HEAD
    public KeyHandler keyH = new KeyHandler(this);
    public MouseHandler mouseH = new MouseHandler(this);
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public Player player = new PY_DogBoy(this);
    public SuperObject obj[] = new SuperObject[2];
    public ArrayList<Entity> projectileList = new ArrayList<>();
    public UI ui = new UI(this);
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
=======
    KeyHandler keyH = new KeyHandler();
    MouseHandler mouseH = new MouseHandler(this);
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public Player player = new Player(this, keyH, mouseH);
    public SuperObject obj[] = new SuperObject[10];
    public Entity monster[] = new Entity[10];
    public ArrayList<Entity> projectileList = new ArrayList<>();
>>>>>>> b118af326173c0b4da0b20a4ad53e539b211e5ca

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.addMouseListener(mouseH);
        this.setFocusable(true);
        getRandomPlayer();
    }

    public void setUpGame() {
        assetSetter.setObject();
<<<<<<< HEAD
        gameState = playState;
    }

    public void getRandomPlayer() {
        int randomPlayer = ThreadLocalRandom.current().nextInt(1, 5);
        switch (randomPlayer) {
            case 1:
                player = new PY_DogBoy(this);
                break;
            case 2:
                player = new PY_PsychicGirl(this);
                break;
            case 3:
                player = new PY_Cyborg(this);
                break;
            case 4:
                player = new PY_BankSensei(this);
                break;
        }
=======
        assetSetter.setMonster();
>>>>>>> b118af326173c0b4da0b20a4ad53e539b211e5ca
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

<<<<<<< HEAD
        if (gameState == playState) {
            player.update();

            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    obj[i].update();
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
=======
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
>>>>>>> b118af326173c0b4da0b20a4ad53e539b211e5ca
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
<<<<<<< HEAD
                obj[i].draw(g2, this, "healthPotion");
            }
        }

        for (int i = 0; i < projectileList.size(); i++) {
            if (projectileList.get(i) != null) {
                projectileList.get(i).draw(g2);
=======
                obj[i].draw(g2, this);
            }
        }

        for (int i = 0; i < monster.length; i++) {
            if (monster[i] != null) {
                monster[i].draw(g2);
>>>>>>> b118af326173c0b4da0b20a4ad53e539b211e5ca
            }
        }

        for (int i = 0; i < projectileList.size(); i++) {
            if (projectileList.get(i) != null) {
                projectileList.get(i).draw(g2);
            }
        }

        player.draw(g2);

        ui.draw(g2);

        g2.dispose();
    }
}
