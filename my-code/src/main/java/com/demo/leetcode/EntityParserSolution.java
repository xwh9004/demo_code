package com.demo.leetcode;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 15:59 on 2021/3/26
 * @version V0.1
 * @classNmae EntityParserSolution
 */
public class EntityParserSolution {


    public static void main(String[] args) {
        EntityParserSolution solution = new EntityParserSolution();
        System.out.println(solution.entityParser("&amp;gt;"));
    }

    public String entityParser(String text) {
        return text
                .replaceAll("&quot;", "\"")
                .replaceAll("&apos;", "'")
                .replaceAll("&gt;", ">")
                .replaceAll("&lt;", "<")
                .replaceAll("&frasl;", "/")
                .replaceAll("&amp;", "&");
    }
}
