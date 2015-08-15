package core.function.operator;

import core.function.normal.Function;

/**
 * Created by Admin on 19/07/2015.
 */
public abstract class Operator extends Function
{
    protected int precedence;
    protected int associativity;

    public int getPrecedence() {
        return precedence;
    }

    public int getAssociativity() {
        return associativity;
    }
}
