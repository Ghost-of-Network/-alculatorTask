package parser;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
    
  //  private static final String OPERATOR_CHARS = "+-*/()";
    private static final TokenType[] OPERATOR_TOKENS = new TokenType[]{
        TokenType.PLUS, TokenType.MINUS,
        TokenType.STAR, TokenType.SLASH,
        TokenType.OPEN_BRACE, TokenType.CLOSE_BRACE
    };
    
    private final String input;
    private final List<Token> tokens;
    private int pos;
    private final int length;
    
    public Lexer(String input) {
        this.input = input;
        length = input.length();
        tokens = new ArrayList<>();
        pos = 0;
    }   
    
    public List<Token> tokenize(){
        String OPERATOR_CHARS = "+-*/()";
        while(pos < length){
            final char currentChar = lookToken(0);
            if(Character.isDigit(currentChar)) tokenizeNumber();
            else if(Character.isLetter(currentChar)) tokenizeWord();
            
            else if(OPERATOR_CHARS.indexOf(currentChar)!=-1){
                
                tokenizeOperator();
            }
            else {
                next();
            }
        }
        return tokens;
    }
    
    private void tokenizeWord(){
        StringBuilder buffer = new StringBuilder();
        char currentChar = lookToken(0);
        while(true){            
            if(!Character.isLetterOrDigit(currentChar)){
                break;
            }
            buffer.append(currentChar);
            currentChar = next();
        }        
        switch (buffer.toString()) {
            case "ln":
                addToken(TokenType.LN);
                break;
            case "sqrt":
                addToken(TokenType.SQRT);
                break;
            default:
                addToken(TokenType.WORD, buffer.toString());
                break;
        }
       // addToken(TokenType.WORD,buffer.toString());
    }
    
    private void tokenizeNumber() {
        StringBuilder currentNumber = new StringBuilder();
        char currentChar = lookToken(0);
        while(true){
            if(currentChar == '.'){
                if(currentNumber.indexOf(".") != -1) throw new RuntimeException("Invalid double");
            }else if(!Character.isDigit(currentChar)){
                break;
            }
            currentNumber.append(currentChar);
            currentChar = next();
        }
        addToken(TokenType.NUMBER,currentNumber.toString());
    }
    
    private void tokenizeOperator(){
        String OPERATOR_CHARS = "+-*/()";
        int operatorPosition = OPERATOR_CHARS.indexOf(lookToken(0));
        addToken(OPERATOR_TOKENS[operatorPosition]);
        next();
    }
    
    private char next(){
        pos++;
        return lookToken(0);
    }
    
    private char lookToken(int relativePosition){
        final int position = pos + relativePosition;
        if (position >= length) return '\0';
        return input.charAt(position);
    }    
    
    private void addToken(TokenType type){
        this.addToken(type,"");
    }
    
    private void addToken(TokenType type, String text){
        tokens.add(new Token(text, type));
    } 

}
