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
        float monthlyInterestRate = getMonthlyInterestRate();
        short numberOfPayments = getNumberOfPayments();

        double mortgage = principal *
                ((monthlyInterestRate * Math.pow((monthlyInterestRate + 1), numberOfPayments))
                        / (Math.pow((1 + monthlyInterestRate), numberOfPayments) - 1));

        return mortgage;
    }

    public double calculateBalance(short noOfPaymentsMade) {
        short numberOfPayments = getNumberOfPayments();
        float monthlyInterestRate = getMonthlyInterestRate();
        double balance = 0;

        balance = (principal * (Math.pow(1 + monthlyInterestRate, numberOfPayments)
                - Math.pow(1 + monthlyInterestRate, noOfPaymentsMade))) /
                (Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1);

        return balance;
    }

    private short getNumberOfPayments() {
        return (short) (years * MONTHS_IN_YEAR);
    }

    private float getMonthlyInterestRate() {
        return annualInterestRate / (MONTHS_IN_YEAR * PERCENT);
    }

    public short getYears() {
        return years;
    }
}
