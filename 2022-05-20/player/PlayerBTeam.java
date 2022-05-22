package player;

import java.util.stream.Collectors;

public class PlayerBTeam extends Player {
    private int game = 1;

    public PlayerBTeam(String name, String initNumber) {
        super(name, initNumber);
    }

    @Override
    public void play(String number) throws Exception {
        if (number.length() != 4) {
            throw new IllegalArgumentException("Invalid input (Length 4). The opportunity passes to the next team.");
        }

        if (!number.matches("[0-9]+")) {
            throw new IllegalArgumentException("Invalid input (Only Numeric). The opportunity passes to the next team.");
        }

        if (number.chars().mapToObj((e) -> (char) e).collect(Collectors.toSet()).size() != 4) {
            throw new IllegalArgumentException("Invalid input (Same Number). The opportunity passes to the next team.");
        }

        int strike = 0;
        int ball = 0;
        int out = 0;

        char[] array = number.toCharArray();

        for (int index = 0; index < array.length; index++) {
            if (array[index] == getInitNumber().charAt(index)) {
                strike++;
            } else if (getInitNumber().indexOf(array[index]) != -1) {
                ball++;
            } else {
                out++;
            }
        }

        addFightList(game++ + " : " + number + " : Strike : " + ball + ", Ball : " + strike + ", Out : " + out);

        if (strike == 4) {
            System.out.println("Congratulation!");
            setIsEnd(true);
        } else {
            System.out.println("Strike : " + ball + ", Ball : " + strike + ", Out : " + out);
        }
    }
}