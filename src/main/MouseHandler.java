package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class MouseHandler implements MouseListener {

    GamePanel gp;
    public boolean leftClick;

    public MouseHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
            leftClick = true;
        }
        if (gp.gameState == gp.pauseState) {
            for (int i = 0; i < gp.ui.pauseScreen.button.buttonNameArr.size(); i++) {
                if (gp.ui.pauseScreen.button.buttonR2Arr.get(i).contains(mouseEvent.getPoint())) {
                    switch (gp.ui.pauseScreen.button.buttonNameArr.get(i)) {
                        case "continue":
                            gp.gameState = gp.playState;
                            break;
                        case "quit to menu":
                            gp.gameState = gp.titleState;
                            break;
                    }
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
            leftClick = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}