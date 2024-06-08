package Rental;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RentalAgreement {
    private Tool tool;
    private int rentalDays;
    private int discountPercent;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private int chargeDays;
    private double preDiscountCharge;
    private double discountAmount;
    private double finalCharge;

    public RentalAgreement(Tool tool, int rentalDays, int discountPercent, LocalDate checkoutDate) {
        this.tool = tool;
        this.rentalDays = rentalDays;
        this.discountPercent = discountPercent;
        this.checkoutDate = checkoutDate;
        calculate();
    }
    

    private void calculate() {
        dueDate = checkoutDate.plusDays(rentalDays);
        chargeDays = calculateChargeDays(checkoutDate, dueDate);
        preDiscountCharge = chargeDays * tool.getDailyCharge();
        discountAmount = preDiscountCharge * discountPercent / 100.0;
        finalCharge = preDiscountCharge - discountAmount;
    }

    public int calculateChargeDays(LocalDate start, LocalDate end) {
        int days = 0;
        LocalDate current = start;
        while (current.isBefore(end)) {
            current = current.plusDays(1);
            boolean isWeekend = current.getDayOfWeek() == java.time.DayOfWeek.SATURDAY || current.getDayOfWeek() == java.time.DayOfWeek.SUNDAY;
            boolean isHoliday = isHoliday(current);
            if ((tool.isWeekdayCharge() && !isWeekend && !isHoliday) || (tool.isWeekendCharge() && isWeekend) || (tool.isHolidayCharge() && isHoliday)) {
                days++;
            }
        }
        return days;
    }

    public boolean isHoliday(LocalDate date) {
        // Independence Day
        if ((date.getMonthValue() == 7 && date.getDayOfMonth() == 4) || 
            (date.getMonthValue() == 7 && date.getDayOfWeek() == java.time.DayOfWeek.FRIDAY && date.minusDays(1).getDayOfMonth() == 4) || 
            (date.getMonthValue() == 7 && date.getDayOfWeek() == java.time.DayOfWeek.MONDAY && date.minusDays(2).getDayOfMonth() == 4)) {
            return true;
        }
        // Labor Day
        if (date.getMonthValue() == 9 && date.getDayOfWeek() == java.time.DayOfWeek.MONDAY && date.getDayOfMonth() <= 7) {
            return true;
        }
        return false;
    }

    public void printAgreement() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        DecimalFormat currencyFormatter = new DecimalFormat("$#,##0.00");
        DecimalFormat percentFormatter = new DecimalFormat("0%");

        System.out.println("Tool code: " + tool.getCode());
        System.out.println("Tool type: " + tool.getType());
        System.out.println("Tool brand: " + tool.getBrand());
        System.out.println("Rental days: " + rentalDays);
        System.out.println("Check out date: " + checkoutDate.format(dateFormatter));
        System.out.println("Due date: " + dueDate.format(dateFormatter));
        System.out.println("Daily rental charge: " + currencyFormatter.format(tool.getDailyCharge()));
        System.out.println("Charge days: " + chargeDays);
        System.out.println("Pre-discount charge: " + currencyFormatter.format(preDiscountCharge));
        System.out.println("Discount percent: " + percentFormatter.format(discountPercent / 100.0));
        System.out.println("Discount amount: " + currencyFormatter.format(discountAmount));
        System.out.println("Final charge: " + currencyFormatter.format(finalCharge));
    }
}
