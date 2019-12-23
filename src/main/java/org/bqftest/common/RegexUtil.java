package org.bqftest.common;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 正则表达式
 *
 */
public class RegexUtil {

    public static void main(String[] args) {
        
        // 手机号(含港台)
//        String PHONE_REGEXP = "^[1|8][3-9][0-9]\\d{4,8}$";
        
        String PHONE_REGEXP = "^\\d{7,11}$";

        List<String> accounts = Arrays.asList("85251644516", "15867890876","1234567","12345678", "a1234567890", "1234567890b");

        for (String a : accounts) {
            boolean result = Pattern.compile(PHONE_REGEXP).matcher(a).matches();
            System.out.println(a + "  " + result);
        }
    }

}
