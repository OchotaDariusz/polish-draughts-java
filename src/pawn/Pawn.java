package pawn;
public class Pawn {

    private boolean isCrowned;
    private int posX;
    private int posY;

    private int player_id;
    Coordinates position = new Coordinates(posX, posY);

    public Pawn(int posX, int posY, int player_id) {
        this.posX = posX;
        this.posY = posY;
        this.player_id = player_id;
        position.setX(posX);
        position.setY(posY);
    }

    public void setPosX(int posX) {
        this.posX = posX;
        position.setX(posX);

    }

    public void setPosY(int posY) {
        this.posY = posY;
        position.setY(posY);
    }

    public void getPlayer()
    {

    }

    public Color getColor() {
        if (player_id == 0)
            return Color.WHITE;
        return Color.BLACK;
    }

    public static void moveValidation(int posX, int posY){

    }

    @Override
    public String toString() {
        return "Pawn{" +
                "isCrowned=" + isCrowned +
                ", posX=" + posX +
                ", posY=" + posY +
                ", player_id=" + player_id +
                ", position=" + position +
                '}';
    }
}
