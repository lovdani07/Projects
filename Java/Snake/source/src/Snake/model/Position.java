package Snake.model;

public class Position {
    private final int x;
    private final int y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public boolean equals(Position pos){
        if(this.x == pos.getX() && this.y == pos.getY())
            return true;
        return false;
    }

    public int getX() { return x; }
    public int getY() { return y; }
}
