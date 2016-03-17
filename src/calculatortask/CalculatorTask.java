package calculatortask;

import java.util.List;
import parser.Lexer;
import parser.Parser;
import parser.Token;
import parser.expressions.Expression;

public class CalculatorTask {

    public static void main(String[] args) throws Exception {
        String inputExpression = "((2.5+2.51)*3.5+PI)";

        calculateExpression(inputExpression);
    }

    public static double calculateExpression(String inputExpression) throws Exception {
        List<Token> tokens = new Lexer(inputExpression).tokenize();

       /* for (Token token : tokens) {
         System.out.println(token.toString());
         } */
        
        Expression expression = new Parser(tokens).parse();
        /* try {
         expressions = new Parser(tokens).parse();
         } catch (IncorrectSymbolException | WrongNumberOpenBraceException | WrongNumberCloseBraceException ex) {
         System.err.println(ex);
         }*/

      //  System.out.println("Expression: " + inputExpression);

        double result = expression.expValue();
        System.out.format(inputExpression + " = %.2f%n%n", result);

        return result;
    }

}
