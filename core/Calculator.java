package core;

import core.function.inversetrigonometry.ACos;
import core.function.inversetrigonometry.ASin;
import core.function.inversetrigonometry.ATan;
import core.function.inversetrigonometry.InverseTrigonometryFunction;
import core.function.normal.*;
import core.function.operator.*;
import core.function.trigonometry.Cos;
import core.function.trigonometry.Sin;
import core.function.trigonometry.Tan;
import core.function.trigonometry.TrigonometryFunction;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Admin on 19/07/2015.
 */
public class Calculator
{
    private Pattern pattern;
    private Matcher matcher;
    private double prevResult;
    private int angularMeasure; // 0: Degree, 1: Radian

    private static HashMap<String, Operator> operatorMap = new HashMap<>();
    private static HashMap<String, Function> functionMap = new HashMap<>();
    private static HashMap<String, Double> constantMap = new HashMap<>();

    public Calculator()
    {
        angularMeasure = 0;
        pattern = Pattern.compile("[[a-z][A-Z]]+|\\d+(\\.\\d+)?|[-^+*/(),π!]|[^\\w\\d.\\-^+*/(),π!]+");
        if (operatorMap.isEmpty())
        {
            operatorMap.put("+", new Add());
            operatorMap.put("-", new Substract());
            operatorMap.put("*", new Multiply());
            operatorMap.put("/", new Divide());
            operatorMap.put("^", new Power());
            operatorMap.put("!", new Factorial());
            operatorMap.put("~", new Negative());
        }
        if (functionMap.isEmpty())
        {
            functionMap.put("abs", new Abs());
            functionMap.put("log", new Log());
            functionMap.put("ln", new Ln());
            functionMap.put("sin", new Sin());
            functionMap.put("cos", new Cos());
            functionMap.put("tan", new Tan());
            functionMap.put("asin", new ASin());
            functionMap.put("acos", new ACos());
            functionMap.put("atan", new ATan());
            functionMap.put("√", new Sqrt());
            functionMap.put("∛", new Cbrt());
        }
        if (constantMap.isEmpty())
        {
            constantMap.put("π", Math.PI);
            constantMap.put("e", Math.E);
        }
    }

    public double calculate(String expr)
    {
        ArrayList<String> infixTokens = tokenizeInfix(expr);
        Queue<String> postfixTokens = toPostfix(infixTokens);
        return calculatePostfixTokens(postfixTokens);
    }

    public int getAngularMeasure()
    {
        return angularMeasure;
    }

    public void setAngularMeasure(int angularMeasure)
    {
        this.angularMeasure = angularMeasure;
    }

    public double getPrevResult()
    {
        return prevResult;
    }

    private ArrayList<String> tokenizeInfix(String infixExpr)
    {
        ArrayList<String> tokens = new ArrayList<>(20);
        matcher = pattern.matcher(infixExpr);
        while (matcher.find())
        {
            tokens.add(matcher.group());
        }
        return tokens;
    }

    private Queue<String> toPostfix(ArrayList<String> infixTokens)
    {
        Queue<String> postfixTokens = new ArrayDeque<>();
        Stack<String> stack = new Stack<>();

        Operator o1;
        String token;
        for (int i = 0; i < infixTokens.size(); i++)
        {
            token = infixTokens.get(i).toLowerCase();
            if (isNegative(infixTokens, token, i))
            {
                stack.add("~");
            }

            else if (isNumber(token) || isConstant(token) || isAns(token))
            {
                postfixTokens.add(token);
            }
            else if (isOperator(token))
            {
                o1 = operatorMap.get(token);

                while (!stack.isEmpty() && isFunction(stack.peek()))
                {
                    postfixTokens.add(stack.pop());
                }

                while (!stack.isEmpty() && isOperator(stack.peek())
                    && ((o1.getAssociativity() == -1 && o1.getPrecedence() <= operatorMap.get(stack.peek()).getPrecedence())
                    ||(o1.getAssociativity() == 1 && o1.getPrecedence() < operatorMap.get(stack.peek()).getPrecedence())))
                {
                    postfixTokens.add(stack.pop());
                }
                stack.push(token);
            }
            else if (isFunction(token))
            {
                stack.push(token);
            }

            else if (isLeftParenthesis(token))
            {
                stack.push(token);
            }
            else if (isFunctionArgSeparator(token))
            {
                try
                {
                    while (!isLeftParenthesis(stack.peek()))
                    {
                        postfixTokens.add(stack.pop());
                    }
                }
                catch(EmptyStackException e)
                {
                    throw new InputMismatchException("Parentheses were mismatched");
                }
            }

            else if (isRightParenthesis(token))
            {
                try
                {
                    while (!isLeftParenthesis(stack.peek()))
                    {
                        postfixTokens.add(stack.pop());
                    }
                }
                catch(EmptyStackException e)
                {
                    throw new InputMismatchException("Parentheses were mismatched");
                }

                stack.pop();
            }
            else if (!token.equals(" "))
            {
                throw new InputMismatchException("Invalid function name");
            }
        }

        while (!stack.isEmpty())
        {
            if (isLeftParenthesis(stack.peek()))
            {
                throw new InputMismatchException("Parentheses were mismatched");
            }
            postfixTokens.add(stack.pop());
        }
        return postfixTokens;
    }

