package edu.nyu.cs9053.homework9;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * User: blangel
 */
public class Factory {

    private static final Semaphore BINARY_SEMAPHORE = new Semaphore(1);
    private static final CoffeeDrink[] COFFEE_DRINK = new CoffeeDrink[] { new Cappuccino(true, true),
            new Espresso(false, true), new FlatWhite(true, false), new SkinnyMocha(false, false) };

    public static Semaphore getBinarySemaphore() {
        return BINARY_SEMAPHORE;
    }
    
    public static Customer createCustomer() {
        Random randomOrderGenerator = new Random();
        return new OrderPlaced(COFFEE_DRINK[randomOrderGenerator.nextInt(COFFEE_DRINK.length)]);
    }

    public static Barista createBarista() {
        return new OrderServiced();
    }

}
