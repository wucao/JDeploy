package com.xxg.jdeploy.util;

import java.util.HashMap;
import java.util.Map;

public class QueryStringParser {

    public static Map<String, String> parse(String queryString) {

        Map<String, String> map = new HashMap<String, String>();

        String[] pairs = queryString.split("&");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            if(keyValue.length > 1) {
                map.put(keyValue[0], keyValue[1]);
            } else {
                map.put(keyValue[0], null);
            }
        }

        return map;
    }
}