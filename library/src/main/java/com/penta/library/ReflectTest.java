package com.penta.library;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by linyueyang on 2018/11/7.
 */

public class ReflectTest {


    public static class Student {
        String name = "Jim";
        Book book = new Book();
    }

    public static class Book {
        String name = "Love";
    }

    public static void main(String arg[]) {
        long time = System.currentTimeMillis();
        Object student = new Student();
        System.out.println(getFiled(student, "book.name"));
        System.out.println(System.currentTimeMillis() - time);
    }

    public static String getFiled(Object object, String name) {
        String[] names = name.split("\\.");
        List<String> list = new ArrayList(Arrays.asList(names));
        return getFiled(object, list);
    }

    private static String getFiled(Object object, List<String> names) {

        if (names == null || names.size() <= 0) {
            return null;
        }

        try {
            Class clazz = object.getClass();
            Field field = clazz.getDeclaredField(names.get(0));
            names.remove(0);
            if (names.size() > 0) {
                return getFiled(field.get(object), names);
            } else {
                return field.get(object).toString();
            }

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
