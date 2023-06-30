
package BinaryExpression;

import InterfacesAndGenerics.Expression;

/**
 * Plus extends BinaryExpression. It is an expression that get two epxression
 * and returns the sum of them.
 */
public class Plus extends BinaryExpression {
    static final String OPERATOR = "+";

    /**
     * Constractor.
     * @param a expression
     * @param b expression
     */
    public Plus(Expression a, Expression b) {
        super(a, b, OPERATOR);
    }

    @Override
    public double returnValue() {
        return super.getFirstExpression().returnValue() + super.getSecondExpression().returnValue();
    }

    @Override
    public Expression differentiate(String var) {
        return new Plus(this.getFirstExpression().differentiate(var), this.getSecondExpression().differentiate(var));
    }


    @Override
    public Expression assign(String var, Expression expression) {
        return new Plus(this.getFirstExpression().assign(var, expression),
                this.getSecondExpression().assign(var, expression));
    }

    @Override
    public Expression simplify() {
        Expression e = this.clone();
        e = super.simplify();
        try {

            // x+0
            if (((BinaryExpression) e).getSecondExpression().returnValue() == 0) {
                return ((BinaryExpression) e).getFirstExpression();
            }
        } catch (Exception ignorException) {

        }
        try {
            // 0+x
            if (((BinaryExpression) e).getFirstExpression().returnValue() == 0) {
                return ((BinaryExpression) e).getSecondExpression();
            }
        } catch (Exception ignorException) {

        }
        return e;
    }

    @Override
    public Expression clone() {
        return new Plus(getFirstExpression(), getSecondExpression());
    }
}
