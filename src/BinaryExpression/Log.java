
package BinaryExpression;

import InterfacesAndGenerics.Expression;
import UnaryExpression.Ln;
import Variables.Num;

/**
 * Log extends BinaryExpression. It is an expression that get two epxression
 * and returns the Log of them.
 */
public class Log extends BinaryExpression {
    static final String OPERATOR = "log";

    /**
     * Constractor.
     * @param a expression
     * @param b expression
     */
    public Log(Expression a, Expression b) {
        super(a, b, OPERATOR);

    }

    @Override
    public double returnValue() {
        if (super.getFirstExpression().toString().equals(super.getSecondExpression().toString())) {
            return 1;
        }

        double base = super.getFirstExpression().returnValue();
        double log = super.getSecondExpression().returnValue();

        if (base <= 0 || log <= 0) {
            return 5 / 0;
        }

        if (base == 1) {
            return 5 / 0;
        }

        return Math.log10(log) / Math.log10(base);
    }

    @Override
    public String toString() {
        return "log(" + this.getFirstExpression().toString() + ", " + this.getSecondExpression().toString() + ")";
    }


    @Override
    public Expression assign(String var, Expression expression) {
        return new Log(this.getFirstExpression().assign(var, expression),
                this.getSecondExpression().assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        return new Div(new Ln(this.getSecondExpression()), new Ln(this.getFirstExpression())).differentiate(var);
    }

    @Override
    public Expression simplify() {
        Expression e = this.clone();
        e = super.simplify();
        try {
            if (((BinaryExpression) e).getFirstExpression().toString()
                    .equals(((BinaryExpression) e).getSecondExpression().toString())) {
                return new Num(1);
            }
            if (((BinaryExpression) e).getFirstExpression().toString().equals("e")) {
                return new Num(1);
            }
        } catch (Exception igException) {
        }
       return e;
    }

    @Override
    public Expression clone() {
        return new Log(getFirstExpression(), getSecondExpression());
    }
}
