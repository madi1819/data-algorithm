package com.dataStructures.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author: md
 * @Date: 2020/8/10 12:14
 */
public class PolandNotation {

    public static void main(String[] args) {
        //定义逆波兰表达式
        String suffixExpression = "30 4 + 3 / 6 -";

        String s = "afvg";
        char c = s.charAt(0);
        System.out.println(c == 97);
        //1.suffixExpression = 》 ArrayList
        //2.将ArrayList传递给方法，配合栈

        System.out.println(getListString(suffixExpression));
        System.out.println(calculate(getListString(suffixExpression)));
        System.out.println(toInfixExpressionList("1+((2+3)*4)-5"));
        System.out.println(toInfixExpressionList("2+3*5"));
        System.out.println(parseSuffixExpressionList(toInfixExpressionList("1+((2+3)*4)-5")));
        System.out.println(parseSuffixExpressionList(toInfixExpressionList("1-2*3")));

        System.out.println(calculate(parseSuffixExpressionList(toInfixExpressionList("1+((2+3)*4)-5"))));
        System.out.println(calculate(parseSuffixExpressionList(toInfixExpressionList("2+3*5"))));


    }

    public static List<String> getListString( String suffixExpression ){
        String[] split = suffixExpression.split(" ");
        List list = new ArrayList();
        for (String s : split) {
            list.add(s);
        }
        return list;
    }
    //中缀list
    public static List<String> toInfixExpressionList(String s){
        List<String> ls = new ArrayList<>();
        //指针，用于遍历中缀表达式字符串
        int i = 0;
        String str;
        char c;//每遍历一个字符，放到c
        do {
            //如果c是非数字，就需要假如到ls
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57){
                ls.add(""+c);
                i++;
            }else {
                str = "";
                while (i < s.length() && (c=s.charAt(i))>=48 && (c=s.charAt(i))<=57){
                    str += c;
                    i++;
                }
                ls.add(str);
            }
        }while (i < s.length());
        return ls;
    }

    //将中缀list转成后缀list
    public static List<String> parseSuffixExpressionList(List<String> ls){
        Stack<String> stack = new Stack<>(); //符号栈
        //Stack<String> stack2 = new Stack<>(); //中间结果栈，没有pop操作，不需要
        List<String> list = new ArrayList<>();

        for (String item : ls) {
            //如果是数，入list
            if (item.matches("\\d+")){
                list.add(item);
            }else if (item.equals("(")){
                stack.push(item);
            }else if (item.equals(")")){
                while (!stack.peek().equals("(")){
                    list.add(stack.pop());
                }
                stack.pop();
            }else {
                //当item的优先级小于等于栈顶的优先级，将s1栈顶的运算符假如list
                while (stack.size() != 0 && Operation.getValue(stack.peek()) >= Operation.getValue(item)){
                    list.add(stack.pop());
                }
                //将item放入栈
                stack.push(item);
            }
        }

        //将stack的运算符依次弹出假如list
        while (stack.size() != 0){
            list.add(stack.pop());
        }
        return list;

    }


    public static int calculate(List<String> list){
        Stack<String> stack = new Stack<>();
        for (String s : list) {
            if (s.matches("\\d+")){
                stack.push(s);
            }else {
                int num2 = Integer.valueOf(stack.pop());
                int num1 = Integer.valueOf(stack.pop());
                int res = 0;
                if (s.equals("+")){
                    res = num1 + num2;
                }else if (s.equals("-")){
                    res = num1 - num2;
                }else if (s.equals("*")){
                    res = num1 * num2;
                }else if (s.equals("/")){
                    res = num1 / num2;
                }else {
                    throw new RuntimeException("符号有误");
                }
                stack.push(res + "");
            }
        }
        return Integer.valueOf(stack.pop());
    }
}

//编写一个类Operation,返回一个运算符对应的优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    public static int getValue(String operation){
        int result = 0;
        switch (operation){
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在");
                break;
        }
        return result;
    }
}
