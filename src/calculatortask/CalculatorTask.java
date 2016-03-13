package calculatortask;

import exceptions.IncorrectSymbolException;
import java.util.List;
import parser.Lexer;
import parser.Parser;
import parser.Token;
import parser.expressions.Expression;

public class CalculatorTask {

    public static void main(String[] args) throws IncorrectSymbolException{
       
        String statement = "((2.5+2.545)*3.5)";
     //   String statement = "((2.5+2.5)*(((((-3.5)";
        
        List<Token> tokens = new Lexer(statement).tokenize();
        
     /*   for (Token token : tokens) {
            System.out.println(token.toString());
        } */
        
        List<Expression> expressions = new Parser(tokens).parse();
        for (Expression expression : expressions) {
            System.out.println(expression + " = " +expression.expValue());
        }       
    }
    
}