package ClickMeButton;

import ClickMeButton.gui.ClickMeButtonFrame;
import ClickMeButton.logic.ClickMeButtonLogic;

import javax.swing.*;

public class Boot {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ClickMeButtonFrame(new ClickMeButtonLogic()).setVisible(true));
    }
}
