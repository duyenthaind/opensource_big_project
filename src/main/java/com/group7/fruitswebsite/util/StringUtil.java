package com.group7.fruitswebsite.util;

import com.github.slugify.Slugify;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Random;

public class StringUtil {
    private static final String regex = "\\d+";
    private static final Random RANDOM = new Random();
    private static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    private StringUtil() {
    }

    public static String seo(String text) {
        return new Slugify().slugify(text);
    }

    public static boolean isNullOrEmpty(String input) {
        return input == null || input.equals("");
    }

    public static boolean isNumber(String input) {
        return input.matches(regex);
    }

    public static String randomString(int length) {
        byte[] arr = new byte[length];
        RANDOM.nextBytes(arr);
        return new String(arr, StandardCharsets.UTF_8);
    }

    public static String randomString(int length, int type) {
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int index = -1; ++index < length; ) {
            stringBuilder.append(AB.charAt(SECURE_RANDOM.nextInt(AB.length())));
        }
        return stringBuilder.toString();
    }
}
