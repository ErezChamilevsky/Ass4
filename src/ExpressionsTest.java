
import java.util.Map;
import java.util.TreeMap;

import BinaryExpression.Mult;
import BinaryExpression.Plus;
import BinaryExpression.Pow;
import InterfacesAndGenerics.Expression;
import UnaryExpression.Sin;
import Variables.Num;
import Variables.Var;

/**
 * A class that contains a main function to run a test of expression's assignment.
 */
public class ExpressionsTest {
    /**
     * Main. Running the test from the assignment's git.  
     *
     *
     * @param args not used.
     */
    public static void main(String[] args) throws Exception {
     Expression expression =
             new Plus(
                new Plus(new Mult(new Num(2), new Var("x")),
                     new Sin(new Mult(new Num(4), new Var("y")))),
                     new Pow(new Var("e"), new Var("x")));
        System.out.println(expression.toString());
        Map<String, Double> assigment = new TreeMap<String, Double>();
        assigment.put("x", 2.0);
        assigment.put("y", 0.25);
        assigment.put("e", 2.71);
        System.out.println(expression.evaluate(assigment));
        System.out.println(expression.differentiate("x"));
        System.out.println(expression.differentiate("x").
                evaluate(assigment));
        System.out.println(expression.differentiate("x").simplify());
    }
}
