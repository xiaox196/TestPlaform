package com.tool.plaform;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
        Pattern p = Pattern.compile("\\$\\{(.*?)\\}");
         Pattern funPattern = Pattern
                .compile("__(\\w*?)\\((([\\w\\\\\\/:\\.\\$]*,?)*)\\)");
        Matcher m=p.matcher("${_csv(bbb,11)}");
        if(m.find()){
            System.out.println(m.group(1));
        }
        Matcher m2=funPattern.matcher("__xxx(ooo)}");
        if(m2.find()){
            System.out.println(m2.group(1));
            System.out.println(m2.group(2));
        }
    }
}
