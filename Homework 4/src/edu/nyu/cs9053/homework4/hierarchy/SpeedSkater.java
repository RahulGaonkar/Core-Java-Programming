package edu.nyu.cs9053.homework4.hierarchy;

public class SpeedSkater extends IceSkater {

    private final String speedSkatingType;

    public SpeedSkater(String name, int age, int skateSize, String speedSkatingType) {
        super(name, age, skateSize);
        this.speedSkatingType = speedSkatingType;
    }

    public String getSpeedSkatingType() {
        return speedSkatingType;
    }

    @Override
    public boolean equals(Object a) {
        if (this == a) {
            return true;
        }
        if ((a == null) || (getClass() != a.getClass())) {
            return false;
        }
        SpeedSkater speedSkater = (SpeedSkater) a;
        return ((getName() == null ? speedSkater.getName() == null : getName().equals(speedSkater.getName()))
                && (getAge() == speedSkater.getAge()) && (getSkateSize() == speedSkater.getSkateSize())
                && (speedSkatingType == null ? speedSkater.speedSkatingType == null
                        : speedSkatingType.equals(speedSkater.speedSkatingType)));
    }

    @Override
    public int hashCode() {
        int result = getName() == null ? 0 : getName().hashCode();
        Integer age = getAge();
        result = 31 * result + age.hashCode();
        Integer skateSize = getSkateSize();
        result = 31 * result + skateSize.hashCode();
        result = 31 * result + (speedSkatingType == null ? 0 : speedSkatingType.hashCode());
        return result;
    }

}
