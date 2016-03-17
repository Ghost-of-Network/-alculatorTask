package parser;

import exceptions.IncorrectSymbolException;
import exceptions.WrongNumberCloseBraceException;
import exceptions.WrongNumberOpenBraceException;
import java.util.List;
import parser.expressions.BinaryExpression;
import parser.expressions.Expression;
import library.Functions;
import parser.expressions.NumberExpression;
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

    public Expression parse() throws IncorrectSymbolException, WrongNumberOpenBraceException, WrongNumberCloseBraceException {
        Expression result = null;
        while (!match(TokenType.EOF)) {
            result = parseFunction();
        }
        return result;
    }

    private Expression parseFunction() throws IncorrectSymbolException, WrongNumberOpenBraceException, WrongNumberCloseBraceException {

        Expression resulExpression;

        switch (getCurrentTokenType().toString()) {
            case "LN":
                pos++;
                resulExpression = Functions.lnFunction(parseFunction());
                break;
            case "SQRT":
                pos++;
                resulExpression = Functions.sqrtFunction(parseFunction());
                break;
            case "COS":
                pos++;
                resulExpression = Functions.cosFunction(parseFunction());
                break;
            case "SIN":
                pos++;
                resulExpression = Functions.sinFunction(parseFunction());
                break;
            default:
                resulExpression = parseExpression();
                break;
        }
        return resulExpression;
    }

    private Expression parseExpression() throws IncorrectSymbolException, WrongNumberOpenBraceException, WrongNumberCloseBraceException {
        return parseAddition();
    }

    private Expression parseAddition() throws IncorrectSymbolException, WrongNumberOpenBraceException, WrongNumberCloseBraceException {
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

    private Expression parseMultiplicate() throws IncorrectSymbolException, WrongNumberOpenBraceException, WrongNumberCloseBraceException {

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

    private Expression parseUnary() throws IncorrectSymbolException, WrongNumberOpenBraceException, WrongNumberCloseBraceException {
        if (match(TokenType.MINUS)) {
            return new UnaryExpression(TokenType.MINUS, parseSummand());
        }
        if (match(TokenType.PLUS)) {
            return parseSummand();
        }
        return parseSummand();
    }

    private Expression parseSummand() throws IncorrectSymbolException, WrongNumberOpenBraceException, WrongNumberCloseBraceException {
        final Token currentToken = get(0);
        if (match(TokenType.NUMBER)) {
            return new NumberExpression(Double.parseDouble(currentToken.getText()));
        }
        if (match(TokenType.OPEN_BRACE)) {
            Expression result = parseFunction();
            if (!match(TokenType.CLOSE_BRACE)) {
                throw new WrongNumberOpenBraceException("The number of opening brace more than closing");
            }
            return result;
        }
        if ("CLOSE_BRACE".equals(currentToken.getType().toString())) {
            throw new WrongNumberCloseBraceException("The number of closing brace more than opening");
        }
        throw new IncorrectSymbolException("Perhaps this symbol excess: " + currentToken.toString());
    }

    private boolean match(TokenType type) {
        final Token currentToken = get(0);
        if (type != currentToken.getType()) {
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
