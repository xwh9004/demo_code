package com.demo.leetcode;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 13:39 on 2021/3/15
 * @version V0.1
 * @classNmae BracketValidateSolution
 */

import org.junit.Test;

import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 *
 */
public class BracketValidateSolution {

    @Test
    public void solutionTest(){
        System.out.println(isValid("()"));
        System.out.println(isValid("()[]{}"));
        System.out.println(isValid("(]"));
        System.out.println(isValid("([)]"));
        System.out.println(isValid("{[]}"));
    }

    public boolean isValid(String s) {
        Stack<Character> bracketStack = new Stack<>();
        for (int i = 0;i<s.length();i++){
           char c = s.charAt(i);
            if(bracketStack.isEmpty()){
                bracketStack.push(c);
            }else {
                char sc=bracketStack.peek();
                if((sc=='(' &&c==')')
                        ||(sc=='[' &&c==']')
                        ||(sc=='{' &&c=='}')
                   ){
                    bracketStack.pop();
                }else{
                    bracketStack.push(c);
                }
            }

        }

        return bracketStack.isEmpty();
    }
}
