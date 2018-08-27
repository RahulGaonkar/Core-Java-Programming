package edu.nyu.cs9053.homework4.hierarchy;

public class SkeletonPlayer extends Sledder {

    private final int ballastWeight;

    public SkeletonPlayer(String name, int age, String sledColor, int ballastWeight) {
        super(name, age, sledColor);
        this.ballastWeight = ballastWeight;
    }

    public int getBallastWeight() {
        return ballastWeight;
    }

    @Override
    public boolean equals(Object a) {
        if (this == a) {
            return true;
        }
        if ((a == null) || (getClass() != a.getClass())) {
            return false;
        }
        SkeletonPlayer skeletonPlayer = (SkeletonPlayer) a;
        return ((getName() == null ? skeletonPlayer.getName() == null : getName().equals(skeletonPlayer.getName()))
                && (getAge() == skeletonPlayer.getAge())
                && (getSledColor() == null ? skeletonPlayer.getSledColor() == null
                        : getSledColor().equals(skeletonPlayer.getSledColor()))
                && (ballastWeight == skeletonPlayer.ballastWeight));
    }

    @Override
    public int hashCode() {
        int result = getName() == null ? 0 : getName().hashCode();
        Integer age = getAge();
        result = 31 * result + age.hashCode();
        result = 31 * result + (getSledColor() == null ? 0 : getSledColor().hashCode());
        Integer ballastWeight = this.ballastWeight;
        result = 31 * result + ballastWeight.hashCode();
        return result;
    }
}
