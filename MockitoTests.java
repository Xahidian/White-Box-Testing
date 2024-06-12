package assigs.assig2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.lang.reflect.Field;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MockitoTests {

    private TaxCalculator2 taxCalculator;
    private Exemption exemptionMock;

    @BeforeEach
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        // Create a mock instance of Exemption
        exemptionMock = Mockito.mock(Exemption.class);
        // Create an instance of TaxCalculator2
        taxCalculator = new TaxCalculator2();
        // Use reflection to set the private 'ex' field in the TaxCalculator2 instance
        Field exemptionField = TaxCalculator2.class.getDeclaredField("ex");
        exemptionField.setAccessible(true);
        exemptionField.set(taxCalculator, exemptionMock);
    }
// When there are 4 children with ages 10, 12, 18, 20, the mock is called twice
    @Test
    public void whenFourChildren_thenExemptionCalledTwice() {
        double[] incomeList = {100000};
        int[] childAgeList = {10, 12, 18, 20};
        when(exemptionMock.getExemptionValue(anyInt())).thenReturn(4000.0, 3000.0);
        double expectedTax = (100000 * 0.2) - (4000 + 3000);

        double tax = taxCalculator.computeTax(incomeList, childAgeList);

        assertEquals(expectedTax, tax, 0.0, "Tax calculated is incorrect");
        verify(exemptionMock, times(2)).getExemptionValue(anyInt());
        verify(exemptionMock).getExemptionValue(1);
        verify(exemptionMock).getExemptionValue(2);
        verifyNoMoreInteractions(exemptionMock);
    }
//When there are 5 children with ages 7, 11, 13, 15, 19 the method is called 4 times


    @Test
    public void whenFiveChildren_thenExemptionCalledFourTimes() {
        double[] incomeList = {100000};
        int[] childAgeList = {7, 11, 13, 15, 19};
        when(exemptionMock.getExemptionValue(anyInt())).thenReturn(4000.0, 4000.0, 4000.0, 3000.0);
        double expectedTax = (100000 * 0.2) - (4000 * 3 + 3000);

        double tax = taxCalculator.computeTax(incomeList, childAgeList);

        assertEquals(expectedTax, tax, 0.0, "Tax calculated is incorrect");
        verify(exemptionMock, times(4)).getExemptionValue(anyInt());
        verify(exemptionMock).getExemptionValue(1);
        verify(exemptionMock).getExemptionValue(2);
        verify(exemptionMock).getExemptionValue(3);
        verify(exemptionMock).getExemptionValue(4);
        verifyNoMoreInteractions(exemptionMock);
    }

    //Create a test that uses three children of ages 19, 22, 24 and check that getExemptionValue() is never called.

    @Test
    public void whenAllChildrenOver18_thenExemptionNeverCalled() {
        // Arrange
        double[] incomeList = {120000};
        int[] childAgeList = {19, 22, 24}; //as per the question
        double expectedTax = 120000 * 0.2; // Expected tax calculation with no exemptions

        // Act
        double tax = taxCalculator.computeTax(incomeList, childAgeList);

        // Assert
        assertEquals(expectedTax, tax, 0.0, "Tax calculated is incorrect");
        verify(exemptionMock, never()).getExemptionValue(anyInt()); // Verify method is never called
    }

}
