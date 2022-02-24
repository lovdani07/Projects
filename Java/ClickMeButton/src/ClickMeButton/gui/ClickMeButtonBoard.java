package ClickMeButton.gui;

import ClickMeButton.logic.ClickMeButtonLogic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClickMeButtonBoard extends JPanel{
    private final ClickMeButtonLogic logic;

    private boolean isRunning;
    private Timer timer;

    private final JButton counterButton;
    private final JButton startButton;

    public ClickMeButtonBoard(ClickMeButtonLogic logic){
        isRunning = false;
        this.logic = logic;

        setPreferredSize(new Dimension(ClickMeButtonUIConstants.BOARD_WIDTH,ClickMeButtonUIConstants.BOARD_HEIGHT));
        setFocusable(true);

        startButton = new JButton();
        startButton.setText("Start Game!");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        counterButton = new JButton();
        counterButton.setPreferredSize(
                new Dimension(ClickMeButtonUIConstants.BUTTON_WIDTH, ClickMeButtonUIConstants.BUTTON_HEIGHT));
        counterButton.setText("Click me!");
        counterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonClicked();
            }
        });
        counterButton.setVisible(false);

        add(startButton);
        add(counterButton);
    }

    private void stopTimer(){
        if(timer != null && timer.isRunning()) timer.stop();
    }

    public void startGame(){
        isRunning = true;

        startButton.setVisible(false);
        counterButton.setVisible(true);
        buttonClicked();

        timer = new Timer(ClickMeButtonUIConstants.PERIOD, new oneGameCycleActionListener());
        timer.start();
    }

    private class oneGameCycleActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) { doInOneGameCycle(); }
    }

    private void doInOneGameCycle(){
        if(logic.getCounter() == logic.getCheckerCounter()){
            stopTimer();
            gameOver();
        }
        else{
            logic.setCheckerCounter(logic.getCounter());
        }
        repaint();
    }

    private void gameOver(){
        isRunning = false;
        counterButton.setVisible(false);
    }

    public void buttonClicked(){
        logic.buttonClicked();
        counterButton.setLocation(logic.getButtonX(), logic.getButtonY());
        repaint();
    }

    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);

        if(isRunning) {
            //paint points
            graphics.setColor(Color.BLACK);
            FontMetrics metrics = getFontMetrics(graphics.getFont());
            graphics.drawString("Button clicked " + logic.getCounter() + " times.",
                    (600 - metrics.stringWidth("Button clicked " + logic.getCounter() + " times.")) / 2,
                    graphics.getFont().getSize());
        }
        else{
            if(logic.getCounter() > -1) {
                //draw "Game Over" text
                graphics.setColor(Color.RED);
                FontMetrics metrics1 = getFontMetrics(graphics.getFont());
                graphics.drawString("Game Over", (ClickMeButtonUIConstants.BOARD_WIDTH - metrics1.stringWidth("Game Over")) / 2,
                        ClickMeButtonUIConstants.BOARD_HEIGHT / 2);

                //draw points
                graphics.setColor(Color.RED);
                FontMetrics metrics2 = getFontMetrics(graphics.getFont());
                graphics.drawString("Score: " + logic.getCounter(),
                        (ClickMeButtonUIConstants.BOARD_WIDTH - metrics2.stringWidth("Score: " + logic.getCounter())) / 2,
                        ClickMeButtonUIConstants.BOARD_HEIGHT / 2 + graphics.getFont().getSize());
            }
        }
    }
}
