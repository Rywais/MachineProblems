grammar query;

OR : '||' ;
AND : '&&' ;
TEXT : ~[<>]+ ;
LPAREN : '(' ;
RPAREN : ')' ;
IN : 'in' ;
NAME : 'name' ;
RATING : 'rating' ;
CATEGORY : 'category' ;
PRICE : 'price' ;
RANGE : [1-5] ;
DOTS : '..' ;

root : atom EOF;
orExpr : andExpr (OR andExpr)* ;
andExpr : atom (AND atom)* ;
atom : in | category | rating | price | name | LPAREN orExpr RPAREN ;
string : TEXT ;
in : IN LPAREN string RPAREN ;
category : CATEGORY LPAREN string RPAREN ;
name : NAME LPAREN string RPAREN ; 
rating : RATING LPAREN range RPAREN ;
price : PRICE LPAREN range RPAREN ;
range : RANGE DOTS RANGE ;