package edu.nyu.cs9053.homework4.hierarchy;

public class MogulSkier extends Skier {

    private final int mogulSkiingScore;

    public MogulSkier(String name, int age, int skiLength, int mogulSkiingScore) {
        super(name, age, skiLength);
        this.mogulSkiingScore = mogulSkiingScore;
    }

    public int getMogulSkiingScore() {
        return mogulSkiingScore;
    }

    @Override
    public boolean equals(Object a) {
        if (this == a) {
            return true;
        }
        if ((a == null) || (getClass() != a.getClass())) {
            return false;
        }
        MogulSkier mogulSkier = (MogulSkier) a;
        return ((getName() == null ? mogulSkier.getName() == null : getName().equals(mogulSkier.getName()))
                && (getAge() == mogulSkier.getAge()) && (getSkiLength() == mogulSkier.getSkiLength())
                && (mogulSkiingScore == mogulSkier.mogulSkiingScore));
    }

    @Override
    public int hashCode() {
        int result = getName() == null ? 0 : getName().hashCode();
        Integer age = getAge();
        result = 31 * result + age.hashCode();
        Integer skiLength = getSkiLength();
        result = 31 * result + skiLength.hashCode();
        Integer mogulSkiingScore = this.mogulSkiingScore;
        result = 31 * result + mogulSkiingScore.hashCode();
        return result;
    }
}
