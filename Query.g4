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
in : IN LPAREN string RPAREN ;
category : CATEGORY LPAREN string RPAREN ;
rating : RATING LPAREN range dotrating range RPAREN ;
price : PRICE LPAREN range dotprice range RPAREN ;
name : NAME LPAREN string RPAREN ; 
string : TEXT ;
dotrating : DOTS ;
dotprice : DOTS ;
range : RANGE ;

