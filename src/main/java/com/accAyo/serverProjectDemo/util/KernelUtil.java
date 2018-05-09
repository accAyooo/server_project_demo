package com.accAyo.serverProjectDemo.util;

import java.io.ObjectInputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午11:19 2018/5/8
 */
public class KernelUtil {

    /**
     * @description 获取方法中的泛型
     * @param clazz
     * @return
     */
    public static Class<?> getSuperClassGenericType(Class<?> clazz) {
        // 返回clazz的超类类型
        Type type = clazz.getGenericSuperclass();

        // 强制转换成参数化类型 Collection<String>
        ParameterizedType pt = (ParameterizedType) type;

        // 返回表示此类型的实际类型参数的 TYPE 对象的数组
        Type[] types = pt.getActualTypeArguments();
        return (Class<?>) types[0];
    }
}
