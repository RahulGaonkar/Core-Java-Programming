package edu.nyu.cs9053.homework9;

public class OrderPlaced implements Customer {

    private final CoffeeDrink coffeeDrink;

    public OrderPlaced(CoffeeDrink coffeeDrink) {
        this.coffeeDrink = coffeeDrink;
    }

    @Override
    public OrderNumber order(Queue queue) {
        Thread threadThatAcquiredTheSemaphore = null;
        try {
            Factory.getBinarySemaphore().acquire();
            threadThatAcquiredTheSemaphore = Thread.currentThread();
            if (!queue.full()) {
                return queue.addOrder(coffeeDrink);
            }
            return null;
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(ie);
        } finally {
            if (Thread.currentThread().equals(threadThatAcquiredTheSemaphore)) {
                Factory.getBinarySemaphore().release();
            }
        }
    }

}
