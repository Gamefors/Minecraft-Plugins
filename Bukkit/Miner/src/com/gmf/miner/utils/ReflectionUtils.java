package com.gmf.miner.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionUtils {


    public static Object invokeMethod(Object obj, String name, boolean declared) throws Exception {
        Method met = declared ? obj.getClass().getDeclaredMethod(name) : obj.getClass().getMethod(name);
        return met.invoke(obj);
    }

    public static Field getField(Object clazz, String name, boolean declared) throws Exception {
        return getField(clazz.getClass(), name, declared);
    }

    public static Field getField(Class<?> clazz, String name, boolean declared) throws Exception {
        Field field = declared ? clazz.getDeclaredField(name) : clazz.getField(name);
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }

        return field;
    }


}
