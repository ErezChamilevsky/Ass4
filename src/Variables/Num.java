package Variables;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import InterfacesAndGenerics.Expression;

/**
 * Num is a class of an expression that is a number. Implements Expression interface.
 */
public class Num implements Expression {
  private double num;


  /**
   * Constractor.
   * @param num number
   */
  public Num(double num) {
    this.num = num;
  }

  @Override
  public double returnValue() {
    return this.num;
  }

  @Override
  public double evaluate(Map<String, Double> assignment) throws Exception {
    return evaluate();
  }

  @Override
  public double evaluate() throws Exception {
    return returnValue();
  }

  @Override
  public List<String> getVariables() {
    return new ArrayList<String>();
  }

  @Override
  public Expression assign(String var, Expression expression) {
    return this;
  }

  @Override
  public String toString() {
    return String.valueOf(this.num);
  }

  @Override
  public void setExpression(Expression e) {
    return;
  }

  @Override
  public Expression differentiate(String var) {
    return new Num(0);
  }

  @Override
  public Expression simplify() {
    return this;
  }

  @Override
  public Expression clone() {
    return new Num(num);
  }
}
