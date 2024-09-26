package com.ebay;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;

import static com.ebay.Operation.*;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorImplTest {
    private final CalculatorFactory calculatorFactory = new CalculatorFactoryImpl();

    @Test
    public void calculateTest() {
        assertEquals(13L, calculatorFactory.createCalculator(Integer.class).calculate(ADD, 6, 7), "calculate results 6+7 != 13");
        assertEquals(BigInteger.ONE, calculatorFactory.createCalculator(BigInteger.class).calculate(DIVIDE, BigInteger.valueOf(7), BigInteger.valueOf(7)), "calculate results 7/7 != 1");
        assertEquals(BigInteger.ZERO, calculatorFactory.createCalculator(BigInteger.class).calculate(SUBTRACT, BigInteger.valueOf(7), BigInteger.valueOf(7)), "calculate results 7-7 != 0");
        assertEquals(BigInteger.valueOf(14), calculatorFactory.createCalculator(BigInteger.class).calculate(MULTIPLY, BigInteger.valueOf(2), BigInteger.valueOf(7)), "calculate results 2*7 != 14");
        assertEquals(0.5D, calculatorFactory.createCalculator(Float.class).calculate(DIVIDE, 2F, 4F), "calculate results 2.0/4.0 != 0.5");
        assertEquals(Double.POSITIVE_INFINITY, calculatorFactory.createCalculator(Double.class).calculate(DIVIDE, 13D, 0D), "Div by 0 should throw exception");
    }

    @Test
    public void calculateDivByZeroTest() {
        assertThrows(ArithmeticException.class, () -> calculatorFactory.createCalculator(Integer.class).calculate(DIVIDE, 13, 0), "Div by 0 should throw exception");
    }

    @Test
    public void evalThrowsNullPointerTest() {
        Calculator<BigDecimal> calculator = calculatorFactory.createCalculator(BigDecimal.class);
        assertThrows(NullPointerException.class, () -> calculator.eval(null, BigDecimal.ONE), "op of null should throw NPtr");
        assertThrows(NullPointerException.class, () -> calculator.eval(ADD, null), "number of null should throw NPtr");
    }

    @Test
    public void calculateThrowsNullPointerTest() {
        Calculator<BigDecimal> calculator = calculatorFactory.createCalculator(BigDecimal.class);
        assertThrows(NullPointerException.class, () -> calculator.calculate(null, BigDecimal.ONE, BigDecimal.TEN), "op of null should throw NPtr");
        assertThrows(NullPointerException.class, () -> calculator.calculate(ADD, BigDecimal.ONE, null), "number of null should throw NPtr");
        assertThrows(NullPointerException.class, () -> calculator.calculate(ADD, null, BigDecimal.ONE), "number of null should throw NPtr");
    }

    @Test
    public void evalBigIntegerTest() {
        Calculator<BigInteger> calculator = calculatorFactory.createCalculator(BigInteger.class);
        calculator.eval(DIVIDE, BigInteger.valueOf(10))
                .eval(SUBTRACT, BigInteger.valueOf(3))
                .eval(MULTIPLY, BigInteger.valueOf(7));
        assertNotNull(calculator.getResult());
        assertTrue(calculator.getResult() instanceof BigInteger, "eval of BigInteger should return BigInteger");
        assertEquals(BigInteger.valueOf(49), calculator.getResult(), "eval result should be 13");
    }

    @Test
    public void evalBigDecimalTest() {
        Calculator<BigDecimal> calculator = calculatorFactory.createCalculator(BigDecimal.class);
        calculator.eval(ADD, BigDecimal.valueOf(10))
                .eval(SUBTRACT, BigDecimal.valueOf(3))
                .eval(DIVIDE, BigDecimal.valueOf(7));
        assertNotNull(calculator.getResult());
        assertTrue(calculator.getResult() instanceof BigDecimal, "eval of BigDecimal should return BigDecimal");
        assertEquals(BigDecimal.ONE, calculator.getResult(), "eval result should be 13");
    }

    @Test
    public void evalIntegerTest() {
        Calculator<AtomicInteger> calculator = calculatorFactory.createCalculator(AtomicInteger.class);
        calculator.eval(ADD, new AtomicInteger(1))
                .eval(ADD, new AtomicInteger(3))
                .eval(MULTIPLY, new AtomicInteger(4));
        assertNotNull(calculator.getResult());
        assertTrue(calculator.getResult() instanceof Long, "eval of Integer should return Long");
        assertEquals(16L, calculator.getResult(), "eval result should be 13");
    }

    @Test
    public void evalFloatTest() {
        Calculator<Float> calculator = calculatorFactory.createCalculator(Float.class);
        calculator.eval(ADD, 1.0F)
                .eval(ADD, 3.5F)
                .eval(MULTIPLY, 2.5F);
        assertNotNull(calculator.getResult());
        assertTrue(calculator.getResult() instanceof Double, "eval of Float should return Double");
        assertEquals(11.25D, calculator.getResult(), "eval result should be 13");
    }

    @Test
    public void evalInfinityTest() {
        Calculator<Float> calculator = calculatorFactory.createCalculator(Float.class);
        calculator.eval(ADD, 2.0F)
                .eval(SUBTRACT, 1.0F)
                .eval(DIVIDE, 0F);
        assertNotNull(calculator.getResult());
        assertEquals(Double.POSITIVE_INFINITY, calculator.getResult(), "eval result should be Infinity");
    }
}
