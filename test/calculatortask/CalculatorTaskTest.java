package calculatortask;

import exceptions.IncorrectSymbolException;
import exceptions.InvalidDoubleException;
import exceptions.WrongNumberCloseBraceException;
import exceptions.WrongNumberOpenBraceException;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatorTaskTest extends Assert {

    public CalculatorTaskTest() {
    }

    @Test(expected = NullPointerException.class)
    public void testcalculateExpression0() throws Exception {
        CalculatorTask.calculateExpression("");
    }

    @Test
    public void testcalculateExpression1() throws Exception {
        int n = (int) CalculatorTask.calculateExpression("2+22-4");
        assertEquals(20, n);
    }

    @Test
    public void testcalculateExpression2() throws Exception {
        int n = (int) CalculatorTask.calculateExpression("(2+1)-((4+10)+(6-15))");
        assertEquals(-2, n);
    }

    @Test
    public void testcalculateExpression3() throws Exception {
        double n = CalculatorTask.calculateExpression("((2.5+2.51)*3.5)");
        assertEquals(17.535, n, 0.0001);
    }

    @Test
    public void testcalculateExpression4() throws Exception {
        double n = CalculatorTask.calculateExpression("2.5+2.51(3.5)");
        assertEquals(3.5, n, 0.0001);
    }

    @Test
    public void testcalculateExpression5() throws Exception {
        double n = CalculatorTask.calculateExpression("LN(18.7)");
        assertEquals(2.928523, n, 0.00001);
    }

    @Test
    public void testcalculateExpression6() throws Exception {
        double n = CalculatorTask.calculateExpression("22/2*(ln(2+2)+(2))-(sqrt(2)-2)");
        assertEquals(19.709354, n, 0.00001);
    }

    @Test
    public void testcalculateExpression7() throws Exception {
        double n = CalculatorTask.calculateExpression("LN(sqrT(cos(SIN(398.41/(11+4.25)))))");
        assertEquals(0.244734, n, 0.00001);
    }

    @Test(expected = WrongNumberCloseBraceException.class)
    public void testcalculateExpression8() throws Exception {
        CalculatorTask.calculateExpression("(2*((2))))");
    }

    @Test(expected = WrongNumberOpenBraceException.class)
    public void testcalculateExpression9() throws Exception {
        CalculatorTask.calculateExpression("((27.5+68.5)(((((-3.5)");
    }

    @Test(expected = WrongNumberCloseBraceException.class)
    public void testcalculateExpression10() throws Exception {
        CalculatorTask.calculateExpression("((()))");
    }

    @Test(expected = RuntimeException.class)
    public void testcalculateExpression11() throws Exception {
        CalculatorTask.calculateExpression("50/0");
    }

    @Test(expected = IncorrectSymbolException.class)
    public void testcalculateExpression12() throws Exception {
        CalculatorTask.calculateExpression("44/abc");
    }

    @Test(expected = IncorrectSymbolException.class)
    public void testcalculateExpression13() throws Exception {
        CalculatorTask.calculateExpression("4+(2-a)");
    }

    @Test(expected = IncorrectSymbolException.class)
    public void testcalculateExpression14() throws Exception {
        CalculatorTask.calculateExpression("4+(2-&)");
    }

    @Test(expected = InvalidDoubleException.class)
    public void testcalculateExpression15() throws Exception {
        CalculatorTask.calculateExpression("4.2.3+2");
    }
}
