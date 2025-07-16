package main.entities;

import main.keyboard.KeyHandler;
import main.window.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;

    int defaultSpeed = 2;

    int hasKey = 0;


    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        screenX = gamePanel.screenWidth / 2 - (gamePanel.tileSize / 2);
        screenY = gamePanel.screenHeight / 2 - (gamePanel.tileSize / 2);

        //collision area of player
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gamePanel.tileSize * 23;
        worldY = gamePanel.tileSize * 21;
        speed = 2;
        sprintSpeed=4;
        direction = "down";
    }

    public void getPlayerImage() {

        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Walking sprites/boy_up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Walking sprites/boy_up_2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Walking sprites/boy_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Walking sprites/boy_down_2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Walking sprites/boy_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Walking sprites/boy_left_2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Walking sprites/boy_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Walking sprites/boy_right_2.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() { //method updates 60 times per second
        if (keyHandler.upPressed || keyHandler.downPressed ||
                keyHandler.leftPressed || keyHandler.rightPressed) {

            if (keyHandler.upPressed) {
                direction = "up";
            }
            if (keyHandler.downPressed) {
                direction = "down";
            }
            if (keyHandler.leftPressed) {
                direction = "left";
            }
            if (keyHandler.rightPressed) {
                direction = "right";
            }
            if (keyHandler.shiftPressed == true) {
                speed = sprintSpeed;
            }
            if (keyHandler.shiftPressed == false) {
                speed = defaultSpeed;
            }


            //Check tile collision
            collisionOn = false;
            gamePanel.collisionChecker.checkTile(this);

            //Check Object Collision
            int objIndex = gamePanel.collisionChecker.checkObject(this, true);
            pickUpObject(objIndex);

            if (collisionOn == false) {
                switch (direction) {
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
            if (spriteCounter > 15) { //player image changes in every 15 frames
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void pickUpObject(int index) {
        if (index != 999) {
            String objectName = gamePanel.obj[index].name;
            switch (objectName){
                case "Key":
                    gamePanel.obj[index] = null;
                    gamePanel.playSE(1);
                    hasKey++;
                    gamePanel.obj[index] = null;
                    System.out.println("Keys: "+ hasKey);
                    break;
                case "Door":
                    if (hasKey > 0 ){
                        gamePanel.playSE(3);
                        gamePanel.obj[index] = null;
                        hasKey--;
                    }
                    System.out.println("Keys: "+ hasKey);
                    break;
                case "Boots":
                    gamePanel.playSE(2);
                    defaultSpeed +=2;
                    sprintSpeed +=2;
                    gamePanel.obj[index] = null;
                    break;
            }
        }
    }

    public void draw(Graphics2D graphics2D) {

        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }
        graphics2D.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }


}
