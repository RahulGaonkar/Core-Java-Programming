package edu.nyu.cs9053.homework5;

import java.util.concurrent.atomic.AtomicReference;

public class Baguette implements Recipe {

    private static final int FOOD_VOLUME_CUBIC_INCHES = 2000;
    private static final double DECAY_RATE = 0.5;
    private final AtomicReference<Double> remainingTime;
    private final AtomicReference<Integer> currentTemperature;

    public Baguette() {
        remainingTime = new AtomicReference<>(0d);
        currentTemperature = new AtomicReference<>(0);
    }

    @Override
    public void initializeFromOven(Oven oven) {
        remainingTime.set(20d);
        currentTemperature.set(oven.getSetTemperature());
    }

    @Override
    public int getVolumeCubicInches() {
        return Baguette.FOOD_VOLUME_CUBIC_INCHES;
    }

    @Override
    public Double getRemainingSecondsUntilDone() {
        return remainingTime.get() * 60;
    }

    @Override
    public void adjust(Time unit, int amount, int ovenTemperature) {

        if (unit.toString() == "Minutes") {
            remainingTime.set(remainingTime.get() - remainingTime.get() * Math.pow(Math.E, -(DECAY_RATE * amount)));
        } else if (unit.toString() == "Seconds") {
            remainingTime
                    .set(remainingTime.get() - remainingTime.get() * Math.pow(Math.E, -(DECAY_RATE * (amount / 60.0))));
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
