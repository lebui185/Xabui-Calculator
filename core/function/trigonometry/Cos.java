package core.function.trigonometry;

/**
 * Created by Admin on 20/07/2015.
 */
public class Cos extends TrigonometryFunction
{
    public Cos()
    {
        param = new double[1];
    }

    @Override
    public double execute()
    {
        double result = Math.cos(param[0]);
        if (Math.abs(result) <= 9.9E-13)
        {
            result = 0;
        }
        if (Math.abs(result - 0.5) <= 1E-12)
        {
            result = 0.5;
        }
        if (Math.abs(result + 0.5) <= 1E-12)
        {
            result = -0.5;
        }
        return result;
    }

}
