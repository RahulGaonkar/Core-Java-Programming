package edu.nyu.cs9053.homework4.hierarchy;

public class Snowboarder extends WinterSportPlayer {

    private final int snowBoardLength;

    public Snowboarder(String name, int age, int snowBoardLength) {
        super(name, age);
        this.snowBoardLength = snowBoardLength;
    }

    public int getSnowBoardLength() {
        return snowBoardLength;
    }

    @Override
    public boolean equals(Object a) {
        if (this == a) {
            return true;
        }
        if ((a == null) || (getClass() != a.getClass())) {
            return false;
        }
        Snowboarder snowboarder = (Snowboarder) a;
        return ((getName() == null ? snowboarder.getName() == null : getName().equals(snowboarder.getName()))
                && (getAge() == snowboarder.getAge()) && (getSnowBoardLength() == snowboarder.getSnowBoardLength()));
    }

    @Override
    public int hashCode() {
        int result = getName() == null ? 0 : getName().hashCode();
        Integer age = getAge();
        result = 31 * result + age.hashCode();
        Integer snowBoardLength = getSnowBoardLength();
        result = 31 * result + snowBoardLength.hashCode();
        return result;
    }

}
