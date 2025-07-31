package main.window;

import main.object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    GamePanel gamePanel;
    Font ariel_40;
    BufferedImage keyImage;

    public UI(GamePanel gamePanel){
        this.gamePanel=gamePanel;

        ariel_40 = new Font("Arial", Font.PLAIN,40);
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;
    }

    public void draw(Graphics2D graphics2D){
        graphics2D.setFont(ariel_40);
        graphics2D.setColor(Color.WHITE);
        graphics2D.drawImage(keyImage, gamePanel.tileSize/2, gamePanel.tileSize/2, gamePanel.tileSize, gamePanel.tileSize, null);
        graphics2D.drawString("x "+gamePanel.player.hasKey,74,65);
    }
}
