package Snake.gui;

import java.awt.*;

public class SnakeUIConstants {
    private SnakeUIConstants(){}

    public static final int BOARD_HEIGHT = 600;
    public static final int BOARD_WIDTH = 600;
    public static final int UNIT_SIZE = 100;
    public static final int MIDDLE_HEIGHT = (BOARD_HEIGHT/UNIT_SIZE)/2;
    public static final int MIDDLE_WIDTH = (BOARD_WIDTH/UNIT_SIZE)/2;
    public static final int GAME_UNITS = (BOARD_HEIGHT*BOARD_WIDTH)/(UNIT_SIZE*UNIT_SIZE);
    public static final int MAX_ROCK_COUNT = 0;

    public static final int FPS = 2;
    public static final int PERIOD = 1000 / FPS;

    public static final Color TEXT_COLOR = Color.BLACK;
    public static final Font MAIN_FONT = new Font("Monospaced", Font.BOLD, 48);
    public static final Font SMALL_FONT = MAIN_FONT.deriveFont(Font.BOLD, 18);
}
