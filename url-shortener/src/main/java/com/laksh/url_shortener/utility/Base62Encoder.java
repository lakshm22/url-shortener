package com.laksh.url_shortener.utility;

public class Base62Encoder {
    private static final String CHARACTERS="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String encode(Long num){
        StringBuilder code=new StringBuilder();

        while(num>0){
            int rem=(int)(num%62);
            code.append(CHARACTERS.charAt(rem));
            num/=62;
        }
        return code.reverse().toString();
    }
}