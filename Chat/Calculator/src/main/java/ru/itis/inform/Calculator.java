package ru.itis.inform;


public class Calculator {

    public static int sum(int x, int y){
        return x+y;
    }
    public static int difference(int x, int y){
        return x-y;
    }
    public static int div(int x,int y){
        return x/y;
    }
    public static int product(int x,int y){
        return x*y;
    }

    public static int count(String x, String y, String op){
        if (op.equals("+")){
           return sum(Integer.parseInt(x),Integer.parseInt(y));
        }
        if (op.equals("-")){
           return difference(Integer.parseInt(x),Integer.parseInt(y));
        }
        if (op.equals("/")){
           return div(Integer.parseInt(x),Integer.parseInt(y));
        }
        if (op.equals("*")){
           return product(Integer.parseInt(x),Integer.parseInt(y));
        }
        return 0;
    }

}
