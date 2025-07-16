package main.keyboard;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed, shiftPressed;

    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        else if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        else if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        else if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        else if (code == KeyEvent.VK_UP) {
            upPressed = true;
        }
        else if (code == KeyEvent.VK_DOWN) {
            downPressed = true;
        }
        else if (code == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }
        else if (code == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }
        else if (code == KeyEvent.VK_SHIFT) {
            shiftPressed = true;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
         else if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
         else if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        else if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        else if (code == KeyEvent.VK_UP) {
            upPressed = false;
        }
        else if (code == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        else if (code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        else if (code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
        else if (code == KeyEvent.VK_SHIFT) {
            shiftPressed = false;
        }
    }
}
