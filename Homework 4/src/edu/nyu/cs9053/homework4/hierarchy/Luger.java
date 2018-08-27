package edu.nyu.cs9053.homework4.hierarchy;

public class Luger extends Sledder {

    private final String lugeDiscipline;

    public Luger(String name, int age, String sledColor, String lugeDiscipline) {
        super(name, age, sledColor);
        this.lugeDiscipline = lugeDiscipline;
    }

    public String getLugeDiscipline() {
        return lugeDiscipline;
    }

    @Override
    public boolean equals(Object a) {
        if (this == a) {
            return true;
        }
        if ((a == null) || (getClass() != a.getClass())) {
            return false;
        }
        Luger luger = (Luger) a;
        return ((getName() == null ? luger.getName() == null : getName().equals(luger.getName()))
                && (getAge() == luger.getAge())
                && (getSledColor() == null ? luger.getSledColor() == null : getSledColor().equals(luger.getSledColor()))
                && (lugeDiscipline == null ? luger.lugeDiscipline == null
                        : lugeDiscipline.equals(luger.lugeDiscipline)));
    }

    @Override
    public int hashCode() {
        int result = getName() == null ? 0 : getName().hashCode();
        Integer age = getAge();
        result = 31 * result + age.hashCode();
        result = 31 * result + (getSledColor() == null ? 0 : getSledColor().hashCode());
        result = 31 * result + (lugeDiscipline == null ? 0 : lugeDiscipline.hashCode());
        return result;
    }
}
