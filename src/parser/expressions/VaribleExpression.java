package parser.expressions;

public class VaribleExpression implements Expression{

    private final String name;   

    public VaribleExpression(String name) {
        this.name = name;
    }    
    
    @Override
    public double expValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
