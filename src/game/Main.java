package game;

import java.util.ArrayList;
import java.util.LinkedList;
import board.Board;

public class Main {

    public static void main(String[] args) {
        ArrayList array = new ArrayList<>();
        LinkedList list = new LinkedList<>();
        // add
        array.add(2);
        array.add(2);
        array.add(2);
        array.add(2);
        array.add(2);
        array.add("2");
        array.add(true);
        int a = array.size();
        int b = (int) array.get(3);
        System.out.println(b);
        System.out.println(array.get(3));


        System.out.println(a);
        System.out.println(array);
        Board board = new Board();
        board.setGameBoard();

    }
}
