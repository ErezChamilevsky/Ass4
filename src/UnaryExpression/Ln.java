
package UnaryExpression;

import BinaryExpression.Div;
import InterfacesAndGenerics.Expression;
import Variables.Num;
import Variables.Var;

/**
 * Ln extends unaryExpression. It is an expression that get epxression
 * and returns the Ln of it.
 */
public class Ln extends UnaryExpression {
    static final String OPERATOR = "ln";
    /**
     * Constractor.
     * @param a expression
     */
    public Ln(Expression a) {
        super(a, OPERATOR);
    }

    @Override
    public double returnValue() {
        return Math.log(super.getExpression().returnValue());
    }

    @Override
    public String toString() {
        return "log(" + new Var("e") + ", " + this.getExpression() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Ln(this.getExpression().assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        return new Div(this.getExpression().differentiate(var), this.getExpression());
    }

    @Override
    public Expression simplify() {
        Expression e = this.clone();
        e = super.simplify();
        try {
            if (((UnaryExpression) e).getExpression().toString().equals("e")) {
                return new Num(1);
            }
        } catch (Exception ignoreException) {

        }
        return e;
    }

    @Override
    public Expression clone() {
        return new Ln(this.getExpression());
    }
}
