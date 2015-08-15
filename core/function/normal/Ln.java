package core.function.normal;

/**
 * Created by Admin on 20/07/2015.
 */
public class Ln extends Function
{
    public Ln()
    {
        param = new double[1];
    }

    @Override
    public double execute()
    {
        if (param[0] <= 0)
        {
            throw new ArithmeticException("Logarith function error");
        }
        return Math.log(param[0]);
    }

}
