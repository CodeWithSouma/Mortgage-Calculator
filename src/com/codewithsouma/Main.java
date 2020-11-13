package com.codewithsouma;

import java.text.NumberFormat;

public class Main {
    final static byte MONTHS_IN_YEAR = 12;
    final static byte PERCENT = 100;

    public static void main(String[] args) {
        int principal = (int) Console.readNumber("Principal: ", 1000, 1_000_000);
        float annualInterestRate = (float) Console.readNumber("Annual Interest Rate: ", 1, 30);
        byte years = (byte) Console.readNumber("Period(Years): ", 1, 30);

        printMortgage(principal, annualInterestRate, years);
        printPaymentSchedule(principal, annualInterestRate, years);
    }

    private static void printMortgage(int principal, float annualInterestRate, byte years) {
        double mortgage = calculateMortgage(principal, annualInterestRate, years);
        String formattedMortgage = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println();
        System.out.println("MORTGAGE");
        System.out.println("--------");
        System.out.println("Monthly Payments: " + formattedMortgage);
    }

    public static void printPaymentSchedule(int principal, float annualInterestRate, byte years) {
        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("----------------");
        for (short month = 1; month <= years * MONTHS_IN_YEAR; month++) {
            double balance = calculateBalance(principal, annualInterestRate, years, month);
            String formattedBalance = NumberFormat.getCurrencyInstance().format(balance);
            System.out.println(formattedBalance);
        }
    }

    public static double calculateMortgage(
            int principal,
            float annualInterestRate,
            byte years) {

        float monthlyInterestRate = annualInterestRate / (MONTHS_IN_YEAR * PERCENT);
        short numberOfPayments = (short) (years * MONTHS_IN_YEAR);

        double mortgage = principal *
                ((monthlyInterestRate * Math.pow((monthlyInterestRate + 1), numberOfPayments))
                        / (Math.pow((1 + monthlyInterestRate), numberOfPayments) - 1));

        return mortgage;
    }

    public static double calculateBalance(
            int principal,
            float annualInterestRate,
            byte years,
            short noOfPaymentsMade) {

        short numberOfPayments = (short) (years * MONTHS_IN_YEAR);
        float monthlyInterestRate = annualInterestRate / (MONTHS_IN_YEAR * PERCENT);
        double balance = 0;

        balance = (principal * (Math.pow(1 + monthlyInterestRate, numberOfPayments)
                - Math.pow(1 + monthlyInterestRate, noOfPaymentsMade))) /
                (Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1);

        return balance;
    }

}
