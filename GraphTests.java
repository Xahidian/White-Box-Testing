import assigs.assig2.TaxCalculator2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

;

public class GraphTests {

    private TaxCalculator2 taxCalculator;

    @BeforeEach
    public void setUp() {
        taxCalculator = new TaxCalculator2();
    }

    @Test
    public void testPath1() {
        double[] incomeList = {50000, 30000};
        int[] childAgeList = {10, 5};
        double expectedTax = 8000; // Adjusted expected value based on tax logic
        double tax = taxCalculator.computeTax(incomeList, childAgeList);
        assertEquals(expectedTax, tax, 0.0, "Tax calculated for Test Path 1 is incorrect");
    }


    @Test
    public void testPath2() {
        double[] incomeList = {45000};
        int[] childAgeList = {10, 5, 2};
        double expectedTax = 0; // Adjusted expected value based on tax logic
        double tax = taxCalculator.computeTax(incomeList, childAgeList);
        assertEquals(expectedTax, tax, 0.0, "Tax calculated for Test Path 2 is incorrect");
    }



    @AfterEach
    public void tearDown() {
        taxCalculator = null;
    }
}
