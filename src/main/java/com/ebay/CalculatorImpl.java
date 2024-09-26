package com.ebay;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class CalculatorImpl<N extends Number> implements Calculator<N> {
    private Number value;
    private final Map<Operation, BiFunction<Number,Number,Number>> funcMap = new HashMap<>();

    protected CalculatorImpl(BiFunction<Number,Number,Number> addFunc, BiFunction<Number,Number,Number> subFunc,
                                             BiFunction<Number,Number,Number> multFunc, BiFunction<Number,Number,Number> divFunc) {
        this.funcMap.put(Operation.ADD, addFunc);
        this.funcMap.put(Operation.SUBTRACT, subFunc);
        this.funcMap.put(Operation.MULTIPLY, multFunc);
        this.funcMap.put(Operation.DIVIDE, divFunc);
    }

    @Override
    public Calculator<N> eval(Operation op, N num) {
        if (op == null) {
            throw new NullPointerException("eval operator can't be null");
        }

        if (num == null) {
            throw new NullPointerException("eval number can't be null");
        }

        if (this.value == null) {
            this.value = num;
        }
        else {
            this.value = this.funcMap.get(op).apply(this.value, num);
        }
        return this;
    }

    @Override
    public Number calculate(Operation op, N num1, N num2) {
        if (op == null) {
            throw new NullPointerException("calculate operator can't be null");
        }

        if (num1 == null || num2 == null) {
            throw new NullPointerException("calculate numbers can't be null");
        }

        return this.funcMap.get(op).apply(num1, num2);
    }

    @Override
    public Number getResult() {
        return this.value;
    }
}
