package me.zephi.grass;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class Reflect {
    public static List<Field> getAllNonStaticFields(Class<?> cl) {
        List<Field> fields = new ArrayList<>();
        Class<?> current = cl;

        while (current.getSuperclass() != null) {
            for (Field field : current.getDeclaredFields()) {
                if (Modifier.isStatic(field.getModifiers()))
                    continue;

                fields.add(field);
            }

            current = current.getSuperclass();
        }

        return fields;
    }
}
