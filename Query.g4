grammar Query;

// This puts "package formula;" at the top of the output Java files.
@header {
package ca.ece.ubc.cpen221.mp5;
}

// This adds code to the generated lexer and parser. Do not change these lines.
@members {
    // This method makes the lexer or parser stop running if it encounters
    // invalid input and throw a RuntimeException.
    public void reportErrorsAsExceptions() {
        //removeErrorListeners();
        
        addErrorListener(new ExceptionThrowingErrorListener());
    }
    
    private static class ExceptionThrowingErrorListener extends BaseErrorListener {
        @Override
        public void syntaxError(Recognizer<?, ?> recognizer,
                Object offendingSymbol, int line, int charPositionInLine,
                String msg, RecognitionException e) {
            throw new RuntimeException(msg);
        }
    }
}

OR : '||' ;
AND : '&&' ;
//TEXT : ~[<>]+ ;
TEXT : QUOTE [A-Za-z ]+ QUOTE;
IN : 'in' ;
NAME : 'name' ;
RATING : 'rating' ;
CATEGORY : 'category' ;
PRICE : 'price' ;
RANGE : [1-5] ;
DOTS : '..' ;
LPAREN : '(' ;
RPAREN : ')' ;
WHITESPACE : [ \t\r\n]+ -> skip ;
fragment QUOTE : '"' ;

root : orExpr* EOF;
orExpr : andExpr (OR andExpr)* ;
andExpr : atom (AND atom)* ;
atom : in | category | rating | price | name | LPAREN orExpr RPAREN ;
in : IN LPAREN TEXT RPAREN ;
category : CATEGORY LPAREN TEXT RPAREN ;
rating : RATING LPAREN RANGE DOTS RANGE RPAREN ;
price : PRICE LPAREN RANGE DOTS RANGE RPAREN ;
name : NAME LPAREN TEXT RPAREN ; 
//string : QUOTE TEXT QUOTE ;

