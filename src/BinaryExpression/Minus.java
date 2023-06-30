
package BinaryExpression;

import InterfacesAndGenerics.Expression;
import UnaryExpression.Neg;
import Variables.Num;

/**
 * Minus extends BinaryExpression. It is an expression that get two epxression
 * and returns the difference of them.
 */
public class Minus extends BinaryExpression {
    static final String OPERATOR = "-";

    /**
     * Constractor.
     * @param a expression
     * @param b expression
     */
    public Minus(Expression a, Expression b) {
        super(a, b, OPERATOR);
    }

    @Override
    public double returnValue() {
        if (this.getFirstExpression().toString().equals(this.getSecondExpression().toString())) {
            return 0;
        }
        return super.getFirstExpression().returnValue() - super.getSecondExpression().returnValue();
    }

    @Override
    public Expression differentiate(String var) {
        return new Minus(this.getFirstExpression().differentiate(var), this.getSecondExpression().differentiate(var));
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Minus(this.getFirstExpression().assign(var, expression),
                this.getSecondExpression().assign(var, expression));
    }

    @Override
    public Expression simplify() {
        Expression e = this.clone();
        e = super.simplify();
        try {
            // x-x
            if (((BinaryExpression) e).getFirstExpression().toString()
                    .equals(((BinaryExpression) e).getSecondExpression().toString())) {
                return new Num(0);
            }
        } catch (Exception ignorException) {

        }
        try {
            // x-0
            if (((BinaryExpression) e).getSecondExpression().returnValue() == 0) {
                return ((BinaryExpression) e).getFirstExpression();
            }

        } catch (Exception ignorException) {

        }
        try {
            // 0-x
            if (((BinaryExpression) e).getFirstExpression().returnValue() == 0) {
                return new Neg(((BinaryExpression) e).getSecondExpression());
            }

        } catch (Exception ignorException) {

        }

        return e;
    }

    @Override
    public Expression clone() {
        return new Minus(getFirstExpression(), getSecondExpression());
    }
}
