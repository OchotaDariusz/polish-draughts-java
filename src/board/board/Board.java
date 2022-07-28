package board;

import java.util.Scanner;

import pawn.Color;
import pawn.Pawn;

import static java.lang.Integer.parseInt;

public final class Board {
    private static Board instance;
    public String value;
    private int size;

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    private Pawn[][] gameBoard;

    public Pawn[][] getGameBoard() {
        return gameBoard;
    }

    private Board(String value) {
        // The following code emulates slow initialization.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        this.value = value;
    }

    public static Board getInstance(String value) {
        if (instance == null) {
            instance = new Board(value);
        }
        return instance;
    }

    public void setGameBoard() {
        System.out.println("Please provide board size");
        Scanner scan = new Scanner(System.in);
        boolean flag = true;
        int size = 0;
        while (flag) {
            size = scan.nextInt();
            if (10 <= size && size <= 20) {
                flag = false;
                setSize(size);
            } else {
                System.out.println("Invalid value");
            }
        }

        // initialize the board
        Pawn[][] gameBoard = new Pawn[size][size];

        // set pawns for player 0
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < gameBoard.length; x++) {
                if ((x + y) % 2 == 0) { // pawn on even/odd positions
                    gameBoard[y][x] = new Pawn(x, y, 0);
                }
            }
        }

        // set pawns for player 1
        int pos_fix = gameBoard.length % 2;
        int y_offset = gameBoard.length - 4;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < gameBoard.length; x++) {
                if ((x + y + pos_fix) % 2 == 0) { //shifting +1 spot to the right to mirrow upper pawn rows
                    gameBoard[y + y_offset][x] = new Pawn(x, y + y_offset, 1);
                }
            }
        }
        this.gameBoard = gameBoard;
    }

    public void displayBoard() {
        // display header
        System.out.print("   ");
        for (int x = 0; x < gameBoard[0].length; x++) {
            char col_symbol = (char) ('A' + x);
            System.out.print("  " + col_symbol + " ");
        }
        System.out.println();

        System.out.print("   ");
        for (int x = 0; x < gameBoard[0].length; x++) {
            System.out.print("----");
        }
        System.out.println("-");


        for (int y = 0; y < gameBoard.length; y++) {

            if (y < 10)
                System.out.print(" ");
            System.out.print(y + " ");

            for (int x = 0; x < gameBoard[0].length; x++) {
                String pawn_symbol = " ";

                if (gameBoard[y][x] != null && gameBoard[y][x].getColor() == Color.BLACK)
                    pawn_symbol = "B";
                if (gameBoard[y][x] != null && gameBoard[y][x].getColor() == Color.WHITE)
                    pawn_symbol = "W";
                System.out.print("|");

                if ((x + y) % 2 == 0) {
                    System.out.print(Console.Color.BLACK);
                    System.out.print(Console.Color.WHITE_BACKGROUND);
                }
                System.out.print(" " + pawn_symbol + " ");
                System.out.print(Console.Color.RESET);
            }
            System.out.println("|");
        }

    }

    static int[] getUserInput(String info, int size) {
        Scanner scan = new Scanner(System.in);
        System.out.println(info);
        String pos = scan.next().toUpperCase();
        while (pos.length() > 3) {
            System.out.println("Invalid value");
            scan.nextLine();
            pos = scan.next().toUpperCase();
        }
        scan.nextLine();
        char posXChar = pos.charAt(0);
        int posXInt = posXChar - 65;
        if (posXInt >= size) {
            System.out.println("Invalid value");
            return getUserInput(info, size);
        }
        int posY = parseInt(pos.substring(1));
        if (posY >= size) {
            System.out.println("Invalid value");
            return getUserInput(info, size);
        }
        int[] cords = new int[2];
        cords[1] = posXInt;
        cords[0] = posY;
        return cords;
    }

    static boolean checkForEnemyPawn(Pawn[][] gameBoard, int[] moveCords, int playerID) {
        if (gameBoard[moveCords[0]][moveCords[1]] != null) {
            return gameBoard[moveCords[0]][moveCords[1]].getPlayer() != playerID;
        }
        System.out.println("You cannot do that");
        return false;
    }

    public static void movePawn(int playerID, Pawn[][] gameBoard, int size) {

        int[] pawnCords = getUserInput("Specify pawn", size);

        while (
                !Pawn.isMovePossible(gameBoard, pawnCords[0], pawnCords[1], playerID)
                || gameBoard[pawnCords[0]][pawnCords[1]] == null
        ) {

            System.out.println("Is move possible? " + Pawn.isMovePossible(gameBoard, pawnCords[0], pawnCords[1], playerID));
            System.out.println("Is it null? " + gameBoard[pawnCords[0]][pawnCords[1]]);

            System.out.println("Invalid Pawn");
            pawnCords = getUserInput("Specify pawn", size);
        }

        int[] moveCords = getUserInput("Specify position", size);
        boolean flagMove = true;
        while (flagMove) {
            if ((Math.abs(pawnCords[0] - moveCords[0]) != 1) || ((Math.abs(pawnCords[1] - moveCords[1]) != 1))) {
                System.out.println("Invalid move position, try again");
                moveCords = getUserInput("Specify position", size);
            } else {
                flagMove = false;
            }
        }
        boolean canMove = gameBoard[pawnCords[0]][pawnCords[1]].tryToMakeMove(gameBoard, moveCords[0], moveCords[1], playerID);
        if (canMove) {
            gameBoard[moveCords[0]][moveCords[1]] = gameBoard[pawnCords[0]][pawnCords[1]];
            gameBoard[moveCords[0]][moveCords[1]].setPosX(moveCords[0]);
            gameBoard[moveCords[0]][moveCords[1]].setPosY(moveCords[1]);
            gameBoard[pawnCords[0]][pawnCords[1]] = null;
        } else {
            if (checkForEnemyPawn(gameBoard, moveCords, playerID)) {
                // capture the pawn
                boolean captured = Pawn.checkIsCapturePossible(gameBoard, pawnCords, moveCords);
                if (!captured) {
                    System.out.println("You can't capture that pawn");
                    movePawn(playerID, gameBoard, size);
                } else {
                    int[] cordsAfterCapture = new int[2];
                    cordsAfterCapture[0] = (moveCords[0] - pawnCords[0]) * 2 + pawnCords[0];
                    cordsAfterCapture[1] = (moveCords[1] - pawnCords[1]) * 2 + pawnCords[1];
                    Pawn.captureThePawn(gameBoard, pawnCords, moveCords, cordsAfterCapture);
                }
            } else {
                System.out.println("You can't move here");
                movePawn(playerID, gameBoard, size);
            }
        }
    }

    public static Integer checkForWinner(Pawn[][] gameBoard) {

        Integer currentWinner = null;
        for (int y = 0; y < gameBoard.length; y++) {
            for (int x = 0; x < gameBoard[0].length; x++) {
                if (gameBoard[x][y] == null)
                    continue;
                if (currentWinner == null)
                    currentWinner = gameBoard[x][y].getPlayer();
                if (currentWinner != gameBoard[x][y].getPlayer())
                    return null;
            }
        }
        return currentWinner;
    }

}
