package pawn;

public class Coordinates {

    private int x;
    private int y;

//    public int getX() {  // never used
//        return x;
//    }

    public void setX(int x) {
        this.x = x;
    }

//    public int getY() {  // never used
//        return y;
//    }

    public void setY(int y) {
        this.y = y;
    }

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
