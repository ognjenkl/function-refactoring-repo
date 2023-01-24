package com.ogi.functionrefactoring.refactoring;

import lombok.Data;

import java.time.LocalDate;

public class CardValidator {

    Boolean validateCard(Card card) {
        return isChecksumValid(card.getNumber()) && isMonthYearValid(card.getExpYear(), card.getExpMonth());
    }

    private static boolean isMonthYearValid(int expYear, int expMonth) {
        return LocalDate.of(expYear, expMonth, 1).isAfter(LocalDate.now());
    }

    private boolean isChecksumValid(String cardNumber) {
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
        Card card = new Card();
        card.setNumber("1249190007575069");
        card.setExpMonth(1);
        card.setExpYear(2024);
        customer.setCard(card);

        customer.setValid(cardValidator.validateCard(customer.getCard()));
        System.out.println("Is Alice's card valid?");
        System.out.println(customer.isValid());
        System.out.println(customer);
    }


    @Data
    static class Customer {
        String name;
        String phone;
        Card card;
        boolean valid = false;
    }

    @Data
    private static class Card {
        String number;
        Integer expMonth;
        Integer expYear;
    }
}
