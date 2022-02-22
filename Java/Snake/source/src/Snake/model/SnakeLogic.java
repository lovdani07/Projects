package Snake.model;

import Snake.gui.SnakeUIConstants;

import java.util.ArrayList;
import java.util.Random;

public class SnakeLogic {
    private Direction snakeGoing;
    private Direction snakeNextTurn;
    private Position[] rockPositions;
    private Position applePosition;
    private ArrayList<Position> snakePositions;
    private int points;

    private Random random;

    //public SnakeLogic(){ this.highScoreDao = new HighScoreDao(); }

    public void newGame(){
        random = new Random();
        rockPositions = new Position[SnakeUIConstants.MAX_ROCK_COUNT];
        snakePositions = new ArrayList<>();
        points = 0;

        generateSnake();
        generateRocks();
        generateRandomApple();
    }

    public void gameOver(){//TODO save highscore to database
        ;
    }

    public boolean willCollide(){
        boolean result = false;

        Position snakeHeaded = new Position(
                snakePositions.get(snakePositions.size()-1).getX()+snakeNextTurn.getX()*SnakeUIConstants.UNIT_SIZE,
                snakePositions.get(snakePositions.size()-1).getY()+snakeNextTurn.getY()*SnakeUIConstants.UNIT_SIZE);

        //check rocks
        for(int i = 0; i < SnakeUIConstants.MAX_ROCK_COUNT; i++)
            if(rockPositions[i].equals(snakeHeaded))
                result = true;

        //check snake
        for(int i = 1; i <= points; i++)
            if(snakePositions.get(i).equals(snakeHeaded))
                result = true;

        //check border
        if(snakeHeaded.getX() < 0 || snakeHeaded.getX() > SnakeUIConstants.BOARD_WIDTH-SnakeUIConstants.UNIT_SIZE ||
           snakeHeaded.getY() < 0 || snakeHeaded.getY() > SnakeUIConstants.BOARD_HEIGHT-SnakeUIConstants.UNIT_SIZE)
            result = true;

        return result;
    }

    public void moveSnake(){
        snakePositions.remove(0);
        snakePositions.add(new Position(
                snakePositions.get(snakePositions.size()-1).getX()+snakeNextTurn.getX()*SnakeUIConstants.UNIT_SIZE,
                snakePositions.get(snakePositions.size()-1).getY()+snakeNextTurn.getY()*SnakeUIConstants.UNIT_SIZE));
        snakeGoing = snakeNextTurn;
    }

    //true - new apple generated or snake didnt eat apple; false - gameboard is full, gameover
    public boolean checkApple(){
        Position snakeHeaded = new Position(
                snakePositions.get(snakePositions.size()-1).getX()+snakeNextTurn.getX()*SnakeUIConstants.UNIT_SIZE,
                snakePositions.get(snakePositions.size()-1).getY()+snakeNextTurn.getY()*SnakeUIConstants.UNIT_SIZE);
        if(snakeHeaded.equals(applePosition)) {
            snakePositions.add(0, new Position(0,0));
            return generateRandomApple();
        }
        return true;
    }

    private void generateSnake(){
        snakePositions.add(new Position(SnakeUIConstants.MIDDLE_HEIGHT*SnakeUIConstants.UNIT_SIZE,
                                        SnakeUIConstants.MIDDLE_WIDTH*SnakeUIConstants.UNIT_SIZE));
        int startRandom = random.nextInt(4);
        Direction startDirection = switch (startRandom) {
            case 1 -> Direction.RIGHT;
            case 2 -> Direction.DOWN;
            case 3 -> Direction.LEFT;
            default -> Direction.UP;
        };
        snakePositions.add(new Position((SnakeUIConstants.MIDDLE_HEIGHT+startDirection.getX())*SnakeUIConstants.UNIT_SIZE,
                                        (SnakeUIConstants.MIDDLE_WIDTH+startDirection.getY())*SnakeUIConstants.UNIT_SIZE));
        snakeGoing = startDirection;
        snakeNextTurn = startDirection;
    }

    private Position randomPosition(){
        int randomX = random.nextInt(SnakeUIConstants.BOARD_WIDTH/SnakeUIConstants.UNIT_SIZE)*SnakeUIConstants.UNIT_SIZE;
        int randomY = random.nextInt(SnakeUIConstants.BOARD_HEIGHT/SnakeUIConstants.UNIT_SIZE)*SnakeUIConstants.UNIT_SIZE;

        return new Position(randomX, randomY);
    }

    //true - a new apple was generated; false - gameboard is full, gameover
    private boolean generateRandomApple(){
        //no empty space for an apple to spawn
        if(snakePositions.size() + SnakeUIConstants.MAX_ROCK_COUNT == SnakeUIConstants.GAME_UNITS){
            return false;
        }

        points++;
        do{
            applePosition = randomPosition();
        }while(!correctPosition(applePosition));
        return true;
    }

    private void generateRocks() {
        if(SnakeUIConstants.MAX_ROCK_COUNT == 0)
            return;
        int i = 0;
        Position tempPos;
        do{
            tempPos = randomPosition();
            if(correctPosition(tempPos)){
                rockPositions[i] = tempPos;
                i++;
            }
        }while(i < SnakeUIConstants.MAX_ROCK_COUNT);
    }

    private boolean correctPosition(Position pos){
        boolean result = true;
        for(int i = 0; i < SnakeUIConstants.MAX_ROCK_COUNT; i++)
            if(rockPositions[i] != null)
                if(rockPositions[i].getX() == pos.getX() && rockPositions[i].getY() == pos.getY())
                    result = false;
        for(int i = 0; i < snakePositions.size(); i++)
            if(snakePositions.get(i).getX() == pos.getX() && snakePositions.get(i).getY() == pos.getY())
                result = false;
        return result;
    }

    public  int getSnakeLength(){ return snakePositions.size(); }
    public  int getPoints(){ return points; }
    public Position getApplePosition(){ return applePosition; }
    public Position getRockPositionAtIndex(int index){ return rockPositions[index]; }
    public Position getSnakePositionAtIndex(int index){ return snakePositions.get(index); }
    public Direction getSnakeGoing(){ return snakeGoing; }
    public void setSnakeNextTurn(Direction nextTurn){ snakeNextTurn = nextTurn; }
}
