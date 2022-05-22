package player;

import java.util.LinkedList;
import java.util.List;

public abstract class Player {
    private String name;
    private String initNumber;
    private Boolean isEnd;

    private List<String> fightList = new LinkedList<>();

    public Player(String name, String initNumber) {
        this.name = name;
        this.initNumber = initNumber;
        this.isEnd = false;
    }

    public abstract void play(String number) throws Exception;

    public void addFightList(String history) {
        fightList.add(history);
    }

    public void printFightList() {
        System.out.println(name);
        System.out.println("-------------------------------------");

        for (String history : fightList) {
            System.out.println(history);
        }

        System.out.println("-------------------------------------");
    }

    public String getName() {
        return name;
    }

    public String getInitNumber() {
        return initNumber;
    }

    public Boolean getIsEnd() {
        return isEnd;
    }

    public void setIsEnd(Boolean isEnd) {
        this.isEnd = isEnd;
    }
}