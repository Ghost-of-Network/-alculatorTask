package library;

import parser.expressions.Expression;

public class LnFunction implements Expression{
    
    private final Expression exp1;
    
    public LnFunction(Expression exp1) {
        this.exp1 = exp1;
    }
    
    
    @Override
    public double expValue() {
        return Math.log(exp1.expValue());
    }
}
