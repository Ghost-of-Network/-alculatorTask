package parser.expressions;

public class UnaryExpression implements Expression{

    private final Expression exp1;
    private final char operation;
    
    public UnaryExpression(char operation, Expression exp1) {
        this.operation = operation;
        this.exp1 = exp1;
    }
    
    
    @Override
    public double expValue() {
        
        switch(operation){
            case '-': return -exp1.expValue();
            case '+': 
                default: return +exp1.expValue();
        }
        
    }

    @Override
    public String toString() {
        return String.format("%c %s",operation,exp1);
    }
    
    
    
}
