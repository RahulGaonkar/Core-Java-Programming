package edu.nyu.cs9053.homework4.hierarchy;

public class Curler extends WinterSportPlayer {

    public final int curlingShoeSize;

    public Curler(String name, int age, int curlingShoesSize) {
        super(name, age);
        this.curlingShoeSize = curlingShoesSize;
    }

    public int getCurlingShoeSize() {
        return curlingShoeSize;
    }

    @Override
    public boolean equals(Object a) {
        if (this == a) {
            return true;
        }
        if ((a == null) || (getClass() != a.getClass())) {
            return false;
        }
        Curler curler = (Curler) a;
        return ((getName() == null ? curler.getName() == null : getName().equals(curler.getName()))
                && (getAge() == curler.getAge()) && (getCurlingShoeSize() == curler.getCurlingShoeSize()));
    }

    @Override
    public int hashCode() {
        int result = getName() == null ? 0 : getName().hashCode();
        Integer age = getAge();
        result = 31 * result + age.hashCode();
        Integer curlingShoeSize = getCurlingShoeSize();
        result = 31 * result + curlingShoeSize.hashCode();
        return result;
    }

}
