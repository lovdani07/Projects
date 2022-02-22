package RubikClock;

import RubikClock.gui.RubikClockFrame;
import RubikClock.logic.RubikClockLogic;

public class Boot {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(
                ()-> new RubikClockFrame(new RubikClockLogic()).setVisible(true)
        );
    }
}