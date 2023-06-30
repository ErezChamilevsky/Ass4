package UnaryExpression;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import BinaryExpression.BinaryExpression;
import InterfacesAndGenerics.BaseExpression;
import InterfacesAndGenerics.Expression;
import Variables.Num;

/**
 * UnaryExpression is a class of unary operations on expression. Extends
 * BaseExpression class.
 */
public abstract class UnaryExpression extends BaseExpression {
    private Expression expression;
    private String operator;

    /**
     * Constractor.
     * @param a expression
     * @param operator of the operation
     */
    public UnaryExpression(Expression a, String operator) {
        super(a);
        this.expression = a;
        this.operator = operator;
    }

    /**
     * Getting expression of the unary.
     * @return epression
     */
    public Expression getExpression() {
        return this.expression;
    }

    /**
     * Setting the exprssion of the unaryExpression.
     * @param e expression to be seted
     */
    public void setExpression(Expression e) {
        this.expression = e;
    }

    /**
     * Getting the operator the expression.
     * @return operator
     */
    public String getOperator() {
        return this.operator;
    }

    @Override
    public String toString() {
        return this.getOperator() + "(" + this.getExpression().toString() + ")";
    }

    @Override
    public List<String> getVariables() {
        List<String> vars = new ArrayList<String>();
        if (this.getExpression().getVariables() != null) {
            vars.addAll(this.getExpression().getVariables());
        }
        return removeDoublesFromList(vars);
    }

    @Override
    public Expression simplify() {
        Expression e = super.simplify();
        if (e instanceof BinaryExpression) {

            ((BinaryExpression) e).setFirstExpression(((BinaryExpression) e).getFirstExpression().simplify());
            ((BinaryExpression) e).setSecondExpression(((BinaryExpression) e).getSecondExpression().simplify());

        }
        if (e instanceof UnaryExpression) {
            e.setExpression(((UnaryExpression) e).getExpression().simplify());
        }
        return e;
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        Expression e = this.clone();
        for (String i : assignment.keySet()) {
            ((UnaryExpression) e).setExpression(((UnaryExpression) e).getExpression()
                    .assign(i, new Num(assignment.get(i))));
        }
        return e.evaluate();
    }

    @Override
    public Expression assign(String var, Expression expression) {
        Expression e = this.clone();
        e = super.simplify();
        e.setExpression(this.getExpression().assign(var, expression));
        return e;
    }

}
