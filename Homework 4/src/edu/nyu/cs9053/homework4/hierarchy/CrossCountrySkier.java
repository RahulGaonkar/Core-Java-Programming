package edu.nyu.cs9053.homework4.hierarchy;

public class CrossCountrySkier extends Skier {

    private final String skiType;

    public CrossCountrySkier(String name, int age, int skiLength, String skiType) {
        super(name, age, skiLength);
        this.skiType = skiType;
    }

    public String getSkiType() {
        return skiType;
    }

    @Override
    public boolean equals(Object a) {
        if (this == a) {
            return true;
        }
        if ((a == null) || (getClass() != a.getClass())) {
            return false;
        }
        CrossCountrySkier crossCountrySkier = (CrossCountrySkier) a;
        return ((getName() == null ? crossCountrySkier.getName() == null
                : getName().equals(crossCountrySkier.getName())) && (getAge() == crossCountrySkier.getAge())
                && (getSkiLength() == crossCountrySkier.getSkiLength())
                && (skiType == null ? crossCountrySkier.skiType == null : skiType.equals(crossCountrySkier.skiType)));
    }

    @Override
    public int hashCode() {
        int result = getName() == null ? 0 : getName().hashCode();
        Integer age = getAge();
        result = 31 * result + age.hashCode();
        Integer skiLength = getSkiLength();
        result = 31 * result + skiLength.hashCode();
        result = 31 * result + (skiType == null ? 0 : skiType.hashCode());
        return result;
    }
}
