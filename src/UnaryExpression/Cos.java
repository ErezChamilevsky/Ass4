
package UnaryExpression;

import BinaryExpression.Mult;
import InterfacesAndGenerics.Expression;

/**
 * Cos extends unaryExpression. It is an expression that get epxression
 * and returns the cos of it.
 */
public class Cos extends UnaryExpression {
    static final String OPERATOR = "cos";
   /**
    * Constracotr.
    * @param a expression
    */
    public Cos(Expression a) {
        super(a, OPERATOR);
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Cos(this.getExpression().assign(var, expression));
    }

    @Override
    public double returnValue() {
        return Math.cos(Math.toRadians(super.getExpression().returnValue()));
    }

    @Override
    public Expression differentiate(String var) {
        return new Mult(new Neg(new Sin(this.getExpression())), this.getExpression().differentiate(var));
    }

    @Override
    public Expression clone() {
        return new Cos(this.getExpression());
    }

}
