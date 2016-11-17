package com.nextstep.constant;

public class Constans {
    public static final String HOST = "http://192.168.0.105:8080/";
    public static final String LIST_TASKS = "tasks/";
    public static final String BALANCE = "balance/";

    public static String getURL(String URL, String REST){
        StringBuilder stringBuilder = new StringBuilder(URL);
        stringBuilder.append(REST);
        return stringBuilder.toString();
    }

}
