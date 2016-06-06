package com.example.dllo.liwushuo.tool;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by dllo on 16/6/6.
 */
public class FormatCodeUtil {
    public static String codingFormat(String str) {

        try {
            String utfStr = URLEncoder.encode(str, "utf-8");
            return utfStr;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "" ;
    }
}