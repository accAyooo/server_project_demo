package com.accAyo.serverProjectDemo.util;

import org.apache.commons.lang.RandomStringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午11:34 2018/5/27
 */
public class RandomUtil {

    public static char[] charArray = new char[] { '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
            'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
            'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
            'W', 'X', 'Y', 'Z', '+', '/' };

    /**
     * 微信支付调起客户端支付时，随机字符串中不能出现+号
     */
    public static char[] charArray2 = new char[] { '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
            'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
            'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
            'W', 'X', 'Y', 'Z'};

    public static long time = 0;

    public static int count = 0;

    public static String localIp = null;

    public static final Object lock = new Object();

    public static String randomString(int count){
        return RandomStringUtils.random(count, charArray);
    }

    /**
     * 微信支付生成随机字符串
     * @param count
     * @return
     */
    public static String randomString4WeixinPay(int count){
        return RandomStringUtils.random(count, charArray2);
    }

    public static String get32Random() {
        synchronized (lock) {
            if (localIp == null) {
                try {
                    InetAddress addr = InetAddress.getLocalHost();
                    localIp = getPackIp(addr.getHostAddress());// 获得本机IP
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                    localIp = "" + Math.random();
                }
            }
            long currTime = System.currentTimeMillis();
            if (time != currTime) {
                time = System.currentTimeMillis();
                count = 0;
            }

            long l = time * 1000 + (++count);
            return localIp + get64Encode(l);
        }
    }

    private static String getPackIp(String ipStr) {
        String packStr = "";
        String[] ips = ipStr.split("\\.");
        for (String ip : ips) {
            packStr += get3Str(Integer.valueOf(ip));
        }

        long l = Long.parseLong(packStr);
        return get64Encode(l);
    }

    private static String get3Str(int number) {
        String s = number + 1000 + "";
        return s.substring(s.length() - 3);
    }

    private static String get64Encode(long l) {
        String rs = "";
        while (l != 0) {
            rs = charArray[(int) (l % 64)] + rs;
            l >>= 6;
        }
        return rs;
    }

    public static List getRandom(List list, int count) {
        System.out.println(count);
        if (list == null || count <= 0)
            return null;
        Map map = new HashMap();
        List tempList = new ArrayList();



        while (tempList.size() < count) {

            if (tempList.size() == list.size())
                break;
            Random r = new Random();
            int i = r.nextInt(list.size());
            if (map.get("" + i) == null) {
                map.put("" + i, "");
                tempList.add(list.get(i));
            }
        }
        return tempList;

    }


    static Random r = new Random(System.currentTimeMillis());


    public static int getHongBao(int totalBi, int totalCount, int leftBi, int leftCount) {
        int avg = totalBi / totalCount;
        if(leftCount == 1)
            return leftBi;
        if((totalCount - leftCount) % 2 == 0) {
            return Math.max(r.nextInt(avg*2), 1);
        } else {
            return leftBi - (leftCount - 1) * avg;
        }
    }

    public static void main(String[] args) {
        int totalBi = 1000;
        int totalCount = 107;
        int leftBi = 1000;
        int leftCount = 107;
        for(int i = 0; i<totalCount; i++) {
            int bi = getHongBao(totalBi, totalCount, leftBi, leftCount);
            System.out.println(i + "__________" + bi);
            leftCount --;
            leftBi -= bi;
        }
    }

}
