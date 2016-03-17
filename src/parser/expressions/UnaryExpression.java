package parser.expressions;

import parser.TokenType;

public class UnaryExpression implements Expression {

    private final Expression exp1;
    private final TokenType operation;

    public UnaryExpression(TokenType operation, Expression exp1) {
        this.operation = operation;
        this.exp1 = exp1;
    }

    @Override
    public double expValue() {

        switch (operation.toString()) {
            case "MINUS":
                return -exp1.expValue();
            case "PLUS":
            default:
                return +exp1.expValue();
        }
    }
}
