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


    public void setGame() {
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


        this.gameBoard = new int[size][size];

    }


}
