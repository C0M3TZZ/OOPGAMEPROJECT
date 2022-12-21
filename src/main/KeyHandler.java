package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
<<<<<<< HEAD
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, shiftPressed, xPressed;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

=======
    public boolean upPressed, downPressed, leftPressed, rightPressed, shiftPressed, shotKeyPressed;
>>>>>>> b118af326173c0b4da0b20a4ad53e539b211e5ca
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_SHIFT) {
            shiftPressed = true;
        }
<<<<<<< HEAD
        if (code == KeyEvent.VK_X) {
            xPressed = true;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            if (gp.gameState == gp.playState) {
                gp.gameState = gp.pauseState;
            }
            else if (gp.gameState == gp.pauseState) {
                gp.gameState = gp.playState;
            }
=======
        if (code == KeyEvent.VK_SPACE) {
            shotKeyPressed = true;
>>>>>>> b118af326173c0b4da0b20a4ad53e539b211e5ca
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_SHIFT) {
            shiftPressed = false;
        }
<<<<<<< HEAD
        if (code == KeyEvent.VK_X) {
            xPressed = false;
=======
        if (code == KeyEvent.VK_SPACE) {
            shotKeyPressed = false;
>>>>>>> b118af326173c0b4da0b20a4ad53e539b211e5ca
        }
    }
}
