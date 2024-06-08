package Rental;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class ToolTest {

    @Test
    public void testToolCreation() {
        Tool tool = new Tool("CHNS", "Chainsaw", "Stihl", 1.49, true, false, true);
        assertEquals("CHNS", tool.getCode());
        assertEquals("Chainsaw", tool.getType());
        assertEquals("Stihl", tool.getBrand());
        assertEquals(1.49, tool.getDailyCharge(), 0.0); // note the delta parameter
        assertTrue(tool.isWeekdayCharge());
        assertFalse(tool.isWeekendCharge());
        assertTrue(tool.isHolidayCharge());
    }
}