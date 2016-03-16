package parser;

import exceptions.IncorrectSymbolException;
import java.util.ArrayList;
import java.util.List;
import parser.expressions.BinaryExpression;
import parser.expressions.Expression;
import library.LnFunction;
import parser.expressions.NumberExpression;
import library.SqrtFunction;
import parser.expressions.UnaryExpression;

public class Parser {

    private static final Token EOF = new Token("", TokenType.EOF);

    private final List<Token> tokens;
    private final int size;
    private int pos;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        size = tokens.size();
    }

    public List<Expression> parse() throws IncorrectSymbolException {
        List<Expression> result = new ArrayList<>();
        while (!match(TokenType.EOF)) {
            result.add(parseFunction());
        }
        return result;
    }

    private Expression parseFunction() throws IncorrectSymbolException {

        Expression resulExpression;

        // while (true) {
        switch (getCurrentTokenType().toString()) {
            case "LN":
                pos++;
                resulExpression = new LnFunction(parseFunction());
                break;
            case "SQRT":
                pos++;
                resulExpression = new SqrtFunction(parseFunction());
                break;
            case "OPEN_BRACE": {
                pos++;
                resulExpression = parseFunction();
                // pos++;
                if (match(TokenType.CLOSE_BRACE));
                {
                    if (!match(TokenType.EOF)) {
                        
                        resulExpression = parseFunction();
                    }
                    //throw new IncorrectSymbolException("Perhaps this symbol excess111: " + get(0).getType().toString());
                }
                return resulExpression;
            }
            default:
                resulExpression = parseExpression();
                break;
        }
        //}
        return resulExpression;
    }

    private Expression parseExpression() throws IncorrectSymbolException {
        return parseAddition();
    }

    private Expression parseAddition() throws IncorrectSymbolException {
        Expression resulExpression = parseMultiplicate();

        while (true) {
            if (match(TokenType.PLUS)) {
                resulExpression = new BinaryExpression(resulExpression, parseMultiplicate(), TokenType.PLUS);
                continue;
            }
            if (match(TokenType.MINUS)) {
                resulExpression = new BinaryExpression(resulExpression, parseMultiplicate(), TokenType.MINUS);
                continue;
            }
            break;
        }
        return resulExpression;
    }

    private Expression parseMultiplicate() throws IncorrectSymbolException {

        Expression resulExpression = parseUnary();
        while (true) {
            if (match(TokenType.STAR)) {
                resulExpression = new BinaryExpression(resulExpression, parseUnary(), TokenType.STAR);
                continue;
            }
            if (match(TokenType.SLASH)) {
                resulExpression = new BinaryExpression(resulExpression, parseUnary(), TokenType.SLASH);
                continue;
            }
            break;
        }
        return resulExpression;
    }

    private Expression parseUnary() throws IncorrectSymbolException {
        if (match(TokenType.MINUS)) {
            return new UnaryExpression(TokenType.MINUS, parseSummand());
        }
        if (match(TokenType.PLUS)) {
            return parseSummand();
        }
        return parseSummand();
    }

    private Expression parseSummand() throws IncorrectSymbolException {
        final Token currentToken = get(0);
        if (match(TokenType.NUMBER)) {
            return new NumberExpression(Double.parseDouble(currentToken.getText()));
        }
        if (match(TokenType.OPEN_BRACE)) {
            //    Expression result = parseExpression();
            Expression result = parseFunction();
            if (match(TokenType.CLOSE_BRACE));
            return result;
        }
        throw new IncorrectSymbolException("Perhaps this symbol excess: " + currentToken.toString());
    }

    private boolean match(TokenType type) {
        final Token currentToken = get(0);
        if (currentToken.getType() != type) {
            return false;
        }
        pos++;
        return true;
    }

    private TokenType getCurrentTokenType() {
        TokenType currentToken = get(0).getType();
        return currentToken;
    }

    private Token get(int relativePosition) {
        int position = pos + relativePosition;
        if (position >= size) {
            return EOF;
        }
        return tokens.get(position);
    }

}
