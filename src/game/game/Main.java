package game;

import board.Board;

public class Main {
    private static boolean isRunning = true;
    private static int playerId = 0;

    public static boolean isRunning() {
        return isRunning;
    }

    public static void setRunning(boolean running) {
        isRunning = running;
    }

    public static int getPlayerId() {
        return playerId;
    }

    public static void setPlayerId(int playerId) {
        Main.playerId = playerId;
    }

    public static void main(String[] args) {

        Board board = new Board();
        board.setGameBoard();
        board.displayBoard();

        while (isRunning()) {
            System.out.println("Player " + (getPlayerId() + 1) + " turn.");
            Board.movePawn(getPlayerId(), board.getGameBoard());
            // check for win
            int player = (getPlayerId() == 0) ? 1 : 0;
            setPlayerId(player);
        }

    }
}
