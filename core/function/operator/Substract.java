package core.function.operator;

/**
 * Created by Admin on 19/07/2015.
 */
public class Substract extends Operator
{
    public Substract()
    {
        param = new double[2];
        precedence = 2;
        associativity = -1; //left
    }

    @Override
    public double execute()
    {
        return param[0] - param[1];
    }
}
