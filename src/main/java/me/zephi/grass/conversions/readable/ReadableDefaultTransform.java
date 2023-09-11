package me.zephi.grass.conversions.readable;

import me.zephi.grass.Reflect;
import me.zephi.grass.modifier.bytes.ByteModifier;
import me.zephi.grass.tag.DefaultTransform;
import me.zephi.grass.tag.Tag;
import me.zephi.grass.tag.TransformException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadableDefaultTransform extends DefaultTransform {

    @Override
    @SuppressWarnings("unchecked")
    public Object readData(ByteModifier modifier) {
        try {
            if (!modifier.canRead(1))
                return null;

            Tag<String> objectTag = (Tag<String>) transform().readTag(modifier);

            if (!objectTag.name().equals("type"))
                throw new TransformException("Cannot read tag data.");

            String objectClassName = (String) objectTag.data();

            Tag<Integer> fieldsTag = (Tag<Integer>) transform().readTag(modifier);

            if (!fieldsTag.name().equals("fields"))
                throw new TransformException("Cannot read tag data.");

            int fields = (int) fieldsTag.data();
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

            if (modifier.canRead(Byte.BYTES))
                modifier.readByteChar();

            return data;
        } catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void writeData(ByteModifier modifier, Object data) {
        try {
            if (data == null)
                return;

            Class<?> objectClass = data.getClass();
            String objectClassName = objectClass.getName();

            Tag<String> objectTag = new Tag<>("type", String.class, objectClassName);
            transform().writeTag(modifier, objectTag);

            List<Field> fields = Reflect.getAllNonStaticFields(objectClass);

            Tag<Integer> fieldsTag = new Tag<>("fields", Integer.class, fields.size());
            transform().writeTag(modifier, fieldsTag);

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
