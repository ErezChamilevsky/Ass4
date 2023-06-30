package UnaryExpression;

import BinaryExpression.Mult;
import InterfacesAndGenerics.Expression;

/**
 * Sin extends unaryExpression. It is an expression that get epxression
 * and returns the sin of it.
 */
public class Sin extends UnaryExpression {
    static final String OPERATOR = "sin";

    /**
     * Constractor.
     * @param a expresion
     */
    public Sin(Expression a) {
        super(a, OPERATOR);
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Sin(this.getExpression().assign(var, expression));
    }

    @Override
    public double returnValue() {
        return Math.sin(Math.toRadians(super.getExpression().returnValue()));
    }

    @Override
    public Expression differentiate(String var) {
        return new Mult(new Cos(this.getExpression()), this.getExpression().differentiate(var));
    }

    @Override
    public Expression clone() {
        return new Sin(this.getExpression());
    }
}
