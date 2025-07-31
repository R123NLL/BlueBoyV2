package main.window;

import main.object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {

    GamePanel gamePanel;
    Font ariel_40, ariel_80B;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    double playTime;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        ariel_40 = new Font("Arial", Font.PLAIN, 40);
        ariel_80B = new Font("Arial", Font.BOLD, 80);
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D graphics2D) {
        if (gameFinished) {

            graphics2D.setFont(ariel_40);
            graphics2D.setColor(Color.WHITE);

            String text;
            int textLength;
            int x;
            int y;

            text = "I found the treasure!";
            textLength = (int) graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
            x = gamePanel.screenWidth / 2 - textLength / 2;
            y = gamePanel.screenHeight / 2 - (gamePanel.tileSize * 3);
            graphics2D.drawString(text, x, y);

            text = "Your time is : " + decimalFormat.format(playTime) + "!";
            textLength = (int) graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
            x = gamePanel.screenWidth / 2 - textLength / 2;
            y = gamePanel.screenHeight / 2 + (gamePanel.tileSize * 4);
            graphics2D.drawString(text, x, y);

            graphics2D.setFont(ariel_80B);
            graphics2D.setColor(Color.yellow);
            text = "Congratulations!";
            textLength = (int) graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
            x = gamePanel.screenWidth / 2 - textLength / 2;
            y = gamePanel.screenHeight / 2 + (gamePanel.tileSize * 2);
            graphics2D.drawString(text, x, y);

            gamePanel.gameThread = null;


        } else {
            graphics2D.setFont(ariel_40);
            graphics2D.setColor(Color.WHITE);
            graphics2D.drawImage(keyImage, gamePanel.tileSize / 2, gamePanel.tileSize / 2, gamePanel.tileSize, gamePanel.tileSize, null);
            graphics2D.drawString("x " + gamePanel.player.hasKey, 74, 65);

            //TIME
            playTime += (double) 1 / 60;
            graphics2D.drawString("Time:" + decimalFormat.format(playTime), gamePanel.tileSize * 11, 65);

            //Message
            if (messageOn) {
                graphics2D.setFont(graphics2D.getFont().deriveFont(30F));
                graphics2D.drawString(message, gamePanel.tileSize * 2, gamePanel.tileSize * 5);

                messageCounter++;

                if (messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }
}
