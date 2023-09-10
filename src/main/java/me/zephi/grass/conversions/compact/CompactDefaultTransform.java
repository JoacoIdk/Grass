package me.zephi.grass.conversions.compact;

import me.zephi.grass.Reflect;
import me.zephi.grass.modifier.bytes.ByteModifier;
import me.zephi.grass.tag.DefaultTransform;
import me.zephi.grass.tag.Tag;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompactDefaultTransform extends DefaultTransform {

    @Override
    public Object readData(ByteModifier modifier) {
        try {
            int discard = modifier.readInt();

            if (discard == 0)
                return null;

            String objectClassName = modifier.readString();
            int fields = modifier.readInt();
            Map<String, Object> map = new HashMap<>();

            for (int i = 0; i < fields; i++) {
                Tag<?> tag = transform().readTag(modifier);

                map.put(tag.name(), tag.data());
            }

            Class<?> objectClass = Class.forName(objectClassName);
            Constructor<?> objectConstructor = objectClass.getConstructor();

            objectConstructor.setAccessible(true);

            Object data = objectConstructor.newInstance();

            for (Field field : Reflect.getAllNonStaticFields(objectClass)) {
                String fieldName = field.getName();

                if (!map.containsKey(fieldName))
                    continue;

                Object fieldValue = map.get(fieldName);

                field.setAccessible(true);
                field.set(data, fieldValue);
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
                modifier.writeInt(0);
                return;
            }

            modifier.writeInt(1);

            Class<?> objectClass = data.getClass();
            String objectClassName = objectClass.getName();

            modifier.writeString(objectClassName);

            List<Field> fields = Reflect.getAllNonStaticFields(objectClass);

            modifier.writeInt(fields.size());

            for (Field field : fields) {
                field.setAccessible(true);

                Object value = field.get(data);

                transform().writeTag(modifier, new Tag<Object>(field.getName(), value.getClass(), value));
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
