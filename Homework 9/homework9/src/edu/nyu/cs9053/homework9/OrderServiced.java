package edu.nyu.cs9053.homework9;

public class OrderServiced implements Barista {

    @Override
    public OrderNumber handle(Queue from) {
        Thread threadThatAcquiredTheSemaphore = null;
        try {
            Factory.getBinarySemaphore().acquire();
            threadThatAcquiredTheSemaphore = Thread.currentThread();
            if (!from.isEmpty()) {
                return from.getOrderNumber();
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
