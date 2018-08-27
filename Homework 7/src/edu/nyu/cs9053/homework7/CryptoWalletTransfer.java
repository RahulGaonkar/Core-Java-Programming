package edu.nyu.cs9053.homework7;

public class CryptoWalletTransfer {

    public <T> void exchangeCryptoWalletValues(CryptoWallet<? extends T> fromThisCryptoWallet,
            CryptoWallet<? super T> intoThisCryptoWallet) {
        intoThisCryptoWallet.getWalletArrayReference().set(fromThisCryptoWallet.getWalletArrayReference().get());
    }

}
