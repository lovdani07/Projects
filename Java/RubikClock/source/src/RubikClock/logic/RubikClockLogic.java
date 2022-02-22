package RubikClock.logic;

import java.util.Random;

public class RubikClockLogic {
    private final int SIZE = 3;
    private final boolean TEST = false;
    private final int[][] fixedClock = {{11,10,11},{10,8,10},{11,10,11}};


    private int[][] clocks;
    private int steps;

    public void newGame(){
        steps = 0;
        clocks = new int[SIZE][SIZE];

        fillClocks();
    }

    private void fillClocks() {
        Random random = new Random();
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                if(TEST){
                    clocks[i][j] = fixedClock[i][j];
                }
                else{
                    clocks[i][j] = random.nextInt(12)+1;
                }
            }
        }
    }

    public String clockTimeFromLogic(int row, int column){
        String time;
        int hour = clocks[row][column];
        if (hour < 10) {
            time = "0";
            time += String.valueOf(hour);
        }
        else {
            time = String.valueOf(hour);
        }
        time += ":00";
        return time;
    }

    public void raiserButtonClicked(int rowBtn, int columnBtn){
        for(int i = rowBtn; i < rowBtn+2; i++){
            for(int j = columnBtn; j < columnBtn+2; j++){
                clocks[i][j]++;
                if(clocks[i][j] == 13){
                    clocks[i][j] = 1;
                }
            }
        }
        steps++;
    }

    public boolean isGameEnd(){
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                if(clocks[i][j] != 12){
                    return false;
                }
            }
        }
        return true;
    }

    public int getSteps(){return steps;}
}
