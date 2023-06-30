
package InterfacesAndGenerics;

import java.util.List;
import java.util.Map;

/**
 * Interface of Exprssion. Expression refers to math expression that can be
 * calculated
 * and be assigned with variables.
 */
public interface Expression {

    /**
     * Evaluate the expression. Using the variable values provided
     * in the assignment, and return the result. If the expression
     * contains a variable which is not in the assignment, an exception
     * is thrown.
     *
     * @param assignment map of strings of vars and doubles to assign.
     * @return double of the value of the expression
     * @throws Exception if the expression contains vars or cannot be calculated
     */
    double evaluate(Map<String, Double> assignment) throws Exception;

    /**
     * Evaluate the value of the expression.
     *
     * @return double of the value of the expression
     * @throws Exception if the expression contains vars or cannot be calculated
     */
    double evaluate() throws Exception;

    /**
     * Returns a list of the variables in the expression.
     *
     * @return list of strings of vars
     */
    List<String> getVariables();

    /**
     * Returns a nice string representation of the expression.
     *
     * @return string of the expression
     */
    String toString();

    /**
     * Returns a new expression. In which all occurrences of the variable
     * var are replaced with the provided expression (Does not modify the
     * current expression).
     *
     * @param var string of var who will be replaced
     * @param expression that takes the place of the var
     * @return expression with the given epression inplace of the given var
     */
    Expression assign(String var, Expression expression);

    /**
     * Calculating and returns the value of the expression.
     *
     * @return double of the value
     */
    double returnValue();

    /**
     * Setting the expression by given expression.
     *
     * @param e expression that will be setted
     */
    void setExpression(Expression e);

    /**
     * Returns the expression tree resulting from differentiating the current
     * expression relative to variable `var`.
     *
     * @param var that the expression will be deriviated by it
     * @return the derivative
     */
    Expression differentiate(String var);

    /**
     * Returned a simplified version of the current expression.
     *
     * @return expression
     */
    Expression simplify();

    /**
     * Giving the clone of the expression.
     *
     * @return clone of the epxression.
     */
    Expression clone();

}