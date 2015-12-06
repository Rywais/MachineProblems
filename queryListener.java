// Generated from query.g4 by ANTLR 4.5.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link queryParser}.
 */
public interface queryListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link queryParser#orExpr}.
	 * @param ctx the parse tree
	 */
	void enterOrExpr(queryParser.OrExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#orExpr}.
	 * @param ctx the parse tree
	 */
	void exitOrExpr(queryParser.OrExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#andExpr}.
	 * @param ctx the parse tree
	 */
	void enterAndExpr(queryParser.AndExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#andExpr}.
	 * @param ctx the parse tree
	 */
	void exitAndExpr(queryParser.AndExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(queryParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(queryParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#string}.
	 * @param ctx the parse tree
	 */
	void enterString(queryParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#string}.
	 * @param ctx the parse tree
	 */
	void exitString(queryParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#in}.
	 * @param ctx the parse tree
	 */
	void enterIn(queryParser.InContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#in}.
	 * @param ctx the parse tree
	 */
	void exitIn(queryParser.InContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#category}.
	 * @param ctx the parse tree
	 */
	void enterCategory(queryParser.CategoryContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#category}.
	 * @param ctx the parse tree
	 */
	void exitCategory(queryParser.CategoryContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(queryParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(queryParser.NameContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#rating}.
	 * @param ctx the parse tree
	 */
	void enterRating(queryParser.RatingContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#rating}.
	 * @param ctx the parse tree
	 */
	void exitRating(queryParser.RatingContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#price}.
	 * @param ctx the parse tree
	 */
	void enterPrice(queryParser.PriceContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#price}.
	 * @param ctx the parse tree
	 */
	void exitPrice(queryParser.PriceContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#range}.
	 * @param ctx the parse tree
	 */
	void enterRange(queryParser.RangeContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#range}.
	 * @param ctx the parse tree
	 */
	void exitRange(queryParser.RangeContext ctx);
}