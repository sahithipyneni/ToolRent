package Rental;

import java.time.LocalDate;

public class RentalAgreementTest {
    private Tool tool;
    private int rentalDays;
    private int discountPercent;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private int chargeDays;
    private double dailyRentalCharge;
    private double preDiscountCharge;
    private double discountAmount;
    private double finalCharge;

    public RentalAgreementTest(Tool tool, int rentalDays, int discountPercent, LocalDate checkoutDate) {
        this.tool = tool;
        this.rentalDays = rentalDays;
        this.discountPercent = discountPercent;
        this.checkoutDate = checkoutDate;
        this.dueDate = checkoutDate.plusDays(rentalDays);
        this.chargeDays = calculateChargeDays();
        this.dailyRentalCharge = tool.getDailyCharge();
        this.preDiscountCharge = dailyRentalCharge * chargeDays;
        this.discountAmount = preDiscountCharge * (discountPercent / 100.0);
        this.finalCharge = preDiscountCharge - discountAmount;
    }

    private int calculateChargeDays() {
        int chargeDays = 0;
        for (int i = 0; i < rentalDays; i++) {
            LocalDate date = checkoutDate.plusDays(i);
            if (tool.isWeekdayCharge() && date.getDayOfWeek().getValue() <= 5) {
                chargeDays++;
            } else if (tool.isWeekendCharge() && date.getDayOfWeek().getValue() > 5) {
                chargeDays++;
            } else if (tool.isHolidayCharge() && isHoliday(date)) {
                chargeDays++;
            }
        }
        return chargeDays;
    }

    private boolean isHoliday(LocalDate date) {
    	if (date.getMonthValue() == 7 && date.getDayOfMonth() == 4) {
            return true;
        } else if (date.getMonthValue() == 9 && date.getDayOfMonth() == 1 && date.getDayOfWeek().getValue() == 1) {
            return true;
        } else {
            return false;
        }
    }

    public Tool getTool() {
        return tool;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public int getChargeDays() {
        return chargeDays;
    }

    public double getDailyRentalCharge() {
        return dailyRentalCharge;
    }

    public double getPreDiscountCharge() {
        return preDiscountCharge;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public double getFinalCharge() {
        return finalCharge;
    }

    public void printRentalAgreement() {
        System.out.println("Rental Agreement:");
        System.out.println("Tool: " + tool.getType());
        System.out.println("Rental Days: " + rentalDays);
        System.out.println("Discount Percent: " + discountPercent);
        System.out.println("Checkout Date: " + checkoutDate);
        System.out.println("Due Date: " + dueDate);
        System.out.println("Charge Days: " + chargeDays);
        System.out.println("Daily Rental Charge: " + dailyRentalCharge);
        System.out.println("Pre-Discount Charge: " + preDiscountCharge);
        System.out.println("Discount Amount: " + discountAmount);
        System.out.println("Final Charge: " + finalCharge);
    }
}