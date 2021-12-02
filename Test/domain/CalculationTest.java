package domain;

import junit.framework.TestCase;

public class CalculationTest extends TestCase {

    public void testCalculatePassiveContingent() {
        //Arrange
        Calculation calculation = new Calculation();
        double expected = 500;
        //Act
        double result = calculation.calculateContingent(20,false);
        //Assert
        assertEquals(expected,result);
    }

    public void testCalculateJuniorActiveContingent() {
        Calculation calculation = new Calculation();
        double expected = 1000;
        double result = calculation.calculateContingent(17,true);
        assertEquals(expected,result);
    }

    public void testCalculateSeniorActiveContingent() {
        Calculation calculation = new Calculation();
        double expected = 1600;
        double result = calculation.calculateContingent(20,true);
        assertEquals(expected,result);
    }

    public void testCalculateSeniorActiveContingentWithDiscount() {
        Calculation calculation = new Calculation();
        double expected = 1200;
        double result = calculation.calculateContingent(60,true);
        assertEquals(expected,result);
    }
}