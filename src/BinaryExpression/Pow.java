package BinaryExpression;

import InterfacesAndGenerics.Expression;
import UnaryExpression.Ln;

/**
 * Pow extends BinaryExpression. It is an expression that get two epxression
 * and returns the pow of them.
 */
public class Pow extends BinaryExpression {
    static final String OPERATOR = "^";

    /**
     * Constractor.
     * @param a expression
     * @param b expression
     */
    public Pow(Expression a, Expression b) {
        super(a, b, OPERATOR);

    }

    @Override
    public double returnValue() {
        return Math.pow(super.getFirstExpression().returnValue(), super.getSecondExpression().returnValue());
    }



    @Override
    public Expression assign(String var, Expression expression) {
        return new Pow(this.getFirstExpression().assign(var, expression),
                this.getSecondExpression().assign(var, expression));

    }

    @Override
    public String toString() {
        return "(" + this.getFirstExpression().toString() + this.getOperator()
                + this.getSecondExpression().toString() + ")";
    }

    // the math is f^g(f'*g/f +g'*ln(f))
    @Override
    public Expression differentiate(String var) {
        Expression fPg = new Pow(getFirstExpression(), getSecondExpression());
        Expression fD = this.getFirstExpression().differentiate(var);
        Expression gD = this.getSecondExpression().differentiate(var);
        Expression fDg = new Div(this.getSecondExpression(), this.getFirstExpression());
        return new Mult(fPg, new Plus(new Mult(fD, fDg), new Mult(gD, new Ln(this.getFirstExpression()))));
    }

    @Override
    public Expression clone() {
        return new Pow(getFirstExpression(), getSecondExpression());
    }
}
