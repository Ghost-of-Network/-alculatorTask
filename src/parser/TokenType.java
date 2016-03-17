package parser;

public enum TokenType {

    // Base tokens
    NUMBER,
    WORD,
    
    // Functions
    LN,
    SQRT,
    COS,
    SIN,
    
    // Base operations
    STAR,
    SLASH,
    PLUS,
    MINUS,
    
    // Brace
    OPEN_BRACE,
    CLOSE_BRACE,
    
    // End of file
    EOF
}
