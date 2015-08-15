package core.function.normal;

/**
 * Created by Admin on 20/07/2015.
 */
public class Abs extends Function
{
    public Abs()
    {
        param = new double[1];
    }

    @Override
    public double execute()
    {
        return Math.abs(param[0]);
    }

}
