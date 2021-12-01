package com.group7.fruitswebsite.common;

import com.group7.fruitswebsite.util.StringUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author duyenthai
 */
public class TestRandomString {
    @Test
    void genString() {
        String newString = StringUtil.randomString(8, 1);
        System.out.println(newString);
        Assertions.assertEquals(newString, "");
    }
}
