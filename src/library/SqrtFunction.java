package library;

import parser.expressions.Expression;

public class SqrtFunction implements Expression{
       
    private final Expression exp1;
    
    public SqrtFunction(Expression exp1) {
        this.exp1 = exp1;
    }
    
    
    @Override
    public double expValue() {
        return Math.sqrt(exp1.expValue());
    }
}
