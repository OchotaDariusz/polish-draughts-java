import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList array = new ArrayList<>();
        LinkedList list = new LinkedList<>();
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
