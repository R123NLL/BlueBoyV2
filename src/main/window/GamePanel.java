package main.window;

import main.entities.Player;
import main.keyboard.KeyHandler;
import main.object.SuperObject;
import main.sound.Sound;
import main.tiles.TileManager;
import main.tools.AssetSetter;
import main.tools.CollisionChecker;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //Screen settings
    final int originTilesSize = 16; // 16x16
    final int scale = 3;
    public final int tileSize = originTilesSize * scale; // 48x48
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; //768 pixels
    public final int screenHeight = tileSize * maxScreenRow; //576 pixels

    //WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;



    //FPS
    int fps = 60;

    //SYSTEM
    public TileManager tileManager = new TileManager(this);
    KeyHandler keyHandler = new KeyHandler();
    Sound sound = new Sound();
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    Thread gameThread;

    //ENTITY AND OBJECT
    public Player player = new Player(this, keyHandler);
    public SuperObject obj[] = new SuperObject[10];


    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

   public void setupGame(){

        assetSetter.setObject();

//        playMusic(0);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / fps; // 0.01666 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                // 1 UPDATE: update information such as character positions
                update();
                // 2 DRAW: draw the screen with updated information
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics graphics) {

        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;

        //TILE
        tileManager.draw(graphics2D);
        //OBJECT
        for (int i = 0; i < obj.length; i++) {
            if(obj[i] !=null){
                obj[i].draw(graphics2D,this);
            }
        }
        //PLAYER
        player.draw(graphics2D);

        graphics2D.dispose();
    }
    public void playMusic(int i){
        sound.setFile(i);
        sound.play();
        sound.loop();
    }
    public void stopMusic(){
        sound.stop();
    }
    public void playSE(int i){
        sound.setFile(i);
        sound.play();
    }
}
