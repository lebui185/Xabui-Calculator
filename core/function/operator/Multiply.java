package core.function.operator;

/**
 * Created by Admin on 19/07/2015.
 */
public class Multiply extends Operator
{
    public Multiply()
    {
        param = new double[2];
        precedence = 3;
        associativity = -1; //left
    }

    @Override
    public double execute()
    {
        return param[0] * param[1];
    }
}
