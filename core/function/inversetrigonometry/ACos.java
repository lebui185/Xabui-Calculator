package core.function.inversetrigonometry;

/**
 * Created by Admin on 20/07/2015.
 */
public class ACos extends InverseTrigonometryFunction
{
    public ACos()
    {
        param = new double[1];
    }

    @Override
    public double execute()
    {
        if (param[0] < - 1 || param[0] > 1)
        {
            throw new ArithmeticException("acos function error");
        }
        return Math.acos(param[0]);
    }
}
