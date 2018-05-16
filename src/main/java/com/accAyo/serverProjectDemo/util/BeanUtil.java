package com.accAyo.serverProjectDemo.util;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午10:52 2018/5/16
 */
public class BeanUtil extends org.apache.commons.beanutils.BeanUtils {

    protected static final Log logger = LogFactory.getLog(BeanUtil.class);

    private BeanUtil() {
    }

    public static Field getDeclaredField(Object object, String propertyName)
            throws NoSuchFieldException {
        Assert.notNull(object);
        Assert.hasText(propertyName);
        return getDeclaredField(object.getClass(), propertyName);
    }

    public static Field getDeclaredField(Class clazz, String propertyName)
            throws NoSuchFieldException {
        Assert.notNull(clazz);
        Assert.hasText(propertyName);
        for (Class superClass = clazz; superClass != Object.class; superClass = superClass
                .getSuperclass()) {
            try {
                return superClass.getDeclaredField(propertyName);
            }
            catch (NoSuchFieldException ex) {
            }
        }
        throw new NoSuchFieldException("No such field: " + clazz.getName()
                + '.' + propertyName);
    }

    public static Object forceGetProperty(Object object, String propertyName)
            throws NoSuchFieldException {
        Assert.notNull(object);
        Assert.hasText(propertyName);
        Object result = null;
        try {
            result = BeanUtilsBean.getInstance().getPropertyUtils()
                    .getProperty(object, propertyName);
            if (result != null) {
                return result;
            }
        }
        catch (Exception e) {

        }

        Field field = getDeclaredField(object, propertyName);

        boolean accessible = field.isAccessible();
        field.setAccessible(true);

        try {
            result = field.get(object);
        }
        catch (IllegalAccessException e) {
            logger.info("error wont' happen");
        }
        field.setAccessible(accessible);
        return result;
    }

    public static void forceSetProperty(Object object, String propertyName,
                                        Object newValue) throws NoSuchFieldException {
        Assert.notNull(object);
        Assert.hasText(propertyName);

        try {
            BeanUtilsBean.getInstance().getPropertyUtils().setProperty(object,
                    propertyName, newValue);
            return;
        }
        catch (Exception e) {
        }

        Field field = getDeclaredField(object, propertyName);
        boolean accessible = field.isAccessible();
        field.setAccessible(true);
        try {
            field.set(object, newValue);
        }
        catch (IllegalAccessException e) {
            logger.info("Error won't happen");
        }
        field.setAccessible(accessible);
    }

    public static Object invokePrivateMethod(Object object, String methodName,
                                             Object... params) throws NoSuchMethodException {
        Assert.notNull(object);
        Assert.hasText(methodName);
        Class[] types = new Class[params.length];
        for (int i = 0; i < params.length; i++) {
            types[i] = params[i].getClass();
        }

        Class clazz = object.getClass();
        Method method = null;
        for (Class superClass = clazz; superClass != Object.class; superClass = superClass
                .getSuperclass()) {
            try {
                method = superClass.getDeclaredMethod(methodName, types);
                break;
            }
            catch (NoSuchMethodException ex) {
            }
        }

        if (method == null) {
            throw new NoSuchMethodException("No Such Method:"
                    + clazz.getSimpleName() + methodName);
        }

        boolean accessible = method.isAccessible();
        method.setAccessible(true);
        Object result = null;
        try {
            result = method.invoke(object, params);
        }
        catch (Exception e) {
            ReflectionUtils.handleReflectionException(e);
        }
        method.setAccessible(accessible);
        return result;
    }

    public static List<Field> getFieldsByType(Object object, Class type) {
        List<Field> list = new ArrayList<Field>();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getType().isAssignableFrom(type)) {
                list.add(field);
            }
        }
        return list;
    }

    public static boolean hasProperty(Object object, Class type, String name){
        List<Field> fields = getFieldsByType(object, type);

        for (Field field : fields) {
            if(field.getName().equals(name))
                return true;
        }
        return false;
    }

    public static Class getPropertyType(Class type, String name)
            throws NoSuchFieldException {
        return getDeclaredField(type, name).getType();
    }

    public static String getGetterName(Class type, String fieldName) {
        Assert.notNull(type, "Type required");
        Assert.hasText(fieldName, "FieldName required");

        if (type.getName().equals("boolean")) {
            return "is" + StringUtils.capitalize(fieldName);
        }
        else {
            return "get" + StringUtils.capitalize(fieldName);
        }
    }

    public static Method getGetterMethod(Class type, String fieldName) {
        try {
            return type.getMethod(getGetterName(type, fieldName));
        }
        catch (NoSuchMethodException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

}
