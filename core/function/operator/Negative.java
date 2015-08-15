package core.function.operator;


/**
 * Created by Admin on 20/07/2015.
 */
public class Negative extends Operator
{
    public Negative()
    {
        param = new double[1];
        precedence = 5;
        associativity = -1; //left
    }

    @Override
    public double execute()
    {
        return -param[0];
    }

}
