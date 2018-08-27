package edu.nyu.cs9053.homework10;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * User: blangel
 */

public class MiddleAgesFortification extends ConcurrencyFactorProviderImplementation implements Fortification<Thread> {

    private final BlockingQueue<Thread> middleAgesFortificationSoldiersBlockingQueue;
    private final Lock middleAgesFortificationSoldiersBlockingQueueControllingLock;
    private final Condition middleAgesFortificationSoldiersBlockingQueueFullCondition;

    public MiddleAgesFortification(int concurrencyFactor,
            BlockingQueue<Thread> middleAgesFortificationSoldiersBlockingQueue) {
        super(concurrencyFactor);
        this.middleAgesFortificationSoldiersBlockingQueue = middleAgesFortificationSoldiersBlockingQueue;
        this.middleAgesFortificationSoldiersBlockingQueueControllingLock = new ReentrantLock();
        this.middleAgesFortificationSoldiersBlockingQueueFullCondition = middleAgesFortificationSoldiersBlockingQueueControllingLock
                .newCondition();
    }

    @Override
    public void handleAttack(AttackHandler handler) {
        addMiddleAgesFortificationSoldiersBlockingQueueThread(handler);
        removeMiddleAgesFortificationSoldiersBlockingQueueThread(handler);
    }

    @Override
    public void surrender() {
        middleAgesFortificationSoldiersBlockingQueue.clear();
    }

    private void removeMiddleAgesFortificationSoldiersBlockingQueueThread(AttackHandler handler) {
        middleAgesFortificationSoldiersBlockingQueueControllingLock.lock();
        Runnable middleAgesFortificationSoldiersBlockingQueueThreadWork = middleAgesFortificationSoldiersBlockingQueue
                .poll();
        if (middleAgesFortificationSoldiersBlockingQueueThreadWork != null) {
            middleAgesFortificationSoldiersBlockingQueueThreadWork.run();
            middleAgesFortificationSoldiersBlockingQueueFullCondition.signalAll();
        }
        middleAgesFortificationSoldiersBlockingQueueControllingLock.unlock();
    }

    private void addMiddleAgesFortificationSoldiersBlockingQueueThread(AttackHandler handler) {
        middleAgesFortificationSoldiersBlockingQueueControllingLock.lock();
        try {
            while (middleAgesFortificationSoldiersBlockingQueue.size() == getConcurrencyFactor()) {
                middleAgesFortificationSoldiersBlockingQueueFullCondition.await();
            }
            middleAgesFortificationSoldiersBlockingQueue.offer(new Thread(new Runnable() {
                @Override
                public void run() {
                    handler.soldiersReady();
                }
            }));
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(ie);
        } finally {
            middleAgesFortificationSoldiersBlockingQueueControllingLock.unlock();
        }
    }
}
