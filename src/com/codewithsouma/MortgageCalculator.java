package com.codewithsouma;

public class MortgageCalculator {
    public final static byte MONTHS_IN_YEAR = 12;
    public final static byte PERCENT = 100;
    private int principal;
    private float annualInterestRate;
    private byte years;

    public MortgageCalculator(int principal, float annualInterestRate, byte years) {
        this.principal = principal;
        this.annualInterestRate = annualInterestRate;
        this.years = years;
    }

    public double calculateMortgage() {
        float monthlyInterestRate = annualInterestRate / (MONTHS_IN_YEAR * PERCENT);
        short numberOfPayments = (short) (years * MONTHS_IN_YEAR);

        double mortgage = principal *
                ((monthlyInterestRate * Math.pow((monthlyInterestRate + 1), numberOfPayments))
                        / (Math.pow((1 + monthlyInterestRate), numberOfPayments) - 1));

        return mortgage;
    }

    public double calculateBalance(short noOfPaymentsMade) {
        short numberOfPayments = (short) (years * MONTHS_IN_YEAR);
        float monthlyInterestRate = annualInterestRate / (MONTHS_IN_YEAR * PERCENT);
        double balance = 0;

        balance = (principal * (Math.pow(1 + monthlyInterestRate, numberOfPayments)
                - Math.pow(1 + monthlyInterestRate, noOfPaymentsMade))) /
                (Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1);

        return balance;
    }

    public short getYears() {
        return years;
    }
}
