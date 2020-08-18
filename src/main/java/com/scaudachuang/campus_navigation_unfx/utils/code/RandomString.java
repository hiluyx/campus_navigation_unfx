package com.scaudachuang.campus_navigation_unfx.utils.code;

public class RandomString {
    public static String getRandomString(int stringLength) {
        String string = "0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < stringLength; i++) {
            int index = (int) Math.floor(Math.random() * string.length());//向下取整0-25
            sb.append(string.charAt(index));
        }
        return sb.toString();
    }
}
