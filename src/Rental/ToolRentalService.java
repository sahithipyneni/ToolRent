package Rental;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ToolRentalService {
    private Map<String, Tool> tools = new HashMap<>();

    public ToolRentalService() {
        tools.put("CHNS", new Tool("CHNS", "Chainsaw", "Stihl", 1.49, true, false, true));
        tools.put("LADW", new Tool("LADW", "Ladder", "Werner", 1.99, true, true, false));
        tools.put("JAKD", new Tool("JAKD", "Jackhammer", "DeWalt", 2.99, true, false, false));
        tools.put("JAKR", new Tool("JAKR", "Jackhammer", "Ridgid", 2.99, true, false, false));
    }

    public RentalAgreement checkout(String toolCode, int rentalDays, int discountPercent, LocalDate checkoutDate) {
        if (!tools.containsKey(toolCode)) {
            throw new IllegalArgumentException("Invalid tool code.");
        }
        if (rentalDays < 1) {
            throw new IllegalArgumentException("Rental day count must be 1 or greater.");
        }
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Discount percent must be between 0 and 100.");
        }

        Tool tool = tools.get(toolCode);
        return new RentalAgreement(tool, rentalDays, discountPercent, checkoutDate);
    }
}
