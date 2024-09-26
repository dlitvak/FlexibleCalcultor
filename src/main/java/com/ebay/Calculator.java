package com.ebay;

import lombok.NonNull;

public interface Calculator<N extends Number> {
    /**
     * Chaining method to perform a stream of calculations.
     * @param op - operation to be performed if Calculator has a value in register.
     *           Otherwise, op is disregarded and register is set to num.
     * @param num - number to be operated on.
     * @return chained calculator obj
     */
    Calculator<N> eval(@NonNull Operation op, @NonNull N num);

    Number calculate(@NonNull Operation op, @NonNull N num1, @NonNull N num2);

    Number getResult();
}
