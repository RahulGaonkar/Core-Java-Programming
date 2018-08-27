package edu.nyu.cs9053.homework7;

import java.util.concurrent.atomic.AtomicReference;

public abstract class Wallet<T extends ArrayCreator<T>> {

    private final AtomicReference<T[]> walletArrayReference;

    protected Wallet(T[] initialWalletArray) {
        if (initialWalletArray == null) {
            throw new IllegalArgumentException();
        }
        this.walletArrayReference = new AtomicReference<>(initialWalletArray);
    }

    public AtomicReference<T[]> getWalletArrayReference() {
        return walletArrayReference;
    }

    public boolean add(T valueToBeAdded) {
        if (contains(valueToBeAdded)) {
            return false;
        }
        T[] updatedWalletArray = valueToBeAdded.create(walletArrayReference.get().length + 1);
        System.arraycopy(walletArrayReference.get(), 0, updatedWalletArray, 0, walletArrayReference.get().length);
        updatedWalletArray[walletArrayReference.get().length] = valueToBeAdded;
        walletArrayReference.set(updatedWalletArray);
        return true;
    }

    public boolean contains(T value) {
        for (int walletArrayIndex = 0; walletArrayIndex < walletArrayReference.get().length; walletArrayIndex++) {
            if (walletArrayReference.get()[walletArrayIndex].equals(value)) {
                return true;
            }
        }
        return false;
    }

    public boolean remove(T valueToBeRemoved) {
        for (int walletArrayIndex = 0; walletArrayIndex < walletArrayReference.get().length; walletArrayIndex++) {
            if (walletArrayReference.get()[walletArrayIndex].equals(valueToBeRemoved)) {
                T[] updatedWalletArray = valueToBeRemoved.create(walletArrayReference.get().length - 1);
                System.arraycopy(walletArrayReference.get(), 0, updatedWalletArray, 0, walletArrayIndex);
                System.arraycopy(walletArrayReference.get(), walletArrayIndex + 1, updatedWalletArray, walletArrayIndex,
                        walletArrayReference.get().length - walletArrayIndex - 1);
                walletArrayReference.set(updatedWalletArray);
                return true;
            }
        }
        return false;
    }

    public T get(int walletArrayIndex) {
        if (walletArrayIndex < 0 || walletArrayIndex > walletArrayReference.get().length - 1) {
            return null;
        }
        return walletArrayReference.get()[walletArrayIndex];
    }

    public int size() {
        return walletArrayReference.get().length;
    }

}
