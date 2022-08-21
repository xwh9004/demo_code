package com.demo.nowcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class CVSMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        while (scanner.hasNextLine()) {
//            String input =  scanner.nextLine();
//            String[] values=input.split(",");
//            System.out.println(cvsCell(values));
//        }

        String[] values = {"<C>", "2<A>00","AAA","<C>"};
        System.out.println(cvsCell(values));
    }

    /**
     * 1,2<A>00
     *
     * @param values
     * @return
     */
    private static String cvsCell(String[] values) {
        int pair = 0;
        Map<Character, String> valueMap = new HashMap<>();
        Map<Integer, String> refMap = new HashMap<>();


        for (int i = 0; i < values.length; i++) {
            char c = (char) ('A' + i);
            //校验value
            String ref = "";
            String value = values[i];
            int start = 0;
            int end = 0;
            for (int j = 0; j < value.length(); j++) {
                if (value.charAt(j) == '<') {
                    pair = pair - 1;
                    start = j;
                }
                if (value.charAt(j) == '>') {
                    pair = pair + 1;
                    end = j;
                }

            }

            if (pair != 0) {
                //输入有错
                return "-1";
            }
             if(start!=end){
                 ref = value.substring(start,end+1);
             }

            if (ref.length() > 3) {
                return "-1";
            }
            if (ref.length() == 3) {
                if ('Z' < ref.charAt(1) || ref.charAt(1) < 'A') {
                    return "-1";
                }
                //输入正确

                refMap.put(i, ref);
            }
            valueMap.put(c, values[i]);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            String value = values[i];
            String plain = value;
            if (refMap.containsKey(i)) {
                String refValue = getRef(refMap.get(i), valueMap);
                plain = plain.replace(refMap.get(i),refValue);
            }
            sb.append(plain + ",");
        }
        String res = sb.toString().substring(0, sb.length()-1);
        return res;
    }

    public static String getRef(String ref, Map<Character, String> valueMap) {
        String value = valueMap.get(ref.charAt(1));
        if (!value.contains("<")) {
            return value;
        }
       int start = value.indexOf("<");
        ref = value.substring(start,start+2);
        //获取下一层引用

        return getRef(ref, valueMap);
    }
}
