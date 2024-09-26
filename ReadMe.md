#Flexible Calculator

This project a simple, extensible calculator in Java 8 that supports multiple operations: ADD, SUBTRACT, MULTIPLY, and DIVIDE.

##Design
While designing the app, I assumed that only the classes of the same type can be operated on.
The project is using CalculatorFactory to instantiate Calculator of specific data type.
As such, the client of the app would have to cast different data types operated on.
E.g. adding BigInteger and Float would require casting both to BigDecimal by the lib user.

Because of the former design decision, there's some inconsistency in how the calculator behaves.
This is due to different data types behaving differently during division by zero operation.
Integer and Long throw ArithmeticException, whereas Float, Double, BigDecimal and BigInteger return Infinity.
This could be improved by catching the exception and returning MAX values.   

## Extension

Adding new Number subclasses would require modifying CalculatorFactory implementation. 