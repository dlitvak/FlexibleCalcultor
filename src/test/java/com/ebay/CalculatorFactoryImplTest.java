package com.ebay;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CalculatorFactoryImplTest {
    private final CalculatorFactory calculatorFactory = new CalculatorFactoryImpl();

    @Test
    public void createCalculatorNullTest() {
        assertThrows(UnsupportedCalculationException.class, () -> calculatorFactory.createCalculator(null), "Factory class should not be null");
    }

    @Test
    public void createCalculatorTest() {
        assertDoesNotThrow(() -> calculatorFactory.createCalculator(AtomicInteger.class));
    }
}
