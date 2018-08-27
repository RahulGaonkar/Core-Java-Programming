package edu.nyu.cs9053.homework4.hierarchy;

public class FigureSkater extends IceSkater {

    private final String figureSkatingDiscipline;

    public FigureSkater(String name, int age, int skateSize, String figureSkatingDiscipline) {
        super(name, age, skateSize);
        this.figureSkatingDiscipline = figureSkatingDiscipline;
    }

    public String getFigureSkatingDiscipline() {
        return figureSkatingDiscipline;
    }

    @Override
    public boolean equals(Object a) {
        if (this == a) {
            return true;
        }
        if ((a == null) || (getClass() != a.getClass())) {
            return false;
        }
        FigureSkater figureSkater = (FigureSkater) a;
        return ((getName() == null ? figureSkater.getName() == null : getName().equals(figureSkater.getName()))
                && (getAge() == figureSkater.getAge()) && (getSkateSize() == figureSkater.getSkateSize())
                && (figureSkatingDiscipline == null ? figureSkater.figureSkatingDiscipline == null
                        : figureSkatingDiscipline.equals(figureSkater.figureSkatingDiscipline)));
    }

    @Override
    public int hashCode() {
        int result = getName() == null ? 0 : getName().hashCode();
        Integer age = getAge();
        result = 31 * result + age.hashCode();
        Integer skateSize = getSkateSize();
        result = 31 * result + skateSize.hashCode();
        result = 31 * result + (figureSkatingDiscipline == null ? 0 : figureSkatingDiscipline.hashCode());
        return result;
    }
}
