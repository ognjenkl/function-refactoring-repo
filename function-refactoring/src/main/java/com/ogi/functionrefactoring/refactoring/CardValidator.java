package com.ogi.functionrefactoring.refactoring;

import lombok.Data;

import java.time.LocalDate;

public class CardValidator {

    Boolean validateCard(Customer customer) {
        customer.setValid(isChecksumValid(customer) && isMonthYearValid(customer));
        return customer.isValid();

    }

    private static boolean isMonthYearValid(Customer customer) {
        return LocalDate.of(
                customer.getExpYear(), customer.getExpMonth(), 1).isAfter(LocalDate.now());
    }

    private boolean isChecksumValid(Customer customer) {
        String cardNumber = customer.getNumber();
        int checksum = 0;
        for (int i = 0; i < cardNumber.length(); i++) {
            int digit = Integer.parseInt(cardNumber.substring(i, i + 1));
            if (i % 2 == 0) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            checksum += digit;
        }
        return checksum % 10 == 0;
    }

    public static void main(String[] args) {
        CardValidator cardValidator = new CardValidator();
        Customer customer = new Customer();
        customer.setName("Alice");
        customer.setPhone("2341");
        customer.setNumber("1249190007575069");
        customer.setExpMonth(1);
        customer.setExpYear(2024);

        boolean isValid = cardValidator.validateCard(customer);
        System.out.println("Is Alice's card valid?");
        System.out.println(isValid);
        System.out.println(customer);
    }


    @Data
    static class Customer {
        String name;
        String phone;
        String number;
        Integer expMonth;
        Integer expYear;
        boolean valid = false;
    }
}
