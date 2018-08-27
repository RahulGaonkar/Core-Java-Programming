package edu.nyu.cs9053.homework10;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * User: blangel
 */
public class FortificationFactory {

    public static Fortification<Thread> createMiddleAges(int concurrencyFactor) {
        BlockingQueue<Thread> middleAgesFortificationSoldiersBlockingQueue = new LinkedBlockingQueue<>(
                concurrencyFactor);
        return new MiddleAgesFortification(concurrencyFactor, middleAgesFortificationSoldiersBlockingQueue);
    }

    public static Fortification<ExecutorService> createModern(final int concurrencyFactor) {
        ExecutorService modernFortificationSoldiersExecutorService = Executors.newFixedThreadPool(concurrencyFactor);
        return new ModernFortification(concurrencyFactor, modernFortificationSoldiersExecutorService);
    }

}
