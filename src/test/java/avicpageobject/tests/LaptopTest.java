package avicpageobject.tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LaptopTest extends BaseTest {

    @Test
    public void howManyFiltersHasLaptop(){
        getHomePage().clickOnLaptopButton();
        assertEquals(getLaptopPage().amountOfLaptopFilters(), 16);
    }
}
