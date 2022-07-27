package board;
import java.util.Scanner;

import pawn.Color;
import pawn.Pawn;

import static java.lang.Integer.parseInt;

public class Board {
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    private int size;

    private Pawn[][] gameBoard;

    public Pawn[][] getGameBoard() {
        return gameBoard;
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
        int y_offset = gameBoard.length - 4;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < gameBoard.length; x++) {
                if ((1 + x + y) % 2 == 0) { //shifting +1 spot to the right to mirrow upper pawn rows
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
        System.out.println("");

        System.out.print("   ");
        for (int x = 0; x < gameBoard[0].length; x++) {
            System.out.print("----");
        }
        System.out.println("-");


        for (int y = 0; y < gameBoard.length; y++) {

            if (y <10)
                System.out.print(" ");
            System.out.print(y + " ");

            for (int x = 0; x < gameBoard[0].length; x++) {
                String pawn_symbol = " ";

                if (gameBoard[y][x] != null && gameBoard[y][x].getColor() == Color.BLACK)
                    pawn_symbol = "B";
                if (gameBoard[y][x] != null && gameBoard[y][x].getColor() == Color.WHITE)
                    pawn_symbol = "W";
                System.out.print("|");

                if ( (x+y) %2 == 0)
                {
                    System.out.print(Console.Color.BLACK);
                    System.out.print(Console.Color.WHITE_BACKGROUND);
                }
                System.out.print(" "+ pawn_symbol + " ");
                System.out.print(Console.Color.RESET);
            }
            System.out.println("|");
        }

    }

    static int[] getUserInput(String info) {
        Scanner scan = new Scanner(System.in);
        System.out.println(info);
        String pos = scan.next().toUpperCase();
        scan.nextLine();
        char posXChar = pos.charAt(0);
        int posXInt = posXChar - 65;
        int posY = parseInt(pos.substring(1))-1;
        System.out.println(posXChar);
        System.out.println(posXInt);
        System.out.println(posY);
        int[] cords = new int[2];
        cords[0] = posXInt;
        cords[1] = posY;
        return cords;
    }

    public static void movePawn(int playerID, Pawn[][] gameBoard){
        int[] pawnCords = getUserInput("Specify pawn");
        int[] moveCords = getUserInput("Specify position");

        boolean canMove = gameBoard[pawnCords[0]][pawnCords[1]].tryToMakeMove(gameBoard, moveCords[0], moveCords[1], playerID);
        if (canMove) {
            System.out.println("You can move");
        } else {
            System.out.println("You can't move");
        }
    }

    public Integer checkForWinner() {
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
