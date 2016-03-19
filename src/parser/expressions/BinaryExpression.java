package parser.expressions;

import exceptions.DividedByZeroExceptions;
import parser.TokenType;

public class BinaryExpression implements Expression {

    private final Expression exp1, exp2;
    private final TokenType operator;

    public BinaryExpression(Expression exp1, Expression exp2, TokenType operator) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.operator = operator;
    }

    @Override
    public double expValue() {

        switch (operator.toString()) {
            case "MINUS":
                return exp1.expValue() - exp2.expValue();
            case "STAR":
                return exp1.expValue() * exp2.expValue();
            case "SLASH": {
                try {
                    if (exp2.expValue() != 0) {
                        return exp1.expValue() / exp2.expValue();
                    } else {
                        throw new DividedByZeroExceptions("Dividing by zero is not allowed");
                    }
                } catch (DividedByZeroExceptions ex) {
                    throw new RuntimeException();
                }

            }
            case "PLUS":
            default:
                return exp1.expValue() + exp2.expValue();
        }
    }
}
