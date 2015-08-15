package core.function.normal;

/**
 * Created by Admin on 20/07/2015.
 */
public class Sqrt extends Function
{
    public Sqrt()
    {
        param = new double[1];
    }

    @Override
    public double execute()
    {
        if (param[0] < 0)
        {
            throw new ArithmeticException("Square root function error");
        }
        return Math.sqrt(param[0]);
    }

}
