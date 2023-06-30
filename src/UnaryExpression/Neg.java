package UnaryExpression;

import InterfacesAndGenerics.Expression;

/**
 * Neg extends unaryExpression. It is an expression that get epxression
 * and returns the negative of it.
 */
public class Neg extends UnaryExpression {
    static final String OPERATOR = "-";

    /**
     * Constractor.
     * @param a expression
     */
    public Neg(Expression a) {
        super(a, OPERATOR);
    }


    @Override
    public Expression assign(String var, Expression expression) {
        return new Neg(this.getExpression().assign(var, expression));
    }

    @Override
    public double returnValue() {
        return -super.getExpression().returnValue();
    }

    @Override
    public String toString() {
        return "(" + this.getOperator() + this.getExpression().toString() + ")";
    }

    @Override
    public Expression differentiate(String var) {
        return new Neg(this.getExpression().differentiate(var));
    }

    @Override
    public Expression clone() {
        return new Neg(this.getExpression());
    }
}