    private double calculatePostfixTokens(Queue<String> postfixTokens)
    {
        String token;
        Stack<Double> stack = new Stack<>();
        int paramNum;
        double param;
        double funcResult;

        while (!postfixTokens.isEmpty())
        {
            token = postfixTokens.poll();
            if (isNumber(token))
            {
                stack.add(Double.parseDouble(token));
            }
            else if (isConstant(token))
            {
                stack.add(constantMap.get(token));
            }
            else if (isAns(token))
            {
                stack.add(prevResult);
            }
            else
            {
                Function func;
                func = operatorMap.get(token);
                if (func == null)
                {
                    func = functionMap.get(token);
                }
                paramNum = func.getParamNumber();
                for (int i = paramNum - 1; i >= 0; i--)
                {
                    try
                    {
                        param = stack.pop();
                        if (func instanceof TrigonometryFunction)
                        {
                            switch (angularMeasure)
                            {
                                case 0: // Degree
                                    param = Math.toRadians(param);
                                    break;
                            }
                        }
                        func.setParam(i, param);
                    }
                    catch (Exception e)
                    {
                        throw new InputMismatchException("Parameters error");
                    }
                }
                funcResult = func.execute();
                if (func instanceof InverseTrigonometryFunction)
                {
                    switch (angularMeasure)
                    {
                        case 0: // Degree
                            funcResult = Math.toDegrees(funcResult);
                            break;
                    }
                    funcResult = round(funcResult);
                }
                stack.push(funcResult);
            }
        }
        if (stack.size() != 1)
        {
            throw new InputMismatchException("Final result error");
        }
        prevResult = stack.peek();
        return stack.peek();
    }

    private boolean isNegative(ArrayList<String> infixTokens, String token, int index)
    {
        if (token.equals("-"))
        {
            if (index == 0)
            {
                return true;
            }
            String prev = infixTokens.get(index - 1);
            return (isOperator(prev) || isLeftParenthesis(prev) || isFunctionArgSeparator(prev));
        }
        return false;
    }

    private boolean isNumber(String token)
    {
        try
        {
            Double.parseDouble(token);
            return true;
        }
        catch(NumberFormatException e)
        {
            return false;
        }
    }

    private boolean isFunction(String token)
    {
        return functionMap.containsKey(token);
    }

    private boolean isFunctionArgSeparator(String token)
    {
        return token.equals(",");
    }

    private boolean isLeftParenthesis(String token)
    {
        return token.equals("(");
    }

    private boolean isRightParenthesis(String token)
    {
        return token.equals(")");
    }

    private boolean isOperator(String token)
    {
        return operatorMap.containsKey(token);
    }

    private boolean isConstant(String token)
    {
        return constantMap.containsKey(token);
    }

    private boolean isAns(String token)
    {
        return token.equals("ans");
    }

    private double round(double num)
    {
        final double epsilon = 1E-13;
        double nearestInt = Math.round(num);
        if (Math.abs(nearestInt - num) < epsilon)
        {
            return nearestInt;
        }
        return num;
    }
}
