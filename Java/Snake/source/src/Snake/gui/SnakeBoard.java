package Snake.gui;

import Snake.model.Direction;
import Snake.model.SnakeLogic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SnakeBoard extends JPanel {
    private final SnakeLogic logic;
    private Timer timer;
    private boolean isRunning = true;


    public SnakeBoard(SnakeLogic logic){
        this.logic = logic;
        setPreferredSize(new Dimension(SnakeUIConstants.BOARD_WIDTH, SnakeUIConstants.BOARD_HEIGHT));
        setFocusable(true);

        addKeyListener(snakeKeyListener);


        startNewGame();

    }

    private void stopTimer(){
        if(timer != null && timer.isRunning()) timer.stop();
    }

    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        if(isRunning) {
            for (int i = 0; i < SnakeUIConstants.BOARD_HEIGHT / SnakeUIConstants.UNIT_SIZE; i++) {
                graphics.drawLine(i * SnakeUIConstants.UNIT_SIZE, 0,
                        i * SnakeUIConstants.UNIT_SIZE, SnakeUIConstants.BOARD_HEIGHT);
                graphics.drawLine(0, i * SnakeUIConstants.UNIT_SIZE,
                        SnakeUIConstants.BOARD_WIDTH, i * SnakeUIConstants.UNIT_SIZE);
            }

            //draw snake body
            graphics.setColor(Color.GREEN);
            for (int i = 0; i < logic.getSnakeLength() - 1; i++) {
                graphics.fillRect(logic.getSnakePositionAtIndex(i).getX(),
                        logic.getSnakePositionAtIndex(i).getY(),
                        SnakeUIConstants.UNIT_SIZE, SnakeUIConstants.UNIT_SIZE);
            }
            //draw snake head
            graphics.setColor(new Color(34, 139, 34));
            graphics.fillRect(logic.getSnakePositionAtIndex(logic.getSnakeLength() - 1).getX(),
                    logic.getSnakePositionAtIndex(logic.getSnakeLength() - 1).getY(),
                    SnakeUIConstants.UNIT_SIZE, SnakeUIConstants.UNIT_SIZE);
            //draw snake eye
            graphics.setColor(Color.WHITE);
            graphics.fillOval(logic.getSnakePositionAtIndex(logic.getSnakeLength() - 1).getX() + SnakeUIConstants.UNIT_SIZE / 3,
                    logic.getSnakePositionAtIndex(logic.getSnakeLength() - 1).getY() + SnakeUIConstants.UNIT_SIZE / 3,
                    SnakeUIConstants.UNIT_SIZE / 5, SnakeUIConstants.UNIT_SIZE / 5);

            //draw apple
            graphics.setColor(Color.RED);
            graphics.fillOval(logic.getApplePosition().getX(), logic.getApplePosition().getY(),
                    SnakeUIConstants.UNIT_SIZE, SnakeUIConstants.UNIT_SIZE);

            //draw rocks
            graphics.setColor(Color.GRAY);
            for (int i = 0; i < SnakeUIConstants.MAX_ROCK_COUNT; i++) {
                graphics.fillOval(logic.getRockPositionAtIndex(i).getX(), logic.getRockPositionAtIndex(i).getY(),
                        SnakeUIConstants.UNIT_SIZE, SnakeUIConstants.UNIT_SIZE);
            }
            //draw points
            graphics.setColor(Color.BLACK);
            graphics.setFont(SnakeUIConstants.MAIN_FONT);
            FontMetrics metrics = getFontMetrics(graphics.getFont());
            graphics.drawString("Score: " + logic.getPoints(),
                    (SnakeUIConstants.BOARD_WIDTH - metrics.stringWidth("Score: " + logic.getPoints())) / 2,
                    graphics.getFont().getSize());
        }
        else{
            //draw "Game Over" text
            graphics.setColor(Color.RED);
            graphics.setFont(SnakeUIConstants.MAIN_FONT);
            FontMetrics metrics1 = getFontMetrics(graphics.getFont());
            graphics.drawString("Game Over", (SnakeUIConstants.BOARD_WIDTH - metrics1.stringWidth("Game Over")) / 2,
                             SnakeUIConstants.BOARD_HEIGHT / 2);

            //draw points
            graphics.setColor(Color.RED);
            graphics.setFont(SnakeUIConstants.SMALL_FONT);
            FontMetrics metrics2 = getFontMetrics(graphics.getFont());
            graphics.drawString("Score: " + logic.getPoints(),
                    (SnakeUIConstants.BOARD_WIDTH - metrics2.stringWidth("Score: " + logic.getPoints())) / 2,
                    SnakeUIConstants.BOARD_HEIGHT / 2 + graphics.getFont().getSize());
        }
    }

    protected void startNewGame(){
        stopTimer();

        logic.newGame();
        isRunning = true;

        timer = new Timer(SnakeUIConstants.PERIOD, new OneGameCycleActionListener());
        timer.start();
    }

    private void gameOver(){
        isRunning = false;
        logic.gameOver();
    }

    private void doInOneGameCycle() {
        if(!logic.willCollide()) {
            if(logic.checkApple())
                logic.moveSnake();
            else
                gameOver();
        }
        else{
            stopTimer();
            gameOver();
        }
        repaint();
    }

    private class OneGameCycleActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            doInOneGameCycle();
        }

    }

    private final KeyListener snakeKeyListener = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e){
            switch (e.getKeyCode()){
                case KeyEvent.VK_UP:
                    if(!(logic.getSnakeGoing() == Direction.DOWN))
                        logic.setSnakeNextTurn(Direction.UP);
                    break;
                case KeyEvent.VK_RIGHT:
                    if(!(logic.getSnakeGoing() == Direction.LEFT))
                        logic.setSnakeNextTurn(Direction.RIGHT);
                    break;
                case KeyEvent.VK_DOWN:
                    if(!(logic.getSnakeGoing() == Direction.UP))
                        logic.setSnakeNextTurn(Direction.DOWN);
                    break;
                case KeyEvent.VK_LEFT:
                    if(!(logic.getSnakeGoing() == Direction.RIGHT))
                        logic.setSnakeNextTurn(Direction.LEFT);
                    break;
                default:
                    break;
            }
        }
    };
}
