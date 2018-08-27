package edu.nyu.cs9053.homework9;

public class SkinnyMocha implements CoffeeDrink {

    private final boolean decaffeinated;
    private final boolean containsMilk;

    public SkinnyMocha(boolean decaffeinated, boolean containsMilk) {
        this.decaffeinated = decaffeinated;
        this.containsMilk = containsMilk;
    }

    @Override
    public boolean isDecaf() {
        return decaffeinated;
    }

    @Override
    public boolean containsMilk() {
        return containsMilk;
    }

}
