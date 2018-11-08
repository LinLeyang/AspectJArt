package com.penta.aspectjart;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class UrlParamUtil {

    public static String urlParamsToContext(String url, Map<?, ?> params) {
        return url + urlEncodeUTF8(params);
    }

    private static String urlEncodeUTF8(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    public static String urlEncodeUTF8(Map<?, ?> map) {
        StringBuilder sb = new StringBuilder();
        if (map == null) {
            return "";
        }
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            } else {
                sb.append("?");
            }
            sb.append(String.format("%s=%s",
                    urlEncodeUTF8(entry.getKey().toString()),
                    urlEncodeUTF8(entry.getValue().toString())
            ));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("p1", 12);
        map.put("p2", "cat");
        map.put("p3", "a & b");
        System.out.println(urlEncodeUTF8(map));
        // prints "p3=a+%26+b&p2=cat&p1=12"
    }
}