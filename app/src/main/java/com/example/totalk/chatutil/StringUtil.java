package com.example.totalk.chatutil;

public class StringUtil {
    public static String dellEmpty(String str){
        if(null==str){
            return "";
        }
        return str.trim();
    }
}
