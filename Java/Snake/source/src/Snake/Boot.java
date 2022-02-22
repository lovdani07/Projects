package Snake;

import Snake.gui.SnakeFrame;
import Snake.model.SnakeLogic;

import javax.swing.*;

public class Boot {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SnakeFrame(new SnakeLogic()).setVisible(true));
    }
}
