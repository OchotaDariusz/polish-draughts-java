import java.util.Scanner;

public class Board {
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    private int size;
    private int[][] gameBoard;


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
        this.gameBoard = new int[size*2][size*2];
    }
    public void printBoard(Board board){

    }

//    tu chyba powinnop byc tworzenie poionków
//    przypisanie kazdemu polu planszy jego  koordynat
}
