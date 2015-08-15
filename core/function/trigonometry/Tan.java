package core.function.trigonometry;

/**
 * Created by Admin on 20/07/2015.
 */
public class Tan extends TrigonometryFunction
{
    private Sin sinFunc;
    private Cos cosFunc;

    public Tan()
    {
        param = new double[1];
        sinFunc = new Sin();
        cosFunc = new Cos();
    }

    @Override
    public double execute()
    {
        sinFunc.setParam(0, param[0]);
        cosFunc.setParam(0, param[0]);
        double sinVal = sinFunc.execute();
        double cosVal = cosFunc.execute();
        if (cosVal == 0)
        {
            throw new ArithmeticException("Tan function error");
        }
        double result = sinVal / cosVal;
        if (Math.abs(result - 1) <= 1E-12)
        {
            result = 1;
        }
        if (Math.abs(result + 1) <= 1E-12)
        {
            result = -1;
        }
        return result;
    }

}
