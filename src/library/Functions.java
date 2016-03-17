package library;

import parser.expressions.Expression;

public final class Functions {

    public static Expression lnFunction(final Expression exp1) {
        return new Expression() {
            @Override
            public double expValue() {
                return Math.log(exp1.expValue());
            }
        };
    }

    public static Expression sqrtFunction(final Expression exp1) {
        return new Expression() {
            @Override
            public double expValue() {
                return Math.sqrt(exp1.expValue());
            }
        };
    }
    
    public static Expression cosFunction(final Expression exp1) {
        return new Expression() {
            @Override
            public double expValue() {
                return Math.cos(exp1.expValue());
            }
        };
    }
    
    public static Expression sinFunction(final Expression exp1) {
        return new Expression() {
            @Override
            public double expValue() {
                return Math.sin(exp1.expValue());
            }
        };
    }
}
