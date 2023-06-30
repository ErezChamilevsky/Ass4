
package BinaryExpression;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import InterfacesAndGenerics.BaseExpression;
import InterfacesAndGenerics.Expression;
import UnaryExpression.UnaryExpression;
import Variables.Num;

/**
 * BinaryExpression is a class of binary operations on expression. Extends
 * BaseExpression class.
 */
public abstract class BinaryExpression extends BaseExpression {
  private Expression first;
  private Expression second;
  private String operator;

/**
 * Constractor of BinaryExpression.
 *
 * @param a  first expression
 * @param b  second expression
 * @param op operator of the expression
 */
  public BinaryExpression(Expression a, Expression b, String op) {
    super(a, b);
    this.first = a;
    this.second = b;
    this.operator = op;
  }


  /**
   * GetFirstExpression.
   * @return first expression of the BinaryExpression
   */
  public Expression getFirstExpression() {
    return this.first;
  }

  /**
   * GetSecondExpression.
   *
   * @return second expression of the BinaryExpression
   */
  public Expression getSecondExpression() {
    return this.second;
  }

  /**
   * SetfirstExpression.
   * @param e expression to be seted
   */
  public void setFirstExpression(Expression e) {
    this.first = e;
  }

  /**
   * SetfirstExpression.
   * @param e expression to be seted
   */
  public void setSecondExpression(Expression e) {
    this.second = e;
  }

  /**
   * Getting the operator of this expression.
   * @return string of operator
   */
  public String getOperator() {
    return this.operator;
  }

  @Override
  public String toString() {
    return "(" + this.getFirstExpression().toString() + " " + this.getOperator() + " "
        + this.getSecondExpression().toString() + ")";
  }

  @Override
  public List<String> getVariables() {
    List<String> vars = new ArrayList<String>();
    if (this.getFirstExpression().getVariables() != null) {
      vars.addAll(this.getFirstExpression().getVariables());
    }
    if (this.getSecondExpression().getVariables() != null) {
      vars.addAll(this.getSecondExpression().getVariables());
    }
    return removeDoublesFromList(vars);
  }


  @Override
  public double evaluate(Map<String, Double> assignment) throws Exception {
    Expression e = this.clone();
    for (String i : assignment.keySet()) {
      ((BinaryExpression) e).setFirstExpression(((BinaryExpression) e)
          .getFirstExpression().assign(i, new Num(assignment.get(i))));
      ((BinaryExpression) e).setSecondExpression(((BinaryExpression) e)
          .getSecondExpression().assign(i, new Num(assignment.get(i))));
    }
    return e.evaluate();
  }

  @Override
  public Expression assign(String var, Expression expression) {
    Expression e = this.clone();
    ((BinaryExpression) e).setFirstExpression(this.getFirstExpression().assign(var, expression));
    ((BinaryExpression) e).setSecondExpression(this.getSecondExpression().assign(var, expression));
    return e;
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

}
