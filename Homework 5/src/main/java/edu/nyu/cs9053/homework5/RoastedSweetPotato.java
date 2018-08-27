package edu.nyu.cs9053.homework5;

import java.util.concurrent.atomic.AtomicReference;

public class RoastedSweetPotato implements Recipe {

    private static final int FOOD_VOLUME_CUBIC_INCHES = 6000;
    private final AtomicReference<Double> remainingTime;
    private final AtomicReference<Integer> currentTemperature;

    public RoastedSweetPotato() {
        remainingTime = new AtomicReference<>(0d);
        currentTemperature = new AtomicReference<>(0);
    }

    @Override
    public void initializeFromOven(Oven oven) {
        remainingTime.set((1 / 10.0) * (oven.getSetTemperature()));
        currentTemperature.set(oven.getSetTemperature());

    }

    @Override
    public int getVolumeCubicInches() {
        return RoastedSweetPotato.FOOD_VOLUME_CUBIC_INCHES;
    }

    @Override
    public Double getRemainingSecondsUntilDone() {
        return remainingTime.get() * 60;
    }

    @Override
    public void adjust(Time unit, int amount, int ovenTemperature) {

        if (unit.toString() == "Minutes") {
            remainingTime.set(remainingTime.get() - amount);
        } else if (unit.toString() == "Seconds") {
            remainingTime.set(remainingTime.get() - (amount / 60.0));
        }
        remainingTime.set((remainingTime.get() * ovenTemperature) / currentTemperature.get());
        currentTemperature.set(ovenTemperature);
        if (remainingTime.get() < 0) {
            remainingTime.set(0d);
        }
    }

    @Override
    public boolean isRecipeDone() {
        if (getRemainingSecondsUntilDone() == 0) {
            return true;
        }
        return false;
    }

}
