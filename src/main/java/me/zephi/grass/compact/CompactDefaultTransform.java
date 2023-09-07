package me.zephi.grass.compact;

import me.zephi.grass.modifier.ByteModifier;
import me.zephi.grass.tag.DefaultTransform;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CompactDefaultTransform implements DefaultTransform {
    private static final Map<Integer, String> intToType = new HashMap<>();
    private static final Map<String, Integer> typeToInt = new HashMap<>();

    static { // Boolean Byte Char Short Int Float Long Double
        intToType.put(0, "boolean");
        intToType.put(1, "byte");
        intToType.put(2, "char");
        intToType.put(3, "short");
        intToType.put(4, "int");
        intToType.put(5, "float");
        intToType.put(6, "long");
        intToType.put(7, "double");
        // Object is 8
        // Null is 9

        typeToInt.put("boolean", 0);
        typeToInt.put("byte", 1);
        typeToInt.put("char", 2);
        typeToInt.put("short", 3);
        typeToInt.put("int", 4);
        typeToInt.put("float", 5);
        typeToInt.put("long", 6);
        typeToInt.put("double", 7);
        // Object is 8
        // Null is 9
    }

    @Override
    public Object readData(ByteModifier modifier) {
        try {
            int type = modifier.readInt();

            if (type == 9)
                return null;

            if (intToType.containsKey(type)) {
                String objectClassName = intToType.get(type);
                Class<?> objectClass = Class.forName(objectClassName);

                String prettyObjectClassName = Character.toUpperCase(objectClassName.charAt(0)) + objectClassName.substring(1);
                String methodName = "read%s".formatted(prettyObjectClassName);
                Class<?> modifierClass = modifier.getClass();

                Method method = modifierClass.getMethod(methodName);
                method.setAccessible(true);
                Object data = method.invoke(modifier);

                return objectClass.cast(data);
            }

            String objectClassName = modifier.readString();
            Class<?> objectClass = Class.forName(objectClassName);
            Constructor<?> objectConstructor = objectClass.getConstructor();

            objectConstructor.setAccessible(true);

            Object data = objectConstructor.newInstance();

            for (Field field : objectClass.getFields()) {
                field.setAccessible(true);

                field.set(data, readData(modifier));
            }

            return data;
        } catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | IllegalAccessException |
                 InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void writeData(ByteModifier modifier, Object data) {
        try {
            if (data == null) {
                modifier.writeInt(9);

                return;
            }

            Class<?> objectClass = data.getClass();
            String objectClassName = objectClass.getName();

            if (typeToInt.containsKey(objectClassName)) {
                int type = typeToInt.get(objectClassName);
                modifier.writeInt(type);

                String prettyObjectClassName = Character.toUpperCase(objectClassName.charAt(0)) + objectClassName.substring(1);
                String methodName = "write%s".formatted(prettyObjectClassName);
                Class<?> modifierClass = modifier.getClass();

                Method method = modifierClass.getMethod(methodName, objectClass);
                method.setAccessible(true);
                method.invoke(modifier, data);

                return;
            }

            modifier.writeInt(8);
            modifier.writeString(objectClassName);

            for (Field field : objectClass.getFields()) {
                field.setAccessible(true);

                Object value = field.get(data);

                writeData(modifier, value);
            }
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
