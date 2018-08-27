package edu.nyu.cs9053.homework4.hierarchy;

public class Bobsledder extends Sledder {

    private final int bobsledTeamSize;

    public Bobsledder(String name, int age, String sledColor, int bobsledTeamSize) {
        super(name, age, sledColor);
        this.bobsledTeamSize = bobsledTeamSize;
    }

    public int getBobsledTeamSize() {
        return bobsledTeamSize;
    }

    @Override
    public boolean equals(Object a) {
        if (this == a) {
            return true;
        }
        if ((a == null) || (getClass() != a.getClass())) {
            return false;
        }
        Bobsledder bobsledder = (Bobsledder) a;
        return ((getName() == null ? bobsledder.getName() == null : getName().equals(bobsledder.getName()))
                && (getAge() == bobsledder.getAge())
                && (getSledColor() == null ? bobsledder.getSledColor() == null
                        : getSledColor().equals(bobsledder.getSledColor()))
                && (bobsledTeamSize == bobsledder.bobsledTeamSize));
    }

    @Override
    public int hashCode() {
        int result = getName() == null ? 0 : getName().hashCode();
        Integer age = getAge();
        result = 31 * result + age.hashCode();
        result = 31 * result + (getSledColor() == null ? 0 : getSledColor().hashCode());
        Integer bobsledTeamSize = this.bobsledTeamSize;
        result = 31 * result + bobsledTeamSize.hashCode();
        return result;
    }
}
