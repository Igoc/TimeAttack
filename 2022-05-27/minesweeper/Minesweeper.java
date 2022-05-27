package minesweeper;

public class Minesweeper {
    public static int[] DX = {0, 1, 1, 1, 0, -1, -1, -1};
    public static int[] DY = {-1, -1, 0, 1, 1, 1, 0, -1};

    public static int WIDTH = 10;
    public static int HEIGHT = 10;

    private int[][] map;
    private int blankNumber;
    private int tryNumber;

    public Minesweeper(int[][] map) {
        this.map = map;

        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {
                if (map[row][col] == 0) {
                    blankNumber++;
                }
            }
        }
    }

    public int pick(int x, int y) {
        if (map[y][x] == 1) {
            return -1;
        }

        int output = 0;

        if (map[y][x] != 2) {
            blankNumber--;
        }

        map[y][x] = 2;
        tryNumber++;

        for (int index = 0; index < 8; index++) {
            int newX = x + DX[index];
            int newY = y + DY[index];

            if (newX >= 0 && newX < WIDTH && newY >= 0 && newY < HEIGHT) {
                if (map[newY][newX] == 0) {
                    map[newY][newX] = 2;
                    blankNumber--;
                } else if (map[newY][newX] == 1) {
                    output++;
                }
            }
        }

        return output;
    }

    public boolean checkMap() {
        return blankNumber == 0;
    }

    public void printMap() {
        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {
                System.out.print(map[row][col] + " ");
            }

            System.out.println();
        }
    }

    public int getTryNumber() {
        return tryNumber;
    }
}