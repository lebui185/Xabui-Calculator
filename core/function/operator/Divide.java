package core.function.operator;

/**
 * Created by Admin on 19/07/2015.
 */
public class Divide extends Operator
{
    public Divide()
    {
        param = new double[2];
        precedence = 3;
        associativity = -1; //left
    }

    @Override
    public double execute()
    {
        if (param[1] == 0)
        {
            throw new ArithmeticException("Divided by zero");
        }
        return param[0] / param[1];
    }
}
