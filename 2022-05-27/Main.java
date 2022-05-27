import calculator.Calculator;
import minesweeper.Minesweeper;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        System.out.println("Intersection = " + calculator.intersection(new int[]{1, 2, 4, 11, 6, 7, 5, 14, 19, 16}, new int[]{2, 9, 8, 4, 11, 19, 15, 12}));
        System.out.println("Difference of set = " + calculator.differenceOfSet(new int[]{1, 2, 4, 11, 6, 7, 5, 14, 19, 16}, new int[]{2, 9, 8, 4, 11, 19, 15, 12}));

        int[][] map = {
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 1, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                {0, 1, 0, 0, 1, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 1, 0, 0, 1, 0, 0},
                {0, 1, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 1, 0, 0, 1, 0, 0},
                {0, 1, 0, 0, 0, 0, 1, 0, 0, 1},
                {1, 0, 1, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0}
        };

        Minesweeper minesweeper = new Minesweeper(map);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Minesweeper start");
        System.out.println("--------------------");

        while (!minesweeper.checkMap()) {
            minesweeper.printMap();

            System.out.print("Input x (0-9) : ");
            int x = scanner.nextInt();

            System.out.print("Input y (0-9) : ");
            int y = scanner.nextInt();

            if (x < 0 || x >= minesweeper.WIDTH || y < 0 || y >= minesweeper.HEIGHT) {
                break;
            }

            int mineNumber = minesweeper.pick(x, minesweeper.HEIGHT - y - 1);

            if (mineNumber == -1) {
                System.out.println("Mine has exploded");
                break;
            } else {
                System.out.println("There's a mine around : " + mineNumber);
            }
        }

        System.out.println("--------------------");
        System.out.println("Number of attempts : " + minesweeper.getTryNumber());
        System.out.println("Minesweeper end");
    }
}