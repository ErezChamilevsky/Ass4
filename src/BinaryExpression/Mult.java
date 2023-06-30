
package BinaryExpression;

import InterfacesAndGenerics.Expression;
import Variables.Num;

/**
 * Mult extends BinaryExpression. It is an expression that get two epxression and
 * returns the multiplication of them.
 */
public class Mult extends BinaryExpression {
    static final String OPERATOR = "*";

    /**
     * Constractor.
     * @param a expression
     * @param b epression
     */
    public Mult(Expression a, Expression b) {
        super(a, b, OPERATOR);
    }

    @Override
    public double returnValue() {
        return super.getFirstExpression().returnValue() * super.getSecondExpression().returnValue();
    }

    @Override
    public Expression differentiate(String var) {
        Expression fg = new Mult(this.getFirstExpression(), this.getSecondExpression().differentiate(var)); // fg'
        Expression gf = new Mult(this.getSecondExpression(), this.getFirstExpression().differentiate(var)); // gf'
        return new Plus(gf, fg);
    }


    @Override
    public Expression assign(String var, Expression expression) {
        return new Mult(this.getFirstExpression().assign(var, expression),
                this.getSecondExpression().assign(var, expression));
    }

    @Override
    public Expression simplify() {
        Expression e = this.clone();
        e = super.simplify();
        try {

            // x*1
            if (((BinaryExpression) e).getFirstExpression().returnValue() == 1) {
                return ((BinaryExpression) e).getSecondExpression();
            }
        } catch (Exception ignorException) {

        }
        try {
            if (((BinaryExpression) e).getSecondExpression().returnValue() == 1) {
                return ((BinaryExpression) e).getFirstExpression();
            }
        } catch (Exception ignorException) {

        }
        try {
            // x*0
            if (((BinaryExpression) e).getFirstExpression().returnValue() == 0) {
                return new Num(0);
            }
        } catch (Exception ignorException) {

        }
        try {
            if (((BinaryExpression) e).getSecondExpression().returnValue() == 0) {
                return new Num(0);
            }
        } catch (Exception ignorException) {

        }
        return e;
    }

    @Override
    public Expression clone() {
        return new Mult(getFirstExpression(), getSecondExpression());
    }

}
