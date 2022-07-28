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

    public static void removePawn(Pawn[][] gameBoard, int[]cords) {
        gameBoard[cords[0]][cords[1]] = null;
    }

    public static void captureThePawn(Pawn[][] gameBoard, int[] pawnCords, int[]enemyCords) {
        int id = gameBoard[pawnCords[0]][pawnCords[1]].getPlayer();
        int[] cordsAfterCapture = new int[2];
        System.out.println("Id gracza: " + id);

// coordy od 1 to literki,  od 0 to cyfry
        if (enemyCords[1] - pawnCords[1] != 1) {
            cordsAfterCapture[1] = enemyCords[1] - 1;
            if (id == 1) {
                cordsAfterCapture[0] = enemyCords[0] - 1;
            } else {
                cordsAfterCapture[0] = enemyCords[0] + 1;
            }
        } else {
            cordsAfterCapture[1] = enemyCords[1] + 1;
            if (id == 1) {
                cordsAfterCapture[0] = enemyCords[0] - 1;
            } else {
                cordsAfterCapture[0] = enemyCords[0] + 1;
            }
        }
        gameBoard[cordsAfterCapture[0]][cordsAfterCapture[1]] = gameBoard[pawnCords[0]][pawnCords[1]];
        gameBoard[cordsAfterCapture[0]][cordsAfterCapture[1]].setPosX(cordsAfterCapture[1]);
        gameBoard[cordsAfterCapture[0]][cordsAfterCapture[1]].setPosY(cordsAfterCapture[0]);
        gameBoard[pawnCords[0]][pawnCords[1]] = null;
        removePawn(gameBoard, enemyCords);
    }
}
