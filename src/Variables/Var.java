package Variables;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import InterfacesAndGenerics.Expression;


/**
 * Var is a class of an expression that is a variable. Implements Expression
 * interface.
 */
public class Var implements Expression {
    static final double EULER = 2.71828;
    static final double PI = 3.14159;

    private String val;

    /**
     * Constractor.
     *
     * @param val string of variable
     */
    public Var(String val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return val;
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        throw new UnsupportedOperationException("Error while evaluating");
    }

    @Override
    public double evaluate() throws Exception {
        throw new UnsupportedOperationException("Error while evaluating");
    }

    @Override
    public List<String> getVariables() {
        List<String> vars = new ArrayList<String>();
        vars.add(this.toString());
        return vars;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        if (this.toString().equals(var)) {
            return expression;
        } else {
            return this;
        }
    }

    @Override
    public double returnValue() {
        if (this.toString().equals("e")) {
            return new Num(EULER).returnValue();
        }
        if (this.toString().equals("Pi")) {
            return new Num(PI).returnValue();
        }
        throw new UnsupportedOperationException("Error while evaluating");
    }

    @Override
    public void setExpression(Expression e) {
        return;
    }

    @Override
    public Expression differentiate(String var) {
        if (var.equals(this.val)) {
            return new Num(1);
        }
        return new Num(0);
    }

    @Override
    public Expression simplify() {
        return this;
    }

    @Override
    public Expression clone() {
        return new Var(this.val);
    }

}
