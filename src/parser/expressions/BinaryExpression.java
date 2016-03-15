package parser.expressions;
 import parser.TokenType;

public class BinaryExpression implements Expression{

    private final Expression exp1,exp2;
    private final TokenType operator;

    public BinaryExpression(Expression exp1, Expression exp2, TokenType operator) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.operator = operator;
    }
    
    @Override
    public double expValue() {
            
        switch(operator.toString()){
            case "MINUS": return exp1.expValue() - exp2.expValue();
            case "STAR": return exp1.expValue() * exp2.expValue();
            case "SLASH": return exp1.expValue() / exp2.expValue();
            case "PLUS":
                default: return exp1.expValue() + exp2.expValue();
        }
    }

 /*   @Override
    public String toString() {
       // return String.format("(%s %c %s)", exp1,operator,exp2);
        return String.format("(%s %s)", exp1,exp2);
    }*/
}
