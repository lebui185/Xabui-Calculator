package core.function.inversetrigonometry;

/**
 * Created by Admin on 20/07/2015.
 */
public class ATan extends InverseTrigonometryFunction
{
    public ATan()
    {
        param = new double[1];
    }

    @Override
    public double execute()
    {
        return Math.atan(param[0]);
    }
}
