package com.group7.fruitswebsite.util;

import com.github.slugify.Slugify;

public class StringUtil {

    private StringUtil() {
    }

    public static String seo(String text) {
        return new Slugify().slugify(text);
    }

    public static boolean isNullOrEmpty(String input) {
        return input == null || input.equals("");
    }
}
