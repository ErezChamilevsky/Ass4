
package InterfacesAndGenerics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Variables.Num;

/**
 * BaseExpression is an abstract class that implements the Expression interface.
 * It is the super of BinaryExpression and UnaryExpression classes.
 */
public abstract class BaseExpression implements Expression {
    // private Expression firstExpression;
    // private Expression secondExpression;
    /**
     * Constractor. Not used.
     * @param e expression
     */
    protected BaseExpression(Expression e) {
      //  this.firstExpression = e;
    //    this.secondExpression = null;
    }

    /**
     * Constractor. Not used.
     * @param a expression
     * @param b expression
     */
    public BaseExpression(Expression a, Expression b) {
  //      this.firstExpression = a;
//        this.secondExpression = b;
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        for (String i : assignment.keySet()) {
            this.assign(i, new Num(assignment.get(i)));
        }
        return this.evaluate();
    }

    @Override
    public double evaluate() throws Exception {
        return returnValue();
    }

    @Override
    public List<String> getVariables() {
        List<String> vars = new ArrayList<String>();

        return vars;
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return this;
    }

    @Override
    public double returnValue() {
        return 0;
    }

    @Override
    public void setExpression(Expression e) {
        return;
    }

    @Override
    public Expression differentiate(String var) {
        return this;
    }

    /**
     * removeDubblesFromList getting a list and returns this list without duplicate
     * values.
     * @param list of strings
     * @return list of strings
     */
    public List<String> removeDoublesFromList(List<String> list) {
        List<String> vars = new ArrayList<String>();
        for (String a : list) {
            if (!vars.contains(a)) {
                vars.add(a);
            }
        }
        return vars;
    }

    @Override
    public Expression simplify() {
        Expression e = this.clone();
        try {
            if (this.getVariables().isEmpty()) {
                return new Num(e.returnValue());
            }
        } catch (Exception ignorException) {

        }
        return e;
    }

    @Override
    public Expression clone() {
        return this;
    }
}
