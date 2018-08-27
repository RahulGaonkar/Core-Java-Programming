package edu.nyu.cs9053.homework10;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicReference;

/**
 * User: blangel
 */
public class ModernFortification extends ConcurrencyFactorProviderImplementation
        implements Fortification<ExecutorService> {

    private final AtomicReference<ExecutorService> modernFortificationSoldiersExecutorServiceReference;

    public ModernFortification(int concurrencyFactor, ExecutorService modernFortificationSoldiersExecutorService) {
        super(concurrencyFactor);
        this.modernFortificationSoldiersExecutorServiceReference = new AtomicReference<>(
                modernFortificationSoldiersExecutorService);
    }

    @Override
    public void handleAttack(AttackHandler handler) {
        modernFortificationSoldiersExecutorServiceReference.get().submit(new Runnable() {
            @Override
            public void run() {
                handler.soldiersReady();
            }
        });
    }

    @Override
    public void surrender() {
        modernFortificationSoldiersExecutorServiceReference.get().shutdown();
    }
}
