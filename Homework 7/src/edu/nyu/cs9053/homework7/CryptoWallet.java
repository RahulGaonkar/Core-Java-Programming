package edu.nyu.cs9053.homework7;

public class CryptoWallet<T extends ArrayCreator<T> & Cryptocurrency> extends Wallet<T> {

    public CryptoWallet(T[] initialWalletArray) {
        super(initialWalletArray);
    }
}
