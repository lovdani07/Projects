package Snake.gui;

import Snake.model.SnakeLogic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SnakeFrame extends JFrame {
    private final SnakeBoard board;

    public SnakeFrame(SnakeLogic logic){

        setTitle("Snake");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setJMenuBar(new SnakeMenuBar());

        board = new SnakeBoard(logic);
        getContentPane().add(board, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }

    private class SnakeMenuBar extends JMenuBar{
        public SnakeMenuBar(){
            JMenu gameMenu = new JMenu("Game");
            JMenuItem newGame = new JMenuItem(newGameAction);
            gameMenu.add(newGame);
            add(gameMenu);
        }
        private final Action newGameAction = new AbstractAction("New game") {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.startNewGame();
                pack();
            }
        };
    }
}
