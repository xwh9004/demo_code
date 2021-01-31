package com.demo.nowcoder;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 密码是我们生活中非常重要的东东，我们的那么一点不能说的秘密就全靠它了。哇哈哈. 接下来渊子要在密码之上再加一套密码，虽然简单但也安全。
 *
 * 假设渊子原来一个BBS上的密码为zvbo9441987,为了方便记忆，他通过一种算法把这个密码变换成YUANzhi1987，这个密码是他的名字和出生年份，怎么忘都忘不了，而且可以明目张胆地放在显眼的地方而不被别人知道真正的密码。

 * 他是这么变换的，大家都知道手机上的字母： 1--1， abc--2, def--3, ghi--4, jkl--5, mno--6, pqrs--7, tuv--8 wxyz--9, 0--0,就这么简单，渊子把密码中出现的小写字母都变成对应的数字，数字和其他的符号都不做变换，
 *
 * 声明：密码中没有空格，而密码中出现的大写字母则变成小写之后往后移一位，如：X，先变成小写，再往后移一位，不就是y了嘛，简单吧。记住，z往后移是a哦。
 *YUANzhi1987 --->zvbo9441987
 */
public class PwdMask {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
           String input =  scanner.nextLine();
           //c
            System.out.println(encode(input));
        }



    }

    public static String encode(String plain){
        StringBuilder sb = new StringBuilder();
        Map<Character,Integer> char2int = new HashMap();
        char2int.put('a',2);
        char2int.put('b',2);
        char2int.put('c',2);
        char2int.put('d',3);
        char2int.put('e',3);
        char2int.put('f',3);
        char2int.put('g',4);
        char2int.put('h',4);
        char2int.put('i',4);
        char2int.put('j',5);
        char2int.put('k',5);
        char2int.put('l',5);
        char2int.put('m',6);
        char2int.put('n',6);
        char2int.put('o',6);
        char2int.put('p',7);
        char2int.put('q',7);
        char2int.put('r',7);
        char2int.put('s',7);
        char2int.put('t',8);
        char2int.put('u',8);
        char2int.put('v',8);
        char2int.put('w',9);
        char2int.put('x',9);
        char2int.put('y',9);
        char2int.put('z',9);

        for (int i = 0;i<plain.length();i++){
            char c = plain.charAt(i);
            //是否是大写  大写转小写 并循环后移动1位
            if(c>='A'&&c<='Z'){
                if(c=='Z'){
                    sb.append('a');
                }else{
                    sb.append((char)(33 + (int)c));
                }

            }
            //小写 转字母
            if(c>='a'&&c<='z'){
                sb.append(char2int.get(c));
            }
            if(c>='0'&&c<='9'){
                sb.append(c);
            }
        }
       return sb.toString();
    }
}
