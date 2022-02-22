package RubikClock.gui;

import RubikClock.logic.RubikClockLogic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RubikClockFrame extends JFrame {
    private final RubikClockLogic logic;
    private final RubikClockPanel gamePanel;

    public RubikClockFrame(RubikClockLogic logic){
        this.logic = logic;

        logic.newGame();

        this.gamePanel = new RubikClockPanel(logic);
        getContentPane().add(gamePanel);
        setJMenuBar(new RubikClockMenuBar());

        setTitle("Rubik Clock");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(200,200);
        setMinimumSize(new Dimension(500,500));

        pack();
    }

    private class RubikClockMenuBar extends JMenuBar{

        public RubikClockMenuBar(){
            JMenu gameMenu = new JMenu("Game");
            JMenuItem newGame = new JMenuItem(newGameAction);
            gameMenu.add(newGame);
            add(gameMenu);
        }
        private final Action newGameAction = new AbstractAction("New game") {

            @Override
            public void actionPerformed(ActionEvent ae) {
                logic.newGame();
                gamePanel.newGame();
                pack();
            }

        };
    }

}
