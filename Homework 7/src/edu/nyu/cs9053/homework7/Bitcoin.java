package edu.nyu.cs9053.homework7;

public class Bitcoin implements Cryptocurrency, ArrayCreator<Bitcoin> {

    private final double amount;

    public Bitcoin(double amount) {
        this.amount = amount;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public Bitcoin[] create(int sizeOfArray) {
        return new Bitcoin[sizeOfArray];
    }

    @Override
    public boolean equals(Object a) {
        if (this == a) {
            return true;
        }

        if (a == null || getClass() != a.getClass()) {
            return false;
        }

        Bitcoin bitcoin = (Bitcoin) a;
        return amount == bitcoin.amount;
    }

    @Override
    public int hashCode() {
        Double cryptocurrencyAmount = amount;
        int result = cryptocurrencyAmount.hashCode();
        return result;
    }

}
