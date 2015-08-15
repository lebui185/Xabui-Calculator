package core.function.operator;

/**
 * Created by Admin on 19/07/2015.
 */
public class Add extends Operator
{
    public Add()
    {
        param = new double[2];
        precedence = 2;
        associativity = -1; //left
    }

    @Override
    public double execute()
    {
        return param[0] + param[1];
    }
}
