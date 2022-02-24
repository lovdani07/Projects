package ClickMeButton.gui;

import ClickMeButton.logic.ClickMeButtonLogic;

import javax.swing.*;
import java.awt.*;

public class ClickMeButtonFrame extends JFrame {
    private final ClickMeButtonBoard board;

    public ClickMeButtonFrame(ClickMeButtonLogic logic){

        setTitle("Click Me Button");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        this.board = new ClickMeButtonBoard(logic);
        getContentPane().add(board, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }
}
