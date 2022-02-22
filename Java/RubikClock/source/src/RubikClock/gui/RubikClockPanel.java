package RubikClock.gui;

import RubikClock.common.gui.RaiserButton;
import RubikClock.logic.RubikClockLogic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RubikClockPanel extends JPanel {

    private final RubikClockLogic logic;
    private final ActionListener raiserButtonActionListener = new RaiserButtonActionListener();

    public RubikClockPanel(RubikClockLogic logic) {
        this.logic = logic;
        newGame();
    }

    public void newGame() {
        setupGamePanel();
        refreshUI();
    }

    private void refreshUI() {
        JLabel assist = new JLabel();
        int labelPosition = 0;
        for (Component component : getComponents()) {
            if(assist.getClass() == component.getClass()){
                JLabel label = (JLabel) component;
                if(!"".equals(label.getText())){
                    label.setText(logic.clockTimeFromLogic(labelPosition/3, labelPosition%3));
                    labelPosition++;
                }
            }
        }
    }

    private void setupGamePanel() {
        removeAll();
        int SIZE = 5;

        setLayout(new GridLayout(SIZE, SIZE));
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        add(createJLabelRandomClock(i / 2, j / 2));
                    }
                    else {
                        add(new JLabel(""));
                    }
                }
                else {
                    if (j % 2 == 0) {
                        add(new JLabel(""));
                    }
                    else {
                        JButton button = new RaiserButton(i / 3, j / 3);
                        button.setText("+");
                        button.addActionListener(raiserButtonActionListener);
                        add(button);
                    }
                }
            }
        }
        revalidate();
        repaint();
    }

    private JLabel createJLabelRandomClock(int i, int j) {
        return new JLabel(logic.clockTimeFromLogic(i, j), SwingConstants.CENTER);
    }

    private class RaiserButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            RaiserButton btn = (RaiserButton) e.getSource();
            logic.raiserButtonClicked(btn.getRow(), btn.getColumn());
            refreshUI();
            checkEndOfGame();
        }
    }

    private void checkEndOfGame() {
        if(logic.isGameEnd()){
            JOptionPane.showMessageDialog(null,"Congratulations! You won the game!\nIt took you "
                    + logic.getSteps() + " steps!", "GG",JOptionPane.INFORMATION_MESSAGE);

            logic.newGame();
            newGame();
        }
    }
}