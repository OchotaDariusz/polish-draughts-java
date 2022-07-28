package pawn;

public class Pawn {

    private boolean isCrowned;
    private int posX;
    private int posY;

    private final int playerId;

    Coordinates position = new Coordinates(posX, posY);

    public Pawn(int posX, int posY, int playerId) {
        this.posX = posX;
        this.posY = posY;
        this.playerId = playerId;
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

    public int getPlayer() {
        return this.playerId;
    }

    public Color getColor() {
        if (playerId == 0)
            return Color.WHITE;
        return Color.BLACK;
    }

    public boolean tryToMakeMove(Pawn[][] gameBoard, int posX, int posY, int playerId) {
        System.out.println(playerId);
        System.out.println(getPlayer());
        System.out.println(gameBoard[posX][posY]);
        System.out.println(posX);
        System.out.println(posY);
        if (gameBoard[posX][posY] == null && playerId == getPlayer()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Pawn{" +
                "isCrowned=" + isCrowned +
                ", posX=" + posX +
                ", posY=" + posY +
                ", player_id=" + playerId +
                ", position=" + position +
                '}';
    }
}
