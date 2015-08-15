package core.function.inversetrigonometry;

/**
 * Created by Admin on 20/07/2015.
 */
public class ASin extends InverseTrigonometryFunction
{
    public ASin()
    {
        param = new double[1];
    }

    @Override
    public double execute()
    {
        if (param[0] < - 1 || param[0] > 1)
        {
            throw new ArithmeticException("asin function error");
        }
        return Math.asin(param[0]);
    }
}