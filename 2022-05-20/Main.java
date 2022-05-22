import player.Player;
import player.PlayerATeam;
import player.PlayerBTeam;
import player.PlayerCTeam;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Player playerArray[] = {
                new PlayerATeam("A Team", "1234"),
                new PlayerBTeam("B Team", "5678"),
                new PlayerCTeam("C Team", "2468")
        };

        int checkPlayer = 0;

        while (true) {
            checkPlayer = 0;

            for (Player player : playerArray) {
                if (player.getIsEnd() == false) {
                    System.out.print("Please enter a 4 digit number (" + player.getName() + ") defence : ");

                    String number = scanner.nextLine();

                    try {
                        player.play(number);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    checkPlayer++;
                }
            }

            if (checkPlayer == playerArray.length) {
                break;
            }
        }

        for (Player player : playerArray) {
            player.printFightList();
        }
    }
}