package Test;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import Rental.RentalAgreement;
import Rental.Tool;
import Rental.ToolRentalService;
import junit.framework.Assert;

import java.time.LocalDate;

public class ToolRentalServiceTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testRentalAgreement1() {
		ToolRentalService service = new ToolRentalService();
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Discount percent must be between 0 and 100.");
		service.checkout("JAKR", 5, 101, LocalDate.of(2015, 9, 3));
	}

	@Test
	public void testRentalAgreement2() {
		ToolRentalService service = new ToolRentalService();
		RentalAgreement agreement = service.checkout("LADW", 3, 10, LocalDate.of(2020, 7, 2));
		agreement.printAgreement();
	}

	@Test
	public void testRentalAgreement3() {
		ToolRentalService service = new ToolRentalService();
		RentalAgreement agreement = service.checkout("CHNS", 5, 25, LocalDate.of(2015, 7, 2));
		agreement.printAgreement();
	}

	@Test
	public void testRentalAgreement4() {
		ToolRentalService service = new ToolRentalService();
		RentalAgreement agreement = service.checkout("JAKD", 6, 0, LocalDate.of(2015, 9, 3));
		agreement.printAgreement();
	}

	@Test
	public void testRentalAgreement5() {
		ToolRentalService service = new ToolRentalService();
		RentalAgreement agreement = service.checkout("JAKR", 9, 0, LocalDate.of(2015, 7, 2));
		agreement.printAgreement();
	}

	@Test
	public void testRentalAgreement6() {
		ToolRentalService service = new ToolRentalService();
		RentalAgreement agreement = service.checkout("JAKR", 4, 50, LocalDate.of(2020, 7, 2));
		agreement.printAgreement();
	}

	@Test
	public void testisholiday() {
		Tool tool = new Tool("JAKR", "Jackhammer", "Ridgid", 2.99, true, false, false);
		RentalAgreement test = new RentalAgreement(tool, 1, 5, LocalDate.of(2024, 9, 2));
		 boolean holiday = test.isHoliday(LocalDate.of(2024, 9, 3));
		 Assert.assertEquals(false, holiday);
	}
	
	
	@Test
    public void testCalculateChargeDays() {
        Tool tool = new Tool("JAKR", "Jackhammer", "Ridgid", 2.99, true, true, true);
        RentalAgreement test = new RentalAgreement(tool, 7, 5, LocalDate.of(2024, 8, 30)); 
        int chargeDays = test.calculateChargeDays(LocalDate.of(2024, 8, 30), LocalDate.of(2024, 9, 6)); 
        Assert.assertEquals(7, chargeDays);
    }
}