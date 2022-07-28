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

        Board board = Board.getInstance("Board");
        board.setGameBoard();
        int size = board.getSize();
        
        Integer winnerId = null;

        while (isRunning()) {
            board.displayBoard();
            System.out.println("Player " + (getPlayerId() + 1) + " turn.");

            Board.movePawn(getPlayerId(), board.getGameBoard(), size);

            winnerId = Board.checkForWinner(board.getGameBoard());
            if (winnerId != null) {
                setRunning(false);
            }
            
            int player = (getPlayerId() == 0) ? 1 : 0;
            setPlayerId(player);
        }
        board.displayBoard();
        System.out.println("Player " + (winnerId + 1) + " won!");
    }
}
