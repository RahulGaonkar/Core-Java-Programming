package edu.nyu.cs9053.homework4.hierarchy;

public abstract class Skier extends WinterSportPlayer {

    private final int skiLength;

    protected Skier(String name, int age, int skiLength) {
        super(name, age);
        this.skiLength = skiLength;
    }

    public int getSkiLength() {
        return skiLength;
    }

}
