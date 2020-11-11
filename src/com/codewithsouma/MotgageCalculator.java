package com.codewithsouma;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Scanner;

public class MotgageCalculator {
    public static void main(String[] args) {
        int principal = (int) readNumber("Principal: ", 1000, 1_000_000);
        float annualInterestRate = (float) readNumber("Annual Interest Rate: ", 1, 30);
        byte years = (byte) readNumber("Period(Years): ", 1, 30);

        //Calculate mortgage
        double mortgage = calculateMortgage(principal, annualInterestRate, years);
        System.out.println("\nMORTGAGE");
        System.out.println("--------");
        System.out.println("Monthly Payments: " + NumberFormat.getCurrencyInstance().format(mortgage));

        //displaying payment schedule
        displayPaymentSchedule(principal, annualInterestRate, years);
    }

    public static void displayPaymentSchedule(int principal, float annualInterestRate, byte years) {
        final byte MONTHS_IN_YEAR = 12;
        final byte PERCENT = 100;
        short numberOfPayments = (short) (years * MONTHS_IN_YEAR);
        float monthlyInterestRate = annualInterestRate / (MONTHS_IN_YEAR * PERCENT);
        double balance = 0;

        System.out.println("\nPAYMENT SCHEDULE");
        System.out.println("----------------");
        for (short paymentNo = 1; paymentNo <= numberOfPayments; paymentNo++) {
            balance = (principal * (Math.pow((1 + monthlyInterestRate), numberOfPayments)
                    - Math.pow((1 + monthlyInterestRate), paymentNo))) /
                    (Math.pow((1 + monthlyInterestRate), numberOfPayments) - 1);

            String formattedBalance = NumberFormat.getCurrencyInstance().format(balance);
            System.out.println(formattedBalance);
        }

    }

    public static double readNumber(String prompt, double min, double max) {
        Scanner scanner = new Scanner(System.in);
        double value;
        while (true) {
            System.out.print(prompt);
            value = scanner.nextInt();
            if (value >= min && value < max)
                break;
            System.out.println("Enter a value between " + min + " and " + max);
        }
        return value;
    }

    public static double calculateMortgage(
            int principal,
            float annualInterestRate,
            byte years) {
        final byte MONTHS_IN_YEAR = 12;
        final byte PERCENT = 100;

        float monthlyInterestRate = annualInterestRate / (MONTHS_IN_YEAR * PERCENT);
        short numberOfPayments = (short) (years * MONTHS_IN_YEAR);

        double mortgage = principal *
                ((monthlyInterestRate * Math.pow((monthlyInterestRate + 1), numberOfPayments))
                        / (Math.pow((1 + monthlyInterestRate), numberOfPayments) - 1));

        return mortgage;
    }
}
