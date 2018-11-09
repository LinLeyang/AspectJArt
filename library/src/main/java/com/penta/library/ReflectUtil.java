package com.penta.library;

import android.text.TextUtils;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by linyueyang on 2018/11/8.
 */

public class ReflectUtil {
    public static String getFiled(Object object, String name) {

        if (TextUtils.isEmpty(name)) {
            return object.toString();
        }

        String[] names = name.split("\\.");
        List<String> list = new ArrayList(Arrays.asList(names));
        return getFiled(object, list);
    }

    private static String getFiled(Object object, List<String> names) {

        if (names == null || names.size() <= 0) {
            return null;
        }

        try {
            Log.d("error", names.toString());
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
