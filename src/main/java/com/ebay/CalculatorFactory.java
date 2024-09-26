package com.ebay;

import lombok.NonNull;

import java.math.BigDecimal;

public interface CalculatorFactory {
    <N extends Number> Calculator<N> createCalculator(@NonNull Class<N> numClass);
}
