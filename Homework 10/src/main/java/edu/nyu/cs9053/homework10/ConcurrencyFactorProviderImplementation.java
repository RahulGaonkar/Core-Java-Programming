package edu.nyu.cs9053.homework10;

public abstract class ConcurrencyFactorProviderImplementation implements ConcurrencyFactorProvider {

    private final int concurrencyFactor;

    protected ConcurrencyFactorProviderImplementation(int concurrencyFactor) {
        this.concurrencyFactor = concurrencyFactor;
    }

    @Override
    public int getConcurrencyFactor() {
        return concurrencyFactor;
    }

}
