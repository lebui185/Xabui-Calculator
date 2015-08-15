package core.function.normal;

/**
 * Created by Admin on 20/07/2015.
 */
public class Log extends Function
{
    public Log()
    {
        param = new double[2];
    }

    @Override
    public double execute()
    {
        if (param[0] <= 0 || param[1] <= 0)
        {
            throw new ArithmeticException("Logarith function error");
        }
        return Math.log(param[1]) / Math.log(param[0]);
    }

}
