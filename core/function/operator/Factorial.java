package core.function.operator;

/**
 * Created by Admin on 19/07/2015.
 */
public class Factorial extends Operator
{
    public Factorial()
    {
        param = new double[1];
        precedence = 6;
        associativity = -1; //left
    }

    @Override
    public double execute()
    {
        if (param[0] % 1 != 0 || param[0] < 0)
        {
            throw new ArithmeticException("Factorial operator error");
        }
        if (param[0] < 2)
        {
            return 1;
        }
        double result = 2;
        for (double i = 3; i <= param[0] ; i += 1.0)
        {
            result *= i;
        }
        return result;
    }
}
