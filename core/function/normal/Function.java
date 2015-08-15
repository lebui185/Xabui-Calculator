package core.function.normal;

/**
 * Created by Admin on 19/07/2015.
 */
public abstract class Function
{
    protected double[] param;

    public void setParam(int index, double value)
    {
        param[index] = value;
    }

    public int getParamNumber()
    {
        return param.length;
    }

    public abstract double execute();

}
