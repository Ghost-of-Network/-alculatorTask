package parser.expressions;

public class BinaryExpression implements Expression{

    private final Expression exp1,exp2;
    private final char operator;

    public BinaryExpression(Expression exp1, Expression exp2, char operator) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.operator = operator;
    }
    
    @Override
    public double expValue() {
            
        switch(operator){
            case '-': return exp1.expValue() - exp2.expValue();
            case '*': return exp1.expValue() * exp2.expValue();
            case '/': return exp1.expValue() / exp2.expValue();
            case '+':
                default: return exp1.expValue() + exp2.expValue();
        }
    }

    @Override
    public String toString() {
        return String.format("(%s %c %s)", exp1,operator,exp2);
    }
}
