package pawn;

public class Pawn {

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

    public static boolean isMovePossible(Pawn[][] gameBoard, int posX, int posY, int playerId) {
        if (playerId == 0 && gameBoard[posX][posY] != null && gameBoard[posX][posY].getPlayer() == playerId) {
            if (posY > 0 && posY < gameBoard.length - 1) {
                return gameBoard[posX + 1][posY + 1] == null || gameBoard[posX + 1][posY - 1] == null;
            } else if (posY == 0) {
                return gameBoard[posX + 1][posY + 1] == null;
            } else {
                return gameBoard[posX + 1][posY - 1] == null;
            }
        } else if (gameBoard[posX][posY] != null && gameBoard[posX][posY].getPlayer() == playerId) {
            if (posY > 0 && posY < gameBoard.length - 1) {
                return gameBoard[posX - 1][posY + 1] == null || gameBoard[posX - 1][posY - 1] == null;
            } else if (posY == 0) {
                return gameBoard[posX - 1][posY + 1] == null;
            } else {
                return gameBoard[posX - 1][posY - 1] == null;
            }
        }
        return false;
    }

    public boolean tryToMakeMove(Pawn[][] gameBoard, int posX, int posY, int playerId) {
        return gameBoard[posX][posY] == null && playerId == getPlayer();
    }

    @Override
    public String toString() {
        return "Pawn{" +
                ", posX=" + posX +
                ", posY=" + posY +
                ", player_id=" + playerId +
                ", position=" + position +
                '}';
    }

    public static void removePawn(Pawn[][] gameBoard, int[] cords) {
        gameBoard[cords[0]][cords[1]] = null;
    }

    public static boolean checkIsCapturePossible(Pawn[][] gameBoard, int[] pawnCords, int[] enemyCords) {
        int id = gameBoard[pawnCords[0]][pawnCords[1]].getPlayer();
        int[] cordsAfterCapture = new int[2];

        if (enemyCords[1] - pawnCords[1] != 1 && enemyCords[1] > 0) {
            cordsAfterCapture[1] = enemyCords[1] - 1;
        } else if (enemyCords[1] < gameBoard.length - 1 && enemyCords[1] != 0) {
            cordsAfterCapture[1] = enemyCords[1] + 1;
        } else {
            return false;
        }
        if (id == 1 && enemyCords[0] > 0) {
            cordsAfterCapture[0] = enemyCords[0] - 1;
        } else if (enemyCords[0] < gameBoard.length - 1 && enemyCords[0] != 0) {
            cordsAfterCapture[0] = enemyCords[0] + 1;
        } else {
            return false;
        }

        if (gameBoard[cordsAfterCapture[0]][cordsAfterCapture[1]] == null) {
            captureThePawn(gameBoard, pawnCords, enemyCords, cordsAfterCapture);
            return true;
        }
        return false;
    }

    private static void captureThePawn(Pawn[][] gameBoard, int[] pawnCords, int[] enemyCords, int[] cordsAfterCapture) {
        gameBoard[cordsAfterCapture[0]][cordsAfterCapture[1]] = gameBoard[pawnCords[0]][pawnCords[1]];
        gameBoard[cordsAfterCapture[0]][cordsAfterCapture[1]].setPosX(cordsAfterCapture[1]);
        gameBoard[cordsAfterCapture[0]][cordsAfterCapture[1]].setPosY(cordsAfterCapture[0]);
        gameBoard[pawnCords[0]][pawnCords[1]] = null;
        removePawn(gameBoard, enemyCords);
    }
}
