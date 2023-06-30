
package BinaryExpression;

import InterfacesAndGenerics.Expression;
import Variables.Num;

/**
 * Div extends BinaryExpression. It is an expression that get two epxression
 * and  returns the division of them.
 */
public class Div extends BinaryExpression {
    static final String OPERATOR = "/";

    /**
     * Constractor.
     * @param a expression
     * @param b expression
     */
    public Div(Expression a, Expression b) {
        super(a, b, OPERATOR);
    }

    @Override
    public double returnValue() {
        if (this.getFirstExpression().toString().equals(this.getSecondExpression().toString())) {
            return 1;
        }
        if (this.getSecondExpression().returnValue() == 0) {
            return 5 / 0;
        }
        return super.getFirstExpression().returnValue() / super.getSecondExpression().returnValue();
    }

    // (f/g)' = f'g - g'f / g^2
    @Override
    public Expression differentiate(String var) {
        Expression fD = this.getFirstExpression().differentiate(var); // f'
        Expression gD = this.getSecondExpression().differentiate(var); // g'
        Expression gP = new Pow(this.getSecondExpression(), new Num(2)); // g^2
        Expression fDg = new Mult(fD, this.getSecondExpression()); // f'g
        Expression gDf = new Mult(gD, this.getFirstExpression()); // g'f
        Expression numerator = new Minus(fDg, gDf);
        return new Div(numerator, gP);
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Div(this.getFirstExpression().assign(var, expression),
                this.getSecondExpression().assign(var, expression));
    }

    @Override
    public Expression simplify() {
        Expression e = this.clone();
        e = super.simplify();

        try {
            // x/x
            if (((BinaryExpression) e).getFirstExpression().toString()
                    .equals(((BinaryExpression) e).getSecondExpression().toString())) {
                return new Num(1);
            }
        } catch (Exception igException) {

        }

        try {
            // x/1
            if (((BinaryExpression) e).getSecondExpression().returnValue() == 1) {
                return ((BinaryExpression) e).getFirstExpression();
            }

        } catch (Exception ignorException) {

        }
        return e;
    }

    @Override
    public Expression clone() {
        return new Div(getFirstExpression(), getSecondExpression());
    }
}
