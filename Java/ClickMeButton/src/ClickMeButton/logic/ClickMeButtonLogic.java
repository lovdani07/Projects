package ClickMeButton.logic;


import ClickMeButton.gui.ClickMeButtonUIConstants;

import java.util.Random;

public class ClickMeButtonLogic {
    private int counter = -1;
    private int checkerCounter;
    private int buttonX;
    private int buttonY;
    private Random random;

    public void buttonClicked(){
        counter++;
        random = new Random();
        buttonX = random.nextInt(ClickMeButtonUIConstants.BOARD_WIDTH-ClickMeButtonUIConstants.BUTTON_WIDTH);
        buttonY = random.nextInt(ClickMeButtonUIConstants.BOARD_HEIGHT-ClickMeButtonUIConstants.BUTTON_HEIGHT);
    }

    public int getCheckerCounter() { return checkerCounter; }
    public void setCheckerCounter(int checkerCounter) { this.checkerCounter = checkerCounter; }

    public int getCounter() { return counter; }
    public int getButtonX() { return buttonX; }
    public int getButtonY() { return buttonY; }
}
