package com.alatka.messages.util;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ybliu
 */
public class ClassUtil {

    public static Object newInstance(Class<?> clazz) {
        try {
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            return constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static Class<?> forName(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T, S> S invoke(Class<T> clazz, String methodName, Class<?>[] paramTypes, Object[] params) {
        try {
            Method method = clazz.getDeclaredMethod(methodName, paramTypes);
            method.setAccessible(true);
            Constructor<T> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            T instance = constructor.newInstance();
            return (S) method.invoke(instance, params);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T, S> S invoke(T instance, String methodName, Class<?>[] paramTypes, Object[] params) {
        try {
            Method method = instance.getClass().getDeclaredMethod(methodName, paramTypes);
            method.setAccessible(true);
            return (S) method.invoke(instance, params);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static void buildDeclaredFields(Class<?> clazz, List<Field> list) {
        List<Field> result = Stream.of(clazz.getDeclaredFields())
                // Coverage 生成__$hits$__ 属性
                .filter(field -> !"__$hits$__".equals(field.getName()))
                .collect(Collectors.toList());
        list.addAll(result);
        Class<?> superclass = clazz.getSuperclass();
        if (superclass != null && superclass != Object.class) {
            buildDeclaredFields(superclass, list);
        }
    }

    public static void setFieldValue(Object instance, String fieldName, Object value) {
        try {
            Field field = getField(instance, fieldName);
            field.set(instance, value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object getFieldValue(Object instance, String fieldName) {
        try {
            Field field = getField(instance, fieldName);
            return field.get(instance);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static Field getField(Object instance, String fieldName) {
        List<Field> list = new ArrayList<>();
        buildDeclaredFields(instance.getClass(), list);
        Map<String, Field> map = list.stream().collect(Collectors.toMap(Field::getName, Function.identity()));
        Field field = map.get(fieldName);
        field.setAccessible(true);
        return field;
    }

    /**
     * 获取集合泛型类型
     *
     * @param field {@link Field}
     * @return 集合泛型类型
     */
    public static Class<?> getGenericType(Field field) {
        ParameterizedType type = (ParameterizedType) field.getGenericType();
        return (Class<?>) type.getActualTypeArguments()[0];
    }

}
