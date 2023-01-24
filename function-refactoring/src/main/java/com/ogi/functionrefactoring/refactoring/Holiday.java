package com.ogi.functionrefactoring.refactoring;

import lombok.Data;

public class Holiday {

    static final int FIXED_VACATION_DAYS_PAYOUT = 5;

    public static void main(String[] args) {
        Employee employee = new Employee();
        employee.setName("John Doe");
        employee.setRole(Role.ENGINEER);
        employee.setVacationDays(25);

        employee.takeHoliday(1, Boolean.parseBoolean(args[0]));
    }

    @Data
    static class Employee {
        String name;
        Role role;
        int vacationDays;

        public void takeHoliday(int numberOfDays, boolean payout) {
            if (payout) {
                if (vacationDays < FIXED_VACATION_DAYS_PAYOUT) {
                    throw new IllegalArgumentException(
                            String.format("You don't have enough holidays left over for a payout. "
                                    + "Remaining holidays: %d.", vacationDays));
                }
                vacationDays -= FIXED_VACATION_DAYS_PAYOUT;
                System.out.printf("Paying out a holiday. Holidays left: %d%n", vacationDays);
            } else {
                if (vacationDays < numberOfDays) {
                    throw new IllegalArgumentException("You don't have any holidays left. "
                            + "Now back to work, you!");
                }
                vacationDays -= numberOfDays;
                System.out.println("Have fun on your holiday. Don't forget to check your emails!");
            }
        }
    }

    enum Role {
        PRESIDENT, VICEPRESIDENT, MANAGER, LEAD, ENGINEER, INTERN
    }
}
