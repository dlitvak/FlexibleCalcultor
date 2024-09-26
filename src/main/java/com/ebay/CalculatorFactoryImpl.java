package com.ebay;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class CalculatorFactoryImpl implements CalculatorFactory {

    @Override
    public <N extends Number> Calculator<N> createCalculator(Class<N> numClass) {
        if (numClass == BigDecimal.class) {
            return new CalculatorImpl<>((n1, n2) -> ((BigDecimal) n1).add((BigDecimal) n2),
                    (n1, n2) -> ((BigDecimal) n1).subtract((BigDecimal) n2),
                    (n1, n2) -> ((BigDecimal) n1).multiply((BigDecimal) n2),
                    (n1, n2) -> ((BigDecimal) n1).divide((BigDecimal) n2, RoundingMode.HALF_UP));
        }
        else if (numClass == BigInteger.class) {
            return new CalculatorImpl<>((n1, n2) -> ((BigInteger) n1).add((BigInteger) n2),
                    (n1, n2) -> ((BigInteger) n1).subtract((BigInteger) n2),
                    (n1, n2) -> ((BigInteger) n1).multiply((BigInteger) n2),
                    (n1, n2) -> ((BigInteger) n1).divide((BigInteger) n2));
        }
        else if (numClass == Short.class || numClass == Integer.class || numClass == Long.class || numClass == Byte.class
                || numClass == AtomicInteger.class || numClass == AtomicLong.class) {
            return new CalculatorImpl<>((n1, n2) -> n1.longValue() + n2.longValue(),
                    (n1, n2) -> n1.longValue() - n2.longValue(),
                    (n1, n2) -> n1.longValue() * n2.longValue(),
                    (n1, n2) -> n1.longValue() / n2.longValue());
        }
        else if (numClass == Float.class || numClass == Double.class) {
            return new CalculatorImpl<>((n1, n2) -> n1.doubleValue() + n2.doubleValue(),
                    (n1, n2) -> n1.doubleValue() - n2.doubleValue(),
                    (n1, n2) -> n1.doubleValue() * n2.doubleValue(),
                    (n1, n2) -> n1.doubleValue() / n2.doubleValue());
        }

        throw new UnsupportedCalculationException(String.format("Class %s not supported", numClass != null ? numClass.getName() : null));
    }

}
