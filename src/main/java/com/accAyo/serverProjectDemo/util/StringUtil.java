package com.accAyo.serverProjectDemo.util;

import com.accAyo.serverProjectDemo.common.Constants;

/**
 * Desc:
 *
 * @author shixiangyu
 * @date 2018/5/28
 */
public class StringUtil {
    public static String getOneRandom(String oldOneRandom) {
        String ram = Math.random() + "0000000000000000";
        while (ram.equals(oldOneRandom)) {
            ram = Math.random() + "0000000000000000";
        }
        return ram.substring(0, Constants.RANDOM_ONE_LENGTH);
    }

    public static int str2int(String str) {
        int i = 0;
        if (str == null) {
            return 0;
        } else {
            try {
                i = Integer.parseInt(str);
            } catch (Exception e) {
                return 0;
            }
        }
        return i;
    }
}
