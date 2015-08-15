package core.function.normal;

/**
 * Created by Admin on 20/07/2015.
 */
public class Cbrt extends Function
{
    public Cbrt()
    {
        param = new double[1];
    }

    @Override
    public double execute()
    {
        return Math.cbrt(param[0]);
    }

}
