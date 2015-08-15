package core.function.operator;

/**
 * Created by Admin on 19/07/2015.
 */
public class Power extends Operator
{
    public Power()
    {
        param = new double[2];
        precedence = 4;
        associativity = 1; //right
    }

    @Override
    public double execute()
    {
        return Math.pow(param[0], param[1]);
    }
}
