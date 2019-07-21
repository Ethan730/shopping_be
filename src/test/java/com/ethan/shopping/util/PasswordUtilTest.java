package com.ethan.shopping.util;

import com.ethan.shopping.utils.PasswordUtil;
import org.junit.Test;

public class PasswordUtilTest {
    @Test
    public void test(){
        System.out.println(PasswordUtil.MD5("1234"));
    }
}
