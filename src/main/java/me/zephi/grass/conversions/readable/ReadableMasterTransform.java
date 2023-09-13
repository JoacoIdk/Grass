package me.zephi.grass.conversions.readable;

import me.zephi.grass.ProtectedObject;
import me.zephi.grass.modifier.bytes.ByteModifier;
import me.zephi.grass.tag.*;

import java.util.HashMap;
import java.util.Map;

// TODO: Make the read / write process better somehow.
public class ReadableMasterTransform implements MasterTransform {
    private final Map<String, TypeTransform<?>> transforms = new HashMap<>();
    private final ProtectedObject<DefaultTransform> defaultTransform = new ProtectedObject<>();

    @Override
    public void registerTransform(TypeTransform<?> transform) {
        if (transform instanceof DefaultTransform)
            throw new TransformException("A DefaultTransform must be registered with the setDefaultTransform(DefaultTransform) method.");

        transform.setMasterTransform(this);

        Class<?> type = transform.getType();
        String typeName = type.getName();
        String typeId = String.valueOf(typeName.hashCode());

        transforms.put(typeId, transform);
    }

    @Override
    public void setDefaultTransform(DefaultTransform transform) {
        transform.setMasterTransform(this);

        defaultTransform.set(transform);
        defaultTransform.lock();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Tag<?> readTag(ByteModifier modifier) {
        if (!modifier.canRead(1))
            return null;

        String name = null;
        String typeName = null;

        StringBuilder buffer = new StringBuilder();
        char read;

        while (modifier.canRead(Byte.BYTES)) {
            read = modifier.readByteChar();

            if (Character.isWhitespace(read))
                continue;

            if (read == '[') {
                if (buffer.isEmpty())
                    throw new TransformException("Buffer is empty on opening bracket.");

                name = buffer.reverse().toString();
                buffer = new StringBuilder();
                continue;
            }

            if (read == ']') {
                if (buffer.isEmpty())
                    throw new TransformException("Buffer is empty on closing bracket.");

                typeName = buffer.reverse().toString();
                buffer = new StringBuilder();
                continue;
            }

            if (read == '{') {
                if (!buffer.isEmpty())
                    throw new TransformException("Buffer is not empty on exit loop.");

                break;
            }

            buffer.insert(0, read);
        }

        if (name == null || typeName == null)
            throw new TransformException("Name or type name is null.");

        TypeTransform<?> transform = transforms.get(typeName);

        if (transform == null)
            transform = defaultTransform.get();

        Class<Object> type = (Class<Object>) transform.getType();
        Object data = transform.readData(modifier);

        return new Tag<>(name, type, data);
    }

    @Override
    public void writeTag(ByteModifier modifier, Tag<?> tag) {
        if (tag == null)
            return;

        String name = tag.name();
        Class<?> type = tag.type();
        String typeName = type == null ? null : type.getName();
        String typeId = String.valueOf(typeName == null ? null : typeName.hashCode());

        if (name == null || typeName == null)
            throw new TransformException("Name or type name is null.");

        TypeTransform<?> transform = transforms.get(typeName);

        if (transform == null)
            transform = defaultTransform.get();

        Object data = tag.data();

        modifier.writeBytesString(name + '[' + typeId + ']');

        modifier.writeByteChar('{');
        transform.writeUnsafeData(modifier, data);
        modifier.writeByteChar('}');
    }
}
